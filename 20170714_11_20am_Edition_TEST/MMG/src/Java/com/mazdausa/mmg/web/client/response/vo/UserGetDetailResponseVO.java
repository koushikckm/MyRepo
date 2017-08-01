/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.service.rest.response.vo.UserDetailResponseParameterVO;

/**
 * This Value object will contain all the details that will be sent to client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserGetDetailResponseVO {

    private String error, status, description;
    private UserDetailResponseParameterVO userdetails;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDetailResponseParameterVO getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(UserDetailResponseParameterVO userdetails) {
        this.userdetails = userdetails;
    }


}
