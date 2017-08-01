/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.AccessLogDaoIFace;
import com.mazdausa.mmg.db.vo.AccessLogVO;
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
 * This is the DAO Layer Implementation class containing implementation of functions present in AccessLogDaoIFace
 * @author pankajkh
 */
@Repository
public class AccessLogDaoImpl extends HibernateDaoSupport implements AccessLogDaoIFace {

    /**
     * Declaring the Logger.
     */
    
	
	private static Logger logger = LoggerFactory.getLogger(AccessLogDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "logSessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * This Function will return the Access Log Details given a particular AccessLog Id.
     * @param accessLogId Access Log Id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public AccessLogVO getAccessLogDetails(int accessLogId) {
        AccessLogVO accessLogVO = null;
        logger.debug(" >> Entering {} getAccessLogDetails() with accessLogId= {}", this.getClass(), accessLogId);

        accessLogVO = (AccessLogVO) this.getHibernateTemplate().get(AccessLogVO.class, accessLogId);

        if (accessLogVO != null && accessLogVO.getSessionId() == null) {
            accessLogVO = null;
        }
        logger.debug(" << Exiting {} getAccessLogDetails() with Result= {}", this.getClass(), accessLogVO);
        return accessLogVO;
    }

    /**
     * This Function will return the Access Log Details GUID.
     * @param installationGuid Application installation GUID
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public AccessLogVO getAccessLogDetails(String installationGuid) {

        AccessLogVO accessLogVO = null;
       

        return accessLogVO;
    }

    /**
     * This Function will add a new Access Log details to the database
     * @param installationGuid Application installation GUID
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addAccessLogDetails(AccessLogVO accessLogVO) {

        logger.debug(" >> Entering {} addAccessLogDetails() with input=", this.getClass(), accessLogVO);
        this.getHibernateTemplate().persist(accessLogVO);
        
        logger.debug(" << Exiting {} addAccessLogDetails() with Result TRUE", this.getClass());
    }

    /**
     * This Function will update the record from the Database.
     * @param accessLogVO Access Log VO
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateAccessLogDetails(AccessLogVO accessLogVO) {

        logger.debug(" >> Entering {} updateAccessLogDetails() with input=", this.getClass(), accessLogVO);
        
        this.getHibernateTemplate().update(accessLogVO);
        logger.debug(" << Exiting {} updateAccessLogDetails() with Result TRUE", this.getClass());
    }

    /**
     * This Function will delete the record from the Database.
     * @param accessLogVO Access Log VO
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteAccessLogDetails(AccessLogVO accessLogVO) {

        logger.debug(" >> Entering {} deleteAccessLogDetails() with input=", this.getClass(), accessLogVO);
       
        this.getHibernateTemplate().delete(accessLogVO);
        logger.debug(" << Exiting {} deleteAccessLogDetails() with Result TRUE", this.getClass());
    }

    /**
     * This is the implementation function for Adding a Logging Record in the database.
     * @param accessLogVO
     */
    public void log(AccessLogVO accessLogVO) {
        try {
            this.getHibernateTemplate().save(accessLogVO);
           

        } catch (Exception ex) {
            
            logger.error("An Exception has occured, while adding a logging record in the database.", ex);
        }
    }

    /**
     * This is the function, which is responsible for retrieving all the Access Log Details records present in the database.
     * @return
     */
    public List<AccessLogVO> getAllAccessLogDetails() {
        logger.debug(" >> Entering {} getAllAccessLogDetails() ");
       
        List<AccessLogVO> list = (List<AccessLogVO>) this.getHibernateTemplate().loadAll(AccessLogVO.class);
        logger.debug(" << Exiting {} getAllAccessLogDetails() ");
        return list;
    }
}
