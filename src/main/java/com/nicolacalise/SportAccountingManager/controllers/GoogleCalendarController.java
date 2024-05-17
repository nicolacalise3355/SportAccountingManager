package com.nicolacalise.SportAccountingManager.controllers;

import com.google.api.client.util.DateTime;
import com.nicolacalise.SportAccountingManager.services.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/calendar")
public class GoogleCalendarController {

    @Autowired
    private GoogleCalendarService googleCalendarService;

    @PostMapping("/addEvent")
    public void addEvent(@RequestParam String summary, @RequestParam String description,
                         @RequestParam String location, @RequestParam String startDateTime,
                         @RequestParam String endDateTime) throws Exception {
        DateTime start = DateTime.parseRfc3339(startDateTime);
        DateTime end = DateTime.parseRfc3339(endDateTime);

        googleCalendarService.addEvent(summary, description, location, start, end);
    }
}
