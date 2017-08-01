/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.rest.response.vo.DealerCouponVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsErrorDescriptionVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerCouponsErrorVO;
import java.util.List;

/**
 * This Value Object will contain the list of attributes that needs to be sent to the Client
 * @author PankajB
 * @version 1.0
 */
public class DealerCouponResponseVO {

    private String status,description,count;
    private List<DealerCouponVO> coupons;
    private List<RestDealerCouponsErrorDescriptionVO> errors;

    public List<DealerCouponVO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<DealerCouponVO> coupons) {
        this.coupons = coupons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RestDealerCouponsErrorDescriptionVO> getErrors() {
        return errors;
    }

    public void setErrors(List<RestDealerCouponsErrorDescriptionVO> errors) {
        this.errors = errors;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    
    
    
}
