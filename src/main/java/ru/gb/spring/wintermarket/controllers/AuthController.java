package ru.gb.spring.wintermarket.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.wintermarket.dto.JwtRequest;
import ru.gb.spring.wintermarket.dto.JwtResponse;
import ru.gb.spring.wintermarket.dto.StringResponse;
import ru.gb.spring.wintermarket.services.UserService;
import ru.gb.spring.wintermarket.utils.JwtTokenUtil;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
     private final UserService userService;
     private final JwtTokenUtil jwtTokenUtil;
     private final AuthenticationManager authenticationManager;//спринг-бин аутентификации
     @PostMapping("/auth")
    public ResponseEntity<?>createAuthToken(@RequestBody JwtRequest authRequests){
         try{//проверяем наличие авторизации (ТУТ ПРОВЕРЯЕТ ЛОГИН И ПАРОЛЬ!)
             authenticationManager
                     .authenticate(new UsernamePasswordAuthenticationToken
                             (authRequests.getUsername(), authRequests.getPassword()));

         }catch (BadCredentialsException e){
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }

         //проверили, тогда достаем из базы userDetails
         UserDetails userDetails = userService.loadUserByUsername(authRequests.getUsername());
         log.warn(userDetails.getUsername() + "->userDetails-AuthControl->AUTHORIZED");
         //По нему собираем токен
         String token = jwtTokenUtil.generateToken(userDetails);
         //клиенту идёт ответ Json с полем token
         log.warn("Token is " + token.toString());
         return ResponseEntity.ok(new JwtResponse(token));
    }
    @GetMapping("/secured")
        public String testSecurity(){
         return "Security is OK";
        }
    @GetMapping("/auth_check")
    public StringResponse authCheck(Principal principal){
        return new StringResponse("/auth_check " + principal.getName());
    }

}
