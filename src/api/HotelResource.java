package api;

import models.Customer;
import models.IRoom;
import models.Reservation;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource instance;
    private final CustomerService customerService;
    private final ReservationService reservationService;

    private HotelResource(CustomerService customerService,
                          ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationService = reservationService;
    }

    public static HotelResource getInstance() {
        if (instance == null) {
            instance = new HotelResource(CustomerService.getInstance(), ReservationService.getInstance());
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstname, String lastname) {
        customerService.addCustomer(email, firstname, lastname);
    }

    public IRoom getARoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws Exception {
        Customer customer = customerService.getCustomer(customerEmail);
        if (customer == null) {
            throw new Exception("Email does not exist");
        }
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomerReservations(customer);
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Collection<IRoom> getRecommendedRooms(Date originCheckIn, Date originCheckOut) {
        return reservationService.getRecommendedRooms(originCheckIn, originCheckOut);
    }
}
