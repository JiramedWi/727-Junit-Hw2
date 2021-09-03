package se727.homework.store.entity;

import java.util.Objects;

public class Product {
    String productId;
    String productName;
    Double StandardPrice;

    public Product(String productName, Double standardPrice) {
        this.productName = productName;
        StandardPrice = standardPrice;
    }

    public Product() {
    }

    public Product(String productId, String productName, Double standardPrice) {
        this.productId = productId;
        this.productName = productName;
        StandardPrice = standardPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(StandardPrice, product.StandardPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, StandardPrice);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getStandardPrice() {
        return StandardPrice;
    }

    public void setStandardPrice(Double standardPrice) {
        StandardPrice = standardPrice;
    }
}
