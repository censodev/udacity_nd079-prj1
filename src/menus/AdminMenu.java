package menus;

import api.AdminResource;
import models.Room;

import java.util.List;
import java.util.Scanner;

public class AdminMenu implements IMenu {
    private final AdminResource adminResource = AdminResource.getInstance();
    private Scanner scanner;

    @Override
    public void printMenuItems() {
        System.out.println("""
                ======== ADMIN MENU ========
                1. See all customers
                2. See all rooms
                3. See all reservations
                4. Add a room
                5. Back to main menu
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
                    seeAllCustomers();
                    break;
                case "2":
                    seeAllRooms();
                    break;
                case "3":
                    seeAllReservations();
                    break;
                case "4":
                    addARoom();
                    break;
                default:
                    return;
            }
        }
    }

    private void addARoom() {
        Room room = new Room();
        System.out.print("Room number: ");
        room.setRoomNumber(scanner.nextLine());
        System.out.print("Room type (S/D): ");
        if (scanner.nextLine().equals("S")) {
            room.setRoomType(Room.RoomType.SINGLE);
        } else {
            room.setRoomType(Room.RoomType.DOUBLE);
        }
        System.out.print("Room price: ");
        room.setPrice(Double.parseDouble(scanner.nextLine()));
        adminResource.addRoom(List.of(room));
    }

    private void seeAllReservations() {
        adminResource.printAllReservations();
    }

    private void seeAllRooms() {
        adminResource.getAllRooms().forEach(System.out::println);
    }

    private void seeAllCustomers() {
        adminResource.getAllCustomers().forEach(System.out::println);
    }
}
