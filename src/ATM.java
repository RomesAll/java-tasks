/*
    Практическое занятие №1 состоит из выполнения двух заданий, формулировка которых
    приводится ниже.

    Задание 1. Разработать программу, которая выполняет следующие
    действия:

      1) для введенной пользователем суммы в рублях рассчитывает количество купюр
    различного номинала для выдачи банкоматом введенной суммы денег
    наименьшим количеством рублевых купюр;

      2) выводит информацию о количестве выданных купюр каждого номинала.
    В качестве результата выводится количество выданных купюр каждого номинала.
    Пользователем задается: сумма в рублях для выдачи банкоматом, кратная 100 и не
    превышающая 100 000.

    Важно: минимальный номинал купюры для выдачи банкоматом - 100. При выводе
    результатов на экран пользователь должен понимать, какие результаты выводятся, то есть
    выводимые результаты должны сопровождаться текстовыми пояснениями.
 */

import java.util.HashMap;
import java.util.Scanner;

// Реализация с помощью жадных алгоритмов
public static class ATM {

    protected static int[] denomination = new int[]{5000, 1000, 500, 200, 100};

    private static HashMap<Integer, Integer> counting_denomination(int input_sum){
        if (!(input_sum >= 100 && input_sum <= 100_000 && input_sum % 100 == 0))
            throw new IllegalArgumentException("Число должно быть от 100 до 100_000 и кратна 100");
        HashMap<Integer, Integer> denomination_info = new HashMap<>();
        for (int i=0; i < denomination.length; i++){
            while (input_sum >= denomination[i]) {
                if (denomination[i] <= input_sum) {
                    int current_count = denomination_info.getOrDefault(denomination[i], 0);
                    denomination_info.put(denomination[i], current_count + 1);
                    input_sum -= denomination[i];
                }
            }
        }
        return denomination_info;
    }
}

public class ClientATM {

    protected static Class<ATM> atm = ATM.class;
    protected int input_sum = 0;
    public String name;

    public ClientATM(String name) {
        this.name = name;
    }

    public void input_amount_money(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сумму денег для получения в рублях: ");
        this.input_sum = scanner.nextInt();
    }

    public void get_bills(){
        if (this.input_sum == 0)
            throw new IllegalArgumentException("Число не может быть нулем!");
        HashMap<Integer, Integer> denomination_info = ATM.counting_denomination(this.input_sum);
        Collection<Integer> values = denomination_info.values();
        short count_bills = 0;
        for (Integer value : values) {
            count_bills += value;
        }
        System.out.println("Кол-во купюр: " + count_bills);
        System.out.println("Информация о каждой купюре: " + denomination_info);
    }
}

void main(){
    ClientATM client1 = new ClientATM("Beskokotov Roman");
    client1.input_amount_money();
    client1.get_bills();
}