/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.mazdausa.mmg.web.client.response.vo.UserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;
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
 * This Controller, will be responsible for performing all the tasks related to User Vehicle
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/maintenanceschedule")
public class UserVehicleMaintenanceController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserVehicleMaintenanceController.class);
    @Autowired
    UserVehicleServiceIFace userVehicleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/vehicle/{vin}/drivinghabit/{drivingHabit}")
    public UserVehicleMaintenanceResponseVO retrieveUserVehicleMaintenanceSchedule(@PathParam("vin") String vin, @PathParam("drivingHabit") String drivingHabit, @Context HttpServletRequest request) {


        logger.debug(" Entering {} retrieveUserVehicleMaintenanceSchedule() with Parameters = {}", this.getClass(), new Object[]{vin, drivingHabit});
        UserVehicleMaintenanceResponseVO userVehicleMaintenanceSchedule = null;

        // Do the Logging part here
        userVehicleMaintenanceSchedule = userVehicleService.getVehicleSchedule(vin.trim(), drivingHabit.trim());
        logger.debug("<< Exiting retrieveUserVehicleMaintenanceSchedule() with response ={}" + userVehicleMaintenanceSchedule);


        return userVehicleMaintenanceSchedule;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/model/{model}/year/{year}/drivinghabit/{drivingHabit}")
    public UserVehicleMaintenanceResponseVO retrieveUserVehicleMaintenanceSchedule(@PathParam("model") String model,
            @PathParam("year") String year,
            @PathParam("drivingHabit") String drivingHabit,
            @Context HttpServletRequest request) {


        logger.debug(" Entering {} retrieveUserVehicleMaintenanceSchedule() with Parameters = {}", this.getClass(), new Object[]{model,year, drivingHabit});
        UserVehicleMaintenanceResponseVO userVehicleMaintenanceSchedule = null;

        // Do the Logging part here
        userVehicleMaintenanceSchedule = userVehicleService.getVehicleSchedule(model.trim(), year.trim(), drivingHabit.trim());
        logger.debug("<< Exiting retrieveUserVehicleMaintenanceSchedule() with response ={}" + userVehicleMaintenanceSchedule);


        return userVehicleMaintenanceSchedule;
    }
}

