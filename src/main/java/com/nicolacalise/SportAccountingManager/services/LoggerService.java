package com.nicolacalise.SportAccountingManager.services;

import com.nicolacalise.SportAccountingManager.config.AsyncConfig;
import com.nicolacalise.SportAccountingManager.runnable.AsyncSaveLogRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    @Value("${http.log.file}")
    private String file_path;

    @Autowired
    private AsyncConfig asyncConfig;

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
    public void _async_http_log_save(String msg){
        Thread t = new Thread(new AsyncSaveLogRunnable(asyncConfig.locker, file_path, msg));
        t.start();
    }

}
