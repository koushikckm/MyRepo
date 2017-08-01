/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.service.impl;

import com.emm.v1.mobile.vo.MobileCharacteristicsVO;
import com.mazdausa.mmg.business.iface.ApplicationBusIFace;
import com.emm.v1.request.vo.ClientGuidRequestVO;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import com.mazdausa.mmg.web.client.service.iface.ApplicationServiceIFace;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is the Implementation class of Service layer Interface ApplicationServiceIFace.
 * @author PankajB
 * @version 1.0
 */
@Service
public class ApplicationServiceImpl implements ApplicationServiceIFace {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);
    @Autowired
    ApplicationBusIFace applicationBL;

    //@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public String getGuid(ClientGuidRequestVO clientGuidRequestVO, MobileCharacteristicsVO mobileCharactersticsVO) {

        logger.debug(">> Entering {} getGuid() with Request Parameter= {}", this.getClass(), clientGuidRequestVO);
        String appInstallationGuid = "";
        Date currentDate = Calendar.getInstance().getTime();
        Boolean serviceResult = false;
        ApplicationInstallationDetailsVO existingApplicationInstallationDetailsVO = null;

        ApplicationDetailsVO applicationDetailsVO = applicationBL.getApplicationDetailsByVersion(clientGuidRequestVO.getVersion().trim());
        if (applicationDetailsVO != null && applicationDetailsVO.getAppName() != null) {
            ApplicationInstallationDetailsVO applicationInstallationDetailsVO = new ApplicationInstallationDetailsVO();

            applicationInstallationDetailsVO.setAddedDate(currentDate);
            applicationInstallationDetailsVO.setAppDetailsVO(applicationDetailsVO);
            applicationInstallationDetailsVO.setDeviceAPNSToken(clientGuidRequestVO.getApnstoken());
            applicationInstallationDetailsVO.setMobileImei(clientGuidRequestVO.getImei());
            applicationInstallationDetailsVO.setMobileImsi(clientGuidRequestVO.getImsi());
            applicationInstallationDetailsVO.setMobileNumber(clientGuidRequestVO.getMobileno());
            applicationInstallationDetailsVO.setMobileUserAgent(mobileCharactersticsVO.getUserAgent());
            applicationInstallationDetailsVO.setPhoneBrand(mobileCharactersticsVO.getPhoneBrand());
            applicationInstallationDetailsVO.setPhoneType(mobileCharactersticsVO.getPhoneType());

            appInstallationGuid = EmmUtilities.getGUID();
            while (true) {
                existingApplicationInstallationDetailsVO = applicationBL.getApplicationInstallationDetails(appInstallationGuid);
                if (existingApplicationInstallationDetailsVO != null && existingApplicationInstallationDetailsVO.getMobileUserAgent() != null) {
                    continue;
                } else {
                    break;
                }
            }

            // Coming Here we have the GUID which is a new one.

            applicationInstallationDetailsVO.setAppInstallationGuid(appInstallationGuid);
            // Now Insert the Detail in the Database.

            serviceResult = applicationBL.addApplicationInstallationDetails(applicationInstallationDetailsVO);
            if (serviceResult == false) {
                appInstallationGuid = "";
            }


        } else {
            logger.error(" Application of Version {} does not exist on the server", clientGuidRequestVO.getVersion());
        }


        logger.debug("<< Returing getGuid() with Result = " + appInstallationGuid);
        return appInstallationGuid;
    }

    /**
     * THis is the function, which will check for the authenticity of a particular GUID.
     * @param guid
     * @return
     */
    public boolean checkAuthentictyOfGUID(String appInstallationGUID) {

        logger.debug(">> Entering {} checkAuthentictyOfGUID() with Request Parameter= {}", this.getClass(), appInstallationGUID);
        boolean result = false;
        ApplicationInstallationDetailsVO applicationInstallationDetailsVO = applicationBL.getApplicationInstallationDetails(appInstallationGUID);
        if (applicationInstallationDetailsVO != null && applicationInstallationDetailsVO.getDeviceAPNSToken() != null) {
            result = true;
        }
        logger.debug("<< Exiting checkAuthentictyOfGUID() with Response= {}", result);
        return result;
    }
}
