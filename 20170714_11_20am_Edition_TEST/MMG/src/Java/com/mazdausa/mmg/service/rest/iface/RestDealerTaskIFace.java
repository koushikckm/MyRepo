/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.iface;

import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsResponseVO;

/**
 * This interface will contain a list of functions that will be related to Dealer.
 * @author PankajB
 * @version 1.0
 */
public interface RestDealerTaskIFace {

    /**
     * This Function is being responsible for getting the Dealer Coupons by Dealer ID
     * @param dealerId
     * @return
     */
    public RestDealerCouponsResponseVO getDealerCoupons(String dealerId);

    /**
     * This Function is being responsible for getting the Dealer Coupons by Dealer ID and Coupon ID
     * @param dealerId
     * @param couponId
     * @return
     */
    public RestDealerCouponsResponseVO getDealerCouponsByCouponId(String dealerId,String couponId);

}
