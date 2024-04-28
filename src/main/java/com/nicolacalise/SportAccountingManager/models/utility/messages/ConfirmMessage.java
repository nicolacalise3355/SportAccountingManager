package com.nicolacalise.SportAccountingManager.models.utility.messages;

import com.nicolacalise.SportAccountingManager.models.utility.Message;

public class ConfirmMessage extends Message {

    private int internalCode;

    public ConfirmMessage(int code, String message, int internalCode) {
        super(code, message);
        this.internalCode = internalCode;
    }
}
