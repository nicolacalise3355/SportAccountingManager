package com.nicolacalise.SportAccountingManager.exceptions.types;

public class WorkdayNotFoundException extends WorkdayException{
    private int internalCodeError;

    public WorkdayNotFoundException(String msg, int code){
        super(msg, code);
        this.internalCodeError = code;
    }

}
