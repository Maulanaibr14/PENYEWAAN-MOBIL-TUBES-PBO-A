// File: Car.java
public class Car extends Vehicle implements Rentable {
    private boolean isRented;
    private int rentalPrice;

    public Car(String brand, String model, int year, int rentalPrice) {
        super(brand, model, year);
        this.isRented = false;
        this.rentalPrice = rentalPrice;
    }

    @Override
    void displayInfo() {
        System.out.println("Car: " + year + " " + brand + " " + model);
    }

    @Override
    public void rent(Customer customer, int rentalDays) {
        if (!isRented) {
            System.out.println(customer.getName() + " is renting the car for " + rentalDays + " days...");
            isRented = true;
        } else {
            System.out.println("Car is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (isRented) {
            System.out.println("Returning the car...");
            isRented = false;
        } else {
            System.out.println("Car is not rented.");
        }
    }

    @Override
    public int getRentalPrice() {
        return rentalPrice;
    }
}
