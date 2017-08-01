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
 * This is an DB Value object that is going to represent the table UserVehicle in the database.
 * @author PankajB
 * @version 1.0
 */
@Entity
@Table(name=DatabaseConstants.TABLE_MMG_APP_USERS_VEHICLES)
@NamedQueries({@NamedQuery(name="com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByVIN",query="from com.mazdausa.mmg.db.vo.UserVehicleVO  where trim(upper(vehicleVIN))=? "),
               @NamedQuery(name="com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByCustomerId",query="from com.mazdausa.mmg.db.vo.UserVehicleVO  where  trim(upper(userVO.customerId))=? "),
               @NamedQuery(name="com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByMailId",query="from com.mazdausa.mmg.db.vo.UserVehicleVO  where  trim(upper(userVO.mailId))=? "),
               @NamedQuery(name="com.mazdausa.mmg.db.vo.UserVehicleVO.findUserVehicleByCustomerIdAndVIN",query="from com.mazdausa.mmg.db.vo.UserVehicleVO  where  trim(upper(userVO.customerId))=? and trim(upper(vehicleVIN))=?")})
public class UserVehicleVO implements Serializable{

                   private int userVehicleId;
                   private Date addedDate;
                   private String vehicleVIN;
                   private UserVO userVO;

                   //01-01-2015 : Added New column to hold vehicle title
                   private String vehicleTitle;

                   
    @Column(name=DatabaseConstants.TABLE_MMG_APP_USERS_VEHICLES_COLUMN_ADDEDDATE)
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @ManyToOne(fetch=FetchType.EAGER,targetEntity=UserVO.class)
    @JoinColumn(name=DatabaseConstants.TABLE_MMG_APP_USERS_VEHICLES_COLUMN_USER)
    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=DatabaseConstants.TABLE_MMG_APP_USERS_VEHICLES_COLUMN_USERVEHICLEID)
    public int getUserVehicleId() {
        return userVehicleId;
    }

    public void setUserVehicleId(int userVehicleId) {
        this.userVehicleId = userVehicleId;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_USERS_VEHICLES_COLUMN_CUSTVIN)
    public String getVehicleVIN() {
        return vehicleVIN;
    }

    public void setVehicleVIN(String vehicleVIN) {
        this.vehicleVIN = vehicleVIN;
    }
    
    
    @Column(name=DatabaseConstants.TABLE_MMG_APP_USERS_VEHICLES_COLUMN_VTITLE)
    public String getVehicleTitle() {
		return vehicleTitle;
	}

	public void setVehicleTitle(String vehicleTitle) {
		this.vehicleTitle = vehicleTitle;
	}
	

	@Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("User Vehicle: VIN: ").append(this.getVehicleVIN()).append("  USER CUST ID: ").append(this.getUserVO().getCustomerId());
        return sb.toString();
    }

                   

}
