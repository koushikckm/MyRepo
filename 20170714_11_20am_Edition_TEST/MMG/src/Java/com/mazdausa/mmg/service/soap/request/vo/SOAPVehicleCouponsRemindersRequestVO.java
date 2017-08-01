/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the details of the
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA,namespace=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUESTELEMENT_NAMESPACE)
public class SOAPVehicleCouponsRemindersRequestVO {

    private VehicleCouponsRemindersVO vehicleCouponRemindersVO;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_REQUEST_PARAMETER_GETCOUPONDATA)
    public VehicleCouponsRemindersVO getVehicleCouponRemindersVO() {
        return vehicleCouponRemindersVO;
    }

    public void setVehicleCouponRemindersVO(VehicleCouponsRemindersVO vehicleCouponRemindersVO) {
        this.vehicleCouponRemindersVO = vehicleCouponRemindersVO;
    }


}
