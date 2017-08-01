package com.mazdausa.mmg.service.rest.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER, namespace = ApplicationConstants.SERVICE_REST_USERDETAILS_REQUESTELEMENT_NAMESPACE)
public class RestUserDetailRequestVO {
	
	private UserDetailRequestParametersVO userRequestDetailParameters;

    @XmlElement(name = ApplicationConstants.XML_REST_USERDETAILS_REQUEST_PARAMETER_GETCUSTOMER_CUSTOMER)
    public UserDetailRequestParametersVO getUserRequestDetailParameters() {
        return userRequestDetailParameters;
    }

    public void setUserRequestDetailParameters(UserDetailRequestParametersVO userRequestDetailParameters) {
        this.userRequestDetailParameters = userRequestDetailParameters;
    }

}
