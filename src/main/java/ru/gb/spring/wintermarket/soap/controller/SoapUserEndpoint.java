package ru.gb.spring.wintermarket.soap.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.spring.wintermarket.soap.GetAllUsersRequest;
import ru.gb.spring.wintermarket.soap.GetAllUsersResponse;
import ru.gb.spring.wintermarket.soap.GetUserByNameRequest;
import ru.gb.spring.wintermarket.soap.GetUserByNameResponse;
import ru.gb.spring.wintermarket.soap.service.SoapService;
@Slf4j
@Endpoint
@RequiredArgsConstructor
public class SoapUserEndpoint {
    private final SoapService soapService;
    private static final String NAMESPACE_URI = "http://www.borodinov.ru/spring/ws/users";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByNameRequest")
    @ResponsePayload
    public GetUserByNameResponse getUserByName(@RequestPayload GetUserByNameRequest request){
        GetUserByNameResponse response = new GetUserByNameResponse();
        response.setUser(soapService.getSoapUserByName(request.getName()));
        return response;
    }
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request){
        log.warn("getAllUsersRequest!!!!");
        GetAllUsersResponse response = new GetAllUsersResponse();
        soapService.getAllSoapUsers().forEach(response.getUsers()::add);
        return response;
    }
    /*
                        Пример запроса:

     POST http://localhost:8189/winter/ws
     Header -> Content-Type: text/xml

<soap-env:Envelope xmlns:soap-env="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:f="http://www.borodinov.ru/spring/ws/users">
        <soap-env:Header/>
        <soap-env:Body>
            <f:getAllUsersRequest/>
        </soap-env:Body>
</soap-env:Envelope>
     */

//                  ВЫДАЕТ ТАКУЮ ОШИБКУ:
//<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
//    <SOAP-ENV:Header/>
//    <SOAP-ENV:Body>
//        <SOAP-ENV:Fault>
//            <faultcode>SOAP-ENV:Server</faultcode>
//            <faultstring xml:lang="en">No adapter for endpoint [public ru.gb.spring.wintermarket.soap.GetAllUsersResponse ru.gb.spring.wintermarket.soap.controller.SoapUserEndpoint.getAllUsers(ru.gb.spring.wintermarket.soap.GetAllUsersRequest)]: Is your endpoint annotated with @Endpoint, or does it implement a supported interface like MessageHandler or PayloadEndpoint?</faultstring>
//        </SOAP-ENV:Fault>
//    </SOAP-ENV:Body>
//</SOAP-ENV:Envelope>







}
