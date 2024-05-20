package com.nicolacalise.SportAccountingManager.utility;

import jakarta.servlet.http.HttpServletRequest;

public class LoggingUtility {

    public static String generateHttpLogForFile(HttpServletRequest request){
        String req = "[" + request.getMethod() + "] - ";
        String uri = request.getRequestURI() + " - ";
        String time = TimeUtility.getFormattedDateTime() + " - ";
        String user = request.getRemoteUser();
        return req + uri + time + user;
    }

}
