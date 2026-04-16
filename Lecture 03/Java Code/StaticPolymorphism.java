/**
 * Static Polymorphism (Compile-Time Polymorphism) in Java
 * Achieved through Method Overloading
 *
 * Method overloading allows multiple methods with the same name
 * but different parameter lists within the same class.
 */

class Car {
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear()     { return year; }

    // Overloaded method: no parameters
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year);
    }

    // Overloaded method: with a label parameter
    public void displayInfo(String label) {
        System.out.println("[" + label + "] Brand: " + brand + ", Model: " + model + ", Year: " + year);
    }

    // Overloaded method: with label and include-year flag
    public void displayInfo(String label, boolean includeYear) {
        if (includeYear) {
            System.out.println("[" + label + "] Brand: " + brand + ", Model: " + model + ", Year: " + year);
        } else {
            System.out.println("[" + label + "] Brand: " + brand + ", Model: " + model);
        }
    }

    // Overloaded: calculate fuel cost for given distance (km) at fixed rate
    // Note: updated default rate to 6.5 to better reflect current fuel prices
    public double calculateFuelCost(double distanceKm) {
        double ratePerKm = 6.5; // updated default rate in currency units
        return distanceKm * ratePerKm;
    }

    // Overloaded: calculate fuel cost with custom rate
    public double calculateFuelCost(double distanceKm, double ratePerKm) {
        return distanceKm * ratePerKm;
    }

    // Overloaded: calculate fuel cost with distance, rate, and efficiency multiplier
    public double calculateFuelCost(double distanceKm, double ratePerKm, double efficiencyMultiplier) {
        return distanceKm * ratePerKm * efficiencyMultiplier;
    }
}

public class StaticPolymorphism {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla", 2022);

        System.out.println("=== Method Overloading: displayInfo ===");

        // Calls displayInfo()
        car.displayInfo();

        // Calls displayInfo(String)
        car.displayInfo("Featured Car");

        // Calls displayInfo(String, boolean) — with year
        car.displayInfo("Featured Car", true);

        // Calls displayInfo(String, boolean) — without year
        car.displayInfo("Featured Car", false);

        System.out.println();
        System.out.println("=== Method Overloading: calculateFuelCost ===");

        double distance = 150.0;

        // Calls calculateFuelCost(double)
        double cost1 = car.calculateFuelCost(distance);
        System.out.println("Fuel cost (default rate) for " + distance + " km: " + cost1);

        // Calls calculateFuelCost(double, double)
        double cost2 = car.calculateFuelCost(distance, 8.0);
        System.out.println("Fuel cost (custom rate 8.0) for " + distance + " km: " + cost2);

        // Calls calculateFuelCost(double, double, double)
        double cost3 = car.calculateFuelCost(distance, 8.0, 0.9);
        System.out.println("Fuel cost (custom rate 8.0, efficiency 0.9) for " + distance + " km: " + cost3);
    }
}
