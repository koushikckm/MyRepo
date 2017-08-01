/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.UserVehicleDaoIFace;
import com.mazdausa.mmg.db.vo.UserVehicleVO;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is an DAO Layer implementation of
 * @author PankajB
 */
@Repository
public class UserVehicleDaoImpl extends HibernateDaoSupport implements UserVehicleDaoIFace {

    public static final String findUserVehicleByVIN = "com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByVIN";
    public static final String findUserVehicleByCustomerId = "com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByCustomerId";
    public static final String findUserVehicleByMailId = "com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByMailId";
    public static final String findUserVehicleByCustomerIdAndVIN = "com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByCustomerIdAndVIN";
    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(UserVehicleDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * Return an User Vehicle
     * @param userVehicleId
     * @return
     */
    public UserVehicleVO getUserVehicle(int userVehicleId) {
        UserVehicleVO userVehicleVO = null;
        logger.debug(" >> Entering {} getUserVehicle() with appDetalsId= {}", this.getClass(), userVehicleId);
        userVehicleVO = (UserVehicleVO) this.getHibernateTemplate().get(UserVehicleVO.class, userVehicleId);
        if (userVehicleVO != null && userVehicleVO.getVehicleVIN() == null) {
            userVehicleVO = null;
        }
        logger.debug(" << Exiting {} getUserVehicle() with Result= {}", this.getClass(), userVehicleVO);
        return userVehicleVO;
    }

    /**
     * Return the list of User Vehicles by providing the Customer ID.
     * @param customerId
     * @return
     */
    public List<UserVehicleVO> getUserVehiclesByCustomerId(String customerId) {
        logger.debug(" >> Entering {} getUserVehiclesByCustomerId() with CustomerID={}", this.getClass(), customerId);
        List<UserVehicleVO> listOfUserVehicles = null;
        listOfUserVehicles = this.getHibernateTemplate().findByNamedQuery(findUserVehicleByCustomerId, customerId);
        logger.debug(" << Exiting {} getUserVehiclesByCustomerId() with Result={}", this.getClass(), listOfUserVehicles);
        return listOfUserVehicles;
    }

    /**
     * This Function will return the User Vehicle by providing the VIN
     * @param userVehicleVIN
     * @return
     */
    public List<UserVehicleVO> getUserVehicleByVIN(String userVehicleVIN) {
        logger.debug(" >> Entering {} getUserVehicleByVIN() with User VIN={}", this.getClass(), userVehicleVIN);
        List<UserVehicleVO> listOfUserVehicles = null;
        listOfUserVehicles = this.getHibernateTemplate().findByNamedQuery(findUserVehicleByVIN, userVehicleVIN);
        logger.debug(" << Exiting {} getUserVehicleByVIN() with Result={}", this.getClass(), listOfUserVehicles);
        return listOfUserVehicles;
    }

    /**
     * Return the list of User Vehicles by User Mail ID
     * @param userMailId
     * @return
     */
    public List<UserVehicleVO> getUserVehiclesByUserMailId(String userMailId) {
        logger.debug(" >> Entering {} getUserVehiclesByUserMailId() with CustomerID={}", this.getClass(), userMailId);
        List<UserVehicleVO> listOfUserVehicles = null;
        listOfUserVehicles = this.getHibernateTemplate().findByNamedQuery(findUserVehicleByMailId, userMailId);
        logger.debug(" << Exiting {} getUserVehiclesByUserMailId() with Result={}", this.getClass(), listOfUserVehicles);
        return listOfUserVehicles;
    }

    /**
     * Function to add an User Vehicle in the Database.
     * @param userVehicleVO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addUserVehicle(UserVehicleVO userVehicleVO) {
        logger.debug(" >> Entering {} addUserVehicle() with input={}", this.getClass(), userVehicleVO);
        this.getHibernateTemplate().persist(userVehicleVO);
        logger.debug(" << Exiting {} addUserVehicle() with True", this.getClass());
    }

    /**
     * Function to update the details of an User Vehicle
     * @param userVehicleVO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateUserVehicle(UserVehicleVO userVehicleVO) {
        logger.debug(" >> Entering {} updateUserVehicle() with input={}", this.getClass(), userVehicleVO);
        this.getHibernateTemplate().saveOrUpdate(userVehicleVO);
        logger.debug(" << Exiting {} updateUserVehicle() with True", this.getClass());
    }

    /**
     * Function to Delete an UserVehicleVO
     * @param userVehicleVO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteUserVehicle(UserVehicleVO userVehicleVO) {
        logger.debug(" >> Entering {} deleteUserVehicle() with input={}", this.getClass(), userVehicleVO);
        UserVehicleVO existingUserVehicleVO = this.getUserVehicle(userVehicleVO.getUserVehicleId());
        this.getHibernateTemplate().delete(existingUserVehicleVO);
        existingUserVehicleVO = null;
        logger.debug(" << Exiting {} deleteUserVehicle() with True", this.getClass());
    }

    /**
     * Function to return the list of all User Vehicles.
     * @return
     */
    public List<UserVehicleVO> getAllUserVehicles() {
        logger.debug(" >> Entering {} getAllUserVehicles() with input=", this.getClass());
        List<UserVehicleVO> listOfUserVehicles = (List<UserVehicleVO>) this.getHibernateTemplate().loadAll(UserVehicleVO.class);
        logger.debug(" << Exiting {} getAllUserVehicles() with Result Size= {}", this.getClass(), listOfUserVehicles.size());
        return listOfUserVehicles;
    }

    /**
     * This function, will return the user Vehicle given the CustomerDi and the User VIN
     * @param customerId
     * @param userVehiclevin
     * @return
     */
    public UserVehicleVO getUserVehiclesByCustomerId(String customerId, String userVehicleVin) {
        logger.debug(" >> Entering getUserVehiclesByCustomerId() with CustomerID={} and VIN={}", customerId, userVehicleVin);
        List<UserVehicleVO> listOfUserVehicles = null;
        UserVehicleVO userVehicleVO = null;
        listOfUserVehicles = this.getHibernateTemplate().findByNamedQuery(findUserVehicleByCustomerIdAndVIN, new Object[]{customerId, userVehicleVin});
        if (listOfUserVehicles != null && listOfUserVehicles.size() > 0) {
            userVehicleVO = listOfUserVehicles.get(0);
        }
        logger.debug(" << Exiting {} getUserVehiclesByCustomerId() with Result={}", this.getClass(), userVehicleVO);
        listOfUserVehicles = null;
        return userVehicleVO;
    }
}
