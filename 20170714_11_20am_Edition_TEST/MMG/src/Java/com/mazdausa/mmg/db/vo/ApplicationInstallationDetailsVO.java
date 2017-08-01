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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This Class will contain the details of an Single Client Application Installation on the Client Device.
 * @author PankajB
 * @version 1.0
 */
@Entity
@Table(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS)
@NamedQueries({@NamedQuery(name="com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO.findApplicationInstallationByGUID",query="from com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO  where trim(upper(appInstallationGuid))=?"),
               @NamedQuery(name="com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO.findApplicationInstallationByAPNSToken",query="from com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO  where trim(upper(deviceAPNSToken))=?") })
public class ApplicationInstallationDetailsVO implements Serializable{

    private int appInstllationDetailsId;
    private String mobileImei,phoneType,mobileUserAgent,appInstallationGuid,userIp,phoneBrand,mobileNumber,mobileImsi,deviceAPNSToken;
    private Date addedDate;
    private ApplicationDetailsVO appDetailsVO;

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_ADDEDDATE)
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_APPINSTALLATIONGUID)
    public String getAppInstallationGuid() {
        return appInstallationGuid;
    }

    public void setAppInstallationGuid(String appInstallationGuid) {
        this.appInstallationGuid = appInstallationGuid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_APPINSTALLDETAILSID)
    public int getAppInstllationDetailsId() {
        return appInstllationDetailsId;
    }

    public void setAppInstllationDetailsId(int appInstllationDetailsId) {
        this.appInstllationDetailsId = appInstllationDetailsId;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_DEVICEAPNSTOKEN)
    public String getDeviceAPNSToken() {
        return deviceAPNSToken;
    }

    public void setDeviceAPNSToken(String deviceAPNSToken) {
        this.deviceAPNSToken = deviceAPNSToken;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILEIMEI)
    public String getMobileImei() {
        return mobileImei;
    }

    public void setMobileImei(String mobileImei) {
        this.mobileImei = mobileImei;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILEIMSI)
    public String getMobileImsi() {
        return mobileImsi;
    }

    public void setMobileImsi(String mobileImsi) {
        this.mobileImsi = mobileImsi;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILENO)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILEUSERAGENT)
    public String getMobileUserAgent() {
        return mobileUserAgent;
    }

    public void setMobileUserAgent(String mobileUserAgent) {
        this.mobileUserAgent = mobileUserAgent;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_PHONEBRAND)
    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_PHONETYPE)
    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_USERIP)
    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    @ManyToOne(fetch=FetchType.EAGER,targetEntity=ApplicationDetailsVO.class)
    @JoinColumn(name=DatabaseConstants.TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_APPDETAILSID)
    public ApplicationDetailsVO getAppDetailsVO() {
        return appDetailsVO;
    }

    public void setAppDetailsVO(ApplicationDetailsVO appDetailsVO) {
        this.appDetailsVO = appDetailsVO;
    }

    /**
     * This is an Simple Implementation of toString() Method.
     * @return
     */
    public String toString()
    {
        return "GUID- " + this.getAppInstallationGuid() + " : "+ this.getMobileNumber() + " : "+ this.getMobileUserAgent();
    }
    



}
