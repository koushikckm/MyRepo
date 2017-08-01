package com.mazdausa.mmg.db.dao.impl;

public class MMgRecallJobExecutionTableVO {

	private int id;
	private String jobEnableStatus;
	private String jobRunningStatus;
	private int chunkSize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getJobEnableStatus() {
		return jobEnableStatus;
	}
	public void setJobEnableStatus(String jobEnableStatus) {
		this.jobEnableStatus = jobEnableStatus;
	}
	public String getJobRunningStatus() {
		return jobRunningStatus;
	}
	public void setJobRunningStatus(String jobRunningStatus) {
		this.jobRunningStatus = jobRunningStatus;
	}
	public int getChunkSize() {
		return chunkSize;
	}
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	
	
}
