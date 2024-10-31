import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import Service.BookingService;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Room data
        Map<String, Room> rooms = new HashMap<>();
        rooms.put("RS001", new Room("RS001", RoomType.SINGLE, 8.0));
        rooms.put("RD001", new Room("RD001", RoomType.DOUBLE, 12.0));
        rooms.put("RQ002", new Room("RQ002", RoomType.QUEEN, 35.0));
        rooms.put("RT001", new Room("RT001", RoomType.TRIPLE, 12.5));
        rooms.put("RQ001", new Room("RQ001", RoomType.QUAD, 20.5));

        Map<String, Customer> customers = new HashMap<>();
        customers.put("001", new Customer("001", "", "123"));
        customers.put("002", new Customer("002", "Vinh", "456"));
        customers.put("003", new Customer("003", "Hieu", "789"));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking("1", "RS001", "001", LocalDateTime.of(2023, 3, 15, 9, 30), LocalDateTime.of(2023, 3, 16, 12, 30)));
        bookings.add(new Booking("2", "RS001", "002", LocalDateTime.of(2023, 6, 9, 19, 30), LocalDateTime.of(2023, 6, 10, 11, 25)));
        bookings.add(new Booking("3", "RD001", "002", LocalDateTime.of(2023, 3, 11, 10, 10), LocalDateTime.of(2023, 3, 13, 11, 5)));
        bookings.add(new Booking("4", "RT001", "003", LocalDateTime.of(2023, 11, 11, 11, 11), LocalDateTime.of(2023, 11, 13, 11, 15)));
        bookings.add(new Booking("5", "RT001", "001", LocalDateTime.of(2023, 10, 25, 9, 20), LocalDateTime.of(2023, 10, 26, 12, 25)));
        bookings.add(new Booking("6", "RQ001", "001", LocalDateTime.of(2023, 8, 18, 18, 25), LocalDateTime.of(2023, 8, 19, 11, 35)));

        BookingService bookingService = new BookingService(rooms, customers, bookings);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer name, phone, and room type (Single, Double, etc.):");
        String name = scanner.nextLine();
        String phone = scanner.nextLine();
        RoomType roomType = RoomType.valueOf(scanner.nextLine().toUpperCase());
        bookingService.createBooking(name, phone, roomType);

        System.out.println("\nEnter customer name, phone, and room ID to view booking:");
        name = scanner.nextLine();
        phone = scanner.nextLine();
        String roomId = scanner.nextLine();
        bookingService.displayBooking(name, phone, roomId);

        System.out.println("\nRevenue by RoomType:");
        Map<RoomType, Double> revenueByRoomType = bookingService.calculateRevenueByRoomType();
        revenueByRoomType.forEach((type, revenue) ->
                System.out.println(type + ": $" + revenue)
        );

        System.out.println("\nRoom Type with highest revenue:");
        bookingService.displayTopRevenueRoomType();
    }
}
