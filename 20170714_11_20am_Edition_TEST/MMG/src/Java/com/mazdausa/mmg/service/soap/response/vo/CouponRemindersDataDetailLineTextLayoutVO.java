/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object, will contains he Line Text Layout for each of the line Text, that is being present for Service Offers/Service Reminders.
 * @author PankajB
 * @version 1.0
 */
public class CouponRemindersDataDetailLineTextLayoutVO {

    private String disclaimer,formatCode,typeCode;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_TEXTLINE_LAYOUT_DISCLIAMER)
    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_TEXTLINE_LAYOUT_LINEFORMATCODE)
    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_TEXTLINE_LAYOUT_LINETYPECODE)
    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    

}
