/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.session.vo;

import java.io.Serializable;

/**
 * This is an Value Object, that will contain the session details of each client which is being online.
 *
 * @author PankajB
 */
public class ClientSessionVO implements Serializable{

    private String requestorsIp,token,guid,userId;

    public String getRequestorsIp() {
        return requestorsIp;
    }

    public void setRequestorsIp(String requestorsIp) {
        this.requestorsIp = requestorsIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    


}
