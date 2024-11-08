package Entity;

import java.time.LocalDateTime;
import Entity.Customer;
import Entity.Room;

public class Booking {
    private int id;
    private Room room;
    private Customer customer;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public Booking() {
    }

    public Booking(int id, Room room, Customer customer, LocalDateTime checkIn, LocalDateTime checkOut) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", room=" + room +
                ", customer=" + customer +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}