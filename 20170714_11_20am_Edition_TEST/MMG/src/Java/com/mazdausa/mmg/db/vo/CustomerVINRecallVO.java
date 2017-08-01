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
 * This class will represent an VIN Recall Value Object.
 * @author PankajB
 * @version 1.0
 */
@Entity
@Table(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL)
@NamedQueries({@NamedQuery(name="com.mazdausa.mmg.db.vo.CustomerVINRecallVO.findCustomerVINRecallByRecallNo",query="from com.mazdausa.mmg.db.vo.CustomerVINRecallVO where trim(upper(vinrecallSSPNo))=? "),
              @NamedQuery(name="com.mazdausa.mmg.db.vo.CustomerVINRecallVO.findCustomerVINRecallByRecallNoVINStartDate",query="from com.mazdausa.mmg.db.vo.CustomerVINRecallVO where trim(upper(vin))=? and trim(upper(vinrecallSSPNo))=? and vinrecallStartDate=?" )})
public class CustomerVINRecallVO implements Serializable {

    private int vinRecall_ident, senttimes;
    private boolean isalertactivated;
    private String vin, vInrecallSSPDesc, vinrecallSSPNo;
    private Date vinrecallStartDate, addeddate;

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_ADDED_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(Date addeddate) {
        this.addeddate = addeddate;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_IS_ALERT_ACTIVATED)
    public boolean isIsalertactivated() {
        return isalertactivated;
    }

    public void setIsalertactivated(boolean isalertactivated) {
        this.isalertactivated = isalertactivated;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_SENTTIMES)
    public int getSenttimes() {
        return senttimes;
    }

    public void setSenttimes(int senttimes) {
        this.senttimes = senttimes;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VINRECALL_SSPDESC)
    public String getvInrecallSSPDesc() {
        return vInrecallSSPDesc;
    }

    public void setvInrecallSSPDesc(String vInrecallSSPDesc) {
        this.vInrecallSSPDesc = vInrecallSSPDesc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VINRECALL_ID)
    public int getVinRecall_ident() {
        return vinRecall_ident;
    }

    public void setVinRecall_ident(int vinRecall_ident) {
        this.vinRecall_ident = vinRecall_ident;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VINRECALL_SSPNO)
    public String getVinrecallSSPNo() {
        return vinrecallSSPNo;
    }

    public void setVinrecallSSPNo(String vinrecallSSPNo) {
        this.vinrecallSSPNo = vinrecallSSPNo;
    }

    @Column(name = DatabaseConstants.TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VIN_RECALLSTART_DATE)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getVinrecallStartDate() {
        return vinrecallStartDate;
    }

    public void setVinrecallStartDate(Date vinrecallStartDate) {
        this.vinrecallStartDate = vinrecallStartDate;
    }
}
