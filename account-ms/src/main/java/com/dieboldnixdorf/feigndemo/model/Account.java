package com.dieboldnixdorf.feigndemo.model;

public class Account {

    private String token;
    private String agency;
    private String account;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Account{" +
                "token='" + token + '\'' +
                ", agency='" + agency + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

}
