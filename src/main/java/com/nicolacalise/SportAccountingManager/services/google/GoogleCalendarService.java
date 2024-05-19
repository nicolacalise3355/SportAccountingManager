package com.nicolacalise.SportAccountingManager.services.google;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.nicolacalise.SportAccountingManager.config.GoogleCalendarConfig;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.TimeZone;

@Service
public class GoogleCalendarService {

    private Calendar calendarService;

    @Autowired
    private GoogleCalendarConfig googleCalendarConfig;

    @Autowired
    private ObjectFactory<Calendar> calendarFactory;

    public void initializeCalendarService() throws Exception {
        if (googleCalendarConfig.getCredential() != null) {
            System.out.println("[@GoogleSerivce] Init Calendar Service.");
            this.calendarService = this.googleCalendarConfig.googleCalendarConfigService();
        }
    }

    public void addEvent(String summary, String description, String location, DateTime startDateTime, DateTime endDateTime) throws IOException {
        Event event = new Event()
                .setSummary(summary)
                .setDescription(description)
                .setLocation(location);

        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startDateTime.toStringRfc3339()))
                .setTimeZone(TimeZone.getDefault().getID());
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endDateTime.toStringRfc3339()))
                .setTimeZone(TimeZone.getDefault().getID());
        event.setEnd(end);

        calendarService.events().insert("primary", event).execute();
    }

}
