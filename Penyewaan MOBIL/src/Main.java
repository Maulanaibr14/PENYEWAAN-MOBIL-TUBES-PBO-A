// File: Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Membuat objek sistem penyewaan
        RentalSystem rentalSystem = new RentalSystem();

        // Menambahkan 5 mobil ke sistem penyewaan dengan harga sewa
        Car car1 = new Car("Toyota", "Camry", 2022, 200000);
        Car car2 = new Car("Honda", "Accord", 2023, 250000);
        Car car3 = new Car("Ford", "Mustang", 2023, 300000);
        Car car4 = new Car("Chevrolet", "Camaro", 2022, 280000);
        Car car5 = new Car("Nissan", "Altima", 2022, 220000);

        rentalSystem.addRentable(car1);
        rentalSystem.addRentable(car2);
        rentalSystem.addRentable(car3);
        rentalSystem.addRentable(car4);
        rentalSystem.addRentable(car5);

        // Menampilkan informasi kendaraan yang dapat disewa
        System.out.println("Available Vehicles:");
        rentalSystem.displayRentableInfo();

        // Meminta input pengguna untuk memilih mobil yang ingin disewa
        System.out.print("\nEnter the number of the car you want to rent: ");
        int selectedCarIndex = scanner.nextInt();

        // Memastikan bahwa indeks yang dimasukkan valid
        if (selectedCarIndex >= 1 && selectedCarIndex <= rentalSystem.rentableList.size()) {
            Rentable selectedCar = rentalSystem.rentableList.get(selectedCarIndex - 1);

            // Meminta input identitas penyewa
            System.out.print("Enter your name: ");
            scanner.nextLine();  // Membersihkan newline dari buffer
            String name = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();

            Customer customer = new Customer(name, phoneNumber);

            // Meminta input durasi penyewaan dari pengguna
            System.out.print("Enter the number of days you want to rent the car: ");
            int rentalDays = scanner.nextInt();

            // Menyewa mobil yang dipilih
            selectedCar.rent(customer, rentalDays);

            // Menampilkan nama mobil yang disewa dan informasi penyewa
            if (selectedCar instanceof Vehicle) {
                Vehicle selectedVehicle = (Vehicle) selectedCar;
                System.out.println("\nYou have rented the following car for " + rentalDays + " days:");
                selectedVehicle.displayInfo();
                System.out.println("Total Rental Price: " + calculateTotalPrice(selectedCar, rentalDays) + " IDR");
                System.out.println("Renter Information:");
                System.out.println("Name: " + customer.getName());
                System.out.println("Phone Number: " + customer.getPhoneNumber());
            }
        } else {
            System.out.println("Invalid car selection.");
        }

        // Menutup scanner
        scanner.close();
    }

    // Metode untuk menghitung total biaya penyewaan
    private static int calculateTotalPrice(Rentable rentable, int rentalDays) {
        return rentable.getRentalPrice() * rentalDays;
    }
}
