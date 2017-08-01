/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This VO will contain the details of an single Vehicle in GetVehicles Request
 * @author PankajB
 * @version 1.0
 */
public class GetVehiclesDetailsVehicleVO {

    private String carlineCode, carlineDesc, currentownerflag, driveCode, vin, trim;
    private GetVehiclesDetailsVehicleEngineVO engine;
    private GetVehiclesDetailsVehicleTransmissionVO transmission;
    private String extColorCode, extColorDesc, intColorCode, intColorDesc, leaseMaturityDate, mdlCode, mdlYear, purchaseDate, seatingPassengers, seatingRows, serviceDealerID;

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_CARLINECODE)
    public String getCarlineCode() {
        return carlineCode;
    }

    public void setCarlineCode(String carlineCode) {
        this.carlineCode = carlineCode;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_CARLINEDESC)
    public String getCarlineDesc() {
        return carlineDesc;
    }

    public void setCarlineDesc(String carlineDesc) {
        this.carlineDesc = carlineDesc;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_CURRENTOWNERFLAG)
    public String getCurrentownerflag() {
        return currentownerflag;
    }

    public void setCurrentownerflag(String currentownerflag) {
        this.currentownerflag = currentownerflag;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_DRIVECODE)
    public String getDriveCode() {
        return driveCode;
    }

    public void setDriveCode(String driveCode) {
        this.driveCode = driveCode;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE)
    public GetVehiclesDetailsVehicleEngineVO getEngine() {
        return engine;
    }

    public void setEngine(GetVehiclesDetailsVehicleEngineVO engine) {
        this.engine = engine;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_EXTCOLORCODE)
    public String getExtColorCode() {
        return extColorCode;
    }

    public void setExtColorCode(String extColorCode) {
        this.extColorCode = extColorCode;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_EXTCOLORDESC)
    public String getExtColorDesc() {
        return extColorDesc;
    }

    public void setExtColorDesc(String extColorDesc) {
        this.extColorDesc = extColorDesc.trim();
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_INTCOLORCODE)
    public String getIntColorCode() {
        return intColorCode;
    }

    public void setIntColorCode(String intColorCode) {
        this.intColorCode = intColorCode;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_INTCOLORDESC)
    public String getIntColorDesc() {
        return intColorDesc;
    }

    public void setIntColorDesc(String intColorDesc) {
        this.intColorDesc = intColorDesc.trim();
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_LEASEMATURITYDATE)
    public String getLeaseMaturityDate() {
        return leaseMaturityDate;
    }

    public void setLeaseMaturityDate(String leaseMaturityDate) {
        this.leaseMaturityDate = leaseMaturityDate;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_MDLCODE)
    public String getMdlCode() {
        return mdlCode;
    }

    public void setMdlCode(String mdlCode) {
        this.mdlCode = mdlCode;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_MDLYEAR)
    public String getMdlYear() {
        return mdlYear;
    }

    public void setMdlYear(String mdlYear) {
        this.mdlYear = mdlYear;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_PURCHASEDATE)
    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_SEATINGPASSENGERS)
    public String getSeatingPassengers() {
        return seatingPassengers;
    }

    public void setSeatingPassengers(String seatingPassengers) {
        this.seatingPassengers = seatingPassengers;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_SEATINGROWS)
    public String getSeatingRows() {
        return seatingRows;
    }

    public void setSeatingRows(String seatingRows) {
        this.seatingRows = seatingRows;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_SERVICINGDEALERID)
    public String getServiceDealerID() {
        return serviceDealerID;
    }

    public void setServiceDealerID(String serviceDealerID) {
        this.serviceDealerID = serviceDealerID;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_TRANSMISSION)
    public GetVehiclesDetailsVehicleTransmissionVO getTransmission() {
        return transmission;
    }

    public void setTransmission(GetVehiclesDetailsVehicleTransmissionVO transmission) {
        this.transmission = transmission;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_TRIM)
    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        if (trim != null) {
            this.trim = trim.trim();
        } else {
            this.trim = trim;
        }
    }
}
