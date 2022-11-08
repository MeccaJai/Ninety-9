package com.example.restaurant.controllers2;

import com.example.restaurant.models2.Accounts;
import com.example.restaurant.services2.AccountsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/accounts")
@RestController
public class AccountsController {

    public AccountsService accountsService;

    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/")
    public ArrayList<Accounts> getUserAccounts() throws ExecutionException, InterruptedException {
        return accountsService.getUserAccounts();
    }

    @GetMapping("/{id}")
    public Accounts getAccount(@PathVariable String id) throws ExecutionException, InterruptedException {
        return accountsService.getAccount(id);
    }

    @GetMapping("/deactivate/{id}")
    public void deactivateAccount(@PathVariable String id) throws ExecutionException, InterruptedException {
        accountsService.deactivateAccount(id);
    }
}
