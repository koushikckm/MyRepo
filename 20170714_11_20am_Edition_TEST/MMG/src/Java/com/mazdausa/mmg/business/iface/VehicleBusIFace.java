/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.business.iface;

import com.mazdausa.mmg.db.vo.VehicleCodeVO;
import java.util.List;

/**
 * This is the interface, which will contain the list of functions, that are independently applied to an Vehicle.
 * @author PankajB
 * @version 1.0
 */
public interface VehicleBusIFace {

    /**
     * This is the function which will return all the  Vehicle Models present in the database.
     * @return
     */
    public List<VehicleCodeVO> getAllVehicleModels();

    /**
     * This is the function, which will return the list of vehicles for an Year.
     * @param year
     * @return
     */
    public List<VehicleCodeVO> getVehicleModelsForYear(String year);
}
