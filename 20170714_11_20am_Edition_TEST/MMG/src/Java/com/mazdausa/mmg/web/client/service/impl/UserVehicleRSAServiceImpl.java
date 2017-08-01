package com.mazdausa.mmg.web.client.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.web.client.request.vo.RSACloseRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleRSARequestVO;
import com.mazdausa.mmg.web.client.response.vo.RSACloseResponseVO;
import com.mazdausa.mmg.web.client.response.vo.RSALinkDetailsVO;
import com.mazdausa.mmg.web.client.response.vo.RSAResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleRSAResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleRSAServiceIFace;

@Service
public class UserVehicleRSAServiceImpl implements UserVehicleRSAServiceIFace{

	private static final Logger logger = LoggerFactory.getLogger(UserVehicleRSAServiceImpl.class);
	
    @Autowired
	private DataSource dataSource;
    @Autowired
    ApplicationConstants appConstants;
    
    /**
     * This function will submit Road Side Assistance request
     * @param UserVehicleRSARequestVO
     * @param request
     * @return
     */
    public UserVehicleRSAResponseVO submitRSARequest(String custId, UserVehicleRSARequestVO userVehicleRSARequest, @Context HttpServletRequest request){
    	
    	logger.debug(">> Entering submitRSARequest() with request= {} custId = {}",  userVehicleRSARequest, custId);
    	UserVehicleRSAResponseVO userVehicleRSAResponseVO = new UserVehicleRSAResponseVO();
    	
    	
    	String responseFromWebService = null;
    	String rsaRequestId = null;
    	HttpURLConnection connection = null;
    	URL url;
    	
		try {
			//Call agero webservice to create RSA request
			String ageroUrl = appConstants.SERVICE_REST_USER_AGERORSAAPI;
			JSONObject rsaRequest = new JSONObject(userVehicleRSARequest);
    		String rsaRequestString = rsaRequest.toString();
            url = new URL(ageroUrl.trim());
    		connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-apiKey", appConstants.SERVICE_REST_USER_AGERORSAAPI_KEY);
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false); 
            
            connection.connect();
			
            OutputStream os = new BufferedOutputStream(connection.getOutputStream());
            os.write(rsaRequestString.getBytes());
            os.flush();
            
            int responseCode = connection.getResponseCode();
            logger.debug("Response code from agero RSA get request status web service =  {}",responseCode);
			
            if (responseCode == 200) {
				
				BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				responseFromWebService = response.toString();
				//responseFromWebService= " {\"rsaRequestStatus\":\"Service Provider Dispatched\",\"link\" : { \"rel\":\"RoadsideConnect URL\",\"href\":\"https://roadsideconnect.com/?id=781-306-1234\"}} ";
				logger.debug("Response from agero RSA get request status web service =  {}",responseFromWebService);
				
				JSONObject responseJson = new JSONObject(responseFromWebService);
				
				if(responseJson.has("rsaRequestId")){
					rsaRequestId = responseJson.getString("rsaRequestId");
					userVehicleRSAResponseVO.setRsaRequestId(rsaRequestId);
					userVehicleRSAResponseVO.setStatus("success");
					userVehicleRSAResponseVO.setDescription("RSA request successful");
				}

				//Save RSA details to mmgrsarequestdetails table
				saveRSADetails(userVehicleRSARequest, custId, rsaRequestId);
			}
            else if(responseCode == 400){
            	InputStream errorstream = connection.getErrorStream();
            	String response = "";
            	String line;
            	BufferedReader br = new BufferedReader(new InputStreamReader(errorstream));
            	while ((line = br.readLine()) != null) {
            	    response += line;
            	}
            	logger.debug("Error thhrown by RSA webservice : {}",response);
            	userVehicleRSAResponseVO.setStatus("failed");
				userVehicleRSAResponseVO.setDescription(response);
			}
            else{
            	userVehicleRSAResponseVO.setStatus("failed");
				userVehicleRSAResponseVO.setDescription("Error in calling RSA web service");
				logger.debug("Error in calling RSA web service");
            }
            
		} catch (Exception e) {
			logger.error("Exception occurred in calling agero submit RSA request web service ",e);
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
    	logger.debug("<< Exiting submitRSARequest() with result =  {} ", userVehicleRSAResponseVO);
    	return userVehicleRSAResponseVO;
    }
    
   
    /**
     * This function will fetch Road Side Assistance status for given RSA request id
     * @param rsaRequestId
     * @param request
     * @return
     */
    public RSAResponseVO getRSARequestStatusForRefNo(@PathParam("rsaRefNo") String rsaRefNo, @Context HttpServletRequest request){
    	
        logger.debug(">> Entering {} getRSARequestStatusForRefNo() with rsaRefNo= {}", this.getClass(), rsaRefNo);
    	/*UserVehicleRSAResponseVO userVehicleRSAResponseVO = new UserVehicleRSAResponseVO();
    	
    	userVehicleRSAResponseVO = getRsaStatusDetails(rsaRefNo);*/
        
        RSAResponseVO rsaResponseVO = new RSAResponseVO();
		List<UserVehicleRSAResponseVO> responseList = new ArrayList<UserVehicleRSAResponseVO>();
    	List<String> rsaRequestIds = new  ArrayList<String>();
    	String requestId = null;
    	
    	rsaRequestIds = getRSARequestIdsForRefNo(rsaRefNo);
    	
    	if (rsaRequestIds.size()>0)
    	{
    		//For every request id call agero to get status and set the response
    		for(int i=0;i<rsaRequestIds.size();i++)
    		{
    			UserVehicleRSAResponseVO userVehicleRSAResponseVO = new UserVehicleRSAResponseVO();
    			requestId = rsaRequestIds.get(i);
    			userVehicleRSAResponseVO = getRsaStatusDetails(requestId);
    			responseList.add(userVehicleRSAResponseVO);
    		}
    		rsaResponseVO.setStatus("success");
    		rsaResponseVO.setRequests(responseList);
    	}
    	else{
    		rsaResponseVO.setStatus("failed");
    		rsaResponseVO.setDescription("There are no RSA requests for the reference number");
    	}
    	
        logger.debug("<< Exiting getRSARequestStatusForRefNo() with result =  {} ", rsaResponseVO);
    	return rsaResponseVO;
    }
    
    
    /**
     * This function will fetch latest Road Side Assistance status details for selected
     * @param vin
     * @param request
     * @return
     */
    public RSAResponseVO getRSARequestStatusForVin(String vin, @Context HttpServletRequest request){
    	logger.debug(">> Entering getRSARequestStatusForVin() with vin =  {} ", vin);
    	
    	RSAResponseVO rsaResponseVO = new RSAResponseVO();
		List<UserVehicleRSAResponseVO> responseList = new ArrayList<UserVehicleRSAResponseVO>();
    	List<String> rsaRequestIds = new  ArrayList<String>();
    	String requestId = null;
    	
    	rsaRequestIds = getRSARequestIdsForVin(vin);
    	
    	if (rsaRequestIds.size()>0)
    	{
    		//For every request id call agero to get status and set the response
    		for(int i=0;i<rsaRequestIds.size();i++)
    		{
    			UserVehicleRSAResponseVO userVehicleRSAResponseVO = new UserVehicleRSAResponseVO();
    			requestId = rsaRequestIds.get(i);
    			userVehicleRSAResponseVO = getRsaStatusDetails(requestId);
    			responseList.add(userVehicleRSAResponseVO);
    		}
    		rsaResponseVO.setStatus("success");
    		rsaResponseVO.setRequests(responseList);
    	}
    	else{
    		rsaResponseVO.setStatus("failed");
    		rsaResponseVO.setDescription("There are no RSA requests for the selected vehicle");
    	}
    	
    	logger.debug("<< Exiting getRSARequestStatusForVin() with result =  {} ", rsaResponseVO);
    	return rsaResponseVO;
    }
    
    /**
	   * This function will update Road Side Assistance request status to closed
	   * @param RSACloseRequestVO
	   * @param request
	   * @return
	  */
    public RSACloseResponseVO closeRSARequests(RSACloseRequestVO closeRequest, @Context HttpServletRequest request){
    	
    	RSACloseResponseVO closeResponseVO = new RSACloseResponseVO();
    	try {
    		JSONObject closeRSArequestsJson = new JSONObject(closeRequest);
    		String requestIds = closeRSArequestsJson.getString("requestIds");
    		requestIds = requestIds.trim();    		
    		List<String> requests = Arrays.asList(requestIds.split(","));
    		
    		closeResponseVO = closeRSARequest(requests);
    		
		} catch (JSONException e) {
			closeResponseVO.setStatus("failed");
			closeResponseVO.setDescription("Failed to update the requests");
			e.printStackTrace();
		}
    	
    	
        	
    	return closeResponseVO;
    }
    
    public UserVehicleRSAResponseVO getRsaStatusDetails(String rsaRequestId){
    	
    	UserVehicleRSAResponseVO userVehicleRSAResponseVO = new UserVehicleRSAResponseVO();
    	String responseFromWebService = null;
    	HttpURLConnection connection = null;
    	URL url;
    	
    	try{
    		String ageroUrl = appConstants.SERVICE_REST_USER_AGERORSAAPI+"/"+rsaRequestId;
            url = new URL(ageroUrl.trim());
    		connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("x-apiKey", appConstants.SERVICE_REST_USER_AGERORSAAPI_KEY);
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            
            int responseCode = connection.getResponseCode();
            logger.debug("Response code from agero RSA get request status web service =  {}",responseCode);
            
            if(responseCode == 200){

				String rsaRequestStatus = null;
				String linkRel = null;
				String linkHref = null;
				String street1 = "";
				String street2 = "";
				String city = "";
				String state = "";
				String zip = "";
				String finalDate = "";
				BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				responseFromWebService = response.toString();
				//responseFromWebService= " {\"rsaRequestStatus\":\"Service Provider Dispatched\",\"link\" : { \"rel\":\"RoadsideConnect URL\",\"href\":\"https://roadsideconnect.com/?id=781-306-1234\"}} ";
				logger.debug("Response from agero RSA get request status web service =  {}",responseFromWebService);
				
				JSONObject responseJson = new JSONObject(responseFromWebService);
				if(responseJson.has("rsaRequestStatus") && responseJson.has("link")){
					rsaRequestStatus = responseJson.getString("rsaRequestStatus");
					userVehicleRSAResponseVO.setRsaRequestStatus(rsaRequestStatus);
					userVehicleRSAResponseVO.setStatus("success");
					userVehicleRSAResponseVO.setRsaRequestId(rsaRequestId);
					userVehicleRSAResponseVO.setDescription("RSA Request id exists");
					if(responseJson.getString("link") != "null"){
						JSONObject rsaLinkObj = new JSONObject(responseJson.getString("link"));
						RSALinkDetailsVO rsaLinkDetails = new RSALinkDetailsVO();
						linkRel = rsaLinkObj.getString("rel");
						linkHref = rsaLinkObj.getString("href");
						rsaLinkDetails.setRel(linkRel);
						rsaLinkDetails.setHref(linkHref);
						userVehicleRSAResponseVO.setLink(rsaLinkDetails);
					}
					else{
						userVehicleRSAResponseVO.setLink(null);
					}
					
					//Get other request details from table
					
					Connection conn=null;
					PreparedStatement stmt=null;
					ResultSet resultSet=null;
					
			    	try{
			    		conn=dataSource.getConnection();
						stmt=conn.prepareStatement("SELECT * FROM mmgrsarequestdetails where rsaRequestId = ? and cls_flg = 'N' ");
						stmt.setString(1, rsaRequestId);
					
						resultSet=stmt.executeQuery();
						
						if(resultSet.next()){
							//Select all details frm table
							String query="SELECT mmg.disablementReason,mmg.streetAddress1,mmg.streetAddress2,mmg.city,mmg.state,mmg.zip,mmg.requestTime,mmg.vin "
									+" FROM mmgrsarequestdetails mmg"
									+" WHERE rsaRequestId = ? ";
							
							stmt=conn.prepareStatement(query);
							stmt.setString(1, rsaRequestId);
							resultSet=stmt.executeQuery();
							
							while(resultSet.next())
							{
								userVehicleRSAResponseVO.setProblem(resultSet.getString(1));
								if(resultSet.getString(2) != null){
									street1 = resultSet.getString(2);
								}
								if(resultSet.getString(3) != null){
									street2 = resultSet.getString(3);
								}
								if(resultSet.getString(4) != null){
									city = resultSet.getString(4);
								}
								if(resultSet.getString(5) != null){
									state = resultSet.getString(5);
								}
								if(resultSet.getString(6) != null){
									zip = resultSet.getString(6);
								}
								userVehicleRSAResponseVO.setLocation(street1+" "+street2+", "+city+", "+state+" "+zip);
								
								String date = resultSet.getString(7);
								SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
								SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
								Date parsingDate = inputDateFormat.parse(date);
								finalDate = outputDateFormat.format(parsingDate);
								
								userVehicleRSAResponseVO.setTime(finalDate);
								userVehicleRSAResponseVO.setVin(resultSet.getString(8));
							}
							
						}
			    	}
			    	catch(Exception e){
						logger.error("Problem in fetching rsa details from table  > "+e);
						e.printStackTrace();
					}
					finally{
					    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
					    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
					    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
					}
			    	
					//Save rsa status to mmgrsarequestdetails table
					updateRSAStatusDetails(rsaRequestId, rsaRequestStatus, linkRel, linkHref);
				}
            }
            else if(responseCode == 404){
				userVehicleRSAResponseVO.setStatus("failed");
				userVehicleRSAResponseVO.setDescription("Request id does not exists");
				logger.debug("Agero request id not found");
			}
			else{
				userVehicleRSAResponseVO.setStatus("failed");
				userVehicleRSAResponseVO.setDescription("Error in calling RSA web service");
				logger.debug("Error in calling RSA web service");
			}
    	}
    	catch (Exception e) {
        	logger.error("Exception occurred in calling agero RSA get request status web service",e);
            e.printStackTrace();
        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    	
    	return userVehicleRSAResponseVO;
    }
    
    public List<String> getRSARequestIdsForVin(String vin){
    	logger.debug(">> Entering  getRSARequestIdsForVin with vin : {} ", vin);

    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		List<String> rsaRequestIds = new ArrayList<String>();
		
    	try{
    		conn=dataSource.getConnection();
    		String query="SELECT rsaRequestId FROM mmgrsarequestdetails where vin= ? and cls_flg = 'N' ORDER BY requestTime DESC LIMIT 5";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, vin);
			resultSet=stmt.executeQuery();
				
			while(resultSet.next())
			{
				rsaRequestIds.add(resultSet.getString(1));
			}
				
    	}
    	catch(Exception e){
			logger.error("Problem in fetching rsa details from table  > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
    	logger.debug(">> Exiting  getRSARequestIdsForVin with rsaRequestId : {} ", rsaRequestIds);
    	return rsaRequestIds;
    }
    
    public List<String> getRSARequestIdsForRefNo(String refNo){
    	logger.debug(">> Entering  getRSARequestIdsForRefNo with vin : {} ", refNo);

    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		List<String> rsaRequestIds = new ArrayList<String>();
		
    	try{
    		conn=dataSource.getConnection();
    		String query="SELECT rsaRequestId FROM mmgrsarequestdetails where callbackNumber= ? and cls_flg = 'N'  ORDER BY requestTime DESC LIMIT 5";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, refNo);
			resultSet=stmt.executeQuery();
				
			while(resultSet.next())
			{
				rsaRequestIds.add(resultSet.getString(1));
			}
				
    	}
    	catch(Exception e){
			logger.error("Problem in fetching rsa details from table  > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
    	logger.debug(">> Exiting  getRSARequestIdsForRefNo with rsaRequestId : {} ", rsaRequestIds);
    	return rsaRequestIds;
    }
    
    public void saveRSADetails(UserVehicleRSARequestVO userVehicleRSARequest, String custId, String rsaRequestId){
    	
    	logger.debug(">> Entering  saveRSADetails with rsaRequestId : {} custId : {} ",rsaRequestId, custId);
    	Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
    		conn=dataSource.getConnection();
    		
    		String query="INSERT INTO mmgrsarequestdetails(firstName, lastName, email, custId, callbackNumber, vin, licensePlate, vehicleModel, vehicleYear, mileage, disablementReason," +
    				" streetAddress1, streetAddress2, city, state, zip, requestTime, rsaRequestId, rsaRequestStatus)" +
    				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?, ?)";
    		stmt=conn.prepareStatement(query);
    		stmt.setString(1,userVehicleRSARequest.getRequestor().getName().getFirstName());
    		stmt.setString(2,userVehicleRSARequest.getRequestor().getName().getLastName());
    		stmt.setString(3, userVehicleRSARequest.getNotificationPreferences().getEmailId());
    		stmt.setString(4, custId);
    		stmt.setString(5,userVehicleRSARequest.getRequestor().getCallbackNumber());
    		stmt.setString(6,userVehicleRSARequest.getVehicle().getVin());
    		stmt.setString(7,userVehicleRSARequest.getVehicle().getLicensePlate());
    		stmt.setString(8,userVehicleRSARequest.getVehicle().getModel());
    		stmt.setInt(9,userVehicleRSARequest.getVehicle().getYear());
    		stmt.setInt(10,userVehicleRSARequest.getVehicle().getMileage());
    		stmt.setString(11,userVehicleRSARequest.getServiceDetails().getDisablementReason());
    		stmt.setString(12,userVehicleRSARequest.getDisablementLocation().getAddress().getStreetAddress1());
    		stmt.setString(13,userVehicleRSARequest.getDisablementLocation().getAddress().getStreetAddress2());
    		stmt.setString(14,userVehicleRSARequest.getDisablementLocation().getAddress().getCity());
    		stmt.setString(15,userVehicleRSARequest.getDisablementLocation().getAddress().getState());
    		stmt.setString(16,userVehicleRSARequest.getDisablementLocation().getAddress().getZip());
    		stmt.setString(17,rsaRequestId);
    		stmt.setString(18,"In Progress");
    		
    		int temp=stmt.executeUpdate();
    		logger.debug("No of affected records in saveRSADetails: "+temp);
    	}
    	catch(Exception e){
			logger.error("Problem -  saveRSADetails > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		}
    	logger.debug("<< Exiting updateRSAStatusDetails() ");
    }
    
    public void updateRSAStatusDetails(String rsaRequestId, String rsaRequestStatus, String linkRel, String linkHref){
    	
    	logger.debug(">> Entering  updateRSAStatusDetails with rsaRequestId : {} rsaRequestStatus :{} ",rsaRequestId,rsaRequestStatus);
    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
    	try{
    		conn=dataSource.getConnection();
			stmt=conn.prepareStatement("SELECT * FROM mmgrsarequestdetails where rsaRequestId = ? ");
			stmt.setString(1, rsaRequestId);
		
			resultSet=stmt.executeQuery();
			
			if(resultSet.next()){
				String query="UPDATE mmgrsarequestdetails mmg SET rsaRequestStatus = "
					+"'"+rsaRequestStatus+"',"
					+"linkRel="
					+"'"+linkRel+"',"
					+"linkHref="
					+"'"+linkHref+"'"
					+" WHERE rsaRequestId = "
					+"'"+rsaRequestId+"'";
			
				stmt=conn.prepareStatement(query);
				stmt.executeUpdate();
			}
    	}
    	catch(Exception e){
			logger.error("Problem -  updateRSAStatusDetails > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
    	logger.debug("<< Exiting updateRSAStatusDetails() ");
    }
    
    public RSACloseResponseVO closeRSARequest(List<String> requests){
    	
    	logger.debug(">> Entering  closeRSARequest with requestIds :{} ",requests);
    	RSACloseResponseVO closeResponseVO = new RSACloseResponseVO();
    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
    	String successRequests = "";
    	String failedRequests = "";
    	for(int i=0; i<requests.size(); i++){
    		try{
    			conn=dataSource.getConnection();
    			stmt=conn.prepareStatement("SELECT * FROM mmgrsarequestdetails where rsaRequestId = ? ");
    			stmt.setString(1, requests.get(i));
		
    			resultSet=stmt.executeQuery();
    			if(resultSet.next()){
    				String query="UPDATE mmgrsarequestdetails mmg SET cls_flg = 'Y' "
    						+" WHERE rsaRequestId = "
    						+"'"+requests.get(i)+"'";
			
    				stmt=conn.prepareStatement(query);
    				stmt.executeUpdate();
    				if(successRequests != ""){
    					successRequests += ","+requests.get(i);
    				}
    				else{
    					successRequests = requests.get(i);
    				}
    			}
    			else{
    				if(failedRequests != ""){
    					failedRequests += ","+requests.get(i);
    				}
    				else{
    					failedRequests = requests.get(i);
    				}
    			}	
    		}
    		catch(Exception e){
    			closeResponseVO.setStatus("failed");
    			closeResponseVO.setDescription("Failed to update requests");
    			logger.error("Problem -  closeRSARequest > "+e);
    			e.printStackTrace();
    		}
    		finally{
    			try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
    			try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
    		}
    	}
    	closeResponseVO.setStatus("success");
		closeResponseVO.setDescription("Requests updated successfully");
		closeResponseVO.setSuccessRequestIds(successRequests);
		closeResponseVO.setFailedRequestIds(failedRequests);
		
    	logger.debug("<< Exiting closeRSARequest() with response = {}",closeResponseVO);
    	return closeResponseVO;
    }
    
}
