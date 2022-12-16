package com.adidas.shopshoes.exception;

import com.adidas.shopshoes.dto.BaseResponse;
import com.adidas.shopshoes.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
@Slf4j
public class ConfigExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BaseResponse response = new BaseResponse();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setErrorCode(Constant.ERROR_CODE);
        response.setMessage(Constant.MESSAGE_VALID);
        response.setData(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResponse> handleAccessDenied(AccessDeniedException ex) {
        log.info(ex.getMessage());
        BaseResponse response = new BaseResponse();
        response.setMessage("FORBIDDEN!");
        response.setErrorCode(Constant.HTTP_CODE.FORBIDDEN);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        log.info(e.getMessage());
        BaseResponse response = new BaseResponse();
        response.setErrorCode(Constant.HTTP_CODE.INTERNAL_SERVER_ERROR);
        response.setMessage("INTERNAL_SERVER_ERROR!");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomCodeException.class)
    public BaseResponse handleCustomCodeException(CustomCodeException e) {
        log.info(e.getMessage());
        return e.getBaseResponse();
    }
}
