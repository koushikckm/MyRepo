/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.UserVehicleVO;
import java.util.List;

/**
 * This Interface Deals with all the functions related to the management of operations in the User Vehicle Table.
 * @author PankajB
 * @version 1.0
 */
public interface UserVehicleDaoIFace {

    /**
     * Function to return User Vehicle
     * @param userVehicleId
     * @return
     */
    public UserVehicleVO getUserVehicle(int userVehicleId);


    /**
     * Function to return the User Vehicle by VIN.
     * @param userVehicleVIN
     * @return
     */
    public List<UserVehicleVO> getUserVehicleByVIN(String userVehicleVIN);

    /**
     * Function to return List of User Vehicle by CustomerID
     * @param customerId
     * @return
     */
    public List<UserVehicleVO> getUserVehiclesByCustomerId(String customerId);

    /**
     * Function to return List of User Vehicle by MAIL ID
     * @param userMailId
     * @return
     */
    public List<UserVehicleVO> getUserVehiclesByUserMailId(String userMailId);

    /**
     * This Function, will be responsible for getting a Customer Vehicle details by providing the CustomerID and VIN
     * @param customerId
     * @param userVehiclevin
     * @return
     */
    public UserVehicleVO getUserVehiclesByCustomerId(String customerId,String userVehiclevin);
    /**
     * Function to Add the User Vehicle.
     * @param userVehicleVO
     */
    public void addUserVehicle(UserVehicleVO userVehicleVO);

    /**
     * Function to update the details of User Vehicle
     * @param userVehicleVO
     */
    public void updateUserVehicle(UserVehicleVO userVehicleVO);

    /**
     * Function to delete the User Vehicle
     * @param userVehicleVO
     */
    public void deleteUserVehicle(UserVehicleVO userVehicleVO);

    /**
     * Function to return the list of all User Vehicles.
     * @return
     */
    public List<UserVehicleVO> getAllUserVehicles();

    

    

}
