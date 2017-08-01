/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.business.iface;

import com.mazdausa.mmg.db.vo.UserVO;
import com.mazdausa.mmg.db.vo.UserVehicleVO;
import java.util.List;

/**
 * This is a BL Layer user interface, that will contain the listing of all the functions, related to User.
 * @author PankajB
 * @version 1.0
 */
public interface UserBusIFace {

     /**
     * This Function will be responsible for returning the USeR by User Id.
     * @param userId UserId
     * @return
     */
    public UserVO getUser(int userId);


    /**
     * This Function will return the User By its Mail ID
     * @param userMailId
     * @return
     */
    public UserVO getUserByMailId(String userMailId);

    /**
     * This Function will return the User by its Customer Id
     * @param mazdaCustId Customer Id
     * @return
     */
    public UserVO getUserByCustomerId(String mazdaCustId);

    /**
     * This Function will add the User in the database.
     * @param userVO
     */
    public boolean addUser(UserVO userVO);

    /**
     * This Function will update the details of the User in the Database.
     * @param userVO
     */
    public boolean updateUser(UserVO userVO);

    /**
     * This Function will mark the User deleted in the database.
     * @param userVO
     */
    public boolean deleteUser(UserVO userVO);

    /**
     * This Function will return the list of all the User present in the database.
     */
    public List<UserVO> findAllUsers();
    
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
    public UserVehicleVO getUserVehicleByVIN(String userVehicleVIN);

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
     * Function to Add the User Vehicle.
     * @param userVehicleVO
     */
    public boolean addUserVehicle(UserVehicleVO userVehicleVO);

    /**
     * This is the function, which is responsible for adding a Vehicle to an Customer.
     * @param customerId
     * @param vin
     * @return
     */
    public void updateOwnershipOfUserVehicle(String customerId,String vin);
    
    /**
     * This is the function, which is responsible for adding a Vehicle name.
     * @param customerId
     * @param vin
     * @return
     */
    public void updateOwnershipOfUserVehicleCarline(String customerId, String vin, String vinVehicleName);
    
    /**
     * This function is responsible for adding a vehicle's vin & name to a customer.
     * @param customerId
     * @param vin
     * @param vinVehicleTitle
     */
    public void updateOwnershipOfUserVehicle(String customerId,String vin,String vinVehicleTitle);

    
    /**
     * Function to update the details of User Vehicle
     * @param userVehicleVO
     */
    public boolean updateUserVehicle(UserVehicleVO userVehicleVO);

    /**
     * Function to delete the User Vehicle
     * @param userVehicleVO
     */
    public boolean deleteUserVehicle(UserVehicleVO userVehicleVO);

    /**
     * Function to return the list of all User Vehicles.
     * @return
     */
    public List<UserVehicleVO> getAllUserVehicles();

    /**
     * This Function will be responsible for checking whether the user exists in the database or not, if not, then it inserts the user in the database.
     * @param userVO
     * @return
     */
    public boolean checkUserExistsOrInsert(UserVO userVO);


    /**
     * This is the function which will be responsible for checking whether what operation we need to perform on the passed UserVehicles {}
     * @param customerId
     * @param userVehicles
     * @param delete  If it is true, it determines that we need to delete all the mentioned User Vehicles from the DB.
     */
    public void manageUserVehicles(String customerId, UserVehicleVO[] userVehicles, boolean delete);

    /**
     * This is the function, which will be responsible for returning a fragment of Users that exists in the database, as per the parameters values.
     * @param noOfResultsRequired
     * @param startingRange
     * @return
     */
    public List<UserVO> getAllActiveUsers(int noOfResultsRequired,int startingRange);

}
