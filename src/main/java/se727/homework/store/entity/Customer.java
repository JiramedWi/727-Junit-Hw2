package se727.homework.store.entity;

import java.util.Objects;

public class Customer {
    Long id;
    String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }


    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
