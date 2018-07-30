package com.dieboldnixdorf.feigndemo.repository;

import com.dieboldnixdorf.feigndemo.model.Account;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@CacheConfig(cacheNames =  "accounts")
public class AccountRepository {

    private Map<String, Account> accountsMap;

    @PostConstruct
    private void init() {
        this.accountsMap = new HashMap<>();
    }

    public List<Account> getAccounts() {
        return this.accountsMap.values().stream().collect(Collectors.toList());
    }

    @Cacheable(value = "accounts", key = "#token")
    public Account findByToken(String token) {
        return this.accountsMap.get(token);
    }

    @CachePut(value = "accounts")
    public Account save(Account account) {
        account.setToken(UUID.randomUUID().toString());
        this.accountsMap.put(account.getToken(), account);
        return account;
    }

}
