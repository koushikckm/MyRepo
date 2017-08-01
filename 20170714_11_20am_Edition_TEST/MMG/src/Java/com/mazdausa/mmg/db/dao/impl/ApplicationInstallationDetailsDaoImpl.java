/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.impl;

import com.mazdausa.mmg.db.dao.iface.ApplicationInstallationDetailsDaoIFace;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import java.io.IOException;
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
 *
 * @author PankajB
 */
@Repository
public class ApplicationInstallationDetailsDaoImpl extends HibernateDaoSupport implements ApplicationInstallationDetailsDaoIFace {

    private static final String findApplicationInstallationByGUID = "com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO.findApplicationInstallationByGUID";
    /**
     * Declaring the Logger.
     */
    private static Logger logger = LoggerFactory.getLogger(ApplicationInstallationDetailsDaoImpl.class.getCanonicalName());

    /**
     * Auto wiring the Session Factory for this.
     */
    @Autowired
    public void setMySessionFactory(@Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * This Function will return the Application Installation Details given a particular AppInstallationDetails Id.
     * @param appInstallationDetailsId Application Installation Details Id
     * @return
     */
    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(int appInstallationDetailsId) {
        ApplicationInstallationDetailsVO applicationInstallationDetailsVO = null;
        logger.debug(" >> Entering {} getApplicationInstallationDetails() with appInstallationDetailsId= {}", this.getClass(), appInstallationDetailsId);
        applicationInstallationDetailsVO = (ApplicationInstallationDetailsVO) this.getHibernateTemplate().get(ApplicationInstallationDetailsVO.class, appInstallationDetailsId);
        if (applicationInstallationDetailsVO != null && applicationInstallationDetailsVO.getAppInstallationGuid() == null) {
            applicationInstallationDetailsVO = null;
        }
        logger.debug(" << Exiting {} getApplicationInstallationDetails() with Result= {}", this.getClass(), applicationInstallationDetailsVO);
        return applicationInstallationDetailsVO;
    }

    /**
     * This Function will return the Application Installation Details by GUID
     * @param appInstallationGuid GUID of the Installation.
     * @return
     */
    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appInstallationGuid) {
        ApplicationInstallationDetailsVO applicationInstallationDetailsVO = null;
        logger.debug(" >> Entering {} getApplicationInstallationDetails() with GUID= {}", this.getClass(), appInstallationGuid);
        List<ApplicationInstallationDetailsVO> listOfApplicationDetails = null;
        listOfApplicationDetails = (List<ApplicationInstallationDetailsVO>) this.getHibernateTemplate().findByNamedQuery(findApplicationInstallationByGUID, appInstallationGuid);
        if (listOfApplicationDetails != null && listOfApplicationDetails.size() == 1) {
            applicationInstallationDetailsVO = listOfApplicationDetails.get(0);
        }
        listOfApplicationDetails = null;
        logger.debug(" << Exiting {} getApplicationInstallationDetails() with Result= {}", this.getClass(), applicationInstallationDetailsVO);
        return applicationInstallationDetailsVO;
    }

    /**
     * This Function will return the Application Installation Details by Application Name, Application Version and OS TYPe
     * @param appName Application Name
     * @param appVersion Application Version
     * @param osType OS Type
     * @return
     */
    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appName, String appVersion, String osType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This Function will return all the Application Installation on the client device.
     * @return
     */
    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations() {
        logger.debug(" >> Entering {} getAllApplicationInstallations() with input", this.getClass());
        List<ApplicationInstallationDetailsVO> listOfApplicationsInstallations = (List<ApplicationInstallationDetailsVO>) this.getHibernateTemplate().loadAll(ApplicationInstallationDetailsVO.class);
        logger.debug(" << Exiting {} getAllApplicationInstallations() with Result Size= {}", this.getClass(), listOfApplicationsInstallations.size());
        return listOfApplicationsInstallations;
    }

    /**
     * This Function will return the list of all application installation by the Version Number.
     */
    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallationsByVersion(String version) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This Function will return the List of Application installation by App Name, Version, OS Type
     * @param appName Application Name
     * @param version Application Version
     * @param osType OS Type
     * @return
     */
    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations(String appName, String version, String osType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This Function will add a new Application Installation Record in the database.
     * @applicationInstallationDetailsVO Application Installation Details VO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void addApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO) {
        logger.debug(" >> Entering {} addApplicationInstallationDetails() with input=", this.getClass(), applicationInstallationDetailsVO);
        this.getHibernateTemplate().saveOrUpdate(applicationInstallationDetailsVO);
        logger.debug(" << Exiting {} addApplicationInstallationDetails() with Result TRUE", this.getClass());
    }

    /**
     *  This Function will delete the App Installation Details from the database.
     * @param applicationInstallationDetailsVO Application Installation Details VO
     * @return
     */
    public void deleteApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO) {
        logger.debug(" >> Entering {} deleteApplicationInstallationDetails() with input=", this.getClass(), applicationInstallationDetailsVO);
        this.getHibernateTemplate().delete(applicationInstallationDetailsVO);
        logger.debug(" << Exiting {} deleteApplicationInstallationDetails() with Result TRUE", this.getClass());
    }

    /**
     * This Function will update the Application Installation Details from the database.
     * @applicationInstallationDetailsVO Application Installation Details VO
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateApplicationInstallationDetails(ApplicationInstallationDetailsVO updateApplicationInstallationDetails) {
        logger.debug(" >> Entering {} updateApplicationInstallationDetails() with input={}", this.getClass(), updateApplicationInstallationDetails);
        try {
            this.getHibernateTemplate().update(updateApplicationInstallationDetails);
        } catch (Exception ioEx) {
            ioEx.printStackTrace();
        }
        logger.debug(" << Exiting {} updateApplicationInstallationDetails() with Result TRUE", this.getClass());

    }
}
