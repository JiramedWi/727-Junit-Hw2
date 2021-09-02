package se727.homework.store.entity;

public class Product {
    String productId;
    String productName;
    Double StandardPrice;

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
