/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import java.util.ArrayList;

import com.mazdausa.mmg.service.soap.response.vo.DealerDetailInfoResponse;

/**
 * This Web Service Value Object, will return from the service layer.
 * @author PankajB
 * @version 1.0
 */
public class DealerInfoResponseVO {

    private String status,description;
    private DealerDetailInfoResponse dealer;
    private ArrayList<KeyValuePairVO> alert;
    
    

    
    public ArrayList<KeyValuePairVO> getAlert() {
		return alert;
	}

	public void setAlert(ArrayList<KeyValuePairVO> alert) {
		this.alert = alert;
	}

	public DealerDetailInfoResponse getDealer() {
        return dealer;
    }

    public void setDealer(DealerDetailInfoResponse dealer) {
        this.dealer = dealer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
