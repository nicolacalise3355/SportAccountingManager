package com.nicolacalise.SportAccountingManager.controllers.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.nicolacalise.SportAccountingManager.config.GoogleCalendarConfig;
import com.nicolacalise.SportAccountingManager.services.google.GoogleCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class GoogleAuthController {

    @Value("${google.calendar.auth.redirect_uri}")
    private String REDIRECT_URI;

    private GoogleCalendarService googleCalendarService;
    private GoogleCalendarConfig googleCalendarConfig;

    @Autowired
    public GoogleAuthController(GoogleCalendarConfig googleCalendarConfig, GoogleCalendarService googleCalendarService) {
        this.googleCalendarConfig = googleCalendarConfig;
        this.googleCalendarService = googleCalendarService;
    }

    @GetMapping("/google/calendar")
    public ResponseEntity<String> authorize() throws Exception {
        GoogleAuthorizationCodeFlow flow = googleCalendarConfig.getFlow();

        String authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
        return ResponseEntity.ok(authorizationUrl);
    }

    @GetMapping("/google/calendar/oauth2callback")
    public ResponseEntity<String> oauth2callback(@RequestParam("code") String code) throws Exception {
        GoogleAuthorizationCodeFlow flow = googleCalendarConfig.getFlow();
        GoogleTokenResponse gtr = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        Credential credential = googleCalendarConfig.createCredentialWithTokenResponse(flow, gtr);
        googleCalendarConfig.setCredential(credential);

        this.googleCalendarService.initializeCalendarService();

        return ResponseEntity.ok("Authorization successful. You can now use the calendar.");
    }
}
