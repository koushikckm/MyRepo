/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.mobile.vo.MobileCharacteristicsVO;
import com.emm.v1.utilities.EmmUtilities;
import com.emm.v1.request.vo.ClientGuidRequestVO;
import com.emm.v1.response.vo.ClientGuidResponseVO;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.web.client.service.iface.ApplicationServiceIFace;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
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
 * This is the client controller which is being responsible for Performing the
 * GUID Registration for a particular Client.
 * 
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/guid")
public class ClientGuidGeneratorController {

	/**
	 * Initializing the Logger.
	 */
    private static final Logger logger = LoggerFactory.getLogger(ClientGuidGeneratorController.class);
	@Autowired
	ApplicationServiceIFace applicationService;
	@Autowired
	EMMConstants EmmConstants;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ClientGuidResponseVO getGuid(ClientGuidRequestVO requestVO,
			@HeaderParam(ApplicationConstants.USER_AGENT) String userAgent,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response, @Context UriInfo uriInfo) {

		logger.debug(">> Entering GUID Controller.");
		ClientGuidResponseVO clientGuidResponseVO = new ClientGuidResponseVO();

		// For the GUID TRIM the APNS Token coming from the client.
		/*
		 * if(requestVO.getApnstoken()!=null) { String
		 * token=requestVO.getApnstoken(); try { token=token.replaceAll(" ",
		 * ""); token=token.substring(1, token.length()-1);
		 * requestVO.setApnstoken(token); } catch(Exception ex) { logger.error(
		 * "An Excpetion occured, while parsing the GUID from the client. ",ex);
		 * } }
		 */

		MobileCharacteristicsVO mobileCharactersticsVO = EmmUtilities
				.getMobilePhoneCharacteristics(userAgent,
						request.getRemoteAddr());
		clientGuidResponseVO.setGuid(applicationService.getGuid(requestVO,
				mobileCharactersticsVO));

		// Checkign whether the Correct GUID has been generated or NOT.
		if (clientGuidResponseVO.getGuid() != null
				&& clientGuidResponseVO.getGuid().trim().length() != 0) {
			clientGuidResponseVO
					.setDescription(EmmConstants.EMM_REGISTRATION_SUCCESS);
			clientGuidResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
		} else {
			clientGuidResponseVO
					.setDescription(EmmConstants.EMM_REGISTRATION_FAILURE);
			clientGuidResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
		}

		// Do The Logging Part Here.

		return clientGuidResponseVO;

	}

}
