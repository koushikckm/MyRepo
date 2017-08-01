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
 * This Class has been introduced on 10 August to incorport the changes related to Multiple SR & Coupons.
 * @author PankajB
 * @version 1.0
 */
public class ServiceOfferDataVO {

    private List<CouponRemindersDataDetailVO> listOfcouponDataDetailVO;

    @XmlElementWrapper(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA)
    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUPONDATA_CD)
    public List<CouponRemindersDataDetailVO> getListOfcouponDataDetailVO() {
        return listOfcouponDataDetailVO;
    }

    public void setListOfcouponDataDetailVO(List<CouponRemindersDataDetailVO> listOfcouponDataDetailVO) {
        this.listOfcouponDataDetailVO = listOfcouponDataDetailVO;
    }

    

}
