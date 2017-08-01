/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * This Value Object will contain the description of the Error that is being sent by the client.
 * @author PankajB
 * @version 1.0
 */
public class RestDealerCouponsErrorDescriptionVO {

    private String code;
    private String description;

    @XmlAttribute(name=ApplicationConstants.XML_DEALER_COUPONS_RESULT_ERRORS_ERROR_ERRORS_ATTRIBUTE_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlValue
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
