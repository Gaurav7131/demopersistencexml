package com.example.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    // Pessimistic Locking:Pessimitistic.WRITE-request physical Exclusive Lock
    // Explicitly telling jpa to Lock the specific row for Write priviledges
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BankAccount findWithLockById(Long id);
}
