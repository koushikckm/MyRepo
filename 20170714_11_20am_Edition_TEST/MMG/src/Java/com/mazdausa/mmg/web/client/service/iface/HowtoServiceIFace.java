package com.mazdausa.mmg.web.client.service.iface;

import com.mazdausa.mmg.web.client.response.vo.HowToLookUpResponseVO;

/**
 * This is an Service level Interface and being responsible for all Service Layer Functions.
 * @author AnilK
 *
 */
public interface HowtoServiceIFace {

	/**
	 * Fetch how to  listing based on Year model input
	 * @param model
	 * @param year
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItems(String model,String year);

	
	/**
	 * Fetch how to  item details based on Year model input
	 * @param type
	 * @param id
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItemDetail(String type,String id);
	
	
	/**
	 * Fetch how to items listing based on Year model and trim input
	 * @param model
	 * @param year
	 * @param trim
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItems(String model,String year, String trim);
	
	/**
	 * Fetch how to items listing for 2014 q2 based on Year model and trim input
	 * @param model
	 * @param year
	 * @param trim
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItemsWithFilterObjects(String model,String year, String trim);

	/**
	 * Fetch how to items listing for 2014 q2 based on Year model and trim input for web applications.
	 * @param model
	 * @param year
	 * @param trim
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItemsWithFilterObjects(String model,String year, String trim,String userAgent);
	
	
	/**
	 * Fetch how to items listing based on search string
	 * 
	 * @param searchStr
	 * @return HowToLookUpResponseVO
	 */
	public HowToLookUpResponseVO getHowToSearchItems(String searchStr);

	/**
	 * Fetch how to items listing based on search string,Year & model
	 * 
	 * @param searchStr
	 * @return HowToLookUpResponseVO
	 */
	public HowToLookUpResponseVO getHowToSearchItems(String year,String model,String searchStr);
	
	
	/**
	 * Fetch how to items listing based on search string,Year & model
	 * 
	 * @param searchStr
	 * @return HowToLookUpResponseVO
	 */
	public HowToLookUpResponseVO getHowToSearchItemsWithFilterObjects(String year,String model,String searchStr);
}
