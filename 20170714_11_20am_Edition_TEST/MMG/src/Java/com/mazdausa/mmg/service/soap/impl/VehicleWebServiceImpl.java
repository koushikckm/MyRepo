/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.impl;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.emm.v1.constants.EMMConstants;
import com.googlecode.ehcache.annotations.Cacheable;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.service.soap.iface.VehicleWebServiceIface;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserVehicleMileageRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserVehicleMileageUpdateRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPVehicleCouponsRemindersRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponsRemindersDataVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserVehicleMileageResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserVehicleMileageUpdateResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPVehicleCouponsRemindersResponseReturnVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPVehicleCouponsRemindersResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;

/**
 * This Class is an SOAP Service Layer class and implements all the functions of Vehicle Web Service interface.
 * @author PankajB
 * @version 1.0
 */
@Component
public class VehicleWebServiceImpl implements VehicleWebServiceIface {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VehicleWebServiceImpl.class);
    @Autowired
    WebServiceTemplate webServiceTemplate;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    EMMConstants emmConstants;

    /**
     * This is an implementation of function to retrieve the User Vehicle Mileage.
     * @param mileageRequestVO
     * @return
     */
    public SOAPUserVehicleMileageResponseVO getUserVehicleMileage(SOAPUserVehicleMileageRequestVO mileageRequestVO) {
        logger.debug(">> Entering {} getUserVehicleMileage() with VIN = {}", this.getClass(), mileageRequestVO.getVin());
        SOAPUserVehicleMileageResponseVO userVehicleMileageResponseVO = null;

        Object object = mmgUtilities.getWebServiceHeaderElement(appConstants.SERVICE_SOAP_VEHICLE_MILEAGE_ACTION.trim(), appConstants);
        try
        {
            userVehicleMileageResponseVO = (SOAPUserVehicleMileageResponseVO) webServiceTemplate.marshalSendAndReceive(appConstants.SERVICE_SOAP_VEHICLE_MILEAGE.trim(), mileageRequestVO, (WebServiceMessageCallback) object);
        }
        catch(Exception ex)
        {
            logger.error("An Exception, has occured while invoking web service. ",ex);
        }
        logger.debug("<< Exiting getUserVehicleMileage() with Result = {} ", userVehicleMileageResponseVO);
        return userVehicleMileageResponseVO;
    }

    /**
     * This is the function for updating the User Vehicle Mileage given the required set of parameters.
     * @param mileageUpdateRequestVO
     * @return
     */
    public SOAPUserVehicleMileageUpdateResponseVO updateUserVehicleMileage(SOAPUserVehicleMileageUpdateRequestVO mileageUpdateRequestVO) {
        logger.debug(">> Entering {} getUserVehicleMileage() with VIN = {}", this.getClass(), mileageUpdateRequestVO.getVehicleMileageUpdate().getVin());
        SOAPUserVehicleMileageUpdateResponseVO userVehicleMileageUpdateResponseVO = null;
        try {
            Object object = mmgUtilities.getWebServiceHeaderElement(appConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_ACTION.trim(), appConstants);
            userVehicleMileageUpdateResponseVO = (SOAPUserVehicleMileageUpdateResponseVO) webServiceTemplate.marshalSendAndReceive(appConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE.trim(), mileageUpdateRequestVO, (WebServiceMessageCallback) object);

        } catch (SoapFaultClientException soEx) {
            logger.error("AN Web Service Invocation Exception has been thrown, while invoking the web service ", soEx);
            userVehicleMileageUpdateResponseVO=new SOAPUserVehicleMileageUpdateResponseVO();
            userVehicleMileageUpdateResponseVO.setResult(emmConstants.EMM_REQUEST_ERROR + " : " + soEx.getFaultCode() + " : " + soEx.getFaultStringOrReason());
        }
        catch(Exception ex)
        {
            logger.error("An Exception, has occured while updating UserVehicle Mileage",ex);
        }
        logger.debug("<< Exiting getUserVehicleMileage() with Result = {} ", userVehicleMileageUpdateResponseVO);
        return userVehicleMileageUpdateResponseVO;


    }

 

   /* *//**
     * This Function invoke the Update Vehicle Ownership Web Service and perform the function.
     * @param vehicleUpdateOwnershipRequestVO
     * @return
     *//*
    public RestUserVehicleUpdateOwnershipResponseVO updateVehicleOwnership(RestUserVehicleUpdateOwnershipRequestVO vehicleUpdateOwnershipRequestVO) {
        logger.debug(">> Entering {} updateVehicleOwnership() with VIN = {}", this.getClass(), vehicleUpdateOwnershipRequestVO.getUpdateVehicleOwnershipVO().getVin());
        RestUserVehicleUpdateOwnershipResponseVO userVehicleUpdateOwnershipResponseVO = null;
        try {
            Object object = mmgUtilities.getWebServiceHeaderElement(appConstants.SERVICE_SOAP_VEHICLE_UPDATE_OWNSERSHIP_ACTION.trim(), appConstants);
            userVehicleUpdateOwnershipResponseVO = (RestUserVehicleUpdateOwnershipResponseVO) webServiceTemplate.marshalSendAndReceive(appConstants.SERVICE_SOAP_VEHICLE_UPDATE_OWNSERSHIP.trim(), vehicleUpdateOwnershipRequestVO, (WebServiceMessageCallback) object);

        } catch (SoapFaultClientException soEx) {
            logger.error("AN Web Service Invocation Exception has been thrown, while invoking the web service ", soEx);
            userVehicleUpdateOwnershipResponseVO=new RestUserVehicleUpdateOwnershipResponseVO();
            userVehicleUpdateOwnershipResponseVO.setResultCode(emmConstants.EMM_REQUEST_ERROR + " : " + soEx.getFaultCode() + " : " + soEx.getFaultStringOrReason());
        }
         catch(Exception ex)
        {
            logger.error("An Exception, has occured while invoking Update Vehilce Ownership. ",ex);
        }
        logger.debug("<< Exiting updateVehicleOwnership() with Result = {} ", userVehicleUpdateOwnershipResponseVO);
        return userVehicleUpdateOwnershipResponseVO;
    }*/

    /**
     * This is the function, which will be responsible for fetching either the COUPONS or SERVICE REMINDER Details for a particular VIN
     * @param vehicleCouponsRemindersRequestVO
     * @return
     */
    //Cacheable property commented on 08/Aug/2011 because of deleting issue.
    //@Cacheable(cacheName = "Coupon_ServiceRemindersCache", keyGeneratorName="defaultCacheKeyGenerator")
    public SOAPVehicleCouponsRemindersResponseVO getCouponAndServiceRemindersData(SOAPVehicleCouponsRemindersRequestVO vehicleCouponsRemindersRequestVO) {
        logger.debug(">> Entering {} getCouponAndServiceRemindersData() with VIN = {}", this.getClass(), vehicleCouponsRemindersRequestVO.getVehicleCouponRemindersVO().getVin());
        SOAPVehicleCouponsRemindersResponseVO vehicleCouponsRemindersResponseVO = null;
        try {
            Object object = mmgUtilities.getWebServiceHeaderElement(appConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_ACTION.trim(), appConstants);
            vehicleCouponsRemindersResponseVO = (SOAPVehicleCouponsRemindersResponseVO) webServiceTemplate.marshalSendAndReceive(appConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS.trim(), vehicleCouponsRemindersRequestVO, (WebServiceMessageCallback) object);

        } catch (SoapFaultClientException soEx) {
            logger.error("AN Web Service Invocation Exception has been thrown, while invoking the web service ", soEx);
            vehicleCouponsRemindersResponseVO=new SOAPVehicleCouponsRemindersResponseVO();
            if (vehicleCouponsRemindersResponseVO.getResponseData() == null) {
                vehicleCouponsRemindersResponseVO.setResponseData(new SOAPVehicleCouponsRemindersResponseReturnVO());
            }
            vehicleCouponsRemindersResponseVO.getResponseData().setStatus(emmConstants.EMM_REQUEST_ERROR + " : " + soEx.getFaultCode() + " : " + soEx.getFaultStringOrReason());
        }
        catch(Exception ex)
        {
            logger.error("An Exception, has occured while invoking Ugetting Coupon and Service Reminders Data",ex);
        }
        logger.debug("<< Exiting getCouponAndServiceRemindersData() with Result = {} ", vehicleCouponsRemindersResponseVO);
        return vehicleCouponsRemindersResponseVO;
    }
}
