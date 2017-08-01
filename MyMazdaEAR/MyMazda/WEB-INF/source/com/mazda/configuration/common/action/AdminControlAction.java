package com.mazda.configuration.common.action;

import java.util.List;

import com.mazda.configuration.common.transferobject.RegistrationTO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminControlAction extends ActionSupport implements ModelDriven<RegistrationTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RegistrationTO searchRegistorTO;
	private List<RegistrationTO> userVehicalList;
	private List<RegistrationTO> userPersonalDetail;
	public RegistrationTO getModel() {
		 searchRegistorTO = new RegistrationTO();
		return searchRegistorTO;
	}
	
	
	public String adminSearchUser(RegistrationTO registrationTO) {
		return SUCCESS;
	}


	public RegistrationTO getSearchRegistorTO() {
		return searchRegistorTO;
	}


	public void setSearchRegistorTO(RegistrationTO searchRegistorTO) {
		this.searchRegistorTO = searchRegistorTO;
	}


	public List<RegistrationTO> getUserPersonalDetail() {
		return userPersonalDetail;
	}


	public void setUserPersonalDetail(List<RegistrationTO> userPersonalDetail) {
		this.userPersonalDetail = userPersonalDetail;
	}

}

