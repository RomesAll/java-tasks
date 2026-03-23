import java.util.HashMap;

public class Car {
    public int count_passengers;
    public int fuel_capacity_liters;
    public int engine_power;
    public float fuel_consumption_100km;
    public int trunk_volume_liters;
    public int mileage_kilometers;
    protected static HashMap<Integer, Integer> tax_amount = new HashMap<Integer, Integer>(){{
        put(150, 22);
        put(200, 35);
        put(250, 56);
    }};

    public Car(int count_passengers, int fuel_capacity_liters,
               int engine_power, float fuel_consumption_100km,
               int trunk_volume_liters, int mileage_kilometers){
        this.count_passengers = count_passengers;
        this.fuel_capacity_liters = fuel_capacity_liters;
        this.engine_power = engine_power;
        this.fuel_consumption_100km = fuel_consumption_100km;
        this.trunk_volume_liters = trunk_volume_liters;
        this.mileage_kilometers = mileage_kilometers;
    }

    public int get_count_passengers(){
        // Метод для получения макс. кол-ва пассажиров
        System.out.println("Кол-во пассажиров: " + this.count_passengers);
        return this.count_passengers;
    }

    public float get_max_travel_distance(){
        // Метод для подсчета макс. дистанции с полным баком
        float max_travel = this.fuel_capacity_liters / this.fuel_consumption_100km * 100;
        System.out.println("Максимальная дистанция с полным баком (км): " + Math.round(max_travel));
        return Math.round(max_travel);
    }

    public float get_required_amount_fuel(int distance){
        // Метод для подсчета топлива, которого нужно потратить, чтобы преодолеть дистанцию
        float fuel = (float) distance / 100 * this.fuel_consumption_100km;
        System.out.println("Для расстояния в " + distance + " км. нужно " + fuel + " литров");
        return fuel;
    }

    public int get_mileage_kilometers(){
        // Метод для получения пробега
        System.out.println("Пробег: " + this.mileage_kilometers + " км.");
        return this.mileage_kilometers;
    }

    public float calculation_transport_tax_amount(){
        // Метод для подсчета транспартного налога
        for (int key: tax_amount.keySet()){
            if (tax_amount.get(key) >= this.engine_power) {
                float transport_tax = tax_amount.get(key) * this.engine_power;
                System.out.println("Транспортный налог: " + transport_tax);
                return transport_tax;
            }
        }
        return 0.0f;
    }

    public float daily_rental_cost(){
        // Метод для подсчета суточной аренды
        float rental_cost = (float) (2000 * (0.01 * this.trunk_volume_liters + 0.1 * this.count_passengers));
        System.out.println("Стоимость суточной аренды: " + rental_cost);
        return rental_cost;
    }
}

void main(){
    Car car = new Car(4, 70,
            155, 9,
            300, 100);

    car.calculation_transport_tax_amount();
    car.daily_rental_cost();
    car.get_required_amount_fuel(1000);
    car.get_max_travel_distance();
    car.get_count_passengers();
    car.get_mileage_kilometers();
}

