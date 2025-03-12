package com.example.banking_system_maven.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_system_maven.entity.BankAccount;
import com.example.banking_system_maven.repository.BankAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankService {

	@Autowired
    private final BankAccountRepository repository;

    public BankAccount createAccount(BankAccount account) {
        log.info("Creating account for {}", account.getAccountHolder());
        return repository.save(account);
    }

    public List<BankAccount> getAllAccounts() {
        log.info("Fetching all accounts");
        return repository.findAll();
    }

    public Optional<BankAccount> getAccountByNumber(String accountNumber) {
        log.info("Fetching account with number {}", accountNumber);
        return repository.findByAccountNumber(accountNumber);
    }
    
    public boolean deposit(String accountNumber, double amount) {
        return repository.findByAccountNumber(accountNumber).map(account -> {
            log.info("Depositing ${} to account {}", amount, accountNumber);
            account.setBalance(account.getBalance() + amount);
            repository.save(account);
            return true;
        }).orElse(false);
    }

    public boolean withdraw(String accountNumber, double amount) {
        return repository.findByAccountNumber(accountNumber).map(account -> {
            if (account.getBalance() >= amount) {
                log.info("Withdrawing ${} from account {}", amount, accountNumber);
                account.setBalance(account.getBalance() - amount);
                repository.save(account);
                return true;
            }
            return false;
        }).orElse(false);
    }
}

