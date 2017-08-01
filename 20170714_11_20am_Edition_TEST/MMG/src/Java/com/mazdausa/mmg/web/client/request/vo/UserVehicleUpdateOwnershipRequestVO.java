/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO represent the values that are being sent by the client in the Update Ownership Request.
 * @author PankajB
 */
@XmlRootElement
public class UserVehicleUpdateOwnershipRequestVO {

    private String newCustomerId,disposalFlag,disposalReason,resaleDate,vin;
    private String customerId,source;
    
    //01-01-2015 : Add New parameter to hold vehicle title
    private String vtitle;

    
    public String getVtitle() {
		return vtitle;
	}

	public void setVtitle(String vtitle) {
		this.vtitle = vtitle;
	}

	public String getDisposalFlag() {
        return disposalFlag;
    }

    public void setDisposalFlag(String disposalFlag) {
        this.disposalFlag = disposalFlag;
    }

    public String getDisposalReason() {
        return disposalReason;
    }

    public void setDisposalReason(String disposalReason) {
        this.disposalReason = disposalReason;
    }

    public String getNewCustomerId() {
        return newCustomerId;
    }

    public void setNewCustomerId(String newCustomerId) {
        this.newCustomerId = newCustomerId;
    }

    public String getResaleDate() {
        return resaleDate;
    }

    public void setResaleDate(String resaleDate) {
        this.resaleDate = resaleDate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    
    

}
