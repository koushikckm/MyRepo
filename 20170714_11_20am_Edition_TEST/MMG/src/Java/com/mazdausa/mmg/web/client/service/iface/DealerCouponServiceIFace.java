/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.service.iface;

import com.mazdausa.mmg.web.client.response.vo.DealerCouponResponseVO;
import javax.servlet.http.HttpServletRequest;

/**
 * This Interface will contain the list of functions that are related to Dealer Coupon
 * @author PankajB
 */
public interface DealerCouponServiceIFace {


    /**
     * This Function retrieve Dealer Coupons by Dealer ID
     * @param dealerId
     * @return
     */
    public DealerCouponResponseVO getDealerCouponByDealerId(String dealerId, HttpServletRequest request);


    /**
     * This Function retrieve Dealer Coupons by Dealer ID and COUPON ID.
     * @param dealerId
     * @param couponId
     * @return
     */
    public DealerCouponResponseVO getDealerCouponByDealerIdAndCouponId(String dealerId,String couponId);
}
