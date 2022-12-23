package ru.gb.spring.wintermarket.soap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.spring.wintermarket.entity.User;
import ru.gb.spring.wintermarket.exceptions.ResourceNotFoundException;
import ru.gb.spring.wintermarket.services.UserService;
import ru.gb.spring.wintermarket.soap.converter.SoapUserConverter;
import ru.gb.spring.wintermarket.soap.soapEntity.UserSoap;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SoapService {
    private final UserService userService;
    private final SoapUserConverter soapUserConverter;


    public static final Function<User, UserSoap> functionEntityToSoap = se -> {
        UserSoap s = new UserSoap();
        s.setId(se.getId());
        s.setName(se.getUsername());
        return s;
    };

    public List<UserSoap> getAllSoapUsers() {
        return   userService.findAll().stream()
                .map(functionEntityToSoap)
                .toList();
    }
    public UserSoap getSoapUserByName(String name) {
        return userService.findByUsername(name)
                .map(functionEntityToSoap)
                .orElseThrow(()->
                        new ResourceNotFoundException("User not found exception!"));
    }




//    public List<UserSoap> getAllSoapUsers() {
//        return   userService.findAll().stream()
//                .map(soapUserConverter::userToUserSoap)
//                .toList();
//    }
//    public UserSoap getSoapUserByName(String name) {
//        return soapUserConverter.userToUserSoap(userService.findByUsername(name).
//                orElseThrow(() ->new ResourceNotFoundException("User not found!")));
//    }

}
