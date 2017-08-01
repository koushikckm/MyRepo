/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.business.impl;

import com.mazdausa.mmg.business.iface.ApplicationBusIFace;
import com.mazdausa.mmg.db.dao.iface.AccessLogDaoIFace;
import com.mazdausa.mmg.db.dao.iface.ApplicationConstantDaoIFace;
import com.mazdausa.mmg.db.dao.iface.ApplicationDetailsDaoIFace;
import com.mazdausa.mmg.db.dao.iface.ApplicationInstallationDetailsDaoIFace;
import com.mazdausa.mmg.db.vo.AccessLogVO;
import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import com.mazdausa.mmg.db.vo.ConstantVO;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * This is the Implementation class of ApplicationBusIFace
 * @author PankajB
 * @version 1.0
 */
@Component
public class ApplicationBusImpl implements ApplicationBusIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ApplicationBusImpl.class);
    @Autowired
    ApplicationDetailsDaoIFace applicationDao;
    @Autowired
    ApplicationInstallationDetailsDaoIFace applicationInstallationDao;
    @Autowired
    AccessLogDaoIFace accessLogDao;
    @Autowired
    ApplicationConstantDaoIFace applicationConstantsDao;

    /**
     * This Function will return the Application Details given the
     * @param appDetailsId
     * @return
     */
    public ApplicationDetailsVO getApplicationDetails(int appDetailsId) {
        logger.debug(">> Entering {} getApplicationDetails() with appDetailsId= {}", this.getClass(), appDetailsId);
        ApplicationDetailsVO applicationDetailsVO = null;
        try {
            applicationDetailsVO = applicationDao.getApplicationDetails(appDetailsId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the Application Details ");
        }
        logger.debug("<< Exiting getApplicationDetails() with Result = {}", applicationDetailsVO);
        return applicationDetailsVO;
    }

    /**
     * This Function will return the Application Details by Application Version.
     * @param appVersion
     * @return
     */
    public ApplicationDetailsVO getApplicationDetailsByVersion(String appVersion) {
        logger.debug(">> Entering {} getApplicationDetailsByVersion() with appVersion= {}", this.getClass(), appVersion);
        ApplicationDetailsVO applicationDetailsVO = null;
        try {
            applicationDetailsVO = applicationDao.getApplicationDetailsByVersion(appVersion);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the Application Details by Verison ", ex);
        }
        logger.debug("<< Exiting getApplicationDetailsByVersion() with Result = {}", applicationDetailsVO);
        return applicationDetailsVO;
    }

    /**
     * This Function will return all the list of application maintained in the Database.
     * @return
     */
    public List<ApplicationDetailsVO> getAllApplications() {
        logger.debug(">> Entering {} getApplicationDetailsByVersion()", this.getClass());
        List<ApplicationDetailsVO> listOfApplicationDetails = null;
        try {
            listOfApplicationDetails = applicationDao.getAllApplications();
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the LIst of Application Details.");
        }
        logger.debug("<< Exiting getApplicationDetailsByVersion() with Result = {}", listOfApplicationDetails);
        return listOfApplicationDetails;
    }

    /**
     * This Function will return the list of all the Active Application present in the System.
     * @return
     */
    public List<ApplicationDetailsVO> getAllActiveApplications() {
        logger.debug(">> Entering {} getAllActiveApplications() ", this.getClass());
        List<ApplicationDetailsVO> listOfActiveApplicationDetails = null;
        try {
            listOfActiveApplicationDetails = applicationDao.getAllActiveApplications();
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the LIst of Application Details.");
        }
        logger.debug("<< Exiting getAllActiveApplications() with Result = {}", listOfActiveApplicationDetails);
        return listOfActiveApplicationDetails;
    }

    /**
     * This Function will be responsible for adding an Application Details to the Database.
     */
    public boolean addApplicationDetails(ApplicationDetailsVO applicationDetailsVO) {
        logger.debug(">> Entering {} addApplicationDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationDao.addApplicationDetails(applicationDetailsVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Adding Application Details in the database. ");
        }
        logger.debug("<< Exiting addApplicationDetails() with Result = {}", result);
        return result;
    }

    /**
     * This Function will delete the Application Details from the database.
     * @param applicationDetailsVO
     * @return
     */
    public boolean deleteApplicationDetails(ApplicationDetailsVO applicationDetailsVO) {
        logger.debug(">> Entering {} deleteApplicationDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationDao.deleteApplicationDetails(applicationDetailsVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while deleting the Application Details.");
        }
        logger.debug("<< Exiting deleteApplicationDetails() with Result = {}", result);
        return result;
    }

    /**
     * This Function will update the Application Details present in the database.
     * @param applicationDetailsVO
     * @return
     */
    public boolean updateApplicationDetails(ApplicationDetailsVO applicationDetailsVO) {
        logger.debug(">> Entering {} updateApplicationDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationDao.updateApplicationDetails(applicationDetailsVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while updating the Application Details.");
        }
        logger.debug("<< Exiting updateApplicationDetails() with Result = {}", result);
        return result;
    }

    /**
     * This Function will be responsible for Fetching the Application Details by AppName,Version and OS Type
     * @param appName
     * @param appVersion
     * @param osType
     * @return
     */
    public ApplicationDetailsVO getApplicationDetails(String appName, String appVersion, String osType) {
        logger.debug(">> Entering {} getApplicationDetails() ", this.getClass());
        ApplicationDetailsVO applicationDetailsVO = null;
        try {
            applicationDetailsVO = applicationDao.getApplicationDetails(appName, appVersion, osType);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Getting the Application Details.");
        }
        logger.debug("<< Exiting getApplicationDetails() with Result = {}", applicationDetailsVO);
        return applicationDetailsVO;
    }

    /**
     * This Function will return the Application Installation Details by Giving the App Installation Details ID.
     * @param appInstallationDetailsId
     * @return
     */
    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(int appInstallationDetailsId) {
        logger.debug(">> Entering {} getApplicationInstallationDetails() ", this.getClass());
        ApplicationInstallationDetailsVO applicationInstallationDetailsVO = null;
        try {
            applicationInstallationDetailsVO = applicationInstallationDao.getApplicationInstallationDetails(appInstallationDetailsId);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the Application Installation Details.");
        }
        logger.debug("<< Exiting getApplicationInstallationDetails() with Result = {}", applicationInstallationDetailsVO);
        return applicationInstallationDetailsVO;
    }

    /**
     * This Function will return the Application Installation Installation Details by GUID
     * @param appInstallationGuid
     * @return
     */
    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appInstallationGuid) {
        logger.debug(">> Entering {} getApplicationInstallationDetails() ", this.getClass());
        ApplicationInstallationDetailsVO applicationInstallationDetailsVO = null;
        try {

            applicationInstallationDetailsVO = applicationInstallationDao.getApplicationInstallationDetails(appInstallationGuid);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting the Application Installation Details.");
        }
        logger.debug("<< Exiting getApplicationInstallationDetails() with Result = {}", applicationInstallationDetailsVO);
        return applicationInstallationDetailsVO;
    }

    /**
     * This Function will return the Application Installation Details by Appname,Version and OS Type
     * @param appName
     * @param appVersion
     * @param osType
     * @return
     */
    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appName, String appVersion, String osType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * This Function will return the list of all the Application Installations.
     * @return
     */
    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations() {
        logger.debug(">> Entering {} getAllApplicationInstallations() ", this.getClass());
        List<ApplicationInstallationDetailsVO> listOfapplicationInstallationDetailsVO = null;
        try {
            listOfapplicationInstallationDetailsVO = applicationInstallationDao.getAllApplicationInstallations();
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting All the Application Installation Details.");
        }
        logger.debug("<< Exiting getAllApplicationInstallations() with Result = {}", listOfapplicationInstallationDetailsVO);
        return listOfapplicationInstallationDetailsVO;
    }

    /**
     * This is the Function which will return the Application Installations by the Version Number.
     * @param version
     * @return
     */
    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallationsByVersion(String version) {
        logger.debug(">> Entering {} getAllApplicationInstallationsByVersion() ", this.getClass());
        List<ApplicationInstallationDetailsVO> listOfapplicationInstallationDetailsVO = null;
        try {
            listOfapplicationInstallationDetailsVO = applicationInstallationDao.getAllApplicationInstallationsByVersion(version);
        } catch (Exception ex) {

            logger.error(" An Exception has occured, while getting All the Application Installation Details.", ex);
        }
        logger.debug("<< Exiting getAllApplicationInstallationsByVersion() with Result = {}", listOfapplicationInstallationDetailsVO);
        return listOfapplicationInstallationDetailsVO;
    }

    /**
     * This is the function, which will return the List of All Application Installation by AppName, Version and OS TYPE.
     * @param appName
     * @param version
     * @param osType
     * @return
     */
    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations(String appName, String version, String osType) {
        logger.debug(">> Entering {} getAllApplicationInstallationsByVersion() ", this.getClass());
        List<ApplicationInstallationDetailsVO> listOfapplicationInstallationDetailsVO = null;
        try {
            listOfapplicationInstallationDetailsVO = applicationInstallationDao.getAllApplicationInstallations(appName, version, osType);
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while getting All the Application Installation Details.",ex);
        }
        logger.debug("<< Exiting getAllApplicationInstallationsByVersion() with Result = {}", listOfapplicationInstallationDetailsVO);
        return listOfapplicationInstallationDetailsVO;
    }

    /**
     * This Function will add the Application Installation Details to the Database.
     * @param applicationInstallationDetailsVO
     * @return
     */
    public boolean addApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO) {
        logger.debug(">> Entering {} addApplicationInstallationDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationInstallationDao.addApplicationInstallationDetails(applicationInstallationDetailsVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Adding the  Application Installation Details.",ex);
        }
        logger.debug("<< Exiting addApplicationInstallationDetails() with Result = {}", result);
        return result;
    }

    /**
     * This is the function, which will responsible for Deletion of Application Installation Details from the Database.
     * @param applicationInstallationDetailsVO
     * @return
     */
    public boolean deleteApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO) {
        logger.debug(">> Entering {} deleteApplicationInstallationDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationInstallationDao.deleteApplicationInstallationDetails(applicationInstallationDetailsVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Deleting the  Application Installation Details.",ex);
        }
        logger.debug("<< Exiting deleteApplicationInstallationDetails() with Result = {}", result);
        return result;
    }

    /**
     * This Function, will be responsible for updating the Application Installation Details from the Database.
     * @param applicationInstallationDetailsVO
     * @return
     */
    public boolean updateApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO) {
        logger.debug(">> Entering {} updateApplicationInstallationDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationInstallationDao.updateApplicationInstallationDetails(applicationInstallationDetailsVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Updating the  Application Installation Details.",ex);
        }
        logger.debug("<< Exiting updateApplicationInstallationDetails() with Result = {}", result);
        return result;
    }

    /**
     * This function will be responsible for logging the audit details in the database, ASYNCHROUSLY
     * @param accessLogVO
     */
    @Async
    public void logAuditRecord(AccessLogVO accessLogVO) {
        try {
            Thread.currentThread().sleep(6000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        accessLogDao.log(accessLogVO);
    }

    /**
     * This is the function, which will return the Constant given the Constant Name.
     * @param constantName
     * @return constantVO
     */
    public ConstantVO getConstant(String constantName) {
        logger.debug(">> Entering {} getConstant() with constantName = {}", this.getClass(),constantName);
        ConstantVO constantVO=null;
        constantVO=applicationConstantsDao.getAppConstantDetails(constantName.trim().toUpperCase());
        logger.debug("<< Exiting getConstant() with Result = {}", constantVO);
        return constantVO;
    }

    /**
     * This is the function, which will return the Constant given the Constant Name.
     * @return constantVO
     **/
    public List<ConstantVO> getAllConstantDetails() {
        logger.debug(">> Entering {} getAllConstantDetails() with = {}", this.getClass());
        List<ConstantVO> constantVO=null;
        constantVO=applicationConstantsDao.getAllConstantDetails();
        if(constantVO == null){
            constantVO = new ArrayList<ConstantVO>();
        }
        logger.debug("<< Exiting getAllConstantDetails() with Result = {}", constantVO);
        return constantVO;
    }

    /**
     * This Function will be responsible for adding an Application Details to the Database.
     * @param moduleName
     * @return result boolean
     **/
    public boolean getConstantByModuleName(String moduleName){
        logger.debug(">> Entering {} getConstant() with constantName = {}", this.getClass(),moduleName);
        ConstantVO constantVO = null;
        boolean serviceResult = false;
        serviceResult=applicationConstantsDao.getAppConstantDetailsByModule(moduleName.trim().toUpperCase());
        logger.debug("<< Exiting getConstant() with Result = {}", constantVO);
        return serviceResult;
    }
    /**
     * This Function will be responsible for adding an Application Details to the Database.
     * @param constantVO
     * @return result boolean
     **/
    public boolean addConstantDetails(ConstantVO constantVO) {
        logger.debug(">> Entering {} addConstantDetails() ", this.getClass());
        boolean result = false;
        try {
            applicationConstantsDao.addAppConstantDetails(constantVO);
            result = true;
        } catch (Exception ex) {
            logger.error(" An Exception has occured, while Adding Application Details in the database. ");
        }
        logger.debug("<< Exiting addConstantDetails() with Result = {}", result);
        return result;
    }

    /**
     * This Function will be responsible for adding an Application Details to the Database.
     * @param moduleName
     * @return constantVO
     **/
    public ConstantVO getConstantByModule(String moduleName){
        logger.debug(">> Entering {} getConstant() with constantName = {}", this.getClass(),moduleName);
        ConstantVO constantVO = null;
        
        constantVO=applicationConstantsDao.getConstantDetailsByModule(moduleName.trim().toUpperCase());
        logger.debug("<< Exiting getConstant() with Result = {}", constantVO);
        return constantVO;
    }
}
