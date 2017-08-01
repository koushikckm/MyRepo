/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.impl;

import com.emm.v1.constants.EMMConstants;
import com.googlecode.ehcache.annotations.Cacheable;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestDealerTaskIFace;
import com.mazdausa.mmg.service.rest.response.vo.DealerCouponVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsResponseVO;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This is the Service Layer Implementation of RestDealerTaskIFace and contain all the functions definitions.
 * @author PankajB
 */
@Component
public class RestDealerTaskImpl implements RestDealerTaskIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestDealerTaskImpl.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    EMMConstants emmConstants;

    /**
     * This Function will return the set of Coupons for a given dealer ID
     * @param dealerId
     * @return
     */
  //  @Cacheable(cacheName = "DealerCouponCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestDealerCouponsResponseVO getDealerCoupons(String dealerId) {
        logger.debug(">> Entering {} getDealerCoupons() with DealerID = {}", this.getClass(), dealerId);
        RestDealerCouponsResponseVO dealerCouponsResponseVO = null;
        StringReader reader = null;
        try {

            //dealerCouponsResponseVO = restTemplate.getForObject(appConstants.SERVICE_REST_DEALER_GETCOUPONS_DEALERID, RestDealerCouponsResponseVO.class, dealerId);

            reader = new StringReader(restTemplate.getForEntity(appConstants.SERVICE_REST_DEALER_GETCOUPONS_DEALERID, String.class, dealerId).getBody());
            dealerCouponsResponseVO = JAXB.unmarshal(reader, RestDealerCouponsResponseVO.class);
            reader = null;

            if (dealerCouponsResponseVO != null && dealerCouponsResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                logger.debug("MAZDA DEALER GET COUPONS Web Service did not return  OK Response Code . Response Code = {}", dealerCouponsResponseVO.getStatus());
                dealerCouponsResponseVO.setCoupons(new ArrayList<DealerCouponVO>());
                //dealerCouponsResponseVO.setErrors(dealerCouponsResponseVO.getErrors());
                dealerCouponsResponseVO.setErrors(dealerCouponsResponseVO.getErrors());

            }
        } catch (Exception ex) {
            logger.error("An Error has been occured, while Getting list of Coupons by DEALER {}", ex);
            if (reader == null) {
                dealerCouponsResponseVO = new RestDealerCouponsResponseVO();
                dealerCouponsResponseVO.setStatus(emmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }
        }

        logger.debug("<< Exiting getDealerCoupons() with response= {}" + dealerCouponsResponseVO);
        return dealerCouponsResponseVO;
    }

    /**
     * This Function will return the set of Coupons for a given Dealer ID and COUPON ID.
     * @param dealerId
     * @param couponId
     * @return
     */
 //   @Cacheable(cacheName = "DealerCouponCache", keyGeneratorName = "defaultCacheKeyGenerator")
    public RestDealerCouponsResponseVO getDealerCouponsByCouponId(String dealerId, String couponId) {
        logger.debug(">> Entering {} getDealerCouponsByCouponId() with DealerID = {}", this.getClass(), dealerId);
        RestDealerCouponsResponseVO dealerCouponsResponseVO = null;
        StringReader reader=null;
        try {

            Map<String, String> urlVariables = new HashMap<String, String>();
            urlVariables.put(appConstants.SERVICE_REST_DEALER_GETCOUPONS_PARAMETER_DEALERID, dealerId);
            urlVariables.put(appConstants.SERVICE_REST_DEALER_GETCOUPONS_PARAMETER_COUPONID, couponId);

             reader = new StringReader(restTemplate.getForEntity(appConstants.SERVICE_REST_DEALER_GETCOUPONS_DEALERID_COUPONID, String.class, urlVariables).getBody());
            dealerCouponsResponseVO = JAXB.unmarshal(reader, RestDealerCouponsResponseVO.class);
            reader = null;
            //dealerCouponsResponseVO = restTemplate.getForObject(appConstants.SERVICE_REST_DEALER_GETCOUPONS_DEALERID_COUPONID, RestDealerCouponsResponseVO.class, urlVariables);

            if (dealerCouponsResponseVO != null && dealerCouponsResponseVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == false) {
                logger.debug("MAZDA DEALER GET COUPONS Web Service did not return  OK Response Code . Response Code = {}", dealerCouponsResponseVO.getStatus());
                dealerCouponsResponseVO.setCoupons(new ArrayList<DealerCouponVO>());
                dealerCouponsResponseVO.setErrors(dealerCouponsResponseVO.getErrors());

            }
        } catch (Exception ex) {
            logger.error("An Error has been occured, while Getting list of Coupons by DEALER with Coupon ID{}", ex);
            if (reader == null) {
                dealerCouponsResponseVO = new RestDealerCouponsResponseVO();
                dealerCouponsResponseVO.setStatus(emmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }
        }

        logger.debug("<< Exiting getDealerCouponsByCouponId() with response= {}" + dealerCouponsResponseVO);
        return dealerCouponsResponseVO;
    }
}
