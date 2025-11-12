package Model.Database;

import Model.Customer;

public class CustomerDatabase {
        private String firstName;
        private String lastName;
        private String email;
        private String address;
        private String phoneNumber;
        private String username;
        private String password;

        public CustomerDatabase() {}

        public CustomerDatabase(String firstName, String lastName, String email, String address, String phoneNumber, String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.username = username;
            this.password = password;
        }

        // Getters
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public String getAddress() { return address; }
        public String getPhoneNumber() { return phoneNumber; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }

        // Setters
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public void setEmail(String email) { this.email = email; }
        public void setAddress(String address) { this.address = address; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }

    public Customer toCustomer() {
        return new Customer(
                null, // ID is unknown at this point
                firstName,
                lastName,
                phoneNumber,
                email,
                address,
                "",
                password
        );
    }

}


