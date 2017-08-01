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
 * This is the class, which will represent the Data Return of the incoming SOAP response message.
 * @author PankajB
 * @version 1.0
 * @since 10 Aug 2011
 */
public class SOAPVehicleCouponsRemindersResponseReturnVO {

    // This Object is going to contain all the Service Offers Data.
    private ServiceOfferDataVO serviceOffers;
    private String count, status;
    // This is going to contain all the Service Reminders Data. This is done like this, because it is creater earlier, so this is just to fix the older
    // data structure in the new data structure.
    private List<CouponsRemindersDataVO> serviceReminders;

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_COUNT)
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_SERVICEOFFERDATA)
    public ServiceOfferDataVO getServiceOffers() {
        return serviceOffers;
    }

    public void setServiceOffers(ServiceOfferDataVO serviceOffers) {
        this.serviceOffers = serviceOffers;
    }

    @XmlElementWrapper(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_SERVICEREMINDERDATA)
    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_VEHICLE_COUPONSREMINDERS_RESPONSE_PARAMETER_COUPONDATARESPONSE_COUPONDATARETURN_SERVICEREMINDERDATA_SERVICEREMINDER)
    public List<CouponsRemindersDataVO> getServiceReminders() {
        return serviceReminders;
    }

    public void setServiceReminders(List<CouponsRemindersDataVO> serviceReminders) {
        this.serviceReminders = serviceReminders;
    }
}
