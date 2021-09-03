package se727.homework.store.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import se727.homework.store.dao.TransactionDao;
import se727.homework.store.entity.Customer;
import se727.homework.store.entity.Product;
import se727.homework.store.entity.Transaction;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
    TransactionDao transactionDao = null;

    @BeforeEach
    void init() {
        //set transaction as mock data
        transactionDao = mock(TransactionDao.class);
        when(transactionDao.getAllTransaction()).thenReturn(
                Arrays.asList(
                        new Transaction(
                                new Product("001","Shirt", 100.0), new Customer("Pii"), 200.0
                        ),
                        new Transaction(
                                new Product("002","Pant", 150.0), new Customer("Pii"), 100.0
                        ),
                        new Transaction(
                                new Product("001","Shirt", 100.0), new Customer("Ploy"), 99.0
                        )
                )
        );

    }

    @Test
    void getAverageTransactionSellPriceByCustomerName() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();
        transactionService.setTransactionDao(transactionDao);
        //test for customer name "Pii"
        assertThat(transactionService.getAverageTransactionSellPriceByCustomerName("Pii"),is(closeTo(150.0,0.001)));
        //test for customer name "Ploy"
        assertThat(transactionService.getAverageTransactionSellPriceByCustomerName("Ploy"),is(closeTo(99.0,0.001)));

        //verify how many times that using function
        verify(transactionDao,times(2)).getAllTransaction();
        //verify order ot used function
        InOrder inOrder = inOrder(transactionDao);
        inOrder.verify(transactionDao,times(2)).getAllTransaction();
    }

    @Test
    void getAverageTransactionSellPriceByProduct() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();
        transactionService.setTransactionDao(transactionDao);
        //test for productID customer 001 150 and 99
        assertThat(transactionService.getAverageTransactionSellPriceByProduct("001"),is(closeTo(149.5,0.001)));
        //test for productID customer 002 only 100
        assertThat(transactionService.getAverageTransactionSellPriceByProduct("002"),is(closeTo(100.0,0.001)));

        //verify how many times that using function
        verify(transactionDao,times(2)).getAllTransaction();
        //verify order ot used function
        InOrder inOrder = inOrder(transactionDao);
        inOrder.verify(transactionDao,times(2)).getAllTransaction();
    }

    @Test
    void getMaximumSellPriceTransaction() {
        TransactionServiceImpl transactionService = new TransactionServiceImpl();
        transactionService.setTransactionDao(transactionDao);
        //test for getMaxsell
        assertThat(transactionService.getMaximumSellPriceTransaction().getSalePrice(),is(200.0));
        assertThat(transactionService.getMaximumSellPriceTransaction().getCustomer(),is(new Customer("Pii")));
        assertThat(transactionService.getMaximumSellPriceTransaction().getProduct(),is( new Product("001","Shirt", 100.0)));
        assertThat(transactionService.getMaximumSellPriceTransaction(),is(
                new Transaction(
                        new Product("001","Shirt", 100.0), new Customer("Pii"), 200.0
                )
        ));

        //set up for throwsExceptioncase
        when(transactionDao.getAllTransaction()).thenReturn(Arrays.asList());
        TransactionServiceImpl transactionServiceFail = new TransactionServiceImpl();
        transactionServiceFail.setTransactionDao(transactionDao);
        //test for throwsException
        Assertions.assertThrows(NoSuchElementException.class,()->{
            transactionServiceFail.getMaximumSellPriceTransaction();
        });
        
        //verify how many time that called function
        verify(transactionDao,times(5)).getAllTransaction();
        //verify order ot used function
        InOrder inOrder = inOrder(transactionDao);
        inOrder.verify(transactionDao,times(5)).getAllTransaction();
    }
}