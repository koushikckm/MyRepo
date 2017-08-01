/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object will contain the details of the Error that is being sent by the Server because of invalid input.
 * @author PankajB
 * @version 1.0
 */
public class RestDealerCouponsErrorVO {

    private List<RestDealerCouponsErrorDescriptionVO> errors;

    @XmlElement(name=ApplicationConstants.XML_DEALER_COUPONS_RESULT_ERRORS_ERROR_ERRORS)
    public List<RestDealerCouponsErrorDescriptionVO> getErrors() {
        return errors;
    }

    public void setErrors(List<RestDealerCouponsErrorDescriptionVO> errors) {
        this.errors = errors;
    }

    
}
