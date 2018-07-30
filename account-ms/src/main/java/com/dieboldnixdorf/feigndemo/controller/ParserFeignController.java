package com.dieboldnixdorf.feigndemo.controller;

import com.dieboldnixdorf.feigndemo.feignclients.AccountClient;
import com.dieboldnixdorf.feigndemo.feignclients.CustomerClient;
import com.dieboldnixdorf.feigndemo.model.Account;
import com.dieboldnixdorf.feigndemo.model.Customer;
import com.dieboldnixdorf.feigndemo.model.CustomerList;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/accounts/parser")
public class ParserFeignController {

    AccountClient accountClient;

    CustomerClient customerClient;

    @PostConstruct
    private void init() {
        this.accountClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(AccountClient.class, "http://www.mocky.io/v2");

        JAXBContextFactory factory = new JAXBContextFactory.Builder()
                .withMarshallerJAXBEncoding("UTF-8")
                .build();

        this.customerClient =Feign.builder()
                .encoder(new JAXBEncoder(factory))
                .decoder(new JAXBDecoder(factory))
                .target(CustomerClient.class, "http://www.mocky.io/v2");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/json")
    public ResponseEntity<List<Account>> getJson() {
        List<Account> accountList = this.accountClient.findAccountList();
        System.out.println(accountList);
        return ResponseEntity.ok(accountList);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Customer> getXML() {
        CustomerList customers = this.customerClient.getCustomers();
        System.out.println(customers);
        return customers.getCustomers();
    }

}
