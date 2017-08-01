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
 * This Class will represent the Application Details of an Application that is present in the Database.
 * @author PankajB
 * @version 1.0
 */
@Entity
@Table(name=DatabaseConstants.TABLE_MMG_APP_DETAILS)
@NamedQueries({@NamedQuery(name="com.mazdausa.mmg.db.vo.ApplicationDetailsVO.findApplicationByAppVersion",query="from com.mazdausa.mmg.db.vo.ApplicationDetailsVO  where trim(upper(appVersion))=? and appDeleted=false"),
               @NamedQuery(name="com.mazdausa.mmg.db.vo.ApplicationDetailsVO.findApplicationByAppVersion_Name_OS",query="from com.mazdausa.mmg.db.vo.ApplicationDetailsVO  where  appDeleted=false and trim(upper(appName))=? and trim(upper(appVersion))=? and trim(upper(clientOsType))=?") })
public class ApplicationDetailsVO implements Serializable{

    private int appDetailsId;
    private String appName,appVersion,appPath,ip,addedBy,deletedBy,clientOsType,clientOsMinVersion,clientOsMaxVersion,appFeatureList;
    private Date addedDate,deletedDate;
    private boolean upgradedVersion,appDeleted;


    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_ADDEDBY)
    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_ADDEDDATE)
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_APPDETAILSID)
    public int getAppDetailsId() {
        return appDetailsId;
    }

    public void setAppDetailsId(int appDetailsId) {
        this.appDetailsId = appDetailsId;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_APPFEATURELIST)
    public String getAppFeatureList() {
        return appFeatureList;
    }

    public void setAppFeatureList(String appFeatureList) {
        this.appFeatureList = appFeatureList;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_APPNAME)
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_APPPATH)
    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_APPVERSION)
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_CLIENTOSMAXVERSION)
    public String getClientOsMaxVersion() {
        return clientOsMaxVersion;
    }

    public void setClientOsMaxVersion(String clientOsMaxVersion) {
        this.clientOsMaxVersion = clientOsMaxVersion;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_CLIENTOSMINVERSION)
    public String getClientOsMinVersion() {
        return clientOsMinVersion;
    }

    public void setClientOsMinVersion(String clientOsMinVersion) {
        this.clientOsMinVersion = clientOsMinVersion;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_CLIENTOSTYPE)
    public String getClientOsType() {
        return clientOsType;
    }

    public void setClientOsType(String clientOsType) {
        this.clientOsType = clientOsType;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_DELETEDBY)
    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_DELETEDDATE)
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_IP)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_ISAPPDELETED)
    public boolean isAppDeleted() {
        return appDeleted;
    }

    public void setAppDeleted(boolean appDeleted) {
        this.appDeleted = appDeleted;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_DETAILS_COLUMN_ISUPGRADEDVERSION)
    public boolean isUpgradedVersion() {
        return upgradedVersion;
    }

    public void setUpgradedVersion(boolean upgradedVersion) {
        this.upgradedVersion = upgradedVersion;
    }

    /**
     * This is simple Implementation of toString Method.
     * @return
     */
    public String toString()
    {
        return this.getAppName() + " : " + this.getAppVersion() + " : " + this.getClientOsType();
    }

}
