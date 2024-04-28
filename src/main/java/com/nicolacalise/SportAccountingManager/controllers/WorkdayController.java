package com.nicolacalise.SportAccountingManager.controllers;

import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayException;
import com.nicolacalise.SportAccountingManager.exceptions.types.WorkdayNotFoundException;
import com.nicolacalise.SportAccountingManager.models.DataResponse;
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

    @GetMapping("workday/{id}")
    public ResponseEntity<DataResponse> getWorkday(@PathVariable("id") int id){
        try {
            Workday w = this.workdayService.getWorkday(id);
            return ResponseEntity.ok().body(new DataResponse(200, w));
        } catch (WorkdayException e) {
            return ResponseEntity.badRequest().body(new DataResponse(400, e.getMessage()));
        } catch (WorkdayNotFoundException e) {
            return ResponseEntity.status(404).body(new DataResponse(404, e.getMessage()));
        }
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
