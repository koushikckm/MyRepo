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

/**
 * This VO contain all the details of an individual dealer coupon.
 * @author PankajB
 * @version 1.0
 */
public class DealerCouponVO {

    private String title, deal, id;
    private List<String> descriptions;
    private List<String> services;
    private String disclaimer, exception;
    

    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_DEAL)
    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_DESCRIPTIONS)
    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_DESCRIPTIONS_DESCRIPTION)
    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_DISCLAIMER)
    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_EXCEPTION)
    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_SERVICES)
    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_SERVICES_SERVICE)
    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_TITLE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute(name = ApplicationConstants.XML_DEALER_COUPONS_RESULT_COUPONS_COUPON_ATTRIBUTE_ID)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
