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
public class UserVehicleReminderResponseVO {

    private String status, description, count;
    /* OLD OBJECT which are previously used.
    private List<CouponRemindersDataDetailVO> servicereminders;
    private String titleDesc, titleText;
    private CouponRemindersDataDetailFullCircleDataVO fullCircleData;*/


    // This is for the new service Reminders.
    private List<UserVehicleServiceReminderVO> serviceReminders;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public List<UserVehicleServiceReminderVO> getServiceReminders() {
        return serviceReminders;
    }

    public void setServiceReminders(List<UserVehicleServiceReminderVO> serviceReminders) {
        this.serviceReminders = serviceReminders;
    }




}
