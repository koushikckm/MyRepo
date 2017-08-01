/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO represent the detail of an individual service details.
 * @author PankajB
 * @version 1.0
 */
public class VehicleServiceVO {

    private String batteryInspectionFlag,brakesInspectionFlag,tiresInspectionFlag,mileage,serviceDate,serviceDealerId,serviceTypeCode;
    private String serviceTypeDesc;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_BATINSPFLAG)
    public String getBatteryInspectionFlag() {
        return batteryInspectionFlag;
    }

    public void setBatteryInspectionFlag(String batteryInspectionFlag) {
        this.batteryInspectionFlag = batteryInspectionFlag;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_BRAKENSPFLAG)
    public String getBrakesInspectionFlag() {
        return brakesInspectionFlag;
    }

    public void setBrakesInspectionFlag(String brakesInspectionFlag) {
        this.brakesInspectionFlag = brakesInspectionFlag;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_MILEAGE)
    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_SERVICEDATE)
    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_SERVICEDEALERID)
    public String getServiceDealerId() {
        return serviceDealerId;
    }

    public void setServiceDealerId(String serviceDealerId) {
        this.serviceDealerId = serviceDealerId;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_SERVICETYPECODE)
    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_SERVICETYPEDESC)
    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        this.serviceTypeDesc = serviceTypeDesc.trim();
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE_TIRESINSPFLAG)
    public String getTiresInspectionFlag() {
        return tiresInspectionFlag;
    }

    public void setTiresInspectionFlag(String tiresInspectionFlag) {
        this.tiresInspectionFlag = tiresInspectionFlag;
    }

    

}
