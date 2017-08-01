/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This is the Detail line text, that will be used to stored the Coupons/Service Reminders Data.
 * @author PankajB
 * @version 1.0
 */
public class CouponRemindersDataDetailLineTextVO {

    private CouponRemindersDataDetailLineTextLayoutVO layout;

    private String text;


    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_TEXTLINE_LAYOUT)
    public CouponRemindersDataDetailLineTextLayoutVO getLayout() {
        return layout;
    }

    public void setLayout(CouponRemindersDataDetailLineTextLayoutVO layout) {
        this.layout = layout;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD_TEXTLINE_TEXT)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(text!=null && text.trim().length() !=0)
            this.text=text.trim();
        else
            this.text = text;
    }




}
