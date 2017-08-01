/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import com.mazdausa.mmg.web.client.response.vo.HowToLookUpResponseVO;
import com.mazdausa.mmg.web.client.service.iface.HowtoServiceIFace;

/**
 * This is the Controller, which is responsible for handling all the howto
 * client request.
 * 
 * @author Anilk
 * @version 1.0
 */

@Controller
@Path("/howto")
public class HowtoController {

	private static final Logger logger = LoggerFactory
			.getLogger(HowtoController.class);

	@Autowired
	HowtoServiceIFace howtoService;
	
	/**
	 * This function is responsible for Retrieving the list based on Year model
	 * input
	 * 
	 * @param model
	 * @param year
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/model/{model}/year/{year}")
	public HowToLookUpResponseVO getHowToListing(@PathParam("model") String model,
			@PathParam("year") String year, @Context HttpServletRequest request) {

		logger.debug(" Request model = "+model+" | year = "+year);
		
		HowToLookUpResponseVO tempVo=howtoService.getHowtoItems(model, year);
		
		return tempVo;
	}


	/**
	 * This function is responsible for Retrieving the list based on Year model & trim
	 * input. Response has been upgraded for menu & submenu implementation. (NEWONE2)
	 * 
	 * @param model
	 * @param year
	 * @param trim
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/model/{model}/year/{year}/trim/{trim}/2014q2/x")
	public HowToLookUpResponseVO getHowToListingWithFilterObjects(@PathParam("model") String model,
			@PathParam("year") String year,@PathParam("trim") String trim, @Context HttpServletRequest request) {

		logger.debug(" Request model = "+model+" | year = "+year+" | trim = "+trim);
		
		if(trim!=null && (trim.equals("")||trim.equalsIgnoreCase("all")))
			trim="default";
		
		HowToLookUpResponseVO tempVo=howtoService.getHowtoItemsWithFilterObjects(model, year,trim);
		
		return tempVo;
	}
	

	/**
	 * This function is responsible for Retrieving the list based on Year model & trim
	 * input. Response has been upgraded for menu & submenu implementation. (NEWONE2)
	 * 
	 * @param model
	 * @param year
	 * @param trim
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/model/{model}/year/{year}/trim/{trim}/2014q2")
	public HowToLookUpResponseVO getHowToListingWithFilterObjectsForWeb(@PathParam("model") String model,
			@PathParam("year") String year,@PathParam("trim") String trim, @Context HttpServletRequest request) {

		String userAgentOfDevice = request.getHeader("User-Agent");
		
		logger.debug("User agent = "+userAgentOfDevice+" Request model = "+model+" | year = "+year+" | trim = "+trim);
		
		if(trim!=null && (trim.equals("")||trim.equalsIgnoreCase("all")))
			trim="default";
		
		HowToLookUpResponseVO tempVo=howtoService.getHowtoItemsWithFilterObjects(model, year,trim,userAgentOfDevice);
		
		return tempVo;
	}
	
	
	
	/**
	 * This function is responsible for Retrieving the list based on Year model & trim
	 * input (NEWONE)
	 * 
	 * @param model
	 * @param year
	 * @param trim
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/model/{model}/year/{year}/trim/{trim}")
	public HowToLookUpResponseVO getHowToListing(@PathParam("model") String model,
			@PathParam("year") String year,@PathParam("trim") String trim, @Context HttpServletRequest request) {

		logger.debug(" Request model = "+model+" | year = "+year+" | trim = "+trim);
		
		if(trim!=null && (trim.equals("")||trim.equalsIgnoreCase("all")))
			trim="default";
		
		HowToLookUpResponseVO tempVo=howtoService.getHowtoItems(model, year,trim);
		
		return tempVo;
	}
	
	
	/**
	 * This function is responsible for Retrieving the list based on Year model & trim
	 * input. Response has been upgraded for menu & submenu implementation. (NEWONE2)
	 * 
	 * @param squery
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/model/{model}/year/{year}/squery/{squery}/2014q2")
	public HowToLookUpResponseVO getHowToSearchListingWithFilterObjects(@PathParam("model") String model,@PathParam("year") String year,@PathParam("squery") String squery, @Context HttpServletRequest request) {

		logger.debug(" Request model = "+model+" | year = "+year+" | squery = "+squery);
		
		HowToLookUpResponseVO tempVo=howtoService.getHowToSearchItemsWithFilterObjects(year,model,squery);
		
		return tempVo;
	}	
	
	/**
	 * This function is responsible for Retrieving the list based on Year model & trim
	 * input (NEWONE)
	 * 
	 * @param squery
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/model/{model}/year/{year}/squery/{squery}")
	public HowToLookUpResponseVO getHowToSearchListing(@PathParam("model") String model,@PathParam("year") String year,@PathParam("squery") String squery, @Context HttpServletRequest request) {

		logger.debug(" Request model = "+model+" | year = "+year+" | squery = "+squery);
		
		HowToLookUpResponseVO tempVo=howtoService.getHowToSearchItems(year,model,squery);
		
		return tempVo;
	}
	
	/**
	 * This function is responsible for Retrieving the list based on Year model & trim
	 * input (NEWONE)
	 * 
	 * @param squery
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/squery/{squery}")
	public HowToLookUpResponseVO getHowToSearchListingByYMSQ(@PathParam("squery") String squery, @Context HttpServletRequest request) {

		logger.debug(" Request squery = "+squery);
		
		HowToLookUpResponseVO tempVo=howtoService.getHowToSearchItems(squery);
		
		return tempVo;
	}
	
	
	/**
	 * This function is responsible for fetching how to item details based upon
	 * type and content ID input
	 * 
	 * @param type
	 * @param id
	 * @param request
	 * @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/type/{type}/id/{id}")
	public HowToLookUpResponseVO getHowToItem(@PathParam("type") String type,
			@PathParam("id") String id, @Context HttpServletRequest request) {

		logger.debug(" Request type = "+type+" | id = "+id);
		HowToLookUpResponseVO tempVo=howtoService.getHowtoItemDetail(type, id);
		
		return tempVo;
	}

	
	/**
	 * Test method....
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public ApplicationDetailsVO testHowTo() {

        ApplicationDetailsVO app = new ApplicationDetailsVO();
        app.setAddedBy("pankaj");
        app.setAddedDate(new Date());
        app.setAppDeleted(false);
        app.setAppFeatureList("pankasdfsd");
        app.setAppName("MMG");
        app.setAppPath("ww.yahoo.com");
        app.setClientOsMaxVersion("2.3");
        app.setClientOsMinVersion("1.1");
        //app.setClientOsType("MAC");
        app.setIp("192");
        app.setAppVersion("1.0");
        app.setUpgradedVersion(false);		
		
		return app;
	}

}
