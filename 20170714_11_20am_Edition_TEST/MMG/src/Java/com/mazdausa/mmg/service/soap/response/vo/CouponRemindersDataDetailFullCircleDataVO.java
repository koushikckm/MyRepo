/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object, will represent the Date,Tire & Brakes flag in the Coupon/Service Reminders web service
 * @author PankajB
 * @version 1.0
 */
public class CouponRemindersDataDetailFullCircleDataVO {

    private String brake, tire, serviceDate;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_FULLCIRCLEDATA_BRAKE)
    public String getBrake() {
        return brake;
    }

    public void setBrake(String brake) {
        if (brake != null && brake.trim().length() != 0) {
            this.brake = brake;
        } else {
            this.brake = "WNot Inspected";
            //  this.brake = "GGood Condition  50% or more remaining";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_FULLCIRCLEDATA_SERVICEDATE)
    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        if (serviceDate != null && serviceDate.trim().length() != 0) {
            this.serviceDate = serviceDate;
        } else {
            this.serviceDate = "";
        }
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_FULLCIRCLEDATA_TIRE)
    public String getTire() {
        return tire;
    }

    public void setTire(String tire) {
        if (tire != null && tire.trim().length() != 0) {
            this.tire = tire;
        } else {
            this.tire = "WNot Inspected";


        }
    }
}
