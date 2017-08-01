/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object, will contain the Response of the Dealer Coupons for a specific dealer.
 * @author PankajB
 */
@XmlRootElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT)
public class RestDealerCouponsResponseVO {

    private String status;
    private List<DealerCouponVO> coupons;
    List<RestDealerCouponsErrorVO> errors;

    @XmlAttribute(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_ATTRIBUTE_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS)
    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON)
    public List<DealerCouponVO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<DealerCouponVO> coupons) {
        this.coupons = coupons;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_ERRORS)
    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_ERRORS_ERROR)
    public List<RestDealerCouponsErrorVO> getErrors() {
        return errors;
    }

    public void setErrors(List<RestDealerCouponsErrorVO> errors) {
        this.errors = errors;
    }
}
