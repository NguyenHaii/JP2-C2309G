package Controller;

import Entity.Booking;
import Entity.Customer;
import Entity.Room;
import E3.Entity.RoomType;
import Service.RoomService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RoomController {
    private final RoomService roomService;
    private final Scanner scanner;

    public RoomController() {
        List<Room> rooms = new ArrayList<>();
        List<Booking> bookings = new ArrayList<>();

        initializeRoomsAndBookings(rooms, bookings);

        this.roomService = new RoomService(rooms, bookings);
        this.scanner = new Scanner(System.in);
    }

    private void initializeRoomsAndBookings(List<Room> rooms, List<Booking> bookings) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        rooms.add(new Room(1, "RS001", RoomType.SINGLE, 8));
        rooms.add(new Room(2, "RD001", RoomType.DOUBLE, 12));
        rooms.add(new Room(3, "RQ002", RoomType.QUEEN, 35));
        rooms.add(new Room(4, "RT001", RoomType.TRIPLE, 12.5));
        rooms.add(new Room(5, "RQ001", RoomType.QUAD, 20.5));

        bookings.add(new Booking(1, rooms.get(0), new Customer(1, "001", "Mr. Linus Tovaldo", "84125325346457"),
                LocalDateTime.parse("2023-03-15 09:30:15", formatter), LocalDateTime.parse("2023-03-16 12:30:45", formatter)));
    }

    public void startMenu() {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Book a room");
            System.out.println("2. Display booking information");
            System.out.println("3. Revenue statistics by RoomType");
            System.out.println("4. RoomType with the largest revenue for a specific year");
            System.out.println("0. Exit");
            System.out.print("Select function: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    bookRoom();
                    break;
                case 2:
                    displayBookingInformation();
                    break;
                case 3:
                    revenueByRoomType();
                    break;
                case 4:
                    topRevenueByYear();
                    break;
                case 0:
                    System.out.println("Exit the program.");
                    return;
                default:
                    System.out.println("Invalid selection. Please select again.");
            }
        }
    }

    private void bookRoom() {
        System.out.println("Choose room type: ");
        List<RoomType> roomTypes = roomService.getRoom();
        for (int i = 0; i < roomTypes.size(); i++) {
            System.out.println((i + 1) + "." + roomTypes.get(i));
        }

        System.out.print("Enter your choice: ");
        int roomTypeChoice;
        try {
            roomTypeChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid room type choice.");
            return;
        }

        if (roomTypeChoice < 1 || roomTypeChoice > roomTypes.size()) {
            System.out.println("Invalid room type choice. Please select again.");
            return;
        }

        RoomType selectedRoomType = roomTypes.get(roomTypeChoice - 1);
        List<Room> availableRooms = roomService.getRoomsType(selectedRoomType);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms for the selected type.");
            return;
        }

        LocalDateTime checkIn = LocalDateTime.now();
        LocalDateTime checkOut = checkIn.plusHours(24);
        List<Room> roomsAvailableAtTime = roomService.getRoomsByTypeAndTime(selectedRoomType, checkIn, checkOut);

        if (roomsAvailableAtTime.isEmpty()) {
            System.out.println("No available rooms for the selected type and time.");
            return;
        }

        Room selectedRoom = roomsAvailableAtTime.get(0);

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter customer phone number: ");
        String phone = scanner.nextLine().trim();

        Customer customer = new Customer(0, id, name, phone);
        Booking booking = new Booking(0, selectedRoom, customer, checkIn, checkOut);
        roomService.addBooking(booking);
        System.out.println("Booking created successfully for " + selectedRoom.getCodeRoom() +
                "\nCheck-in: " + checkIn + "\nCheck-out: " + checkOut);
    }

    private void displayBookingInformation() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().trim();
        System.out.print("Enter customer phone number: ");
        String customerPhone = scanner.nextLine().trim();
        System.out.print("Enter room ID: ");
        String roomCode = scanner.nextLine();
        Booking booking = roomService.findBooking(customerName, customerPhone, roomCode);
        if (booking != null) {
            System.out.println("Booking information found:");
            System.out.println(booking);
        } else {
            System.out.println("No booking found with the provided information.");
        }
    }

    private void revenueByRoomType() {
        Map<RoomType, Double> revenueByRoomType = roomService.revenueByRoomType();
        System.out.println("Revenue by Room Type:");
        revenueByRoomType.forEach((roomType, revenue) -> {
            System.out.println(roomType + ": " + revenue + " (total revenue)");
        });
    }

    private void topRevenueByYear() {
        System.out.print("Enter year for revenue statistics: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine());
            System.out.println(roomService.topRevenueByYear(year));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid year.");
        }
    }
}
