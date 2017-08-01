/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.impl;

import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestUserVehicleTaskIFace;
import com.mazdausa.mmg.service.rest.request.vo.RestUserGetVehiclesRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleServiceHistoryRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserGetVehiclesResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserTaskResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.client.SoapFaultClientException;

import org.apache.commons.codec.binary.Base64;

/**
 * This Class is responsible for Performing all the Vehicle maintenance tasks.
 * @author PankajB
 * @version 1.0
 */
@Component
public class RestUserVehicleTaskImpl implements RestUserVehicleTaskIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestUserVehicleTaskImpl.class);
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    RestTemplate restTemplate;

    /**
     * The Function is being responsible for performing the Vehicle Maintenance by Vehicle No and Driving Habit.
     * @param vehicleNumber
     * @param drivingHabit
     * @return
     */
    public RestUserVehicleMaintenanceResponseVO getVehicleSchedule(String vehicleNumber, String drivingHabit) {

        logger.debug(">> Entering {} getVehicleSchedule() with Parameter={}", this.getClass(), vehicleNumber + " : " + drivingHabit);


        RestUserVehicleMaintenanceResponseVO userVehicleMaintenanceResponseVO=null;
        Map<String, String> parameterList = new HashMap<String, String>();
        parameterList.put(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_PARAMETER_VIN, vehicleNumber);
        parameterList.put(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_PARAMETER_DRIVINGHABIT, drivingHabit);

        try {
            userVehicleMaintenanceResponseVO = (RestUserVehicleMaintenanceResponseVO) restTemplate.getForObject(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_VIN_DRIVINGHABIT, RestUserVehicleMaintenanceResponseVO.class, parameterList);
        } catch (RuntimeException rEx) {
            logger.error("An Exception occured, while calling Maintenance Schedule by VIN and Driving Habit. ", rEx);
        }
        logger.debug("<< Exiting getVehicleSchedule() with response = {} " + userVehicleMaintenanceResponseVO);

        return userVehicleMaintenanceResponseVO;
    }

    /**
     * This Function is being responsible for performing the  Vehicle Maintenance by MODEL,YEAR, Driving Habit
     * @param model
     * @param year
     * @param drivingHabit
     * @return
     */
    public RestUserVehicleMaintenanceResponseVO getVehicleSchedule(String model, String year, String drivingHabit) {
        logger.debug(">> Entering {} getVehicleSchedule() with MODEL,YEAR,DRIVING HABIT={}", this.getClass(), model + " " + year + " " + drivingHabit);


        RestUserVehicleMaintenanceResponseVO userVehicleMaintenanceResponseVO=null;

        Map<String, String> parameterList = new HashMap<String, String>();
        parameterList.put(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_PARAMETER_MODEL, model);
        parameterList.put(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_PARAMETER_YEAR, year);
        parameterList.put(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_PARAMETER_DRIVINGHABIT, drivingHabit);


        try {
            userVehicleMaintenanceResponseVO = (RestUserVehicleMaintenanceResponseVO) restTemplate.getForObject(appConstants.SERVICE_REST_USER_VECHILE_MAINTENANCE_SCHEDULE_MODEL_YEAR_DRIVINGHABIT, RestUserVehicleMaintenanceResponseVO.class, parameterList);            
        } catch (RuntimeException rEx) {
            logger.error("An Exception occured, while calling Maintenance Schedule by MODEL, YEAR AND Driving Habit. ", rEx);
        }
        logger.debug("<< Exiting getVehicleSchedule() with response = {} " + userVehicleMaintenanceResponseVO);

        return userVehicleMaintenanceResponseVO;
    }

    /**
     * This Function will return the Vehicle Service History Details.
     * @param userVehicleServiceHistoryRequest
     * @return
     */
    public RestUserVehicleServiceHistoryResponseVO getUserVehicleHistoryDetails(RestUserVehicleServiceHistoryRequestVO userVehicleServiceHistoryRequest) {
        logger.debug(">> Entering {} getUserVehicleHistoryDetails() with Customer id = {} and VIN={}", new Object[]{this.getClass(), userVehicleServiceHistoryRequest.getCustomerId(), userVehicleServiceHistoryRequest.getVin()});
        RestUserVehicleServiceHistoryResponseVO userVehicleServiceHistoryResponseVO = null;
        
		String responseFromWebService = "";
		
		String basicAuthEncodedValue=mmgUtilities.getBasicAuthEncodedStringForFusionService(appConstants.SERVICE_FUSION_AUTHORIZATION_USER,appConstants.SERVICE_FUSION_AUTHORIZATION_PASSWORD);
		
		logger.debug("In getUserVehicleHistoryDetails encoded basic auth for fusion service : {}",basicAuthEncodedValue);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		
		//Setting header for basic authentication
		headers.set(appConstants.SERVICE_FUSION_AUTHORIZATION_KEY, basicAuthEncodedValue);
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RestUserVehicleServiceHistoryRequestVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(userVehicleServiceHistoryRequest, sw);
			String xmlString = sw.toString();
		
			//String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><getServiceHistory><vin>1YVFP80C045N07580</vin><custId>0024989929</custId></getServiceHistory>";
        
			ResponseEntity<String> response = restTemplate.exchange(appConstants.SERVICE_REST_SERVICE_HISTORY_INFO,HttpMethod.POST,new HttpEntity<String>(xmlString,headers),String.class);
		
			responseFromWebService=response.getBody();
			
			//responseFromWebService = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><getServiceHistoryResponse><getServiceHistoryReturn><vehServiceDetails><count></count><serHistoryList><Service><batteryInspFlag>Y</batteryInspFlag><brakesInspFlag>Y</brakesInspFlag><mileage>32785</mileage><serviceDate>04302011</serviceDate><serviceDealerID>23756</serviceDealerID><serviceTypeCode>01</serviceTypeCode><serviceTypeDesc>LUBE OIL</serviceTypeDesc><tiresInspFlag>G</tiresInspFlag></Service><Service><batteryInspFlag>Y</batteryInspFlag><brakesInspFlag>Y</brakesInspFlag><mileage>32785</mileage><serviceDate>04302011</serviceDate><serviceDealerID>23756</serviceDealerID><serviceTypeCode>02</serviceTypeCode><serviceTypeDesc>BRAKE OIL</serviceTypeDesc><tiresInspFlag>G</tiresInspFlag></Service></serHistoryList></vehServiceDetails><error></error></getServiceHistoryReturn></getServiceHistoryResponse>";
			
			StringReader responseReader = new StringReader(responseFromWebService.trim());
			userVehicleServiceHistoryResponseVO = JAXB.unmarshal(responseReader, RestUserVehicleServiceHistoryResponseVO.class);
		
        	responseReader = null;
		} catch (JAXBException e) {
			logger.error("Error occurred in getUserVehicleHistoryDetails , ", e);
			e.printStackTrace();
		}	
        logger.debug("<< Exiting getUserVehicleHistoryDetails() with response = {} ", userVehicleServiceHistoryResponseVO);
        return userVehicleServiceHistoryResponseVO;
    }
    
    /**
     * This is the function that is to be called, when we need to update the Servicing Dealer for a particular VIN Belongs to an Customer
     * @param vehicleServicingDealerRequestVO
     * @return
     */
    public RestUserVehicleSetServicingDealerResponseVO setVehicleServicingDealer(RestUserVehicleSetServicingDealerRequestVO vehicleServicingDealerRequestVO) {
        logger.debug(">> Entering {} setVehicleServicingDealer() with VIN = {}", this.getClass(), vehicleServicingDealerRequestVO.getNewServicingDealerVO().getVin());
        RestUserVehicleSetServicingDealerResponseVO userVehicleSetServicingDealerResponseVO = null;
        
        String responseFromWebService = "";
        String basicAuthEncodedValue=mmgUtilities.getBasicAuthEncodedStringForFusionService(appConstants.SERVICE_FUSION_AUTHORIZATION_USER,appConstants.SERVICE_FUSION_AUTHORIZATION_PASSWORD);
		
		logger.debug("In setVehicleServicingDealer encoded basic auth for fusion service : {}",basicAuthEncodedValue);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		
		//Setting header for basic authentication
		headers.set(appConstants.SERVICE_FUSION_AUTHORIZATION_KEY, basicAuthEncodedValue);
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RestUserVehicleSetServicingDealerRequestVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(vehicleServicingDealerRequestVO, sw);
			String xmlString = sw.toString();
		
			//String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><getServiceHistory><vin>1YVFP80C045N07580</vin><custId>0024989929</custId></getServiceHistory>";
        
			ResponseEntity<String> response = restTemplate.exchange(appConstants.SERVICE_REST_SET_SERVICING_DEALER,HttpMethod.POST,new HttpEntity<String>(xmlString,headers),String.class);
		
			responseFromWebService=response.getBody();
			
			//responseFromWebService = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:updateServDlrResponse xmlns:ns2=\"http://webservices.vehicles.marketing.mazdausa.com\"><updateServDlrReturn></updateServDlrReturn></ns2:updateServDlrResponse>";
			
			StringReader responseReader = new StringReader(responseFromWebService.trim());
			userVehicleSetServicingDealerResponseVO = JAXB.unmarshal(responseReader, RestUserVehicleSetServicingDealerResponseVO.class);
		
        	responseReader = null;
		} catch (JAXBException e) {
			logger.error("An Exception, has occured while invoking Set Vehicle Servicing Dealer. ",e);
			e.printStackTrace();
		}
        logger.debug("<< Exiting getUserVehicleMileage() with Result = {} ", userVehicleSetServicingDealerResponseVO);
        return userVehicleSetServicingDealerResponseVO;
    }
    
    /**
     * This Function invoke the Update Vehicle Ownership Web Service and perform the function.
     * @param vehicleUpdateOwnershipRequestVO
     * @return
     */
    public RestUserVehicleUpdateOwnershipResponseVO updateVehicleOwnership(RestUserVehicleUpdateOwnershipRequestVO vehicleUpdateOwnershipRequestVO) {
        logger.debug(">> Entering {} updateVehicleOwnership() with VIN = {}", this.getClass(), vehicleUpdateOwnershipRequestVO.getUpdateVehicleOwnershipVO().getVin());
        RestUserVehicleUpdateOwnershipResponseVO userVehicleUpdateOwnershipResponseVO = null;
        
        String responseFromWebService = "";
        String basicAuthEncodedValue=mmgUtilities.getBasicAuthEncodedStringForFusionService(appConstants.SERVICE_FUSION_AUTHORIZATION_USER,appConstants.SERVICE_FUSION_AUTHORIZATION_PASSWORD);
		
		logger.debug("In updateVehicleOwnership encoded basic auth for fusion service : {}",basicAuthEncodedValue);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		//Setting header for basic authentication
		headers.set(appConstants.SERVICE_FUSION_AUTHORIZATION_KEY, basicAuthEncodedValue);
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RestUserVehicleUpdateOwnershipRequestVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(vehicleUpdateOwnershipRequestVO, sw);
			String xmlString = sw.toString();
		
			//String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><getServiceHistory><vin>1YVFP80C045N07580</vin><custId>0024989929</custId></getServiceHistory>";
        
			ResponseEntity<String> response = restTemplate.exchange(appConstants.SERVICE_REST_UPDATE_OWNERSHIP,HttpMethod.POST,new HttpEntity<String>(xmlString,headers),String.class);
		
			responseFromWebService=response.getBody();
			
			//responseFromWebService = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><updateOwnershipResponse><updateOwnershipReturn></updateOwnershipReturn>success</updateOwnershipResponse>";
			
			StringReader responseReader = new StringReader(responseFromWebService.trim());
			userVehicleUpdateOwnershipResponseVO = JAXB.unmarshal(responseReader, RestUserVehicleUpdateOwnershipResponseVO.class);
		
        	responseReader = null;
		} catch (JAXBException e) {
			logger.error("An Exception, has occured while invoking update ownership service. ",e);
			e.printStackTrace();
		}
		
        logger.debug("<< Exiting updateVehicleOwnership() with Result = {} ", userVehicleUpdateOwnershipResponseVO);
        return userVehicleUpdateOwnershipResponseVO;
    }
    
    
    /**
     * This is an implementation of function of getUserVehicles
     * @param getVehiclesRequestVO
     * @return
     */
    public RestUserGetVehiclesResponseVO getUserVehicles(RestUserGetVehiclesRequestVO getVehiclesRequestVO) {
        logger.debug(">> Entering {} getUserVehicles() with CustomerID={}", this.getClass(), getVehiclesRequestVO.getCustomerId());
        RestUserGetVehiclesResponseVO getVehicleResponseVO = new RestUserGetVehiclesResponseVO();
        String responseFromWebService = "";
        String basicAuthEncodedValue=mmgUtilities.getBasicAuthEncodedStringForFusionService(appConstants.SERVICE_FUSION_AUTHORIZATION_USER,appConstants.SERVICE_FUSION_AUTHORIZATION_PASSWORD);
		
		logger.debug("In getUserVehicles encoded basic auth for fusion service : {}",basicAuthEncodedValue);
		
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		//Setting header for basic authentication
		headers.set(appConstants.SERVICE_FUSION_AUTHORIZATION_KEY, basicAuthEncodedValue);
				
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RestUserGetVehiclesRequestVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(getVehiclesRequestVO, sw);
			String xmlString = sw.toString();
		        
			ResponseEntity<String> response = restTemplate.exchange(appConstants.SERVICE_REST_GET_USER_VEHICLES,HttpMethod.POST,new HttpEntity<String>(xmlString,headers),String.class);
			
			responseFromWebService=response.getBody();
			
			//responseFromWebService = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><getVehiclesResponse><getVehiclesReturn><error/><vehDetails><count>3</count><vehicleList><Vehicle><carlineCode>RX8</carlineCode><carlineDesc>RX-8</carlineDesc><currentownerflag>Y</currentownerflag><driveCode>2R</driveCode><engine><camShaft>R00</camShaft><cylRot>IR</cylRot><engDesc>RENESIS ROTARY(2R)</engDesc><engType>R</engType><cylQty>2.0</cylQty><dispcc>1300.0</dispcc><hpQty>238.0</hpQty><hprpm>8500.0</hprpm></engine><extColorCode>29Y</extColorCode><extColorDesc>TITANIUM GRAY M</extColorDesc><intColorCode>F6</intColorCode><intColorDesc>BLACK (FABRIC)</intColorDesc><leaseMaturityDate/><mdlCode>RX8     6P</mdlCode><mdlYear>2005</mdlYear><purchaseDate>08202005</purchaseDate><seatingPassengers>4</seatingPassengers><seatingRows>2</seatingRows><serviceDealerID>40337</serviceDealerID><transmission><speeds>6</speeds><transdesc>6-SPEED MANUAL TRANS</transdesc><trntype>M</trntype></transmission><trim>RX8</trim><vin>JM1FE173050155012</vin></Vehicle></vehicleList></vehDetails></getVehiclesReturn></getVehiclesResponse>";
			
			StringReader responseReader = new StringReader(responseFromWebService.trim());
			getVehicleResponseVO = JAXB.unmarshal(responseReader, RestUserGetVehiclesResponseVO.class);
		
			
			
        	responseReader = null;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


        logger.debug("<< Exiting getUserVehicles() with response = {} ", getVehicleResponseVO);
        return getVehicleResponseVO;
    }
    
    /*
    public static void main(String arfs[])
    {
        try
        {
        RestUserVehicleMaintenanceResponseVO vo=JAXB.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><maintenance status=\"ok\"><drivingHabit>H</drivingHabit><maintenanceNonInterval><nonIntervalItem><itemDescription>Drive belts</itemDescription><nonIntervalDesc>Inspect every 160,000 km (100,000 miles)Replace every 240,000 km (150,000 miles)</nonIntervalDesc></nonIntervalItem></maintenanceNonInterval></maintenance>"), RestUserVehicleMaintenanceResponseVO.class);
        System.out.println(vo.getMaintenanceNonInterval().size());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }*/
    

}
