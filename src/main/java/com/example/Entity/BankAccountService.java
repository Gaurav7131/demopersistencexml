package com.example.Entity;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BankAccountService {
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    // Scenario 1.Optimistic Locking
    // If USer 1 and User 2 tries to acess account at same time, user 1 will saved
    // sucessfuly while user 2 will throws
    // ObjectOptimisticLockClockFailureException
    @Transactional
    public void updateProfile(Long accountId, String newName) {
        BankAccount account = repository.findById(accountId).orElseThrow();
        account.setName(newName);
        repository.save(account);
    }

    // Scenario 2.Pessimistic Locking
    // USer 1 complete first,while user 2 blocked physically(db_level)
    @Transactional
    public void withdrawMoney(Long accountId, Double amount) {
        BankAccount account = repository.findWithLockById(accountId);

        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            repository.save(account);
        }
    }
}
