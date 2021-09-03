package se727.homework.store.service;

import se727.homework.store.dao.TransactionDao;
import se727.homework.store.entity.Transaction;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService{
    TransactionDao transactionDao;

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public Double getAverageTransactionSellPriceByCustomerName(String customerName) {
        return transactionDao.getAllTransaction().stream().filter(
                t -> t.getCustomer().getCustomerName().equals(customerName)
        ).collect(Collectors.toList()).stream().mapToDouble(Transaction::getSalePrice).average().getAsDouble();

    }

    @Override
    public Double getAverageTransactionSellPriceByProduct(String productId) {
        return transactionDao.getAllTransaction().stream()
                .filter(t -> t.getProduct().getProductId().equals(productId))
                .mapToDouble(Transaction::getSalePrice).average().getAsDouble();
    }

    @Override
    public Transaction getMaximumSellPriceTransaction() {
        return transactionDao.getAllTransaction()
                .stream()
                .max(Comparator.comparingDouble(Transaction::getSalePrice))
                .orElseThrow(NoSuchElementException::new);
    }
}
