package com.nghia.cashservice.service.iml;

import com.nghia.cashservice.entity.Transaction;
import com.nghia.cashservice.repository.TransactionRepository;
import com.nghia.cashservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceIml implements TransactionService {

  private final TransactionRepository transactionRepository;

  public TransactionServiceIml(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public Transaction createTransaction(Transaction transaction) {
    return transactionRepository.save(transaction);;
  }
}
