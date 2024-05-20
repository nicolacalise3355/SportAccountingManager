package com.nicolacalise.SportAccountingManager.controllers.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.util.DateTime;
import com.nicolacalise.SportAccountingManager.config.GoogleCalendarConfig;
import com.nicolacalise.SportAccountingManager.models.utility.Message;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ConfirmMessage;
import com.nicolacalise.SportAccountingManager.models.utility.messages.ErrorMessage;
import com.nicolacalise.SportAccountingManager.services.google.GoogleCalendarService;
import com.nicolacalise.SportAccountingManager.staticValues.GoogleMessages;
import com.nicolacalise.SportAccountingManager.staticValues.WorkdayMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/google/calendar")
public class GoogleCalendarController {

    @Autowired
    private GoogleCalendarService googleCalendarService;

    private GoogleCalendarConfig googleCalendarConfig;

    @Autowired
    public GoogleCalendarController(GoogleCalendarConfig googleCalendarConfig) {
        this.googleCalendarConfig = googleCalendarConfig;
    }

    @PostMapping("/addEvent")
    public ResponseEntity<Message> addEvent(@RequestParam String summary, @RequestParam String description,
                                            @RequestParam String location, @RequestParam String startDateTime,
                                            @RequestParam String endDateTime) {
        DateTime start = DateTime.parseRfc3339(startDateTime);
        DateTime end = DateTime.parseRfc3339(endDateTime);

        try {
            googleCalendarService.addEvent(summary, description, location, start, end);
            return ResponseEntity.ok(new ConfirmMessage(200, GoogleMessages.EVENT_ADDED, 1));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new ErrorMessage(400, e.getMessage(), 0));
        }
    }

}
