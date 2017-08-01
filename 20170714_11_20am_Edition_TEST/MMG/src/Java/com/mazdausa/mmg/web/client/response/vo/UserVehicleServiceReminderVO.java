/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailFullCircleDataVO;
import com.mazdausa.mmg.service.soap.response.vo.CouponRemindersDataDetailVO;
import java.util.List;

/**
 * This is the Value Object, which will represent an individual SR that is to be returned to the client.
 * @author PankajB
 * @version 1.0
 * @since 10 Aug 2011
 */
public class UserVehicleServiceReminderVO {

    private List<CouponRemindersDataDetailVO> coupons;
    private String titleDesc, titleText;
    private CouponRemindersDataDetailFullCircleDataVO fullCircleData;
    private String serviceReminderDate,contactOfferId;

    public CouponRemindersDataDetailFullCircleDataVO getFullCircleData() {
        return fullCircleData;
    }

    public void setFullCircleData(CouponRemindersDataDetailFullCircleDataVO fullCircleData) {
        this.fullCircleData = fullCircleData;
    }

    public List<CouponRemindersDataDetailVO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponRemindersDataDetailVO> coupons) {
        this.coupons = coupons;
    }

    

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getContactOfferId() {
        return contactOfferId;
    }

    public void setContactOfferId(String contactOfferId) {
        this.contactOfferId = contactOfferId;
    }

    public String getServiceReminderDate() {
        return serviceReminderDate;
    }

    public void setServiceReminderDate(String serviceReminderDate) {
        this.serviceReminderDate = serviceReminderDate;
    }

    
    
    


}
