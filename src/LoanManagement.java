public class LoanManagement {

    protected double loan_amounts;
    protected double loan_term;
    protected float interest_rates;

    public LoanManagement(double loan_amounts, double loan_term, float interest_rates){
        check_input_data(loan_amounts, loan_term, interest_rates);
        this.loan_amounts = loan_amounts;
        this.loan_term = loan_term;
        this.interest_rates = interest_rates;
    }

    private static void check_input_data(double loan_amounts, double loan_term, float interest_rates){
        if (!(loan_amounts >= 5_000 && loan_amounts <= 1_000_000)){
            throw new IllegalArgumentException("");
        }
        if (!(loan_term >= 0.5 && loan_term <= 10)){
            throw new IllegalArgumentException("");
        }
        if (!(interest_rates >= 0 && interest_rates <= 100)){
            throw new IllegalArgumentException("");
        }
    }

    public void calculation_differentiated_payment(){
        double basic_payment = this.loan_amounts / this.loan_term;
        float monthly_rate = this.interest_rates / 12 / 100;

        double balance_debt = this.loan_amounts;
        double result_payment = 0;

        System.out.println("Дифференцированный платеж");
        System.out.println("Сумма: " + this.loan_amounts);
        System.out.println("Срок: " + this.loan_term + " месяцев");
        System.out.println("Ставка: " + this.interest_rates + "%");
        System.out.println("Основной платеж: " + basic_payment + " руб.");

        for (short i=1; i <= this.loan_term; i++){
            double current_payment = basic_payment + balance_debt * monthly_rate;
            result_payment += current_payment;
            balance_debt -= basic_payment;

            System.out.printf("%2s месяц: %.2f руб. остаток: %.2f \n", i, current_payment, balance_debt);
        }
        System.out.println("Всего: " + Math.round(result_payment) + " руб.");
    }

    public void calculation_annuity_payment(){
        System.out.println("Аннуитетный платеж");
        System.out.println("Сумма: " + this.loan_amounts);
        System.out.println("Срок: " + this.loan_term + " месяцев");
        System.out.println("Ставка: " + this.interest_rates + "%");
        double monthly_rate = (float)this.interest_rates/100/12;
        double annuity = this.loan_amounts * (monthly_rate * Math.pow(1 + monthly_rate, this.loan_term))
                / (Math.pow(1 + monthly_rate, loan_term) - 1);
        double total_payment = annuity * this.loan_term;
        System.out.printf("Аннуитетный платеж: %.2f руб.\n", annuity);
        System.out.printf("Общая сумма выплат: %.2f руб.\n", total_payment);
    }
}


void main(){
    LoanManagement l = new LoanManagement(100_000, (float) 10,  12);
}