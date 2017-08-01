/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;


import java.util.ArrayList;

import javax.servlet.ServletRequest;
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

import com.mazdausa.mmg.web.client.request.vo.PushNotificationVO;
import com.mazdausa.mmg.web.client.request.vo.RelcallAlertsJobStatusVO;
import com.mazdausa.mmg.web.client.service.iface.PushNotificationServiceIface;

/**
 * This is the Controller, which is responsible for handling push notification requests
 *  
 * @author Anilk
 * @version 1.0
 */

@Controller
@Path("/pushnotification")
public class PushNotificationController {

	private static final Logger logger = LoggerFactory
			.getLogger(PushNotificationController.class);

	@Autowired
	PushNotificationServiceIface pushNotificationService;
	

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/year/{year}/model/{model}/notify")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public PushNotificationVO notifyByVehicleYearModel(PushNotificationVO pushNotificationvo,@PathParam("year") String year,@PathParam("model") String model, @Context HttpServletRequest request)
    {
        logger.debug(" >>> Entering push notification for year {} and model {}", year,model);
        logger.debug(" >>> Entering push notification title {} notify() from {}", pushNotificationvo.getPushtitle(),pushNotificationvo.getDevicetype());
        PushNotificationVO tempVo=null;
        
        tempVo=pushNotificationService.sendNotification(pushNotificationvo,year,model);
        
        logger.debug(" <<< Exiting  notify() with Status = {}", tempVo.getStatus());

        return tempVo;
    }

	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/notify")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public PushNotificationVO notify(PushNotificationVO pushNotificationvo, @Context HttpServletRequest request)
    {
        logger.debug(" >>> Entering push notification title {} notify() from {}", pushNotificationvo.getPushtitle(),pushNotificationvo.getDevicetype());
        PushNotificationVO tempVo=null;
        
        tempVo=pushNotificationService.sendNotification(pushNotificationvo);
        
        logger.debug(" <<< Exiting  notify() with Status = {}", tempVo.getStatus());

        return tempVo;
    }
    
	
	/**
	 * This function is responsible to register devices for push notification... 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register/devicetype/{devicetype}/devicetoken/{devicetoken}")
	public PushNotificationVO registerDevice(@PathParam("devicetype") String devicetype,@PathParam("devicetoken") String devicetoken) {

		logger.debug(">>> Entering registerDevice : devicetype = "+devicetype+" | devicetoken = "+devicetoken);
		
		PushNotificationVO tempVo=null;
		
		tempVo=pushNotificationService.registerDevice(devicetype, devicetoken);
		tempVo.setStatus("success");
		
		logger.debug("<<< Exiting registerDevice");
		
		return tempVo;
	}


	/**
	 * This function is responsible to register devices for push notification... 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/vehiclevin/year/{year}/model/{model}/vin/{vin}")
	public PushNotificationVO mapVehicleVin(@PathParam("year") String year,@PathParam("model") String model,@PathParam("vin") String vin) {

		logger.debug(">>> Entering mapVehicleVin : year = "+year+" | model = "+model+" | VIN = "+vin);
		
		PushNotificationVO tempVo=new PushNotificationVO();
		boolean status =pushNotificationService.mapVehicleVin(year, model, vin);
		if(status==true)
		tempVo.setStatus("success");
		
		logger.debug("<<< Exiting mapVehicleVin with status {}",tempVo.getStatus());
		
		return tempVo;
	}	
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/recallPushnotificationData")
	public RelcallAlertsJobStatusVO  recallPushnotificaionData(@Context HttpServletRequest request) {

		logger.debug(">>> Entering recallPushnotificaionData at time {}",System.currentTimeMillis());
		
		RelcallAlertsJobStatusVO tempVo = new RelcallAlertsJobStatusVO();
		boolean closedRecallsUpdateStatus = pushNotificationService.getClosedStatusRecallsAndUpdate();
		if (closedRecallsUpdateStatus == true){
			
			boolean recallPushNotStatus = pushNotificationService.recallPushnotificaionData();
			if(recallPushNotStatus == true){
				tempVo.setStatus("success");
				tempVo.setDescription("Recall push notification job successful");
			}else{
				tempVo.setStatus("failure");
				tempVo.setDescription("Recall push notification job failed");
			}
		}else{
			tempVo.setStatus("failure");
			tempVo.setDescription("Recall push notification job failed");
		}
		
		logger.debug("<<< Exiting recallPushnotificaionData with response : {} at time : {}",tempVo,System.currentTimeMillis());
		return tempVo;

	}
	
}
