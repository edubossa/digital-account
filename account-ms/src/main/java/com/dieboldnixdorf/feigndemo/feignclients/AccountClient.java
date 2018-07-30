package com.dieboldnixdorf.feigndemo.feignclients;

import com.dieboldnixdorf.feigndemo.model.Account;
import feign.RequestLine;

import java.util.List;

//@FeignClient(value = "accountClient", url = "http://www.mocky.io/v2")
public interface AccountClient {

    @RequestLine("GET /5b55ff3d32000045468280ed")
    Account findAccount();

    @RequestLine("GET /5b55ff653200006a428280ee")
    List<Account> findAccountList();

}
