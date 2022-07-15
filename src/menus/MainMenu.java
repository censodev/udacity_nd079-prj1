package menus;

import api.AdminResource;
import api.HotelResource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MainMenu implements IMenu {
    private final HotelResource hotelResource = HotelResource.getInstance();
    private Scanner scanner;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @Override
    public void printMenuItems() {
        System.out.println("""
                ======== MAIN MENU ========
                1. Find and reserve a room
                2. See my reservations
                3. Create an account
                4. Admin
                5. Exit
                """);
    }

    @Override
    public void start() {
        scanner = new Scanner(System.in);
        while (true) {
            printMenuItems();
            var c = scanner.nextLine();
            switch (c) {
                case "1":
                    try {
                        findAndReserveARoom();
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    seeMyReservations();
                    break;
                case "3":
                    createAccount();
                    break;
                case "4":
                    openAdminMenu();
                    break;
                default:
                    return;
            }
        }
    }

    void findAndReserveARoom() throws ParseException {
        printAllRooms();
        System.out.println("Room number: ");
        var room = hotelResource.getARoom(scanner.nextLine());
        System.out.println("Customer email: ");
        var email = scanner.nextLine();
        System.out.println("Check-in date (yyyy/MM/dd): ");
        var checkIn = sdf.parse(scanner.nextLine());
        System.out.println("Check-out date (yyyy/MM/dd): ");
        var checkOut = sdf.parse(scanner.nextLine());
        hotelResource.bookARoom(email, room, checkIn, checkOut);
    }

    private void printAllRooms() {
        AdminResource.getInstance().getAllRooms().forEach(System.out::println);
    }

    void seeMyReservations() {
        System.out.println("Email: ");
        var email = scanner.nextLine();
        hotelResource.getCustomerReservations(email).forEach(System.out::println);
    }

    void createAccount() {
        System.out.println("First name: ");
        var fn = scanner.nextLine();
        System.out.println("Last name: ");
        var ln = scanner.nextLine();
        System.out.println("Email: ");
        var email = scanner.nextLine();
        try {
            hotelResource.createACustomer(email, fn, ln);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    void openAdminMenu() {
        (new AdminMenu()).start();
    }
}