/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.mobile.vo.EmmSessionTokenVO;
import com.emm.v1.response.vo.EmmClientResponseVO;
import com.emm.v1.session.vo.ClientSessionVO;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.web.client.service.iface.ApplicationServiceIFace;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This is Token Generator Controller and being responsible for generating the TOKEN and Session ID for the Client. So that later on
 * for each and every request the token and the session will be verified.
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/token")
public class ClientTokenGeneratorController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClientTokenGeneratorController.class);
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    ApplicationServiceIFace applicationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.TEXT_PLAIN})
    public EmmClientResponseVO getToken(@HeaderParam(ApplicationConstants.USER_AGENT) String userAgent, @Context HttpServletRequest request,
            @HeaderParam(ApplicationConstants.GUID_HEADER) String appInstallationGuid,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering Token Controller.");
        EmmClientResponseVO clientResponseVO = new EmmClientResponseVO();
        EmmSessionTokenVO sessionTokenVO = EmmUtilities.getSessionAndToken(request);

        // Verify the Authenticity of the GUID
        
        boolean authenticGuid = applicationService.checkAuthentictyOfGUID(appInstallationGuid);
        if (authenticGuid == false) {
            clientResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            clientResponseVO.setDescription(EmmConstants.EMM_REQUEST_GUID_MISMATCH);
            return clientResponseVO;
        }

        // if the GUID is an Authentic One.
        if (sessionTokenVO != null && sessionTokenVO.getTokenid() != null) {
            ClientSessionVO clientSessionVO = new ClientSessionVO();
            clientSessionVO.setGuid(appInstallationGuid);
            clientSessionVO.setToken(sessionTokenVO.getTokenid());

            EMMConstants.updateTOKENTABLE(sessionTokenVO.getSessionid(), clientSessionVO);
            logger.debug("Placing sessionid = {} and token={}", sessionTokenVO.getSessionid(), sessionTokenVO.getTokenid());
            logger.debug("CLIENT SESSION VO = {}", clientSessionVO);

            clientResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
        } else {
            clientResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        // Setting the Response for the Client.
        clientResponseVO.setResponse(sessionTokenVO);



        return clientResponseVO;


    }
}
