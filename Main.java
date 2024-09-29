import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        
        // Adding sample rooms
        hotel.addRoom(new Room(101, "Single", 50));
        hotel.addRoom(new Room(102, "Double", 80));
        hotel.addRoom(new Room(103, "Suite", 150));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Reservation System!");
        
        // Create a user
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        User user = new User(name, email);

        while (true) {
            System.out.println("\n1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View your reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Single/Double/Suite): ");
                    scanner.nextLine(); // consume leftover newline
                    String category = scanner.nextLine();
                    List<Room> availableRooms = hotel.searchAvailableRooms(category);

                    if (availableRooms.isEmpty()) {
                        System.out.println("No rooms available in this category.");
                    } else {
                        System.out.println("Available rooms:");
                        for (Room room : availableRooms) {
                            System.out.println("Room " + room.getRoomNumber() + " - $" + room.getPricePerNight() + " per night");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter check-in date (in milliseconds): ");
                    long checkInMillis = scanner.nextLong();
                    System.out.print("Enter check-out date (in milliseconds): ");
                    long checkOutMillis = scanner.nextLong();
                    Date checkIn = new Date(checkInMillis);
                    Date checkOut = new Date(checkOutMillis);
                    hotel.makeReservation(user, roomNumber, checkIn, checkOut);
                    break;

                case 3:
                    hotel.viewReservations(user);
                    break;

                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
