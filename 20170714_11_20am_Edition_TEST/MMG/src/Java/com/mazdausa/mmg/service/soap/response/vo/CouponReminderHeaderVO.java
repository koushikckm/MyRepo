/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This is a header text vo, which is responsible for storing headerText details of leftheader/rightheader(coupon service reminder)
 * @author pankajkh
 * @version 1.0
 */
public class CouponReminderHeaderVO {
    
    private CouponReminderHeaderTextVO headerText;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_HEADERTEXT)
    public CouponReminderHeaderTextVO getHeaderText() {
        return headerText;
    }

    public void setHeaderText(CouponReminderHeaderTextVO headerText) {
        this.headerText = headerText;
    }

    
}
