package ru.gb.spring.wintermarket.soap.converter;

import org.springframework.stereotype.Component;
import ru.gb.spring.wintermarket.entity.User;
import ru.gb.spring.wintermarket.soap.soapEntity.UserSoap;

@Component
public class SoapUserConverter {
    public User userSoapToUser(UserSoap userSoap){
        User user = new User();
        user.setId(userSoap.getId());
        user.setUsername(userSoap.getName());
        return user;
    }

    public UserSoap userToUserSoap(User user){
        UserSoap userSoap = new UserSoap();
        userSoap.setId(user.getId());
        userSoap.setName(user.getUsername());
        return userSoap;
    }

}
