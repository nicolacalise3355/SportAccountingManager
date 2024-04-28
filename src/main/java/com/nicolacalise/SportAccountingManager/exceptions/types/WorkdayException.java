package com.nicolacalise.SportAccountingManager.exceptions.types;

public class WorkdayException extends Exception{

    private int internalCodeError;

    public WorkdayException(String msg, int code){
        super(msg);
        this.internalCodeError = code;
    }
}
