import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Bicycle
class Bicycle {
    private String id;
    private boolean isHired;
    private double pricePerHour; // Price rate per hour
    private LocalDateTime startTime; // Time when the bicycle is hired

    // Constructor to initialize Bicycle with an ID and price rate
    public Bicycle(String id, double pricePerHour) {
        this.id = id;
        this.pricePerHour = pricePerHour;
        this.isHired = false; // By default, the bicycle is not hired
    }

    // Getter method for bicycle ID
    public String getId() {
        return id;
    }

    // Getter method to check if the bicycle is hired
    public boolean isHired() {
        return isHired;
    }

    // Setter method to update the hire status of the bicycle
    public void setHired(boolean isHired) {
        this.isHired = isHired;
    }

    // Getter method for price rate per hour
    public double getPricePerHour() {
        return pricePerHour;
    }

    // Method to record the start time when the bicycle is hired
    public void startHire() {
        startTime = LocalDateTime.now();
    }

    // Method to calculate the duration of hire in hours
    public double calculateHireDuration() {
        LocalDateTime endTime = LocalDateTime.now();
        long durationInMinutes = java.time.Duration.between(startTime, endTime).toMinutes();
        return durationInMinutes / 60.0; // Convert minutes to hours
    }

    // Method to calculate the cost of hire based on duration and price rate
    public double calculateHireCost() {
        double durationInHours = calculateHireDuration();
        return durationInHours * pricePerHour;
    }
}

// Main class for Bicycle Hiring System
public class BicycleHiringSystem {
    private static ArrayList<Bicycle> bicycles = new ArrayList<>(); // ArrayList to store bicycles
    private static Scanner scanner = new Scanner(System.in); // Scanner object for user input

    public static void main(String[] args) {
        // Initialize bicycles with price rates per hour
        initializeBicycles();

        while (true) {
            // Display menu options
            System.out.println("1. Hire a bicycle");
            System.out.println("2. Return a bicycle");
            System.out.println("3. Show available bicycles and rates");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); // Read user choice
            scanner.nextLine();  // Consume newline character

            // Process user choice
            switch (choice) {
                case 1:
                    hireBicycle(); // Call method to hire a bicycle
                    break;
                case 2:
                    returnBicycle(); // Call method to return a bicycle
                    break;
                case 3:
                    showAvailableBicycles(); // Call method to show available bicycles and rates
                    break;
                case 4:
                    System.out.println("Exiting..."); // Exit the program
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option."); // Handle invalid input
            }
        }
    }

    // Method to initialize bicycles with price rates per hour
    private static void initializeBicycles() {
        bicycles.add(new Bicycle("B001", 100.0)); // Bicycle ID: B001, Price rate: 100 KES per hour
        bicycles.add(new Bicycle("B002", 150.0)); // Bicycle ID: B002, Price rate: 150 KES per hour
        bicycles.add(new Bicycle("B003", 120.0)); // Bicycle ID: B003, Price rate: 120 KES per hour
        bicycles.add(new Bicycle("B004", 200.0)); // Bicycle ID: B004, Price rate: 200 KES per hour
    }

    // Method to hire a bicycle
    private static void hireBicycle() {
        // Display available bicycles
        showAvailableBicycles();

        System.out.print("Enter bicycle ID to hire: ");
        String id = scanner.nextLine();

        // Find the bicycle by ID
        Bicycle bicycle = findBicycle(id);
        if (bicycle != null) {
            // If bicycle found
            if (!bicycle.isHired()) {
                // If bicycle is not already hired, mark it as hired and record start time
                bicycle.setHired(true);
                bicycle.startHire(); // Record start time
                System.out.println("Bicycle " + id + " hired successfully.");
            } else {
                // If bicycle is already hired
                System.out.println("Bicycle " + id + " is already hired.");
            }
        } else {
            // If bicycle not found
            System.out.println("Bicycle " + id + " not found.");
        }
    }

    // Method to return a bicycle
    private static void returnBicycle() {
        System.out.print("Enter bicycle ID to return: ");
        String id = scanner.nextLine();

        // Find the bicycle by ID
        Bicycle bicycle = findBicycle(id);
        if (bicycle != null) {
            // If bicycle found
            if (bicycle.isHired()) {
                // If bicycle is hired, calculate duration, cost, and mark it as not hired
                double duration = bicycle.calculateHireDuration();
                double cost = bicycle.calculateHireCost();
                bicycle.setHired(false);
                System.out.println("Bicycle " + id + " returned successfully.");
                System.out.println("Duration of hire: " + duration + " hours");
                System.out.println("Cost of hire: " + cost + " KES");
            } else {
                // If bicycle is not hired
                System.out.println("Bicycle " + id + " is not hired.");
            }
        } else {
            // If bicycle not found
            System.out.println("Bicycle " + id + " not found.");
        }
    }

    // Method to show available bicycles and rates
    private static void showAvailableBicycles() {
        System.out.println("Available bicycles and rates per hour:");
        for (Bicycle bicycle : bicycles) {
            System.out.println("Bicycle ID: " + bicycle.getId() + ", Rate: " + bicycle.getPricePerHour() + " KES per hour");
        }
    }

    // Method to find a bicycle by ID
    private static Bicycle findBicycle(String id) {
        for (Bicycle bicycle : bicycles) {
            if (bicycle.getId().equals(id)) {
                return bicycle; // Return the bicycle if found
            }
        }
        return null; // Return null if bicycle not found
    }
}

