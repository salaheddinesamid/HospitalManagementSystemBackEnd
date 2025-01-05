package com.hospitalmanagement.application.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Filter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getRemoteAddr());
    }
}
