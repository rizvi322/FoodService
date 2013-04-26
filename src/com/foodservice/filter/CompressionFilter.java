package com.foodservice.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

public class CompressionFilter implements Filter {

    static Logger log = Logger.getLogger(CompressionFilter.class.getName());
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
        this.filterConfig = filterConfig;
        log.info(filterConfig.getFilterName() + " initialized.");
    }

    @Override
    public void destroy() {
        log.info(filterConfig.getFilterName() +  " destroyed.");
    }

    public void doFilter(ServletRequest pRequest, ServletResponse pResponse, FilterChain chain) throws IOException, ServletException {
        // Can only filter HTTP responses
        if (pRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) pRequest;
            HttpServletResponse response = (HttpServletResponse) pResponse;

            // If GZIP is supported, use compression
            String accept = request.getHeader("Accept-Encoding");
            if (accept != null && accept.indexOf("gzip") != -1) {
                CompressionResponseWrapper wrapped = new CompressionResponseWrapper(response);
                wrapped.setHeader("Content-Encoding","gzip");
                try {
                    chain.doFilter(pRequest, wrapped);
                }
                finally {
                    wrapped.flushResponse();
                }
                return;
            }
        }

        // Else, contiue chain
        chain.doFilter(pRequest, pResponse);
    }
}