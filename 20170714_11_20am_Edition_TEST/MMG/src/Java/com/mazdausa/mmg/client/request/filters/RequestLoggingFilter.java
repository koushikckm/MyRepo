/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.client.request.filters;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.session.vo.ClientSessionVO;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.business.iface.ApplicationBusIFace;
import com.mazdausa.mmg.db.vo.AccessLogVO;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

/**
 * This is the filter which is being responsible for Logging the request details to the database.
 * @author PankajB
 * @version 1.0
 */
public class RequestLoggingFilter extends GenericFilterBean {

    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    EmmUtilities EmmUtilities;
    @Autowired
    ApplicationBusIFace applicationService;

    /**
     * This is the method which will be invoked  for each and every request that is being passed through EMM>
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Log only, if the flag has been enabled
        if (EmmConstants.EMM_REQUEST_DB_LOGGING == true) {

            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            StatusExposingServletResponse statusExposingServletResponse = new StatusExposingServletResponse(httpServletResponse);
            AccessLogVO accessLogVO = new AccessLogVO();
            long startTime = System.currentTimeMillis();

            // Start Creating Access Log Value Object for insertion in the Database.
            accessLogVO.setRequestedTime(Calendar.getInstance().getTime());
                accessLogVO.setInstallationGuid(EmmConstants.EMM_DEFAULT_GUID);
                accessLogVO.setSessionId(System.currentTimeMillis()+(int)(Math.random()*10000)+"");
            // Starting check for session.
            /*if (httpServletRequest.getSession(false) != null && httpServletRequest.isRequestedSessionIdValid()) {
                // Here make sure, you get the data VO that holds the details from the session.
                ClientSessionVO clientSessionVO = EMMConstants.getTOKENTABLE().get(httpServletRequest.getSession().getId());
                accessLogVO.setInstallationGuid(clientSessionVO.getGuid());
                accessLogVO.setSessionId(httpServletRequest.getSession().getId());

            } else {
                accessLogVO.setInstallationGuid(EmmConstants.EMM_DEFAULT_GUID);
                accessLogVO.setSessionId("----");
            }*/

            accessLogVO.setRelativeApplicationURI(httpServletRequest.getRequestURI());

            filterChain.doFilter(httpServletRequest, statusExposingServletResponse);

            double executionTime = System.currentTimeMillis() - startTime;
            accessLogVO.setExecutionTime((int) executionTime);

            // Storing the HTTP RESPONSE CODE.
            if (statusExposingServletResponse.httpStatus == HttpServletResponse.SC_OK) {
                accessLogVO.setResponseCode(HttpServletResponse.SC_OK);
            } else {
                accessLogVO.setResponseCode(statusExposingServletResponse.httpStatus);
            }

            applicationService.logAuditRecord(accessLogVO);

        } // CLOSING THE IF.
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

/**
 * This is the class which will Extend the Servlet Response and store the status Code.
 * @author PankajB
 * @version 1.0
 */
class StatusExposingServletResponse extends HttpServletResponseWrapper {

    public int httpStatus;

    public StatusExposingServletResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void sendError(int sc) throws IOException {
        httpStatus = sc;
        super.sendError(sc);
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        httpStatus = sc;
        super.sendError(sc, msg);
    }

    @Override
    public void setStatus(int sc) {
        httpStatus = sc;
        super.setStatus(sc);
    }

    public int getStatus() {
        return httpStatus;
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        httpStatus = 302;
        super.sendRedirect(location);
    }
}
