/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object represent the request which needs to be sent to the SOAP Web Service Request.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_REQUEST_MAZDADEALERINFO, namespace = ApplicationConstants.SERVICE_SOAP_DEALERINFO_REQUESTELEMENT_NAMESPACE)
public class SOAPDealerInfoRequestVO {

    private String dealerCode;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_DEALERINFO_REQUEST_MAZDADEALERINFO_DEALERCODE)
    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    
    
}
