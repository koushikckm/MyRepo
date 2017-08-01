/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is an Value Object, that will hold the response of User related tasks Registration/Login/ForgotPassword at the Mazda Web Services.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.RESULT_DATA)
public class RestUserTaskResponseVO {

    private String status, value;
    private RestUserTaskSuccessResponseVO successResponse;

    @XmlAttribute(name = ApplicationConstants.RESULT_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = ApplicationConstants.XML_SUCCESS_CUSTOMERID)
    public RestUserTaskSuccessResponseVO getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(RestUserTaskSuccessResponseVO successResponse) {
        this.successResponse = successResponse;
    }

    @XmlTransient
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
