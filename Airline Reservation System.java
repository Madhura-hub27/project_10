import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationSystem {

    // Flight class
    static class Flight {
        private int flightNumber;
        private String origin;
        private String destination;
        private int capacity;
        private int bookedSeats;

        public Flight(int flightNumber, String origin, String destination, int capacity) {
            this.flightNumber = flightNumber;
            this.origin = origin;
            this.destination = destination;
            this.capacity = capacity;
            this.bookedSeats = 0;
        }

        public int getFlightNumber() {
            return flightNumber;
        }

        public String getDetails() {
            return "Flight " + flightNumber + ": " + origin + " -> " + destination +
                   " | Seats Available: " + (capacity - bookedSeats);
        }

        public boolean bookSeat() {
            if (bookedSeats < capacity) {
                bookedSeats++;
                return true;
            }
            return false;
        }
    }

    // Reservation class
    static class Reservation {
        private String passengerName;
        private int flightNumber;

        public Reservation(String passengerName, int flightNumber) {
            this.passengerName = passengerName;
            this.flightNumber = flightNumber;
        }

        public String getDetails() {
            return "Passenger: " + passengerName + " | Flight Number: " + flightNumber;
        }
    }

    // Main system logic
    private static List<Flight> flights = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeFlights();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Airline Reservation System ---");
            System.out.println("1. View Available Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. View Reservations");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear newline

            switch (choice) {
                case 1 -> viewFlights();
                case 2 -> bookFlight(scanner);
                case 3 -> viewReservations();
                case 0 -> System.out.println("Thank you for using the system.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void initializeFlights() {
        flights.add(new Flight(101, "New York", "London", 5));
        flights.add(new Flight(102, "Tokyo", "San Francisco", 3));
        flights.add(new Flight(103, "Paris", "Rome", 4));
    }

    private static void viewFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight flight : flights) {
            System.out.println(flight.getDetails());
        }
    }

    private static void bookFlight(Scanner scanner) {
        System.out.print("\nEnter passenger name: ");
        String name = scanner.nextLine();
        System.out.print("Enter flight number to book: ");
        int flightNumber = scanner.nextInt();
        scanner.nextLine(); // Clear newline

        Flight selectedFlight = null;
        for (Flight f : flights) {
            if (f.getFlightNumber() == flightNumber) {
                selectedFlight = f;
                break;
            }
        }

        if (selectedFlight != null) {
            if (selectedFlight.bookSeat()) {
                reservations.add(new Reservation(name, flightNumber));
                System.out.println("Booking confirmed!");
            } else {
                System.out.println("Sorry, no available seats on this flight.");
            }
        } else {
            System.out.println("Flight not found.");
        }
    }

    private static void viewReservations() {
        System.out.println("\nReservations:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
        } else {
            for (Reservation r : reservations) {
                System.out.println(r.getDetails());
            }
        }
    }
}
