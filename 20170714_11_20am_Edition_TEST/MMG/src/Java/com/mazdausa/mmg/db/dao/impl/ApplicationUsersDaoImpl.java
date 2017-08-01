/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.ApplicationUsersDaoIFace;
import com.mazdausa.mmg.db.vo.UserVO;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the DAO Layer Implementation class containing implementation of functions present in ApplicationUsersDaoIFace
 * @author PankajB
 * @version 1.0
 */
@Repository
public class ApplicationUsersDaoImpl extends HibernateDaoSupport implements ApplicationUsersDaoIFace {

    // QUERY
    public static final String findUserByMailId = "com.mazdausa.mmg.db.vo.UserVO.findUserByMailId";
    public static final String findUserByCustomerId = "com.mazdausa.mmg.db.vo.UserVO.findUserByCustomerId";
    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ApplicationUsersDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this DAO.
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * Return
     * @param userId
     * @return
     */
    public UserVO getUser(int userId) {
        UserVO userVO = null;
        logger.debug(" >> Entering {} getUser() with UserID= {}", this.getClass(), userId);
        userVO = (UserVO) this.getHibernateTemplate().get(UserVO.class, userId);
        if (userVO != null && userVO.getMailId() == null) {
            userVO = null;
        }
        logger.debug(" << Exiting {} getUser() with Result= {}", this.getClass(), userVO);
        return userVO;
    }

    /**
     * Return the User by MailID
     * @param userMailId
     * @return
     */
    public UserVO getUserByMailId(String userMailId) {
        logger.debug(" >> Entering {} getUserByMailId() with MailId={}", this.getClass(), userMailId);
        List<UserVO> listOfUsers = null;
        UserVO userVO = null;
        listOfUsers = this.getHibernateTemplate().findByNamedQuery(findUserByMailId, userMailId);
        if (listOfUsers != null && listOfUsers.size() > 0) {
            userVO = listOfUsers.get(0);
        }
        logger.debug(" << Exiting {} getUserByMailId() with Result={}", this.getClass(), userVO);
        listOfUsers = null;
        return userVO;
    }

    /**
     * Returns the user by Mazda Customer ID
     * @param mazdaCustId
     * @return
     */
    public UserVO getUserByCustomerId(String mazdaCustId) {
        logger.debug(" >> Entering {} getUserByCustomerId() with MailId={}", this.getClass(), mazdaCustId);
        List<UserVO> listOfUsers = null;
        UserVO userVO = null;
        listOfUsers = this.getHibernateTemplate().findByNamedQuery(findUserByCustomerId, mazdaCustId);
        if (listOfUsers != null && listOfUsers.size() > 0) {
            userVO = listOfUsers.get(0);
        }
        logger.debug(" << Exiting {} getUserByCustomerId() with Result={}", this.getClass(), userVO);
        listOfUsers = null;
        return userVO;
    }

    /**
     * Add a User to the database.
     * @param userVO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addUser(UserVO userVO) {
        logger.debug(" >> Entering {} addUser() with input={}", this.getClass(), userVO);
        this.getHibernateTemplate().persist(userVO);
        logger.debug(" << Exiting {} addUser() with True", this.getClass());
    }

    /**
     * Update an User
     * @param userVO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateUser(UserVO userVO) {
        logger.debug(" >> Entering {} updateUser() with input={}", this.getClass(), userVO);
        this.getHibernateTemplate().update(userVO);
        logger.debug(" << Exiting {} updateUser() with True", this.getClass());
    }

    /**
     * Delete an User from the Database
     * @param userVO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteUser(UserVO userVO) {
        logger.debug(" >> Entering {} deleteUser() with input={}", this.getClass(), userVO);
        this.getHibernateTemplate().delete(userVO);
        logger.debug(" << Exiting {} deleteUser() with True", this.getClass());
    }

    /**
     * Return list of all the users.
     * @return
     */
    public List<UserVO> findAllUsers() {
        logger.debug(" >> Entering {} findAllUsers() with input=", this.getClass());
        List<UserVO> listOfUsers = (List<UserVO>) this.getHibernateTemplate().loadAll(UserVO.class);
        logger.debug(" << Exiting {} findAllUsers() with Result Size= {}", this.getClass(), listOfUsers.size());
        return listOfUsers;
    }

    /**
     * This is the function, which will be responsible for returning a fragment of users that exists in the database.
     * @param noOfUsersRequired
     * @param startingFrom
     * @return
     */
    public List<UserVO> getAllActiveUsers(int noOfUsersRequired, int startingFrom) {
        logger.debug(">> Entering getAllActiveUsers() with input parameters = NoOfUsersRequired={} and startingFrom={}", noOfUsersRequired, startingFrom);
        List<UserVO> listOfUsers = null;
        DetachedCriteria criteria = DetachedCriteria.forClass(UserVO.class);
        listOfUsers = this.getHibernateTemplate().findByCriteria(criteria, startingFrom, noOfUsersRequired);
        logger.debug("<< Exiting getAllActiveUsers() with users list having size={}", listOfUsers.size());
        return listOfUsers;


    }
}
