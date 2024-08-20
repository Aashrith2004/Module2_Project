import java.util.Scanner;
class Vehicle {
    private String licensePlate;
    private String model;
    private boolean isAvailable;

    public Vehicle(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.isAvailable = true;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public double calculateRentalCost(int days) {
        return 0.0;
    }

    @Override
    public String toString() {
        return model + " (License: " + licensePlate + ")";
    }
}
class Car extends Vehicle {
    private double ratePerDay;

    public Car(String licensePlate, String model, double ratePerDay) {
        super(licensePlate, model);
        this.ratePerDay = ratePerDay;
    }

    @Override
    public double calculateRentalCost(int days) {
        return ratePerDay * days;
    }
}
class Bike extends Vehicle {
    private double ratePerDay;

    public Bike(String licensePlate, String model, double ratePerDay) {
        super(licensePlate, model);
        this.ratePerDay = ratePerDay;
    }
    @Override
    public double calculateRentalCost(int days) {
        return ratePerDay * days;
    }
}
class Customer {
    private String name;
    private String driverLicense;

    public Customer(String name, String driverLicense) {
        this.name = name;
        this.driverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " (Driver License: " + driverLicense + ")";
    }
}
class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int rentalDays;
    private double totalCost;

    public Rental(Vehicle vehicle, Customer customer, int rentalDays) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;
        this.totalCost = vehicle.calculateRentalCost(rentalDays);
        vehicle.setAvailable(false); // Mark vehicle as rented
    }

    public void completeRental() {
        vehicle.setAvailable(true);
        System.out.println("Rental completed for " + customer.getName() + ". Total cost: $" + totalCost);
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() +
               "\nVehicle: " + vehicle.getModel() +
               "\nRental Days: " + rentalDays +
               "\nTotal Cost: $" + totalCost;
    }
}
public class RentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vehicle type (Car/Bike): ");
        String vehicleType = scanner.nextLine();

        System.out.println("Enter license plate: ");
        String licensePlate = scanner.nextLine();

        System.out.println("Enter model: ");
        String model = scanner.nextLine();

        System.out.println("Enter rate per day: ");
        double ratePerDay = scanner.nextDouble();
        scanner.nextLine();

        Vehicle vehicle;
        if (vehicleType.equalsIgnoreCase("Car")) {
            vehicle = new Car(licensePlate, model, ratePerDay);
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            vehicle = new Bike(licensePlate, model, ratePerDay);
        } else {
            System.out.println("Invalid vehicle type.");
            scanner.close();
            return;
        }
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter driver license number: ");
        String driverLicense = scanner.nextLine();

        Customer customer = new Customer(customerName, driverLicense);
        System.out.println("Enter rental days: ");
        int rentalDays = scanner.nextInt();
        Rental rental = new Rental(vehicle, customer, rentalDays);
        System.out.println(rental);
        rental.completeRental();
        scanner.close();
    }
}
