package com.example.demo.student.exception;

import com.alibaba.fastjson.JSON;
import com.example.demo.student.dto.PlainResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = StudentException.class)
    public ResponseEntity<Object> handleStudentException(StudentException e, ServletWebRequest request) {
        HttpServletRequest httpServletRequest = request.getRequest();
        log.error("request url: {}, param: {}, code: {}, message: {}", httpServletRequest.getRequestURI()
                , JSON.toJSONString(httpServletRequest.getParameterMap()), e.getCode(), e.getMessage());
        return new ResponseEntity<>(PlainResult.fail(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(StudentException e, ServletWebRequest request) {
        HttpServletRequest httpServletRequest = request.getRequest();
        log.error("request url: {}, param: {},, message: {}", httpServletRequest.getRequestURI()
                , JSON.toJSONString(httpServletRequest.getParameterMap()), e.getMessage());
        return new ResponseEntity<>(PlainResult.fail(500, "系统异常，请稍后再试"), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
