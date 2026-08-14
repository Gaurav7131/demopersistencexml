/*
 * package com.example.Entity;
 * 
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.orm.ObjectOptimisticLockingFailureException;
 * 
 * import static org.junit.jupiter.api.Assertions.*;
 * 
 * @SpringBootTest
 * public class BankAccountServiceTest {
 * 
 * @Autowired
 * private BankAccountRepository repository;
 * 
 * @Autowired
 * private BankAccountService service;
 * 
 * // This runs before EVERY test to make sure we have a clean database
 * 
 * @BeforeEach
 * public void setup() {
 * repository.deleteAll();
 * }
 * 
 * @Test
 * public void testOptimisticLocking_ThrowsExceptionOnConcurrentUpdate() {
 * // 1. Create a brand new account
 * BankAccount account = new BankAccount();
 * account.setName("Original Name");
 * account.setBalance(1000.0);
 * BankAccount savedAccount = repository.save(account);
 * 
 * // 2. Simulate User 1 reading the account
 * BankAccount user1Account = repository.findById(savedAccount.getId()).get();
 * 
 * // 3. Simulate User 2 reading the EXACT SAME account at the exact same time
 * BankAccount user2Account = repository.findById(savedAccount.getId()).get();
 * 
 * // 4. User 1 updates the name and saves successfully.
 * // Behind the scenes, the database @Version goes from 0 to 1.
 * user1Account.setName("User 1 Changed This");
 * repository.save(user1Account);
 * 
 * // 5. User 2 tries to update the name using their old copy (which still has
 * // version 0).
 * user2Account.setName("User 2 Changed This");
 * 
 * // 6. We ASSERT (expect) that Spring Boot will throw an Optimistic Locking
 * // Exception!
 * assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
 * repository.save(user2Account);
 * });
 * 
 * System.out.
 * println("✅ Optimistic Locking works! User 2 was blocked from overwriting User 1."
 * );
 * }
 * 
 * @Test
 * public void testPessimisticLocking_SuccessfulWithdrawal() {
 * // 1. Create an account with $500
 * BankAccount account = new BankAccount();
 * account.setName("John Doe");
 * account.setBalance(500.0);
 * BankAccount savedAccount = repository.save(account);
 * 
 * // 2. Call our Pessimistic Lock service method to withdraw $100
 * service.withdrawMoney(savedAccount.getId(), 100.0);
 * 
 * // 3. Fetch the account again to check the balance
 * BankAccount updatedAccount = repository.findById(savedAccount.getId()).get();
 * 
 * // 4. Assert the balance is exactly $400
 * assertEquals(400.0, updatedAccount.getBalance());
 * 
 * System.out.
 * println("✅ Pessimistic Locking works! Balance safely updated to $400.");
 * }
 * }
 */