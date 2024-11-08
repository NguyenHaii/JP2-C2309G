package Service;

import Entity.Booking;
import Entity.Room;
import E3.Entity.RoomType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomService {
    private final List<Room> rooms;
    private final List<Booking> bookings;

    public RoomService(List<Room> rooms, List<Booking> bookings) {
        this.rooms = rooms;
        this.bookings = bookings;
    }

    public List<RoomType> getRoom() {
        return List.of(RoomType.values());
    }

    public List<Room> getRoomsType(RoomType roomType) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .collect(Collectors.toList());
    }

    public List<Room> getRoomsByTypeAndTime(RoomType roomType, LocalDateTime checkIn, LocalDateTime checkOut) {
        return rooms.stream()
                .filter(room -> room.getRoomType() == roomType)
                .filter(room -> isRoomAvailable(room, checkIn, checkOut))
                .collect(Collectors.toList());
    }

    private boolean isRoomAvailable(Room room, LocalDateTime checkIn, LocalDateTime checkOut) {
        return bookings.stream()
                .filter(booking -> booking.getRoom().equals(room))
                .noneMatch(booking -> checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn()));
    }

    public void addBooking(Booking booking) {
        if (isRoomAvailable(booking.getRoom(), booking.getCheckIn(), booking.getCheckOut())) {
            bookings.add(booking);
        } else {
            throw new IllegalArgumentException("Room is not available for the selected dates.");
        }
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Booking findBooking(String customerName, String customerPhone, String roomCode) {
        return bookings.stream()
                .filter(booking -> booking.getCustomer().getName().equalsIgnoreCase(customerName))
                .filter(booking -> booking.getCustomer().getPhone().equals(customerPhone))
                .filter(booking -> booking.getRoom().getCodeRoom().equalsIgnoreCase(roomCode))
                .findFirst()
                .orElse(null);
    }

    public Map<RoomType, Double> revenueByRoomType() {
        return bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(this::bookingRevenue)
                ));
    }

    public String topRevenueByYear(int year) {
        Map<RoomType, Double> revenueByRoomTypeForYear = bookings.stream()
                .filter(booking -> booking.getCheckIn().getYear() == year)
                .collect(Collectors.groupingBy(
                        booking -> booking.getRoom().getRoomType(),
                        Collectors.summingDouble(this::bookingRevenue)
                ));
        return revenueByRoomTypeForYear.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(entry -> "Room Type: " + entry.getKey() + " - Revenue: " + entry.getValue())
                .orElse("No bookings found for the year " + year + ".");
    }

    private double bookingRevenue(Booking booking) {
        long hours = Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
        return booking.getRoom().getPricePerHour() * hours;
    }
}
