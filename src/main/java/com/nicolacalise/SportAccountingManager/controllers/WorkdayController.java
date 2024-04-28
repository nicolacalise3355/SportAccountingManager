package com.nicolacalise.SportAccountingManager.controllers;

import com.nicolacalise.SportAccountingManager.exceptions.WorkdayException;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import com.nicolacalise.SportAccountingManager.models.utility.Message;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ConfirmMessage;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ErrorMessage;
import com.nicolacalise.SportAccountingManager.services.WorkdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class WorkdayController {

    private WorkdayService workdayService;

    @Autowired
    public WorkdayController(WorkdayService workdayService){
        this.workdayService = workdayService;
    }

    @GetMapping("workday/list")
    public ResponseEntity<List<Workday>> getWorkdays(){
        return ResponseEntity.ok().body(this.workdayService.getWorkdays());
    }

    @PostMapping("workday")
    public ResponseEntity<Message> insertWorkday(@RequestBody Workday w){
        try{
            this.workdayService.insertWorkday(w);
            return ResponseEntity.ok(new ConfirmMessage(200, "Workday Added.", 1));
        }catch(WorkdayException e){
            return ResponseEntity.badRequest().body(new ErrorMessage(400, e.getMessage(), 0));
        }
    }
}
