package ru.gb.spring.wintermarket.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FieldsValidatorError {
    private List<String> errorFieldMessages;

    public FieldsValidatorError(List<String> errorFieldMessages){
        this.errorFieldMessages = errorFieldMessages;
    }
}

