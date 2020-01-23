package com.tsv.servlet;

import javax.servlet.*;
import java.io.IOException;

public class EncodingResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("**** EncodingResponseFilter *****");
        resp.setCharacterEncoding("UTF-8");
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
