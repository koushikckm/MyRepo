/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.session.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author PankajB
 */
public final class TestFilter implements Filter{

    private Logger logger=LoggerFactory.getLogger(TestFilter.class);
    private FilterConfig filterConfig = null;



    /**
     * This is the Initialization function which will get invoked at the time of initialization of the FILTER.
     * @param filterConfig
     * @throws ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info(">> Initializing the Filter  ");
        this.filterConfig=filterConfig;
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hey Coming Here in the filter ");
        filterChain.doFilter(request, response);
    }

    /**
     * This is the destroy function that is called automatically by COntainer.
     */
    public void destroy() {
        
    }

    

}
