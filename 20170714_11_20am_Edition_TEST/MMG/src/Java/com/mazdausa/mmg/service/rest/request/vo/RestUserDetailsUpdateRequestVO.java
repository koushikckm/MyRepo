package com.mazdausa.mmg.service.rest.request.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER,namespace=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUESTELEMENT_NAMESPACE)
public class RestUserDetailsUpdateRequestVO {

	  private UserDetailsUpdateVO userDetailsUpdateVO;

	    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_UPDATE_DETAILS_REQUEST_PARAMETER_UPDATECUSTOMER_CUSTOMER)
	    public UserDetailsUpdateVO getUserDetailsUpdateVO() {
	        return userDetailsUpdateVO;
	    }

	    public void setUserDetailsUpdateVO(UserDetailsUpdateVO userDetailsUpdateVO) {
	        this.userDetailsUpdateVO = userDetailsUpdateVO;
	    }
}
