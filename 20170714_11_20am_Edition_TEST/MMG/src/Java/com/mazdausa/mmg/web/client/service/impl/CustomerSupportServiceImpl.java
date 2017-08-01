package com.mazdausa.mmg.web.client.service.impl;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.emm.v1.constants.EMMConstants;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.db.constants.DatabaseConstants;
import com.mazdausa.mmg.web.client.request.vo.CustomerSupportRequestVO;
import com.mazdausa.mmg.web.client.response.vo.CustomerSupportResponseVO;
import com.mazdausa.mmg.web.client.service.iface.CustomerSupportServiceIface;

/**
 * This is the Implementation class of Service layer Interface HowtoServiceIFace.
 * @author AnilK
 *
 */

@Service
public class CustomerSupportServiceImpl implements CustomerSupportServiceIface{

    private static final Logger logger = LoggerFactory.getLogger(CustomerSupportServiceImpl.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    EMMConstants emmConstants;    
	
	@Override
	public CustomerSupportResponseVO getAppVersion(String agentType) {
		CustomerSupportResponseVO response = new CustomerSupportResponseVO();

		try {

			// Commented @ 20-05-2014
			/*
			 * if(agentType.equalsIgnoreCase("iphone"))
			 * response.setNewVersion(appConstants
			 * .MMG_CUSTOMER_SUPPORT_APPVERSION_IPHONE); else
			 * response.setNewVersion
			 * (appConstants.MMG_CUSTOMER_SUPPORT_APPVERSION_ANDROID);
			 */

			// Getting APP version from the DB -start@20-05-2014

			if (agentType.equalsIgnoreCase("android")
					|| agentType.equalsIgnoreCase("iphone")) {
				response.setNewVersion(getAppVersionFromDb(agentType));
				response.setStatus("success");
			} else {
				response.setStatus("failure");
				response.setMessage("Invalid request.");
			}
			// Getting APP version from the DB -end@20-05-2014

		} catch (Exception e) {
			response.setStatus("failure");
			response.setMessage(e.getMessage());
		}

		return response;
	}
	
	@Override
	public CustomerSupportResponseVO emailCoupons(CustomerSupportRequestVO custSuppReq) {
		
		CustomerSupportRequestVO customerSupportReq=custSuppReq;
				
		//Mail sending start
        String tempMailSendMessage="";
        int temp=0;
        try{

    		//String updateMessage="<html> <head> <meta charset=\"utf-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <title></title> <link rel=\"stylesheet\" href=\"\"> <style type=\"text/css\"> body{background: #e3efff; padding: 0px; margin: 0px; font-family: arial; font-size: 12px; color: #363636;} .clearfix{clear: both;} .container{ position: relative; width: 100%; padding: 0 20px;} .wrapper{ width: 640px; margin: 0 auto; position: relative; background: #fff;} .header{ padding: 20px 10px; position: relative;} .vin-no{ float: left; width: 45%; height: auto;} .address{ float: right; width: 45%; text-align: right;} .offer-contaienr{padding: 10px; position: relative;} .offer-btn-wrapper{ background-color:#313131; box-shadow:5px 5px 15px 2px #1a1a1a inset; padding: 5px; -moz-border-radius:10px; -webkit-border-radius:10px; border-radius:10px; } h1{ font-size: 24px; color: #43b1da; text-decoration: none; font-weight: normal; padding: 0px 30px; text-align: center; font-style: italic; line-height: 50px;} .offer-title{width: 70%; float: left; text-align: center;} .offer-btn-contaier{ width: 25%; float: right;} .offer-btn{ background: #889099; /* Old browsers */ background: -moz-linear-gradient(top, #889099 0%, #414952 50%, #727984 100%); /* FF3.6+ */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#889099), color-stop(50%,#414952), color-stop(100%,#727984)); /* Chrome,Safari4+ */ background: -webkit-linear-gradient(top, #889099 0%,#414952 50%,#727984 100%); /* Chrome10+,Safari5.1+ */ background: -o-linear-gradient(top, #889099 0%,#414952 50%,#727984 100%); /* Opera 11.10+ */ background: -ms-linear-gradient(top, #889099 0%,#414952 50%,#727984 100%); /* IE10+ */ background: linear-gradient(to bottom, #889099 0%,#414952 50%,#727984 100%); /* W3C */ filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#889099', endColorstr='#727984',GradientType=0 ); /* IE6-9 */ text-decoration: none; color: #f1e63c; text-align: center; padding: 30px 10px; display: block; font-size: 18px; -moz-border-radius:10px; -webkit-border-radius:10px; border-radius:10px; } .msg-wrapper{ padding: 10px; text-align: justify; font-weight: normal; line-height: 18px; font-size: 13px; min-height: 250px;} .small-text{ font-size: 10px; text-align: justify; font-weight: normal;} .footer-wrapper{ margin-top: 20px; background: #1e1e1e;} .footer-cont{ padding: 10px; font-size: 11px; color: #fff; font-weight: normal; text-align: justify;} </style> </head> <body> <div class=\"container\"> <div class=\"wrapper\"> <div class=\"header\"> <div class=\"vin-no\"> <p><strong>VIN:</strong>'"+customerSupportReq.getVin()+"'</p> </div> <div class=\"address\"> '"+customerSupportReq.getAddress()+"'<br/>'"+customerSupportReq.getUserphone()+"'</div> <div class=\"clearfix\"></div> </div> <div class=\"offer-contaienr\"> <div class=\"offer-btn-wrapper\"> <div class=\"offer-title\"><h1>'"+customerSupportReq.getEmailsubject()+"'</h1></div> <div class=\"offer-btn-contaier\"> <a href=\"\" class=\"offer-btn\">'"+customerSupportReq.getCouponvalue()+"'</a> </div> <div class=\"clearfix\"></div> </div> </div> <div class=\"msg-wrapper\"> <p> '"+customerSupportReq.getEmailmessage()+"' </p> </div> <div class=\"footer-wrapper\"> <div class=\"footer-cont\"> '"+customerSupportReq.getDisclaimer()+"' </div> </div> </div> </div> </body> </html>";
    		//New template
        	//String updateMessage="<!DOCTYPE html> <html> <head> <meta charset=\"utf-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <title></title> </head> <body> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding: 0px; margin: 0px; font-family: arial; font-size: 12px; color: #363636;\"> <tr> <td align=\"left\" valign=\"top\"> <table width=\"640\" align=\"center\" border=\"0\" cellspacing=\"2\" cellpadding=\"0\" style=\"border:1px solid black; margin:0 auto; font-family: arial; font-size: 12px; color: #363636;\"> <tr> <td align=\"left\" valign=\"top\" style=\"padding:15px; background-color:#fff;\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td align=\"left\" valign=\"top\" style=\"padding-bottom:20px;\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td align=\"left\" valign=\"middle\" ><p><strong>VIN:</strong> "+customerSupportReq.getVin()+"</p></td> <td align=\"right\" valign=\"top\"> "+customerSupportReq.getAddress()+"<br/>"+customerSupportReq.getUserphone()+"</td> </tr> </table> </td> </tr> <tr> <td align=\"left\" valign=\"top\" style=\"background-color: #313131;border-radius: 10px; box-shadow: 5px 5px 15px 2px #1A1A1A inset; padding: 5px;\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"> <tr> <td align=\"center\" valign=\"middle\" style=\"text-align: center; width: 70%;\"><h1 style=\"color:#43b1da; font-weight:normal; font-style:italic;\">"+customerSupportReq.getEmailsubject()+"</h1></td> <td align=\"right\" valign=\"middle\" style=\"width: 25%;\"><a href=\"\" class=\"offer-btn\" style=\"background: linear-gradient(to bottom, #889099 0%, #414952 50%, #727984 100%) repeat scroll 0 0 rgba(0, 0, 0, 0); border-radius: 10px; color: #F1E63C; display: block; font-size: 18px; padding: 30px 10px; text-align: center; text-decoration: none;\">"+customerSupportReq.getCouponvalue()+"</a></td> </tr> </table> </td> </tr> <tr> <td align=\"left\" valign=\"top\" style=\"font-size: 13px;font-weight: normal;line-height: 18px; min-height: 250px; padding: 10px; text-align: justify;\"><p>"+customerSupportReq.getEmailmessage()+"</p></td> </tr> </table> </td> </tr> <tr> <td align=\"left\" valign=\"top\" style=\"color: #FFFFFF;font-size: 11px;font-weight: normal;padding: 10px; text-align: justify; background-color:#000;\">"+customerSupportReq.getDisclaimer()+".</td> </tr> </table> </td> </tr> </table> </body> </html>";
        	//String updateMessage="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> <title>Demystifying Email Design</title> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/> </head> <body style=\"margin: 0; padding: 0; background-color:#e3efff;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"padding: 10px 0 30px 0;\"> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\"> <tr> <td bgcolor=\"#ffffff\" style=\"padding: 20px 30px 40px 30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td width=\"240\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"padding: 10px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\"> <b>VIN:</b><br> "+customerSupportReq.getVin()+"</td> </tr> </table> </td> <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp; </td> <td width=\"280\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"padding: 0px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\"> "+customerSupportReq.getAddress()+"<br/>"+customerSupportReq.getUserphone()+"</td> </tr> </table> </td> </tr> </table> </td> </tr> <tr> <td style=\"height: 35px;\"></td> </tr> <tr> <td style=\"background-color: #313131; padding:8px; border:2px solid #141414;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td width=\"370\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"height:38px;\"></td> </tr> <tr> <td style=\"color: #43b1da; font-weight: normal; font-style: italic; text-transform: uppercase; font-family: impact; font-size: 28px; letter-spacing: 1px; text-align:center;\"> <b>"+customerSupportReq.getEmailsubject()+"</b> </td> </tr> </table> </td> <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp; </td> <td width=\"150\" valign=\"top\" style=\"height:100px; background: #889099; color: #F1E63C;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"height:38px;\"></td> </tr> <tr> <td style=\"color: #F1E63C; font-size: 30px; font-weight: bold; text-align:center; text-transform: uppercase; font-family: Arial, sans-serif; \"> "+customerSupportReq.getCouponvalue()+" </td> </tr> </table> </td> </tr> </table> </td> </tr> <tr> <td style=\"height: 35px;\"></td> </tr> <tr> <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px; font-weight:normal;\"> "+customerSupportReq.getEmailmessage()+" </td> </tr> <tr> <td style=\"height: 10px;\"></td> </tr> </table> </td> </tr> <tr> <td bgcolor=\"#131313\" style=\"padding: 20px 30px 20px 30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\" width=\"75%\"> "+customerSupportReq.getDisclaimer()+" </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </body> </html>";
        	String updateMessage="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /> <title>Demystifying Email Design</title> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/> </head> <body style=\"margin: 0; padding: 0; background-color:#e3efff;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"padding: 10px 0 30px 0;\"> <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\"> <tr> <td bgcolor=\"#ffffff\" style=\"padding: 20px 30px 40px 30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td width=\"240\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"padding: 10px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\"> <b>VIN:</b><br> "+customerSupportReq.getVin()+" </td> </tr> </table> </td> <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp; </td> <td width=\"280\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"padding: 0px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px; text-align:right;\"> "+customerSupportReq.getAddress()+"<br/>"+customerSupportReq.getUserphone()+"</td> </tr> </table> </td> </tr> </table> </td> </tr> <tr> <td style=\"height: 35px;\"></td> </tr> <tr> <td style=\"background-color: #414141; padding:8px; border:2px solid #141414;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td width=\"370\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"height:38px;\"></td> </tr> <tr> <td style=\"color: #43b1da; font-weight: normal; font-style: italic; text-transform: uppercase; font-family: impact; font-size: 28px; letter-spacing: 1px; text-align:center;\"> <b>"+customerSupportReq.getEmailsubject()+"</b> </td> </tr> </table> </td> <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp; </td> <td width=\"150\" valign=\"top\" style=\"height:100px; background: #889099; color: #F1E63C;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"height:38px;\"></td> </tr> <tr> <td style=\"color: #F1E63C; font-size: 30px; font-weight: bold; text-align:center; text-transform: uppercase; font-family: Arial, sans-serif; \"> "+customerSupportReq.getCouponvalue()+" </td> </tr> </table> </td> </tr> </table> </td> </tr> <tr> <td style=\"height: 35px;\"></td> </tr> <tr> <td style=\"color: #808080; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px; font-weight:normal;\"> "+customerSupportReq.getEmailmessage()+" </td> </tr> <tr> <td style=\"height: 10px;\"></td> </tr> </table> </td> </tr> <tr> <td bgcolor=\"#131313\" style=\"padding: 20px 30px 20px 30px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr> <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\" width=\"75%\"> "+customerSupportReq.getDisclaimer()+" </td> </tr> </table> </td> </tr> </table> </td> </tr> </table> </body> </html>";
        	logger.debug("Coupon email str : {}",updateMessage);

//        	SimpleMailMessage message = new SimpleMailMessage();
//			message.setFrom(customerSupportReq.getUseremail());
//			message.setTo(customerSupportReq.getEmailto());
//			message.setSubject(custSuppReq.getEmailsubject());
//			message.setText(updateMessage);
//			
//			mailSender.send(message);

        	 MimeMessage mime = mailSender.createMimeMessage();
        	 MimeMessageHelper helper = new MimeMessageHelper(mime, true);        	
        	 helper.setFrom(customerSupportReq.getUseremail());
        	 helper.setTo(customerSupportReq.getEmailto());
        	 helper.setSubject(custSuppReq.getEmailsubject());
        	 helper.setText(updateMessage.replaceAll("\\n", "<br/>"), true);
        	 mailSender.send(mime);
        	 
			temp=1;
        }catch(Exception e){
        	logger.debug("Send mail failed : "+e.getMessage());
	        tempMailSendMessage=e.getMessage();
	        temp=0;
        }
        
        //setting email status to req object
        customerSupportReq.setEmailstatusmessage(tempMailSendMessage);
        customerSupportReq.setEmailstatus(temp);
        logger.debug("Send mail status : "+temp);
		
        //Mail sending end		
		
		//Save email details to db
		saveEmailDetails(customerSupportReq,"coupon_email");

		//Create response object
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		if(temp==1)
		{
			response.setStatus("success");
		}
		else
		{
			response.setStatus("failure");
			response.setMessage(tempMailSendMessage);
		}

		return response;
	}

	
	public CustomerSupportResponseVO emailCoupons_bkps(CustomerSupportRequestVO custSuppReq) {
		
		CustomerSupportRequestVO customerSupportReq=custSuppReq;
		

		//Create Message start
		StringBuffer updateMessage=new StringBuffer();
		updateMessage.append("VIN: ").append(customerSupportReq.getVin()).append("\n");
		updateMessage.append("Coupon Value: ").append(customerSupportReq.getCouponvalue()).append("\n");
		updateMessage.append("Email: ").append(customerSupportReq.getUseremail()).append("\n");
		updateMessage.append("Phone: ").append(customerSupportReq.getUserphone()).append("\n");
		updateMessage.append("Address: ").append(customerSupportReq.getAddress()).append("\n");
		updateMessage.append("Message: ").append(custSuppReq.getEmailmessage()).append("\n");
		updateMessage.append("Disclaimer: ").append(custSuppReq.getDisclaimer()).append("\n");
		//Create Message end
		
				
		//Mail sending start
        String tempMailSendMessage="";
        int temp=0;
        try{
			SimpleMailMessage message = new SimpleMailMessage();
		
			message.setFrom(customerSupportReq.getUseremail());
			message.setTo(customerSupportReq.getEmailto());
			message.setSubject(custSuppReq.getEmailsubject());
			message.setText(updateMessage.toString());
			
			mailSender.send(message);
			temp=1;
        }catch(Exception e){
        	logger.debug("Send mail failed : "+e.getMessage());
	        tempMailSendMessage=e.getMessage();
	        temp=0;
        }
        
        //setting email status to req object
        customerSupportReq.setEmailstatusmessage(tempMailSendMessage);
        customerSupportReq.setEmailstatus(temp);
        logger.debug("Send mail status : "+temp);
		
        //Mail sending end		
		
		//Save email details to db
		saveEmailDetails(customerSupportReq,"coupon_email");

		//Create response object
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		if(temp==1)
		{
			response.setStatus("success");
		}
		else
		{
			response.setStatus("failure");
			response.setMessage(tempMailSendMessage);
		}

		return response;
	}

	
    @Override
	public CustomerSupportResponseVO sendMail(CustomerSupportRequestVO custSuppReq) {
		
		CustomerSupportRequestVO customerSupportReq=custSuppReq;
		
		//Create Message start
		StringBuffer updateMessage=new StringBuffer();
		updateMessage.append("VIN: ").append(customerSupportReq.getVin()).append("\n");
		updateMessage.append("From: ").append(customerSupportReq.getUsername()).append("\n");
		updateMessage.append("Email: ").append(customerSupportReq.getUseremail()).append("\n");
		updateMessage.append("Phone: ").append(customerSupportReq.getUserphone()).append("\n");
		updateMessage.append("Message: \n").append(customerSupportReq.getEmailmessage()).append("\n");
		
		//Create Message end
		
				
		//Mail sending start
        String tempMailSendMessage="";
        int temp=0;
        try{
			SimpleMailMessage message = new SimpleMailMessage();
		
			message.setFrom(customerSupportReq.getUseremail());
			message.setTo(customerSupportReq.getEmailto());
			message.setSubject("My Mazda - "+customerSupportReq.getEmailsubject());
			message.setText(updateMessage.toString());
			
			mailSender.send(message);
			temp=1;
        }catch(Exception e){
        	logger.debug("Send mail failed : "+e.getMessage());
	        tempMailSendMessage=e.getMessage();
	        temp=0;
        }
        
        //setting email status to req object
        customerSupportReq.setEmailstatusmessage(tempMailSendMessage);
        customerSupportReq.setEmailstatus(temp);
        logger.debug("Send mail status : "+temp);
		
        //Mail sending end		
		
		//Save email details to db
		saveEmailDetails(customerSupportReq,"");

		//Create response object
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		if(temp==1)
			response.setStatus("success");
		else
			response.setStatus("failure");

		return response;
	}


	@Override
	public CustomerSupportResponseVO getCustomerCareInfo() {
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		response.setEmail(appConstants.MMG_CUSTOMER_SUPPORT_EMAIL);
		response.setPhone(appConstants.MMG_CUSTOMER_SUPPORT_PHONE);
		
		/*
		response.setAllowemail(appConstants.MMG_CUSTOMER_SUPPORT_EMAIL_ALLOW);
		
		*Updated for MMG Q2@2014 to move Email enable/disable configuration from Proprty file to DB.
		*/
		response.setAllowemail(this.getEmailAllowInfo());
		
		return response;
	}
	
    
	/**
	 * This method is used to create crash log of devices using MMG services
	 * @param customerSupportReq
	 * @return
	 */
	public CustomerSupportResponseVO createCrashLog(CustomerSupportRequestVO customerSupportReq) 
	{
	
		boolean fileCreated=false;
		
		String basePath=null;
		
		if(customerSupportReq.getDevicetype().equalsIgnoreCase("iphone"))
			basePath=appConstants.MMG_CUSTOMER_SUPPORT_CRASHLOGDIR_IPHONE;
		else
			basePath=appConstants.MMG_CUSTOMER_SUPPORT_CRASHLOGDIR_ANDROID;
			
		
		//write file start ----------
    	String filePath = new StringBuffer(basePath).append("\\report").append(new Date().getTime()).append(".crash").toString();
    	logger.debug("File Path = "+filePath);
    	 
        String writeToFile=customerSupportReq.getCrashdata();

        try{

        File file=new File(filePath);
        FileUtils.writeStringToFile(file, writeToFile, null);
       // FileUtils.writeByteArrayToFile(file, writeToFile);
        fileCreated=true;
        
        }catch(Exception e){
        logger.debug("Problem > "+e);
         fileCreated=false;
        }
		//write file end
		
		//save to db start ---------
        saveCrshLog(customerSupportReq, filePath);
		//save to db end
		
		
		//Create response object -------
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		if(fileCreated==true){
			response.setStatus("success");
			response.setCrashlog(filePath);
		}
		else
			response.setStatus("failure");

		return response;
	}
	
	
	
	/**
	 * This metgod is used to save email details & status to db
	 * @param customerSupportReq
	 */
	private void saveEmailDetails(CustomerSupportRequestVO customerSupportReq,String emailType) {

		logger.debug("Inside saveEmailDetails >>>");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
		conn=dataSource.getConnection();
		String query="INSERT INTO mmgcustemails(username,userphone, useremail, email_to, email_subject, email_message, email_status, email_status_message, last_update_date,vin,emailtypes) VALUES (?,?,?,?,?,?,?,?, current_timestamp,?,?)";
		logger.debug("Query >>>"+query);
		stmt=conn.prepareStatement(query);
		stmt.setString(1,customerSupportReq.getUsername());
		stmt.setString(2,customerSupportReq.getUserphone());
		stmt.setString(3,customerSupportReq.getUseremail());
		stmt.setString(4,customerSupportReq.getEmailto());
		stmt.setString(5,customerSupportReq.getEmailsubject());
		stmt.setString(6,customerSupportReq.getEmailmessage());
		stmt.setInt(7,customerSupportReq.getEmailstatus());
		stmt.setString(8,customerSupportReq.getEmailstatusmessage());
		stmt.setString(9,customerSupportReq.getVin());
		stmt.setString(10,emailType);

		int temp=stmt.executeUpdate();
		System.out.println("No of affected records : "+temp);
		
		}catch(Exception e){
			logger.error("Problem -  saveEmailDetails > "+e);
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
	
		logger.debug("<<< Exiting saveEmailDetails ");
	}


	/**
	 * This metgod is used to save crash log to db
	 * @param customerSupportReq
	 */
	private void saveCrshLog(CustomerSupportRequestVO customerSupportReq,String crashLogLocation) {

		logger.debug("Inside saveCrshLog >>>");
		
		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
		conn=dataSource.getConnection();
		String query="INSERT INTO mmgcrashlog(devicetype, crash_logfile_location, crash_log_data, last_update_date,guid, device_model) VALUES (?,?,?, current_timestamp,?,?)";
		logger.debug("Query >>>"+query);
		stmt=conn.prepareStatement(query);
		stmt.setString(1,customerSupportReq.getDevicetype());
		stmt.setString(2,crashLogLocation);
		stmt.setString(3,customerSupportReq.getCrashdata());

		stmt.setString(4,customerSupportReq.getGuid());
		stmt.setString(5,customerSupportReq.getDevicemodel());

		int temp=stmt.executeUpdate();
		logger.debug("No of affected records : "+temp);
		
		}catch(Exception e){
			logger.error("Problem -  saveCrshLog > "+e);
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
	
		logger.debug("<<< Exiting saveCrshLog ");
	}

	/**
	 * This method is responsible for fetching crash agreement message.
	 */
	public CustomerSupportResponseVO getCrashAgreementMessage() {
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		response.setMessage(appConstants.MMG_CUSTOMER_SUPPORT_CRASHAGREEMENT_MESSAGE);
		return response;
	}


	/**
	 * 
	 */
	public CustomerSupportResponseVO getSsgInfo(String year, String model) {
		logger.debug("<<<<<<<<Inside getSsgInfo Service");
		
		CustomerSupportResponseVO response=null;

		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		String query="SELECT ssgxml_path, ssgxml_ver, ssgresource_path, ssgresource_ver FROM mmgssg WHERE vehicle_year=? AND vehicle_model=?";
		logger.debug("Query : "+query);

		stmt=conn.prepareStatement(query);
		stmt.setString(1,year);
		stmt.setString(2,model);
		
		resultSet=stmt.executeQuery();
		
			while(resultSet.next())
			{
				response=new CustomerSupportResponseVO();
				response.setSsgfile(resultSet.getString(1));
				response.setSsgfilever(resultSet.getString(2));
				response.setResourcefile(resultSet.getString(3));
				response.setResourcefilever(resultSet.getString(4));
				
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getSsgInfo > "+e);
			response=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

		return response;
	}

	
	
	public CustomerSupportResponseVO getSsgInfo(String year, String model,String trim) {
		logger.debug("<<<<<<<<Inside new getSsgInfo Service");
		
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		
		String imgBaseUrl=appConstants.MMG_SSG_BASEURL_IMG;
		String vdoBaseUrl=appConstants.MMG_SSG_BASEURL_VDO;
		String xmlBaseUrl=appConstants.MMG_SSG_BASEURL_XML;
		String trimName="Default";
		String fileName="ssg.xml";
		
		if(trim!=null && !trim.isEmpty())
			trimName=trim;
		
		StringBuffer tempUrl=new StringBuffer();
		tempUrl.append("/").append(year).append("/").append(model).append("/").append(trimName).append("/").append(fileName);
		
		//Verify URL > If exist pass it >or empty string
		String createdUrl=xmlBaseUrl+tempUrl.toString().toUpperCase();
		logger.debug("Req XML URL : "+createdUrl);
		
		if(verifyUrl(createdUrl)==true)
		response.setSsgfile(createdUrl);
		else
		response.setSsgfile("");
		
		//Creating images & videos base URL
		String createdImageBaseUrl=imgBaseUrl+new StringBuffer().append("/").append(year).append("/").append(model).append("/").append("images").toString().toUpperCase();
		logger.debug("Req image base URL : "+createdImageBaseUrl);
		String createdVideoBaseUrl=vdoBaseUrl+new StringBuffer().append("/").append(year).append("/").append(model).append("/").append("videos").toString().toUpperCase();
		logger.debug("Req video base URL : "+createdVideoBaseUrl);
		
		response.setImagebaseurl(createdImageBaseUrl);
		response.setVideobaseurl(createdVideoBaseUrl);
		
		return response;
	}


	public CustomerSupportResponseVO getSsgInfoForWeb(String year, String model,String trim,String userAgentOfDevice) {
		logger.debug("<<<<<<<<Inside new getSsgInfoForWeb Service");
		
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		
		String imgBaseUrl="";
		String vdoBaseUrl=appConstants.MMG_SSG_BASEURL_VDO;
		String xmlBaseUrl="";
		
		if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_ANDROID))
		{
    		imgBaseUrl=appConstants.MMG_SSG_BASEURL_IMG_WEB_ANDROID;
    		xmlBaseUrl=appConstants.MMG_SSG_BASEURL_XML_WEB_ANDROID;
    		
        }else if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPHONE))
        {
    		imgBaseUrl=appConstants.MMG_SSG_BASEURL_IMG_WEB_IPHONE;
    		xmlBaseUrl=appConstants.MMG_SSG_BASEURL_XML_WEB_IPHONE;
    		
        }else if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPAD))
        {
    		imgBaseUrl=appConstants.MMG_SSG_BASEURL_IMG_WEB_IPAD;
    		xmlBaseUrl=appConstants.MMG_SSG_BASEURL_XML_WEB_IPAD;
    		
    	}else{
    		
    		imgBaseUrl=appConstants.MMG_SSG_BASEURL_IMG_WEB;
    		xmlBaseUrl=appConstants.MMG_SSG_BASEURL_XML_WEB;           
        }
		
		
		//Common code
		String trimName="Default";
		String fileName="ssg.xml";
		
		if(trim!=null && !trim.isEmpty())
			trimName=trim;
		
		StringBuffer tempUrl=new StringBuffer();
		tempUrl.append("/").append(year).append("/").append(model).append("/").append(trimName).append("/").append(fileName);
		
		//Verify URL > If exist pass it >or empty string
		String createdUrl=xmlBaseUrl+tempUrl.toString().toUpperCase();
		logger.debug("Req XML URL : "+createdUrl);
		
		if(verifyUrl(createdUrl)==true)
		response.setSsgfile(createdUrl);
		else
		response.setSsgfile("");
		
		//Creating images & videos base URL
		String createdImageBaseUrl=imgBaseUrl+new StringBuffer().append("/").append(year).append("/").append(model).append("/").append("images").toString().toUpperCase();
		logger.debug("Req image base URL : "+createdImageBaseUrl);
		String createdVideoBaseUrl=vdoBaseUrl+new StringBuffer().append("/").append(year).append("/").append(model).append("/").append("videos").toString().toUpperCase();
		logger.debug("Req video base URL : "+createdVideoBaseUrl);
		
		response.setImagebaseurl(createdImageBaseUrl);
		response.setVideobaseurl(createdVideoBaseUrl);
		
		return response;
	}

	
	
	
	private boolean verifyUrl(String urlx)
	{
        boolean okToGo=false; 
        try {
            URL url = new URL(urlx);
            HttpURLConnection   conn = (HttpURLConnection)url.openConnection();
            int rc = conn.getResponseCode();
            conn.disconnect();
            
            if(rc>=200 && rc<300)
                    okToGo=true; 
            
        } catch (Exception ex) {
            okToGo=false; 
            logger.debug("error verify URL : "+ex.getMessage());
        }

		return okToGo;
	}


	/**
	 * This method picks APP version from the DB based on appType.
	 * @param appType (android/iphone)
	 * @return APP version.
	 */
	private String getAppVersionFromDb(String appType) {

		String appVer=null;
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		if(appType.equalsIgnoreCase("android"))
			stmt=conn.prepareStatement("SELECT param_value FROM mmgserviceparams where param_key='android_app_ver'");
		else
			stmt=conn.prepareStatement("SELECT param_value FROM mmgserviceparams where param_key='iphone_app_ver'");
		
		resultSet=stmt.executeQuery();

			if(resultSet.next())
			{
				appVer=resultSet.getString(1);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getAppVersionFromDb > "+e);

		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

	return appVer;
	
	}
		
	/**
	 * This method is used to get email enable/disable configuration from database.
	 * @return yes/no
	 */
	private String getEmailAllowInfo() {

		String emailAllow=null;
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		stmt=conn.prepareStatement("SELECT param_value FROM mmgserviceparams where param_key='EMAIL_ALLOW'");
		
		resultSet=stmt.executeQuery();

			if(resultSet.next())
			{
				emailAllow=resultSet.getString(1);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getEmailAllowInfo > "+e);

		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

	return emailAllow;
	
	}
		
	
	@Override
	public ArrayList getServiceTypes() {

		ArrayList<String> tempList=new ArrayList<String>();

		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		stmt=conn.prepareStatement("SELECT param_key, param_value FROM mmgserviceparams where param_type='stype'");
		
		resultSet=stmt.executeQuery();

			while(resultSet.next())
			{
				StringBuffer temp=new StringBuffer(resultSet.getString(1)).append(DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL_SEPARACTOR).append(resultSet.getString(2));
				tempList.add(temp.toString());	
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getServiceTypes > "+e);
			tempList.add(e.getMessage());
			tempList.add(e.toString());
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

	return tempList;
	
	}
	

	
	@Override
	public ArrayList getTestInfo(String qry) {

		ArrayList<String> tempList=new ArrayList<String>();

		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		stmt=conn.prepareStatement("select * from "+qry);
		
		resultSet=stmt.executeQuery();

		int t=resultSet.getMetaData().getColumnCount();
		
			while(resultSet.next())
			{
				StringBuffer temp=new StringBuffer();
				for(int i=1;i<=t;i++)
				{
					temp.append(resultSet.getString(i)).append("\t");
				}
				tempList.add(temp.toString());	
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getSsgInfo > "+e);
			tempList.add(e.getMessage());
			tempList.add(e.toString());
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		

	return tempList;
	
	}
	

	@Override
	public CustomerSupportResponseVO getJciLink() {
		CustomerSupportResponseVO response=new CustomerSupportResponseVO();
		try{
		
			response.setJciurl(appConstants.MMG_CUSTOMER_SUPPORT_URL_JCILINK);
			response.setStatus(appConstants.STATUS_SUCCESS);
		}catch(Exception e){
			response.setStatus(appConstants.STATUS_FAILURE);
			logger.error("Problem - getJciLink > "+e);
			}
		
		return response;
	}
	
	
	public static void main(String[] arg)
	{
		String updateMessage=new String("<html> <head> <meta charset=\"utf-8\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <title></title> <link rel=\"stylesheet\" href=\"\"> <style type=\"text/css\"> body{background: #e3efff; padding: 0px; margin: 0px; font-family: arial; font-size: 12px; color: #363636;} .clearfix{clear: both;} .container{ position: relative; width: 100%; padding: 0 20px;} .wrapper{ width: 640px; margin: 0 auto; position: relative; background: #fff;} .header{ padding: 20px 10px; position: relative;} .vin-no{ float: left; width: 45%; height: auto;} .address{ float: right; width: 45%; text-align: right;} .offer-contaienr{padding: 10px; position: relative;} .offer-btn-wrapper{ background-color:#313131; box-shadow:5px 5px 15px 2px #1a1a1a inset; padding: 5px; -moz-border-radius:10px; -webkit-border-radius:10px; border-radius:10px; } h1{ font-size: 24px; color: #43b1da; text-decoration: none; font-weight: normal; padding: 0px 30px; text-align: center; font-style: italic; line-height: 50px;} .offer-title{width: 70%; float: left; text-align: center;} .offer-btn-contaier{ width: 25%; float: right;} .offer-btn{ background: #889099; /* Old browsers */ background: -moz-linear-gradient(top, #889099 0%, #414952 50%, #727984 100%); /* FF3.6+ */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#889099), color-stop(50%,#414952), color-stop(100%,#727984)); /* Chrome,Safari4+ */ background: -webkit-linear-gradient(top, #889099 0%,#414952 50%,#727984 100%); /* Chrome10+,Safari5.1+ */ background: -o-linear-gradient(top, #889099 0%,#414952 50%,#727984 100%); /* Opera 11.10+ */ background: -ms-linear-gradient(top, #889099 0%,#414952 50%,#727984 100%); /* IE10+ */ background: linear-gradient(to bottom, #889099 0%,#414952 50%,#727984 100%); /* W3C */ filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#889099', endColorstr='#727984',GradientType=0 ); /* IE6-9 */ text-decoration: none; color: #f1e63c; text-align: center; padding: 30px 10px; display: block; font-size: 18px; -moz-border-radius:10px; -webkit-border-radius:10px; border-radius:10px; } .msg-wrapper{ padding: 10px; text-align: justify; font-weight: normal; line-height: 18px; font-size: 13px; min-height: 250px;} .small-text{ font-size: 10px; text-align: justify; font-weight: normal;} .footer-wrapper{ margin-top: 20px; background: #1e1e1e;} .footer-cont{ padding: 10px; font-size: 11px; color: #fff; font-weight: normal; text-align: justify;} </style> </head> <body> <div class=\"container\"> <div class=\"wrapper\"> <div class=\"header\"> <div class=\"vin-no\"> <p><strong>VIN:</strong>##Vin##</p> </div> <div class=\"address\"> ##Address##<br/>##Phone## </div> <div class=\"clearfix\"></div> </div> <div class=\"offer-contaienr\"> <div class=\"offer-btn-wrapper\"> <div class=\"offer-title\"><h1>##MailSubject##</h1></div> <div class=\"offer-btn-contaier\"> <a href=\"\" class=\"offer-btn\">##CouponValue##</a> </div> <div class=\"clearfix\"></div> </div> </div> <div class=\"msg-wrapper\"> <p> ##MailMessage## </p> </div> <div class=\"footer-wrapper\"> <div class=\"footer-cont\"> ##Disclaimer## </div> </div> </div> </div> </body> </html>");
		System.out.println(updateMessage.indexOf("##Vin##"));
		updateMessage.replaceAll("##Vin##", "213654");
		updateMessage.replaceAll("##MailSubject##", "Test subject");
		updateMessage.replaceAll("##CouponValue##", "$101");
		updateMessage.replaceAll("##Address##", "Test address");
		updateMessage.replaceAll("##Phone##", "9990451469");
		updateMessage.replaceAll("##MailMessage##","test message");
		updateMessage.replaceAll("##Disclaimer##", "test disclaimer");
		
		System.out.println(updateMessage);
	}

}
