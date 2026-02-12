package com.example.bankingsystem.repository;

import com.example.bankingsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(Long accountNumber);

}

