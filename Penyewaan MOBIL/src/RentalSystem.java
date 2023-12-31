// File: RentalSystem.java
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    List<Rentable> rentableList;

    public RentalSystem() {
        this.rentableList = new ArrayList<>();
    }

    public void addRentable(Rentable rentable) {
        rentableList.add(rentable);
    }

    public void displayRentableInfo() {
        for (Rentable rentable : rentableList) {
            if (rentable instanceof Vehicle) {
                Vehicle vehicle = (Vehicle) rentable;
                vehicle.displayInfo();
                System.out.println("Rental Price: " + ((Car) rentable).getRentalPrice() + " IDR per day");
                System.out.println();
            }
        }
    }
}
