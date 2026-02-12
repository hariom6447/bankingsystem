package com.example.bankingsystem.service;
import com.example.bankingsystem.repository.AccountRepository;

import com.example.bankingsystem.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl {

    private final AccountRepository accountRepository;
    private final Random random = new Random();

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        Long accNo = 1000000000L + random.nextInt(900000000);
        account.setAccountNumber(accNo);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
    }

    public Account deposit(Long accountNumber, Double amount) {
        Account acc = getAccountByNumber(accountNumber);
        acc.setBalance(acc.getBalance() + amount);
        return accountRepository.save(acc);
    }

    public Account withdraw(Long accountNumber, Double amount) {
        Account acc = getAccountByNumber(accountNumber);

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        return accountRepository.save(acc);
    }

    public void deleteAccount(Long accountNumber) {
        Account acc = getAccountByNumber(accountNumber);
        accountRepository.delete(acc);
    }
}
