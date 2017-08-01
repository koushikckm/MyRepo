/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlValue;

/**
 * This Class determines the Result of Successful Value returned from the web service.
 * @author PankajB
 * @version 1.0
 */
public class RestUserTaskSuccessResponseVO {

    private String value;

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value.trim();
    }



}
