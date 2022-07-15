package models;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        validateEmail(email);
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    private void validateEmail(String email) {
        boolean isValid = Pattern.compile("^(\\w+)@(\\w+)\\.(\\w+)$").matcher(email).matches();
        if (!isValid) {
            throw new IllegalArgumentException("Email is invalid!");
        }
    }
}
