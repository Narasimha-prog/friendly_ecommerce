package com.lnreddy.friendlyecommerce.shared.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, jakarta.servlet.ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        long start = System.currentTimeMillis();

        log.info("Incoming request → {} {}", req.getMethod(), req.getRequestURI());

        chain.doFilter(request, response);

        long duration = System.currentTimeMillis() - start;

        log.info("Completed request → {} ({} ms)", req.getRequestURI(), duration);
    }
}
