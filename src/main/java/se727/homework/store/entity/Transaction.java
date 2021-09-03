package se727.homework.store.entity;

import java.util.Objects;

public class Transaction {
    Product product;
    Customer customer;
    Double salePrice;

    public Transaction(Product product, Customer customer, Double salePrice) {
        this.product = product;
        this.customer = customer;
        this.salePrice = salePrice;
    }

    public Transaction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(salePrice, that.salePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, customer, salePrice);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
}
