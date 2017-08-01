/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.mazdausa.mmg.web.client.request.vo.UserVehicleMileageUpdateRequestVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleMileageUpdateResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleGetMileageResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
 * This Controller will contain all the functions related to managing the MILEAGE of the vehicle.
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/mileage")
public class UserVehicleMileageController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserVehicleMileageController.class);
    @Autowired
    UserVehicleServiceIFace vehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/vehicle/{vin}")
    public VehicleGetMileageResponseVO getVehicleMileage(@PathParam("vin") String vin, @PathParam("userid") String customerId, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getVehicleMileage() withVIN = {}", vin);
        VehicleGetMileageResponseVO userVehicleMileageResponseVO = null;

        // Do the Logging part here
        userVehicleMileageResponseVO = (VehicleGetMileageResponseVO) vehicleService.getVehicleMileage(vin);

        logger.debug("<< Exiting getVehicleMileage() with response ={}" + userVehicleMileageResponseVO);
        return userVehicleMileageResponseVO;
    }

    /**
     * This is the function, which will be invoked when the client will sent an request of updating the Vehicle Mileage Request.
     * @param userVehicleMileageUpdateRequestVO
     * @param vin
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/vehicle/{vin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserVehicleMileageUpdateResponseVO updateVehicleMileage(UserVehicleMileageUpdateRequestVO userVehicleMileageUpdateRequestVO,
            @PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering {} updateVehicleMileage() with VIN = {}", vin);
        UserVehicleMileageUpdateResponseVO userVehicleMileageUpdateResponseVO = null;

        // Setting the INPUT Variable VIN.
        userVehicleMileageUpdateRequestVO.setVin(vin);

        // Do the Logging part here
        userVehicleMileageUpdateResponseVO = (UserVehicleMileageUpdateResponseVO) vehicleService.updateVehicleMileage(userVehicleMileageUpdateRequestVO);

        logger.debug("<< Exiting updateVehicleMileage() with response ={}" + userVehicleMileageUpdateResponseVO);
        return userVehicleMileageUpdateResponseVO;
    }
}
