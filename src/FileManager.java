import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public String file_path;
    public File file;

    public FileManager(String file_path){
        this.file_path = file_path;
        this.file = new File(file_path);
        create_file();
    }

    public void create_file(){
        try {
            if (this.file.createNewFile()) {
                System.out.println("Файл создан: " + this.file.getName());
            }
        } catch (IOException e) {
            System.out.println("Ошибка создания файла");
            e.printStackTrace();
        }
    }

    public ArrayList<String> read() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.file.getAbsoluteFile()))) {
            ArrayList<String> list = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(ArrayList<String> data, boolean append){
        try (FileWriter writer = new FileWriter(this.file.getAbsoluteFile(), append)) {
            for (String line: data){
                writer.write(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String data, boolean append){
        try (FileWriter writer = new FileWriter(this.file.getAbsoluteFile(), append)) {
            writer.write(data+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get_parent(){
        return this.file.getParent();
    }
}
