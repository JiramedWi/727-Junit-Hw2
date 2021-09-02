package se727.homework.store.dao;

import se727.homework.store.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();
    List<Product> getAllProductByPartialName(String name);
}
