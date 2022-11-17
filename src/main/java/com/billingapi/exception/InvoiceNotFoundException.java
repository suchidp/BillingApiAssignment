package com.billingapi.exception;

public class InvoiceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvoiceNotFoundException(String message) {
        super(message);
    }
}