// File: Rentable.java
public interface Rentable {
    void rent(Customer customer, int rentalDays);

    void returnVehicle();

    int getRentalPrice();
}
