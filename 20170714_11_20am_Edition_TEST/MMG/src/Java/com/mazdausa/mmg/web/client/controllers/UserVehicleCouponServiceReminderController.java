/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleCouponReminderRequestVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleCouponResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleReminderResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleServiceReminderVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This Controller, will be responsible for Handling all the request related to Coupon and ServiceReminders of a user.
 * @author PankajB
 */
@Controller
@Path("/user/{userid}/vehicle/{vin}")
public class UserVehicleCouponServiceReminderController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserVehicleCouponServiceReminderController.class);
    @Autowired
    UserVehicleServiceIFace vehicleService;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    ApplicationConstants appConstants;

    /**
     * This Function will be responsible for handling the Service Reminders Request from the client.
     * @param userId
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/servicereminders")
    public UserVehicleReminderResponseVO getVehicleServiceReminders(@PathParam("userid") String userId,
            @PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering getVehicleServiceReminders() with VIN = {}", vin);
        UserVehicleCouponReminderRequestVO userVehicleCouponRequestVO = new UserVehicleCouponReminderRequestVO();
        UserVehicleReminderResponseVO userVehicleReminderResponseVO = null;

        userVehicleCouponRequestVO.setCustId(userId);

        // This below code is to make sure, that VIN is always of 17 Characters.
        try {
            if (vin != null) {
                vin = vin.trim();
                int vinLength = vin.length();
                if (vin.length() > 17) {
                    vin = vin.substring(vinLength - 17);
                    System.out.println("Final VIN = " + vin);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        userVehicleCouponRequestVO.setVin(vin);

        // Do the Logging part here
        // userVehicleReminderResponseVO = (UserVehicleReminderResponseVO) vehicleService.getVehicleServiceReminders(userVehicleCouponRequestVO);
        userVehicleReminderResponseVO = new UserVehicleReminderResponseVO();
        userVehicleReminderResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
        userVehicleReminderResponseVO.setDescription(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_ERROR_MESSAGE);

        logger.debug("<< Exiting getVehicleServiceReminders() with response ={}" + userVehicleReminderResponseVO);
        return userVehicleReminderResponseVO;
    }

    /**
     * This Function will be responsible for handling the COUPONS Request from the client.
     * @param userId
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/coupons")
    public UserVehicleCouponResponseVO getVehicleCoupons(@PathParam("userid") String userId,
            @PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering getVehicleCoupons() with VIN = {}", vin);
        UserVehicleCouponReminderRequestVO userVehicleCouponRequestVO = new UserVehicleCouponReminderRequestVO();
        UserVehicleCouponResponseVO userVehicleCouponResponseVO = null;

        userVehicleCouponRequestVO.setCustId(userId);
        userVehicleCouponRequestVO.setVin(vin);

        // Do the Logging part here
        userVehicleCouponResponseVO = (UserVehicleCouponResponseVO) vehicleService.getVehicleCoupons(userVehicleCouponRequestVO,request);

        logger.debug("<< Exiting getVehicleCoupons() with response ={}" + userVehicleCouponResponseVO);
        return userVehicleCouponResponseVO;
    }

    /**
     * This Function will be responsible for handling the Service Reminders(New Service Reminder web service) Request from the client.
     * @param userId
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/servicereminders/v1")
    public UserVehicleReminderResponseVO getVehicleServiceRemindersNew(@PathParam("userid") String userId,
            @PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering getVehicleServiceRemindersNew() with VIN = {}", vin);
        UserVehicleCouponReminderRequestVO userVehicleCouponRequestVO = new UserVehicleCouponReminderRequestVO();
        UserVehicleReminderResponseVO userVehicleReminderResponseVO = null;

        userVehicleCouponRequestVO.setCustId(userId);

        // This below code is to make sure, that VIN is always of 17 Characters.
        try {
            if (vin != null) {
                vin = vin.trim();
                int vinLength = vin.length();
                if (vin.length() > 17) {
                    vin = vin.substring(vinLength - 17);
                    System.out.println("Final VIN = " + vin);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        userVehicleCouponRequestVO.setVin(vin);

        userVehicleReminderResponseVO = (UserVehicleReminderResponseVO) vehicleService.getVehicleServiceReminders(userVehicleCouponRequestVO, request);

        logger.debug("<< Exiting getVehicleServiceRemindersNew() with response ={}" + userVehicleReminderResponseVO);
        /**
         * The Following code is introduced on 26 Sep 2010 because of the Client Issue.
         */
        long time = System.currentTimeMillis();
        List<UserVehicleServiceReminderVO> srList = userVehicleReminderResponseVO.getServiceReminders();
        int i = 1;
        if(srList!=null)
        for (UserVehicleServiceReminderVO srVO : srList) {
            List<CouponRemindersDataDetailVO> ddvoList = srVO.getCoupons();
            if(ddvoList !=null)
            for (CouponRemindersDataDetailVO dd : ddvoList) {
                if (dd.getCouponNumber() != null) {
                    dd.setCouponNumber(dd.getCouponNumber() + time + i + "");
                } else {
                    dd.setCouponNumber(time + i + "");
                }
                i++;
            }

        }
        return userVehicleReminderResponseVO;
    }
}
