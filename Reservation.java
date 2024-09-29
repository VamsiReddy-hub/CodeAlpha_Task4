import java.util.Date;

class Reservation {
    private User user;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalCost;

    public Reservation(User user, Room room, Date checkInDate, Date checkOutDate) {
        this.user = user;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = calculateCost();
    }

    private double calculateCost() {
        long diffInMillis = checkOutDate.getTime() - checkInDate.getTime();
        int nights = (int) (diffInMillis / (1000 * 60 * 60 * 24));
        return room.getPricePerNight() * nights;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
}
