package com.dieboldnixdorf.feigndemo.controller;

import com.dieboldnixdorf.feigndemo.model.Account;
import com.dieboldnixdorf.feigndemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Value("${instance.name}")
    private String instance;

    @Autowired
    private AccountRepository repository;

    @RequestMapping(method = RequestMethod.GET, path = "/instance")
    public ResponseEntity<String> getIntance() {
        return new ResponseEntity<>(this.instance, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> customers = repository.getAccounts();
        log.info("accounts --> " + customers.toString());
        return ResponseEntity.ok(repository.getAccounts());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{token}")
    public ResponseEntity<Account> getCustomer(@PathVariable("token") String token) {
        log.info("/accounts/" + token);
        return ResponseEntity.ok(repository.findByToken(token));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> postCustomers(@RequestBody Account account) {
        log.info("accounts --> " + account.toString());
        repository.save(account);
        return new ResponseEntity<Account>(account, HttpStatus.CREATED);
    }

}

