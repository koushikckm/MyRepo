/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.iface;

import com.googlecode.ehcache.annotations.Cacheable;
import com.mazdausa.mmg.service.rest.request.vo.RestDealerSearchLocatorRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerSearchResultVO;

/**
 * This Interface will contain all the functions that are responsible for searching dealer across the Mazda Network through the
 * Web Service Provided.
 * @author PankajB
 * @version 1.0
 */
public interface RestDealerSearchLocatorIFace {


    /**
     * This Function will be responsible for searching limited set of dealers across the Mazda Network.
     * @param zipCode
     * @return
     */
    public RestDealerSearchResultVO performLimitedDealerSearch(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO);

     /**
     * This Function will be responsible for searching dealer across the Mazda.
     * @param dealerSearchLocatorVO
     * @return
     */
    public RestDealerSearchResultVO performDealerSearchByZipCode(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO);

    /**
     * This Function will be responsible for searching dealer across the Mazda by State & City.
     * @param dealerSearchLocatorVO
     * @return
     */
    public RestDealerSearchResultVO performDealerSearchByStateCity(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO);

    /**
     * This Function search the Dealers by the name provided.
     * @param dealerSearchLocatorVO
     * @return
     */
    public RestDealerSearchResultVO performDealerSearchByName(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO);

    /**
     * This is the function, which will be responsible for searching the dealer by providing the Latitude, Longitude.
     * @param dealerSearchLocatorVO
     * @return
     */
    public RestDealerSearchResultVO performDealerSearchByLatLong(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO);
}
