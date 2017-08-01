/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * This is an VO that will represent an Error in the User Task that is performed at Web Service
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.RESULT_DATA)
public class RestUserTaskErrorResponseVO {

    private String status, value;

    @XmlAttribute(name = ApplicationConstants.RESULT_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    

}
