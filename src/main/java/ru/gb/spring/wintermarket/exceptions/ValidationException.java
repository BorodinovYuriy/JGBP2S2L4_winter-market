package ru.gb.spring.wintermarket.exceptions;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ValidationException extends RuntimeException{
    private List<String> errorFieldMessages;
    public ValidationException(List<String> errorFieldMessages){
        super(errorFieldMessages.stream().collect(Collectors.joining(",  ")));
        this.errorFieldMessages = errorFieldMessages;
    }
}

