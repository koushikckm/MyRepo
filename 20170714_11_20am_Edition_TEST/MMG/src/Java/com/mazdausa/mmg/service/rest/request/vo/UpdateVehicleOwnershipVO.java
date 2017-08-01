/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.request.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO Contains the list of parameters that needs to be sent to the Update Ownership Web Service.
 * @author PankajB
 * @version 1.0
 */
public class UpdateVehicleOwnershipVO {

    private String customerId,disposalFlag,disposalReason,newCustomerId,resaleDate,source,vin;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_CUSTOMERID)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_DISPOSALFLAG)
    public String getDisposalFlag() {
        return disposalFlag;
    }

    public void setDisposalFlag(String disposalFlag) {
        this.disposalFlag = disposalFlag;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_NEWCUSTOMERID)
    public String getNewCustomerId() {
        return newCustomerId;
    }

    public void setNewCustomerId(String newCustomerId) {
        this.newCustomerId = newCustomerId;
    }

      @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_RESALEDATE)
    public String getResaleDate() {
        return resaleDate;
    }

    public void setResaleDate(String resaleDate) {
        this.resaleDate = resaleDate;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_SOURCE)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_DISPOSALREASON)
    public String getDisposalReason() {
        return disposalReason;
    }

    public void setDisposalReason(String disposalReason) {
        this.disposalReason = disposalReason;
    }

   
    @XmlElement(name=ApplicationConstants.SERVICE_REST_VEHICLE_UPDATE_OWNERSHIP_REQUEST_PARAMETER_UPDATEOWNERSHIP_OWNER_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    
}
