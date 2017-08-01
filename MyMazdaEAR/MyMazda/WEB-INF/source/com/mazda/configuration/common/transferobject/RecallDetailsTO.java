package com.mazda.configuration.common.transferobject;

public class RecallDetailsTO {
	
	private String nhtsa_recall_number,mfr_recall_number,recall_date,recall_description;
	private String safety_risk_description,remedy_description,mfr_notes,refresh_date;
	private String startDate,recallSSPNo,recallSSPDesc;
	private int mfr_recall_status;
	public String getNhtsa_recall_number() {
		return nhtsa_recall_number;
	}
	public void setNhtsa_recall_number(String nhtsa_recall_number) {
		this.nhtsa_recall_number = nhtsa_recall_number;
	}
	public String getMfr_recall_number() {
		return mfr_recall_number;
	}
	public void setMfr_recall_number(String mfr_recall_number) {
		this.mfr_recall_number = mfr_recall_number;
	}
	public String getRecall_date() {
		return recall_date;
	}
	public void setRecall_date(String recall_date) {
		this.recall_date = recall_date;
	}
	public String getRecall_description() {
		return recall_description;
	}
	public void setRecall_description(String recall_description) {
		this.recall_description = recall_description;
	}
	public String getSafety_risk_description() {
		return safety_risk_description;
	}
	public void setSafety_risk_description(String safety_risk_description) {
		this.safety_risk_description = safety_risk_description;
	}
	public String getRemedy_description() {
		return remedy_description;
	}
	public void setRemedy_description(String remedy_description) {
		this.remedy_description = remedy_description;
	}
	public String getMfr_notes() {
		return mfr_notes;
	}
	public void setMfr_notes(String mfr_notes) {
		this.mfr_notes = mfr_notes;
	}
	public String getRefresh_date() {
		return refresh_date;
	}
	public void setRefresh_date(String refresh_date) {
		this.refresh_date = refresh_date;
	}
	public int getMfr_recall_status() {
		return mfr_recall_status;
	}
	public void setMfr_recall_status(int mfr_recall_status) {
		this.mfr_recall_status = mfr_recall_status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getRecallSSPNo() {
		return recallSSPNo;
	}
	public void setRecallSSPNo(String recallSSPNo) {
		this.recallSSPNo = recallSSPNo;
	}
	public String getRecallSSPDesc() {
		return recallSSPDesc;
	}
	public void setRecallSSPDesc(String recallSSPDesc) {
		this.recallSSPDesc = recallSSPDesc;
	}
	
}
