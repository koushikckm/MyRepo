/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.ApplicationDetailsDaoIFace;
import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
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
 * This is an Implementation Class for Interface ApplicationDetailsDaoIFace
 * @author PankajB
 */
@Repository
public class ApplicationDetailsDaoImpl extends HibernateDaoSupport implements ApplicationDetailsDaoIFace {

    public static final String findApplicationByAppVersion="com.mazdausa.mmg.db.vo.ApplicationDetailsVO.findApplicationByAppVersion";
    /**
     * Declaring the Logger.
     */
     private static Logger logger = LoggerFactory.getLogger(ApplicationDetailsDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this
     */
     @Autowired
    public void setMySessionFactory(@Qualifier(value="sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * This Function will return the Application Details given the appDetailsID.
     * @param appDetailsId Application Details Id
     * @return
     */
    public ApplicationDetailsVO getApplicationDetails(int appDetailsId) {
        ApplicationDetailsVO applicationDetailsVO=null;
        logger.debug(" >> Entering {} getApplicationDetails() with appDetalsId= {}",this.getClass(),appDetailsId);
        applicationDetailsVO=(ApplicationDetailsVO)this.getHibernateTemplate().get(ApplicationDetailsVO.class, appDetailsId);
        if (applicationDetailsVO != null && applicationDetailsVO.getAppName() == null) {
            applicationDetailsVO = null;
        }
        logger.debug(" << Exiting {} getApplicationDetails() with Result= {}",this.getClass(),applicationDetailsVO);
        return applicationDetailsVO;
    }

    /**
     * This Function will return the list of Applications
     * @return
     */
    public List<ApplicationDetailsVO> getAllApplications() {

        logger.debug(" >> Entering {} getAllApplications() with input=",this.getClass());
        List<ApplicationDetailsVO> listOfApplications=(List<ApplicationDetailsVO>)this.getHibernateTemplate().loadAll(ApplicationDetailsVO.class);
        logger.debug(" << Exiting {} getAllApplications() with Result Size= {}",this.getClass(),listOfApplications.size());
        return listOfApplications;

    }

    public List<ApplicationDetailsVO> getAllActiveApplications() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    /**
     * This Function will be responsible for adding a New Application Details in the Database.
     * @param applicationDetailsVO Application Details  Value Object
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addApplicationDetails(ApplicationDetailsVO applicationDetailsVO) {

        logger.debug(" >> Entering {} addApplicationDetails() with input={}",this.getClass(),applicationDetailsVO);
        this.getHibernateTemplate().persist(applicationDetailsVO);
        logger.debug(" << Exiting {} addApplicationDetails() with True",this.getClass());
        
    }

    /**
     * This Function will delete an Application Details of an Application from the database.
     * @param applicationDetailsVO Application Details  Value Object
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteApplicationDetails(ApplicationDetailsVO applicationDetailsVO) {
        logger.debug(" >> Entering {} deleteApplicationDetails() with input={}",this.getClass(),applicationDetailsVO);
        this.getHibernateTemplate().delete(applicationDetailsVO);
        logger.debug(" << Exiting {} deleteApplicationDetails() with True",this.getClass());
    }

    /**
     * This Function will update the Application Details Record as present in the database.
     * @param applicationDetailsVO Application Details  Value Object
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateApplicationDetails(ApplicationDetailsVO applicationDetailsVO) {
        logger.debug(" >> Entering {} updateApplicationDetails() with input={}",this.getClass(),applicationDetailsVO);
        this.getHibernateTemplate().update(applicationDetailsVO);
        logger.debug(" << Exiting {} updateApplicationDetails() with True",this.getClass());
    }

    /**
     * This Function will return the Application Details given the Application Name, Version and its OS Type.
     * @param appName   Application Name
     * @param appVersion Application Version
     * @param osType OS Type
     * @return
     */
    public ApplicationDetailsVO getApplicationDetails(String appName, String appVersion, String osType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This Function will return the Application Details by giving the Application Version.
     * @param appVersion
     * @return
     */
    public ApplicationDetailsVO getApplicationDetailsByVersion(String appVersion) {
        logger.debug(" >> Entering {} getApplicationDetailsByVersion() with input={}",this.getClass(),appVersion);
        List<ApplicationDetailsVO> listOfApplicationDetails=null;
        ApplicationDetailsVO applicationDetailsVO=null;
        listOfApplicationDetails=this.getHibernateTemplate().findByNamedQuery(findApplicationByAppVersion,appVersion);
        if(listOfApplicationDetails !=null && listOfApplicationDetails.size() >= 0 )
            applicationDetailsVO=listOfApplicationDetails.get(0);
        logger.debug(" << Exiting {} getApplicationDetailsByVersion() with Result={}",this.getClass(),applicationDetailsVO);
        return applicationDetailsVO;
    }

}
