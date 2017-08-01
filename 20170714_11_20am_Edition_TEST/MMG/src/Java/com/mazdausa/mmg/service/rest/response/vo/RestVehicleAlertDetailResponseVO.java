/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This will actual contain the details of the Vehicle Alert
 * @author PankajB
 * @version 1.0
 */
public class RestVehicleAlertDetailResponseVO {

    private String recallSSPNo, recallSSPDesc, startDate;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_RECALLORSSP_RECALLSSPDESC)
    public String getRecallSSPDesc() {
        return recallSSPDesc;
    }

    public void setRecallSSPDesc(String recallSSPDesc) {
        this.recallSSPDesc = recallSSPDesc;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_RECALLORSSP_RECALLSSPNO)
    public String getRecallSSPNo() {
        return recallSSPNo;
    }

    public void setRecallSSPNo(String recallSSPNo) {
        this.recallSSPNo = recallSSPNo;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_RECALLALERT_RECALLINQUIRYRESULT_RECALLORSSP_STARTDATE)
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
