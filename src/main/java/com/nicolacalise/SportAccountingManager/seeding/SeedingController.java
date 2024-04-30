package com.nicolacalise.SportAccountingManager.seeding;

import com.nicolacalise.SportAccountingManager.models.utility.Message;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ConfirmMessage;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/seeding/")
public class SeedingController {

    private SeedingService seedingService;

    @Autowired
    public SeedingController(SeedingService seedingService){
        this.seedingService = seedingService;
    }

    @PostMapping("init")
    public ResponseEntity<Message> initDatabaseData(@RequestBody SeedingObjectDto s){
        if(s == null) return ResponseEntity.badRequest().body(new ErrorMessage(400, SeedingMessages.SEEDING_DATA_NOT_VALID, 0));
        if(!s.getKey().equals(StaticSeedingData.SEED_KEY)) return ResponseEntity.status(403).body(new ErrorMessage(403, SeedingMessages.SEEDING_DATA_NOT_VALID, 0));
        this.seedingService.initDatabase();
        return ResponseEntity.ok(new ConfirmMessage(200, SeedingMessages.SEEDING_DONE, 1));
    }

    @PostMapping("clean")
    public ResponseEntity<Message> cleanDatabase(@RequestBody SeedingObjectDto s){
        if(s == null) return ResponseEntity.badRequest().body(new ErrorMessage(400, SeedingMessages.SEEDING_DATA_NOT_VALID, 0));
        if(!s.getKey().equals(StaticSeedingData.SEED_KEY)) return ResponseEntity.status(403).body(new ErrorMessage(403, SeedingMessages.SEEDING_DATA_NOT_VALID, 0));
        this.seedingService.cleanDatabase();
        return ResponseEntity.ok(new ConfirmMessage(200, SeedingMessages.CLEAN_DATABASE, 1));
    }

}
