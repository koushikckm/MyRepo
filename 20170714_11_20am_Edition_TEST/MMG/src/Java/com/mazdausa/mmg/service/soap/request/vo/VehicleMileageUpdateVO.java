/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This will contain the details of Mileage Request Parameters.
 * @author PankajB
 * @version 1.0
 */
public class VehicleMileageUpdateVO {

    private String error,drivingConditions,vin,calcDate,mileage,milesPerDay;

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS_CALCDATE)
    public String getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(String calcDate) {
        this.calcDate = calcDate;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS_DRIVINGCONDITIONS)
    public String getDrivingConditions() {
        return drivingConditions;
    }

    public void setDrivingConditions(String drivingConditions) {
        this.drivingConditions = drivingConditions;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS_MILEAGE)
    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS_MILESPERDAY)
    public String getMilesPerDay() {
        return milesPerDay;
    }

    public void setMilesPerDay(String milesPerDay) {
        this.milesPerDay = milesPerDay;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_SOAP_VEHICLE_MILEAGE_UPDATE_REQUEST_PARAMETER_SETMILEAGEDETAILS_SETMILEAGEDETAILS_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    

}
