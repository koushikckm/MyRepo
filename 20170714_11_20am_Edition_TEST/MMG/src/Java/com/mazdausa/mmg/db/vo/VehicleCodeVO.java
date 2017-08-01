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
 * This Class will contain the details of a Vehicle's year, model related details.
 * @author pankajkh
 * @version 1.0
 */
@Entity
@Table(name = DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE)
@NamedQueries({
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.VehicleCodeVO.findVehicleByYear", query = "from com.mazdausa.mmg.db.vo.VehicleCodeVO where trim(vehicleYear)=? order by vehicleYear,vehicleModel desc,vehicleName"),
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.VehicleCodeVO.GetVehiclesByYear", query = "from com.mazdausa.mmg.db.vo.VehicleCodeVO order by vehicleYear desc,vehicleModel,vehicleName"),
    @NamedQuery(name = "com.mazdausa.mmg.db.vo.VehicleCodeVO.findVehicleByModel", query = "from com.mazdausa.mmg.db.vo.VehicleCodeVO where trim(vehicleModel)=? order by vehicleYear,vehicleModel,vehicleName")})
public class VehicleCodeVO implements Serializable {

    private int vehicleCodeId;
    private String vehicleYear, vehicleModel, vehicleName;
	private Date vehicleaddedupdateddate;

    @Column(name = DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_ADDED_UPDATED)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getVehicleaddedupdateddate() {
        return vehicleaddedupdateddate;
    }

    public void setVehicleaddedupdateddate(Date vehicleaddedupdateddate) {
        this.vehicleaddedupdateddate = vehicleaddedupdateddate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_IDENT)
    public int getVehicleCodeId() {
        return vehicleCodeId;
    }

    public void setVehicleCodeId(int vehicleCodeId) {
        this.vehicleCodeId = vehicleCodeId;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL)
    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_YEAR)
    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODELNAME)
    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }


}
