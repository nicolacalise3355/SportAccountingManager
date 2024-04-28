package com.nicolacalise.SportAccountingManager.controllers;

import com.nicolacalise.SportAccountingManager.dtos.LoginDto;
import com.nicolacalise.SportAccountingManager.dtos.RegisterUserDto;
import com.nicolacalise.SportAccountingManager.models.entities.User;
import com.nicolacalise.SportAccountingManager.models.utility.LoginResponse;
import com.nicolacalise.SportAccountingManager.services.AuthenticationService;
import com.nicolacalise.SportAccountingManager.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.badRequest().body(null);
        //return ResponseEntity.ok(registeredUser); Not allowed now
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}
