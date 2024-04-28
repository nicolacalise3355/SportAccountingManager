package com.nicolacalise.SportAccountingManager.models.utility.messages;

import com.nicolacalise.SportAccountingManager.models.utility.Message;

public class ErrorMessage extends Message {

    int internalCode;

    public ErrorMessage(int code, String message, int internalCode) {
        super(code, message);
        this.internalCode = internalCode;
    }

    public int getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(int internalCode) {
        this.internalCode = internalCode;
    }
}
