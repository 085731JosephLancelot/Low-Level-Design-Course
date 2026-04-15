/**
 * Demonstrates Dynamic Polymorphism (Runtime Polymorphism) in Java.
 *
 * Dynamic polymorphism is achieved through method overriding.
 * The method to be called is determined at runtime based on the
 * actual object type, not the reference type.
 *
 * Key Concepts:
 * - Method Overriding
 * - Runtime binding / Late binding
 * - Upcasting
 * - The 'super' keyword
 */

// Abstract base class representing a generic Car
abstract class Car {
    protected String brand;
    protected String model;
    protected int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Abstract method — subclasses MUST override this
    public abstract void start();

    // Abstract method — subclasses MUST override this
    public abstract void stop();

    // Concrete method — can be overridden but doesn't have to be
    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year : " + year);
    }

    // Concrete method demonstrating a shared behaviour
    public void honk() {
        System.out.println(brand + " " + model + " goes: Beep Beep!");
    }
}

// ManualCar — a concrete subclass of Car
class ManualCar extends Car {
    private int gears;

    public ManualCar(String brand, String model, int year, int gears) {
        super(brand, model, year);
        this.gears = gears;
    }

    @Override
    public void start() {
        System.out.println(brand + " " + model + ": Clutch pressed, key turned — Vroom! Engine started.");
    }

    @Override
    public void stop() {
        System.out.println(brand + " " + model + ": Gear to neutral, handbrake applied — Engine stopped.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();  // reuse parent implementation
        System.out.println("Type  : Manual");
        System.out.println("Gears : " + gears);
    }

    public void shiftGear(int gear) {
        if (gear < 1 || gear > gears) {
            System.out.println("Invalid gear! Valid range: 1 to " + gears);
        } else {
            System.out.println(brand + " " + model + ": Shifted to gear " + gear);
        }
    }
}

// ElectricCar — another concrete subclass of Car
class ElectricCar extends Car {
    private int batteryCapacityKWh;
    private int rangeKm;

    public ElectricCar(String brand, String model, int year,
                       int batteryCapacityKWh, int rangeKm) {
        super(brand, model, year);
        this.batteryCapacityKWh = batteryCapacityKWh;
        this.rangeKm = rangeKm;
    }

    @Override
    public void start() {
        System.out.println(brand + " " + model + ": Button pressed — Silent hum... Electric motor engaged.");
    }

    @Override
    public void stop() {
        System.out.println(brand + " " + model + ": Regenerative braking activated — Motor disengaged.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();  // reuse parent implementation
        System.out.println("Type    : Electric");
        System.out.println("Battery : " + batteryCapacityKWh + " kWh");
        System.out.println("Range   : " + rangeKm + " km");
    }

    public void chargeBattery() {
        System.out.println(brand + " " + model + ": Plugged in — Charging battery...");
    }
}

// Main class to demonstrate dynamic polymorphism
public class DynamicPolymorphism {
    public static void main(String[] args) {

        // Upcasting: parent reference holding child objects
        // The actual method called is determined at RUNTIME
        Car[] cars = {
            new ManualCar("Toyota", "Corolla", 2020, 5),
            new ElectricCar("Tesla", "Model 3", 2023, 75, 570)
        };

        System.out.println("===== Dynamic Polymorphism Demo =====");

        for (Car car : cars) {
            System.out.println("\n--- Car Info ---");
            car.displayInfo();          // overridden method — resolved at runtime
            System.out.println();
            car.start();                // overridden method — resolved at runtime
            car.honk();                 // inherited, not overridden
            car.stop();                 // overridden method — resolved at runtime
        }

        System.out.println("\n===== Downcasting Demo =====");

        // Downcasting to access subclass-specific methods
        Car myCar = new ManualCar("Honda", "Civic", 2021, 6);
        if (myCar instanceof ManualCar) {
            ManualCar manualCar = (ManualCar) myCar;  // explicit downcast
            manualCar.start();
            manualCar.shiftGear(3);
            manualCar.shiftGear(8);  // invalid gear
        }

        Car myEV = new ElectricCar("BMW", "iX", 2022, 100, 630);
        if (myEV instanceof ElectricCar) {
            ElectricCar electricCar = (ElectricCar) myEV;  // explicit downcast
            electricCar.start();
            electricCar.chargeBattery();
        }
    }
}
