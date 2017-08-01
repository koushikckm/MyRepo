/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

/**
 * This Value Object, will be responsible for representing the Value Objects,  that will be sent in response to scan time out details.
 * @author PankajKh
 */
public class VehicleScanTimeOutResponseVO {

    private String scantimeout, scanmessage;

    public String getScanmessage() {
        return scanmessage;
    }

    public void setScanmessage(String scanmessage) {
        this.scanmessage = scanmessage;
    }

    public String getScantimeout() {
        return scantimeout;
    }

    public void setScantimeout(String scantimeout) {
        this.scantimeout = scantimeout;
    }
    
}
