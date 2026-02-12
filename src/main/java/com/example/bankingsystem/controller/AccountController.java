package com.example.bankingsystem.controller;

import com.example.bankingsystem.model.Account;
import com.example.bankingsystem.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public Account getAccount(@PathVariable Long accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account deposit(@PathVariable Long accountNumber,
                           @PathVariable Double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdraw(@PathVariable Long accountNumber,
                            @PathVariable Double amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    @DeleteMapping("/{accountNumber}")
    public String deleteAccount(@PathVariable Long accountNumber) {
        accountService.deleteAccount(accountNumber);
        return "Account Deleted Successfully";
    }
}

