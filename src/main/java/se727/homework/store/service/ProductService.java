package se727.homework.store.service;

import se727.homework.store.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductByPartialName(String name);
    List<Product> getProductByPriceLessThan(Double price);
}
