package com.dieboldnixdorf.feigndemo.feignclients;

import com.dieboldnixdorf.feigndemo.model.CustomerList;
import feign.RequestLine;

public interface CustomerClient {

    @RequestLine("GET /5b5722df310000412c4d210f")
    CustomerList getCustomers();

}
