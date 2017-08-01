/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.impl;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestUserTaskIFace;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailsUpdateRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserTaskRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailsUpdateResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserTaskErrorResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserTaskResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
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

/**
 * This is an REST Service layer implementation class that will perform all the User related function on the Mazda Web Service
 * @author PankajB
 * @version 1.0
 */
@Component
public class RestUserTaskImpl implements RestUserTaskIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestUserTaskImpl.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmGUtilities;
    @Autowired
    EMMConstants emmConstants;
    @Autowired
    MMGUtilities mmgUtilities;
    
    /**
     * This Function will perform the user registration at the Mazda END.
     * @param userTaskRequestVO
     * @return
     */
    public RestUserTaskResponseVO registerUser(RestUserTaskRequestVO userTaskRequestVO) {
        logger.debug(">> Entering {} registerUser() with userId={}", this.getClass(), userTaskRequestVO.getFirstName() + " " + userTaskRequestVO.getLastName());

        String finalRegistrationQuery = "";
        RestUserTaskResponseVO userTaskResponseVO;
        String responseFromWebService = "";
        boolean error = false;
        // Create a Map of Parameter and their values.
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_CELLNUMBER, userTaskRequestVO.getCellNumber());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_CITY, userTaskRequestVO.getCity());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_EMAIL, userTaskRequestVO.getEmail());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_EMAILCONFIRM, userTaskRequestVO.getEmailConfirm());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_FIRSTNAME, userTaskRequestVO.getFirstName());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_LASTNAME, userTaskRequestVO.getLastName());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_MILEAGE, userTaskRequestVO.getMileage());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_PASSWORD, userTaskRequestVO.getPassword());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_PASSWORDCONFIRM, userTaskRequestVO.getPasswordConfirm());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_STATE, userTaskRequestVO.getState());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_STREETADDRESS, userTaskRequestVO.getStreetAddress());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_VIN, userTaskRequestVO.getVin());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_ZIP, userTaskRequestVO.getZip());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_MODELYEAR, userTaskRequestVO.getModelYear());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_MODELCODE, userTaskRequestVO.getModelCode());

        finalRegistrationQuery = mmGUtilities.addParametersToQuery(appConstants.SERVICE_REST_USER_REGISTRATION, parameters);
        logger.debug("Final Registration Query is = {}", finalRegistrationQuery);


        //responseFromWebService = (String) restTemplate.getForObject(java.net.URI.create(finalRegistrationQuery), String.class);
        try {
            URL userRegistrationURL = new URL(finalRegistrationQuery);
            URLConnection yc = userRegistrationURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder output = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            in.close();
            yc = null;
            userRegistrationURL = null;
            responseFromWebService = output.toString();
            output = null;

        } catch (MalformedURLException urlException) {
            logger.error("An Malformed URL Exception has occured, while processing, the User Registration", urlException);
        } catch (IOException ioException) {
            logger.error("An IO Exception has occured, while processing, the User Registration", ioException);
        } catch (RuntimeException rEx) {
            logger.error("AN Runtime Exception has occured, while performing a Registration through the web service", rEx);
        }
        logger.debug("Registration Data is ={}", responseFromWebService);
        StringReader responseReader = new StringReader(responseFromWebService.trim());
        if (responseFromWebService.toLowerCase().contains(ApplicationConstants.RESULT_STATUS_OK_TEXT) == false) {
            error = true;
        }
        if (error == true && responseFromWebService.trim().length() != 0) {
            RestUserTaskErrorResponseVO userTaskErrorResponseVO = JAXB.unmarshal(responseReader, RestUserTaskErrorResponseVO.class);
            userTaskResponseVO = new RestUserTaskResponseVO();
            userTaskResponseVO.setStatus(userTaskErrorResponseVO.getStatus());
            userTaskResponseVO.setValue(userTaskErrorResponseVO.getValue());
            logger.debug("Error is coming : value = {}", userTaskErrorResponseVO.getValue());
            userTaskErrorResponseVO = null;
        }
        else
        if (error == true && responseFromWebService.trim().length() == 0) {
            // Here Means the web service returns the NULL, so means it is down.
            userTaskResponseVO = new RestUserTaskResponseVO();
            userTaskResponseVO.setStatus(emmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            userTaskResponseVO = JAXB.unmarshal(responseReader, RestUserTaskResponseVO.class);
        }

        responseReader = null;
        responseFromWebService = null;
        parameters = null;
        logger.debug("Exiting registerUser() with response = {} " + userTaskResponseVO);

        return userTaskResponseVO;
    }

    /**
     * This Function will be responsible for performing the login on behalf of the user.
     * @param userTaskRequestVO
     * @return
     */
    public RestUserTaskResponseVO loginUser(RestUserTaskRequestVO userTaskRequestVO) {
        logger.debug(">> Entering {} loginUser() with userId={}", this.getClass(), userTaskRequestVO.getEmail());

        String finalLoginQuery = "";
        RestUserTaskResponseVO userTaskResponseVO = new RestUserTaskResponseVO();
        String responseFromWebService = "";
        boolean error = false;
        // Create a Map of Parameter and their values.
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_USERNAME, userTaskRequestVO.getEmail());
        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_PASSWORD, userTaskRequestVO.getPassword());


        finalLoginQuery = mmGUtilities.addParametersToQuery(appConstants.SERVICE_REST_USER_LOGIN, parameters);
        logger.debug("Final LOGIN Query is = {}", finalLoginQuery);
        //responseFromWebService = (String) restTemplate.getForObject(finalLoginQuery, String.class, parameters);
        //responseFromWebService = (String)restTemplate.getForObject(finalLoginQuery, String.class, parameters);
        // Since it is not working, we are using the Commons HTTP Client.



        try {
            /*   HttpClient httpClient = new HttpClient();
            GetMethod httpGetMethod = new GetMethod();
            httpGetMethod.setURI(new URI("https://www.mymazda.com/MusaWeb/myMazdaLoginWebService.action?Gtj5mo0s=qQwdm500H&username=stufekci@mazdausa.com&password=mazdausa", true));
            int returnCode = httpClient.executeMethod(httpGetMethod);
            if (returnCode == HttpStatus.SC_OK) {

            responseFromWebService = httpGetMethod.getResponseBodyAsString();
            }
            httpClient = null;
            httpGetMethod = null;

            } catch (HttpException ex) {
            logger.error("An HTTP Exception has been occured, while executing the QUERY.", ex);
            } catch (IOException ioEx) {
            logger.error(" An IO Exception has been occured, while executing the method. ", ioEx);
            }*/
            /*Authenticator.setDefault(
            new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("pankajkh", "accounts@1234".toCharArray());

            }
            });*/

            // Now Third Case, doing by Plain HTTP Connection.
            //URL yahoo = new URL("https://www.mymazda.com/MusaWeb/myMazdaLoginWebService.action?Gtj5mo0s=qQwdm500H&username=stufekci%40mazdausa.com&password=mazdausa");
            URL userLoginURL = new URL(finalLoginQuery);
            URLConnection yc = userLoginURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder output = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            in.close();
            yc = null;
            userLoginURL = null;
            responseFromWebService = output.toString();
            output = null;

        } catch (MalformedURLException urlException) {
            logger.error("An Malformed URL Exception has occured, while processing, the User LOGIN", urlException);
        } catch (IOException ioException) {
            logger.error("An IO Exception has occured, while processing, the User LOGIN", ioException);
        } catch (RuntimeException rEx) {
            logger.error("AN Runtime Exception has occured, while performing a LOGIN through the web service", rEx);
        }

        logger.debug("Login Response is = " + responseFromWebService);

        StringReader responseReader = new StringReader(responseFromWebService.trim());
        if (responseFromWebService.toLowerCase().contains(ApplicationConstants.RESULT_STATUS_OK_TEXT) == false) {
            error = true;
        }
        if (error == true && responseFromWebService.trim().length() != 0) {
            RestUserTaskErrorResponseVO userTaskErrorResponseVO = JAXB.unmarshal(responseReader, RestUserTaskErrorResponseVO.class);
            userTaskResponseVO = new RestUserTaskResponseVO();
            userTaskResponseVO.setStatus(userTaskErrorResponseVO.getStatus());
            userTaskResponseVO.setValue(userTaskErrorResponseVO.getValue());
            logger.debug("Error is coming : value = {}", userTaskErrorResponseVO.getValue());
            userTaskErrorResponseVO = null;
        }
        else
        if (error == true && responseFromWebService.trim().length() == 0) {
            // Here Means the web service returns the NULL, so means it is down.
            userTaskResponseVO = new RestUserTaskResponseVO();
            userTaskResponseVO.setStatus(emmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            if (responseReader != null) {
                userTaskResponseVO = JAXB.unmarshal(responseReader, RestUserTaskResponseVO.class);
            }
        }

        responseReader = null;
        responseFromWebService = null;
        responseReader = null;
        logger.debug("Exiting registerUser() with response = {} " + userTaskResponseVO);

        return userTaskResponseVO;
    }

    /**
     * This Function is responsible for performing Forgot Password function.
     * @param userTaskRequestVO
     * @return
     */
    public RestUserTaskResponseVO invokeForgotPassword(RestUserTaskRequestVO userTaskRequestVO) {
        logger.debug(">> Entering {} invokeForgotPassword() with userId={}", this.getClass(), userTaskRequestVO.getEmail());

        String finalPasswordQuery = "";
        RestUserTaskResponseVO userTaskResponseVO;
        String responseFromWebService = "";
        boolean error = false;
        // Create a Map of Parameter and their values.
        Map<String, String> parameters = new HashMap<String, String>();

        parameters.put(appConstants.SERVICE_REST_USER_PARAMETER_EMAIL, userTaskRequestVO.getEmail());

        finalPasswordQuery = mmGUtilities.addParametersToQuery(appConstants.SERVICE_REST_USER_FORGOTPASSWORD, parameters);
        logger.debug("Final Password Query is = {}", finalPasswordQuery);

        // Since it is not working, we are using the Commons HTTP Client.
        //responseFromWebService = (String) restTemplate.getForObject(finalPasswordQuery, String.class, parameters);

        try {



            URL userForgotPasswordURL = new URL(finalPasswordQuery);
            URLConnection yc = userForgotPasswordURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder output = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            in.close();
            yc = null;
            userForgotPasswordURL = null;
            responseFromWebService = output.toString();
            output = null;

        } catch (MalformedURLException urlException) {
            logger.error("An Malformed URL Exception has occured, while processing, the User LOGIN", urlException);
        } catch (IOException ioException) {
            logger.error("An IO Exception has occured, while processing, the User LOGIN", ioException);
        } catch (RuntimeException rEx) {
            logger.error("AN Runtime Exception has occured, while INVOKING FORGOT Password through the web service", rEx);
        }

        logger.debug("Password Response is = " + responseFromWebService);
        StringReader responseReader = new StringReader(responseFromWebService.trim());
        if (responseFromWebService.toLowerCase().contains(ApplicationConstants.RESULT_STATUS_OK_TEXT) == false) {
            error = true;
        }
        if (error == true && responseFromWebService.trim().length() != 0) {
            RestUserTaskErrorResponseVO userTaskErrorResponseVO = JAXB.unmarshal(responseReader, RestUserTaskErrorResponseVO.class);
            userTaskResponseVO = new RestUserTaskResponseVO();
            userTaskResponseVO.setStatus(userTaskErrorResponseVO.getStatus());
            userTaskResponseVO.setValue(userTaskErrorResponseVO.getValue());
            logger.debug("Error is coming : value = {}", userTaskErrorResponseVO.getValue());
            userTaskErrorResponseVO = null;
        }
        else
        if (error == true && responseFromWebService.trim().length() == 0) {
            // Here Means the web service returns the NULL, so means it is down.
            userTaskResponseVO = new RestUserTaskResponseVO();
            userTaskResponseVO.setStatus(emmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            userTaskResponseVO = JAXB.unmarshal(responseReader, RestUserTaskResponseVO.class);
        }

        responseReader = null;
        responseFromWebService = null;
        logger.debug("Exiting invokeForgotPassword() with response = {} " + userTaskResponseVO);

        return userTaskResponseVO;
    }

    /**
     * The Function is being responsible for performing the Vehicle Maintenance by Vehicle No and Driving Habit.
     * @param vehicleNumber
     * @param drivingHabit
     * @return
     */
    
	public RestUserTaskResponseVO performChangePassword(RestUserTaskRequestVO userTaskRequestVO) {
		
        logger.debug(">> Entering {} getVehicleSchedule() with Parameter={}", this.getClass(),userTaskRequestVO.getCustId());
		String responseFromWebService = "";

        RestUserTaskResponseVO restUserTaskResponseVO = null;
        Map<String, String> parameterList = new HashMap<String, String>();
        
        parameterList.put(appConstants.SERVICE_REST_USER_PARAMETER_USERNAME, userTaskRequestVO.getCustId());
        parameterList.put(appConstants.SERVICE_REST_USER_PARAMETER_CURRENTPASSWORD, userTaskRequestVO.getCurrentPassword());
        parameterList.put(appConstants.SERVICE_REST_USER_PARAMETER_NEWPASSWORD, userTaskRequestVO.getNewPassword());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        
        URL url;
        HttpURLConnection connection = null;
        String urlParameters =
                "username="+ userTaskRequestVO.getCustId() +
                "&current=" + userTaskRequestVO.getCurrentPassword() +
                        "&new=" + userTaskRequestVO.getNewPassword();
        
        
        try {
            //Create connection
        	
        	logger.debug("Calling update password web service");
            url = new URL(appConstants.SERVICE_REST_USER_CHANGEPASSWORD);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            //Get Response
            StringBuilder output = new StringBuilder();
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            //StringBuffer responseFromWebService = new StringBuffer();
            while((line = rd.readLine()) != null) {
            	output.append(line);
            }
            rd.close();
            responseFromWebService = output.toString();
            logger.debug("Response from update password webservice {}",responseFromWebService);
            
            
            if (responseFromWebService.toLowerCase().contains(ApplicationConstants.PASSWORD_CHANGE_OK_TEXT) == true) {
            	logger.debug("Update password web service returned success");
            	restUserTaskResponseVO = new RestUserTaskResponseVO();
            	restUserTaskResponseVO.setStatus("Success");
            	restUserTaskResponseVO.setValue("Password changed successfully");
            }
            else if(responseFromWebService.toLowerCase().contains(ApplicationConstants.PASSWORD_CHANGE_FAIL_TEXT) == true){
            	logger.debug("Update password web service returned failure");
            	restUserTaskResponseVO = new RestUserTaskResponseVO();
            	restUserTaskResponseVO.setStatus("Failure");
            	restUserTaskResponseVO.setValue("Failed to change password");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
        
        logger.debug("<< Exiting performChangePassword() with response = {} " + restUserTaskResponseVO);
        return restUserTaskResponseVO;
	}
	
	/**
     * This Function will return the Customer Details given the input.
     * @param userDetailRequestVO
     * @return
     */
    public RestUserDetailResponseVO getUserDetails(RestUserDetailRequestVO userDetailRequestVO) {
        logger.debug(">> Entering {} getUserDetails() with Customer with email id = {}", this.getClass(), userDetailRequestVO.getUserRequestDetailParameters().getEmail());
        RestUserDetailResponseVO userDetailResponseVO = null;
        
        String responseFromWebService = "";
        String basicAuthEncodedValue=mmgUtilities.getBasicAuthEncodedStringForFusionService(appConstants.SERVICE_FUSION_AUTHORIZATION_USER,appConstants.SERVICE_FUSION_AUTHORIZATION_PASSWORD);
		
		logger.debug("In getUserDetails encoded basic auth for fusion service : {}",basicAuthEncodedValue);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		//Setting header for basic authentication
		headers.set(appConstants.SERVICE_FUSION_AUTHORIZATION_KEY, basicAuthEncodedValue);
				
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RestUserDetailRequestVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(userDetailRequestVO, sw);
			String xmlString = sw.toString();
		
			//String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><getServiceHistory><vin>1YVFP80C045N07580</vin><custId>0024989929</custId></getServiceHistory>";
        
			ResponseEntity<String> response = restTemplate.exchange(appConstants.SERVICE_REST_GET_USER_DETAILS,HttpMethod.POST,new HttpEntity<String>(xmlString,headers),String.class);
		
			responseFromWebService=response.getBody();
			
			//responseFromWebService = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getOrInsertCustomerResponse xmlns:ns2=\"http://webservices.marketing.mazdausa.com\"><getOrInsertCustomerReturn><error></error><salesDealerId/><custID>0025231839</custID><dataInSynchFlag/><existingCustFlag/><addressType/><city> LOVELAND </city><country>US US US</country><doNotCall>N</doNotCall><doNotCallDate/><doNotContact/><doNotContactDate/><doNotEmail>N</doNotEmail><doNotEmailDate/><email>jia.khan@gmail.com</email><emailType/><firstName>JIA</firstName><homePhone/><invalidAddressFlag/><lastName>KHAN</lastName><mobilePhone/><state> OH </state><street1address>255 N MARKET STGDSGSDGFS 3651 NANTUCKET DR HBBJBKJ</street1address><street2address> APT LASTE </street2address><workPhone/><workPhoneExt/><zip>95110 45140-3648 02108</zip></getOrInsertCustomerReturn></ns2:getOrInsertCustomerResponse>";
			
			StringReader responseReader = new StringReader(responseFromWebService.trim());
			userDetailResponseVO = JAXB.unmarshal(responseReader, RestUserDetailResponseVO.class);
		
        	responseReader = null;
		} catch (JAXBException e) {
			logger.error("Error occurred in getUserVehicleHistoryDetails , ", e);
			e.printStackTrace();
		}	
		
        logger.debug("<< Exiting getUserDetails() with response = {} ", userDetailResponseVO);
        return userDetailResponseVO;
    }
    
    
    /**
     * This Function will update the user details in the Mazda System.
     * @param userDetailsUpdateRequestVO
     * @return
     */
    public RestUserDetailsUpdateResponseVO updateUserDetails(RestUserDetailsUpdateRequestVO userDetailsUpdateRequestVO) {
        logger.debug(">> Entering {} updateUserDetails() with Customer id = {} and ", this.getClass(), userDetailsUpdateRequestVO.getUserDetailsUpdateVO().getCustID());
        RestUserDetailsUpdateResponseVO userDetailsUpdateResponseVO = null;
        
        String responseFromWebService = "";
        String basicAuthEncodedValue=mmgUtilities.getBasicAuthEncodedStringForFusionService(appConstants.SERVICE_FUSION_AUTHORIZATION_USER,appConstants.SERVICE_FUSION_AUTHORIZATION_PASSWORD);
		
		logger.debug("In updateUserDetails encoded basic auth for fusion service : {}",basicAuthEncodedValue);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		//Setting header for basic authentication
		headers.set(appConstants.SERVICE_FUSION_AUTHORIZATION_KEY, basicAuthEncodedValue);
				
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(RestUserDetailsUpdateRequestVO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(userDetailsUpdateRequestVO, sw);
			String xmlString = sw.toString();
		
			//String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><getServiceHistory><vin>1YVFP80C045N07580</vin><custId>0024989929</custId></getServiceHistory>";
        
			ResponseEntity<String> response = restTemplate.exchange(appConstants.SERVICE_REST_UPDATE_CUSTOMER_DETAILS,HttpMethod.POST,new HttpEntity<String>(xmlString,headers),String.class);
		
			responseFromWebService=response.getBody();
			
			//responseFromWebService = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ns2:getOrInsertCustomerResponse xmlns:ns2=\"http://webservices.marketing.mazdausa.com\"><getOrInsertCustomerReturn><error></error><salesDealerId/><custID>0025231839</custID><dataInSynchFlag/><existingCustFlag/><addressType/><city> LOVELAND </city><country>US US US</country><doNotCall>N</doNotCall><doNotCallDate/><doNotContact/><doNotContactDate/><doNotEmail>N</doNotEmail><doNotEmailDate/><email>jia.khan@gmail.com</email><emailType/><firstName>JIA</firstName><homePhone/><invalidAddressFlag/><lastName>KHAN</lastName><mobilePhone/><state> OH </state><street1address>255 N MARKET STGDSGSDGFS 3651 NANTUCKET DR HBBJBKJ</street1address><street2address> APT LASTE </street2address><workPhone/><workPhoneExt/><zip>95110 45140-3648 02108</zip></getOrInsertCustomerReturn></ns2:getOrInsertCustomerResponse>";
			
			StringReader responseReader = new StringReader(responseFromWebService.trim());
			userDetailsUpdateResponseVO = JAXB.unmarshal(responseReader, RestUserDetailsUpdateResponseVO.class);
		
        	responseReader = null;
		} catch (JAXBException e) {
			logger.error("Error occurred in updateUserDetails , ", e);
			e.printStackTrace();
		}
		
        
        logger.debug("<< Exiting updateUserDetails() with response = {} ", userDetailsUpdateResponseVO);
        return userDetailsUpdateResponseVO;
    }
    
    
    public static void main(String arfs[]) {
        RestUserTaskResponseVO userTaskResponseVO = JAXB.unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"utf-8\" ?><data status=\"system_error\"></data>"), RestUserTaskResponseVO.class);
        /*try {


        	String finalPasswordQuery = "https://approval.mymazda.com/MusaWeb/myMazdaForgotPasswordWebService.action?Gtj5mo0s=qQwdm500H&email=bob%40tester.com";
            URL userForgotPasswordURL = new URL(finalPasswordQuery);
            URLConnection yc = userForgotPasswordURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder output = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                output.append(inputLine);
            }
            in.close();
            yc = null;
            userForgotPasswordURL = null;
            String responseFromWebService = output.toString();
            //System.out.println(responseFromWebService);
            System.out.println(responseFromWebService);
            output = null;

        } catch (MalformedURLException urlException) {
            logger.error("An Malformed URL Exception has occured, while processing, the User LOGIN", urlException);
        } catch (IOException ioException) {
            logger.error("An IO Exception has occured, while processing, the User LOGIN", ioException);
        } catch (RuntimeException rEx) {
            logger.error("AN Runtime Exception has occured, while INVOKING FORGOT Password through the web service", rEx);
        }*/
    }

	
}
