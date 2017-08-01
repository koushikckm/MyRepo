/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;


import com.mazdausa.mmg.web.client.request.vo.UserGetDetailRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserProfileRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserUpdateDetailsRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserUploadImageRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserWindowStickerRequestVO;
import com.mazdausa.mmg.web.client.response.vo.UserEgiftsDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserGetDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserProfileResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserUpdateDetailsResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserUploadImageResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehiclesDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserWindowStickerResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserServiceIFace;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This is the Controller which is responsible for performing User Profile related tasks e.g. Registration/Login/Forgot Password
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/user")
public class UserProfileController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
    @Autowired
    UserServiceIFace userService;
    @Autowired
    UserVehicleServiceIFace userVehicleService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/registration")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public UserProfileResponseVO performUserRegistration(UserProfileRequestVO userProfileRequestVO, @Context HttpServletRequest request
            ,@HeaderParam("guid") String guid) {

        logger.debug(" Entering {} performUserRegistration() with User Name = {}", userProfileRequestVO.getFname());
        UserProfileResponseVO userProfileResponseVO = null;

        // Do the Logging part here
        logger.debug(">> Entering performUserRegistration() with User Id = " + userProfileRequestVO.getFname());
        userProfileResponseVO = userService.performRegistration(userProfileRequestVO, request,guid);
        logger.debug("<< Exiting performUserRegistration() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserProfileResponseVO performLogin(UserProfileRequestVO userProfileRequestVO, @Context HttpServletRequest request,
            @HeaderParam("guid") String guid) {

        logger.debug(" Entering {} performLogin() with Credentials = {}", userProfileRequestVO.getData());
        UserProfileResponseVO userProfileResponseVO = null;
         // For the GUID TRIM the APNS Token coming from the client.
       /* if(userProfileRequestVO.getApnstoken()!=null)
         {
                String token=userProfileRequestVO.getApnstoken();
                try
                {
                    token=token.replaceAll(" ", "");
                    token=token.substring(1, token.length()-1);
                    userProfileRequestVO.setApnstoken(token);
                }
                catch(Exception ex)
                {
                    logger.error("An Excpetion occured, while parsing the GUID from the client. ",ex);
                }
         }*/

        // Do the Logging part here
        userProfileResponseVO = userService.performLogin(userProfileRequestVO, request,guid);
        logger.debug("<< Exiting performLogin() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }

    /**
     * This Function will implement the forgot password functionality
     * @param userProfileRequestVO
     * @param request
     * @return
     */
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userid}/forgotpassword")
    public UserProfileResponseVO performForgotPassword(@PathParam("userid") String userId, @Context HttpServletRequest request) {

        logger.debug(" Entering {} performForgotPassword() with UserID = {}", userId);
        UserProfileResponseVO userProfileResponseVO = null;


        // Do the Logging part here
        userProfileResponseVO = userService.performForgotPassword(userId);
        logger.debug("<< Exiting performForgotPassword() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }*/
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/forgotpassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserProfileResponseVO forgotPassword(UserProfileRequestVO userProfileRequestVO, @Context HttpServletRequest request) {

        logger.debug(" Entering {} forgotPassword() with request = {}", userProfileRequestVO);

        
        UserProfileResponseVO userProfileResponseVO = null;

        userProfileResponseVO = userService.forgotPassword(userProfileRequestVO);
        logger.debug("<< Exiting forgotPassword() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verifyresetpasswordlink/{encryptedVal}")
    public UserProfileResponseVO verifyResetPasswordLink(@PathParam("encryptedVal")  String encryptedVal, @Context HttpServletRequest request) throws ParseException {
    		
    	UserProfileResponseVO userProfileResponseVO = null;
    	logger.debug(" Entering {} verifyResetPasswordLink() with request = {}");
   	
   		userProfileResponseVO = userService.verifyResetPasswordLink(encryptedVal);
   		
   		return userProfileResponseVO;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updatepassword")
    public UserProfileResponseVO resetPassword(UserProfileRequestVO userProfileRequestVO, @Context HttpServletRequest request){
    	UserProfileResponseVO userProfileResponseVO = null;
    	logger.debug(" Entering {} resetPassword() with request = {}", userProfileRequestVO);
    	
        userProfileResponseVO = userService.updatePassword(userProfileRequestVO);
        logger.debug("<< Exiting resetPassword() with response ={}" + userProfileResponseVO);
		return userProfileResponseVO ;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userid}/vehicles")
    public UserVehiclesDetailResponseVO getUserVehicles(@PathParam("userid") String customerId, @Context HttpServletRequest request, @Context HttpHeaders headers) {

        logger.debug(" Entering {} getUserVehicles() with Customer ID = {}", customerId);

        UserVehiclesDetailResponseVO userVehicleDetailResponseVO = null;
        
        // Do the Logging part here

        userVehicleDetailResponseVO = userVehicleService.getUserVehicles(customerId, headers);

        logger.debug("<< Exiting getUserVehicles() with response ={}" + userVehicleDetailResponseVO);
        return userVehicleDetailResponseVO;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getUserEgifts/{userid}/{vin}")
    public UserEgiftsDetailResponseVO getUserEgifts(@PathParam("userid") String customerId, @PathParam("vin") String vin, @Context HttpServletRequest request) {
    	
    	logger.debug(" Entering {} getUserEgifts() with Customer ID = {} VIN = {}", customerId,vin);
    	UserEgiftsDetailResponseVO egiftDetailResponseVO = null;
    	
    	egiftDetailResponseVO = userVehicleService.getUserEgifts(customerId, vin);
    	
    	logger.debug("<< Exiting getUserVehicles() with response ={}" + egiftDetailResponseVO);
    	return egiftDetailResponseVO;
    }

    /**
     * This Function will provide all the user details
     * @param userDetailRequestVO
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/detail")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserGetDetailResponseVO getUserDetails(UserGetDetailRequestVO userDetailRequestVO, @Context HttpServletRequest request, @Context HttpHeaders headers) {

        logger.debug(" Entering {} getUserDetails() with mail id = {}", userDetailRequestVO);
        UserGetDetailResponseVO userDetailResponseVO = null;
        // Do the Logging part here

        userDetailResponseVO = userService.getUserDetails(userDetailRequestVO, headers);

        logger.debug("<< Exiting getUserDetails() with response ={}" + userDetailResponseVO);
        //  Change Added on 26 Sep 2011.
        if(userDetailResponseVO !=null)
            if(userDetailResponseVO.getUserdetails() !=null)
            userDetailResponseVO.getUserdetails().setCountry("");
        return userDetailResponseVO;
    }

    /**
     * This is the function to be invoked for updating the customer Details in the  System.
     * @param userUpdateDetailRequestVO
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userid}/updatedetails")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserUpdateDetailsResponseVO updateUserDetails(UserUpdateDetailsRequestVO userUpdateDetailRequestVO,
            @PathParam("userid") String userId,
            @Context HttpServletRequest request,
            @Context HttpHeaders headers) {

        logger.debug(" Entering {} updateUserDetails() with mail id = {}", this.getClass(), userUpdateDetailRequestVO.getEmail());
        UserUpdateDetailsResponseVO userUpdateDetailsResponseVO = null;
        userUpdateDetailRequestVO.setCustID(userId);

        //Changed By InterraIT:: SEP 25, 2012 - Revert Back:: SEP 28, 2012 
        //As per Klein Sule, ReturnedEmail value should be false (Which is default value).
        //If it is true, it is marking customer address as undeliverable.
        //userUpdateDetailRequestVO.setReturnedEmail("false");
        
        // Do the Logging part here

        userUpdateDetailsResponseVO = userService.updateUserDetails(userUpdateDetailRequestVO, headers);

        logger.debug("<< Exiting updateUserDetails() with response ={}" + userUpdateDetailsResponseVO);
        return userUpdateDetailsResponseVO;
    }
    
    /**
     * This is the function to be invoked for changing password in the  System.
     * @param userUpdateDetailRequestVO
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userid}/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserProfileResponseVO changePassword(UserProfileRequestVO userProfileRequestVO,
            @PathParam("userid") String userId,
            @Context HttpServletRequest request) {
    
    	logger.debug(" Entering {} changePassword() for user = {}", userId);
    	UserProfileResponseVO userProfileResponseVO = null;
        
    	userProfileRequestVO.setCustId(userId);
    	
        userProfileResponseVO = userService.performChangePassword(userProfileRequestVO);
        
        logger.debug("<< Exiting changePassword() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }
   
    
    /**
     * This is the function to be invoked for getting windows sticker image details.
     * @param mdlYear,carlineCode,extColorCode,mdlCode,width
     * @param request
     * @return
     */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/windowSticker/{mdlYear}/{carlineCode}/{extColorCode}/{width}/{mdlCode}")
    public UserWindowStickerResponseVO getWindowStickerImage(@PathParam("mdlYear") String mdlYear, @PathParam("carlineCode") String carlineCode, @PathParam("extColorCode") String extColorCode, 
    		@PathParam("width") String width, @PathParam("mdlCode") String mdlCode, @Context HttpServletRequest request) {
    	
    	logger.debug(" Entering {} getWindowSticker() with Parameters = {}", this.getClass(), new Object[]{mdlYear, carlineCode, extColorCode, mdlCode, width});    
    	UserWindowStickerResponseVO windowStickerResponseVO = null;
    	UserWindowStickerRequestVO windowStickerRequestVO = new UserWindowStickerRequestVO();
    	windowStickerRequestVO.setMdlYear(mdlYear);
    	windowStickerRequestVO.setCarlineCode(carlineCode);
    	windowStickerRequestVO.setExtColorCode(extColorCode);
    	windowStickerRequestVO.setWidth(width);
    	windowStickerRequestVO.setMdlCode(mdlCode);
    	
    	windowStickerResponseVO = userVehicleService.getWindowStickerImage(windowStickerRequestVO);
    	
    	logger.debug("<< Exiting getWindowSticker() with response ={}" +windowStickerResponseVO );
    	return windowStickerResponseVO;
    }
    
   
   
    
    /**
     * This is the function updates the vehicle name details for the vin.
     * @param vin
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/carline")
    @Consumes(value = {MediaType.APPLICATION_JSON})
    public UserProfileResponseVO addOrUpdateVinCarlineName(UserProfileRequestVO userProfileRequestVO, @Context HttpServletRequest request) {

        logger.debug(" Entering {} addOrUpdateVinCarlineName() with request {} ", userProfileRequestVO);
        UserProfileResponseVO userProfileResponseVO = null;

        userProfileResponseVO = userService.addOrUpdateVinCarlineName(userProfileRequestVO, request);
        
        logger.debug("<< Exiting addOrUpdateVinCarlineName() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }
    
    /**
     * This is the function updates the vehicle name details for the vin.
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/carline/{vin}")
    public UserProfileResponseVO getVinCarlineName(@PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getVinCarlineName() with vin {} ", vin);
        UserProfileResponseVO userProfileResponseVO = null;

        userProfileResponseVO = userService.getVinCarlineName(vin);
        
        logger.debug("<< Exiting getVinCarlineName() with response ={}" + userProfileResponseVO);
        return userProfileResponseVO;
    }
    
    @Path("/userImage")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)    
    @Produces(MediaType.APPLICATION_JSON)    
    public UserUploadImageResponseVO uploadUserImageDetails(@FormDataParam("file") InputStream uploadedInputStream, 
    		@FormDataParam("file") FormDataContentDisposition fileDetail, 
    		@FormDataParam("vin") String vin, @FormDataParam("userId") String userId,
    		@FormDataParam("custId") String custId, @Context HttpServletRequest request) {
	
    	logger.debug(">> Entering uploadUserImageDetails() with vin ={} custId={} " +vin +" " +custId );
    	UserUploadImageResponseVO userUploadImageResponseVO = null;
    	UserUploadImageRequestVO uploadImageRequestVO = new UserUploadImageRequestVO();
    	uploadImageRequestVO.setUserId(userId);
    	uploadImageRequestVO.setCustomerId(custId);
    	uploadImageRequestVO.setVin(vin);
    	
    	userUploadImageResponseVO = userVehicleService.uploadUserImageDetails(uploadImageRequestVO, uploadedInputStream, fileDetail);
    	
    	logger.debug("<< Exiting uploadUserImageDetails() with response ={}" +userUploadImageResponseVO );
        return userUploadImageResponseVO; 
    }
    
    
    
    /**
     * This is the function to be invoked for fetching user image details.
     * @param vin,dealerId
     * @param request
     * @return
     */
    @Path("/userImageDetail/{custId}/{vin}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public UserUploadImageResponseVO fetchUserImageDetails(@PathParam("custId") String custId, @PathParam("vin") String vin, @Context HttpServletRequest request) {
    	
    	logger.debug(">> Entering fetchUserImageDetails() with vin ={} custId={} " +vin +" " +custId );
    	UserUploadImageResponseVO userUploadImageResponse = null;
    	UserUploadImageRequestVO uploadImageRequestVO = new UserUploadImageRequestVO();
    	
    	uploadImageRequestVO.setCustomerId(custId);
    	uploadImageRequestVO.setVin(vin);
    	
    	userUploadImageResponse = userVehicleService.fetchUserImageDetails(uploadImageRequestVO);
    	   	
    	logger.debug("<< Exiting fetchUserImageDetails() with response ={}" +userUploadImageResponse );
    	return userUploadImageResponse;
    }
    
    @Path("/userImage/{custId}/{vin}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)    
    public Response fetchUserImage(@PathParam("custId") String custId, @PathParam("vin") String vin, @Context HttpServletRequest request) {
    	
    	logger.debug(">> Entering fetchUserImageDetails() with vin ={} custId={} " +vin +" " +custId );
    	UserUploadImageResponseVO userUploadImageResponse = null;
    	UserUploadImageRequestVO uploadImageRequestVO = new UserUploadImageRequestVO();
    	String fileLocation = null;
    	
    	uploadImageRequestVO.setCustomerId(custId);
    	uploadImageRequestVO.setVin(vin);
    	
    	userUploadImageResponse = userVehicleService.fetchUserImageDetails(uploadImageRequestVO);
    	
    	if(userUploadImageResponse.getStatus().equalsIgnoreCase("success")){
    		//fileLocation = "\\\\mnacpiw04\\IWServer\\default\\main\\Mazda_Apps\\Department\\Mkt\\mymazdagarage\\WORKAREA\\usr\\"+custId+"_"+vin+".jpeg";
    		fileLocation = userUploadImageResponse.getImagePath();

    		File file = new File(fileLocation);
    		ResponseBuilder response = Response.ok((Object) file);
    		response.header("Content-Disposition", "attachment; filename="+custId+"_"+vin+".jpeg");
    		return response.build();
    	}
    	else{
    		return null;
    	}
    		
    }
    
    /**
     * This is the function to be invoked for deleting user image details.
     * @param vin,dealerId
     * @param request
     * @return
     */
    
    @Path("/userImage/{custId}/{vin}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)   
    @Consumes(MediaType.APPLICATION_JSON)
    public UserUploadImageResponseVO deleteUserImageDetails(UserUploadImageRequestVO userUploadImageRequestVO, @PathParam("custId") String custId, @PathParam("vin") String vin, @Context HttpServletRequest request) {
    	
    	logger.debug(">> Entering deleteUserImageDetails() with vin ={} userId={} " +vin +custId );
    	UserUploadImageResponseVO userUploadImageResponse = null;
    	
    	userUploadImageRequestVO.setCustomerId(custId);
    	userUploadImageRequestVO.setVin(vin);
    	
    	userUploadImageResponse = userVehicleService.deleteUserImageDetails(userUploadImageRequestVO);
    	
    	logger.debug("<< Exiting deleteUserImageDetails() with response ={}" +userUploadImageResponse );
    	
    	return userUploadImageResponse;
    }
}
