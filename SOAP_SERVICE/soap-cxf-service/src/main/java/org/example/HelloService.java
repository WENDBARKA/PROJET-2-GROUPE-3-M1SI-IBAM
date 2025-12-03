package org.example;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface HelloService {

    @WebMethod
    String saveClient(
            @WebParam(name = "client") Client client
    );
}
