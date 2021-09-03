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
        //Creat mock for test name
        when(productDao.getAllProductByPartialName("Sock")).thenReturn(
                Arrays.asList(
                        new Product("Sock", 100.0),
                        new Product("HSock", 230.0))
        );
        when(productDao.getAllProductByPartialName("HSock")).thenReturn(
                Arrays.asList(
                        new Product("HSock", 230.0))
        );
        when(productDao.getAllProductByPartialName("SSock")).thenReturn(
                Arrays.asList()
        );
        //Creat mock for test price
        when(productDao.getAllProduct()).thenReturn(
                Arrays.asList(
                        new Product("sPant", 10.0),
                        new Product("mPant", 20.0),
                        new Product("lPant", 30.0),
                        new Product("xlPant", 40.0))
        );


    }

    @Test
    void getProductByPartialName() {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
        //test size of list
        assertThat(productService.getProductByPartialName("Sock").size(), is(2));
        //test get partialName some text
        assertThat(productService.getProductByPartialName("Sock"),
                containsInAnyOrder(
                        new Product("Sock", 100.0),
                        new Product("HSock", 230.0)));
        //test get partialName specific text
        assertThat(productService.getProductByPartialName("HSock"),
                containsInAnyOrder(
                        new Product("HSock", 230.00)));
        //test throws exception for empty list
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
        //test size of list lessthan 30
        assertThat(productService.getProductByPriceLessThan(30.0).size(), is(2));
        //test product lessthan 30
        assertThat(productService.getProductByPriceLessThan(30.0),
                containsInAnyOrder(
                        new Product("sPant", 10.0),
                        new Product("mPant", 20.0)
                ));
        //test size of list lessthan 40
        assertThat(productService.getProductByPriceLessThan(40.0).size(), is(3));
        //test product lessthan 40
        assertThat(productService.getProductByPriceLessThan(40.0),
                containsInAnyOrder(
                        new Product("sPant", 10.0),
                        new Product("mPant", 20.0),
                        new Product("lPant", 30.0)
                ));
        //test thrown exception for empty list
        Assertions.assertThrows(NoProductException.class, () -> {
            productService.getProductByPriceLessThan(5.0);
        });

        //verify how many time to call getAllProduct function
        verify(productDao, times(5)).getAllProduct();
        //verify the order of function that using in the test
        InOrder inOrder = inOrder(productDao);
        inOrder.verify(productDao, times(5)).getAllProduct();
    }
}