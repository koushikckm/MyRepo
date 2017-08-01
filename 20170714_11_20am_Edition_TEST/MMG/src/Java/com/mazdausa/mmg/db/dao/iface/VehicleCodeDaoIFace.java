/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.VehicleCodeVO;
import java.util.List;

/**
 * This interface will deal with all the function related to vehicle year and model details.
 * @author pankajkh
 * @version 1.0
 */
public interface VehicleCodeDaoIFace {

    /*
     * This function will all vehicle by year
     * @param vehicleIdent
     * @return
     */
    public List<VehicleCodeVO> getAllVehicleModels();

    /*
     * This function will return vehicle by year
     * @param vehicleIdent
     * @return
     */
    public List<VehicleCodeVO> getVehicleByYear(String vehicleYear);

    /*
     * This function will return vehicle by Model
     * @param vehicleIdent
     * @return
     */
    public List<VehicleCodeVO> getVehicleByModel(String vehicleModel);

    /*
     * This function will add the vehicle details in to the DB table
     * @param VehicleCodeVO
     */
    public void addVehicleCode(VehicleCodeVO vehicleCodeVO);

    /*
     * This function will update the vehicle details in to the DB table
     * @param VehicleCodeVO
     */
    public void updateVehicleCode(VehicleCodeVO vehicleCodeVO);

    /*
     * This function will delete the vehicle details from the DB table
     * @param VehicleCodeVO
     */
    public void deleteVehicleCode(VehicleCodeVO vehicleCodeVO);
}
