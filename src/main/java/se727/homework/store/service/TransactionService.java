package se727.homework.store.service;

import se727.homework.store.entity.Transaction;

public interface TransactionService {
    Double getAverageTransactionSellPriceByCustomerName(String customerName);
    Double getAverageTransactionSellPriceByProduct(String productId);
    Transaction getMaximumSellPriceTransaction();
}
