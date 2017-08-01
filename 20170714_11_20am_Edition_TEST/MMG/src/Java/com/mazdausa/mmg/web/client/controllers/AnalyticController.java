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

import com.mazdausa.mmg.web.client.service.iface.AnalyticServiceIface;


/**
 * This is the Controller, which is responsible for handling client requests for anlytics data tracking.
 * 
 * @author Anilk
 * @version 1.0
 */

@Controller
@Path("/analytic")
public class AnalyticController {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalyticController.class);

	@Autowired
	AnalyticServiceIface analyticService;
	
	
	/**
	 * This function is responsible for tracking of MMG client apps(iphone & android) usage 
	 * input
	 * 
	 * @param model
	 * @param year
	 * @param request
	 * @return success/failure
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/resourcecode/{resourcecode}/devicetype/{devicetype}/userid/{userid}/track")
	public Object trackAnalytic(@PathParam("resourcecode") String resourcecode,
			@PathParam("devicetype") String devicetype,@PathParam("userid") String userid, @Context HttpServletRequest request) {
		
		class MyResp{

		private String status=null;

		public MyResp(String statusx) {
			status=statusx;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		}
		
		logger.debug(" Request ::> resourcecode = "+resourcecode+" | devicetype = "+devicetype+" | userid = "+userid);
		String status=analyticService.saveAnalytic(resourcecode, devicetype, userid);
		
		return new MyResp(status);
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/resourcecode/{resourcecode}/devicetype/{devicetype}/userid/{userid}/contenttype/{contenttype}/videotype/{videotype}/categoryid/{categoryid}/year/{year}/model/{model}/title/{title}/track")
	public Object trackContentAnalytic(@PathParam("resourcecode") String resourcecode,
			@PathParam("devicetype") String devicetype,@PathParam("userid") String userid,
			@PathParam("contenttype") String contenttype,@PathParam("videotype") String videotype,
			@PathParam("categoryid") String categoryid,@PathParam("year") String year,
			@PathParam("model") String model,@PathParam("title") String title,
			@Context HttpServletRequest request) {
		
		class MyResp{

		private String status=null;

		public MyResp(String statusx) {
			status=statusx;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		}
		
		logger.debug(" Request ::> resourcecode = "+resourcecode+" | devicetype = "+devicetype+" | userid = "+userid+" | contenttype = "+contenttype+" | videotype = "+videotype+" | categoryid = "+categoryid+" | year = "+year+" | model = "+model+" title = "+title);
		String status=analyticService.saveAnalytic(resourcecode, devicetype, userid,contenttype,videotype,categoryid,year,model,title);
		
		return new MyResp(status);
	}


}
