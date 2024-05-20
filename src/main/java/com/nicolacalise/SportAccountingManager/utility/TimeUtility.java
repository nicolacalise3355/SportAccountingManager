package com.nicolacalise.SportAccountingManager.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {

    public static String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy ss:mm:HH");
        return now.format(formatter);
    }

}
