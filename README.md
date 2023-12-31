# 1.	Identifikasi Masalah
Program ini dibuat untuk memodelkan sistem penyewaan mobil sederhana. Tujuan utama program adalah memberikan fungsionalitas untuk menambahkan mobil ke dalam sistem penyewaan, menampilkan daftar mobil yang tersedia, memungkinkan pengguna memilih mobil yang ingin disewa, mengumpulkan informasi penyewa, dan menghitung total biaya penyewaan.

# 2.	Siapa yang terlibat
•	Pengembang (Programmer): Bertanggung jawab untuk merancang dan mengimplementasikan program.
•	Pengguna: Orang yang akan menggunakan program ini untuk menyewa mobil.

# 3.	Data apa saja yang disimpan dan diolah
•	Data yang Disimpan:
Informasi tentang setiap mobil (merek, model, tahun, harga sewa).
Informasi penyewa (nama, nomor telepon).
•	Data yang Diolah:
Pilihan pengguna saat memilih mobil.
Durasi penyewaan yang dimasukkan oleh pengguna.
Total biaya penyewaan yang dihitung berdasarkan harga sewa per hari dan durasi penyewaan.

# 4.	Turunan/Relasi/Abstract/Interface
•	Turunan:
Car adalah turunan dari kelas abstrak Vehicle dan mengimplementasikan antarmuka Rentable.
Customer adalah kelas terpisah yang menyimpan informasi tentang penyewa.
•	Relasi:
Antara RentalSystem dan Rentable terdapat relasi agregasi, di mana RentalSystem memiliki daftar Rentable (mobil-mobil yang dapat disewa).
•	Interface:
Rentable adalah antarmuka yang diimplementasikan oleh kelas Car untuk menyediakan metode terkait penyewaan.

# 5.	Skenario :
•	Menambahkan Mobil ke Sistem:
Pengembang menambahkan mobil ke dalam sistem dengan informasi merek, model, tahun, dan harga sewa.
•	Menampilkan Daftar Mobil yang Tersedia:
Pengguna menjalankan program dan melihat daftar mobil yang tersedia beserta harga sewanya.
•	Memilih Mobil yang Ingin Disewa:
Pengguna memilih mobil dengan memasukkan nomor mobil yang diinginkan.
•	Memasukkan Identitas Diri sebagai Penyewa:
Pengguna memasukkan nama dan nomor telepon mereka sebagai identitas penyewa.
•	Memasukkan Durasi Penyewaan:
Pengguna memasukkan jumlah hari yang ingin mereka sewa mobil.
•	Menyewa Mobil:
Program menyewa mobil yang dipilih oleh pengguna dan menampilkan informasi nama mobil, total biaya penyewaan, serta informasi penyewa.
•	Mengembalikan Mobil:
Jika diperlukan, pengguna dapat mengembalikan mobil setelah periode penyewaan berakhir.

Source Code

Main.Class :

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

Car.class :

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

Customer.class :

// File: Customer.java
public class Customer {
    private String name;
    private String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

Rentable.class :

// File: Rentable.java
public interface Rentable {
    void rent(Customer customer, int rentalDays);

    void returnVehicle();

    int getRentalPrice();
}

RentalSystem.class :
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

Vehicle :

// File: Vehicle.java
public abstract class Vehicle {
    String brand;
    String model;
    int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    abstract void displayInfo();
}
