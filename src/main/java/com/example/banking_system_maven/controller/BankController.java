package com.example.banking_system_maven.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.banking_system_maven.entity.BankAccount;
import com.example.banking_system_maven.service.BankService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Slf4j
public class BankController {

	@Autowired
    private final BankService service;

    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount account) {
        return ResponseEntity.ok(service.createAccount(account));
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllAccounts() {
        return ResponseEntity.ok(service.getAllAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
    	return service.getAccountByNumber(accountNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<String> deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        return service.deposit(accountNumber, amount)
                ? ResponseEntity.ok("Deposit successful")
                : ResponseEntity.badRequest().body("Account not found");
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        return service.withdraw(accountNumber, amount)
                ? ResponseEntity.ok("Withdrawal successful")
                : ResponseEntity.badRequest().body("Insufficient balance or account not found");
    }
}
