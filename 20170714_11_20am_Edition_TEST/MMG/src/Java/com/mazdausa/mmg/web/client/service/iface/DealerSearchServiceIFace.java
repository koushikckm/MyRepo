/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.service.iface;

import com.mazdausa.mmg.web.client.response.vo.DealerExpServiceVO;
import com.mazdausa.mmg.web.client.response.vo.DealerInfoResponseVO;
import com.mazdausa.mmg.web.client.response.vo.DealerSearchResponseVO;

/**
 * This is the service layer interface, that will be responsible for performing the Dealer Search.
 * @author PankajB
 * @version 1.0
 */
public interface DealerSearchServiceIFace {

    /**
     * This is the service layer function, which will be responsible for performing the search at the service Layer and bring the result.
     * @param zipCode ZipCode of the area
     * @return
     */
    public DealerSearchResponseVO dealerLimitedSearchByZipCode(String zipCode);

    /**
     * This function will return the Dealers b providing the ZipCode by taking pagination into account.
     * @param zipCode ZipCode
     * @param count  Number of Search Results Required
     * @param startFrom Start From Parameter.
     * @return
     */
    public DealerSearchResponseVO dealerSearchByZipCode(String zipCode,String count,String startFrom,String distance,String mazdaSpeedOnly);

   /**
     * This is the Function which is responsible for returning the Dealer Search by State and City
     * @param state State Code
     * @param city  City
     * @param count Number of Results Required
     * @param startFrom Starting Result Number
     * @param distance Distance within which the Dealer need to be searched
     * @param mazdaSpeedOnly True, if only MazdaSpeedOnly dealers are required else false.
     * @return
     */
    public DealerSearchResponseVO dealerSearchByStateCity(String state,String city,String count,String startFrom,String distance,String mazdaSpeedOnly) ;

    /**
     * This is the function, which will be responsible for searching the Dealer by its name.
     * @param dealerName Dealer name
     * @return
     */
    public DealerSearchResponseVO dealerSearchByName(String dealerName,String count,String startFrom) ;

    /**
     * This is the function, which will be responsible for searching the Dealer by providing the Longitude, and Latitude
     * @param longtiude
     * @param latitude
     * @return
     */
    public DealerSearchResponseVO dealerSearchByLongLat(String longtiude,String latitude,String count,String startFrom,String distance,String mazdaSpeedOnly) ;

    /**
     * This Function, will return the Dealer Information given the Dealer Information.
     * @param dealerCode
     * @return
     */
    public DealerInfoResponseVO getDealerInfo(String dealerCode);
    
    /**
     * Fetch express service popup details
     * @return
     */
    public DealerExpServiceVO getExpressServiceDetails(String baseUrl);
}
