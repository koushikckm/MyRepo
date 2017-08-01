/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.iface;

import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserVehicleMileageRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserVehicleMileageUpdateRequestVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPVehicleCouponsRemindersRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserVehicleMileageResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserVehicleMileageUpdateResponseVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPVehicleCouponsRemindersResponseVO;

/**
 * This interface will contain the functions for each of the web services provided at Mazda.
 * @author PankajB
 * @version 1.0
 */
public interface VehicleWebServiceIface {

    /**
     * This Function will retrieve the Vehicle Details by providing the VIN
     * @param mileageRequestVO
     * @return
     */
    public SOAPUserVehicleMileageResponseVO getUserVehicleMileage(SOAPUserVehicleMileageRequestVO mileageRequestVO);


    /**
     * this Function will be responsible for updating the User Vehicle Mileage.
     * @param mileageUpdateRequestVO
     * @return
     */
    public SOAPUserVehicleMileageUpdateResponseVO updateUserVehicleMileage(SOAPUserVehicleMileageUpdateRequestVO mileageUpdateRequestVO);


    /**
     * This Function will be able to retrieve the details of a coupon or Service Reminder depending upon the CODE being passed.
     * @param vehicleCouponsRemindersRequestVO
     * @return
     */
    public SOAPVehicleCouponsRemindersResponseVO getCouponAndServiceRemindersData(SOAPVehicleCouponsRemindersRequestVO vehicleCouponsRemindersRequestVO);
}
