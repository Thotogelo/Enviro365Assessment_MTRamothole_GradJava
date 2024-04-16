package com.enviro.assessment.grad001.mtramothole.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

//    Method that is called before the handler is executed.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Log the request method, URI, and remote address.
        logger.info("Received request: {} {} from {}", request.getMethod(), request.getRequestURI(),
                request.getRemoteAddr());
        return true; // Return true to proceed to the next interceptor or the handler itself.
    }

    // Method that is called after the handler is executed.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Log the request method, URI, response status, and any exception that was
        // thrown.
        logger.info("Sent response: {} {} with status {} and exception {}", request.getMethod(),
                request.getRequestURI(), response.getStatus(), ex);
    }
}
