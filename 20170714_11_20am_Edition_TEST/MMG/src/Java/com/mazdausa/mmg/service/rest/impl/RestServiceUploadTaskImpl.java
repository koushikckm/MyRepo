/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.impl;

import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestServiceUploadTaskIFace;
import com.mazdausa.mmg.service.rest.iface.RestUserVehicleTaskIFace;
import com.mazdausa.mmg.service.rest.response.vo.RestServiceHistoryInfoVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserTaskResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import com.mazdausa.mmg.web.client.request.vo.ServiceUploadRequestVO;

import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This Class is responsible for Performing all the Vehicle maintenance tasks.
 * @author PankajB
 * @version 1.0
 */
@Component
public class RestServiceUploadTaskImpl implements RestServiceUploadTaskIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestServiceUploadTaskImpl.class);
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    RestTemplate restTemplate;
    
    

    /**
     * This function parses the Mazda's corresponding web service. 
     */
	public RestServiceHistoryInfoVO getServiceInfo(String vin, String custId) {
	      logger.debug(">> Entering {} getServiceInfo(..) with VIN , CUSTID={}", this.getClass(), vin + " " + custId);
	      RestTemplate restTemplate = new RestTemplate();
	      RestServiceHistoryInfoVO serviceHistoryInfoVO=null;

	      //Create URL
	        StringBuffer url=new StringBuffer(appConstants.SERVICE_REST_SERVICE_RECORD_INFO);
	        url.append("&vin=").append(vin).append("&custId=").append(custId);
	        logger.debug("Url = {}"+url);
	        
	        try {
	        	serviceHistoryInfoVO = restTemplate.getForObject(url.toString(), RestServiceHistoryInfoVO.class);            
	        } catch (RuntimeException rEx) {
	            logger.error("An Exception occured, while getting service record info by vin & custid. ", rEx);
	        }
	        logger.debug("<< Exiting getServiceInfo() with response = {} " + serviceHistoryInfoVO);

	     return serviceHistoryInfoVO;
	}


	public RestServiceHistoryInfoVO addServiceRecord_x(ServiceUploadRequestVO vo) {


	      logger.debug(">> Entering {} addServiceRecord(..) ={}", this.getClass(), vo);
	      RestTemplate restTemplate = new RestTemplate();
	      RestServiceHistoryInfoVO serviceHistoryInfoVO=null;
	      //Adding parameters>>
	      Map<String, String> parameterList = new HashMap<String, String>();
	      parameterList.put("vin", vo.getVin());
	      parameterList.put("custId", vo.getCustId());
	      parameterList.put("month", vo.getMonth());
	      parameterList.put("year", vo.getYear());
	      parameterList.put("day", vo.getDay());
	      parameterList.put("mileage", vo.getMileage());
	      parameterList.put("amount", vo.getAmount());
	      parameterList.put("serviceLocation", vo.getServiceLocation());
	      parameterList.put("opCode", vo.getOpCode());
	      parameterList.put("comments", vo.getComments());

	        try {
	        	String test=restTemplate.postForObject(appConstants.SERVICE_REST_SERVICE_RECORD_INFO_ADD, vo, String.class, parameterList);
	        	System.out.println("Test = "+test);
	        	
	        	serviceHistoryInfoVO = restTemplate.postForObject(appConstants.SERVICE_REST_SERVICE_RECORD_INFO_ADD, null, RestServiceHistoryInfoVO.class,parameterList);          
	        } catch (RuntimeException rEx) {
	            logger.error("An Exception occured, while calling deleteServiceRecord . ", rEx);
	        }
	        
	        logger.debug("<< Exiting addServiceRecord() with response = {} " + serviceHistoryInfoVO);

	     return serviceHistoryInfoVO;			
		
	}



	public RestServiceHistoryInfoVO addServiceRecord(ServiceUploadRequestVO vo) {

	      logger.debug(">> Entering {} addServiceRecord(..) ={}", this.getClass(), vo);
	      RestTemplate restTemplate = new RestTemplate();
	      RestServiceHistoryInfoVO serviceHistoryInfoVO=null;
	      StringBuffer url=new StringBuffer(appConstants.SERVICE_REST_SERVICE_RECORD_INFO_ADD);
	      try {
	      //Adding parameters>>
	      url.append("&vin=").append(vo.getVin());
	      url.append("&month=").append(vo.getMonth());
	      url.append("&year=").append(vo.getYear());
	      url.append("&day=").append(vo.getDay());
	      url.append("&mileage=").append(vo.getMileage());
	      url.append("&amount=").append(vo.getAmount());
	      url.append("&serviceLocation=").append(vo.getServiceLocation());
	      url.append("&serviceType=").append(vo.getServiceType());
	      url.append("&opCode=").append(vo.getOpCode());
	//    url.append("&comments=").append(vo.getComments().replaceAll("\\n", "\\n"));
	      url.append("&comments=").append(URLEncoder.encode(vo.getComments(), "UTF-8"));
	      url.append("&custId=").append(vo.getCustId());
	        
	        
	        	
	        	serviceHistoryInfoVO = restTemplate.postForObject(url.toString(), null, RestServiceHistoryInfoVO.class);

	        } catch (Exception rEx) {
	            logger.error("An Exception occured, while calling addServiceRecord . ", rEx);
	        }
	        
	        logger.debug("<< Exiting addServiceRecord() with response = {} " + serviceHistoryInfoVO);

	     return serviceHistoryInfoVO;			
		
	}


	@Override
	public RestServiceHistoryInfoVO updateServiceRecord(
			ServiceUploadRequestVO vo) {

	      logger.debug(">> Entering {} updateServiceRecord(..) ={}", this.getClass(), vo);
	      RestTemplate restTemplate = new RestTemplate();
	      RestServiceHistoryInfoVO serviceHistoryInfoVO=null;
	      StringBuffer url=new StringBuffer(appConstants.SERVICE_REST_SERVICE_RECORD_INFO_UPDATE);
	      
	      try {
	      //Adding parameters>>
	      url.append("&vin=").append(vo.getVin());
	      url.append("&month=").append(vo.getMonth());
	      url.append("&year=").append(vo.getYear());
	      url.append("&day=").append(vo.getDay());
	      url.append("&mileage=").append(vo.getMileage());
	      url.append("&amount=").append(vo.getAmount());
	      url.append("&serviceLocation=").append(vo.getServiceLocation());
	      url.append("&serviceType=").append(vo.getServiceType());
	      url.append("&opCode=").append(vo.getOpCode());
	      //url.append("&comments=").append(vo.getComments().replaceAll("\\n", "\\\\n"));
	      url.append("&comments=").append(URLEncoder.encode(vo.getComments(), "UTF-8"));
	      url.append("&custId=").append(vo.getCustId());
	      url.append("&musaRefId=").append(vo.getMusaRefId());
	        
	      
	        	
	        	serviceHistoryInfoVO = restTemplate.postForObject(url.toString(), null, RestServiceHistoryInfoVO.class);

	        } catch (Exception rEx) {
	            logger.error("An Exception occured, while calling updateServiceRecord . ", rEx);
	        }
	        
	        logger.debug("<< Exiting updateServiceRecord() with response = {} " + serviceHistoryInfoVO);

	     return serviceHistoryInfoVO;			
		
	
	}

	public RestServiceHistoryInfoVO updateServiceRecord_X(
			ServiceUploadRequestVO vo) {

	      logger.debug(">> Entering {} updateServiceRecord(..) ={}", this.getClass(), vo);
	      RestTemplate restTemplate = new RestTemplate();
	      RestServiceHistoryInfoVO serviceHistoryInfoVO=null;
	      //Adding parameters>>
	      Map<String, String> parameterList = new HashMap<String, String>();
	      parameterList.put("vin", vo.getVin());
	      parameterList.put("custId", vo.getCustId());
	      parameterList.put("month", vo.getMonth());
	      parameterList.put("year", vo.getYear());
	      parameterList.put("day", vo.getDay());
	      parameterList.put("mileage", vo.getMileage());
	      parameterList.put("amount", vo.getAmount());
	      parameterList.put("serviceLocation", vo.getServiceLocation());
	      parameterList.put("opCode", vo.getOpCode());
	      parameterList.put("comments", vo.getComments());

	        try {
	        	serviceHistoryInfoVO = restTemplate.postForObject(appConstants.SERVICE_REST_SERVICE_RECORD_INFO_UPDATE, null, RestServiceHistoryInfoVO.class,parameterList);          
	        } catch (RuntimeException rEx) {
	            logger.error("An Exception occured, while calling updateServiceRecord . ", rEx);
	        }
	        
	        logger.debug("<< Exiting updateServiceRecord() with response = {} " + serviceHistoryInfoVO);

	     return serviceHistoryInfoVO;			
		
	
	}
	

	@Override
	public RestServiceHistoryInfoVO deleteServiceRecord(
			ServiceUploadRequestVO vo) {

	      logger.debug(">> Entering {} deleteServiceRecord(..) ={}", this.getClass(), vo);
	      RestTemplate restTemplate = new RestTemplate();
	      RestServiceHistoryInfoVO serviceHistoryInfoVO=null;
	      StringBuffer url=new StringBuffer(appConstants.SERVICE_REST_SERVICE_RECORD_INFO_DELETE);
          url.append("&musaRefId=").append(vo.getMusaRefId());
          url.append("&vin=").append(vo.getVin());
	      logger.debug("Url = {}"+url);
	        
	        try {
	        	serviceHistoryInfoVO = restTemplate.postForObject(url.toString(), null, RestServiceHistoryInfoVO.class);          
	        } catch (RuntimeException rEx) {
	            logger.error("An Exception occured, while calling deleteServiceRecord . ", rEx);
	        }
	        
	        logger.debug("<< Exiting deleteServiceRecord() with response = {} " + serviceHistoryInfoVO);

	     return serviceHistoryInfoVO;			
	}

    

}
