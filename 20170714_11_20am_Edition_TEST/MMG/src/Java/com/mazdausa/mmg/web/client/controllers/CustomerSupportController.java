/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.web.client.request.vo.CustomerSupportRequestVO;
import com.mazdausa.mmg.web.client.response.vo.CustomerSupportResponseVO;
import com.mazdausa.mmg.web.client.service.iface.CustomerSupportServiceIface;

/**
 * This is the Controller, which is responsible for handling all the customer support requests.
 * 
 * @author Anilk
 * @version 1.0
 */

@Controller
@Path("/customercare")
public class CustomerSupportController {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomerSupportController.class);

	@Autowired
	CustomerSupportServiceIface customercareService;
	
	/**
	 * This function is responsible for fetching Customer JCI link url. 
	 *  
	 ** @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/jciurl")
	public CustomerSupportResponseVO getJciUrl() {

		logger.debug(">>> Entering getJciUrl");
		CustomerSupportResponseVO tempVo=customercareService.getJciLink();
		logger.debug("<<< Exiting getJciUrl");
		
		return tempVo;
	}
		
	
	/**
	 * This function is responsible for fetching Customer support info like email & phone. 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/appver/{agentType}")
	public CustomerSupportResponseVO getForceUpgradeVer(@PathParam("agentType") String agentType) {

		logger.debug(">>> Entering getForceUpgradeVer | Device = {}",agentType);
		CustomerSupportResponseVO tempVo=customercareService.getAppVersion(agentType);
		logger.debug("<<< Exiting getForceUpgradeVer() with Status = {}",tempVo.getStatus());
		
		return tempVo;
	}
	

	/**
	 * This method is used to send email to Customer Support & save the same details to db.
	 * @param customerSupportReq
	 * @param request
	 * @return
	 */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/emailcoupons")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public CustomerSupportResponseVO sendEmailCoupons(CustomerSupportRequestVO customerSupportReq, @Context HttpServletRequest request)
    {
        logger.debug(" >>> Entering user email {} sendEmailCoupons() with  email to {}", customerSupportReq.getUseremail(),customerSupportReq.getEmailto());
    	CustomerSupportResponseVO customerSupportResp=customercareService.emailCoupons(customerSupportReq);
        logger.debug(" <<< Exiting  sendEmailCoupons() with Status = {}", customerSupportResp.getStatus());

        return customerSupportResp;
    }

	
	
	/**
	 * This method is used to send email to Customer Support & save the same details to db.
	 * @param customerSupportReq
	 * @param request
	 * @return
	 */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sendemail")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public CustomerSupportResponseVO sendEmail(CustomerSupportRequestVO customerSupportReq, @Context HttpServletRequest request)
    {
        logger.debug(" >>> Entering user email {} sendEmail() with  email to {}", customerSupportReq.getUseremail(),customerSupportReq.getEmailto());
    	CustomerSupportResponseVO customerSupportResp=customercareService.sendMail(customerSupportReq);
        logger.debug(" <<< Exiting  sendEmail() with Status = {}", customerSupportResp.getStatus());

        return customerSupportResp;
    }

	
	/**
	 * This function is responsible for fetching Customer support info like email & phone. 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/info")
	public CustomerSupportResponseVO getcustomercareinfo() {

		logger.debug(">>> Entering getcustomercareinfo");
		CustomerSupportResponseVO tempVo=customercareService.getCustomerCareInfo();
		logger.debug("<<< Exiting getcustomercareinfo");
		
		return tempVo;
	}


	/**
	 * This method is used to create a crash report file for crash data send by client apps and save the same into db as well.
	 * @param customerSupportReq
	 * @param request
	 * @return
	 */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/crashreport")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public CustomerSupportResponseVO createCrashReport(@FormParam("crashdata")String crashdata,@FormParam("devicetype")String devicetype,@FormParam("userid")String userid,@FormParam("guid")String guid,@FormParam("devicemodel")String devicemodel)
    {
        logger.debug(" >>> Entering createCrashReport with device type {}", devicetype);
        
        CustomerSupportRequestVO customerSupportReq=new CustomerSupportRequestVO();
        customerSupportReq.setCrashdata(crashdata);
        customerSupportReq.setDevicetype(devicetype);
        customerSupportReq.setDevicemodel(devicemodel);
        customerSupportReq.setUserid(userid);
        customerSupportReq.setGuid(guid);
        
        logger.debug("crashdata : "+crashdata);
        logger.debug("devicetype : "+devicetype);
        logger.debug("userid : "+userid);
        logger.debug("guid : "+guid);
        logger.debug("devicemodel : "+devicemodel);

    	
        CustomerSupportResponseVO customerSupportResp=customercareService.createCrashLog(customerSupportReq);
        logger.debug(" <<< Exiting  createCrashReport with Status = {}", customerSupportResp.getStatus());

        return customerSupportResp;
    }
	
    
    
	/**
	 * This function is responsible for fetching agreement message for sending crash log to mazda. 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/crashagreement")
	public CustomerSupportResponseVO getCrashAgreement() {

		CustomerSupportResponseVO tempVo=customercareService.getCrashAgreementMessage();
		
		return tempVo;
	}


	/**
	 * Fetches all the available mmg service types.(NEWONE)
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/servicetypes")
	public Object getServiceType() {
	
		class MyResp{

		private ArrayList serviceTypes=null;
		
		public MyResp(ArrayList mylist) {
			serviceTypes=mylist;
		}

		public ArrayList getServiceTypes() {
			return serviceTypes;
		}

		public void setServiceTypes(ArrayList serviceTypes) {
			this.serviceTypes = serviceTypes;
		}

		}

		//set data start
		ArrayList temp=customercareService.getServiceTypes();
		//set data end
		
		return new MyResp(temp);
	}	
	
	
	/**
	 * This function is responsible for fetching SSG & corresponding resource file path.. 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ssg/year/{year}/model/{model}/trim/{trim}")
	public CustomerSupportResponseVO getSsgInfo(@PathParam("year") String year,@PathParam("model") String model,@PathParam("trim") String trim) {

		logger.debug(">>> Entering getSsgInfo : year = "+year+" | model = "+model+" | trim = "+trim);
		CustomerSupportResponseVO tempVo=customercareService.getSsgInfo(year, model,trim);
		logger.debug("<<< Exiting getSsgInfo : "+tempVo);
		
		return tempVo;
	}


	/**
	 * This function is responsible for fetching SSG & corresponding resource file path for web browser clients. 
	 *  
	 ** @return
	 */

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ssgweb/year/{year}/model/{model}/trim/{trim}")
	public CustomerSupportResponseVO getSsgInfoForWeb(@PathParam("year") String year,@PathParam("model") String model,@PathParam("trim") String trim,@Context HttpServletRequest request) {

		logger.debug(">>> Entering getSsgInfoForWeb : year = "+year+" | model = "+model+" | trim = "+trim);
		String userAgentOfDevice = request.getHeader("User-Agent"); 
		
		CustomerSupportResponseVO tempVo=customercareService.getSsgInfoForWeb(year, model,trim,userAgentOfDevice);
		logger.debug("<<< Exiting getSsgInfoForWeb : "+tempVo);
		
		return tempVo;
	}

	
	
	/**
	 * Test method....
	 * 
	 * @return
	 */

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test/mq/{qry}")
	public Object testHowTo(@PathParam("qry") String qry) {
	
		class MyResp{

		private ArrayList list=null;
		
		public MyResp(ArrayList mylist) {
			list=mylist;
		}
		public ArrayList getList() {
			return list;
		}

		public void setList(ArrayList list) {
			this.list = list;
		}
		}

		//set data start
		ArrayList temp=customercareService.getTestInfo(qry);
		//set data end
		
		return new MyResp(temp);
	}

}
