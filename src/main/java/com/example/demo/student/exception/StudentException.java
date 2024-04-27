package com.example.demo.student.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentException extends RuntimeException {

    private Integer code;

    public StudentException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
