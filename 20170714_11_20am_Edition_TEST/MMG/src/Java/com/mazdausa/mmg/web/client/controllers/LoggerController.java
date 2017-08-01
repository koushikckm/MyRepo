/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.db.dao.iface.AccessLogDaoIFace;
import com.mazdausa.mmg.db.vo.AccessLogVO;
import com.sun.jersey.api.view.Viewable;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.portlet.ModelAndView;

/**
 * This is the Sample Test Controller, which is responsible for showing a set of log records to the client.
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/log")
public class LoggerController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    ApplicationConstants AppConstants;
    @Autowired
    AccessLogDaoIFace accessLog;

    @GET
    public Response log(@Context HttpServletRequest request) {


        AccessLogVO accessLogVO = new AccessLogVO();
        accessLogVO.setExecutionTime(5);
        accessLogVO.setInstallationGuid("3434534534534");
        accessLogVO.setRelativeApplicationURI(request.getPathInfo());
        accessLogVO.setRequestData("Saple request data.");
        accessLogVO.setRequestedTime(Calendar.getInstance().getTime());
        accessLogVO.setResponseCode(200);
        accessLogVO.setSessionId("JSESSIONID=" + System.currentTimeMillis());

        accessLog.addAccessLogDetails(accessLogVO);

        List<AccessLogVO> accessLogList = accessLog.getAllAccessLogDetails();
        request.setAttribute("list", accessLogList);


        return Response.ok(new Viewable("/logview", null)).build();



    }
}
