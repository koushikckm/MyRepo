/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.business.impl;

import com.mazdausa.mmg.business.iface.VehicleBusIFace;
import com.mazdausa.mmg.db.dao.iface.VehicleCodeDaoIFace;
import com.mazdausa.mmg.db.vo.VehicleCodeVO;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is the BL Implementation class, which will implement all the individual functions defined in the VehicleBusIFace
 * @author PankajB
 * @version 1.0
 */
@Component
public class VehicleBusImpl implements VehicleBusIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VehicleBusImpl.class);
    @Autowired
    VehicleCodeDaoIFace vehicleCodeDao;

    /**
     * Function returning the List of  Vehicle Codes .
     * @return
     */
    public List<VehicleCodeVO> getAllVehicleModels() {
        logger.debug(">> Entering getAllVehicleModels() ");
        List<VehicleCodeVO> vehicleCodeList = null;
        vehicleCodeList = vehicleCodeDao.getAllVehicleModels();
        if (vehicleCodeList == null) {
            vehicleCodeList = new ArrayList<VehicleCodeVO>();
        }
        logger.debug("<< Exiting getAllVehicleModels() with result={} ", vehicleCodeList.size());
        return vehicleCodeList;


    }

    /**
     * This is the function, which will be responsible for returning the Models for an Year.
     * @param year
     * @return
     */
    public List<VehicleCodeVO> getVehicleModelsForYear(String year) {
        logger.debug(">> Entering getAllVehicleModels() ");
        List<VehicleCodeVO> vehicleCodeList = null;
        vehicleCodeList = vehicleCodeDao.getVehicleByYear(year);
        logger.debug("<< Exiting getAllVehicleModels() with result={} ", vehicleCodeList);
        return vehicleCodeList;
    }
}
