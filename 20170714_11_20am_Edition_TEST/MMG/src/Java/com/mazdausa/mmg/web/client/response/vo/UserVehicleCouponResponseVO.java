/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailFullCircleDataVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailVO;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain the details of VIN Coupon/Service Reminders.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehicleCouponResponseVO {

    private String status,description,count;
    private List<CouponRemindersDataDetailVO> coupons;
    private CouponRemindersDataDetailFullCircleDataVO fullCircleData;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<CouponRemindersDataDetailVO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponRemindersDataDetailVO> coupons) {
        this.coupons = coupons;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CouponRemindersDataDetailFullCircleDataVO getFullCircleData() {
        return fullCircleData;
    }

    public void setFullCircleData(CouponRemindersDataDetailFullCircleDataVO fullCircleData) {
        this.fullCircleData = fullCircleData;
    }

    
    

}
