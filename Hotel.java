import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    // Add a room to the hotel
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Search for available rooms by category
    public List<Room> searchAvailableRooms(String category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // Book a room
    public boolean makeReservation(User user, int roomNumber, Date checkIn, Date checkOut) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(user, room, checkIn, checkOut);
                reservations.add(reservation);
                
                if (Payment.processPayment(user, reservation.getTotalCost())) {
                    System.out.println("Reservation successful! Total cost: $" + reservation.getTotalCost());
                    return true;
                } else {
                    System.out.println("Payment failed. Reservation cancelled.");
                    return false;
                }
            }
        }
        System.out.println("Room not available or doesn't exist.");
        return false;
    }

    // View all reservations for a user
    public void viewReservations(User user) {
        for (Reservation reservation : reservations) {
            if (reservation.getUser().getEmail().equals(user.getEmail())) {
                System.out.println("Room " + reservation.getRoom().getRoomNumber() +
                        " from " + reservation.getCheckInDate() + " to " + reservation.getCheckOutDate() +
                        ". Total: $" + reservation.getTotalCost());
            }
        }
    }
}
