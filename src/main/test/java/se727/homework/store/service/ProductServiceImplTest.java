package se727.homework.store.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import se727.homework.store.dao.ProductDao;
import se727.homework.store.dao.TransactionDao;
import se727.homework.store.entity.Product;
import se727.homework.store.exception.NoProductException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    ProductDao productDao = null;
    TransactionDao transactionDao = null;
    Product product1 = null;
    Product product2 = null;

    List<Product> products = new ArrayList<>();

    @BeforeEach
    void init() {
        //set ProductDao as mock
        productDao = mock(ProductDao.class);
        //Create list for product in stock
        when(productDao.getAllProductByPartialName("Sock")).thenReturn(
                Arrays.asList(
                        new Product("Sock", 100.0),
                        new Product("HSock", 230.0))
        );
        when(productDao.getAllProductByPartialName("HSock")).thenReturn(
                Arrays.asList(new Product("HSock", 230.0))
        );
        when(productDao.getAllProductByPartialName("SSock")).thenReturn(
                Arrays.asList()
        );
        when(productDao.getAllProduct()).thenReturn(
                Arrays.asList(new Product("sPant",10.0),
                        new Product("mPant",20.0),
                        new Product("lPant",30.0),
                        new Product("xlPant",40.0))
        );


    }

    @Test
    void getProductByPartialName() {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
        assertThat(productService.getProductByPartialName("Sock").size(), is(2));
        assertThat(productService.getProductByPartialName("Sock"),
                containsInAnyOrder(new Product("Sock", 100.0),
                        new Product("HSock", 230.0)));
        assertThat(productService.getProductByPartialName("HSock"),
                containsInAnyOrder(new Product("HSock", 230.00)));
        Assertions.assertThrows(NoProductException.class, () -> {
            productService.getProductByPartialName("SSock");
        });

        //Verify how many time to call function
        verify(productDao, times(4)).getAllProductByPartialName(anyString());
        //verify Order the function that using in the test
        InOrder inOrder = inOrder(productDao);
        inOrder.verify(productDao, times(2)).getAllProductByPartialName("Sock");
        inOrder.verify(productDao).getAllProductByPartialName("HSock");
        inOrder.verify(productDao).getAllProductByPartialName("SSock");
    }

    @Test
    void getProductByPriceLessThan() {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
        assertThat(productService.getProductByPriceLessThan(30.0).size(),is(2));
        assertThat(productService.getProductByPriceLessThan(30.0),
                containsInAnyOrder(
                        new Product("sPant",10.0),
                        new Product("mPant",20.0)
                ));
        assertThat(productService.getProductByPriceLessThan(40.0).size(),is(3));
        assertThat(productService.getProductByPriceLessThan(40.0),
                containsInAnyOrder(
                        new Product("sPant",10.0),
                        new Product("mPant",20.0),
                        new Product("lPant",30.0)
                ));

        //verify how many time to call getAllProduct function
        verify(productDao,times(4)).getAllProduct();
        //verify the order of function that using in the test
        InOrder inOrder = inOrder(productDao);
        inOrder.verify(productDao,times(4)).getAllProduct();
    }
}