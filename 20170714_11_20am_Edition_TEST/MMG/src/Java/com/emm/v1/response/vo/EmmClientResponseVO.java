/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.response.vo;

import com.emm.v1.mobile.vo.EmmSessionTokenVO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This contains an Wrapper Value Object, which will contain the response that is to be sent to the client.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class EmmClientResponseVO {

    private String status,description;
    private Object response;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name="response",type=EmmSessionTokenVO.class)
    public Object getResponse() {
        return response;
    }

    public void setResponse(EmmSessionTokenVO response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
