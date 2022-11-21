package com.billingapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /*Exception Handling for InvoiceNotFoundException
     */
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> invoiceNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse errors = new ExceptionResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setMessage(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }

/* Exception Handling for Validations
  Using   onMethodArgumentNotValidException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorModelResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ErrorModelResponse error = new ErrorModelResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getErrorMessage().add(
                    new ValidationsError("The  " + fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }
*/



    /*
    Exception Handling for Validations to check handleHttpRequestMethodNotSupported

  Using Spring boot Inbuilt ResponseEntityExceptionHandler to check Method is supported or not

     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Please change your Method type", HttpStatus.NOT_FOUND);
    }


    /*
    Exception Handling using Validations to check MethodArgumentNotValidException

  Using Spring boot Inbuilt  handleMethodArgumentNotValid method of ResponseEntityExceptionHandler to check MethodArgumentNotValidException
    to validate @NotNull,@Pattern,@Digits,@DecimalMin,@Min and @Max
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorModelResponse error = new ErrorModelResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getErrorMessage().add(
                    new ValidationsError("The  " + fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
    /*
        Exception Handling using Validations to check MissingPathVariableException

      Using Spring boot Inbuilt  handleMissingPathVariable method of ResponseEntityExceptionHandler to check
        if pathvariable is missing or not
         */
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Please enter  Id", HttpStatus.NOT_FOUND);
    }


}

