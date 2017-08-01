/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object will represent the Dealer Information Response as given by the SOAP Web Service.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_MAZDADEALERINFORESPONSE, namespace = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSEELEMENT_NAMESPACE)
public class SOAPDealerInfoResponseVO {

    
    private DealerDetailInfoResponse dealer;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_RESPONSE)
    public DealerDetailInfoResponse getDealer() {
        return dealer;
    }

    public void setDealer(DealerDetailInfoResponse dealer) {
        this.dealer = dealer;
    }



    
}
