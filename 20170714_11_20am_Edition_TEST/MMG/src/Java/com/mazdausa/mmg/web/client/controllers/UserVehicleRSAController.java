package com.mazdausa.mmg.web.client.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mazdausa.mmg.web.client.request.vo.RSACloseRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleRSARequestVO;
import com.mazdausa.mmg.web.client.response.vo.RSACloseResponseVO;
import com.mazdausa.mmg.web.client.response.vo.RSAResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleRSAResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleRSAServiceIFace;


/**
 * This is the Controller which is responsible for performing Road side assist functionalities
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/rsa")
public class UserVehicleRSAController {
	
	 private static final Logger logger = LoggerFactory.getLogger(UserVehicleRSAController.class);
	 
	 @Autowired
	 UserVehicleRSAServiceIFace userVehicleRSAServiceIFace;
	 
	 
	/**
	   * This function will submit Road Side Assistance request
	   * @param UserVehicleRSARequestVO
	   * @param request
	   * @return
	  */
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/{custId}")
	 @Consumes(value = {MediaType.APPLICATION_JSON})
	 public UserVehicleRSAResponseVO submitRSARequest(@PathParam("custId") String custId, UserVehicleRSARequestVO userVehicleRSARequest, @Context HttpServletRequest request) {

	     logger.debug(" Entering {} postRSARequest() with request = {} custId = {}", userVehicleRSARequest, custId);
	     UserVehicleRSAResponseVO userVehicleRSAResponseVO = null;

	     userVehicleRSAResponseVO = userVehicleRSAServiceIFace.submitRSARequest(custId, userVehicleRSARequest, request);
	        
	     logger.debug("<< Exiting postRSARequest() with response ={}" + userVehicleRSAResponseVO);
	      return userVehicleRSAResponseVO;
	 } 

    /**
     * This function will fetch Road Side Assistance status for given RSA reference number
     * @param rsaRequestId
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{rsaRefNo}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public RSAResponseVO getRSARequestStatusForRefNo(@PathParam("rsaRefNo") String rsaRefNo, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getRSARequestStatusForRefNo() with rsaRefNo {} ", rsaRefNo);
        RSAResponseVO rsaResponseVO = null;

        rsaResponseVO = userVehicleRSAServiceIFace.getRSARequestStatusForRefNo(rsaRefNo, request);
        
        logger.debug("<< Exiting getRSARequestStatusForRefNo() with response ={}" + rsaResponseVO);
        return rsaResponseVO;
    } 

    /**
     * This function will fetch latest Road Side Assistance status for given vin
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/vin/{vin}")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public RSAResponseVO getRSARequestStatusForVin(@PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getRSARequestStatusForVin() with vin {} ", vin);
        RSAResponseVO rsaResponseVO = null;

        rsaResponseVO = userVehicleRSAServiceIFace.getRSARequestStatusForVin(vin, request);
        
        logger.debug("<< Exiting getRSARequestStatusForVin() with response ={}" + rsaResponseVO);
        return rsaResponseVO;
    } 
    
    /**
	   * This function will update Road Side Assistance request status to closed
	   * @param RSACloseRequestVO
	   * @param request
	   * @return
	  */
	 @POST
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/close")
	 @Consumes(value = {MediaType.APPLICATION_JSON})
	 public RSACloseResponseVO closeRSARequests(RSACloseRequestVO closeRequest, @Context HttpServletRequest request) {

	     logger.debug(" Entering {} closeRSARequests() with request = {}", closeRequest);
	     RSACloseResponseVO closeResponseVO = null;

	     closeResponseVO = userVehicleRSAServiceIFace.closeRSARequests(closeRequest, request);
	        
	     logger.debug("<< Exiting closeRSARequests() with response ={}" + closeResponseVO);
	      return closeResponseVO;
	 } 
}
