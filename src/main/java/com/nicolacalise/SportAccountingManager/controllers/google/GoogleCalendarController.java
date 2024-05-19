package com.nicolacalise.SportAccountingManager.controllers.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.util.DateTime;
import com.nicolacalise.SportAccountingManager.config.GoogleCalendarConfig;
import com.nicolacalise.SportAccountingManager.services.google.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public void addEvent(@RequestParam String summary, @RequestParam String description,
                         @RequestParam String location, @RequestParam String startDateTime,
                         @RequestParam String endDateTime) throws Exception {
        DateTime start = DateTime.parseRfc3339(startDateTime);
        DateTime end = DateTime.parseRfc3339(endDateTime);

        googleCalendarService.addEvent(summary, description, location, start, end);
    }

}
