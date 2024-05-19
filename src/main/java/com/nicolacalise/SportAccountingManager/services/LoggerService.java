package com.nicolacalise.SportAccountingManager.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    /**
     * @use: used in login controller to log logins with success
     */
    public void _auth_log(String username) {
        logger.info("[INFO] LOGIN: " + username);
    }

    /**
     * @use: used in http interceptor to log http req
     */
    public void _http_log(){
        logger.info("");
    }

}
