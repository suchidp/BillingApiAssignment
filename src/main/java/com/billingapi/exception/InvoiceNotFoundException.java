package com.billingapi.exception;



/*
     * The custom Exception used to  check   an invoice is present or not   .
     */

public class InvoiceNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public InvoiceNotFoundException (String message) {
        super(message);
    }
	
	

}
