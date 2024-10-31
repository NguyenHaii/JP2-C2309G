package Service;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private Map<String, Room> rooms;
    private Map<String, Customer> customers;
    private List<Booking> bookings;

    public BookingService(Map<String, Room> rooms, Map<String, Customer> customers, List<Booking> bookings) {
        this.rooms = rooms;
        this.customers = customers;
        this.bookings = bookings;
    }

    public void createBooking(String customerName, String customerPhone, RoomType roomType) {
        try {
            Room availableRoom = findAvailableRoomByType(roomType);
            if (availableRoom == null) {
                System.out.println("Room type not available.");
                return;
            }
            String customerId = UUID.randomUUID().toString();
            Customer customer = new Customer(customerId, customerName, customerPhone);
            customers.put(customerId, customer);

            LocalDateTime checkIn = LocalDateTime.now();
            LocalDateTime checkOut = checkIn.plusHours(24);

            Booking booking = new Booking(UUID.randomUUID().toString(), availableRoom.getId(), customerId, checkIn, checkOut);
            bookings.add(booking);

            System.out.println("Booking created successfully: " + booking);
        } catch (Exception e) {
            System.out.println("Error during booking creation: " + e.getMessage());
        }
    }

    private Room findAvailableRoomByType(RoomType roomType) {
        return rooms.values().stream()
                .filter(room -> room.getRoomType() == roomType)
                .findFirst()
                .orElse(null);
    }

    public void displayBooking(String customerName, String customerPhone, String roomId) {
        Customer customer = findCustomerByNameAndPhone(customerName, customerPhone);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        bookings.stream()
                .filter(booking -> booking.getRoomId().equalsIgnoreCase(roomId) && booking.getCustomerId().equals(customer.getId()))
                .forEach(System.out::println);
    }

    private Customer findCustomerByNameAndPhone(String name, String phone) {
        return customers.values().stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(name) && customer.getPhone().equals(phone))
                .findFirst()
                .orElse(null);
    }

    public Map<RoomType, Double> calculateRevenueByRoomType() {
        return bookings.stream()
                .collect(Collectors.groupingBy(
                        booking -> rooms.get(booking.getRoomId()).getRoomType(),
                        Collectors.summingDouble(this::calculateBookingRevenue)
                ));
    }

    private double calculateBookingRevenue(Booking booking) {
        Room room = rooms.get(booking.getRoomId());
        long hours = java.time.Duration.between(booking.getCheckIn(), booking.getCheckOut()).toHours();
        return hours * room.getPricePerHour();
    }

    public void displayTopRevenueRoomType() {
        Map<RoomType, Double> revenueByRoomType = calculateRevenueByRoomType();
        revenueByRoomType.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.println("Room Type with highest revenue: " + entry.getKey() + " - $" + entry.getValue()));
    }
}
