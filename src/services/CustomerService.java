package services;

import models.Customer;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    private static final Collection<Customer> customers = new ArrayList<>();

    public void addCustomer(String email, String firstname, String lastname) {
        customers.add(new Customer(firstname, lastname, email));
    }

    public Customer getCustomer(String email) {
        return customers.stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
