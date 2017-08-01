/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.vo;

import com.mazdausa.mmg.db.constants.DatabaseConstants;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This Class will represent an Service Reminder Value Object.
 * @author PankajB
 * @version 1.0
 */
@Entity
@Table(name=DatabaseConstants.TABLE_MMG_APP_CUSTOMER_SERVICE_REMINDERS)
@NamedQueries(@NamedQuery(name="com.mazdausa.mmg.db.vo.ServiceReminderVO.findServiceReminderByVinDealerCodeCouponNumber",query="from com.mazdausa.mmg.db.vo.ServiceReminderVO where trim(upper(vin))=? and trim(upper(dealercode))=? and trim(upper(couponserviceremindernumber))=? and isalertActivated=true"))
public class ServiceReminderVO implements Serializable {

   private int servicereminders_ident, senttimes;
   private String vin, dealercode, couponserviceremindernumber;
   private Date addeddate;
   private boolean isalertActivated;

    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_COUPON_SERVICEREMINDER_NUMBER)
    public String getCouponserviceremindernumber() {
        return couponserviceremindernumber;
    }

    public void setCouponserviceremindernumber(String couponserviceremindernumber) {
        this.couponserviceremindernumber = couponserviceremindernumber;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_DEALER_CODE)
    public String getDealercode() {
        return dealercode;
    }

    public void setDealercode(String dealercode) {
        this.dealercode = dealercode;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_IS_ALERT_ACTIVATED)
    public boolean getIsalertActivated() {
        return isalertActivated;
    }

    public void setIsalertActivated(boolean isalertActivated) {
        this.isalertActivated = isalertActivated;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_SENT_TIMES)
    public int getSenttimes() {
        return senttimes;
    }

    public void setSenttimes(int senttimes) {
        this.senttimes = senttimes;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_SERVICE_REMINDER_IDENT)
    public int getServicereminders_ident() {
        return servicereminders_ident;
    }

    public void setServicereminders_ident(int servicereminders_ident) {
        this.servicereminders_ident = servicereminders_ident;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_ADDED_DATE)
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(Date addeddate) {
        this.addeddate = addeddate;
    }

}
