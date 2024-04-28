package com.nicolacalise.SportAccountingManager.exceptions.types;

public class WorkdayNotFoundException extends Exception{
    private int internalCodeError;

    public WorkdayNotFoundException(String msg, int code){
        super(msg);
        this.internalCodeError = code;
    }

}
