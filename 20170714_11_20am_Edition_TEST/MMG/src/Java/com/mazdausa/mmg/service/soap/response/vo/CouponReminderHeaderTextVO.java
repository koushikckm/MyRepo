/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;


/**
 * This is a CouponReminderHeaderTextVO, which is responsible for storing text details of headerText element of leftheader/rightheader.
 * @author pankajkh
 * @version 1.0
 */
public class CouponReminderHeaderTextVO {

    private List<CouponRemindersDataDetailLineTextVO> Text;
    
    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_HEADERTEXT_TEXT)
    public List<CouponRemindersDataDetailLineTextVO> getText() {
        return Text;
    }

    public void setText(List<CouponRemindersDataDetailLineTextVO> Text) {
        this.Text = Text;
    }
}
