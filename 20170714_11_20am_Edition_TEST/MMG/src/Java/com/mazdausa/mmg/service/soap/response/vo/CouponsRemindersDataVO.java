/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * This VO will contain Coupons/Reminders Data.
 * @author PankajB
 * @version 1.0
 */
public class CouponsRemindersDataVO {

    private List<CouponRemindersDataDetailVO> coupons;
    private String count, status, letterCode, srText, contactOfferId, serviceReminderDate;
    private CouponRemindersDataDetailFullCircleDataVO fullCircleData;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUNT)
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElementWrapper(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA)
    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD)
    public List<CouponRemindersDataDetailVO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponRemindersDataDetailVO> coupons) {
        this.coupons = coupons;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_FULLCIRCLEDATA)
    public CouponRemindersDataDetailFullCircleDataVO getFullCircleData() {
        return fullCircleData;
    }

    public void setFullCircleData(CouponRemindersDataDetailFullCircleDataVO fullCircleData) {
        this.fullCircleData = fullCircleData;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_LETTERCODE)
    public String getLetterCode() {
        return letterCode;
    }

    public void setLetterCode(String letterCode) {
        this.letterCode = letterCode;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_SERVICEREMTEXT)
    public String getSrText() {
        return srText;
    }

    public void setSrText(String srText) {
        this.srText = srText;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_CONTACTOFFERID)
    public String getContactOfferId() {
        return contactOfferId;
    }

    public void setContactOfferId(String contactOfferId) {
        this.contactOfferId = contactOfferId;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_SERVICEREMDATE)
    public String getServiceReminderDate() {
        return serviceReminderDate;
    }

    public void setServiceReminderDate(String serviceReminderDate) {
        this.serviceReminderDate = serviceReminderDate;
    }    

}
