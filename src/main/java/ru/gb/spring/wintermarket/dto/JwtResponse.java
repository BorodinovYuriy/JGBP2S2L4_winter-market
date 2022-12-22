package ru.gb.spring.wintermarket.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//приходит логин/пароль, отдаем на его место токен
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
}

