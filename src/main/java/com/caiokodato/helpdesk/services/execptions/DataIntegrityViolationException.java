package com.caiokodato.helpdesk.services.execptions;



public class DataIntegrityViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String msg) {
        super(msg);
    }
}
