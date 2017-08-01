/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the Value Object, that will contain the Vehicle Alert details for the Recall Alert response.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT)
public class RestVehicleAlertResponseVO {

    private String vin, error;
    //private List<RestVehicleAlertDetailResponseVO> recallOrSSP;
    private List<RestVehicleAlertDetailResponseVO> recalls;

    @XmlAttribute(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_ATTRIBUTE_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

   // @XmlElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_RECALLORSSP,type=RestVehicleAlertDetailResponseVO.class)
    /*public List<RestVehicleAlertDetailResponseVO> getRecallOrSSP() {
        return recallOrSSP;
    }

    public void setRecallOrSSP(List<RestVehicleAlertDetailResponseVO> recallOrSSP) {
        this.recallOrSSP = recallOrSSP;
    }*/
    public void setRecalls(List<RestVehicleAlertDetailResponseVO> recalls) {
        this.recalls = recalls;
    }

    public void setError(String error) {
        this.error = error;
    }
    

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_ERROR)
    public String getError() {
        return error;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_RECALLORSSP,type=RestVehicleAlertDetailResponseVO.class)
    public List<RestVehicleAlertDetailResponseVO> getRecalls() {
        return recalls;
    }

  
}
