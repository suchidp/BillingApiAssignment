package com.billingapi.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /*Exception Handling for InvoiceNotFoundException
     */
    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> invoiceNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }


    /*
    Exception Handling for Validations to check handleHttpRequestMethodNotSupported

  Using Spring boot Inbuilt ResponseEntityExceptionHandler to check Method is supported or not

     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }


    /*
    Exception Handling using Validations to check MethodArgumentNotValidException
  Using Spring boot Inbuilt  handleMethodArgumentNotValid method of ResponseEntityExceptionHandler ,
  to check MethodArgumentNotValidException
   to validate @NotNull,@Pattern,@Digits,@DecimalMin,@Min and @Max
	 * @param ex the exception to handle
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorModelResponse error = new ErrorModelResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getErrorMessage().add(
                    new ValidationsError("The  " + fieldError.getField(), fieldError.getDefaultMessage(), LocalDateTime.now()));
        }
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);

    }


    /*
    Exception Handling  to check BindException
/*
	 * @param ex the exception to handle
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


/*
        Exception Handling  to check MethodArgumentTypeMismatch .
       It throws a type mismatch when trying  invalid parameter type conversion throws a TypeMismatchException,
       that we can handle with a method
         * @param ex the MethodArgumentTypeMismatchException to handle
         * @param request the current request
         * @return a  ResponseEntity for the response to use
         */

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), error);
        return new ResponseEntity<Object>(

                exceptionResponse, new HttpHeaders(), exceptionResponse.getStatus());
    }
}


