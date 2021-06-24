package ru.fadesml.bank.services.impl;

import ru.fadesml.bank.models.Card;
import ru.fadesml.bank.models.ETransferType;
import ru.fadesml.bank.models.Transaction;
import ru.fadesml.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
class TransactionService {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void create(Card from, Card to, ETransferType type, Double amount) {
        Transaction transaction = new Transaction(from, to, amount, type);
        repository.save(transaction);
    }

    public void create(Card from, ETransferType type, Double amount) {
        Transaction transaction = new Transaction(from, amount, type);
        repository.save(transaction);
    }

    public void create(int i, Card to, ETransferType type, Double amount) {
        Transaction transaction = new Transaction(i, to, amount, type);
        repository.save(transaction);
    }
}
