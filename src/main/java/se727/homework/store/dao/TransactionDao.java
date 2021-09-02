package se727.homework.store.dao;

import se727.homework.store.entity.Transaction;

import java.util.List;

public interface TransactionDao {
    List<Transaction> getAllTransaction();

}
