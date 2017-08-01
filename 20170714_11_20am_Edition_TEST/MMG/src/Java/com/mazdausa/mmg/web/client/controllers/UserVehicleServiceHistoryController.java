/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mazdausa.mmg.web.client.request.vo.ServiceUploadRequestVO;
import com.mazdausa.mmg.web.client.response.vo.ServiceUploadResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;

/**
 * This Controller will be responsible for returning all the service history related details
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/servicehistory")
public class UserVehicleServiceHistoryController {

     /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    @Autowired
    UserVehicleServiceIFace vehicleService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/{userid}/vehicle/{vin}")
    public UserVehicleServiceHistoryResponseVO getVehicleServiceHistory(@PathParam("vin") String vin,@PathParam("userid") String customerId, @Context HttpServletRequest request, @Context HttpHeaders headers) {

        logger.debug(" Entering {} getVehicleServiceHistory() withVIN = {}", vin);
        UserVehicleServiceHistoryResponseVO userVehicleServiceHistoryResponseVO = null;

        // Do the Logging part here
        userVehicleServiceHistoryResponseVO = (UserVehicleServiceHistoryResponseVO) vehicleService.getVehicleServiceHistoryDetails(customerId, vin, headers);

        logger.debug("<< Exiting getVehicleServiceHistory() with response ={}" + userVehicleServiceHistoryResponseVO);
        return userVehicleServiceHistoryResponseVO;
    }
    
    
    /**
     * This method returns uploaded service record details based on vin & customer id
     * @param vin
     * @param custid
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getServiceRecord/{custid}/{vin}")
    public ServiceUploadResponseVO getUploadServiceRecordDetails(@PathParam("vin") String vin,@PathParam("custid") String custid, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getUploadServiceRecordDetails() withVIN = {} and cus id = {}", vin,custid);
        ServiceUploadResponseVO resp = null;

        resp = vehicleService.getUploadServiceRecordDetails(vin, custid);
       

        logger.debug("<< Exiting getUploadServiceRecordDetails() with response ={}" + resp);
        return resp;
    }
    
	/**
	 * Returns service record delete request status based on ref id.
	 * @param refId
	 * @param request
	 * @return
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delServiceRecord/refId/{refId}/vin/{vin}")
    public ServiceUploadResponseVO delUploadedServiceRecord(@PathParam("refId") String refId, @PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering {} delUploadedServiceRecord() with REFID = {}", refId);
        ServiceUploadResponseVO resp = null;

        // Do the Logging part here
        resp = vehicleService.delUploadedServiceRecord(refId,vin);

        logger.debug("<< Exiting delUploadedServiceRecord() with response ={}" + resp);
        return resp;
    }
    
    
    /**
     * Upload service record.
     * @param serviceRecordReq
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uploadServiceRecord")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public ServiceUploadResponseVO uploadServiceRecordDetails(ServiceUploadRequestVO serviceRecordReq, @Context HttpServletRequest request)
    {
        logger.debug(" >>> Enter uploadServiceRecordDetails() Req -> {}  ", serviceRecordReq);
        ServiceUploadResponseVO serviceRecordResp=null;
       
        serviceRecordResp = vehicleService.uploadServiceRecordDetails(serviceRecordReq);
        
        logger.debug(" <<< Exiting  uploadServiceRecordDetails() with response = {}", serviceRecordResp);

        return serviceRecordResp;
    }
    
    /**
     * Edit uploaded service record.
     * @param serviceRecordReq
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateServiceRecord")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public ServiceUploadResponseVO updateServiceRecordDetails(ServiceUploadRequestVO serviceRecordReq, @Context HttpServletRequest request)
    {
    	logger.debug(" >>> Enter updateServiceRecordDetails() Req -> {}  ", serviceRecordReq);
    	ServiceUploadResponseVO resp=null;
        
        resp = vehicleService.updateServiceRecordDetails(serviceRecordReq);
        
        logger.debug(" <<< Exiting  updateServiceRecordDetails() with response = {}", resp);

        return resp;
    }
     
    
}
