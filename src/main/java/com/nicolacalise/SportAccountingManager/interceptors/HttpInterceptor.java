package com.nicolacalise.SportAccountingManager.interceptors;

import com.nicolacalise.SportAccountingManager.services.LoggerService;
import com.nicolacalise.SportAccountingManager.utility.LoggingUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HttpInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggerService logger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
        logger._async_http_log_save(LoggingUtility.generateHttpLogForFile(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model){
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception){
    }
}
