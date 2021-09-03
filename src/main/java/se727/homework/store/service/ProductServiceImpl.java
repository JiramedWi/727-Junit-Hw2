package se727.homework.store.service;

import se727.homework.store.dao.ProductDao;
import se727.homework.store.entity.Product;
import se727.homework.store.exception.NoProductException;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{
    ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProductByPartialName(String name) {
        List<Product> output = productDao.getAllProductByPartialName(name);
        if (output.size() == 0){
            throw new NoProductException();
        }else{
            return output;
        }
    }

    @Override
    public List<Product> getProductByPriceLessThan(Double price) {

        List<Product> output = productDao.getAllProduct().stream().filter(p -> p.getStandardPrice() < price).collect(Collectors.toList());
        if (output.size() == 0){
            throw new NoProductException();
        }else{
            return output;
        }
    }
}
