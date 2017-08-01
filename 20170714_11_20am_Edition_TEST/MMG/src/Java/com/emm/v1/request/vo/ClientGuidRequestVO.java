/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is an value Object that will represent the GUID Request.
 * @author PankajB
 */
@XmlRootElement
public class ClientGuidRequestVO {

    private String appname,version,imei,imsi,apnstoken,mobileno;

    
    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApnstoken() {
        return apnstoken;
    }

    public void setApnstoken(String apnstoken) {
        this.apnstoken = apnstoken;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }


}
