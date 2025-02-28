package com.caiokodato.helpdesk.services.execptions;

public class ObjectnotFoundExecption extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectnotFoundExecption(String msg) {
        super(msg);
    }
}
