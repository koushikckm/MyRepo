package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;

public class VehiclesRecall {

	
		private String custId;
		private String recallNo;
		private String vin;
		private String recallStartDate;
		private String launchedDate;
		private String crlnNm;
		private String mdlYrDt;
		private String rcllDescTx;
		private String rclStatus;
		private String rclType;
		private String dealerCode;
		private String dealerName;
		
		@XmlElement(name="recallNo")
		public String getRecallNo() {
			return recallNo;
		}
		public void setRecallNo(String recallNo) {
			this.recallNo = recallNo;
		}
		@XmlElement(name="vin")
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
		@XmlElement(name="custId")
		public String getCustId() {
			return custId;
		}
		public void setCustId(String custId) {
			this.custId = custId;
		}
		@XmlElement(name="recallStartDate")
		public String getRecallStartDate() {
			return recallStartDate;
		}
		public void setRecallStartDate(String recallStartDate) {
			this.recallStartDate = recallStartDate;
		}
		@XmlElement(name="launchedDate")
		public String getLaunchedDate() {
			return launchedDate;
		}
		public void setLaunchedDate(String launchedDate) {
			this.launchedDate = launchedDate;
		}
		@XmlElement(name="crlnNm")
		public String getCrlnNm() {
			return crlnNm;
		}
		public void setCrlnNm(String crlnNm) {
			this.crlnNm = crlnNm;
		}
		@XmlElement(name="mdlYrDt")
		public String getMdlYrDt() {
			return mdlYrDt;
		}
		public void setMdlYrDt(String mdlYrDt) {
			this.mdlYrDt = mdlYrDt;
		}
		@XmlElement(name="rcllDescTx")
		public String getRcllDescTx() {
			return rcllDescTx;
		}
		public void setRcllDescTx(String rcllDescTx) {
			this.rcllDescTx = rcllDescTx;
		}
		@XmlElement(name="rclStatus")
		public String getRclStatus() {
			return rclStatus;
		}
		public void setRclStatus(String rclStatus) {
			this.rclStatus = rclStatus;
		}
		@XmlElement(name="rclType")
		public String getRclType() {
			return rclType;
		}
		public void setRclType(String rclType) {
			this.rclType = rclType;
		}
		@XmlElement(name="dealerCode")
		public String getDealerCode() {
			return dealerCode;
		}
		public void setDealerCode(String dealerCode) {
			this.dealerCode = dealerCode;
		}
		@XmlElement(name="dealerName")
		public String getDealerName() {
			return dealerName;
		}
		public void setDealerName(String dealerName) {
			this.dealerName = dealerName;
		}
		
		
}
