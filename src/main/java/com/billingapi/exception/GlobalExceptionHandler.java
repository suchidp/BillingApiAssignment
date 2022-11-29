package com.billingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    /*Exception Handling for InvoiceNotFoundException
    Using InvoiceNotFoundException ,to check invoice is find or Not for given invoiceId
    * @param ex InvoiceNotFoundException exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> invoiceNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    /*
    Exception Handling of   handleHttpRequestMethodNotSupported

    Using HttpRequestMethodNotSupportedException check Method is supported or not
    * @param ex HttpRequestMethodNotSupportedException exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /*
    Exception Handling for Validations to check HttpMessageNotReadable
     * @param ex HttpMessageNotReadable exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /*
    Exception Handling using Validations to check MethodArgumentNotValidException
     Using handleMethodArgumentNotValid
     to check MethodArgumentNotValidException
     to validate @NotNull,@Pattern,@Digits,@DecimalMin,@Min and @Max
	 * @param  MethodArgumentNotValidException ex the exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {
        ErrorModelResponse error = new ErrorModelResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            error.getErrors().add(
                    new ExceptionResponse("The field " + fieldError.getField() + "  has  " + fieldError.getDefaultMessage()));
        }
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }


    /*
    Exception Handling  to check ConstraintViolationException

	 * @param ex the exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintValidationException(
            ConstraintViolationException ex, WebRequest request) {
        ErrorModelResponse error = new ErrorModelResponse();
        for (ConstraintViolation exceptionResponse : ex.getConstraintViolations()) {
            error.getErrors().add(
                    new ExceptionResponse(exceptionResponse.getMessage()));
        }
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }


    /*
    Exception Handling  to check BindException

	 * @param ex the exception to handle
	 * @param request the current request
	 * @return a  ResponseEntity for the response to use
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(ex.getMessage());
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(error);
        return new ResponseEntity<Object>(
                exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}


