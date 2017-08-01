/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.service.impl;

import com.emm.v1.constants.EMMConstants;
import com.googlecode.ehcache.annotations.Cacheable;
import com.mazdausa.mmg.business.iface.ApplicationBusIFace;
import com.mazdausa.mmg.business.iface.UserBusIFace;
import com.mazdausa.mmg.business.iface.VehicleBusIFace;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.db.constants.DatabaseConstants;
import com.mazdausa.mmg.db.vo.ConstantVO;
import com.mazdausa.mmg.db.vo.UserVehicleVO;
import com.mazdausa.mmg.db.vo.VehicleCodeVO;
import com.mazdausa.mmg.service.rest.iface.RestServiceUploadTaskIFace;
import com.mazdausa.mmg.service.rest.iface.RestUserVehicleTaskIFace;
import com.mazdausa.mmg.service.rest.iface.RestVehicleInformationIFace;
import com.mazdausa.mmg.service.rest.impl.RestUserVehicleTaskImpl;
import com.mazdausa.mmg.service.rest.request.vo.RestUserGetVehiclesRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleServiceHistoryRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.UpdateVehicleOwnershipVO;
import com.mazdausa.mmg.service.rest.request.vo.VehicleUpdateServicingDealerVO;
import com.mazdausa.mmg.service.rest.response.vo.GetVehiclesDetailsVehicleVO;
import com.mazdausa.mmg.service.rest.response.vo.RestServiceHistoryInfoVO;
import com.mazdausa.mmg.service.rest.response.vo.RestServiceRecordVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserGetVehiclesResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleDetailInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestVehicleAlertResponseVO;
import com.mazdausa.mmg.service.soap.iface.UserWebServiceIFace;
import com.mazdausa.mmg.service.soap.iface.VehicleWebServiceIface;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserGetEgiftsRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserVehicleMileageRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserVehicleMileageUpdateRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPVehicleCouponsRemindersRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.VehicleCouponsRemindersVO;
import com.mazdausa.mmg.service.soap.request.vo.VehicleMileageUpdateVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailFullCircleDataVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailLineTextVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponsRemindersDataVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserGetEgiftsResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserVehicleMileageResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserVehicleMileageUpdateResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPVehicleCouponsRemindersResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import com.mazdausa.mmg.web.client.request.vo.ServiceUploadRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserConstantDetailsRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserUploadImageRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleCouponReminderRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleMileageUpdateRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserWindowStickerRequestVO;
import com.mazdausa.mmg.web.client.response.vo.ConstantDetailsResponseVO;
import com.mazdausa.mmg.web.client.response.vo.ConstantResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserEgiftsDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.ServiceRecordDateVO;
import com.mazdausa.mmg.web.client.response.vo.ServiceRecordResponseVO;
import com.mazdausa.mmg.web.client.response.vo.ServiceUploadResponseVO;
import com.mazdausa.mmg.web.client.response.vo.TrimVO;
import com.mazdausa.mmg.web.client.response.vo.UserUploadImageResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleCouponResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleMileageUpdateResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleModelTrimVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleReminderResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleServiceReminderVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeOldVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeResponseOldVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehiclesDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserWindowStickerResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehcileRecallAlertResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleExtendedInformationVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleGetMileageResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleInformationResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleInformationVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleScanTimeOutResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;
import com.sun.jersey.core.header.FormDataContentDisposition;

import edu.emory.mathcs.backport.java.util.Collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service layer implementation of User Vehicle service Class.
 * @author PankajB
 * @version 1.0
 */
@Service
public class UserVehicleServiceImpl implements UserVehicleServiceIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserVehicleServiceImpl.class);
    @Autowired
    RestUserVehicleTaskIFace vehicleRestService;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    RestVehicleInformationIFace vehicleInformationService;
    @Autowired
    VehicleWebServiceIface vehicleWebService;
    @Autowired
    UserWebServiceIFace userWebService;
    @Autowired
    UserBusIFace userBusService;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    VehicleBusIFace vehicleBusService;
    @Autowired
    ApplicationBusIFace applicationService;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    private DataSource dataSource;
    @Autowired
    RestServiceUploadTaskIFace restServiceUpload;
    @Autowired
    RestUserVehicleTaskImpl userVehicleTask;
    
    private static final String DEFAULT_WIDTH_VALUE = "-1";
    private static final String DEFAULT_MDLCODE_VALUE = "-1";
    /**
     * This Function retrieves the Vehicle Maintenance Schedule by VIN and Driving Habit.
     * @param vehicleNumber
     * @param drivingHabit
     * @return
     */
    public UserVehicleMaintenanceResponseVO getVehicleSchedule(String vehicleNumber, String drivingHabit) {
        logger.debug(">> Entering {} getVehicleSchedule() with VIN & DRIVING HABIT = {}", this.getClass(), new Object[]{vehicleNumber, drivingHabit});
        UserVehicleMaintenanceResponseVO userVehicleMainteanceResponseVO = new UserVehicleMaintenanceResponseVO();
        RestUserVehicleMaintenanceResponseVO restUserVehicleMaintenanceWebServiceResponseVO = null;

        restUserVehicleMaintenanceWebServiceResponseVO = vehicleRestService.getVehicleSchedule(vehicleNumber, drivingHabit);

        if (restUserVehicleMaintenanceWebServiceResponseVO != null && restUserVehicleMaintenanceWebServiceResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_OK)) {
            userVehicleMainteanceResponseVO.setDrivinghabit(restUserVehicleMaintenanceWebServiceResponseVO.getDrivingHabit());
            userVehicleMainteanceResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            userVehicleMainteanceResponseVO.setMaintenanceschedule(restUserVehicleMaintenanceWebServiceResponseVO.getMaintenanceSchedule());
            userVehicleMainteanceResponseVO.setMaintenancenoninterval(restUserVehicleMaintenanceWebServiceResponseVO.getMaintenanceNonInterval());
        } else {
            userVehicleMainteanceResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            if (restUserVehicleMaintenanceWebServiceResponseVO != null) {
                userVehicleMainteanceResponseVO.setDescription(restUserVehicleMaintenanceWebServiceResponseVO.getStatus());
                userVehicleMainteanceResponseVO.setDescription(restUserVehicleMaintenanceWebServiceResponseVO.getError().getMessage());
            } else {
                userVehicleMainteanceResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }

        }

        restUserVehicleMaintenanceWebServiceResponseVO = null;
        logger.debug("<< Exiting getVehicleSchedule() with result =  {} ", userVehicleMainteanceResponseVO);
        return userVehicleMainteanceResponseVO;
    }
    
    /**
     * This Function will return the window sticker details.
     * @param model
     * @param year
     * @param drivingHabit
     * @return
     */
  /*  public UserWindowStickerResponseVO getVehicleWindowSticker(String vin, String dealerId) {
    	
    	logger.debug(">> Entering {} getWindowSticker() with VIN & Dealer id = {}", this.getClass(), new Object[]{vin, dealerId});
    	UserWindowStickerResponseVO windowStickerResponseVO = new UserWindowStickerResponseVO();
    	RestUserVehicleWindowStickerResponseVO restUserVehicleWindowStickerResponseVO = null;
    	
    	restUserVehicleWindowStickerResponseVO = vehicleRestService.getVehicleWindowSticker(vin, dealerId);
    	
    	if (restUserVehicleWindowStickerResponseVO != null && restUserVehicleWindowStickerResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_OK)) {
    		
    		windowStickerResponseVO.setStatus(restUserVehicleWindowStickerResponseVO.getStatus());
    		windowStickerResponseVO.setWindowSticker(restUserVehicleWindowStickerResponseVO.getWindowSticker());
    		
            
        } else {
        	windowStickerResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        	
        }
    	
    	restUserVehicleWindowStickerResponseVO = null;
    	logger.debug("<< Exiting getVehicleWindowSticker() with result =  {} ", windowStickerResponseVO);
    	return windowStickerResponseVO;
    }*/
    
    /**
     * This Function will return the window sticker image details.
     * @param model
     * @param year
     * @param drivingHabit
     * @return
     */
    public UserWindowStickerResponseVO getWindowStickerImage(UserWindowStickerRequestVO windowStickerRequestVO) {
    	
    	logger.debug(">> Entering {} getVehicleWindowSticker() with Parameter={}", this.getClass(), windowStickerRequestVO);

    	UserWindowStickerResponseVO windowStickerResponseVO = new UserWindowStickerResponseVO();
    	    	
    	String responseFromWebService = "";
    	String url = "";
    	
    	String finalWindowStickerQuery = appConstants.SERVICE_REST_USER_WINDOWSTICKER+windowStickerRequestVO.getMdlYear()+"/"+windowStickerRequestVO.getCarlineCode()+"/"
        		+windowStickerRequestVO.getExtColorCode();
    	
    
    	if(windowStickerRequestVO.getWidth()!= DEFAULT_WIDTH_VALUE && windowStickerRequestVO.getMdlCode()!= DEFAULT_MDLCODE_VALUE){
    		finalWindowStickerQuery = finalWindowStickerQuery+"/"+windowStickerRequestVO.getWidth()+"?mdlCode="+windowStickerRequestVO.getMdlCode();
    	}
    	else if(windowStickerRequestVO.getWidth()!= DEFAULT_WIDTH_VALUE && windowStickerRequestVO.getMdlCode()== DEFAULT_MDLCODE_VALUE){
    		finalWindowStickerQuery = finalWindowStickerQuery+"/"+windowStickerRequestVO.getWidth();
    	}
    	else if(windowStickerRequestVO.getWidth()== DEFAULT_WIDTH_VALUE && windowStickerRequestVO.getMdlCode()!= DEFAULT_MDLCODE_VALUE){
    		finalWindowStickerQuery = finalWindowStickerQuery+"?mdlCode="+windowStickerRequestVO.getMdlCode();
    	}
    	
        logger.debug("Final window sticker Query is = {}", finalWindowStickerQuery);
        
        try {
            URL userRegistrationURL = new URL(finalWindowStickerQuery);
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
        logger.debug("Response from window sticker web service is ={}", responseFromWebService);
                
        JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(responseFromWebService);
			
			if(jsonObj.has("body") && !(jsonObj.getString("body").equalsIgnoreCase("null"))){
			
				JSONArray jsonArr = jsonObj.getJSONArray("body");
				 
				url = jsonArr.get(0).toString();
				
				windowStickerResponseVO.setImageUrl(url.trim());
				windowStickerResponseVO.setStatus("success");
		    }
			else{
				windowStickerResponseVO.setStatus("failure");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        logger.debug("<< Exiting getVehicleWindowSticker() with response = {} " + windowStickerResponseVO);
        
    	return windowStickerResponseVO;
    }
    
    /**
     * This function will perform uploading user  image.
     * @param userUploadImageRequestVO
     * @return
     */
    public UserUploadImageResponseVO uploadUserImageDetails(UserUploadImageRequestVO uploadImageRequestVO, InputStream uploadedInputStream, FormDataContentDisposition fileDetail){
    	
    	logger.debug(">> Entering  uploadUserImageDetails() with request =  {} ", uploadImageRequestVO);
    	UserUploadImageResponseVO uploadImageResponseVO = new UserUploadImageResponseVO();
    	try{
    		String fileName = uploadImageRequestVO.getCustomerId()+"_"+uploadImageRequestVO.getVin();
    		String extension = FilenameUtils.getExtension(fileDetail.getFileName());
    	
    		String uploadedFileLocation = appConstants.MMG_USER_VEHICLE_IMAGE_UPLOAD_PATH+ fileName +"."+"jpeg";
    		logger.debug("User image upload file location = {}",uploadedFileLocation);
			
			// Save the image on server path
    		OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
    		int read = 0;
    		byte[] bytes = new byte[1024];

    		out = new FileOutputStream(new File(uploadedFileLocation));
    		while ((read = uploadedInputStream.read(bytes)) != -1) {
    			out.write(bytes, 0, read);
    		}
    		out.flush();
    		out.close();
    		
			logger.debug("Saving image details to DB");
			saveImageDetailsToDB(uploadImageRequestVO.getUserId(), uploadImageRequestVO.getCustomerId().trim(), uploadImageRequestVO.getVin().trim(), uploadedFileLocation);
			
			uploadImageResponseVO.setStatus("success");
			uploadImageResponseVO.setDescription("User image uploaded successfully");
			uploadImageResponseVO.setImagePath(uploadedFileLocation);
			uploadImageResponseVO.setUserId(uploadImageRequestVO.getUserId());
    	} catch(IOException e){
    		logger.error("An error occured while uploading user image. ",e);
    		uploadImageResponseVO.setStatus("failure");
    		uploadImageResponseVO.setDescription("Failed to upload user image");
    		e.printStackTrace();
    	} 
    	logger.debug("<< Exiting getVehicleSchedule() with result =  {} ", uploadImageResponseVO);
    	return uploadImageResponseVO;	
    }
    
    private void saveImageDetailsToDB(String userId, String custId, String vin, String uploadedFileLocation){
       	
    	logger.debug(">> Entering  saveImageDetailsToDB() ");
    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement("SELECT * FROM mmguserimage where cust_id = ? AND vin_cd = ?");
			stmt.setString(1, custId);
			stmt.setString(2, vin);
		
			resultSet=stmt.executeQuery();
		
			if(resultSet.next()){
				String query="UPDATE mmguserimage mmg SET image_path = "
					+"'"+uploadedFileLocation+"'"
					+" WHERE cust_id = "
					+"'"+custId+"'"+" AND vin_cd="
					+"'"+vin+"'";
			
				stmt=conn.prepareStatement(query);
				stmt.executeUpdate();
			}
			else{
				String query="INSERT INTO mmguserimage (user_id,cust_id,vin_cd,image_path) "+
					"VALUES ("+"'"+userId+"','"+custId+"','"+vin+"','"+uploadedFileLocation+"')";
			
				stmt=conn.prepareStatement(query);
				stmt.executeUpdate();
			}
		
		}catch(Exception e){
			logger.error("Problem -  savePushNotification > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
		
		logger.debug("<< Exiting  saveImageDetailsToDB() ");
    }
    
    /**
     * This function will perform fetching user upload image paths.
     * @param userUploadImageRequestVO
     * @return
     */
    public UserUploadImageResponseVO fetchUserImageDetails(UserUploadImageRequestVO uploadImageRequestVO){
    	
    	logger.debug(">> Entering fetchUserImageDetails() with request =  {} ", uploadImageRequestVO);
    	UserUploadImageResponseVO uploadImageResponseVO = new UserUploadImageResponseVO();
    	
    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		String imagePath = null;
		
		try{
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement("SELECT image_path FROM mmguserimage where cust_id = ? AND vin_cd = ?");
			stmt.setString(1, uploadImageRequestVO.getCustomerId());
			stmt.setString(2, uploadImageRequestVO.getVin());
		
			resultSet=stmt.executeQuery();
			
			if(resultSet.next()){
				imagePath = resultSet.getString(1);
				uploadImageResponseVO.setImagePath(imagePath);
				uploadImageResponseVO.setStatus("success");
			}
			else{
				uploadImageResponseVO.setStatus("failure");
				uploadImageResponseVO.setDescription("User Image does not exist");
			}
			
		}catch(Exception e){
			logger.error("Problem -  savePushNotification > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
    	
    	logger.debug("<< Exiting fetchUserImageDetails() with response =  {} ", uploadImageResponseVO);
    	return uploadImageResponseVO;	
    }

    /**
     * This function will perform fetching user upload image paths.
     * @param userUploadImageRequestVO
     * @return
     */
    public UserUploadImageResponseVO deleteUserImageDetails(UserUploadImageRequestVO uploadImageRequestVO){
    	
    	logger.debug(">> Entering deleteUserImageDetails() with request =  {} ", uploadImageRequestVO);
    	UserUploadImageResponseVO uploadImageResponseVO = new UserUploadImageResponseVO();
    	
    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		String imagePath = null;
		
		try{
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement("SELECT * FROM mmguserimage where cust_id = ? AND vin_cd = ?");
			stmt.setString(1, uploadImageRequestVO.getCustomerId());
			stmt.setString(2, uploadImageRequestVO.getVin());
		
			resultSet=stmt.executeQuery();
		
			if(resultSet.next()){
				/*String query="UPDATE mmguserimage mmg SET image_path = "
					+"'"+imagePath+"'"
					+" WHERE cust_id = "
					+"'"+uploadImageRequestVO.getCustomerId()+"'"+" AND vin_cd="
					+"'"+uploadImageRequestVO.getVin()+"'";*/
				
				String query="DELETE FROM mmguserimage "
						+" WHERE cust_id = "
						+"'"+uploadImageRequestVO.getCustomerId()+"'"+" AND vin_cd="
						+"'"+uploadImageRequestVO.getVin()+"'";
			
				stmt=conn.prepareStatement(query);
				stmt.executeUpdate();
				uploadImageResponseVO.setStatus("success");
				uploadImageResponseVO.setDescription("User image deleted successfully");
			}
			else{
				uploadImageResponseVO.setStatus("failure");
				uploadImageResponseVO.setDescription("User Image does not exist");
			}
		
		}catch(Exception e){
			logger.error("Problem -  deleteUserImageDetails > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
    	
    	logger.debug("<< Exiting deleteUserImageDetails() with response =  {} ", uploadImageResponseVO);
    	return uploadImageResponseVO;
    }


    /**
     * This Function will return the Vehicle Schedule by MODEL, YEAR, DRIVING HABIT.
     * @param model
     * @param year
     * @param drivingHabit
     * @return
     */
    public UserVehicleMaintenanceResponseVO getVehicleSchedule(String model, String year, String drivingHabit) {
        logger.debug(">> Entering {} getVehicleSchedule() with MODEL,YEAR,DRIVING HABIT = {}", this.getClass(), new Object[]{model, year, drivingHabit});
        UserVehicleMaintenanceResponseVO userVehicleMainteanceResponseVO = new UserVehicleMaintenanceResponseVO();
        RestUserVehicleMaintenanceResponseVO restUserVehicleMaintenanceWebServiceResponseVO = null;

        restUserVehicleMaintenanceWebServiceResponseVO = vehicleRestService.getVehicleSchedule(model, year, drivingHabit);

        if (restUserVehicleMaintenanceWebServiceResponseVO != null && restUserVehicleMaintenanceWebServiceResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_OK)) {
            userVehicleMainteanceResponseVO.setDrivinghabit(restUserVehicleMaintenanceWebServiceResponseVO.getDrivingHabit());
            userVehicleMainteanceResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            userVehicleMainteanceResponseVO.setMaintenanceschedule(restUserVehicleMaintenanceWebServiceResponseVO.getMaintenanceSchedule());
            userVehicleMainteanceResponseVO.setMaintenancenoninterval(restUserVehicleMaintenanceWebServiceResponseVO.getMaintenanceNonInterval());
        } else {
            userVehicleMainteanceResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            if (restUserVehicleMaintenanceWebServiceResponseVO != null) {
                userVehicleMainteanceResponseVO.setDescription(restUserVehicleMaintenanceWebServiceResponseVO.getStatus());
                userVehicleMainteanceResponseVO.setDescription(restUserVehicleMaintenanceWebServiceResponseVO.getError().getMessage());
            } else {
                userVehicleMainteanceResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }

        }

        restUserVehicleMaintenanceWebServiceResponseVO = null;
        logger.debug("<< Exiting getVehicleSchedule() with result =  {} ", userVehicleMainteanceResponseVO);
        return userVehicleMainteanceResponseVO;
    }

    /**
     * This Function will return the Vehicle Information from the service layer to the controller layer of the application
     * @param vin
     * @return
     */
    public VehicleInformationResponseVO getVehicleInformation(String vin) {
        logger.debug(">> Entering {} getVehicleInformation() with VIN= {}", this.getClass(), vin);
        RestUserVehicleInformationVO userVehicleInformationVO = new RestUserVehicleInformationVO();
        VehicleInformationResponseVO vehicleInformationResponseVO = new VehicleInformationResponseVO();

        userVehicleInformationVO = vehicleInformationService.getVehicleInformation(vin);

        if (userVehicleInformationVO != null && userVehicleInformationVO.getVehicleVO() != null) {

            vehicleInformationResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            VehicleInformationVO vehicleInformationVO = new VehicleInformationVO();

            // Setting the  values.

            vehicleInformationVO.setCarline(userVehicleInformationVO.getVehicleVO().getCarline());
            vehicleInformationVO.setDescription(userVehicleInformationVO.getVehicleVO().getDescription().trim());
            vehicleInformationVO.setEngine(userVehicleInformationVO.getVehicleVO().getEngine().trim());
            vehicleInformationVO.setExtColor(userVehicleInformationVO.getVehicleVO().getExtColor().trim());
            vehicleInformationVO.setIntColor(userVehicleInformationVO.getVehicleVO().getIntColor().trim());
            vehicleInformationVO.setModelYear(userVehicleInformationVO.getVehicleVO().getModelYear().trim());
            vehicleInformationVO.setTransmission(userVehicleInformationVO.getVehicleVO().getTransmission().trim());
            vehicleInformationVO.setTrim(userVehicleInformationVO.getVehicleVO().getTrim());
            vehicleInformationVO.setVin(userVehicleInformationVO.getVehicleVO().getVin());

            vehicleInformationResponseVO.setVehicle(vehicleInformationVO);


        } else if (userVehicleInformationVO != null && userVehicleInformationVO.getError() != null) {
            vehicleInformationResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleInformationResponseVO.setDescription(userVehicleInformationVO.getError());
        } else {
            vehicleInformationResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleInformationResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);

        }


        logger.debug("<< Exiting getVehicleInformation() with result =  {} ", vehicleInformationResponseVO);
        return vehicleInformationResponseVO;
    }

    /**
     * This Function will provide the Detailed information about a Vehicle.
     * @param vin  Vehicle Identification Number
     * @return
     */
    public VehicleExtendedInformationVO getVehicleDetailInformation(String vin) {
        logger.debug(">> Entering {} getVehicleDetailInformation() with VIN= {}", this.getClass(), vin);
        RestUserVehicleDetailInformationVO userVehicleInformationVO = null;
        VehicleExtendedInformationVO vehicleInformationResponseVO = new VehicleExtendedInformationVO();

        userVehicleInformationVO = vehicleInformationService.getVehicleDetailInformation(vin);

        // It means that the execution of the process has happend successfully.
        if (userVehicleInformationVO != null && userVehicleInformationVO.getVehicle().getError() == null) {

            vehicleInformationResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);


            // Setting the  values.
            vehicleInformationResponseVO.setCarline(userVehicleInformationVO.getVehicle().getVehicleCarLineVO());
            vehicleInformationResponseVO.setExtcolor(userVehicleInformationVO.getVehicle().getExtColorVO());
            vehicleInformationResponseVO.setIntcolorVO(userVehicleInformationVO.getVehicle().getIntColorVO());
            vehicleInformationResponseVO.setModel(userVehicleInformationVO.getVehicle().getVehicleModelVO());
            vehicleInformationResponseVO.setOptiongroup(userVehicleInformationVO.getVehicle().getOptionGroupVO());
            vehicleInformationResponseVO.setVin(userVehicleInformationVO.getVehicle().getVin());


        } else if (userVehicleInformationVO != null && userVehicleInformationVO.getVehicle().getError() != null) {
            vehicleInformationResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleInformationResponseVO.setDescription(userVehicleInformationVO.getVehicle().getError());
        } else {
            vehicleInformationResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleInformationResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        }

        logger.debug("<< Exiting getVehicleDetailInformation() with result =  {} ", vehicleInformationResponseVO);
        return vehicleInformationResponseVO;
    }

    /**
     * This is the function, which will be responsible for retrieving the list of Vehicles that belongs to an User, given the User ID.
     * @param userId Represent the Customer ID
     * @return
     */
    public UserVehiclesDetailResponseVO getUserVehicles(String customerId, @Context HttpHeaders headers) {
        logger.debug(">> Entering {} getUserVehicles() with UserID= {}", this.getClass(), customerId);

        RestUserGetVehiclesRequestVO getVehiclesRequestVO = new RestUserGetVehiclesRequestVO();
        RestUserGetVehiclesResponseVO getVehiclesResponseVO = null;
        UserVehiclesDetailResponseVO userVehicleDetailResponseVO = new UserVehiclesDetailResponseVO();

        String requestor = "";
        String requestId = "";
        String version = "";
        
        MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();   
        
        requestor = mmgUtilities.generaterandomReqIdForFusion();
        if(headerParams.containsKey("requestor"))
        {
        	requestor = headers.getRequestHeader("requestor").get(0).trim();
        }
       /* if(headerParams.containsKey("requestid"))
        {
        	requestId = headers.getRequestHeader("requestid").get(0).trim();
        }*/
        requestId = mmgUtilities.generaterandomReqIdForFusion();
        if(headerParams.containsKey("version"))
        {
        	version = headers.getRequestHeader("version").get(0).trim();
        }
        
        // Setting the INPUT Query Variables.
        getVehiclesRequestVO.setCustomerId(customerId);
        getVehiclesRequestVO.setRequestor(requestor);
        getVehiclesRequestVO.setRequestId(requestId);
        getVehiclesRequestVO.setVersion(version);

        getVehiclesResponseVO = userVehicleTask.getUserVehicles(getVehiclesRequestVO);
        if (getVehiclesResponseVO != null && getVehiclesResponseVO.getVehicles()!=null && getVehiclesResponseVO.getVehicles().getError() != null && getVehiclesResponseVO.getVehicles().getError().trim().length() == 0) {

            userVehicleDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            if (getVehiclesResponseVO.getVehicles().getVehiclesDetail() != null) {
                userVehicleDetailResponseVO.setCount(getVehiclesResponseVO.getVehicles().getVehiclesDetail().getCount() + "");

                // here we have to make a check, to determine whether all the vehicles of this User exists in the DB or not. & add those which are not there
                int noOfVehicles = getVehiclesResponseVO.getVehicles().getVehiclesDetail().getCount();
                if (noOfVehicles > 0) {
                    // Find the list of UserVehicles which are present in the database and do a Difference between both of them.
                    UserVehicleVO[] userVehicles = new UserVehicleVO[noOfVehicles];
                    UserVehicleVO userVehicleVO;
                    List<GetVehiclesDetailsVehicleVO> listOfVehicles = getVehiclesResponseVO.getVehicles().getVehiclesDetail().getVehicleList();
                    try {
                        for (int i = 0; i < noOfVehicles; i++) {
                            userVehicleVO = new UserVehicleVO();
                            userVehicleVO.setVehicleVIN(listOfVehicles.get(i).getVin());
                            userVehicles[i] = userVehicleVO;
                        }
                        logger.debug("Trying of Manage {} vehicles for customer ID={}", userVehicles.length, customerId);
                        userBusService.manageUserVehicles(customerId, userVehicles, Boolean.FALSE);
                    } catch (Exception e) {
                        logger.error("An Exception has occured, while trying to manage the list of vehicles for user. ");
                    }
                }

            } else {
                userVehicleDetailResponseVO.setCount(0 + "");
            }


            userVehicleDetailResponseVO.setVehicles(getVehiclesResponseVO.getVehicles().getVehiclesDetail().getVehicleList());

            // Here insert the thing in the Database, to check the number of vehicles are same for each and every user.

        } else if (getVehiclesResponseVO != null && getVehiclesResponseVO.getVehicles().getError() != null) {
            userVehicleDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            userVehicleDetailResponseVO.setDescription(getVehiclesResponseVO.getVehicles().getError());
        } else if (getVehiclesResponseVO == null) {
            userVehicleDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            userVehicleDetailResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            userVehicleDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getUserVehicles() with result =  {} ", userVehicleDetailResponseVO);
        return userVehicleDetailResponseVO;

    }
    
    /**
     * This is the function, which will be responsible for retrieving the egift data belongs to an User, given the User ID and vin.
     * @param userId Represent the Customer ID, vin represent the vin number of vehicle
     * @return
     */
	public UserEgiftsDetailResponseVO getUserEgifts(String customerId, String vin) {
		
        logger.debug(">> Entering getUserEgifts with UserID= {} vin = {}", customerId, vin);

        SOAPUserGetEgiftsRequestVO getEgiftsRequestVO = new SOAPUserGetEgiftsRequestVO();
        SOAPUserGetEgiftsResponseVO getEgiftsResponseVO = null;
        UserEgiftsDetailResponseVO userEgiftDetailResponseVO = new UserEgiftsDetailResponseVO();

        // Setting the INPUT Query Variables.
        getEgiftsRequestVO.setCustomerId(customerId);
        getEgiftsRequestVO.setVin(vin);
        
        getEgiftsResponseVO = userWebService.getUserEgifts(getEgiftsRequestVO);
        
        if (getEgiftsResponseVO != null && getEgiftsResponseVO.getEgifts() !=null && getEgiftsResponseVO.getEgifts().getError() != null && getEgiftsResponseVO.getEgifts().getError().trim().length() == 0) {
        	
        		userEgiftDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
        		if (getEgiftsResponseVO.getEgifts().getEgiftsDetail() != null) {
        			
        			userEgiftDetailResponseVO.setCount(getEgiftsResponseVO.getEgifts().getEgiftsDetail().getCount() + "");
        		}
        		else {
        			userEgiftDetailResponseVO.setCount(0 + "");
                }
        		
        		userEgiftDetailResponseVO.setEgifts(getEgiftsResponseVO.getEgifts().getEgiftsDetail().getEgiftList());
        } 
        else if (getEgiftsResponseVO != null && getEgiftsResponseVO.getEgifts().getError() != null) {
        	userEgiftDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        	userEgiftDetailResponseVO.setDescription(getEgiftsResponseVO.getEgifts().getError());
        }
        else if (getEgiftsResponseVO == null) {
        	userEgiftDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        	userEgiftDetailResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } 
        else {
        	userEgiftDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }
        
        logger.debug("<< Exiting getUserEgifts with response= {} ", getEgiftsResponseVO);

        return userEgiftDetailResponseVO;
	}

    /**
     * This Function will return the Vehicle Recall Alert Response for a particular VIN
     * @param vin
     * @return
     */
    public VehcileRecallAlertResponseVO getVehicleRecallAlert(String vin) {
        logger.debug(">> Entering {} getVehicleRecallAlert() with VIN= {}", this.getClass(), vin);

        VehcileRecallAlertResponseVO vehicleRecallAlertResponseVO = new VehcileRecallAlertResponseVO();
        RestVehicleAlertResponseVO restVehicleAlertResponse = null;


        restVehicleAlertResponse = vehicleInformationService.getVehicleRecallAlertDetail(vin.trim());
        if (restVehicleAlertResponse != null && (restVehicleAlertResponse.getError() == null || restVehicleAlertResponse.getError().trim().length() == 0)) {

            vehicleRecallAlertResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            vehicleRecallAlertResponseVO.setAlerts(restVehicleAlertResponse.getRecalls());


        } else if (restVehicleAlertResponse != null && restVehicleAlertResponse.getError() != null) {
            vehicleRecallAlertResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleRecallAlertResponseVO.setDescription(restVehicleAlertResponse.getError());
        } else {
            vehicleRecallAlertResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleRecallAlertResponseVO.setDescription("Recall information for the VIN entered could not be retrieved at this time. Please try again later"); 
        }

        logger.debug("<< Exiting getVehicleRecallAlert() with result =  {} ", vehicleRecallAlertResponseVO);

        return vehicleRecallAlertResponseVO;
    }

    /**
     * This Function will return the Vehicle History Details.
     * @param customerId Customer ID
     * @param vin VIN
     * @return
     */
    public UserVehicleServiceHistoryResponseVO getVehicleServiceHistoryDetails(String customerId, String vin, @Context HttpHeaders headers) {
        logger.debug(">> Entering {} getVehicleHistoryDetails() with VIN= {}", new Object[]{this.getClass(), customerId, vin});

        UserVehicleServiceHistoryResponseVO vehicleHistoryResponse = new UserVehicleServiceHistoryResponseVO();
        RestUserVehicleServiceHistoryRequestVO vehicleServiceHistoryRequestVO = new RestUserVehicleServiceHistoryRequestVO();
        RestUserVehicleServiceHistoryResponseVO vehicleServiceHistoryResponseVO;
        String requestor = "";
        String requestId = "";
        String version = "";
        
        MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();   
        if(headerParams.containsKey("requestor"))
        {
        	requestor = headers.getRequestHeader("requestor").get(0).trim();
        }        
        /*if(headerParams.containsKey("requestid"))
        {
        	requestId = headers.getRequestHeader("requestid").get(0).trim();
        }*/
        requestId = mmgUtilities.generaterandomReqIdForFusion();
        
        if(headerParams.containsKey("version"))
        {
        	version = headers.getRequestHeader("version").get(0).trim();
        }
        
        // Setting the Input Variables.
        vehicleServiceHistoryRequestVO.setCustomerId(customerId.trim());
        vehicleServiceHistoryRequestVO.setVin(vin.trim());
        vehicleServiceHistoryRequestVO.setRequestor(requestor);
        vehicleServiceHistoryRequestVO.setVersion(version);
        vehicleServiceHistoryRequestVO.setRequestId(requestId);
        
        vehicleServiceHistoryResponseVO = userVehicleTask.getUserVehicleHistoryDetails(vehicleServiceHistoryRequestVO);
        if (vehicleServiceHistoryResponseVO != null && (vehicleServiceHistoryResponseVO.getVehicleServiceHistory().getError() == null || vehicleServiceHistoryResponseVO.getVehicleServiceHistory().getError().trim().length() == 0)) {

            vehicleHistoryResponse.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            vehicleHistoryResponse.setServicedetails(vehicleServiceHistoryResponseVO.getVehicleServiceHistory().getVehicleServiceDetails());


        } else if (vehicleServiceHistoryResponseVO != null && vehicleServiceHistoryResponseVO.getVehicleServiceHistory().getError() != null) {
            vehicleHistoryResponse.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleHistoryResponse.setDescription(vehicleServiceHistoryResponseVO.getVehicleServiceHistory().getError());
        } else if (vehicleServiceHistoryResponseVO == null) {
            vehicleHistoryResponse.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleHistoryResponse.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            vehicleHistoryResponse.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getVehicleHistoryDetails() with result =  {} ", vehicleHistoryResponse);

        return vehicleHistoryResponse;
    }

    /**
     * This Function will return the final Response VO that needs to be sent to the client.
     * @param vin
     * @return
     */
    public VehicleGetMileageResponseVO getVehicleMileage(String vin) {
        logger.debug(">> Entering {} getVehicleMileage() with VIN= {}", new Object[]{this.getClass(), vin});

        VehicleGetMileageResponseVO mileageResponseVO = new VehicleGetMileageResponseVO();
        SOAPUserVehicleMileageRequestVO soapMileageRequestVO = new SOAPUserVehicleMileageRequestVO();
        SOAPUserVehicleMileageResponseVO soapMileageResponseVO = null;

        // Setting the INPUT Variable
        soapMileageRequestVO.setVin(vin.trim());

        soapMileageResponseVO = (SOAPUserVehicleMileageResponseVO) vehicleWebService.getUserVehicleMileage(soapMileageRequestVO);
        
        if (soapMileageResponseVO != null && (soapMileageResponseVO.getMileageDetails().getError() == null || soapMileageResponseVO.getMileageDetails().getError().trim().length() == 0)) {

            mileageResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            soapMileageResponseVO.getMileageDetails().setError(null);
            mileageResponseVO.setMileagedetails(soapMileageResponseVO.getMileageDetails());



        } else if (soapMileageResponseVO != null && soapMileageResponseVO.getMileageDetails().getError() != null) {
            /*
	     ** Commented on 18th Oct 2011 to avoid unknownvin-the vin could not be located on our system issue"
            mileageResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            mileageResponseVO.setDescription(soapMileageResponseVO.getMileageDetails().getError());
            */
            mileageResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            soapMileageResponseVO.getMileageDetails().setError(null);
            soapMileageResponseVO.getMileageDetails().setVin(vin);
            mileageResponseVO.setMileagedetails(soapMileageResponseVO.getMileageDetails());

        } else if (soapMileageResponseVO == null) {
            mileageResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            mileageResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {

            mileageResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            
        }
        
        logger.debug("<< Exiting getVehicleMileage() with result =  {} ", mileageResponseVO);

        return mileageResponseVO;
    }

    /**
     * This Function will be responsible for updating the Mileage of the Vehicle.
     * @param vin
     * @return
     */
    public UserVehicleMileageUpdateResponseVO updateVehicleMileage(UserVehicleMileageUpdateRequestVO vehicleMileageUpdateRequestVO) {
        logger.debug(">> Entering {} updateVehicleMileage() with VIN= {}", new Object[]{this.getClass(), vehicleMileageUpdateRequestVO.getVin()});

        SOAPUserVehicleMileageUpdateRequestVO soapUserVehicleMileageUpdateRequestVO = new SOAPUserVehicleMileageUpdateRequestVO();
        SOAPUserVehicleMileageUpdateResponseVO soapUserVehicleMileageUpdateResponseVO = new SOAPUserVehicleMileageUpdateResponseVO();
        UserVehicleMileageUpdateResponseVO vehicleMileageUpdateResponseVO = new UserVehicleMileageUpdateResponseVO();

        // Setting the INPUT Variables for web service.
        VehicleMileageUpdateVO vehicleMiageUpdateVO = new VehicleMileageUpdateVO();
        soapUserVehicleMileageUpdateRequestVO.setVehicleMileageUpdate(vehicleMiageUpdateVO);
        soapUserVehicleMileageUpdateRequestVO.getVehicleMileageUpdate().setCalcDate(vehicleMileageUpdateRequestVO.getCalcDate());
        soapUserVehicleMileageUpdateRequestVO.getVehicleMileageUpdate().setDrivingConditions(vehicleMileageUpdateRequestVO.getDrivingConditions());
        soapUserVehicleMileageUpdateRequestVO.getVehicleMileageUpdate().setError(vehicleMileageUpdateRequestVO.getError());
        soapUserVehicleMileageUpdateRequestVO.getVehicleMileageUpdate().setMileage(vehicleMileageUpdateRequestVO.getMileage());
        soapUserVehicleMileageUpdateRequestVO.getVehicleMileageUpdate().setMilesPerDay(vehicleMileageUpdateRequestVO.getMilesPerDay());
        soapUserVehicleMileageUpdateRequestVO.getVehicleMileageUpdate().setVin(vehicleMileageUpdateRequestVO.getVin());


        soapUserVehicleMileageUpdateResponseVO = (SOAPUserVehicleMileageUpdateResponseVO) vehicleWebService.updateUserVehicleMileage(soapUserVehicleMileageUpdateRequestVO);
        logger.debug("Result Data from Update Mileage web service: {} ", soapUserVehicleMileageUpdateResponseVO.getResult());

        //In this case, success is being returned by the Web Service, so we are forced to make a check here.
        if (soapUserVehicleMileageUpdateResponseVO != null && (soapUserVehicleMileageUpdateResponseVO.getResult().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase()))) {

            vehicleMileageUpdateResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            vehicleMileageUpdateResponseVO.setDescription(soapUserVehicleMileageUpdateResponseVO.getResult().trim());


        } else if (soapUserVehicleMileageUpdateResponseVO.getResult() != null && (soapUserVehicleMileageUpdateResponseVO.getResult().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase())) == false) {
            vehicleMileageUpdateResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleMileageUpdateResponseVO.setDescription(soapUserVehicleMileageUpdateResponseVO.getResult().trim());

        } else if (soapUserVehicleMileageUpdateResponseVO == null) {
            vehicleMileageUpdateResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleMileageUpdateResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            vehicleMileageUpdateResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting updateVehicleMileage() with result =  {} ", vehicleMileageUpdateResponseVO);

        return vehicleMileageUpdateResponseVO;
    }

    /**
     * This Function will be responsible for set the Servicing Dealer for the VIN
     * @param serServicingDealerRequestVO
     * @return
     */
    public UserVehicleSetServicingDealerResponseVO setServicingDealerForVin(UserVehicleSetServicingDealerRequestVO setServicingDealerRequestVO, @Context HttpHeaders headers) {
        logger.debug(">> Entering {} setServicingDealerForVin() with VIN= {}", new Object[]{this.getClass(), setServicingDealerRequestVO.getVin()});

        RestUserVehicleSetServicingDealerRequestVO userserVehicleSetServicingDealerRequestVO = new RestUserVehicleSetServicingDealerRequestVO();
        RestUserVehicleSetServicingDealerResponseVO userVehicleSetServicingDealerResponseVO = new RestUserVehicleSetServicingDealerResponseVO();
        UserVehicleSetServicingDealerResponseVO vehicleSetServicingDealerResponseVO = new UserVehicleSetServicingDealerResponseVO();

        String requestor = "";
        String requestId = "";
        String version = "";
        
        MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();   
        if(headerParams.containsKey("requestor"))
        {
        	requestor = headers.getRequestHeader("requestor").get(0).trim();
        }
       /* if(headerParams.containsKey("requestid"))
        {
        	requestId = headers.getRequestHeader("requestid").get(0).trim();
        }*/
        requestId = mmgUtilities.generaterandomReqIdForFusion();
        
        if(headerParams.containsKey("version"))
        {
        	version = headers.getRequestHeader("version").get(0).trim();
        }
        
        // Setting the INPUT Variables for web service.
        VehicleUpdateServicingDealerVO vehicleUpdateServicingDealerVO = new VehicleUpdateServicingDealerVO();
        vehicleUpdateServicingDealerVO.setCustomerId(setServicingDealerRequestVO.getCustomerId());
        vehicleUpdateServicingDealerVO.setDealerCode(setServicingDealerRequestVO.getDealerCode());
        vehicleUpdateServicingDealerVO.setSource(setServicingDealerRequestVO.getSource());
        vehicleUpdateServicingDealerVO.setVin(setServicingDealerRequestVO.getVin());
        vehicleUpdateServicingDealerVO.setRequestor(requestor);
        vehicleUpdateServicingDealerVO.setRequestId(requestId);
        vehicleUpdateServicingDealerVO.setVersion(version);
        
        userserVehicleSetServicingDealerRequestVO.setNewServicingDealerVO(vehicleUpdateServicingDealerVO);



        userVehicleSetServicingDealerResponseVO = (RestUserVehicleSetServicingDealerResponseVO) userVehicleTask.setVehicleServicingDealer(userserVehicleSetServicingDealerRequestVO);
        logger.debug("Result Data from Update Mileage web service: {} ", userVehicleSetServicingDealerResponseVO.getResultCode());

        // THis Means something is returned by the web service;
        if (userVehicleSetServicingDealerResponseVO != null && userVehicleSetServicingDealerResponseVO.getResultCode() != null) {
            vehicleSetServicingDealerResponseVO.setCode(userVehicleSetServicingDealerResponseVO.getResultCode());
            if (userVehicleSetServicingDealerResponseVO.getResultCode().trim().toUpperCase().equalsIgnoreCase(appConstants.SERVICE_SOAP_VEHICLE_UPDATE_SERVICINGDEALER_RESPONSECODE_S01.trim().toUpperCase())) {
                // It Means Servicing Dealer has been successfully updated.
                vehicleSetServicingDealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
                vehicleSetServicingDealerResponseVO.setDescription(appConstants.getResponseMessage(appConstants, userVehicleSetServicingDealerResponseVO.getResultCode()));
            } else {
                vehicleSetServicingDealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                vehicleSetServicingDealerResponseVO.setDescription(appConstants.getResponseMessage(appConstants, userVehicleSetServicingDealerResponseVO.getResultCode()));

            }
        } else if (userVehicleSetServicingDealerResponseVO == null) {
            vehicleSetServicingDealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleSetServicingDealerResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            vehicleSetServicingDealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleSetServicingDealerResponseVO.setDescription(appConstants.getResponseMessage(appConstants, " "));

        }

        logger.debug("<< Exiting setServicingDealerForVin() with result =  {} ", vehicleSetServicingDealerResponseVO);

        return vehicleSetServicingDealerResponseVO;
    }

    /**
     * This Function will update the Vehicle Ownership from one Vehicle to another.
     * @param vehicleUpdateOwnershipRequestVO
     * @return
     */
    public UserVehicleUpdateOwnershipResponseVO updateOwnershipForVehicle(UserVehicleUpdateOwnershipRequestVO vehicleUpdateOwnershipRequestVO, @Context HttpHeaders headers) {
        logger.debug(">> Entering {} updateOwnershipForVehicle() with VIN= {}", new Object[]{this.getClass(), vehicleUpdateOwnershipRequestVO.getVin()});

        RestUserVehicleUpdateOwnershipRequestVO userVehicleUpdateOwnershipRequestVO = new RestUserVehicleUpdateOwnershipRequestVO();
        RestUserVehicleUpdateOwnershipResponseVO userVehicleUpdateOwnershipResponseVO = new RestUserVehicleUpdateOwnershipResponseVO();
        UserVehicleUpdateOwnershipResponseVO vehicleUpdateOwnershipResponseVO = new UserVehicleUpdateOwnershipResponseVO();

        String requestor = "";
        String requestId = "";
        String version = "";
        
        MultivaluedMap<String, String> headerParams = headers.getRequestHeaders();   
        if(headerParams.containsKey("requestor"))
        {
        	requestor = headers.getRequestHeader("requestor").get(0).trim();
        }        
       /* if(headerParams.containsKey("requestid"))
        {
        	requestId = headers.getRequestHeader("requestid").get(0).trim();
        }*/
        requestId = mmgUtilities.generaterandomReqIdForFusion();
        
        if(headerParams.containsKey("version"))
        {
        	version = headers.getRequestHeader("version").get(0).trim();
        }  
        
        // Setting the INPUT Variables for web service.
        UpdateVehicleOwnershipVO updateVehicleOwnershipVO = new UpdateVehicleOwnershipVO();
        updateVehicleOwnershipVO.setCustomerId(vehicleUpdateOwnershipRequestVO.getCustomerId());
        updateVehicleOwnershipVO.setDisposalFlag(vehicleUpdateOwnershipRequestVO.getDisposalFlag());
        updateVehicleOwnershipVO.setDisposalReason(vehicleUpdateOwnershipRequestVO.getDisposalReason());
        updateVehicleOwnershipVO.setNewCustomerId(vehicleUpdateOwnershipRequestVO.getNewCustomerId());
        updateVehicleOwnershipVO.setResaleDate(vehicleUpdateOwnershipRequestVO.getResaleDate());
        updateVehicleOwnershipVO.setSource(vehicleUpdateOwnershipRequestVO.getSource());
        updateVehicleOwnershipVO.setVin(vehicleUpdateOwnershipRequestVO.getVin());

        logger.debug(">> Checking for the disposal flag : ", vehicleUpdateOwnershipRequestVO.getDisposalFlag());

        userVehicleUpdateOwnershipRequestVO.setUpdateVehicleOwnershipVO(updateVehicleOwnershipVO);
        
        userVehicleUpdateOwnershipResponseVO = (RestUserVehicleUpdateOwnershipResponseVO) userVehicleTask.updateVehicleOwnership(userVehicleUpdateOwnershipRequestVO);
        logger.debug("Result Data from Update Ownership web service: {} ", userVehicleUpdateOwnershipResponseVO.getResultCode());

        //In this case, success is being returned by the Web Service, so we are forced to make a check here.
        if (userVehicleUpdateOwnershipResponseVO != null && (userVehicleUpdateOwnershipResponseVO.getResultCode().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase()))) {

            vehicleUpdateOwnershipResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            vehicleUpdateOwnershipResponseVO.setDescription(userVehicleUpdateOwnershipResponseVO.getResultCode().trim());


            // Here you need to make sure that the User vehicle which is requested by the User should be a part of user profile.
            if (vehicleUpdateOwnershipRequestVO.getDisposalFlag().equalsIgnoreCase(ApplicationConstants.VEHICLE_OWNERSHIP_REQUEST_DISPOSAL_FLAG_ONE)) {
                UserVehicleVO newUserVehicleVO = new UserVehicleVO();
                newUserVehicleVO.setVehicleVIN(vehicleUpdateOwnershipRequestVO.getVin());
                logger.debug("Adding the UserVehicle VIN={} to User={}", vehicleUpdateOwnershipRequestVO.getVin(), vehicleUpdateOwnershipRequestVO.getNewCustomerId());
                //Updated @ 01-01-2015 to add vehicle name corresponding to VIN
               // userBusService.updateOwnershipOfUserVehicle(vehicleUpdateOwnershipRequestVO.getNewCustomerId(), vehicleUpdateOwnershipRequestVO.getVin());
                userBusService.updateOwnershipOfUserVehicle(vehicleUpdateOwnershipRequestVO.getNewCustomerId(), vehicleUpdateOwnershipRequestVO.getVin(),vehicleUpdateOwnershipRequestVO.getVtitle());
            }

        } else if (userVehicleUpdateOwnershipResponseVO.getResultCode() != null && (userVehicleUpdateOwnershipResponseVO.getResultCode().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase())) == false) {
            vehicleUpdateOwnershipResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleUpdateOwnershipResponseVO.setDescription(userVehicleUpdateOwnershipResponseVO.getResultCode().trim());

        } else if (userVehicleUpdateOwnershipResponseVO == null) {
            vehicleUpdateOwnershipResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            vehicleUpdateOwnershipResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            vehicleUpdateOwnershipResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting updateOwnershipForVehicle() with result =  {} ", vehicleUpdateOwnershipResponseVO);

        return vehicleUpdateOwnershipResponseVO;
    }

    /**
     * This is the Service layer function which is responsible for returning the List of Vehicle Service Reminders for a VIN
     * @param vehicleReminderRequestVO
     * @param request
     * @return
     */
    public UserVehicleReminderResponseVO getVehicleServiceReminders(UserVehicleCouponReminderRequestVO vehicleReminderRequestVO, HttpServletRequest request) {
        logger.debug(">> Entering {} getVehicleServiceReminders() with VIN= {}", new Object[]{this.getClass(), vehicleReminderRequestVO.getVin()});

        SOAPVehicleCouponsRemindersRequestVO soapVehicleCouponsRemindersRequestVO = new SOAPVehicleCouponsRemindersRequestVO();
        SOAPVehicleCouponsRemindersResponseVO soapVehicleCouponsRemindersResponseVO = new SOAPVehicleCouponsRemindersResponseVO();
        UserVehicleReminderResponseVO userVehicleReminderResponseVO = new UserVehicleReminderResponseVO();
        List<UserVehicleServiceReminderVO> listOfServiceReminders = new ArrayList<UserVehicleServiceReminderVO>();

        // Setting the INPUT Variables for web service.
        VehicleCouponsRemindersVO vehicleCouponReminderVO = new VehicleCouponsRemindersVO();
        vehicleCouponReminderVO.setCustId(vehicleReminderRequestVO.getCustId());
        vehicleCouponReminderVO.setSource(ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA_SOURCE_SERVICEREMINDER);
        vehicleCouponReminderVO.setVin(vehicleReminderRequestVO.getVin());

        soapVehicleCouponsRemindersRequestVO.setVehicleCouponRemindersVO(vehicleCouponReminderVO);

        soapVehicleCouponsRemindersResponseVO = (SOAPVehicleCouponsRemindersResponseVO) vehicleWebService.getCouponAndServiceRemindersData(soapVehicleCouponsRemindersRequestVO);

        //In this case, success is being returned by the Web Service, so we are forced to make a check here.
        if (soapVehicleCouponsRemindersResponseVO != null && soapVehicleCouponsRemindersResponseVO.getResponseData() != null && (soapVehicleCouponsRemindersResponseVO.getResponseData().getStatus().trim().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase()))) {

            userVehicleReminderResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            userVehicleReminderResponseVO.setCount(soapVehicleCouponsRemindersResponseVO.getResponseData().getCount());


            // Setting the Service Reminder Text.
            int dotPositionSRText = appConstants.MMG_CUSTOM_MESAGE_SERVICE_REMINDERS_DEFAULT_TEXT.indexOf(".");
            List<CouponsRemindersDataVO> serviceReminders = soapVehicleCouponsRemindersResponseVO.getResponseData().getServiceReminders();


            if (serviceReminders != null) {

                for (CouponsRemindersDataVO serviceReminder : serviceReminders) {
                    UserVehicleServiceReminderVO sr = new UserVehicleServiceReminderVO();

                    // set the serviceREminder Date to the correct format.
                    if (serviceReminder.getServiceReminderDate() != null) {
                        sr.setServiceReminderDate(mmgUtilities.changeDateToDDMMYYYY(serviceReminder.getServiceReminderDate()));
                    }
                    sr.setContactOfferId(serviceReminder.getContactOfferId());

                    String serviceReminderText = serviceReminder.getSrText();
                    if (serviceReminderText != null && serviceReminderText.length() != 0) {
                        // it Means Web service is returning the Text. so copy it directly.
                        sr.setTitleText(appConstants.MMG_CUSTOM_MESAGE_SERVICE_REMINDERS_DEFAULT_TEXT.substring(0, dotPositionSRText + 1) + "\n" + serviceReminderText);
                    } else {
                        // Otherwise copy the Service Reminder text from the properties files.
                        sr.setTitleText(appConstants.MMG_CUSTOM_MESAGE_SERVICE_REMINDERS_DEFAULT_TEXT);

                    }

                    // Setting the Service Reminder TITLE From the Letter Code.
                    String serviceReminderTitleLetterCode = serviceReminder.getLetterCode();
                    if (serviceReminderTitleLetterCode != null && serviceReminderTitleLetterCode.trim().length() != 0) {
                        // here means we are getting the service reminder code, so change it in an string and append it.

                      
                        if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_10.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_10_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1A.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1A_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1AFC.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1AFC_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1AUFC.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1AUFC_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1B.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1B_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1BFC.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_1BFC_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_2.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_2_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_2FC.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_2FC_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_3.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_3_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_5.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_5_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_5FC.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_5FC_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_6.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_6_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_7.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_7_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_8.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_8_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_9.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_9_VALUE);
                        } else if (serviceReminderTitleLetterCode.trim().equalsIgnoreCase(appConstants.MMG_SERVICEREMINDER_LETTERCODE_9FC.trim())) {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_9FC_VALUE);
                        } else {
                            sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_DEFAULT_VALUE);
                        }

                    } else {
                        // here means we are not getting the service Reminder title.
                        sr.setTitleDesc(appConstants.MMG_SERVICEREMINDER_LETTERCODE_DEFAULT_VALUE);
                    }

                    CouponRemindersDataDetailFullCircleDataVO fullCircleData = serviceReminder.getFullCircleData();
                    if (fullCircleData != null) {
                        // Do the Letter Code Changing.
                        setFullCircleData(fullCircleData);
                      //  fullCircleData.setServiceDate(mmgUtilities.changeDate(fullCircleData.getServiceDate()));
                        sr.setFullCircleData(fullCircleData);
                    }

                    List<CouponRemindersDataDetailVO> list = serviceReminder.getCoupons();

                    if (list != null) {
                        logger.debug("No of COupons = {}", list.size());
                        for (CouponRemindersDataDetailVO serviceReminderCoupon : list) {
                            //The condition to check user-agent is being removed to make the response identical for both iPhone as wel as Android client
                            // This is the function call for setting left/right header added on 19/01/2012
                            //if(request.getHeader("User-Agent") != null && request.getHeader("User-Agent").trim().toLowerCase().contains(EmmConstants.EMM_USER_AGENT_TYPE_ANDROID)){
                                // set the left header and right header for Android device
                                setleftRightHeaderForAndroid(serviceReminderCoupon);
                            //}else{
                                // set the left header and right header for iPhone device
                              //  setleftRightHeaderForIphone(serviceReminderCoupon);
                            //}

                            // Now Iterate through the list of Coupons and add the disclaimer text in each of the coupon with the Default text.
                            if (serviceReminderCoupon.getDisclaimerId() != null && serviceReminderCoupon.getDisclaimerId().trim().length() != 0 && serviceReminderCoupon.getDisclaimerId().trim().equalsIgnoreCase("STD") == false) {
                                // set the Disclaimer as being provided by the web service.  Need to change.
                                serviceReminderCoupon.setDisclaimerId(mmgUtilities.dataToBeModifyForDesclaimer(appConstants.MMG_STRING_BR_TAG, serviceReminderCoupon.getDisclaimerId()));
                            } else {

                                serviceReminderCoupon.setDisclaimerId(appConstants.MMG_CUSTOM_MEESAGE_COUPON_DISCLAIMER_DEFAULT_TEXT.concat(mmgUtilities.changeDate(serviceReminderCoupon.getExpirationDate()))+".");

                            }
                        }
                    }

                    // Code introduced on 26th July for setting the price for the VIN COUPON
                    //setCouponsPrice(list);
                    sr.setCoupons(list);   // This is actually setting the number of COupons.

                    listOfServiceReminders.add(sr);


                }  // Ending the Mail for loop for traveing the Service Reminders.
                userVehicleReminderResponseVO.setServiceReminders(listOfServiceReminders);
            }
        } else if (soapVehicleCouponsRemindersResponseVO != null && (soapVehicleCouponsRemindersResponseVO.getResponseData() != null && soapVehicleCouponsRemindersResponseVO.getResponseData().getStatus().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase())) == false) {
            userVehicleReminderResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            userVehicleReminderResponseVO.setDescription(soapVehicleCouponsRemindersResponseVO.getResponseData().getStatus());

        } else if (soapVehicleCouponsRemindersResponseVO == null) {
            userVehicleReminderResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            userVehicleReminderResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            userVehicleReminderResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getVehicleServiceReminders() with result =  {} ", userVehicleReminderResponseVO);

        return userVehicleReminderResponseVO;
    }

    /**
     * This is the function, which will be invoked to get the Coupons for every VEHICLE
     * @param vehicleCouponRequestVO
     * @param request
     * @return
     */
    public UserVehicleCouponResponseVO getVehicleCoupons(UserVehicleCouponReminderRequestVO vehicleCouponRequestVO, HttpServletRequest request) {
        logger.debug(">> Entering {} getVehicleServiceReminders() with VIN= {}", new Object[]{this.getClass(), vehicleCouponRequestVO.getVin()});

        SOAPVehicleCouponsRemindersRequestVO soapVehicleCouponsRemindersRequestVO = new SOAPVehicleCouponsRemindersRequestVO();
        SOAPVehicleCouponsRemindersResponseVO soapVehicleCouponsRemindersResponseVO = new SOAPVehicleCouponsRemindersResponseVO();
        UserVehicleCouponResponseVO userVehicleCouponResponseVO = new UserVehicleCouponResponseVO();

        // Setting the INPUT Variables for web service.
        VehicleCouponsRemindersVO vehicleCouponReminderVO = new VehicleCouponsRemindersVO();
        vehicleCouponReminderVO.setCustId(vehicleCouponRequestVO.getCustId());
        // This has to be different for Coupon and Service Reminders.
        vehicleCouponReminderVO.setSource(ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA_SOURCE_COUPON);
        vehicleCouponReminderVO.setVin(vehicleCouponRequestVO.getVin());

        soapVehicleCouponsRemindersRequestVO.setVehicleCouponRemindersVO(vehicleCouponReminderVO);

        soapVehicleCouponsRemindersResponseVO = (SOAPVehicleCouponsRemindersResponseVO) vehicleWebService.getCouponAndServiceRemindersData(soapVehicleCouponsRemindersRequestVO);

        //In this case, success is being returned by the Web Service, so we are forced to make a check here.
        if (soapVehicleCouponsRemindersResponseVO != null && soapVehicleCouponsRemindersResponseVO.getResponseData() != null && (soapVehicleCouponsRemindersResponseVO.getResponseData().getStatus().trim().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase()))) {

            userVehicleCouponResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

            // Now Iterate through the list of Coupons and add the disclaimer text in each of the coupon with the Default text.
            List<CouponRemindersDataDetailVO> list = soapVehicleCouponsRemindersResponseVO.getResponseData().getServiceOffers().getListOfcouponDataDetailVO();
            if (list != null) {
                for (CouponRemindersDataDetailVO serviceReminderCoupon : list) {
                    //The condition to check user-agent is being removed to make the response identical for both iPhone as wel as Android client
                    // This is the function call for setting left/right header added on 19/01/2012
                    //if(request.getHeader("User-Agent") != null && request.getHeader("User-Agent").trim().toLowerCase().contains(EmmConstants.EMM_USER_AGENT_TYPE_ANDROID)){
                        //set the left header and right header for adnroid device
                        setleftRightHeaderForAndroid(serviceReminderCoupon);
                    //}else{
                        // set the left header and right header for iPhone device
                        //setleftRightHeaderForIphone(serviceReminderCoupon);
                    //}

                    if (serviceReminderCoupon.getDisclaimerId() != null && serviceReminderCoupon.getDisclaimerId().trim().length() != 0 && serviceReminderCoupon.getDisclaimerId().trim().equalsIgnoreCase("STD") == false) {
                        // set the Disclaimer as being provided by the web service.
                        serviceReminderCoupon.setDisclaimerId(mmgUtilities.dataToBeModifyForDesclaimer(appConstants.MMG_STRING_BR_TAG, serviceReminderCoupon.getDisclaimerId()));
                    } else {
                        //Date conversion added on 05/08/2011

                        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("mm/dd/yyyy");
                        String rcievedExpiratationDate = serviceReminderCoupon.getExpirationDate();

                        try {
                            Date parsingDate = inputDateFormat.parse(rcievedExpiratationDate);
                            String finalExpirationDate = outputDateFormat.format(parsingDate);
                            logger.debug("Changed format for expiration date ", finalExpirationDate);
                            //serviceReminderCoupon.setDisclaimerId(appConstants.MMG_CUSTOM_MEESAGE_COUPON_DISCLAIMER_DEFAULT_TEXT.concat(serviceReminderCoupon.getExpirationDate()));
                            serviceReminderCoupon.setDisclaimerId(appConstants.MMG_CUSTOM_MEESAGE_COUPON_DISCLAIMER_DEFAULT_TEXT.concat(finalExpirationDate)+".");
                        } catch (ParseException pe) {
                            logger.error("An Exception occured while parsing the expiration date.", pe);
                        }
                    }
                }
            }

            // Code introduced on 26th July for setting the price for the VIN COUPON
            //setCouponsPrice(list); -->pricing locing is removed, as it has to be provided by the MINAC
            userVehicleCouponResponseVO.setCount(soapVehicleCouponsRemindersResponseVO.getResponseData().getCount());
            userVehicleCouponResponseVO.setCoupons(soapVehicleCouponsRemindersResponseVO.getResponseData().getServiceOffers().getListOfcouponDataDetailVO());
//            userVehicleCouponResponseVO.setFullCircleData(setFullCircleData(soapVehicleCouponsRemindersResponseVO.getCouponRemindersDataVO().getFullCircleData()));


        } else if (soapVehicleCouponsRemindersResponseVO != null && (soapVehicleCouponsRemindersResponseVO.getResponseData() != null && soapVehicleCouponsRemindersResponseVO.getResponseData().getStatus().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase())) == false) {
            userVehicleCouponResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            userVehicleCouponResponseVO.setDescription(soapVehicleCouponsRemindersResponseVO.getResponseData().getStatus());

        } else if (soapVehicleCouponsRemindersResponseVO == null) {
            userVehicleCouponResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            userVehicleCouponResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
        } else {
            userVehicleCouponResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getVehicleServiceReminders() with result =  {} ", userVehicleCouponResponseVO);

        return userVehicleCouponResponseVO;
    }

    /**
     * This is the function, which return the list of Vehicle Codes for the mentioned Year.
     * @param year
     * @return
     */
    @Cacheable(cacheName = "VehicleModelYearCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public UserVehicleYearCodeResponseVO getVehicleCodes(String... year) {
        logger.debug(">> Entering {} getVehicleCodes() with YEAR= {}", new Object[]{this.getClass(), year});
        UserVehicleYearCodeResponseVO userVehicleCodeYearCodes = new UserVehicleYearCodeResponseVO();
        UserVehicleYearCodeVO userVehicleCodeVO = null;
        List<VehicleCodeVO> listOfAllVehicleModels = null;

        try {
            if (year == null || year.length == 0) {

                // Here it means, we have to return all the Models present in the database.
                listOfAllVehicleModels = vehicleBusService.getAllVehicleModels();
            } else if (year != null && year.length > 0) {
                listOfAllVehicleModels = vehicleBusService.getVehicleModelsForYear(year[0]);
            }


            List<UserVehicleYearCodeVO> listOfUserVehicleYearCodes=PopulateCodeResp(listOfAllVehicleModels);
            
            userVehicleCodeYearCodes.setVehicles(listOfUserVehicleYearCodes);
            userVehicleCodeYearCodes.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

            // Add the last change time of the vehicle COdes Table here.
            ConstantVO constantVO = applicationService.getConstant(appConstants.DATABASE_CONSTANT_MMGVEHICLECODE_UPDATEDTIME);
            if (constantVO != null) {
                userVehicleCodeYearCodes.setLastchangetime(constantVO.getUpdatedDate().getTime());
            }

            listOfUserVehicleYearCodes = null;

        } catch (Exception ex) {
            logger.error("AN Exception has occured, whiel getting the list of Vehicle Codes from database. ", ex);
            userVehicleCodeYearCodes.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getVehicleCodes() ");
        return userVehicleCodeYearCodes;
    }

    
    private List<UserVehicleYearCodeVO> PopulateCodeResp(List<VehicleCodeVO> codesList)
    {
    	List<UserVehicleYearCodeVO> models=new ArrayList<UserVehicleYearCodeVO>();
    	
    	try{
    	//Vehicle code & trim name mapping data
    	HashMap<Integer,String> vehicleTrimMap=getVehicleTrims();
    	
    	
    	//models map <2014,cx5~cx-5>
    	HashMap<String,Set<String>> modelsMap=new HashMap<String,Set<String>>();
    	//trims map <2014~cx5~cx-5,trim1~trim2>
    	HashMap<String,Set<String>> trimsMap=new HashMap<String,Set<String>>();
    	
    	for(VehicleCodeVO temp:codesList)
    	{
    		String yrmodelName=new StringBuffer(temp.getVehicleYear()).append("~").append(temp.getVehicleModel()).append("~").append(temp.getVehicleName()).toString();
    		
    		//populate models map
    		if(modelsMap.containsKey(temp.getVehicleYear()))
    		{
    			modelsMap.get(temp.getVehicleYear()).add(yrmodelName);
    		}else{
    			
    			modelsMap.put(temp.getVehicleYear(), new HashSet<String>());
    			modelsMap.get(temp.getVehicleYear()).add(yrmodelName);
    		}
    		
    		//populate trims map
    		String trimName=vehicleTrimMap.get(temp.getVehicleCodeId());
    		if(trimName!=null)
    		trimsMap.put(yrmodelName, new HashSet<String>(Arrays.asList(trimName.split("~"))));
    		else
    		trimsMap.put(yrmodelName, new HashSet<String>());	
    		
    	}
    	
    	ArrayList<String> years=new ArrayList<String>(modelsMap.keySet());
    	Collections.sort(years,Collections.reverseOrder());
    	//create response obj
    	for(String yr:years)
    	{
    		UserVehicleYearCodeVO uvycvo=new UserVehicleYearCodeVO();
    		uvycvo.setYear(yr);
    		Set<String> modelsSet=modelsMap.get(yr);
    		
    		/*
    		//populate models list
    		List<String> modelsList=new ArrayList<String>(modelsSet.size());
    		for(String tempName:modelsSet)
    		{
    			String[] modelNameArr=tempName.split("~");
    			modelsList.add(new StringBuffer(modelNameArr[1]).append(DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL_SEPARACTOR).append(modelNameArr[2]).toString());
    		}
    	*/	
    		//populate trims list from map <2014~cx5~cx-5,trim1>
    		List<UserVehicleModelTrimVO> trimsList=new ArrayList<UserVehicleModelTrimVO>();
    		UserVehicleModelTrimVO mtrimVo=null;
    		for(String temp:modelsSet)
    		{
    			mtrimVo=new UserVehicleModelTrimVO();
    			mtrimVo.setModelCode(temp.split("~")[1]);
    			mtrimVo.setModelName(temp.split("~")[2]);
    			
    			Set<TrimVO> trimSet=new HashSet<TrimVO>();
    			for(String tname:trimsMap.get(temp))
    			{
    				if(!tname.equalsIgnoreCase("default"))
    				trimSet.add(new TrimVO(tname, null));	
    			}	
    			mtrimVo.setTrims(trimSet);
    			
    			trimsList.add(mtrimVo);
    		}
    		
    		Collections.sort(trimsList);
    		
    		//uvycvo.setModel(modelsList);
    		uvycvo.setModels(trimsList);
    		
    		models.add(uvycvo);
    	}
    	
    	years=null;
    	modelsMap=null;
    	trimsMap=null;
    	vehicleTrimMap=null;
    	}catch(Exception e){e.printStackTrace();}
    	
    	return models;
    	
    }
    

    
    /**
     * This is the function, which return the list of Vehicle Codes for the mentioned Year.This is 
     * compatible for old version client apps.
     * @param year
     * @return
     */    
    @Cacheable(cacheName = "VehicleModelYearCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public UserVehicleYearCodeResponseOldVO getVehicleCodesOld(String... year) {
        logger.debug(">> Entering {} getVehicleCodesOld() with YEAR= {}", new Object[]{this.getClass(), year});
        UserVehicleYearCodeResponseOldVO userVehicleCodeYearCodes = new UserVehicleYearCodeResponseOldVO();
        UserVehicleYearCodeOldVO userVehicleCodeVO = null;
        List<UserVehicleYearCodeOldVO> listOfUserVehicleYearCodes = null;
        String modelYear = "";
        List<VehicleCodeVO> listOfAllVehicleModels = null;

        try {
            if (year == null || year.length == 0) {

                // Here it means, we have to return all the Models present in the database.
                listOfAllVehicleModels = vehicleBusService.getAllVehicleModels();
            } else if (year != null && year.length > 0) {
                listOfAllVehicleModels = vehicleBusService.getVehicleModelsForYear(year[0]);
            }

            listOfUserVehicleYearCodes = new ArrayList<UserVehicleYearCodeOldVO>();
            if (listOfAllVehicleModels != null && listOfAllVehicleModels.size() > 0) {
                for (VehicleCodeVO tempVehicleCodeVO : listOfAllVehicleModels) {


                    if (modelYear.trim().length() == 0) {
                        modelYear = tempVehicleCodeVO.getVehicleYear().trim();
                        userVehicleCodeVO = new UserVehicleYearCodeOldVO();
                        userVehicleCodeVO.setYear(tempVehicleCodeVO.getVehicleYear());
                        userVehicleCodeVO.setLastchangetime(tempVehicleCodeVO.getVehicleaddedupdateddate().getTime());
                        userVehicleCodeVO.setModels(new ArrayList<String>());
                    }
                    if (modelYear.equalsIgnoreCase(tempVehicleCodeVO.getVehicleYear().trim()) == false) {
                        // Means u need to store the model and the Code in the
                        listOfUserVehicleYearCodes.add(userVehicleCodeVO);
                        modelYear = tempVehicleCodeVO.getVehicleYear().trim();
                        userVehicleCodeVO = new UserVehicleYearCodeOldVO();
                        userVehicleCodeVO.setYear(tempVehicleCodeVO.getVehicleYear());
                        userVehicleCodeVO.setLastchangetime(tempVehicleCodeVO.getVehicleaddedupdateddate().getTime());
                        userVehicleCodeVO.setModels(new ArrayList<String>());
                    }

                    userVehicleCodeVO.getModels().add(tempVehicleCodeVO.getVehicleModel() + DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL_SEPARACTOR + tempVehicleCodeVO.getVehicleName());

                }

                listOfUserVehicleYearCodes.add(userVehicleCodeVO);
            }

            userVehicleCodeYearCodes.setModels(listOfUserVehicleYearCodes);
            userVehicleCodeYearCodes.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

            // Add the last change time of the vehicle COdes Table here.
            ConstantVO constantVO = applicationService.getConstant(appConstants.DATABASE_CONSTANT_MMGVEHICLECODE_UPDATEDTIME);
            if (constantVO != null) {
                userVehicleCodeYearCodes.setLastchangetime(constantVO.getUpdatedDate().getTime());
            }

            listOfUserVehicleYearCodes = null;

        } catch (Exception ex) {
            logger.error("AN Exception has occured, whiel getting the list of Vehicle Codes from database. ", ex);
            userVehicleCodeYearCodes.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getVehicleCodesOld() ");
        return userVehicleCodeYearCodes;
    }    
    
    
    
    /**
     * This is the function, which return the list of Vehicle Codes for the mentioned Year.
     * @param year
     * @return
     */
/*    
    @Cacheable(cacheName = "VehicleModelYearCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public UserVehicleYearCodeResponseOldVO getVehicleCodesOld(String... year) {
        logger.debug(">> Entering {} getVehicleCodes() with YEAR= {}", new Object[]{this.getClass(), year});
        UserVehicleYearCodeResponseOldVO userVehicleCodeYearCodes = new UserVehicleYearCodeResponseOldVO();
        UserVehicleYearCodeOldVO userVehicleCodeVO = null;
        List<UserVehicleYearCodeOldVO> listOfUserVehicleYearCodes = null;
        String modelYear = "";
        List<VehicleCodeVO> listOfAllVehicleModels = null;

        try {
            if (year == null || year.length == 0) {

                // Here it means, we have to return all the Models present in the database.
                listOfAllVehicleModels = vehicleBusService.getAllVehicleModels();
            } else if (year != null && year.length > 0) {
                listOfAllVehicleModels = vehicleBusService.getVehicleModelsForYear(year[0]);
            }

            listOfUserVehicleYearCodes = new ArrayList<UserVehicleYearCodeOldVO>();
            if (listOfAllVehicleModels != null && listOfAllVehicleModels.size() > 0) {
                for (VehicleCodeVO tempVehicleCodeVO : listOfAllVehicleModels) {


                    if (modelYear.trim().length() == 0) {
                        modelYear = tempVehicleCodeVO.getVehicleYear().trim();
                        userVehicleCodeVO = new UserVehicleYearCodeOldVO();
                        userVehicleCodeVO.setYear(tempVehicleCodeVO.getVehicleYear());
                        userVehicleCodeVO.setLastchangetime(tempVehicleCodeVO.getVehicleaddedupdateddate().getTime());
                        userVehicleCodeVO.setModels(new ArrayList<String>());
                    }
                    if (modelYear.equalsIgnoreCase(tempVehicleCodeVO.getVehicleYear().trim()) == false) {
                        // Means u need to store the model and the Code in the
                        listOfUserVehicleYearCodes.add(userVehicleCodeVO);
                        modelYear = tempVehicleCodeVO.getVehicleYear().trim();
                        userVehicleCodeVO = new UserVehicleYearCodeOldVO();
                        userVehicleCodeVO.setYear(tempVehicleCodeVO.getVehicleYear());
                        userVehicleCodeVO.setLastchangetime(tempVehicleCodeVO.getVehicleaddedupdateddate().getTime());
                        userVehicleCodeVO.setModels(new ArrayList<String>());
                    }

                    userVehicleCodeVO.getModels().add(tempVehicleCodeVO.getVehicleModel() + DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL_SEPARACTOR + tempVehicleCodeVO.getVehicleName());

                }

                listOfUserVehicleYearCodes.add(userVehicleCodeVO);
            }

            userVehicleCodeYearCodes.setModels(listOfUserVehicleYearCodes);
            userVehicleCodeYearCodes.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

            // Add the last change time of the vehicle COdes Table here.
            ConstantVO constantVO = applicationService.getConstant(appConstants.DATABASE_CONSTANT_MMGVEHICLECODE_UPDATEDTIME);
            if (constantVO != null) {
                userVehicleCodeYearCodes.setLastchangetime(constantVO.getUpdatedDate().getTime());
            }

            listOfUserVehicleYearCodes = null;

        } catch (Exception ex) {
            logger.error("AN Exception has occured, whiel getting the list of Vehicle Codes from database. ", ex);
            userVehicleCodeYearCodes.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }

        logger.debug("<< Exiting getVehicleCodes() ");
        return userVehicleCodeYearCodes;
    }
*/
    
    
    private CouponRemindersDataDetailFullCircleDataVO setFullCircleData(CouponRemindersDataDetailFullCircleDataVO fullCircleDataVO) {
        if (fullCircleDataVO.getBrake() != null && fullCircleDataVO.getBrake().trim().length() != 0) {
            logger.debug("Full Circle Data -> Brake information ={}", fullCircleDataVO.getBrake());
            if (fullCircleDataVO.getBrake().trim().equalsIgnoreCase(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_GREEN.trim())) {
                fullCircleDataVO.setBrake(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_GREEN_DESCRIPTION);
            } else if (fullCircleDataVO.getBrake().trim().equalsIgnoreCase(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_YELLOW.trim())) {
                fullCircleDataVO.setBrake(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_YELLOW_DESCRIPTION);
            } else if (fullCircleDataVO.getBrake().trim().equalsIgnoreCase(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_RED.trim())) {
                fullCircleDataVO.setBrake(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_RED_DESCRIPTION);
            } else {
                fullCircleDataVO.setBrake("WNot Inspected");
            }
        } else {
            fullCircleDataVO.setBrake("WNot Inspected");
        }
        // Setting the Tire Information.

        if (fullCircleDataVO.getTire() != null && fullCircleDataVO.getTire().trim().length() != 0) {
            logger.debug("Full Circle Data -> Tire information ={}", fullCircleDataVO.getTire());
            if (fullCircleDataVO.getTire().trim().equalsIgnoreCase(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_GREEN.trim())) {
                fullCircleDataVO.setTire(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_GREEN_DESCRIPTION);
            } else if (fullCircleDataVO.getTire().trim().equalsIgnoreCase(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_YELLOW.trim())) {
                fullCircleDataVO.setTire(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_YELLOW_DESCRIPTION);
            } else if (fullCircleDataVO.getTire().trim().equalsIgnoreCase(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_RED.trim())) {
                fullCircleDataVO.setTire(appConstants.MMG_CODE_TIRE_BRAKE_CONDITION_RED_DESCRIPTION);
            } else {
                fullCircleDataVO.setTire("WNot Inspected");
            }
        } else {
            fullCircleDataVO.setTire("WNot Inspected");
        }


        return fullCircleDataVO;
    }

    /**
     * Below Function is being introduced on 26th July.
     * This is the function to be responsible for introducing the price tag on the Coupons both in service reminders and in coupons.
     * @param listOfCouponReminders
     */
    public void setCouponsPrice(List<CouponRemindersDataDetailVO> listOfCouponReminders) {
        logger.debug(">> Started Modifing the Coupon Price ");

        if (listOfCouponReminders != null && listOfCouponReminders.isEmpty() == false) {
            for (CouponRemindersDataDetailVO coupon : listOfCouponReminders) {

                String priceToBeAdded = "";
                if (coupon.getFree() != null && coupon.getFree().trim().length() != 0 && coupon.getFree().trim().equalsIgnoreCase("Y") == true) {
                    priceToBeAdded = appConstants.COUPON_PRICE_FREE;
                } else if (coupon.getDollarOff() != null && coupon.getDollarOff().trim().length() != 0 && checkCouponPriceZero(coupon.getDollarOff()) == false) {
                    priceToBeAdded = appConstants.DOLLAR + coupon.getDollarOff() + " " + appConstants.COUPON_PRICE_OFF;
                } else if (coupon.getPctOff() != null && coupon.getPctOff().trim().length() != 0 && checkCouponPriceZero(coupon.getPctOff()) == false) {
                    //priceToBeAdded = coupon.getPctOff() + appConstants.PERCENTAGE + " " + appConstants.COUPON_PRICE_OFF;
                    priceToBeAdded = Double.valueOf(coupon.getPctOff()).intValue() + appConstants.PERCENTAGE + " " + appConstants.COUPON_PRICE_OFF;
                } else if (coupon.getSpecificationPrice() != null && coupon.getSpecificationPrice().trim().length() != 0 && checkCouponPriceZero(coupon.getSpecificationPrice()) == false) {
                    priceToBeAdded = appConstants.DOLLAR + coupon.getSpecificationPrice();
                } else {
                    priceToBeAdded = appConstants.DEALER_COUPON_PRICE_SAVE;
                }

                coupon.setSpecificationPrice(priceToBeAdded);

                // In addtion also change the LineText, so that it will appear on the right hand side of the Screen as per the PPT Sent.
                if (coupon.getLineText1() != null && coupon.getLineText1().trim().length() == 0 &&
                    coupon.getLineText2() != null && coupon.getLineText2().trim().length() == 0 &&
                    coupon.getLineText3() != null && coupon.getLineText3().trim().length() == 0 &&
                    coupon.getLineText4() != null && coupon.getLineText4().trim().length() == 0 &&
                    coupon.getLineText5() != null && coupon.getLineText5().trim().length() == 0 &&
                    coupon.getLineText6() != null && coupon.getLineText6().trim().length() == 0 &&
                    coupon.getLineText7() != null && coupon.getLineText7().trim().length() == 0 &&
                    coupon.getLineText8() != null && coupon.getLineText8().trim().length() == 0 &&
                    coupon.getLineText9() != null && coupon.getLineText9().trim().length() == 0 &&
                    coupon.getLineText10() != null && coupon.getLineText10().trim().length() == 0 ) {
                    coupon.setLineText1(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_COUPON_DEFAULT_LINETEXT);
                }

            }
        }
        logger.debug("<< Complete the Coupon Specification Price Modification. ");
    }

    /**
     * This is the function to be responsible for checking the price tag for zero on the Coupons both in service reminders and in coupons.
     * @param listOfCouponReminders
     */
    public boolean checkCouponPriceZero(String priceValue) {
        logger.debug(">> Start Checking zero for coupon price");

        boolean priceZero = false;
        if (priceValue != null && priceValue.trim().length() != 0) {
            priceValue = priceValue.trim();
            priceValue = priceValue.replaceAll(" ", "");

            try {
                double price = Double.parseDouble(priceValue);
                if (price == 0.0) {
                    priceZero = true;
                }
            } catch (NullPointerException nullPntrExc) {
                logger.error("An Exception has occured, while converting the Price {} to Double", priceValue, nullPntrExc);
                nullPntrExc.printStackTrace();
            } catch (Exception ex) {
                logger.error("An Exception has occured, while converting the Price {} to Double", priceValue, ex);
                ex.printStackTrace();
            }
        }
        logger.debug("<< Exits from checkCouponPriceZero().");
        return priceZero;
    }

    /*
     * This is the function for leftHeader/rightHeader which is responsible for setting left and right header.
     * added on 11/11/2011
     * @param list
     */
    public void setleftRightHeaderForIphone(CouponRemindersDataDetailVO couponRemindersDataDetailVO){

        logger.debug(">> Enters the setleftRightHeader = {}", couponRemindersDataDetailVO);
        
        StringBuilder leftHeader = new StringBuilder();
        StringBuilder rightHeader = new StringBuilder();
        
        String couponHeader1 = null;
        String couponHeader2 = null;
        int countLeftHeader = 0,countRightHeader = 0;
        // Starts leftHeader checking
        couponHeader1 = couponRemindersDataDetailVO.getCouponHeader1();
        couponHeader2 = couponRemindersDataDetailVO.getCouponHeader2();
        if(couponRemindersDataDetailVO.getLeftheader() != null && couponRemindersDataDetailVO.getLeftheader().getHeaderText() !=null){
            List<CouponRemindersDataDetailLineTextVO> leftHeaderTextList = couponRemindersDataDetailVO.getLeftheader().getHeaderText().getText();

            if(leftHeaderTextList != null && !leftHeaderTextList.isEmpty()){
                for(CouponRemindersDataDetailLineTextVO lineTextVO : leftHeaderTextList)
                {
                    leftHeader.append(lineTextVO.getText()).append(" ");
                    countLeftHeader++;
                    if(countLeftHeader == 2)
                        break;
                }
            }
            couponRemindersDataDetailVO.setCouponHeader1(leftHeader.toString());
            couponRemindersDataDetailVO.setCouponHeader2("");
        }else{
            couponRemindersDataDetailVO.setCouponHeader1(couponHeader1);
            couponRemindersDataDetailVO.setCouponHeader2(couponHeader2);
        }// Ends leftHeader checking

        // Starts rightHeader checking
        
        if(couponRemindersDataDetailVO.getRightheader() !=null && couponRemindersDataDetailVO.getRightheader().getHeaderText() != null){
            List<CouponRemindersDataDetailLineTextVO> rightHeaderTextList = couponRemindersDataDetailVO.getRightheader().getHeaderText().getText();
            
            if(rightHeaderTextList != null && !rightHeaderTextList.isEmpty()){
                    
                    for(CouponRemindersDataDetailLineTextVO lineTextVO : rightHeaderTextList){

                        if(countRightHeader == 0 && lineTextVO.getText().trim().length()<=appConstants.DEALERCOUPON_SERVICEREMINDER_PRICE_TEXT_LENGTH){
                            //specificationPrice = lineTextVO.getText().toString();
                            couponRemindersDataDetailVO.setSpecificationPrice(lineTextVO.getText());
                            rightHeader.append(lineTextVO.getText().trim()).append(" ");
                        }else if(countRightHeader == 0 && lineTextVO.getText().trim().length()>=appConstants.DEALERCOUPON_SERVICEREMINDER_PRICE_TEXT_LENGTH){
                            couponRemindersDataDetailVO.setSpecificationPrice(appConstants.DEALER_COUPON_PRICE_SAVE);
                            rightHeader.append(lineTextVO.getText().trim()).append(" ");
                        }else {
                            rightHeader.append(lineTextVO.getText().trim()).append(" ");
                        }
                        couponRemindersDataDetailVO.setLineText1(rightHeader.toString().trim());
                        countRightHeader++;
                        if(countRightHeader == 2)
                            break;
                    }
            }            
        }else{
            couponRemindersDataDetailVO.setSpecificationPrice(appConstants.DEALER_COUPON_PRICE_SAVE);
            couponRemindersDataDetailVO.setLineText1(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_COUPON_DEFAULT_LINETEXT);
        }// Ends rightHeader checking
        logger.debug("<< Exits the setleftRightHeader {} ", leftHeader, rightHeader);
    }

    /*
     * This is the function for leftHeader/rightHeader which is responsible for setting left and right header.
     * added on 19/01/2012
     * @param list
     */
    public void setleftRightHeaderForAndroid(CouponRemindersDataDetailVO couponRemindersDataDetailVO){

        logger.debug(">> Enters the setleftRightHeaderForAndroid = {}", couponRemindersDataDetailVO);

        StringBuilder leftHeader = new StringBuilder();
        StringBuilder rightHeader = new StringBuilder();

        String couponHeader1 = null;
        String couponHeader2 = null;
        int countLeftHeader = 0,countRightHeader = 0;
        // Starts leftHeader checking
        couponHeader1 = couponRemindersDataDetailVO.getCouponHeader1();
        couponHeader2 = couponRemindersDataDetailVO.getCouponHeader2();
        if(couponRemindersDataDetailVO.getLeftheader() != null && couponRemindersDataDetailVO.getLeftheader().getHeaderText() !=null){
            List<CouponRemindersDataDetailLineTextVO> leftHeaderTextList = couponRemindersDataDetailVO.getLeftheader().getHeaderText().getText();

            if(leftHeaderTextList != null && !leftHeaderTextList.isEmpty()){
                for(CouponRemindersDataDetailLineTextVO lineTextVO : leftHeaderTextList){
                    //if(countLeftHeader == 0){
                        leftHeader.append(lineTextVO.getText()).append(" ");
                        countLeftHeader++;

                        if(countLeftHeader == 2)
                            break;
                    //}

                }
            }
            couponRemindersDataDetailVO.setCouponHeader1(leftHeader.toString());
            couponRemindersDataDetailVO.setCouponHeader2("");
        }else{
            couponRemindersDataDetailVO.setCouponHeader1(couponHeader1);
            couponRemindersDataDetailVO.setCouponHeader2(couponHeader2);
        }// Ends leftHeader checking

        // Starts rightHeader checking

        if(couponRemindersDataDetailVO.getRightheader() !=null && couponRemindersDataDetailVO.getRightheader().getHeaderText() != null){
            List<CouponRemindersDataDetailLineTextVO> rightHeaderTextList = couponRemindersDataDetailVO.getRightheader().getHeaderText().getText();

            if(rightHeaderTextList != null && !rightHeaderTextList.isEmpty()){

                    for(CouponRemindersDataDetailLineTextVO lineTextVO : rightHeaderTextList){

                        rightHeader.append(lineTextVO.getText().trim()).append(" ");
                        if(countRightHeader == 2)
                            break;
                    }
                    //rightHeader.append("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
                    if(rightHeader.toString().trim().length()<=appConstants.DEALERCOUPON_SERVICEREMINDER_ANDROID_PRICE_TEXT_LENGTH){
                       couponRemindersDataDetailVO.setSpecificationPrice(rightHeader.toString().trim());
                    }else{
                        couponRemindersDataDetailVO.setSpecificationPrice(appConstants.DEALER_COUPON_PRICE_SAVE);
                        couponRemindersDataDetailVO.setLineText1(rightHeader.toString().trim());
                    }
            }
        }else{

            if(couponRemindersDataDetailVO.getLineText1()== null && couponRemindersDataDetailVO.getLineText2() == null &&
               couponRemindersDataDetailVO.getLineText3()== null && couponRemindersDataDetailVO.getLineText4() == null &&
               couponRemindersDataDetailVO.getLineText5()== null && couponRemindersDataDetailVO.getLineText6() == null &&
               couponRemindersDataDetailVO.getLineText7()== null && couponRemindersDataDetailVO.getLineText8() == null &&
               couponRemindersDataDetailVO.getLineText9()== null && couponRemindersDataDetailVO.getLineText10() == null &&
               couponRemindersDataDetailVO.getLineText11()== null && couponRemindersDataDetailVO.getLineText12() == null &&
               couponRemindersDataDetailVO.getLineText13()== null && couponRemindersDataDetailVO.getLineText14() == null &&
               couponRemindersDataDetailVO.getLineText15()== null && couponRemindersDataDetailVO.getLineText16() == null &&
               couponRemindersDataDetailVO.getLineText17()== null && couponRemindersDataDetailVO.getLineText18() == null &&
               couponRemindersDataDetailVO.getLineText19()== null && couponRemindersDataDetailVO.getLineText20() == null &&
               couponRemindersDataDetailVO.getLineText21()== null && couponRemindersDataDetailVO.getLineText22() == null &&
               couponRemindersDataDetailVO.getLineText23()== null && couponRemindersDataDetailVO.getLineText24() == null &&
               couponRemindersDataDetailVO.getLineText25() == null){

                couponRemindersDataDetailVO.setSpecificationPrice(appConstants.DEALER_COUPON_PRICE_SAVE);
                couponRemindersDataDetailVO.setLineText1(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_COUPON_DEFAULT_LINETEXT);
            }else{
                couponRemindersDataDetailVO.setSpecificationPrice(appConstants.DEALER_COUPON_PRICE_SAVE);
            }

        }// Ends rightHeader checking
        logger.debug("<< Exits the setleftRightHeaderForAndroid = {}", leftHeader, rightHeader);
    }

    /**
     * This is the function which will responsible for returning all the constant details from the constant table
     * @param module
     * @return constantDetailsResponseVO
     **/
    public ConstantDetailsResponseVO getConstant(String ... module){

        logger.debug(">> Entering the function getConstant() with = {}",this.getClass(), module);
        ConstantDetailsResponseVO constantDetailsResponseVO = new ConstantDetailsResponseVO();
        List<ConstantResponseVO> constantResponseVOList = null;
        ConstantResponseVO constantResponseVO = null;

        List<ConstantVO> constantVOList = null;
        ConstantVO constantVO = null;        
        boolean flag = false;
        boolean result = false;
        
        try{
                ConstantVO addingConstantVO = new ConstantVO();
                result = applicationService.getConstantByModuleName(appConstants.MMG_CONSTANT_MODULE_NAME_YEAR_MODEL);

                //if reslut is true, it means yearmodel record is available in the DB constant table
                //then it will set flag equals to true and then returns the yearmodel constant details
                //And if the result is false then it add the yearmodel data to the DB table first and then returns the yearmodel constant details.
                if(result){
                    flag = true;
                }else{
                    addingConstantVO.setName("Vehicle Constant");
                    addingConstantVO.setAddedby("Pankaj");
                    addingConstantVO.setValue("1.0");
                    addingConstantVO.setAddedDate(new Date());
                    addingConstantVO.setModulename("yearmodel");
                    result = applicationService.addConstantDetails(addingConstantVO);
                    flag = true;
                }
            
            if(flag == true){
                
                constantVO = applicationService.getConstantByModule(appConstants.MMG_CONSTANT_MODULE_NAME_YEAR_MODEL);
                if(constantVO != null){
                    //for(ConstantVO tempConstantVO : constantVOList){
                        constantResponseVOList = new ArrayList<ConstantResponseVO>();
                        constantResponseVO = new ConstantResponseVO();

                        constantResponseVO.setConstantvname(constantVO.getName());
                        constantResponseVO.setConstantvalue(constantVO.getValue());
                        constantResponseVO.setModulename(constantVO.getModulename());
                    //}
                    constantResponseVOList.add(constantResponseVO);
                }
            }
            constantDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            constantDetailsResponseVO.setConstantDetails(constantResponseVOList);
            
        }catch(Exception e){
            logger.error("An exception has occured while retrieving constant = {}", e);
            constantDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }
        logger.debug("<< Exiting the function getConstant() with = {}", constantDetailsResponseVO);
        return constantDetailsResponseVO;
    }
  /*public ConstantDetailsResponseVO getConstant(String... name)
  {
    ConstantDetailsResponseVO constantDetailsResponseVO = new ConstantDetailsResponseVO();
    List<ConstantResponseVO> constantResponseVOList = null;
    ConstantResponseVO constantResponseVO = null;
    List<ConstantVO> constantVOList = new ArrayList<ConstantVO>();
    try {
      constantVOList = this.applicationService.getAllConstantDetails();

      if(constantVOList != null){
          constantResponseVOList = new ArrayList<ConstantResponseVO>();
          for (ConstantVO tempConstantVO : constantVOList) {
            constantResponseVO = new ConstantResponseVO();
            constantResponseVO.setConstantvname(tempConstantVO.getName());
            constantResponseVO.setConstantvalue(tempConstantVO.getValue());
            constantResponseVO.setModulename(tempConstantVO.getModulename());
            constantResponseVOList.add(constantResponseVO);
          }

          constantDetailsResponseVO.setConstantDetails(constantResponseVOList);
          constantDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
        }
      }
    catch (Exception e)
    {
      logger.error("An exception has occured while retrieving constant {} ", e);
      constantDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
    }

    return constantDetailsResponseVO;
  }*/

    /**
     * This is the function which will responsible for returning all the constant details from the constant table
     * @param userConstantDetailsRequestVO
     * @return constantDetailsResponseVO
     **/
    public ConstantDetailsResponseVO getConstantAndDeviceDetails(UserConstantDetailsRequestVO userConstantDetailsRequestVO){

        logger.debug(">> Entering the function getConstantAndDeviceDetails() with = {}",this.getClass(), userConstantDetailsRequestVO);
        ConstantDetailsResponseVO constantDetailsResponseVO = new ConstantDetailsResponseVO();
        List<ConstantResponseVO> constantResponseVOList = null;
        ConstantResponseVO constantResponseVO = null;
        VehicleScanTimeOutResponseVO scanTimeOutResponseVO = new VehicleScanTimeOutResponseVO();
        List<ConstantVO> constantVOList = null;
        ConstantVO constantVO = null;        
        boolean flag = false;
        boolean result = false;
        logger.debug(">> The App Version : = {}", userConstantDetailsRequestVO.getAppVersion());
        logger.debug(">> The Device Name : = {}",userConstantDetailsRequestVO.getDeviceName());
        logger.debug(">> The Device OS Version : = {}",userConstantDetailsRequestVO.getDeviceOSVersion());

        try{
                ConstantVO addingConstantVO = new ConstantVO();
                result = applicationService.getConstantByModuleName(appConstants.MMG_CONSTANT_MODULE_NAME_YEAR_MODEL);

                //if reslut is true, it means yearmodel record is available in the DB constant table
                //then it will set flag equals to true and then returns the yearmodel constant details
                //And if the result is false then it add the yearmodel data to the DB table first and then returns the yearmodel constant details.
                if(result){
                    flag = true;
                }else{
                    addingConstantVO.setName(appConstants.MMG_APP_CONSTANT_NAME);
                    addingConstantVO.setAddedby(appConstants.MMG_APP_CONSTANT_ADDEDBY_NAME);
                    addingConstantVO.setValue(appConstants.MMG_APP_CONSTANT_VALUE);
                    addingConstantVO.setAddedDate(new Date());
                    addingConstantVO.setModulename(appConstants.MMG_APP_CONSTANT_MODULE_NAME);
                    result = applicationService.addConstantDetails(addingConstantVO);
                    flag = true;
                }

            if(flag == true){

                constantVO = applicationService.getConstantByModule(appConstants.MMG_CONSTANT_MODULE_NAME_YEAR_MODEL);
                if(constantVO != null){
                    //for(ConstantVO tempConstantVO : constantVOList){
                        constantResponseVOList = new ArrayList<ConstantResponseVO>();
                        constantResponseVO = new ConstantResponseVO();

                        constantResponseVO.setConstantvname(constantVO.getName());
                        constantResponseVO.setConstantvalue(constantVO.getValue());
                        constantResponseVO.setModulename(constantVO.getModulename());
                    //}
                    constantResponseVOList.add(constantResponseVO);
                }
            }
            scanTimeOutResponseVO.setScantimeout(appConstants.MMG_SCAN_TIME_OUT_LIMIT);
            scanTimeOutResponseVO.setScanmessage(appConstants.MMG_SCAN_TIME_OUT_MESSAGE);

            constantDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            constantDetailsResponseVO.setConstantDetails(constantResponseVOList);
            constantDetailsResponseVO.setScanDetails(scanTimeOutResponseVO);

        }catch(Exception e){
            logger.error("An exception has occured while retrieving constant = {}", e);
            constantDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        }
        logger.debug("<< Exiting the function getConstantAndDeviceDetails() with = {}", constantDetailsResponseVO);
        return constantDetailsResponseVO;
    }
    
    
	private HashMap<Integer,String> getVehicleTrims() {
		
		HashMap<Integer,String> map=new HashMap<Integer,String>();

		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		String query="select t1.vehicle_id,GROUP_CONCAT(t2.trim_name SEPARATOR '~') as trims from vehicle_trims t1 inner join trims t2 on t1.trim_id=t2.trims_ident group by t1.vehicle_id";
		stmt=conn.prepareStatement(query);
		
		resultSet=stmt.executeQuery();
		
			while(resultSet.next())
			{
			map.put(resultSet.getInt(1), resultSet.getString(2));	
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getVehicleTrims > "+e);
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

		return map;
	}

	@Override
	public ServiceUploadResponseVO getUploadServiceRecordDetails(String vin,
			String custId) {
		ServiceUploadResponseVO resp=new ServiceUploadResponseVO();
		
		try{
			RestServiceHistoryInfoVO restServiceRecord=	restServiceUpload.getServiceInfo(vin, custId);
			
			if(restServiceRecord!=null && restServiceRecord.getServiceRecords()!=null)
			{
				
			ArrayList<ServiceRecordResponseVO> serviceRecords=new ArrayList<ServiceRecordResponseVO>();
			for(RestServiceRecordVO temp:restServiceRecord.getServiceRecords())
			{
				ServiceRecordDateVO serviceDate=new ServiceRecordDateVO(temp.getServiceDate().getYear(),temp.getServiceDate().getMonth(),temp.getServiceDate().getDay());
				List<String> comments=temp.getComments();
				ServiceRecordResponseVO vo=new ServiceRecordResponseVO(serviceDate,comments);
				vo.setAmount(temp.getAmount());
				vo.setMileage(temp.getMileage());
				vo.setMusaRefId(temp.getMusaRefId());
				vo.setOpCode(temp.getOpCode());
				vo.setOwnerEntered(temp.getOwnerEntered());
				vo.setServiceFacility(temp.getServiceFacility());
				vo.setServiceType(temp.getServiceType());
				
				//Add to list
				serviceRecords.add(vo);
			}
			resp=new ServiceUploadResponseVO(restServiceRecord.getVin(), restServiceRecord.getCustId(), serviceRecords);
			
			}
			if(restServiceRecord!=null && restServiceRecord.getError()!=null && !restServiceRecord.getError().isEmpty())
			{
			resp.setStatus(restServiceRecord.getStatus());
			resp.setError(restServiceRecord.getError());
			}else{
				resp.setStatus(appConstants.STATUS_SUCCESS);	
			}
			
		}
		catch(Exception ex)
		{
			resp=new ServiceUploadResponseVO();
			resp.setError(ex.getMessage());
			resp.setStatus(appConstants.STATUS_FAILURE);
			logger.error(" Problem -  getUploadServiceRecordDetails > "+ex);
		}
		
		
		return resp;
	}
	

	@Override
	public ServiceUploadResponseVO uploadServiceRecordDetails(
			ServiceUploadRequestVO serviceRecordReq) {
		
		ServiceUploadResponseVO resp=new ServiceUploadResponseVO();
		try{
			
			RestServiceHistoryInfoVO restServiceRecord=	restServiceUpload.addServiceRecord(serviceRecordReq);
			
			if(restServiceRecord.getServiceRecords()!=null)
			{
				
			ArrayList<ServiceRecordResponseVO> serviceRecords=new ArrayList<ServiceRecordResponseVO>();
			for(RestServiceRecordVO temp:restServiceRecord.getServiceRecords())
			{
				ServiceRecordDateVO serviceDate=new ServiceRecordDateVO(temp.getServiceDate().getYear(),temp.getServiceDate().getMonth(),temp.getServiceDate().getDay());
				List<String> comments=temp.getComments();
				ServiceRecordResponseVO vo=new ServiceRecordResponseVO(serviceDate,comments);
				vo.setAmount(temp.getAmount());
				vo.setMileage(temp.getMileage());
				vo.setMusaRefId(temp.getMusaRefId());
				vo.setOpCode(temp.getOpCode());
				vo.setOwnerEntered(temp.getOwnerEntered());
				vo.setServiceFacility(temp.getServiceFacility());
				vo.setServiceType(temp.getServiceType());
				
				//Add to list
				serviceRecords.add(vo);
			}
			resp=new ServiceUploadResponseVO(restServiceRecord.getVin(), restServiceRecord.getCustId(), serviceRecords);
			
			}
			resp.setStatus(restServiceRecord.getStatus());
			resp.setError(restServiceRecord.getError());
			
		}
		catch(Exception ex)
		{
			resp=new ServiceUploadResponseVO();
			resp.setError(ex.getMessage());
			resp.setStatus(appConstants.STATUS_FAILURE);
			logger.error(" Problem -  uploadServiceRecordDetails > "+ex);
		}
		
		
		return resp;
	}
	

	@Override
	public ServiceUploadResponseVO delUploadedServiceRecord(String refId, String vin) {
		ServiceUploadResponseVO resp=new ServiceUploadResponseVO();
		
		try{
			ServiceUploadRequestVO serviceRecordReq=new ServiceUploadRequestVO();
			serviceRecordReq.setMusaRefId(refId);
			serviceRecordReq.setVin(vin);
			
			RestServiceHistoryInfoVO restServiceRecord=	restServiceUpload.deleteServiceRecord(serviceRecordReq);
			
			if(restServiceRecord.getServiceRecords()!=null)
			{
				
			ArrayList<ServiceRecordResponseVO> serviceRecords=new ArrayList<ServiceRecordResponseVO>();
			for(RestServiceRecordVO temp:restServiceRecord.getServiceRecords())
			{
				ServiceRecordDateVO serviceDate=new ServiceRecordDateVO(temp.getServiceDate().getYear(),temp.getServiceDate().getMonth(),temp.getServiceDate().getDay());
				List<String> comments=temp.getComments();
				ServiceRecordResponseVO vo=new ServiceRecordResponseVO(serviceDate,comments);
				vo.setAmount(temp.getAmount());
				vo.setMileage(temp.getMileage());
				vo.setMusaRefId(temp.getMusaRefId());
				vo.setOpCode(temp.getOpCode());
				vo.setOwnerEntered(temp.getOwnerEntered());
				vo.setServiceFacility(temp.getServiceFacility());
				vo.setServiceType(temp.getServiceType());
				
				//Add to list
				serviceRecords.add(vo);
			}
			resp=new ServiceUploadResponseVO(restServiceRecord.getVin(), restServiceRecord.getCustId(), serviceRecords);
			
			}
			resp.setStatus(restServiceRecord.getStatus());
			resp.setError(restServiceRecord.getError());
			
		}
		catch(Exception ex)
		{
			resp=new ServiceUploadResponseVO();
			resp.setError(ex.getMessage());
			resp.setStatus(appConstants.STATUS_FAILURE);
			logger.error(" Problem -  delUploadedServiceRecord > "+ex);
		}
		
		
		return resp;
	}

	@Override
	public ServiceUploadResponseVO updateServiceRecordDetails(
			ServiceUploadRequestVO serviceRecordReq) {
		ServiceUploadResponseVO resp=new ServiceUploadResponseVO();
		
		try{
		
			RestServiceHistoryInfoVO restServiceRecord=	restServiceUpload.updateServiceRecord(serviceRecordReq);
			
			if(restServiceRecord.getServiceRecords()!=null)
			{
				
			ArrayList<ServiceRecordResponseVO> serviceRecords=new ArrayList<ServiceRecordResponseVO>();
			for(RestServiceRecordVO temp:restServiceRecord.getServiceRecords())
			{
				ServiceRecordDateVO serviceDate=new ServiceRecordDateVO(temp.getServiceDate().getYear(),temp.getServiceDate().getMonth(),temp.getServiceDate().getDay());
				List<String> comments=temp.getComments();
				ServiceRecordResponseVO vo=new ServiceRecordResponseVO(serviceDate,comments);
				vo.setAmount(temp.getAmount());
				vo.setMileage(temp.getMileage());
				vo.setMusaRefId(temp.getMusaRefId());
				vo.setOpCode(temp.getOpCode());
				vo.setOwnerEntered(temp.getOwnerEntered());
				vo.setServiceFacility(temp.getServiceFacility());
				vo.setServiceType(temp.getServiceType());
				
				//Add to list
				serviceRecords.add(vo);
			}
			resp=new ServiceUploadResponseVO(restServiceRecord.getVin(), restServiceRecord.getCustId(), serviceRecords);
			
			}
			resp.setStatus(restServiceRecord.getStatus());
			resp.setError(restServiceRecord.getError());
			
		}
		catch(Exception ex)
		{
			resp=new ServiceUploadResponseVO();
			resp.setError(ex.getMessage());
			resp.setStatus(appConstants.STATUS_FAILURE);
			logger.error(" Problem -  updateServiceRecordDetails > "+ex);
		}
				
		return resp;
	}
    

	public String getVehicleTitle(String custId,String vin) {

		String title=null;
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		stmt=conn.prepareStatement("SELECT vin_vehicle_name FROM mmguservehicles where userid_ident in (SELECT user_ident FROM mmgusers where mmgservicecustid = ?) and custvin = ?");
		stmt.setString(1, custId);
		stmt.setString(2, vin);
		
		resultSet=stmt.executeQuery();

			if(resultSet.next())
			{
				title=resultSet.getString(1);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getVehicleTitle > "+e);

		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

	return title;
	
	}
    
}
