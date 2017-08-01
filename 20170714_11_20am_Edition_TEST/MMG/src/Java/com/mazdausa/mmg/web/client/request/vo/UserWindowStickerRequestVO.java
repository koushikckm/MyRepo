package com.mazdausa.mmg.web.client.request.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserWindowStickerRequestVO {

	private String mdlYear;
	private String carlineCode;
	private String extColorCode;
	private String mdlCode;
	private String width;
	public String getMdlYear() {
		return mdlYear;
	}
	public void setMdlYear(String mdlYear) {
		this.mdlYear = mdlYear;
	}
	public String getCarlineCode() {
		return carlineCode;
	}
	public void setCarlineCode(String carlineCode) {
		this.carlineCode = carlineCode;
	}
	public String getExtColorCode() {
		return extColorCode;
	}
	public void setExtColorCode(String extColorCode) {
		this.extColorCode = extColorCode;
	}
	public String getMdlCode() {
		return mdlCode;
	}
	public void setMdlCode(String mdlCode) {
		this.mdlCode = mdlCode;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
}
