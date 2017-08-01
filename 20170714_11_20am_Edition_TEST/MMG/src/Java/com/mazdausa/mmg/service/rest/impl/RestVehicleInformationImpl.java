/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.impl;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestVehicleInformationIFace;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleServiceHistoryRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerSearchResultVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleDetailInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleVO;
import com.mazdausa.mmg.service.rest.response.vo.RestVehicleAlertResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This Implementation Class contain an implementation of all the functions that are defined in the RestVehicleInformationIFace interface.
 * @author PankajB
 * @version 1.0
 */
@Component
public class RestVehicleInformationImpl implements RestVehicleInformationIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestVehicleInformationImpl.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmGUtilities;

    

    /**
     * This method is created to get VIN details based on new REST based VIN inquiry service.
     * 
     */
    @Cacheable(cacheName = "DealerVINDetailsCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestUserVehicleInformationVO getVehicleInformation(String vin) {
        logger.debug(">> Entering {} getVehicleInformation() with VIn={}", this.getClass(), vin);
        RestUserVehicleInformationVO userVehicleInformationVO = null;
        
    	try{
       	 
		String url = appConstants.SERVICE_REST_VEHICLE_INFORMATION.substring(0,appConstants.SERVICE_REST_VEHICLE_INFORMATION.lastIndexOf("/")+1)+vin;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 		//add request header
		con.setRequestProperty(appConstants.MMG_REST_SERVICES_HEADER_1V_KEY, appConstants.MMG_REST_SERVICES_HEADER_1V_VALUE);
		con.setRequestProperty(appConstants.MMG_REST_SERVICES_HEADER_TOKEN_KEY, appConstants.MMG_REST_SERVICES_HEADER_TOKEN_VALUE);   

		int responseCode = con.getResponseCode();
		logger.debug("\nSending 'GET' request to URL : " + url);
		logger.debug("Response Code : " + responseCode);
		logger.debug("Content type : " + con.getContentType());
		logger.debug("Response message : " + con.getResponseMessage());
		
		if(responseCode!=200) //Handling Errors messages (TEXT/HTML)
		{
		InputStream in = con.getErrorStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		in.close();
		userVehicleInformationVO=new RestUserVehicleInformationVO();
		userVehicleInformationVO.setError(body);
		}
		else //For valid data (XML)
		{
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);
			in.close();
			
			userVehicleInformationVO=JAXB.unmarshal(new StringReader(body), RestUserVehicleInformationVO.class);
		}
		
    	}catch(Exception e){e.printStackTrace();}

    	
    	logger.debug("<< Exiting getVehicleInformation() with response = {} ", userVehicleInformationVO);
        return userVehicleInformationVO;
    }
    
    
    //This method is created for new rest based Mazda service. May be use in incoming releases. >> Response errors has not handled.
    @Cacheable(cacheName = "DealerVINDetailsCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestUserVehicleInformationVO getVehicleInformation_x(String vin) {
        logger.debug(">> Entering {} getVehicleInformation() with VIn={}", this.getClass(), vin);
        RestUserVehicleInformationVO userVehicleInformationVO = null;
        String responseFromWebService = "";

        
        try {
            //Fetch Rest service with headers start updated@25-06-2013>>>

			HttpHeaders headers = new HttpHeaders();
//			headers.add("iv", "2UCCCLm/8HeVre5rVYFj0w==");
//			headers.add("token", "gaPHEfRKm1gcuypN0iTQpj2t1xNk5oI6CXfTdjjVuvhkHP54ZBhWBK9xiY58m5H2yul1Fo+LMUlO9ablhq4m8w==");   
			headers.add(appConstants.MMG_REST_SERVICES_HEADER_1V_KEY, appConstants.MMG_REST_SERVICES_HEADER_1V_VALUE);
			headers.add(appConstants.MMG_REST_SERVICES_HEADER_TOKEN_KEY, appConstants.MMG_REST_SERVICES_HEADER_TOKEN_VALUE);   
            
			ResponseEntity<String> testRestTemp=restTemplate.exchange(appConstants.SERVICE_REST_VEHICLE_INFORMATION,HttpMethod.GET, new HttpEntity<String>(headers), String.class,vin);
			responseFromWebService=testRestTemp.getBody();
			
			System.out.println("Responce status code = "+testRestTemp.getStatusCode().value());
            //end updated@25-06-2013
            
            logger.debug("Output from web service = {}", responseFromWebService);
            StringReader responseReader = new StringReader(responseFromWebService.trim());
            userVehicleInformationVO = JAXB.unmarshal(responseReader, RestUserVehicleInformationVO.class);
            responseReader = null;
        } catch (Exception ex) {
            logger.error("An Exception occured, while getting Vehicle Information from the Web Services. ", ex);

        }
        logger.debug("<< Exiting getVehicleInformation() with response = {} ", userVehicleInformationVO);
        return userVehicleInformationVO;
    }

    
/*
    
    @Cacheable(cacheName = "DealerVINDetailsCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestUserVehicleInformationVO getVehicleInformation(String vin) {
        logger.debug(">> Entering {} getVehicleInformation() with VIn={}", this.getClass(), vin);
        RestUserVehicleInformationVO userVehicleInformationVO = null;
        String finalVehicleInformationQuery;
        String responseFromWebService = "";

        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(appConstants.SERVICE_REST_VEHICLE_INFORMATION_PARAMETER_ID, ApplicationConstants.XML_VEHICLE_INFORMATION_PARAMETER_ID_DEFAULT_VALUE);
        parameters.put(appConstants.SERVICE_REST_VEHICLE_INFORMATION_PARAMETER_TYPE, ApplicationConstants.XML_VEHICLE_INFORMATION_PARAMETER_TYPE_DEFAULT_VALUE);
        parameters.put(appConstants.SERVICE_REST_VEHICLE_INFORMATION_PARAMETER_VIN, vin.toUpperCase().trim());
        finalVehicleInformationQuery = mmGUtilities.addParametersToQuery(appConstants.SERVICE_REST_VEHICLE_INFORMATION, parameters, ApplicationConstants.DEFAULT_VALUE);
        logger.debug("Vehicle Information QUERY IS =" + finalVehicleInformationQuery);

        try {
            responseFromWebService = (String) restTemplate.getForObject(java.net.URI.create(finalVehicleInformationQuery), String.class);
            logger.debug("Output from web service = {}", responseFromWebService);
            StringReader responseReader = new StringReader(responseFromWebService.trim());
            userVehicleInformationVO = JAXB.unmarshal(responseReader, RestUserVehicleInformationVO.class);
            responseReader = null;
        } catch (Exception ex) {
            logger.error("An Exception occured, while getting Vehicle Information from the Web Services. ", ex);

        }
        logger.debug("<< Exiting getVehicleInformation() with response = {} ", userVehicleInformationVO);
        return userVehicleInformationVO;
    }
    
*/
    
    /**
     * This Function will return the detail information of an Vehicle given the VIN
     * @param vin
     * @return
     */
    @Cacheable(cacheName = "DealerVINDetailsCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestUserVehicleDetailInformationVO getVehicleDetailInformation(String vin) {
        logger.debug(">> Entering {} getVehicleDetailInformation() with VIn={}", this.getClass(), vin);
        RestUserVehicleDetailInformationVO userVehicleDetailInformationVO = null;
        String finalVehicleDetailInformationQuery;
        String responseFromWebService = "";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(appConstants.SERVICE_REST_VEHICLE_INFORMATION_PARAMETER_VIN, vin.toUpperCase().trim());
        finalVehicleDetailInformationQuery = mmGUtilities.addParametersToQuery(appConstants.SERVICE_REST_VEHICLE_DETAIL_INFORMATION, parameters, ApplicationConstants.DEFAULT_VALUE);
        logger.debug("Vehicle Detail Information QUERY IS =" + finalVehicleDetailInformationQuery);

        try {
            responseFromWebService = (String) restTemplate.getForObject(java.net.URI.create(finalVehicleDetailInformationQuery), String.class);
            StringReader responseReader = new StringReader(responseFromWebService.trim());
            logger.debug("Output from web service = {}", responseFromWebService);
            userVehicleDetailInformationVO = JAXB.unmarshal(responseReader, RestUserVehicleDetailInformationVO.class);
            responseReader = null;
            responseFromWebService = null;
        } catch (Exception ex) {
            logger.error("An Exception, has occurd, while getting detail VIN INformation. ", ex);
        }
        logger.debug("<< Exiting getVehicleDetailInformation() with response = {} ", userVehicleDetailInformationVO);
        return userVehicleDetailInformationVO;
    }

    /**
     * This is the function, which will be invoked to determine whether there are any alert for a VIN.
     * @param vin
     * @return
     */
    public RestVehicleAlertResponseVO getVehicleRecallAlertDetail(String vin) {
        logger.debug(">> Entering {} getVehicleRecallAlertDetail() with VIN={}", this.getClass(), vin);
        RestVehicleAlertResponseVO vehicleAlertResponseVO = null;
        String finalVehicleRecallAlertQuery;
        String responseFromWebService = "";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(appConstants.SERVICE_REST_VEHICLE_RECALLALERT_PARAMETER_VIN, vin.toUpperCase().trim());
        finalVehicleRecallAlertQuery = mmGUtilities.addParametersToQuery(appConstants.SERVICE_REST_VEHCILE_RECALLALERT, parameters, ApplicationConstants.DEFAULT_VALUE);
        logger.debug("Vehicle Recall Alert QUERY IS =" + finalVehicleRecallAlertQuery);

        try {
            responseFromWebService = (String) restTemplate.getForObject(java.net.URI.create(finalVehicleRecallAlertQuery), String.class);
            StringReader responseReader = new StringReader(responseFromWebService.trim());
            logger.debug("Output from web service = {}", responseFromWebService);
            vehicleAlertResponseVO = JAXB.unmarshal(responseReader, RestVehicleAlertResponseVO.class);
            responseReader = null;
            responseFromWebService = null;
        } catch (Exception ex) {
            logger.error("An Exception has been occured, while processing the Vehicle Recall Alert Query. ", ex);
        }
        logger.debug("<< Exiting getVehicleRecallAlertDetail() with response = {} ", vehicleAlertResponseVO);
        return vehicleAlertResponseVO;
    }

    
    public static void main(String arfs[])
    {
    try
    {
    //RestVehicleAlertResponseVO response=JAXB.unmarshal(new StringReader("<?xml version=\"1.0\" ?><recallInquiryResult VIN=\"JM1BK12FX71742887\"><recallOrSSP><recallSSPNo>0200L</recallSSPNo><recallSSPDesc>2001 </recallSSPDesc><startDate>02-04-2004</startDate></recallOrSSP></recallInquiryResult>"), RestVehicleAlertResponseVO.class);
    RestUserVehicleInformationVO response=JAXB.unmarshal(new StringReader(sendGetWithHeaders()), RestUserVehicleInformationVO.class);
    System.out.println(response.getVehicleVO().getVin());
    }
    catch(Exception ex)
    {
    ex.printStackTrace();
    }
    } 
    
    private static String sendGetWithHeaders()
    {
    	String respMessage="";
    	try{
    	 
		String url = "http://portaltest.mazdausa.com/m371/VinInquiryRestService/vinInquiry/4F2CZ961X6KM00295";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header

		con.setRequestProperty("iv", "2UCCCLm/8HeVre5rVYFj0w==");
		con.setRequestProperty("token", "gaPHEfRKm1gcuypN0iTQpj2t1xNk5oI6CXfTdjjVuvhkHP54ZBhWBK9xiY58m5H2yul1Fo+LMUlO9ablhq4m8w==");   

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		System.out.println("Content type : " + con.getContentType());
		System.out.println("Response message : " + con.getResponseMessage());
		
		if(responseCode!=200)
		{
		InputStream in = con.getErrorStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String body = IOUtils.toString(in, encoding);
		in.close();
		respMessage=body;
		
		System.out.println("BBBB > "+body);		
		}
		else
		{
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body = IOUtils.toString(in, encoding);
			in.close();
			respMessage=body;
			
			System.out.println("BBBB > "+body);	
		}
		
    	}catch(Exception e){e.printStackTrace();}
    	
    return respMessage;	
	}

    
}//End of class
