/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.business.impl;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;
import java.util.Calendar;
import java.util.Date;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Async;
import com.mazdausa.mmg.business.iface.UserBusIFace;
import com.mazdausa.mmg.db.dao.iface.ApplicationUsersDaoIFace;
import com.mazdausa.mmg.db.dao.iface.UserVehicleDaoIFace;
import com.mazdausa.mmg.db.vo.UserVO;
import com.mazdausa.mmg.db.vo.UserVehicleVO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static ch.lambdaj.Lambda.*;

/**
 * This is an implementation of User Business layer Interface.
 * @author PankajB
 */
@Component
public class UserBusImpl implements UserBusIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserBusImpl.class);
    @Autowired
    ApplicationUsersDaoIFace appUserDao;
    @Autowired
    UserVehicleDaoIFace userVehicleDao;

    /**
     * This Function returns the User
     * @param userId
     * @return
     */
    public UserVO getUser(int userId) {
        logger.debug(">> Entering {} getUser() with userId= {}", this.getClass(), userId);
        UserVO userVO = null;
        try {
            userVO = appUserDao.getUser(userId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the User from DB ", ex);
        }
        logger.debug("<< Exiting getUser() with Result = {}", userVO);
        return userVO;
    }

    /**
     * Returns the User by Mail ID
     * @param userMailId
     * @return
     */
    public UserVO getUserByMailId(String userMailId) {
        logger.debug(">> Entering {} getUserByMailId() with userId= {}", this.getClass(), userMailId);
        UserVO userVO = null;
        try {
            userVO = appUserDao.getUserByMailId(userMailId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the User from DB ", ex);
        }
        logger.debug("<< Exiting getUserByMailId() with Result = {}", userVO);
        return userVO;
    }

    /**
     * get User by CustomerID
     * @param mazdaCustId
     * @return
     */
    public UserVO getUserByCustomerId(String mazdaCustId) {
        logger.debug(">> Entering {} getUserByCustomerId() with userId= {}", this.getClass(), mazdaCustId);
        UserVO userVO = null;
        try {
            userVO = appUserDao.getUserByCustomerId(mazdaCustId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the User from DB ", ex);
        }
        logger.debug("<< Exiting getUserByCustomerId() with Result = {}", userVO);
        return userVO;
    }

    /**
     * Add an User
     * @param userVO
     */
    public boolean addUser(UserVO userVO) {
        logger.debug(">> Entering {} addUser() ", this.getClass());
        boolean result = false;
        try {
            appUserDao.addUser(userVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Adding Application User in the database. ", ex);
        }
        logger.debug("<< Exiting addUser() with Result = {}", result);
        return result;
    }

    /**
     * This Function will update the User.
     * @param userVO
     * @return
     */
    public boolean updateUser(UserVO userVO) {
        logger.debug(">> Entering {} updateUser() ", this.getClass());
        boolean result = false;
        try {
            appUserDao.updateUser(userVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Updating User in the database. ", ex);
        }
        logger.debug("<< Exiting updateUser() with Result = {}", result);
        return result;
    }

    /**
     * This Function will delete the User from the database.
     * @param userVO
     * @return
     */
    public boolean deleteUser(UserVO userVO) {
        logger.debug(">> Entering {} deleteUser() ", this.getClass());
        boolean result = false;
        try {
            appUserDao.updateUser(userVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Deleting User in the database. ", ex);
        }
        logger.debug("<< Exiting deleteUser() with Result = {}", result);
        return result;
    }

    /**
     * This Function will return the list of UserVO.
     * @return
     */
    public List<UserVO> findAllUsers() {
        logger.debug(">> Entering {} findAllUsers() ", this.getClass());
        List<UserVO> listOfUsers = null;
        try {
            listOfUsers = appUserDao.findAllUsers();
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the User from DB ", ex);
        }
        logger.debug("<< Exiting findAllUsers() with Result = {}", extract(listOfUsers, on(UserVO.class).getMailId()));
        return listOfUsers;
    }

    /**
     * Function to return an UserVehicle
     * @param userVehicleId
     * @return
     */
    public UserVehicleVO getUserVehicle(int userVehicleId) {
        logger.debug(">> Entering {} getUserVehicle() with user Vehicle ID= {}", this.getClass(), userVehicleId);
        UserVehicleVO userVehicleVO = null;
        try {
            userVehicleVO = userVehicleDao.getUserVehicle(userVehicleId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the User from DB ", ex);
        }
        logger.debug("<< Exiting getUserVehicle() with Result = {}", userVehicleVO);
        return userVehicleVO;
    }

    /**
     * This function will return the User Vehicle by VIN
     * @param userVehicleVIN
     * @return
     */
    public UserVehicleVO getUserVehicleByVIN(String userVehicleVIN) {
        logger.debug(">> Entering {} getUserVehicleByVIN() with user Vehicle ID= {}", this.getClass(), userVehicleVIN);
        List<UserVehicleVO> listOfUserVehicles = null;
        UserVehicleVO userVehicleVO = null;
        try {
            listOfUserVehicles = userVehicleDao.getUserVehicleByVIN(userVehicleVIN);
            if (listOfUserVehicles != null && listOfUserVehicles.size() > 0) {
                userVehicleVO = listOfUserVehicles.get(0);
            }

        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the User from DB ", ex);
        }
        logger.debug("<< Exiting getUserVehicleByVIN() with Result = {}", extract(listOfUserVehicles, on(UserVehicleVO.class).getVehicleVIN()));
        return userVehicleVO;
    }

    /**
     * Function to return all the User Vehicles by CustomerID
     * @param customerId
     * @return
     */
    public List<UserVehicleVO> getUserVehiclesByCustomerId(String customerId) {
        logger.debug(">> Entering {} getUserVehiclesByCustomerId() by CustomerID={}", this.getClass(), customerId);
        List<UserVehicleVO> listOfUsersVehicles = null;
        try {
            listOfUsersVehicles = userVehicleDao.getUserVehiclesByCustomerId(customerId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the List of ALL USER Vehicles from database ", ex);
        }
        logger.debug("<< Exiting getUserVehiclesByCustomerId() with Result = {}", extract(listOfUsersVehicles, on(UserVehicleVO.class).getVehicleVIN()));
        return listOfUsersVehicles;
    }

    /**
     * Function to return the list of User vehicles by MailID
     * @param userMailId
     * @return
     */
    public List<UserVehicleVO> getUserVehiclesByUserMailId(String userMailId) {
        logger.debug(">> Entering {} getUserVehiclesByUserMailId() by UserMailID={}", this.getClass(), userMailId);
        List<UserVehicleVO> listOfUsersVehicles = null;
        try {
            listOfUsersVehicles = userVehicleDao.getUserVehiclesByUserMailId(userMailId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the List of ALL USER Vehicles from database ", ex);
        }
        logger.debug("<< Exiting getUserVehiclesByUserMailId() with Result = {}", extract(listOfUsersVehicles, on(UserVehicleVO.class).getVehicleVIN()));
        return listOfUsersVehicles;
    }

    /**
     * Add a User Vehicle in the database.
     * @param userVehicleVO
     * @return
     */
    public boolean addUserVehicle(UserVehicleVO userVehicleVO) {
        logger.debug(">> Entering {} addUserVehicle() with userVehicle={}", this.getClass(), userVehicleVO);
        boolean result = false;
        try {
            userVehicleDao.addUserVehicle(userVehicleVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Adding a User Vehicle in the database ", ex);
        }
        logger.debug("<< Exiting addUserVehicle() with Result = {}", result);
        return result;
    }

    /**
     * This Function will update the details of the user vehicle from the database.
     * @param userVehicleVO
     * @return
     */
    public boolean updateUserVehicle(UserVehicleVO userVehicleVO) {
        logger.debug(">> Entering {} updateUserVehicle() with userVehicle={}", this.getClass(), userVehicleVO);
        boolean result = false;
        try {
            userVehicleDao.updateUserVehicle(userVehicleVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Updating a User Vehicle in the database ", ex);
        }
        logger.debug("<< Exiting updateUserVehicle() with Result = {}", result);
        return result;
    }

    /**
     * This Function will delete the User Vehicle Record from the database.
     * @param userVehicleVO
     * @return
     */
    public boolean deleteUserVehicle(UserVehicleVO userVehicleVO) {
        logger.debug(">> Entering {} deleteUserVehicle() with userVehicle={}", this.getClass(), userVehicleVO);
        boolean result = false;
        try {
            userVehicleDao.deleteUserVehicle(userVehicleVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Deleting a User Vehicle in the database ", ex);
        }
        logger.debug("<< Exiting deleteUserVehicle() with Result = {}", result);
        return result;
    }

    /**
     * This Function will return a list of all the User Vehicles maintained in the database.
     * @return
     */
    public List<UserVehicleVO> getAllUserVehicles() {
        logger.debug(">> Entering {} getAllUserVehicles() ", this.getClass());
        List<UserVehicleVO> listOfUsersVehicles = null;
        try {
            listOfUsersVehicles = userVehicleDao.getAllUserVehicles();
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the List of ALL USER Vehicles from database ", ex);
        }
        logger.debug("<< Exiting getAllUserVehicles() with Result = {}", extract(listOfUsersVehicles, on(UserVehicleVO.class).getVehicleVIN()));
        return listOfUsersVehicles;
    }

    /**
     * This is the function which will check whether the user exists in the database or not, if not it will simply insert the new user in the
     * database.
     * @param userVO
     * @return
     */
    @Async
    public boolean checkUserExistsOrInsert(UserVO userVO) {
        logger.debug(">> Entering {} checkUserExistsOrInsert() for UserId: ", this.getClass(), userVO.getMailId());
        boolean result = false;
        try {
            UserVO existingUserVO = appUserDao.getUserByMailId(userVO.getMailId());
            if (existingUserVO == null || existingUserVO.getCustomerId() == null) {
                // Here means we need to insert the customer id in the database.
                this.addUser(userVO);
            }
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Saving the user in the database ", ex);
        }
        logger.debug("<< Exiting checkUserExistsOrInsert() with Result = {} for User={}", result, userVO.getMailId());
        return result;
    }

    /**
     * This si the function, which will be responsible for managing the User Vehicles in the database.
     * @param customerId
     * @param userVehicles
     * @param delete If this flag is true, it means the vehicles that are being passed in the array needs to be deleted, otherwise all the vehicles
     * present in the array is checked, to see if any of the vehicles is not present then simply add it to the User Profile.
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    @Async
    public void manageUserVehicles(String customerId, UserVehicleVO[] userVehicles, boolean delete) {
        logger.debug(">> Entering manageUserVehicles() with  customerid={} and Flag ={} ", customerId, delete);
        UserVehicleVO userVehicleVO = null;
        UserVO userVO = appUserDao.getUserByCustomerId(customerId);
        Date currentDate = Calendar.getInstance().getTime();
        Set<String> newVehiclesSet = new HashSet<String>();
        UserVehicleVO newUserVehicleVO;

        try {
            if (delete == false) {
                for (UserVehicleVO lUserVehicleVO : userVehicles) {

                    if (lUserVehicleVO != null) {
                        logger.debug(" Starting checking for VIN = {} ", lUserVehicleVO.getVehicleVIN());
                        newVehiclesSet.add(lUserVehicleVO.getVehicleVIN());
                        userVehicleVO = userVehicleDao.getUserVehiclesByCustomerId(customerId, lUserVehicleVO.getVehicleVIN());
                        if (userVehicleVO != null && userVehicleVO.getVehicleVIN() != null) {
                            // It Means the VIN Already exists there, nothing needs to be done.
                        } else {

                            // Here means u need to map the VIN to the User.
                            newUserVehicleVO = new UserVehicleVO();
                            newUserVehicleVO.setAddedDate(currentDate);
                            newUserVehicleVO.setUserVO(userVO);
                            newUserVehicleVO.setVehicleVIN(lUserVehicleVO.getVehicleVIN());

                            userVehicleDao.addUserVehicle(newUserVehicleVO);
                            logger.debug("Following VIN ={} has been mapped to Customer={}", lUserVehicleVO.getVehicleVIN(), userVO.getMailId());
                        }
                    }


                }

                // Now you need to delete those VIN which exists earlier in the DB but does not apply now to the User Current Set of Vehicles.
                List<UserVehicleVO> listOfUserVehicles = userVehicleDao.getUserVehiclesByCustomerId(customerId);
                logger.debug("Number of Vehicles in the database = {}", listOfUserVehicles.size());
                Set<String> vehiclesSet = new HashSet(extract(listOfUserVehicles, on(UserVehicleVO.class).getVehicleVIN().trim()));
                Set<String> vehiclesToBeDeleted = Sets.difference(vehiclesSet, newVehiclesSet);
                String[] deleteVehicles = vehiclesToBeDeleted.toArray(new String[0]);
                logger.debug("Number of Vehicles to be deleted = {}", deleteVehicles.length, deleteVehicles);

                for (int i = 0; i < deleteVehicles.length; i++) {

                    userVehicleVO = userVehicleDao.getUserVehiclesByCustomerId(customerId, deleteVehicles[i]);
                    logger.debug("Deleting User Vehicle = {} ", deleteVehicles[i]);
                    userVehicleDao.deleteUserVehicle(userVehicleVO);

                }
                deleteVehicles = null;
                vehiclesToBeDeleted = null;
                vehiclesSet = null;
                listOfUserVehicles = null;

            } else {  // Here Means we need to delete the Vehicle as present in the array.

                for (UserVehicleVO lUserVehicleVO : userVehicles) {
                    userVehicleVO = userVehicleDao.getUserVehiclesByCustomerId(customerId, lUserVehicleVO.getVehicleVIN());
                    if (userVehicleVO != null || userVehicleVO.getVehicleVIN() != null) {
                        userVehicleDao.deleteUserVehicle(userVehicleVO);
                    }

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This is the function, which is responsible for adding a User Vehicle to an Customer
     * @param customerId
     * @param vin
     * @return
     */
    @Async
    public void updateOwnershipOfUserVehicle(String customerId, String vin) {
        logger.debug(">> Entering updateOwnershipOfUserVehicle() with customerId={} , VIN={}", customerId, vin);
        boolean result = false;
        UserVO userVO = this.getUserByCustomerId(customerId);

        UserVehicleVO userVehicleVO = this.getUserVehicleByVIN(vin);

        if (userVehicleVO != null && userVehicleVO.getVehicleVIN() != null && userVehicleVO.getVehicleVIN().trim().length() != 0) {
            if (userVehicleVO.getUserVO().getCustomerId().equalsIgnoreCase(customerId.trim()) == false) {
                // If it is false, it means a new CUSTOMER
                userVehicleVO.setUserVO(userVO);  // Updating the Owner of the Vehicle.
                this.updateUserVehicle(userVehicleVO);
                logger.debug("Sucessfully updated the User Vehicle Details for VIN{} ", vin);
                result = true;
            }
        } else {

            // here it means, that the VIN does not exists in the DB, so we need to add a new Record in the database.
            logger.debug("Adding a new VIN ={} to the CustomerID={}", vin, customerId);
            userVehicleVO = new UserVehicleVO();
            userVehicleVO.setAddedDate(Calendar.getInstance().getTime());
            userVehicleVO.setUserVO(userVO);
            userVehicleVO.setVehicleVIN(vin);
            result = this.addUserVehicle(userVehicleVO);

        }
        logger.debug("<< Exiting updateOwnershipOfUserVehicle() with result ={}", result);

    }

    @Async
    public void updateOwnershipOfUserVehicle(String customerId, String vin, String vinVehicleName) {
        logger.debug(">> Entering updateOwnershipOfUserVehicle() with customerId={} , VIN - TITLE={}", customerId, vin+"-"+vinVehicleName);
        boolean result = false;
        UserVO userVO = this.getUserByCustomerId(customerId);

        UserVehicleVO userVehicleVO = this.getUserVehicleByVIN(vin);

        if (userVehicleVO != null && userVehicleVO.getVehicleVIN() != null && userVehicleVO.getVehicleVIN().trim().length() != 0) {
            if (userVehicleVO.getUserVO().getCustomerId().equalsIgnoreCase(customerId.trim()) == false) {
                // If it is false, it means a new CUSTOMER
                userVehicleVO.setUserVO(userVO);  // Updating the Owner of the Vehicle.
                userVehicleVO.setVehicleTitle(vinVehicleName); //Added @ 01-01-2015 for updating vin vehicle name
                this.updateUserVehicle(userVehicleVO);
                logger.debug("Sucessfully updated the User Vehicle Details for VIN{} ", vin);
                result = true;
            }
        } else {

            // here it means, that the VIN does not exists in the DB, so we need to add a new Record in the database.
            logger.debug("Adding a new VIN ={} to the CustomerID={}", vin, customerId);
            userVehicleVO = new UserVehicleVO();
            userVehicleVO.setAddedDate(Calendar.getInstance().getTime());
            userVehicleVO.setUserVO(userVO);
            userVehicleVO.setVehicleVIN(vin);
            userVehicleVO.setVehicleTitle(vinVehicleName); //Added @ 01-01-2015 for updating vin vehicle name
            result = this.addUserVehicle(userVehicleVO);

        }
        logger.debug("<< Exiting updateOwnershipOfUserVehicle() with result ={}", result);

    }
    
    public void updateOwnershipOfUserVehicleCarline(String customerId, String vin, String vinVehicleName) {
        logger.debug(">> Entering updateOwnershipOfUserVehicle() with customerId={} , VIN - TITLE={}", customerId, vin+"-"+vinVehicleName);
        boolean result = false;
        UserVO userVO = this.getUserByCustomerId(customerId);

        UserVehicleVO userVehicleVO = this.getUserVehicleByVIN(vin);

        if (userVehicleVO != null && userVehicleVO.getVehicleVIN() != null && userVehicleVO.getVehicleVIN().trim().length() != 0) {
            //if (userVehicleVO.getUserVO().getCustomerId().equalsIgnoreCase(customerId.trim()) == false) {
                // If it is false, it means a new CUSTOMER
                userVehicleVO.setUserVO(userVO);  // Updating the Owner of the Vehicle.
                userVehicleVO.setVehicleTitle(vinVehicleName); //Added @ 01-01-2015 for updating vin vehicle name
                this.updateUserVehicle(userVehicleVO);
                logger.debug("Sucessfully updated the User Vehicle Details for VIN{} ", vin);
                result = true;
            //}
        } else {

            // here it means, that the VIN does not exists in the DB, so we need to add a new Record in the database.
            logger.debug("Adding a new VIN ={} to the CustomerID={}", vin, customerId);
            userVehicleVO = new UserVehicleVO();
            userVehicleVO.setAddedDate(Calendar.getInstance().getTime());
            userVehicleVO.setUserVO(userVO);
            userVehicleVO.setVehicleVIN(vin);
            userVehicleVO.setVehicleTitle(vinVehicleName); //Added @ 01-01-2015 for updating vin vehicle name
            result = this.addUserVehicle(userVehicleVO);

        }
        logger.debug("<< Exiting updateOwnershipOfUserVehicle() with result ={}", result);

    }
    
    
    /**
     * This is the function, which will be responsible for returning a fragment of existing users.
     * @param noOfResultsRequired
     * @param startingRange
     * @return
     */
    public List<UserVO> getAllActiveUsers(int noOfResultsRequired, int startingRange) {
        logger.debug(">> Entering getAllActiveUsers() for INPUT: noOfresultRequired={} startingRange={}", noOfResultsRequired, startingRange);
        List<UserVO> listOfUsers = null;
        listOfUsers = appUserDao.getAllActiveUsers(noOfResultsRequired, startingRange);
        for(UserVO userVO: listOfUsers)
        {
            userVO.setVehicles(this.getUserVehiclesByCustomerId(userVO.getCustomerId()));
        }
        logger.debug("<< Exiting getAllActiveUsers() with list of users having size = {}", listOfUsers.size());
        return listOfUsers;
    }
}
