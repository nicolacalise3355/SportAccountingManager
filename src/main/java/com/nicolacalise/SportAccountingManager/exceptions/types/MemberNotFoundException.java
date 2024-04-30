package com.nicolacalise.SportAccountingManager.exceptions.types;

public class MemberNotFoundException extends MemberException{

    private int internalCode;

    public MemberNotFoundException(String message, int internalCode) {
        super(message);
        this.internalCode = internalCode;
    }
}
