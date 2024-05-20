package com.nicolacalise.SportAccountingManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AsyncConfig {

    public Object locker = new Object();

}
