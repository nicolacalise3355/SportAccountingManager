package com.nicolacalise.SportAccountingManager.controllers;

import com.nicolacalise.SportAccountingManager.models.utility.Message;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ConfirmMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    public DebugController(){}

    @GetMapping("/debug")
    public ResponseEntity<Message> getStatus(){
        return ResponseEntity.ok().body(new ConfirmMessage(200, "STATUS_OK", 0));
    }
}
