package com.mazdausa.mmg.web.client.service.iface;

import java.util.ArrayList;

import com.mazdausa.mmg.web.client.request.vo.CustomerSupportRequestVO;
import com.mazdausa.mmg.web.client.response.vo.CustomerSupportResponseVO;

/**
 * This is an Service level Interface and being responsible for all Service Layer Functions.
 * @author AnilK
 *
 */
public interface CustomerSupportServiceIface {

	
	/**
	 * This function is responsible for fetching Customer JCI link url.
	 * @return
	 */
	public CustomerSupportResponseVO getJciLink();
	
	
	/**
	 * Returns Mazda customer care info like customer care email & phone etc.
	 * @return
	 */
	public CustomerSupportResponseVO getAppVersion(String agentType);
	
	/**
	 * email coupons.
	 * @param customerSupportReq
	 * @return
	 */
	public CustomerSupportResponseVO emailCoupons(CustomerSupportRequestVO customerSupportReq);

	
	/**
	 * Send mail & save the same into db.
	 * @param customerSupportReq
	 * @return
	 */
	public CustomerSupportResponseVO sendMail(CustomerSupportRequestVO customerSupportReq);

	
	/**
	 * Returns Mazda customer care info like customer care email & phone etc.
	 * @return
	 */
	public CustomerSupportResponseVO getCustomerCareInfo();

	/**
	 * Returns agreement message to send crash log to Mazda.
	 * @return
	 */
	public CustomerSupportResponseVO getCrashAgreementMessage();
	
	/**
	 * This method is used to create crash log of devices using MMG services
	 * @param customerSupportReq
	 * @return
	 */
	public CustomerSupportResponseVO createCrashLog(CustomerSupportRequestVO customerSupportReq);
	
	
	/**
	 * This function is responsible for fetching SSG & corresponding resource file path.
	 * @return
	 */
	public CustomerSupportResponseVO getSsgInfo(String year,String model);

	/**
	 * This function is responsible for fetching SSG & corresponding resource file path for web browser client.
	 * @return
	 */
	public CustomerSupportResponseVO getSsgInfoForWeb(String year,String model,String trim,String deviceType);
	
	/**
	 * This function is responsible for fetching SSG & corresponding resource file path.
	 * @return
	 */
	public CustomerSupportResponseVO getSsgInfo(String year,String model,String trim);
	
	/**
	 * This function is responsible for fetching all the available service type from DB
	 */
	public ArrayList getServiceTypes();

	public ArrayList getTestInfo(String qry);
		
	
}
