/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.batch.utility;

import com.emm.v1.constants.DeviceType;
import com.emm.v1.constants.EMMConstants;
import com.emm.v1.utilities.EmmUtilities;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.db.dao.iface.AppCustomerVinRecallDaoIFace;
import com.mazdausa.mmg.db.dao.iface.AppServiceReminderDaoIFace;
import com.mazdausa.mmg.db.vo.CustomerVINRecallVO;
import com.mazdausa.mmg.db.vo.ServiceReminderVO;
import com.mazdausa.mmg.service.rest.iface.RestVehicleInformationIFace;
import com.mazdausa.mmg.service.rest.response.vo.RestVehicleAlertDetailResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestVehicleAlertResponseVO;
import com.mazdausa.mmg.service.soap.iface.VehicleWebServiceIface;
import com.mazdausa.mmg.service.soap.request.vo.SOAPVehicleCouponsRemindersRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.VehicleCouponsRemindersVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPVehicleCouponsRemindersResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * This is the Utility class related to Batch Process and is being responsible for performing the task related to two batch processes.
 * 1. VIN Recall
 * 2. Service Reminders.
 * @author PankajB
 */
@Component
public class BatchProcessUtility {

    /**
     * Initialization of all the Local Variables to be used.
     */
    private static final Logger logger = LoggerFactory.getLogger(BatchProcessUtility.class);
    @Autowired
    RestVehicleInformationIFace vehicleRestInformationService;
    @Autowired
    VehicleWebServiceIface vehicleSoapInformationService;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    AppServiceReminderDaoIFace serviceReminderDao;
    @Autowired
    AppCustomerVinRecallDaoIFace vinRecallDao;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    EmmUtilities emmUtilities;

    /**
     * This is the function responsible for checking the Service Reminders for a particular Customer & VIN and will take the action, if there
     * is a service reminder pending for the pair.
     * @param customerId
     * @apnsToken this signifies the APNSToken for the CustomerID
     * @param vin
     */
    @Async
    public void processServiceReminders(String customerId, String vin, String apnsToken) {
        logger.info(">> Entering processServiceReminders() For customerid={},vin={}", customerId, vin);
        SOAPVehicleCouponsRemindersRequestVO serviceReminderRequestVO = new SOAPVehicleCouponsRemindersRequestVO();
        List<CouponRemindersDataDetailVO> listOfServiceReminders;
        VehicleCouponsRemindersVO serviceReminderVO = new VehicleCouponsRemindersVO();
        serviceReminderVO.setCustId(customerId);
        serviceReminderVO.setVin(vin);
        serviceReminderVO.setSource(ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA_SOURCE_SERVICEREMINDER);
        serviceReminderRequestVO.setVehicleCouponRemindersVO(serviceReminderVO);

        ServiceReminderVO dbServiceReminderVO = null;
        Date currentDate = Calendar.getInstance().getTime();

        // Send the Request and get the Response.
        SOAPVehicleCouponsRemindersResponseVO serviceReminderResponseVO = vehicleSoapInformationService.getCouponAndServiceRemindersData(serviceReminderRequestVO);

        // Now Verify whether the Response is Success or Failure.
        if (serviceReminderResponseVO != null && serviceReminderResponseVO.getResponseData() != null && (serviceReminderResponseVO.getResponseData().getStatus().trim().toLowerCase().equals(EmmConstants.EMM_REQUEST_SUCCESS.toLowerCase()))) {

            try {
                int countOfServiceReminders = Integer.parseInt(serviceReminderResponseVO.getResponseData().getCount());
                if (countOfServiceReminders > 0 && serviceReminderResponseVO.getResponseData().getServiceReminders().size() > 0) {
                    listOfServiceReminders = serviceReminderResponseVO.getResponseData().getServiceReminders().get(0).getCoupons();
                    //Navigate to the list of service reminders.
                    for (CouponRemindersDataDetailVO serviceReminderDetailVO : listOfServiceReminders) {

                        // Now Check whether this particular Service Reminder is there in the database or not.
                        dbServiceReminderVO = serviceReminderDao.getAppServiceReminders(vin, serviceReminderDetailVO.getDealerCode(), serviceReminderDetailVO.getCouponNumber());
                        if (dbServiceReminderVO == null || (dbServiceReminderVO != null && dbServiceReminderVO.getDealercode() == null)) {
                            // It means the service Reminder object does not exists in the database. so make an entry and sent the notification.
                            dbServiceReminderVO = new ServiceReminderVO();
                            dbServiceReminderVO.setCouponserviceremindernumber(serviceReminderDetailVO.getCouponNumber());
                            dbServiceReminderVO.setDealercode(serviceReminderDetailVO.getDealerCode());
                            dbServiceReminderVO.setIsalertActivated(Boolean.TRUE);
                            dbServiceReminderVO.setSenttimes(1);
                            dbServiceReminderVO.setVin(vin);
                            dbServiceReminderVO.setAddeddate(currentDate);

                            // sent the notification.
                            String data=EmmConstants.IPHONE_PUSH_APNS_MESSAGE_SERVICEREMINDER + " " + vin;
                            emmUtilities.sentNotification(data, apnsToken, DeviceType.IPHONE);
                            // Insert in the database.
                            serviceReminderDao.addAppSerivceReminderDetails(dbServiceReminderVO);
                        } else if (dbServiceReminderVO != null) {
                            // it Means the Service Reminder exists int he DB. just make sure that the no of times sent and then sent the notification.
                            if (dbServiceReminderVO.getSenttimes() < appConstants.IPHONE_PUSH_MAX_LIMIT) {
                                // Sent the Notification.
                                String data=EmmConstants.IPHONE_PUSH_APNS_MESSAGE_SERVICEREMINDER + " "+ vin;
                                emmUtilities.sentNotification(data, apnsToken, DeviceType.IPHONE);
                                // update the sent time values.
                                dbServiceReminderVO.setSenttimes(dbServiceReminderVO.getSenttimes() + 1);
                            } else {
                                // set teh Alert Activate to FALSE.
                                dbServiceReminderVO.setIsalertActivated(Boolean.FALSE);

                            }
                            serviceReminderDao.updateAppSerivceReminderDetails(dbServiceReminderVO);
                        }



                    }
                } else {
                    logger.debug("NO Service Reminder EXISTS for COMBINATION : CUSTOMERID = {} VIN={}", customerId, vin);
                }

            } catch (RuntimeException rEx) {
                logger.error("An Error has occured, while processing Service Reminder.", rEx);
            }
        }
        logger.info("<< Exiting processServiceReminders() For customerid={},vin={}", customerId, vin);
    }

    /**
     * This function will responsible for processing the VIN recall for a particular VIN and send the VIN Recall notification to the server.
     * @param vin
     * @param apnsToken
     */
    @Async
    public void processVinRecall(String vin, String apnsToken) {
        logger.info(">> Entering processVinRecall()  For vin={}", vin);

        RestVehicleAlertResponseVO restVehicleInformationVO = vehicleRestInformationService.getVehicleRecallAlertDetail(vin);
        Date currentDate = Calendar.getInstance().getTime();
        int recallCount = 0;
        CustomerVINRecallVO customerVinRecallVO = null;
        boolean notificationToBeSent = false;

        if (restVehicleInformationVO.getRecalls() != null) {
            recallCount = restVehicleInformationVO.getRecalls().size();
        }

        logger.debug("No Of service Alerts for VIN = {} is {} ", vin, recallCount);
        if (recallCount > 0) {
            try {
                List<RestVehicleAlertDetailResponseVO> listOfRecalls = restVehicleInformationVO.getRecalls();
                for (RestVehicleAlertDetailResponseVO recallAlertDetail : listOfRecalls) {
                    // Now check whether the combination fo VIN,Recall,STARTDATE exists in the table or not.
                    Date recallstartDate = MMGUtilities.getDateFromString(recallAlertDetail.getStartDate(), ApplicationConstants.DATE_FORMAT_MMDDYYYY);
                    customerVinRecallVO = vinRecallDao.getCustomerVinRecallDetails(vin, recallAlertDetail.getRecallSSPNo(), recallstartDate);
                    if (customerVinRecallVO == null || customerVinRecallVO.getVin() == null) {
                        // If it comes here, it means we need to sent the notification.
                        notificationToBeSent = true;
                        // Now Add this record in the database also.
                        customerVinRecallVO = new CustomerVINRecallVO();
                        customerVinRecallVO.setAddeddate(currentDate);
                        customerVinRecallVO.setIsalertactivated(Boolean.TRUE);
                        customerVinRecallVO.setSenttimes(1);
                        customerVinRecallVO.setVin(vin);
                        customerVinRecallVO.setVinrecallSSPNo(recallAlertDetail.getRecallSSPNo());
                        customerVinRecallVO.setVinrecallStartDate(recallstartDate);
                        customerVinRecallVO.setvInrecallSSPDesc(recallAlertDetail.getRecallSSPDesc());

                        vinRecallDao.addCustomerVinRecallDetails(customerVinRecallVO);
                    }
                }
            } catch (Exception ex) {
                logger.error("An Exception occured, while processing the VIN Recall Alerts for the VIN={} ", vin, ex);
            }

            // Now check if notification need to be sent.
            if (notificationToBeSent) {
                emmUtilities.sentNotification(EmmConstants.IPHONE_PUSH_APNS_MESSAGE_VINRECALL + " "+ vin, apnsToken, DeviceType.IPHONE);
            }

        } else {
            logger.debug("No VIN RECALL ALERT EXISTS FOR VIN={}", vin);
        }


        logger.info("<< Exiting processVinRecall() For  vin={}", vin);
    }
}
