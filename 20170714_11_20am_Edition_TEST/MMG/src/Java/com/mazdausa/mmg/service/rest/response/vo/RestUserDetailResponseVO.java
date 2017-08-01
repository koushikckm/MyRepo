package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE, namespace = ApplicationConstants.SERVICE_REST_USERDETAILS_RESPONSEELEMENT_NAMESPACE)
public class RestUserDetailResponseVO {

	private UserDetailResponseParameterVO userDetailResponseParameter;

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_RESPONSE_PARAMETER_CUSTOMERRESPONSE_CUSTOMER)
    public UserDetailResponseParameterVO getUserDetailResponseParameter() {
        return userDetailResponseParameter;
    }

    public void setUserDetailResponseParameter(UserDetailResponseParameterVO userDetailResponseParameter) {
        this.userDetailResponseParameter = userDetailResponseParameter;
    }
}
