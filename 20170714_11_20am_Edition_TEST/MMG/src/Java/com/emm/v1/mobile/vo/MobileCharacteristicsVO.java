/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.mobile.vo;

/**
 * This is the Data Transfer Object, that will represent the Mobile Phone Characteristics.
 * @author PankajB
 */
public class MobileCharacteristicsVO {

    private String phoneBrand,phoneType,osVersion,userAgent,osType,requestorIp;

    
    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestorIp() {
        return requestorIp;
    }

    public void setRequestorIp(String requestorIp) {
        this.requestorIp = requestorIp;
    }


}
