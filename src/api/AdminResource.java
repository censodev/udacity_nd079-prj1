package api;

import models.Customer;
import models.IRoom;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource instance;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private AdminResource(CustomerService customerService,
                          ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static AdminResource getInstance() {
        if (instance == null) {
            instance = new AdminResource(CustomerService.getInstance(), ReservationService.getInstance());
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(reservationService::addRoom);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void printAllReservations() {
        reservationService.printAllReservations();
    }
}
