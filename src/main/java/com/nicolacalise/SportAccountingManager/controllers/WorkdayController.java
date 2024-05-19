package com.nicolacalise.SportAccountingManager.controllers;

import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayException;
import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayNotFoundException;
import com.nicolacalise.SportAccountingManager.models.DataResponse;
import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import com.nicolacalise.SportAccountingManager.models.utility.Message;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ConfirmMessage;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ErrorMessage;
import com.nicolacalise.SportAccountingManager.services.entityServices.WorkdayService;
import com.nicolacalise.SportAccountingManager.staticValues.ParamsMessages;
import com.nicolacalise.SportAccountingManager.staticValues.WorkdayMessages;
import com.nicolacalise.SportAccountingManager.validators.EntityValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class WorkdayController {

    private WorkdayService workdayService;
    private EntityValidators entityValidators;

    @Autowired
    public WorkdayController(WorkdayService workdayService, EntityValidators entityValidators){
        this.workdayService = workdayService;
        this.entityValidators = entityValidators;
    }

    @GetMapping("workday/list")
    public ResponseEntity<List<Workday>> getWorkdays(){
        return ResponseEntity.ok().body(this.workdayService.getWorkdays());
    }

    @GetMapping("workday/{id}")
    public ResponseEntity<DataResponse> getWorkday(@PathVariable("id") int id){
        if(id < 0) return ResponseEntity.badRequest().body(new DataResponse(400, ParamsMessages.ID_NOT_VALID));
        try {
            Workday w = this.workdayService.getWorkday(id);
            return ResponseEntity.ok().body(new DataResponse(200, w));
        } catch (WorkdayNotFoundException e) {
            return ResponseEntity.status(404).body(new DataResponse(404, e.getMessage()));
        }
    }

    @PostMapping("workday")
    public ResponseEntity<Message> insertWorkday(@RequestBody Workday w){
        if(!this.entityValidators.isWorkdayValid(w)) return ResponseEntity.badRequest().body(new ErrorMessage(400, WorkdayMessages.WORKDAY_NOT_VALID, 0));
        try{
            this.workdayService.insertWorkday(w);
            return ResponseEntity.ok(new ConfirmMessage(200, WorkdayMessages.WORKDAY_ADDED, 1));
        }catch(WorkdayException e){
            return ResponseEntity.badRequest().body(new ErrorMessage(400, e.getMessage(), 0));
        }
    }

    @PutMapping("workday/{id}")
    public ResponseEntity<Message> updateWorkday(@PathVariable("id") int id, @RequestBody Workday w){
        if(id < 0) return ResponseEntity.badRequest().body(new ErrorMessage(400, ParamsMessages.ID_NOT_VALID, 0));
        if(!this.entityValidators.isWorkdayValid(w)) return ResponseEntity.badRequest().body(new ErrorMessage(400, WorkdayMessages.WORKDAY_NOT_VALID, 0));
        try {
            this.workdayService.updateWorkday(id, w);
            return ResponseEntity.ok(new ConfirmMessage(200, WorkdayMessages.WORKDAY_UPDATED, 1));
        } catch (WorkdayNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(404, e.getMessage(), 0));
        }
    }

    @DeleteMapping("workday/{id}")
    public ResponseEntity<Message> deleteWorkday(@PathVariable("id") int id){
        if(id < 0) return ResponseEntity.badRequest().body(new ErrorMessage(400, ParamsMessages.ID_NOT_VALID, 0));
        try {
            this.workdayService.deleteWorkday(id);
            return ResponseEntity.ok(new ConfirmMessage(200, WorkdayMessages.WORKDAY_DELETED, 1));
        } catch (WorkdayNotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(404, e.getMessage(), 0));
        }
    }

}
