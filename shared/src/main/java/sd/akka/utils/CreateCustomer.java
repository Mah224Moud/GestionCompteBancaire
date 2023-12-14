package sd.akka.utils;

import sd.akka.customer.Customer;

import java.io.Serializable;

public class CreateCustomer extends Customer implements Serializable {

    public CreateCustomer(int id, String name, String firstname, String gender, String address, String phone,
            String type,
            int accountNumber, int bankerId, String email, String password) {
        super(id, name, firstname, gender, address, phone, type, accountNumber, bankerId, email, password);
    }

    public Customer getCustomer() {
        return new Customer(this.getId(), this.getName(), this.getFirstname(), this.getGender(), this.getAddress(),
                this.getPhone(), this.getType(), this.getAccountNumber(), this.getBankerId(), this.getEmail(),
                this.getPassword());
    }
}
