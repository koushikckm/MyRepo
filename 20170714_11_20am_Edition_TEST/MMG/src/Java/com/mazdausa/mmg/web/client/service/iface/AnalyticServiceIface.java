package com.mazdausa.mmg.web.client.service.iface;


/**
 * This is an Service level Interface having all the functions related to mmg analytic feature.
 * @author anilk
 *
 */
public interface AnalyticServiceIface {
	
	/**
	 * This method is responsible for saving usage data of mmg client application usage.
	 * @param resourceCode
	 * @param deviceType
	 * @param userId
	 * @return success/failure
	 */
	public String saveAnalytic(String resourceCode,String deviceType,String userId);

	/**
	 * This method is used to track client app usage based on year & model.
	 * @param resourceCode
	 * @param deviceType
	 * @param userId
	 * @param year
	 * @param model
	 * @return success/failure
	 */
	//public String saveAnalytic(String resourceCode,String deviceType,String userId,String year,String model);
	
	/**
	 * This method is responsible for saving usage data of mmg contents by client application usage.
	 * @param resourcecode
	 * @param devicetype
	 * @param userid
	 * @param contenttype
	 * @param videotype
	 * @param categoryid
	 * @param year
	 * @param model
	 * @param title
	 * @return success/failure
	 */
	public String saveAnalytic(String resourcecode,String devicetype,String userid,String contenttype,String videotype,String categoryid,String year,String model,String title);

}
