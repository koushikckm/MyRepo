/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.impl;

import com.emm.v1.constants.EMMConstants;
import com.googlecode.ehcache.annotations.Cacheable;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestDealerSearchLocatorIFace;
import com.mazdausa.mmg.service.rest.request.vo.RestDealerSearchLocatorRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerSearchResultVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This is an Implementation class of the Dealer Search Locator Interface, thus providing an Concrete Implementation of all the functions
 * defined in the Search Locator Interface.
 * @author PankajB
 */
@Component
public class RestDealerSearchLocatorImpl implements RestDealerSearchLocatorIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestDealerSearchLocatorImpl.class);
    @Autowired
    ApplicationConstants AppConstants;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    RestTemplate restTemplate;

    /**
     * This Function will be responsible for searching dealer across the Mazda by LIMITED Dealer Search URL.
     * @param dealerSearchLocatorVO
     * @return
     */
    @Cacheable(cacheName = "DealerLocatorCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestDealerSearchResultVO performLimitedDealerSearch(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO) {
        logger.debug(">> Entering {} performLimitedDealerSearch() with zipCode = {}", this.getClass(), dealerSearchLocatorVO.getZipCode());
        RestDealerSearchResultVO searchResultVO = null;
        try {

            searchResultVO = restTemplate.getForObject(AppConstants.SERVICE_REST_DEALERLOCATOR_ZIPCODE_SEARCH_LIMITED, RestDealerSearchResultVO.class, dealerSearchLocatorVO.getZipCode());

            if (searchResultVO != null && searchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                logger.debug("Mazda Limited Dealer Locator Search Service does not return an OK Response Code . Response Code = {}", searchResultVO.getStatus());
                searchResultVO.setDealerCount(0);
                searchResultVO.setDealers(null);

            }
        } catch (Exception ex) {
            logger.error("An Error has been occured, while performign Limited Dealer Locator Search by ZIPCOde {}", ex);
        }

        logger.debug("<< Exiting performLimitedDealerSearch() with response= {}" + searchResultVO);
        return searchResultVO;
    }

    /**
     * This Function will be responsible for searching dealer across the Mazda.
     * @param dealerSearchLocatorVO
     * @return
     */
    @Cacheable(cacheName = "DealerLocatorCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestDealerSearchResultVO performDealerSearchByZipCode(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO) {
        logger.debug(">> Entering {} performDealerSearchByZipCode() with zipCode = {}", this.getClass(), dealerSearchLocatorVO.getZipCode());
        RestDealerSearchResultVO searchResultVO = null;
        try {
            // Create a Map of parameters.
            Map<String, String> listOfPathParameters = new HashMap<String, String>();
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_ZIPCODE, dealerSearchLocatorVO.getZipCode());

            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, "");
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, "");

            if (dealerSearchLocatorVO.isMazdaSpeedOnly() == true) {
                listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, Boolean.toString(Boolean.TRUE));
            }
            if (dealerSearchLocatorVO.getDistance() != -1) {
                listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, dealerSearchLocatorVO.getDistance() + "");
            }
            logger.debug("List Of Parameters = " + listOfPathParameters);
            searchResultVO = (RestDealerSearchResultVO) restTemplate.getForObject(AppConstants.SERVICE_REST_DEALERLOCATOR_SEARCH_ZIPCODE_DISTANCE_MAZDASPEEDONLY, RestDealerSearchResultVO.class, listOfPathParameters);

            //  searchResultVO = performDealerSearchByZipCode(dealerSearchLocatorVO.getZipCode(), String.valueOf(dealerSearchLocatorVO.isMazdaSpeedOnly()),String.valueOf(dealerSearchLocatorVO.getDistance()));


            // Start Factoring the SEARCH Results.
            if (searchResultVO != null) {
                logger.debug("Start From = " + dealerSearchLocatorVO.getStartFrom());
                logger.debug("COUNT From = " + dealerSearchLocatorVO.getCount());
                searchResultVO.setDealers(MMGUtilities.getSubStringList(searchResultVO.getDealers(), dealerSearchLocatorVO.getStartFrom(), dealerSearchLocatorVO.getCount()));

                if (searchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                    logger.debug("Mazda Limited Dealer Locator Search Service does not return an OK Response Code . Response Code = {}", searchResultVO.getStatus());
                    searchResultVO.setDealerCount(0);
                    searchResultVO.setDealers(null);
                    logger.debug("Setting the Error Response = " + searchResultVO.getErrorResponseVO());
                }
            }

        } catch (Exception ex) {
            logger.error("An Error has been occured, while performign Limited Dealer Locator Search by ZIPCOde {}", ex);
            
        }

        logger.debug("<< Exiting performDealerSearchByZipCode() with response= {}" + searchResultVO);

        return searchResultVO;
    }

    /**
     * This Function will be responsible for performing the Dealer Search by State & City.
     * @param dealerSearchLocatorVO
     * @return
     */
    @Cacheable(cacheName = "DealerLocatorCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestDealerSearchResultVO performDealerSearchByStateCity(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO) {

        logger.debug(">> Entering {} performDealerSearchByStateCity() with State = {} and City={}", new Object[]{this.getClass(), dealerSearchLocatorVO.getState(), dealerSearchLocatorVO.getCity()});
        RestDealerSearchResultVO searchResultVO = null;
        try {
            // Create a Map of parameters.
            Map<String, String> listOfPathParameters = new HashMap<String, String>();
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_STATE, dealerSearchLocatorVO.getState());
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_CITY, dealerSearchLocatorVO.getCity());
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, "");
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, "");

            if (dealerSearchLocatorVO.isMazdaSpeedOnly() == true) {
                listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, Boolean.toString(Boolean.TRUE));
            }
            if (dealerSearchLocatorVO.getDistance() != -1) {
                listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, dealerSearchLocatorVO.getDistance() + "");
            }

            logger.debug("List Of Search Parameters = " + listOfPathParameters);
            searchResultVO = restTemplate.getForObject(AppConstants.SERVICE_REST_DEALERLOCATOR_SEARCH_STATE_CITY_DISTANCE_SPEEDONLY, RestDealerSearchResultVO.class, listOfPathParameters);

            // Start Factoring the SEARCH Results.
            if (searchResultVO != null) {
                logger.debug("Start From = " + dealerSearchLocatorVO.getStartFrom());
                logger.debug("COUNT From = " + dealerSearchLocatorVO.getCount());
                searchResultVO.setDealers(MMGUtilities.getSubStringList(searchResultVO.getDealers(), dealerSearchLocatorVO.getStartFrom(), dealerSearchLocatorVO.getCount()));

                if (searchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                    logger.debug("Mazda Limited Dealer Locator Search Service does not return an OK Response Code . Response Code = {}", searchResultVO.getStatus());
                    searchResultVO.setDealerCount(0);
                    searchResultVO.setDealers(null);
                    logger.debug("Setting the Error Response = " + searchResultVO.getErrorResponseVO());
                }
            }

        } catch (Exception ex) {
            logger.error("An Error has been occured, while performign Limited Dealer Locator Search by State & City {}", ex);
        }

        logger.debug("<< Exiting performDealerSearchByStateCity() with response= {}" + searchResultVO);

        return searchResultVO;

    }

    /**
     * This Function will be responsible for searching the Dealer based upon the Dealer Name
     */
    @Cacheable(cacheName = "DealerLocatorCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestDealerSearchResultVO performDealerSearchByName(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO) {
        logger.debug(">> Entering {} performDealerSearchByName() with name = {}", this.getClass(), dealerSearchLocatorVO.getName());
        RestDealerSearchResultVO searchResultVO = null;
        try {

            searchResultVO = restTemplate.getForObject(AppConstants.SERVICE_REST_DEALERLOCATOR_SEARCH_NAME, RestDealerSearchResultVO.class, dealerSearchLocatorVO.getName());

            if (searchResultVO != null) {
                logger.debug("Start From = " + dealerSearchLocatorVO.getStartFrom());
                logger.debug("COUNT From = " + dealerSearchLocatorVO.getCount());
                searchResultVO.setDealers(MMGUtilities.getSubStringList(searchResultVO.getDealers(), dealerSearchLocatorVO.getStartFrom(), dealerSearchLocatorVO.getCount()));

                if (searchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                    logger.debug("Mazda Limited Dealer Locator Search Service does not return an OK Response Code . Response Code = {}", searchResultVO.getStatus());
                    searchResultVO.setDealerCount(0);
                    searchResultVO.setDealers(null);
                    logger.debug("Setting the Error Response = " + searchResultVO.getErrorResponseVO());
                }
            }
        } catch (Exception ex) {
            logger.error("An Error has been occured, while performign Limited Dealer Locator Search by NAME {}", ex);
        }

        logger.debug("<< Exiting performDealerSearchByName() with response= {}" + searchResultVO);
        return searchResultVO;
    }

    /**
     * Below is a list of Cacheable functions, that are being invoked for getting data from the web services.
     */
    /**
     * NOT USED CURRENTLY BUT FUTURE USE WOULD INCLUDE THIS.
     * This is the Cacheable function for the Dealer Locator by ZIP COde.
     * @param zipCode
     * @param speedOnly
     * @param distance
     * @return
     */
    @Cacheable(cacheName = "DealerLocatorCache", keyGeneratorName = "defaultCacheKeyGenerator")
    private RestDealerSearchResultVO performDealerSearchByZipCode(String zipCode, String speedOnly, String distance) {
        logger.debug(">>&& [Cacheable function] with parameters={}", new Object[]{zipCode, speedOnly, distance});
        Map<String, String> listOfPathParameters = new HashMap<String, String>();
        listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_ZIPCODE, zipCode);

        listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, "");
        listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, "");

        if (Boolean.valueOf(speedOnly) == true) {
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, Boolean.toString(Boolean.TRUE));
        }
        if (Integer.parseInt(distance) != -1) {
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, distance + "");
        }
        logger.debug("List Of Parameters = " + listOfPathParameters);

        return (RestDealerSearchResultVO) restTemplate.getForObject(AppConstants.SERVICE_REST_DEALERLOCATOR_SEARCH_ZIPCODE_DISTANCE_MAZDASPEEDONLY, RestDealerSearchResultVO.class, listOfPathParameters);
    }

    /**
     * This is the function, which will be responsible for Searching Dealer by the Latitude and Longitude.
     * @param dealerSearchLocatorVO
     * @return
     */
    public RestDealerSearchResultVO performDealerSearchByLatLong(RestDealerSearchLocatorRequestVO dealerSearchLocatorVO) {
        logger.debug(">> Entering {} performDealerSearchByLatLong() with Latitude = {} and Longitude ={}", new Object[]{this.getClass(), dealerSearchLocatorVO.getLongitude(), dealerSearchLocatorVO.getLatitude()});
        RestDealerSearchResultVO searchResultVO = null;
        try {
            // Create a Map of parameters.
            Map<String, String> listOfPathParameters = new HashMap<String, String>();
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_LONGITUDE, dealerSearchLocatorVO.getLongitude());
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_LATITUDE, dealerSearchLocatorVO.getLatitude());
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, "");
            listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, "");

            if (dealerSearchLocatorVO.isMazdaSpeedOnly() == true) {
                listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_SPEEDONLY, Boolean.toString(Boolean.TRUE));
            }
            if (dealerSearchLocatorVO.getDistance() != -1) {
                listOfPathParameters.put(AppConstants.DEALER_SEARCH_URL_PARAMETER_DISTANCE, dealerSearchLocatorVO.getDistance() + "");
            }

            logger.debug("List Of Search Parameters = " + listOfPathParameters);
            searchResultVO = restTemplate.getForObject(AppConstants.SERVICE_REST_DEALERLOCATOR_SEARCH_LONGLAT, RestDealerSearchResultVO.class, listOfPathParameters);

            // Start Factoring the SEARCH Results.
            if (searchResultVO != null) {
                logger.debug("Start From = " + dealerSearchLocatorVO.getStartFrom());
                logger.debug("COUNT From = " + dealerSearchLocatorVO.getCount());
                searchResultVO.setDealers(MMGUtilities.getSubStringList(searchResultVO.getDealers(), dealerSearchLocatorVO.getStartFrom(), dealerSearchLocatorVO.getCount()));

                if (searchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                    logger.debug("Mazda Dealer Locator Search Service does not return an OK Response Code . Response Code = {}", searchResultVO.getStatus());
                    searchResultVO.setDealerCount(0);
                    searchResultVO.setDealers(null);
                    logger.debug("Setting the Error Response = " + searchResultVO.getErrorResponseVO());
                }
            }

            listOfPathParameters = null;
        } catch (Exception ex) {
            logger.error("An Error has been occured, while performing Limited Dealer Locator Search by Longitude & Latitude. ", ex);
        }
        logger.debug("<< Exiting performDealerSearchByLatLong() with response= {}" + searchResultVO);

        return searchResultVO;
    }
}
