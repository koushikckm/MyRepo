/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.service.iface;

import java.io.InputStream;

import com.mazdausa.mmg.web.client.request.vo.ServiceUploadRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserConstantDetailsRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserUploadImageRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleCouponReminderRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleMileageUpdateRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserWindowStickerRequestVO;
import com.mazdausa.mmg.web.client.response.vo.ConstantDetailsResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserEgiftsDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.ServiceUploadResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserUploadImageResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleCouponResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleMileageUpdateResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleReminderResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeResponseOldVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehiclesDetailResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserWindowStickerResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehcileRecallAlertResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleExtendedInformationVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleGetMileageResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleInformationResponseVO;
import com.sun.jersey.core.header.FormDataContentDisposition;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * This Interface, will contain the service level interface for performing tasks related to User Vehicle
 * @author PankajB
 * @version 1.0
 */
public interface UserVehicleServiceIFace {

    /**
     * This Function is responsible for fetching the Vehicle Schedule.
     * @param vehicleNumber
     * @param drivingHabit
     * @return
     */
    public UserVehicleMaintenanceResponseVO getVehicleSchedule(String vehicleNumber,String drivingHabit);

    /**
     * This Function is responsible for fetching the Vehicle Maintenance schedule.
     * @param model
     * @param year
     * @param drivingHabit
     * @return
     */
    public UserVehicleMaintenanceResponseVO getVehicleSchedule(String model,String year,String drivingHabit);


      /**
     * This Function will return the Vehicle Information given the VIN
     * @param vin  Vehicle Identification Number.
     * @return
     */
    public VehicleInformationResponseVO getVehicleInformation(String vin);

    /**
     * This Function will return the Vehicle Detailed Information about the VIN
     * @param vin Vehicle Identification Number.
     * @return
     */
    public VehicleExtendedInformationVO getVehicleDetailInformation(String vin);

    /**
     * This Function will return the Description and the number of vehicles that the user belongs to
     * @param userId Represent the User ID.
     * @return
     */
    public UserVehiclesDetailResponseVO getUserVehicles(String userId, @Context HttpHeaders headers);


    /**
     * This Function will return the Vehicle Recall Alerts for the VIN passed.
     * @param vin
     * @return
     */
    public VehcileRecallAlertResponseVO getVehicleRecallAlert(String vin);

    /**
     * This Function will return the history details of the vehicle.
     * @param customerId Customer ID
     * @param vin Vehicle Identification Number
     * @return
     */
    public UserVehicleServiceHistoryResponseVO getVehicleServiceHistoryDetails(String customerId, String vin, @Context HttpHeaders headers);

    /**
     * This Function will return the Vehicle Mileage
     * @param vin VIN
     * @return
     */
    public VehicleGetMileageResponseVO getVehicleMileage(String vin);

    /**
     * This Function will be responsible for updating the Vehicle Mileage.
     * @param vin
     * @return
     */
    public UserVehicleMileageUpdateResponseVO updateVehicleMileage(UserVehicleMileageUpdateRequestVO vehicleMileageUpdateRequestVO);

    /**
     * This Function will set the Servicing Dealer for the Vehicle.
     * @param serServicingDealerRequestVO
     * @return
     */
    public UserVehicleSetServicingDealerResponseVO setServicingDealerForVin(UserVehicleSetServicingDealerRequestVO serServicingDealerRequestVO, @Context HttpHeaders headers);


    /**
     * This Function will be responsible for updating the ownership of the Vehicle from one user to another.
     * @param vehicleUpdateOwnershipRequestVO
     * @return
     */
    public UserVehicleUpdateOwnershipResponseVO updateOwnershipForVehicle(UserVehicleUpdateOwnershipRequestVO vehicleUpdateOwnershipRequestVO, @Context HttpHeaders headers);

    /**
     * This is the function which will be responsible for fetching the Service Reminders for a vehicle.
     * @param vehicleReminderRequestVO
     * @return
     */
    public UserVehicleReminderResponseVO getVehicleServiceReminders(UserVehicleCouponReminderRequestVO vehicleReminderRequestVO, HttpServletRequest request);

    /**
     * This is the function, which will be responsible for returning the COUPUNS associated, with a  VEHICLE
     * @param vehicleCouponRequestVO
     * @return
     */
    public UserVehicleCouponResponseVO getVehicleCoupons(UserVehicleCouponReminderRequestVO vehicleCouponRequestVO, HttpServletRequest request);

    /**
     * This is the function which will be responsible for returning the Vehicle Codes that exists for an Year.
     * @param year
     * @return
     */
    public UserVehicleYearCodeResponseVO getVehicleCodes(String ... year);

    /**
     * This is the function which will be responsible for returning the Vehicle Codes that exists for an Year.
     * @param year
     * @return
     */

    public UserVehicleYearCodeResponseOldVO getVehicleCodesOld(String ... year);

    /**
     * This is the function which will be responsible for returning the Vehicle Codes that exists for an Year.
     * @param year
     * @return
     */
    
    public ConstantDetailsResponseVO getConstant(String ... name);

    /**
     * This is the function which will be responsible for returning constant details.
     * @param userConstantDetailsRequestVO
     * @return
     */
    public ConstantDetailsResponseVO getConstantAndDeviceDetails(UserConstantDetailsRequestVO userConstantDetailsRequestVO);

    /* New services added for MMG Q4 @29-01-2014*/
    
    /**
     * Get service details based on vin & custid.
     * @param vin
     * @param custid
     * @return
     */
    public ServiceUploadResponseVO getUploadServiceRecordDetails(String vin,String custid);
    
    /**
     * Upload new service record.
     * @param serviceRecordReq
     * @return
     */
    public ServiceUploadResponseVO uploadServiceRecordDetails(ServiceUploadRequestVO serviceRecordReq);
    
    /**
     * Delete a service record by ref id.
     * @param refId,vin
     * @return
     */
    public ServiceUploadResponseVO delUploadedServiceRecord(String refId, String vin);
    
    /**
     * Update a service record.
     * @param serviceRecordReq
     * @return
     */
    public ServiceUploadResponseVO updateServiceRecordDetails(ServiceUploadRequestVO serviceRecordReq);
    
    /**
     * This method returns vin vehicle title based on custid & vin.
     * @param custId
     * @param vin
     * @return title
     */
    public String getVehicleTitle(String custId,String vin);

    /**
     * This Function will return the Description and the number of vehicles that the user belongs to
     * @param userId Represent the User ID.
     * @return
     */
    public UserEgiftsDetailResponseVO getUserEgifts(String customerId,String vin);
    
    /**
     * This Function is responsible for fetching the window sticker details.
     * @param vin,dealerId
     * @return
     */
    //public UserWindowStickerResponseVO getVehicleWindowSticker(String vin,String dealerId);

    /**
     * This Function is responsible for fetching the window sticker details.
     * @param mdlYear,carlineCode,extColorCode,mdlCode,width
     * @return
     */
    public UserWindowStickerResponseVO getWindowStickerImage(UserWindowStickerRequestVO windowStickerRequestVO);
    
    /**
     * This function will perform uploading user  image.
     * @param userUploadImageRequestVO
     * @return
     */
    public UserUploadImageResponseVO uploadUserImageDetails(UserUploadImageRequestVO uploadImageRequestVO, InputStream uploadedInputStream, FormDataContentDisposition fileDetail);
    
    /**
     * This function will perform fetching user upload image paths.
     * @param 
     * @return
     */
    public UserUploadImageResponseVO fetchUserImageDetails(UserUploadImageRequestVO userUploadImageRequestVO);
    
    /**
     * This function will perform removing user upload image paths.
     * @param userUploadImageRequestVO
     * @return
     */
    public UserUploadImageResponseVO deleteUserImageDetails(UserUploadImageRequestVO userUploadImageRequestVO);

}
