package org.eclipse.rest.api.security.filter;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.lang3.time.StopWatch;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class TimeWrapper implements Filter {
    @Inject Logger logger;
    public void doFilter(ServletRequest request,
        ServletResponse response, FilterChain chain) 
        throws IOException, ServletException {
        // do pre-servlet work here
        StopWatch watch = new StopWatch();
        watch.start();
        logger.info("Time Start: " + watch.getTime());
        chain.doFilter(request, response);
        // do post servlet work here
        watch.stop();
        logger.info("Time Elapsed: " + watch.getTime());
    }
}