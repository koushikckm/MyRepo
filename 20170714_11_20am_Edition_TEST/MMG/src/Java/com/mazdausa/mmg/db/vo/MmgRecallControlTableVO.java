package com.mazdausa.mmg.db.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mazdausa.mmg.db.constants.DatabaseConstants;


@Entity
@Table(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE)
public class MmgRecallControlTableVO implements Serializable{
	

	//private int parameter_id;
	private String parameter;
    private String value;
    private String addedBy;
    private String updatedBy;
	private Date addedDate;
    private Date updateDate;
    
    
/*    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_ADDEDBY="addedby";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_UPDATEBY="updatedby";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_ADDEDDATE="addeddate";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_UPDATEDATE="updatedate";*/
    @Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_ADDEDBY)
    public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	@Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_UPDATEBY)
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_ADDEDDATE)
    @Temporal(value = TemporalType.TIMESTAMP)
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	@Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_UPDATEDATE)
    @Temporal(value = TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

    
    
   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_PARAMETER_ID)
	public int getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}*/
    
	 @Id
	@Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_PARAMETER)
    public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	@Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_VALUE)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/*@Column(name=DatabaseConstants.TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_LASTUPDATEDTIME)
    @Temporal(value = TemporalType.TIMESTAMP)
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}*/

	   @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("parameter: ").append(this.getParameter()).append(": value: ").append(this.getValue());
	        return sb.toString();
	    }
 
}
