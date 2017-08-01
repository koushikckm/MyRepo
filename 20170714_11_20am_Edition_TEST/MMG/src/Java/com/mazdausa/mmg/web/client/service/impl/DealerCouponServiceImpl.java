/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.service.impl;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestDealerTaskIFace;
import com.mazdausa.mmg.service.rest.response.vo.DealerCouponVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsErrorDescriptionVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsErrorVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import com.mazdausa.mmg.web.client.response.vo.DealerCouponResponseVO;
import com.mazdausa.mmg.web.client.service.iface.DealerCouponServiceIFace;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This VO will represent the Service Layer Implementation of the Dealer Coupon Service.
 * @author PankajB
 * @version 1.0
 */
@Service
public class DealerCouponServiceImpl implements DealerCouponServiceIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DealerCouponServiceImpl.class);
    @Autowired
    RestDealerTaskIFace dealerTaskService;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmgUtilities;

    /**
     * This Function will search for the Dealer Coupons.
     * @param dealerId
     * @return
     */
    public DealerCouponResponseVO getDealerCouponByDealerId(String dealerId, HttpServletRequest request) {
        logger.debug(">> Entering getDealerCouponByDealerId() with DEALER ID ={}" + dealerId);
        DealerCouponResponseVO dealerCouponResponeVO = new DealerCouponResponseVO();

        RestDealerCouponsResponseVO restDealerCouponsResponseVO = dealerTaskService.getDealerCoupons(dealerId);

        if (restDealerCouponsResponseVO != null && restDealerCouponsResponseVO.getCoupons() != null && restDealerCouponsResponseVO.getCoupons().isEmpty() == false && restDealerCouponsResponseVO.getErrors() == null) {

            if (restDealerCouponsResponseVO.getCoupons() != null) {
                dealerCouponResponeVO.setCount(String.valueOf(restDealerCouponsResponseVO.getCoupons().size()));
                dealerCouponResponeVO.setCoupons(restDealerCouponsResponseVO.getCoupons());


                 // Iterate through the Coupons list and check the Deal, if it is zero,

               /* for (DealerCouponVO dealerCouponVO : restDealerCouponsResponseVO.getCoupons()) {

                    if (dealerCouponVO.getDeal() != null && dealerCouponVO.getDeal().trim().length() != 0 && dealerCouponVO.getDeal().trim().length()<=14) {

                    } else {
                        //dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        logger.debug("THE SAVE Value is : "+ appConstants.DEALER_COUPON_PRICE_SAVE);

                    }
            //        checkSpecialCouponIds(restDealerCouponsResponseVO.getCoupons());
               }*/
            }
            //checkSpecialCouponIds(restDealerCouponsResponseVO.getCoupons()); //Here it is calling the special coupon check function
            if(request.getHeader("User-Agent") != null && request.getHeader("User-Agent").trim().contains(EmmConstants.EMM_USER_AGENT_TYPE_ANDROID)){
                //here it is calling the function for checking the deal and description for Android
                checkDealAndDescription(restDealerCouponsResponseVO.getCoupons());
            }else{
                //Here its calling the special coupon check function
                checkSpecialCouponIds(restDealerCouponsResponseVO.getCoupons());
            }
            dealerCouponResponeVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {

            dealerCouponResponeVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            StringBuilder errorDescription = new StringBuilder();
            List<RestDealerCouponsErrorDescriptionVO> listOfDealerCouponsErrors = new ArrayList<RestDealerCouponsErrorDescriptionVO>();
            if (restDealerCouponsResponseVO != null && restDealerCouponsResponseVO.getErrors() != null) {
                for (RestDealerCouponsErrorVO restDealerCouponsErrorVO : restDealerCouponsResponseVO.getErrors()) {
                    listOfDealerCouponsErrors.addAll(restDealerCouponsErrorVO.getErrors());
                    for (RestDealerCouponsErrorDescriptionVO errorDescrptionVO : restDealerCouponsErrorVO.getErrors()) {
                        errorDescription.append(errorDescrptionVO.getDescription()).append(" : ");

                    }
                }

            }
            dealerCouponResponeVO.setErrors(listOfDealerCouponsErrors);

            dealerCouponResponeVO.setDescription(errorDescription.toString());

            if (restDealerCouponsResponseVO != null && restDealerCouponsResponseVO.getStatus() != null) {
                dealerCouponResponeVO.setDescription(dealerCouponResponeVO.getDescription() + restDealerCouponsResponseVO.getStatus());

            }
            errorDescription = null;
        }

        logger.debug("<< Exiting getDealerCouponByDealerId() with result = {}", dealerCouponResponeVO);

        return dealerCouponResponeVO;
    }

    /**
     * This is the function, which will be responsible for getting Dealer Coupons by Dealer id and COUPON ID
     * @param dealerId
     * @param couponId
     * @return
     */
    public DealerCouponResponseVO getDealerCouponByDealerIdAndCouponId(String dealerId, String couponId) {
        logger.debug(">> Entering getDealerCouponByDealerIdAndCouponId() with DEALER ID ={}" + dealerId);
        DealerCouponResponseVO dealerCouponResponeVO = new DealerCouponResponseVO();

        RestDealerCouponsResponseVO restDealerCouponsResponseVO = dealerTaskService.getDealerCouponsByCouponId(dealerId, couponId);

        if (restDealerCouponsResponseVO != null && restDealerCouponsResponseVO.getCoupons() != null && restDealerCouponsResponseVO.getCoupons().isEmpty() == false && restDealerCouponsResponseVO.getErrors() == null) {

            if (restDealerCouponsResponseVO.getCoupons() != null) {
                dealerCouponResponeVO.setCount(String.valueOf(restDealerCouponsResponseVO.getCoupons().size()));
                dealerCouponResponeVO.setCoupons(restDealerCouponsResponseVO.getCoupons());

                // Iterate through the Coupons list and check the Deal, if it is zero,
                for (DealerCouponVO dealerCouponVO : restDealerCouponsResponseVO.getCoupons()) {
                    if (dealerCouponVO.getDeal() != null && dealerCouponVO.getDeal().trim().length() != 0 && dealerCouponVO.getDeal().trim().length() <=14) {

                    } else {
                        dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        logger.debug("THE SAVE Value is : "+ appConstants.DEALER_COUPON_PRICE_SAVE);
                    }
                }

            }
            checkSpecialCouponIds(restDealerCouponsResponseVO.getCoupons()); //Here its calling the special coupon check function
            dealerCouponResponeVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {

            dealerCouponResponeVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            StringBuilder errorDescription = new StringBuilder();
            List<RestDealerCouponsErrorDescriptionVO> listOfDealerCouponsErrors = new ArrayList<RestDealerCouponsErrorDescriptionVO>();
            if (restDealerCouponsResponseVO != null && restDealerCouponsResponseVO.getErrors() != null) {
                for (RestDealerCouponsErrorVO restDealerCouponsErrorVO : restDealerCouponsResponseVO.getErrors()) {
                    listOfDealerCouponsErrors.addAll(restDealerCouponsErrorVO.getErrors());
                    for (RestDealerCouponsErrorDescriptionVO errorDescrptionVO : restDealerCouponsErrorVO.getErrors()) {
                        errorDescription.append(errorDescrptionVO.getDescription()).append(" : ");

                    }
                }

            }
            dealerCouponResponeVO.setErrors(listOfDealerCouponsErrors);

            dealerCouponResponeVO.setDescription(errorDescription.toString());
            if (restDealerCouponsResponseVO != null && restDealerCouponsResponseVO.getStatus() != null) {
                dealerCouponResponeVO.setDescription(dealerCouponResponeVO.getDescription() + restDealerCouponsResponseVO.getStatus());


            }
            errorDescription = null;
        }

        logger.debug("<< Exiting getDealerCouponByDealerIdAndCouponId() with result = {}", dealerCouponResponeVO);

        return dealerCouponResponeVO;
    }

    /*
     * This function is to compare the special coupon such as 5,14,22,24,32,39 and 229
     * @param dealerCouponVOList
     * @return
     */

    public void checkSpecialCouponIds(List<DealerCouponVO> dealerCouponVOList){

        logger.debug(">> Enters the funciton for checking Special Coupon");
        if(dealerCouponVOList != null){
            for(DealerCouponVO dealerCouponVO : dealerCouponVOList){
		String couponId = dealerCouponVO.getId();
                List<String> descriptionList = new ArrayList<String>();
                //adding the description to the description list object
                if(dealerCouponVO.getDescriptions()!=null){
                        descriptionList.addAll(dealerCouponVO.getDescriptions());
                } else {
                        descriptionList.add(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_COUPON_DEFAULT_LINETEXT);
                }
                String deal = dealerCouponVO.getDeal();
                //Getting the special coupon
                String specialCouponNumbersList = appConstants.MMG_SERVICEREMINDER_SPECIAL_COUPON;
                String[] specialCouponNumber = specialCouponNumbersList.split(",");
                boolean isSuccess=false; // after review
                //Here checking for the sepcial coupon
                for(int i = 0;i<specialCouponNumber.length;i++){
                    if(specialCouponNumber[i].trim() != null && couponId.trim().equalsIgnoreCase(specialCouponNumber[i].trim())){
                        dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        if(dealerCouponVO.getDeal() != null && dealerCouponVO.getDeal().trim().length() !=0) { //scenario 2
                            descriptionList.add(deal);
                            isSuccess=true;
                            break; // after review
                        }                       
                    }
                }   // for close after review
                //else {
                if(isSuccess==false){
                    if(dealerCouponVO.getDeal() != null && dealerCouponVO.getDeal().trim().length() != 0){
                        if (deal.trim().length() <= appConstants.DEALERCOUPON_SERVICEREMINDER_PRICE_TEXT_LENGTH) {//scenario 4

                                
                            } else { //scenario 3
                                dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                                //descriptionList.add(deal);
                                List<String> descriptionListTemp = new ArrayList<String>();
                                descriptionListTemp.addAll(descriptionList);
                                descriptionList.clear();
                                descriptionList.add(deal);
                                descriptionList.addAll(descriptionListTemp);                                
                            }
                        } else { //scenario 5
                            dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        }
                    //}
                    //dealerCouponVO.setDescriptions(descriptionList);
                }
                dealerCouponVO.setDescriptions(mmgUtilities.dataToBeModify(appConstants.MMG_STRING_BR_TAG,descriptionList));  //setting the description after checking for <br /> tag
                dealerCouponVO.setDisclaimer(mmgUtilities.dataToBeModifyForDesclaimer(appConstants.MMG_STRING_BR_TAG,dealerCouponVO.getDisclaimer()));//setting the desclaimer after checking for <br />tag
                logger.debug("<< Exits the function for checking special coupon {} ", dealerCouponVO.getDescriptions());
            }
			
        }
    }
    /*
     * This function is for Android to check deal and description
     * @param dealerCouponVOList
     * @return null
     */
    public void checkDealAndDescription(List<DealerCouponVO> dealerCouponVOList){
        logger.debug(">> Enter the checkDealAndDescription() {} to check deal and description for Android", dealerCouponVOList);
        String delimeter = "<br />";
        if(dealerCouponVOList != null){
            for(DealerCouponVO dealerCouponVO : dealerCouponVOList){
                List<String> descriptionList = new ArrayList<String>();
                String deal = dealerCouponVO.getDeal();
                if(deal != null){
                    if(deal.trim().length()<=appConstants.DEALERCOUPON_SERVICEREMINDER_ANDROID_PRICE_TEXT_LENGTH){
                        dealerCouponVO.setDeal(deal);
                        //descriptionList.addAll(dealerCouponVO.getDescriptions());
                    }else{
                        dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        descriptionList.add(deal);
                        //descriptionList.addAll(dealerCouponVO.getDescriptions());
                    }
                    if(dealerCouponVO.getDescriptions()!=null){
                        descriptionList.addAll(dealerCouponVO.getDescriptions());
                    }else{
                        descriptionList.add(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_COUPON_DEFAULT_LINETEXT);
                    }
                }else{
                    if(dealerCouponVO.getDescriptions() != null){
                        dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        descriptionList.addAll(dealerCouponVO.getDescriptions());
                    }else{
                        dealerCouponVO.setDeal(appConstants.DEALER_COUPON_PRICE_SAVE);
                        descriptionList.add(appConstants.MMG_CUSTOM_MESSAGE_SERVICEREMINDERS_COUPON_DEFAULT_LINETEXT);
                    }
                }
                //dealerCouponVO.setDescriptions(descriptionList);
                dealerCouponVO.setDescriptions(mmgUtilities.dataToBeModify(appConstants.MMG_STRING_BR_TAG,descriptionList));  //setting the description after checking for <br /> tag
                dealerCouponVO.setDisclaimer(mmgUtilities.dataToBeModifyForDesclaimer(appConstants.MMG_STRING_BR_TAG,dealerCouponVO.getDisclaimer()));//setting the desclaimer after checking for <br />tag
            }
        }
    }
}
