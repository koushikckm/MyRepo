/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object will contains the details of the Response that is being sent by the client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE, namespace = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSEELEMENT_NAMESPACE)
public class SOAPVehicleCouponsRemindersResponseVO {

    private SOAPVehicleCouponsRemindersResponseReturnVO responseData;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN)
    public SOAPVehicleCouponsRemindersResponseReturnVO getResponseData() {
        return responseData;
    }

    public void setResponseData(SOAPVehicleCouponsRemindersResponseReturnVO responseData) {
        this.responseData = responseData;
    }
}
