import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.Collections;

public class AccountManagement {

    public ArrayList<String> list_user;
    public FileManager __file_manager;

    public AccountManagement(FileManager file_manager){
        this.__file_manager = file_manager;
        this.list_user = this.__file_manager.read();
    }

    public void user_processing(){
        for (int i=0; i < this.list_user.size(); i++){
            ArrayList<String> user_info = new ArrayList<>(Arrays.asList(this.list_user.get(i).split(";")));
            if (!(validation_email(user_info.get(3)) && validation_phone(user_info.get(4)))){
                FileManager file = new FileManager(this.__file_manager.get_parent() + "/incorrect_user_info.txt");
                file.write(this.list_user.get(i), true);
            }
            String email = generate_email(user_info.subList(0, 3));
            String psw = generate_psw();
            this.list_user.set(i, this.list_user.get(i) + ";" + email + ";" + psw);
            user_info.add(email);
            user_info.add(psw);
        }
        this.__file_manager.write(this.list_user, false);
    }

    public static boolean validation_email(String email){
        return Pattern.matches("[a-zA-Z_+%-\\.]+[^\\.]@[a-z]+\\.(ru|com)", email);
    }

    public static boolean validation_phone(String phone){
        return Pattern.matches("(\\+7|8)(\\d{3})(\\d{3})(\\d{2})(\\d{2})", phone);
    }

    public static String generate_psw(){
        final String ALP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String ALL_CHARS = ALP + DIGITS;
        final int PASSWORD_LENGTH = 10;
        final int MIN_DIGITS = 2;

        Random rand = new Random();
        List<Character> result = new ArrayList<>();

        for (int i=0; i<MIN_DIGITS; i++){
            result.add(DIGITS.charAt(rand.nextInt(DIGITS.length())));
        }
        for(int j=2; j<=PASSWORD_LENGTH-3; j++){
            result.add(ALL_CHARS.charAt(rand.nextInt(ALL_CHARS.length())));
        }

        Collections.shuffle(result);
        return result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static String generate_email(List<String> fio){
        String prefix = fio.getFirst().toLowerCase() + "_" + fio.get(1).toLowerCase().charAt(0) + "_" + fio.getLast().toLowerCase().charAt(0);
        return prefix + "@company.ru";
    }

}

void main(){
    AccountManagement a = new AccountManagement(new FileManager("/home/roman/Desktop/java-tasks/src/user.txt"));
    a.user_processing();
}