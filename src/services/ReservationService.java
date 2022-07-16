package services;

import models.Customer;
import models.IRoom;
import models.Reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ReservationService {
    private static ReservationService instance;
    private static final Collection<IRoom> rooms = new ArrayList<>();
    private static final Collection<Reservation> reservations = new HashSet<>();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    public IRoom getARoom(String id) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Reservation reserveARoom(Customer customer,
                                    IRoom room,
                                    Date checkInDate,
                                    Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        var occupiedRoomNumbers = reservations.stream()
                .filter(r -> r.getCheckInDate().equals(checkInDate))
                .filter(r -> r.getCheckOutDate().equals(checkOutDate))
                .map(Reservation::getRoom)
                .map(IRoom::getRoomNumber)
                .collect(Collectors.toList());
        return rooms.stream()
                .filter(r -> !occupiedRoomNumbers.contains(r.getRoomNumber()))
                .collect(Collectors.toList());
    }

    public Collection<Reservation> getCustomerReservations(Customer customer) {
        return reservations.stream()
                .filter(r -> r.getCustomer().getEmail().equals(customer.getEmail()))
                .collect(Collectors.toList());
    }

    public void printAllReservations() {
        reservations.forEach(System.out::println);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms;
    }
}
