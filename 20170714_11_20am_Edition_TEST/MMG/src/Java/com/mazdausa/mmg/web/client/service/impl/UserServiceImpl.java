/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.service.impl;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.session.vo.ClientSessionVO;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.business.iface.ApplicationBusIFace;
import com.mazdausa.mmg.business.iface.UserBusIFace;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import com.mazdausa.mmg.db.vo.UserVO;
import com.mazdausa.mmg.db.vo.UserVehicleVO;
import com.mazdausa.mmg.service.rest.iface.RestUserTaskIFace;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailsUpdateRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserTaskRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.UserDetailRequestParametersVO;
import com.mazdausa.mmg.service.rest.request.vo.UserDetailsUpdateVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailsUpdateResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserTaskResponseVO;
import com.mazdausa.mmg.service.soap.iface.UserWebServiceIFace;
import com.mazdausa.mmg.utilities.MMGUtilities;
import com.mazdausa.mmg.web.client.request.vo.UserGetDetailRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserProfileRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserUpdateDetailsRequestVO;
import com.mazdausa.mmg.web.client.response.vo.UserCredentialsDataVO;
import com.mazdausa.mmg.web.client.response.vo.UserGetDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserProfileResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserUpdateDetailsResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserServiceIFace;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * This is an service layer implementation of User Service Interface.
 * @author PankajB
 */
@Service
public class UserServiceImpl implements UserServiceIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RestUserTaskIFace userTaskService;
    @Autowired
    private UserWebServiceIFace userWebService;
    @Autowired
    ApplicationBusIFace applicationService;
    @Autowired
    UserBusIFace userService;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    EmmUtilities emmUtilities;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DataSource musadataSource;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    UserBusIFace userBusService;
    
    private static final String PASSWORD_PATTERN  = "[a-zA-Z0-9@*$.!%\\n\\t]{4,15}";

    /**
     * This is the function that will perform the user registration.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO performRegistration(UserProfileRequestVO userProfileRequestVO, HttpServletRequest request, String guid) {
        logger.debug(">> Entering {} performRegistration() with User ID = {}", this.getClass(), userProfileRequestVO.getFname() + " " + userProfileRequestVO.getLname());

        RestUserTaskRequestVO userTaskRequestVO = new RestUserTaskRequestVO();
        RestUserTaskResponseVO userTaskResponseVO = null;
        UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
        long currentTime = System.currentTimeMillis();

        try {
            logger.debug("Credentials is : {} ", userProfileRequestVO.getData());
            UserCredentialsDataVO userCredentialsVO = (UserCredentialsDataVO) MMGUtilities.objMapper.readValue(userProfileRequestVO.getData(), UserCredentialsDataVO.class);
            userTaskRequestVO.setEmail(userCredentialsVO.getUserid());  // Which is the EMAIL ID
            userTaskRequestVO.setEmailConfirm(userCredentialsVO.getUserid());  // Which is the EMAIL ID
            String userAgentOfDevice = request.getHeader("User-Agent");  // Identify the User Agent.
            String password = emmUtilities.getDecryptedData(userCredentialsVO.getPassword(), userAgentOfDevice);
            //String password = emmUtilities.getDecryptedData(userCredentialsVO.getPassword(), "");
            logger.debug("Decrypted password is {} ", password);
            userTaskRequestVO.setPassword(password);
            userTaskRequestVO.setPasswordConfirm(password);
            userTaskRequestVO.setCellNumber(userCredentialsVO.getMobileno());

            // Set the Other components from the RequestVO.
            userTaskRequestVO.setCity(userProfileRequestVO.getCity());
            userTaskRequestVO.setFirstName(userProfileRequestVO.getFname());
            userTaskRequestVO.setLastName(userProfileRequestVO.getLname());
            userTaskRequestVO.setMileage(userProfileRequestVO.getMileage());
            userTaskRequestVO.setState(userProfileRequestVO.getState());
            userTaskRequestVO.setStreetAddress(userProfileRequestVO.getStreet());
            userTaskRequestVO.setVin(userProfileRequestVO.getVin());
            userTaskRequestVO.setZip(userProfileRequestVO.getZip());
            userTaskRequestVO.setModelCode(userProfileRequestVO.getModelcode());
            userTaskRequestVO.setModelYear(userProfileRequestVO.getModelyear());

            userTaskResponseVO = userTaskService.registerUser(userTaskRequestVO);

            if (userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_OK)) // It Means the Request is Successful
            {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
                userProfileResponseVO.setRegistered(ApplicationConstants.YES);
                userProfileResponseVO.setRegistrationTime(String.valueOf(currentTime));

                if (userTaskResponseVO.getSuccessResponse() != null) {
                    userProfileResponseVO.setCustomerId(userTaskResponseVO.getSuccessResponse().getValue()); // Setting the Customer ID.
                    userProfileResponseVO.setDescription(appConstants.CLIENT_MESSAGE_REGISTRATION_SUCCESS);

                    // Add or Update teh user details.
                    addOrUpdateCustomer(userProfileResponseVO, userProfileRequestVO, userCredentialsVO, request, guid);


                } else {
                    logger.debug("Customer ID is not available at the time of registration. ");
                    userProfileResponseVO.setDescription(appConstants.CLIENT_NO_MESSAGE_REGISTRATION_SUCCESS);

                }


                // If it has been done successfully then also insert a record in the Database. DO THE CODE HERE.

            } else if (userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_VALIDATIONERROR)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_SYSTEMERROR)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_EMAILNOTFOUND)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_EMAILEXISTS)) // It Means a  Validation Error has occured.
            {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userProfileResponseVO.setDescription(userTaskResponseVO.getStatus() + ":  " + userTaskResponseVO.getValue());
                userProfileResponseVO.setRegistered(ApplicationConstants.NO);
            } else {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userProfileResponseVO.setRegistered(ApplicationConstants.NO);
                if (userTaskResponseVO != null) {
                    userProfileResponseVO.setDescription(userTaskResponseVO.getStatus());
                }
            }


        } catch (IOException rEx) {
            logger.error("An IO Exception has been occured, while parsing JSON Credentials data from the request. ", rEx);
        } catch (RuntimeException rEx) {
            logger.error("An RunTime Exception has been occured, while parsing JSON Credentials data from the request. ", rEx);
        }
        logger.debug("<< Exiting {} performRegistration() with result={} ", userProfileResponseVO);
        return userProfileResponseVO;
    }

    /**
     * This Function will perform the Login and check whether the user credentials that are passed are right or not.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO performLogin(UserProfileRequestVO userProfileRequestVO, HttpServletRequest request, String guid) {
        logger.debug(">> Entering {} performLogin() with Crdentials = {}", this.getClass(), userProfileRequestVO.getData());

        RestUserTaskRequestVO userTaskRequestVO = new RestUserTaskRequestVO();
        RestUserTaskResponseVO userTaskResponseVO = null;
        UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();

        try {
            logger.debug("Credentials is : {} ", userProfileRequestVO.getData());
            UserCredentialsDataVO userCredentialsVO = (UserCredentialsDataVO) MMGUtilities.objMapper.readValue(userProfileRequestVO.getData(), UserCredentialsDataVO.class);
            userTaskRequestVO.setEmail(userCredentialsVO.getUserid());  // Which is the EMAIL ID
            String userAgentOfDevice = request.getHeader("User-Agent");  // Identify the User Agent.
            String password = emmUtilities.getDecryptedData(userCredentialsVO.getPassword(), userAgentOfDevice);
	    //String password = emmUtilities.getDecryptedData(userCredentialsVO.getPassword(), "");
            logger.debug("DEcryptd password in login is {} ", password);
            userTaskRequestVO.setPassword(password);

            userTaskResponseVO = userTaskService.loginUser(userTaskRequestVO);

            if (userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_OK)) // It Means the Request is Successful
            {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
                logger.debug("Success Response Object is {} ", userTaskResponseVO.getSuccessResponse());


                if (userTaskResponseVO.getSuccessResponse() != null) {
                    userProfileResponseVO.setCustomerId(userTaskResponseVO.getSuccessResponse().getValue()); // Setting the Customer ID.
                    userProfileResponseVO.setDescription(appConstants.CLIENT_MESSAGE_LOGIN_SUCCESS);
                    userProfileResponseVO.setAuthenticated(ApplicationConstants.YES);
                    addOrUpdateCustomer(userProfileResponseVO, userProfileRequestVO, userCredentialsVO, request, guid);

                }


            } else if (userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_VALIDATIONERROR)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_SYSTEMERROR)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_EMAILNOTFOUND)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_LOGINFAILED)) // It Means an Error has occured.
            {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userProfileResponseVO.setDescription(userTaskResponseVO.getStatus() + " : " + appConstants.CLIENT_MESSAGE_LOGIN_FAILURE);
                userProfileResponseVO.setAuthenticated(ApplicationConstants.NO);

            } else {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userProfileResponseVO.setAuthenticated(ApplicationConstants.NO);
                if (userTaskResponseVO != null) {
                    userProfileResponseVO.setDescription(userTaskResponseVO.getStatus());
                }

            }


        } catch (IOException rEx) {
            logger.error("An IO Exception has been occured, while parsing JSON Credentials data from the request. ", rEx);
        } catch (RuntimeException rEx) {
            logger.error("An RunTime Exception has been occured, while Executing login request. ", rEx);
        }
        logger.debug("<< Exiting {} performLogin() with result={} ", userProfileResponseVO);
        return userProfileResponseVO;


    }

    /**
     * This Function will perform the Forgot Password Function.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO performForgotPassword(String userId) {
        logger.debug(">> Entering {} performForgotPassword() with UserID = {}", this.getClass(), userId);

        RestUserTaskRequestVO userTaskRequestVO = new RestUserTaskRequestVO();
        RestUserTaskResponseVO userTaskResponseVO = null;
        UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();

        try {

            userTaskRequestVO.setEmail(userId);  // Which is the EMAIL ID

            userTaskResponseVO = userTaskService.invokeForgotPassword(userTaskRequestVO);

            if (userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_OK)) // It Means the Request is Successful
            {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);


                if (userTaskResponseVO.getSuccessResponse() != null) {
                    userProfileResponseVO.setDescription(appConstants.CLIENT_MESSAGE_LOGIN_SUCCESS);
                }



            } else if (userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_VALIDATIONERROR)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_SYSTEMERROR)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_EMAILNOTFOUND)
                    || userTaskResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.RESULT_STATUS_LOGINFAILED)) // It Means an Error has occured.
            {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userProfileResponseVO.setDescription(userTaskResponseVO.getStatus() + " : " + userTaskResponseVO.getValue());


            } else {
                userProfileResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                if (userTaskResponseVO != null) {
                    userProfileResponseVO.setDescription(userTaskResponseVO.getStatus());
                }
            }


        } catch (RuntimeException rEx) {
            logger.error("An RunTime Exception has been occured, while parsing JSON Credentials data from the request. ", rEx);
        }
        logger.debug("<< Exiting {} performForgotPassword() with result={} ", userProfileResponseVO);
        return userProfileResponseVO;
    }
    
	/**
	 * This Function will perform the Forgot Password Function.
	 * 
	 * @param userProfileReqeustVO
	 * @return
	 */
	public UserProfileResponseVO performChangePassword(
			UserProfileRequestVO userProfileRequestVO) {
		logger.debug(">> Entering {} performChangePassword() with UserID = {}",
				this.getClass(), userProfileRequestVO.getCustId());

		UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();

		String userEmail = userProfileRequestVO.getCustId().trim().toLowerCase();
		String currentPassword = userProfileRequestVO.getCurrentPassword().trim();
		String newPassword = userProfileRequestVO.getNewPassword().trim();
		String confirmPassword = userProfileRequestVO.getConfirmPassword().trim();

		try {
			// Validate current password in DB
			// If current password is valid , continue, else not
			String passwordFromDB = getUserCurrentPassword(userEmail);

			// String pattern = Pattern.compile(PASSWORD_PATTERN);
			if (passwordFromDB != null) {

				if (currentPassword == "") {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("please enter current password");
				} else if (newPassword == "") {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("please enter new password");
				} else if (confirmPassword == "") {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("please enter confirm password");

				} else if (!newPassword.equals(confirmPassword)) {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("Your new password and confirm password entries do not match. Please try again.");
				} else if (currentPassword.equals(newPassword)) {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("Current Password and New Password should not be same ,Please try again.");
				} else if (!newPassword.matches(PASSWORD_PATTERN)) {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("Password must be 4-15 characters long. Can contain alphanumeric characters and the following symbols @ * $ . ! % ");
				} else if (!currentPassword.equals(passwordFromDB)) {
					userProfileResponseVO.setStatus("failure");
					userProfileResponseVO
							.setDescription("Current password is wrong.");
				} else {
					userProfileResponseVO = changePassword(userEmail,
							currentPassword, newPassword);
					userProfileResponseVO.setStatus("success");
					userProfileResponseVO
							.setDescription("Password Changed successfully...!");
				}

			} else {
				userProfileResponseVO.setStatus("failure");
				userProfileResponseVO
						.setDescription("Error in fetching current password");
			}
		} catch (RuntimeException rEx) {
			userProfileResponseVO.setStatus("failure");
			userProfileResponseVO.setDescription("Exception occured");
			logger.error(
					"An RunTime Exception has been occured, while parsing forgotPassword request. ",
					rEx);
		}

		logger.debug("<< Exiting {} performChangePassword() with result={} ",
				this.getClass(), userProfileResponseVO);
		return userProfileResponseVO;
	}
    
    
    /**
     * This Function will perform the Forgot Password Function.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO forgotPassword(UserProfileRequestVO userProfileRequestVO) {
        logger.debug(">> Entering {} performForgotPassword() with user = {}", this.getClass(), userProfileRequestVO.getEmail());

       
        UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
        boolean isValidEmail = false;
        boolean isSaved = false;
        String userEmail = userProfileRequestVO.getEmail().toLowerCase().trim();
        try {
        	//Validate email 
        	isValidEmail = isValidUserEmail(userEmail);
        	
        	//If valid email, continue, else not
        	if(isValidEmail){
        		//Generate token and encrypt
        		String token = userEmail;
        		String passPhrase   = EmmConstants.EMM_AES_ENCRYPTION_KEY;
        		String encryptedValue = emmUtilities.encryptURLSafe(token, passPhrase);       	        		
        		
        		//Save to mmgforgotpassword table 
        		isSaved = saveForgotPasswordDetails(userEmail,token);
        		if(isSaved){
        			//Generate one time change password link and email link to user
        			
        			//TODO : How will you get base url to send email???....Save it in prop file and read
        			//String updateMsg = "Dear"+"  "+userProfileRequestVO.getEmail().trim()+"\n\n"+"Here is the link to change your password as per your request. Just click and follow the steps."+ "\n\n" + "http://localhost:9080/MyMazda/resetPasswordLink.action?"+ encryptedValue +"\n\n"+"If the link doesn’t work, please copy paste the above URL  to your browser."+"We suggest that you keep this information for your reference.If you wish to create a new password,  please log in to  www.mymazda.com";
     
        			//Fetch firstname from User table in DB2
        			String userFirstName = getUserFirstName(userEmail);
        			
        			//String redirectUrl="http://localhost:9080/MyMazda/resetPasswordLink.action?"+encryptedValue;
        			String redirectUrl=appConstants.SERVICE_REST_USER_RESETPASSWORDLINK+"?"+encryptedValue;
        			String updateMsg="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>RESET PASSWORD</title> </head> <body style=\"margin: 0; padding: 0; background-color:#e3efff;\"><b>Dear </b>" +userFirstName + ","+"<br/><br/>" + "<p>Kindly "+"<a href=\""+redirectUrl+"\">Click Here</a>"+" to reset your password. The link will be active for 24 hours.</p>"+ "<br/><br/>"  +"UserName: " +userEmail+ "<br/><br/>"+ "<p>If you wish to create a new password,  please log in to www.MyMazda.com and select "+"Change Password "+" in the My Profile section.</p>"+"</body> </html>";
        			
        			MimeMessage mime = mailSender.createMimeMessage();
           	 		MimeMessageHelper helper = new MimeMessageHelper(mime, true);        	
           	 		helper.setFrom("webmaster@mazdausa.com");
           	 		helper.setTo(userEmail);
           	 		helper.setSubject("Forgot Password");
           	 		//helper.setText(updateMsg.replaceAll("\\n", "<br/>")+"http://localhost:9080/MyMazda/resetPasswordLink.action?"+encryptedValue, true);
           	 		helper.setText(updateMsg.replaceAll("\\n", "<br/>"), true);
					
           	 		mailSender.send(mime);	
           	 		
           	 		userProfileResponseVO.setStatus("success");
           	 		userProfileResponseVO.setDescription("Password link mailed to user");
        		}
        		else{
        			userProfileResponseVO.setStatus("failure");
            		userProfileResponseVO.setDescription("Error in saving forgot password details");
        		}
        	}
        	else{
        		userProfileResponseVO.setStatus("failure");
        		userProfileResponseVO.setDescription("Email id not found");
        	}

        } catch (MessagingException e) {
        	logger.error("Exception occured in sending forgot password email to user");
        	userProfileResponseVO.setStatus("failure");
    		userProfileResponseVO.setDescription("Problem in sending forgot password email to user");
			e.printStackTrace();
		} catch (RuntimeException rEx) {
        	userProfileResponseVO.setStatus("failure");
        	userProfileResponseVO.setDescription("Exception occured");
            logger.error("An RunTime Exception has been occured, while parsing forgotPassword request. ", rEx);
        }
        
        userProfileResponseVO.setEmail(userEmail);
        logger.debug("<< Exiting {} performForgotPassword() with result={} ", userProfileResponseVO);
        return userProfileResponseVO;
    }
    
    public UserProfileResponseVO changePassword(String userEmail,String currentPassword,String newPassword){
	    logger.debug(">> Entering  changePassword() ");
	    Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
		
		try{
			conn=musadataSource.getConnection();
			stmt=conn.prepareStatement("SELECT USERNAME,PASSWORD FROM   MUSAUSER.USER WHERE LOWER(USERNAME)=? ");
			stmt.setString(1, userEmail);
			resultSet=stmt.executeQuery();
			
			if(resultSet.next()){
				String query="UPDATE  MUSAUSER.USER  SET PASSWORD = "+"'"+newPassword+"'"+" WHERE LOWER(USERNAME) = " +"'"+userEmail+"'";
			    stmt=conn.prepareStatement(query);
			    stmt.executeUpdate();
			}
			userProfileResponseVO.setStatus("success");
				
		}catch(Exception e){
			logger.error("Problem -  changePassword > "+e);
			
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
	   
	   return userProfileResponseVO;
  }
    
    public String getUserFirstName(String userMailId){
    	
    	logger.debug("Entering getUserFirstName -",userMailId);
    	Connection conn=null;
    	PreparedStatement stmt=null;
    	ResultSet resultSet=null;
    	String userFirstName = null;
    	
    	try{
    		conn=musadataSource.getConnection();
    		String query="SELECT first_name FROM MUSAUSER.USER WHERE LOWER(USERNAME) =? ";

    		stmt=conn.prepareStatement(query);
    		stmt.setString(1,userMailId);
    		resultSet=stmt.executeQuery();
    	
    		if(resultSet.next()){
    			logger.debug("User First Name  found.",userMailId);
    			userFirstName = resultSet.getString(1);
    		}
    	
    	}catch(Exception e){
    		logger.error(" Problem -  getUserFirstName > "+e);
    		e.printStackTrace();
    	}
    	finally{
    	    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
    	    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
    	    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
    	}		
    	
		return userFirstName;
        }
 public boolean isValidUserEmail(String userMailId){
    	
    	logger.debug("Entering isValidUserEmail -",userMailId);
    	Boolean isPresent=false;
    	Connection conn=null;
    	PreparedStatement stmt=null;
    	ResultSet resultSet=null;
    	
    	try{
    		conn=musadataSource.getConnection();
    		String query="SELECT USERNAME FROM MUSAUSER.USER WHERE LOWER(USERNAME)=? ";

    		stmt=conn.prepareStatement(query);
    		stmt.setString(1,userMailId);
    		resultSet=stmt.executeQuery();
    	
    		if(resultSet.next()){
    			isPresent=true;	
    			logger.debug("User email id found.",userMailId);
    		}
    	
    	}catch(Exception e){
    		logger.error(" Problem -  isValidUserEmail > "+e);
    		e.printStackTrace();
    	}
    	finally{
    	    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
    	    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
    	    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
    	}		

    	return isPresent;
        }
    
    public boolean saveForgotPasswordDetails(String userEmail, String token){
    	
    	logger.debug(">> Entering  saveForgotPasswordDetails with email : {} token :{}",userEmail,token);
    	boolean isSaved = false;
    	Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String startTime = sdf.format(date);
		
		Calendar c = Calendar.getInstance();
		c.setTime(date); 
		c.add(Calendar.DATE, 1); // Adding 1 day
		String expiryTime = sdf.format(c.getTime());
		
		try{
			conn=dataSource.getConnection();
			stmt=conn.prepareStatement("SELECT * FROM mmgforgotpassword where user_id = ? ");
			stmt.setString(1, userEmail);
		
			resultSet=stmt.executeQuery();
		
			if(resultSet.next()){
				String query="UPDATE mmgforgotpassword mmg SET token = "
					+"'"+token+"',"
					+"start_time="
					+"'"+startTime+"',"
					+"expiry_time="
					+"'"+expiryTime+"',"
					+"isTokenActive="
					+"true"
					+" WHERE user_id = "
					+"'"+userEmail+"'";
			
				stmt=conn.prepareStatement(query);
				stmt.executeUpdate();
			}
			else{
				String query="INSERT INTO mmgforgotpassword (user_id,token,start_time,expiry_time,isTokenActive) "+
					"VALUES ("+"'"+userEmail+"','"+token+"','"+startTime+"','"+expiryTime+"',"+"true"+")";
			
				stmt=conn.prepareStatement(query);
				stmt.executeUpdate();
			}
			isSaved = true;
		
		}catch(Exception e){
			isSaved = false;
			logger.error("Problem -  saveForgotPasswordDetails > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
		
		logger.debug("<< Exiting  saveForgotPasswordDetails() ");
		return isSaved;
    }
    
    public String getUserCurrentPassword(String userMailId){
    	
    	Connection conn=null;
    	PreparedStatement stmt=null;
    	ResultSet resultSet=null;
    	String password = null;
    	try{
    	logger.debug("****Data source ******= {}",musadataSource);
    	conn=musadataSource.getConnection();
    	String query="SELECT PASSWORD FROM MUSAUSER.USER WHERE LOWER(USERNAME)=? ";

    	stmt=conn.prepareStatement(query);
    	stmt.setString(1,userMailId);
    	
    	resultSet=stmt.executeQuery();
    	
    		if(resultSet.next())
    		{
    			password = resultSet.getString(1);
    			logger.debug("User password found.",userMailId);
    		}
    	
    	}catch(Exception e){
    		logger.error(" Problem -  isValidPassword > "+e);
    		e.printStackTrace();
    	}
    	finally{
    	    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
    	    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
    	    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
    	}		

    	return password;
        }
    
    /**
     * This Function will validate reset password link.
     * @param 
     * @return
     */
    public UserProfileResponseVO verifyResetPasswordLink(String  encryptedVal) throws ParseException{
 	   
    	logger.debug(">> Entering {} verifyResetPasswordLink() ");
 	   	   
        UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
        boolean isTokenActive = false;
        
        String decryptedVal = EmmUtilities.decryptURLSafe(encryptedVal, EmmConstants.EMM_AES_ENCRYPTION_KEY);
      
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentDate = sdf.format(date).trim();
        Date currDate= sdf.parse(currentDate);
 	   
        String  expiryDate =  getExpiryTime(decryptedVal);
        Date expDate= sdf.parse(expiryDate);
 	   
        isTokenActive = isValidToken(decryptedVal);
        //Compare if expiry date is crossed and isToken active is false
        if(currDate.compareTo(expDate)>=0 || !isTokenActive){
 		   userProfileResponseVO.setStatus("failure");
        }
        else{
 		   userProfileResponseVO.setStatus("success");
  		   userProfileResponseVO.setEmail(decryptedVal);

        }
 	   	return userProfileResponseVO;
    }
    
    public String getExpiryTime(String userEmail){
       	
   		logger.debug(">> Entering  readExpiryTime() ");
   		Connection conn=null;
   		PreparedStatement stmt=null;
   		ResultSet resultSet = null;
   		String expireTime = null;
   		
   		try{
   		conn=dataSource.getConnection();
   		String query="select expiry_time from mmgforgotpassword where  user_id = ? ";

   		stmt=conn.prepareStatement(query);
   		stmt.setString(1,userEmail);
   		
   		resultSet=stmt.executeQuery();
   		
   			if(resultSet.next())
   			{
   				logger.debug("User email id found.",userEmail);
   				expireTime = resultSet.getString(1);
   			}
   		
   		}catch(Exception e){
   			logger.error(" Problem -  isValidUserEmail > "+e);
   		}
   		finally{
   		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
   		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
   		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
   		}		
		return expireTime;
   }
    
    public boolean isValidToken(String userEmail){
       	
   		logger.debug(">> Entering  getIsValidToken() ");
   		Connection conn=null;
   		PreparedStatement stmt=null;
   		ResultSet resultSet = null;
   		boolean isActive = false;
   		
   		try{
   		conn=dataSource.getConnection();
   		String query="select isTokenActive from mmgforgotpassword where  user_id = ? ";

   		stmt=conn.prepareStatement(query);
   		stmt.setString(1,userEmail);
   		
   		resultSet=stmt.executeQuery();
   		
   			if(resultSet.next())
   			{
   				logger.debug("User email id found.",userEmail);
   				isActive = resultSet.getBoolean(1);
   			}
   		
   		}catch(Exception e){
   			logger.error(" Problem -  getIsValidToken > "+e);
   		}
   		finally{
   		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
   		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
   		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
   		}		
		return isActive;
   }

    /**
     * This Function will update the new password.
     * @param userProfileReqeustVO
     * @return
     */
    public UserProfileResponseVO updatePassword(UserProfileRequestVO userProfileRequestVO){
 	   
    	logger.debug(">> Entering {} updatePassword() with user = {}", this.getClass(), userProfileRequestVO.getEmail());
        UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
        String userEmail = userProfileRequestVO.getEmail().trim();
        System.out.println(userEmail);
        
        String newPassword = userProfileRequestVO.getNewPassword().trim();
        
        boolean isUpdated = false;
        boolean isSaved = false;
        
        try {
     	    isUpdated = updatemmgForgotPassword(userEmail); 
     	    if(isUpdated){
     	    	isSaved = saveResetPassword(userEmail,newPassword);
     	    	if(isSaved){
     	    		userProfileResponseVO.setStatus("success");
     	        	userProfileResponseVO.setDescription("Password updated successfully");
     	    	}
     	    	else{
     	    		userProfileResponseVO.setStatus("failure");
     	        	userProfileResponseVO.setDescription("Failed to update password");
     	    	}
     	    }
     	    else{
     	    	userProfileResponseVO.setStatus("failure");
 	        	userProfileResponseVO.setDescription("Failed to update MMG dadabase");
     	    }

     } catch (RuntimeException rEx) {
     	userProfileResponseVO.setStatus("failure");
     	userProfileResponseVO.setDescription("Exception occured");
         logger.error("An RunTime Exception has been occured, while parsing resetPassword request. ", rEx);
     }
        		
 		return userProfileResponseVO;
     }
    
    public boolean updatemmgForgotPassword(String userMailId){
    	logger.debug(">>> Entering updatemmgForgotPassword with mail id = {}",userMailId);
    	boolean isUpdated = false;
    	Connection conn=null;
    	PreparedStatement stmt=null;
    	ResultSet resultSet=null;
    	
    	try{
    	conn=dataSource.getConnection();
    	stmt=conn.prepareStatement("select * from mmgforgotpassword where  user_id = ? ");
    	stmt.setString(1, userMailId);
    	
    	resultSet = stmt.executeQuery();
    	
    	if(resultSet.next()){
    		String query="UPDATE mmgforgotpassword  SET isTokenActive = "+false+" WHERE user_id = " +"'"+userMailId+"'";
    	    stmt=conn.prepareStatement(query);
    	    stmt.executeUpdate();
    	    isUpdated = true;
    	}
    	
    	}catch(Exception e){
    		logger.error(" Problem -  updatemmgForgotPassword > "+e);
    		e.printStackTrace();
    	}
    	finally{
    	    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
    	    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
    	    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
    	}		

    	return isUpdated;
        }
    
    public boolean saveResetPassword(String userEmail,String newPassword){
	    logger.debug(">> Entering  saveResetPassword() ");
	    Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		boolean isUpdated = false;
		
		try{
			conn=musadataSource.getConnection();
			stmt=conn.prepareStatement("SELECT USERNAME,PASSWORD FROM   MUSAUSER.USER WHERE USERNAME=? ");
			stmt.setString(1, userEmail);
			resultSet=stmt.executeQuery();
			
			if(resultSet.next()){
				System.out.println("Update reset password ....");
				String query="UPDATE  MUSAUSER.USER  SET PASSWORD = "+"'"+newPassword+"'"+" WHERE USERNAME = " +"'"+userEmail+"'";
			    stmt=conn.prepareStatement(query);
			    stmt.executeUpdate();
			    isUpdated = true;
			}
			
				
		}catch(Exception e){
			logger.error("Problem -  saveResetPassword > "+e);
			e.printStackTrace();
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }		
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
	   
	   return isUpdated;
   }


    /**
     * This is the function, which will be responsible for getting the User Details by giving the User's detail parameters.
     * @param userDetailRequestVO
     * @return
     */
    public UserGetDetailResponseVO getUserDetails(UserGetDetailRequestVO userDetailRequestVO, @Context HttpHeaders headers) {
        logger.debug(">> Entering {} getUserDetails()  ", this.getClass());

        RestUserDetailResponseVO userDetailsResponseVO = null;
        RestUserDetailRequestVO userDetailsRequestVO = new RestUserDetailRequestVO();
        UserGetDetailResponseVO userDetailResponseVO = new UserGetDetailResponseVO();

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

        try {

            // Setting the web service input parameters.
            UserDetailRequestParametersVO userDetailRequestParameters = new UserDetailRequestParametersVO();

            userDetailRequestParameters.setCity(userDetailRequestVO.getCity());
            userDetailRequestParameters.setCustomerId(userDetailRequestVO.getCustomerid());
            userDetailRequestParameters.setEmail(userDetailRequestVO.getEmail());
            userDetailRequestParameters.setFirstName(userDetailRequestVO.getFirstname());
            userDetailRequestParameters.setLastName(userDetailRequestVO.getLastname());
            userDetailRequestParameters.setSource(userDetailRequestVO.getSource());
            userDetailRequestParameters.setState(userDetailRequestVO.getState());
            userDetailRequestParameters.setStreetAddress1(userDetailRequestVO.getStreet1address());
            userDetailRequestParameters.setStreetAddress2(userDetailRequestVO.getStreet2address());
            userDetailRequestParameters.setVin(userDetailRequestVO.getVin());
            userDetailRequestParameters.setZip(userDetailRequestVO.getZip());
            userDetailRequestParameters.setRequestor(requestor);
            userDetailRequestParameters.setRequestId(requestId);
            userDetailRequestParameters.setVersion(version);
            
            userDetailsRequestVO.setUserRequestDetailParameters(userDetailRequestParameters);

            userDetailsResponseVO = userTaskService.getUserDetails(userDetailsRequestVO);


            if (userDetailsResponseVO != null && userDetailsResponseVO.getUserDetailResponseParameter().getError() != null && userDetailsResponseVO.getUserDetailResponseParameter().getError().trim().length() == 0) {

                userDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
                if (userDetailsResponseVO.getUserDetailResponseParameter() != null) {
                    userDetailResponseVO.setUserdetails(userDetailsResponseVO.getUserDetailResponseParameter());
                    // Adding a new line character to the street Address of the Customer. 
                    /* Not Required Now.
                     String streetAddress="\n"+userDetailResponseVO.getUserdetails().getStreet1address();
                    userDetailResponseVO.getUserdetails().setStreet1address(streetAddress);*/

                }
                
                //  Writer code here to check if the required customer exists in the database or not, if not then add it. NOT NEEDED.

            } else if (userDetailsResponseVO != null && userDetailsResponseVO.getUserDetailResponseParameter().getError() != null) {
                userDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userDetailResponseVO.setDescription(userDetailsResponseVO.getUserDetailResponseParameter().getError());
            } else if (userDetailsResponseVO == null) {
                userDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userDetailResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            } else {
                userDetailResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            }

        } catch (RuntimeException rEx) {
            logger.error("An RunTime Exception has been occured, Executing Customer Detail request. ", rEx);
        }
        logger.debug("<< Exiting {} performForgotPassword() with result={} ", userDetailResponseVO);
        return userDetailResponseVO;
    }

    /**
     * This Function will update the User Details in the System.
     * @param userUpdateDetailsRequestVO
     * @return
     */
    public UserUpdateDetailsResponseVO updateUserDetails(UserUpdateDetailsRequestVO userUpdateDetailsRequestVO, @Context HttpHeaders headers) {
        logger.debug(">> Entering {} updateUserDetails() with Cust ID={} ", this.getClass(), userUpdateDetailsRequestVO.getCustID());

        RestUserDetailsUpdateResponseVO userDetailsUpdateResponseVO = null;
        RestUserDetailsUpdateRequestVO userDetailsUpdateRequestVO = new RestUserDetailsUpdateRequestVO();
        UserUpdateDetailsResponseVO userUpdateDetailsResponseVO = new UserUpdateDetailsResponseVO();

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
        
        try {

            // Setting the web service input parameters.
            UserDetailsUpdateVO userDetailsUpdateVO = new UserDetailsUpdateVO();

            userDetailsUpdateVO.setAddOnZip(userUpdateDetailsRequestVO.getAddOnZip());
            userDetailsUpdateVO.setAddressType(userUpdateDetailsRequestVO.getAddressType());
            userDetailsUpdateVO.setAddressValidation(userUpdateDetailsRequestVO.getAddressValidation());
            userDetailsUpdateVO.setCity(userUpdateDetailsRequestVO.getCity());
            userDetailsUpdateVO.setCountry(userUpdateDetailsRequestVO.getCountry());
            userDetailsUpdateVO.setCustID(userUpdateDetailsRequestVO.getCustID());
            userDetailsUpdateVO.setDoNotCall(userUpdateDetailsRequestVO.getDoNotCall());
            userDetailsUpdateVO.setDoNotContact(userUpdateDetailsRequestVO.getDoNotContact());
            userDetailsUpdateVO.setDoNotEmail(userUpdateDetailsRequestVO.getDoNotEmail());
            userDetailsUpdateVO.setEmail(userUpdateDetailsRequestVO.getEmail());
            userDetailsUpdateVO.setEmailType(userUpdateDetailsRequestVO.getEmailType());
            userDetailsUpdateVO.setError(userUpdateDetailsRequestVO.getError());
            userDetailsUpdateVO.setFirstName(userUpdateDetailsRequestVO.getFirstName());
            userDetailsUpdateVO.setHomePhone(userUpdateDetailsRequestVO.getHomePhone());
            userDetailsUpdateVO.setInvalidAddressFlag(userUpdateDetailsRequestVO.getInvalidAddressFlag());
            userDetailsUpdateVO.setLastName(userUpdateDetailsRequestVO.getLastName());
            userDetailsUpdateVO.setMobilePhone(userUpdateDetailsRequestVO.getMobilePhone());
            userDetailsUpdateVO.setReturnedEmail(userUpdateDetailsRequestVO.getReturnedEmail());
            userDetailsUpdateVO.setSource(userUpdateDetailsRequestVO.getSource());
            userDetailsUpdateVO.setState(userUpdateDetailsRequestVO.getState());
            userDetailsUpdateVO.setStreetAddress1(userUpdateDetailsRequestVO.getStreet1Address());
            userDetailsUpdateVO.setStreetAddress2(userUpdateDetailsRequestVO.getStreet2Address());
            userDetailsUpdateVO.setWorkPhone(userUpdateDetailsRequestVO.getWorkPhone());
            userDetailsUpdateVO.setWorkPhoneExt(userUpdateDetailsRequestVO.getWorkPhoneExt());
            userDetailsUpdateVO.setZip(userUpdateDetailsRequestVO.getZip());
            userDetailsUpdateVO.setRequestor(requestor);
            userDetailsUpdateVO.setRequestId(requestId);
            userDetailsUpdateVO.setVersion(version);

            userDetailsUpdateRequestVO.setUserDetailsUpdateVO(userDetailsUpdateVO);

            userDetailsUpdateResponseVO = userTaskService.updateUserDetails(userDetailsUpdateRequestVO);


            if (userDetailsUpdateResponseVO != null && (userDetailsUpdateResponseVO.getResult().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase()))) {

                userUpdateDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
                userUpdateDetailsResponseVO.setDescription(userDetailsUpdateResponseVO.getResult().trim());


            } else if (userDetailsUpdateResponseVO.getResult() != null && (userDetailsUpdateResponseVO.getResult().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase())) == false) {
                userUpdateDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userUpdateDetailsResponseVO.setDescription(userDetailsUpdateResponseVO.getResult().trim());

            } else if (userDetailsUpdateResponseVO.getResult() == null) {
                userUpdateDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
                userUpdateDetailsResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            } else {
                userUpdateDetailsResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            }

        } catch (RuntimeException rEx) {
            logger.error("An RunTime Exception has been occured, Executing Customer Update Details Request ", rEx);
        }
        logger.debug("<< Exiting {} updateUserDetails() with result={} ", userUpdateDetailsResponseVO);
        return userUpdateDetailsResponseVO;
    }

    /**
     * This is the function, which will be responsible for adding/updating the details of the user as exist in the database. 
     * @param userProfileResponseVO
     * @param userProfileRequestVO
     * @param userCredentialsVO
     * @param request
     * MODIFIED on 18 July 2011
     */
    @Async
    private void addOrUpdateCustomer(UserProfileResponseVO userProfileResponseVO, UserProfileRequestVO userProfileRequestVO, UserCredentialsDataVO userCredentialsVO, HttpServletRequest request, String guid) {
    	
    	try{
        Date currentDate = Calendar.getInstance().getTime();
        UserVO userVO = new UserVO();
        userVO.setAddedBy(ApplicationConstants.USER_APPLICATION);
        userVO.setAddedDate(currentDate);
        userVO.setCustomerId(userProfileResponseVO.getCustomerId());
        userVO.setMailId(userCredentialsVO.getUserid()); // Here, it is the MAIL ID.
        userVO.setUserAuthenticated(Boolean.TRUE);
        userVO.setUserDeleted(Boolean.FALSE);
        // You need to find the Application Details here.        

        ApplicationInstallationDetailsVO appInstallDetails = applicationService.getApplicationInstallationDetails(guid);
        if (appInstallDetails != null && appInstallDetails.getMobileUserAgent() != null && userProfileRequestVO.getApnstoken() != null && userProfileRequestVO.getApnstoken().trim().length() != 0 && userProfileRequestVO.getApnstoken().equalsIgnoreCase("(null)") == false) {
            //Update the Device TOKEn for this DEVICE.
            appInstallDetails.setDeviceAPNSToken(userProfileRequestVO.getApnstoken());
            applicationService.updateApplicationInstallationDetails(appInstallDetails);
            logger.debug("Trying to update the App Installation Details for the installation = {} ", appInstallDetails.getAppInstallationGuid());
            userVO.setApplicationInstallationVO(appInstallDetails);

        }

        // Now Check if the user exists or not.
        UserVO existingUserVO = userService.getUserByMailId(userVO.getMailId().trim());
        if (existingUserVO != null && existingUserVO.getCustomerId() != null) {
            // Here means the user already exists in the database. So update it.
            // Code added on 18 July 2009.
            existingUserVO.setApplicationInstallationVO(appInstallDetails);
            userService.updateUser(existingUserVO);
            logger.info("User {} mapping has been updated to Application having GUID {} ", userVO.getMailId(), guid);

        } else {
            // Insert the new User in the database.
            boolean result = userService.addUser(userVO);
            if (result) {
                logger.debug("Successfully add the new User Record in the database ={}", result);
            } else {
                logger.debug("Could not be able to insert the record in database = {} ", result);
            }
        }
        existingUserVO = null;
        userVO = null;
    	}catch(Exception e){logger.error(e.getMessage());}

    	//Added@20-01-2014 -> Update user email id corresponding to deviceToken
    	updateDeviceUser(userCredentialsVO.getUserid(),userProfileRequestVO.getApnstoken());
    	//Added@01-01-2015 to update vehicle name for a vin
    	updateVinVehicleName(userProfileRequestVO.getVin(),userProfileRequestVO.getVtitle());
    }
    
    
    /**
     * This is the function, which will be responsible for adding/updating the vehicle name details of the vin in mmguservehicles. 
     * @param userProfileRequestVO
     * @param request
     */
    public UserProfileResponseVO addOrUpdateVinCarlineName(UserProfileRequestVO userProfileRequestVO,HttpServletRequest request){
    	
        logger.debug(">> Exiting {} addOrUpdateVinCarlineName() with vin={} and carline = {} ", userProfileRequestVO.getVin(),userProfileRequestVO.getVtitle());
    	UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
    	
        userBusService.updateOwnershipOfUserVehicleCarline(userProfileRequestVO.getCustId(), userProfileRequestVO.getVin(),userProfileRequestVO.getVtitle());
        
        userProfileResponseVO.setStatus("success");
		userProfileResponseVO.setDescription("Vehicle name updated successfully");
        logger.debug("<< Exiting {} addOrUpdateVinCarlineName() with result={} ", userProfileResponseVO);
    	return userProfileResponseVO;
    }
    
    /**
     * This is the function, which will be responsible for adding/updating the vehicle name details of the vin in mmguservehicles. 
     * @param vin
     * @param request
     */
    public UserProfileResponseVO getVinCarlineName(String vin){
    	logger.debug(">> Exiting {} getVinCarlineName() with vin={} ", vin);
    	UserProfileResponseVO userProfileResponseVO = new UserProfileResponseVO();
    	UserVehicleVO userVehicleVO = null;
    	//Get car name for the vin
    	userVehicleVO = userBusService.getUserVehicleByVIN(vin);
    	
    	if(userVehicleVO != null){
    		userProfileResponseVO.setStatus("success");
    		userProfileResponseVO.setCarlineDesc(userVehicleVO.getVehicleTitle());
    	}
    	else{
    		userProfileResponseVO.setStatus("failure");
    	}
    	logger.debug("<< Exiting {} getVinCarlineName() with result={} ", userProfileResponseVO);
    	return userProfileResponseVO;
    }
    
	private void updateVinVehicleName(String vin,String vinVehicleName)
	{
		logger.debug(">>>entering updateVinVehicleName");

		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
		String query="UPDATE mmguservehicles SET vin_vehicle_name = ? WHERE custvin = ?";	
		conn=dataSource.getConnection();
		logger.debug("Query = "+query);
		
		stmt=conn.prepareStatement(query);
		stmt.setString(1,vin);
		stmt.setString(2,vinVehicleName);
		
		int updateRow=stmt.executeUpdate();
		logger.debug("No of affected rows = "+updateRow);
		
		}catch(Exception e){
			logger.error(" Problem -  updateVinVehicleName > "+e);
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		logger.debug("<<< exiting updateVinVehicleName");
	}

    /**
     * This method updates user id=email id corresponding to device token. 
     * @param deviceUser
     * @param deviceToken
     */
	private void updateDeviceUser(String deviceUser,String deviceToken)
	{
		logger.debug(">>>entering updateDeviceUser");

		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
		String query="UPDATE mmg_devices SET last_update_date=current_timestamp, device_user=? WHERE device_token=?";	
		conn=dataSource.getConnection();
		logger.debug("Query = "+query);
		
		stmt=conn.prepareStatement(query);
		stmt.setString(1,deviceUser);
		stmt.setString(2,deviceToken);
		
		int updateRow=stmt.executeUpdate();
		logger.debug("No of affected rows = "+updateRow);
		
		}catch(Exception e){
			logger.error(" Problem -  updateDeviceUser > "+e);
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		logger.debug("<<< exiting updateDeviceUser");
	}
    

    /**
     * This is the function, which will be responsible for adding/updating the details of the user as exist in the database.
     * @param userProfileResponseVO
     * @param userProfileRequestVO
     * @param userCredentialsVO
     * @param request
     */
    @Async
    private void addOrUpdateCustomerOLD(UserProfileResponseVO userProfileResponseVO, UserProfileRequestVO userProfileRequestVO, UserCredentialsDataVO userCredentialsVO, HttpServletRequest request) {
        Date currentDate = Calendar.getInstance().getTime();
        UserVO userVO = new UserVO();
        userVO.setAddedBy(ApplicationConstants.USER_APPLICATION);
        userVO.setAddedDate(currentDate);
        userVO.setCustomerId(userProfileResponseVO.getCustomerId());
        userVO.setMailId(userCredentialsVO.getUserid()); // Here, it is the MAIL ID.
        userVO.setUserAuthenticated(Boolean.TRUE);
        userVO.setUserDeleted(Boolean.FALSE);
        // You need to find the Application Details here.
        HttpSession clientSession = request.getSession(false);

        if (clientSession != null) {
            // Get the Object stored in the HashMap
            ClientSessionVO clientSessionVO = EMMConstants.getTOKENTABLE().get(clientSession.getId());
            ApplicationInstallationDetailsVO appInstallDetails = applicationService.getApplicationInstallationDetails(clientSessionVO.getGuid());
            if (appInstallDetails != null && appInstallDetails.getMobileUserAgent() != null) {
                //Update the Device TOKEn for this DEVICE.
                appInstallDetails.setDeviceAPNSToken(userProfileRequestVO.getApnstoken());
                applicationService.updateApplicationInstallationDetails(appInstallDetails);
                logger.debug("Trying to update the App Installation Details for the installation = {} ", appInstallDetails.getAppInstallationGuid());
                userVO.setApplicationInstallationVO(appInstallDetails);
                appInstallDetails = null;
            }

            // Now Check if the user exists or not.
            UserVO existingUserVO = userService.getUserByMailId(userVO.getMailId().trim());
            if (existingUserVO != null && existingUserVO.getCustomerId() != null) {
                // Here means the user already exists in the database. So update it.
                // Code added on 18 July 2009.
                userService.updateUser(userVO);
                logger.info("User {} mapping has been updated to Application having GUID {} ", userVO.getMailId(), clientSessionVO.getGuid());

            } else {
                // Insert the new User in the database.
                boolean result = userService.addUser(userVO);
                if (result) {
                    logger.debug("Successfully add the new User Record in the database ={}", result);
                } else {
                    logger.debug("Could not be able to insert the record in database = {} ", result);
                }
            }
            existingUserVO = null;

        }

        clientSession = null;
        userVO = null;


    }

}
