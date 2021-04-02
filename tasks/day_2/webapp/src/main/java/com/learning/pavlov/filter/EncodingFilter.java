package com.learning.pavlov.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class EncodingFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }
}
