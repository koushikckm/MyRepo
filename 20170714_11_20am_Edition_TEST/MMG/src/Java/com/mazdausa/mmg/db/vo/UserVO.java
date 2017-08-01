/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.vo;

import com.mazdausa.mmg.db.constants.DatabaseConstants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This Class will represent an represent an Mazda Customer Record.
 * @author PankajB
 */
@Entity
@Table(name = DatabaseConstants.TABLE_MMG_APP_USERS)
@NamedQueries({
//    @NamedQuery(name = "com.mazdausa.mmg.db.vo.UserVO.findUserByMailId", query = "from com.mazdausa.mmg.db.vo.UserVO  where trim(upper(mailId))=? and userDeleted=false"),
//    @NamedQuery(name = "com.mazdausa.mmg.db.vo.UserVO.findUserByCustomerId", query = "from com.mazdausa.mmg.db.vo.UserVO  where  trim(upper(customerId))=? and userDeleted=false")
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.UserVO.findUserByMailId", query = "from com.mazdausa.mmg.db.vo.UserVO  where trim(upper(mailId))=?"),
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.UserVO.findUserByCustomerId", query = "from com.mazdausa.mmg.db.vo.UserVO  where  trim(upper(customerId))=?")

})
public class UserVO implements Serializable {

    private int userId;
    private String mailId, addedBy, updatedBy, customerId;
    private Date addedDate, updatedDate;
    private boolean userDeleted, userAuthenticated;
    private ApplicationInstallationDetailsVO applicationInstallationVO;
    private List<UserVehicleVO> vehicles;

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_ADDEDBY)
    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_ADDEDDATE)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ApplicationInstallationDetailsVO.class)
    @JoinColumn(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_LASTLOGINAPPINSTALLATIONID)
    public ApplicationInstallationDetailsVO getApplicationInstallationVO() {
        return applicationInstallationVO;
    }

    public void setApplicationInstallationVO(ApplicationInstallationDetailsVO applicationInstallationVO) {
        this.applicationInstallationVO = applicationInstallationVO;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_CUSTOMERID)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_USERMAILID)
    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_UPDATEDBY)
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_UPDATEDDATE)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_ISURAUTHENTICATED)
    public boolean isUserAuthenticated() {
        return userAuthenticated;
    }

    public void setUserAuthenticated(boolean userAuthenticated) {
        this.userAuthenticated = userAuthenticated;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_ISUSERDELETED)
    public boolean isUserDeleted() {
        return userDeleted;
    }

    public void setUserDeleted(boolean userDeleted) {
        this.userDeleted = userDeleted;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.TABLE_MMG_APP_USERS_COLUMN_USERID)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToMany(fetch = FetchType.LAZY, targetEntity = UserVehicleVO.class, mappedBy = "userVO")
    public List<UserVehicleVO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<UserVehicleVO> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: mail: ").append(this.getMailId()).append(": MazdaCustomer ID: ").append(this.getCustomerId());
        return sb.toString();
    }
}
