package com.mazda.configuration.common.transferobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HowToVehicalTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String iconUrl;
	private String mdlCode;
	private String modelName;
	private String trim;
	private String seatName;
	private String vin;
	private String title;
	private int contentId;
	private String contantType;
	private List<String> trims;
	private int id;
	private int trackingId;
	private int year;
	private int index;
	private String squery;
	private String streemURL;
	private String streemImg;
	private List<String> ssgText;
	private String textContent;
	private List<HowToVehicalTO> coordsList = new ArrayList<HowToVehicalTO>();
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	
	private List<HowToVehicalTO> subItemList = new ArrayList<HowToVehicalTO>();
	private List<HowToVehicalTO> lookUplist = new ArrayList<HowToVehicalTO>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}
	public List<HowToVehicalTO> getSubItemList() {
		return subItemList;
	}
	public void setSubItemList(List<HowToVehicalTO> subItemList) {
		this.subItemList = subItemList;
	}
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public List<String> getTrims() {
		return trims;
	}
	public void setTrims(List<String> trims) {
		this.trims = trims;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
	public String getContantType() {
		return contantType;
	}
	public void setContantType(String contantType) {
		this.contantType = contantType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMdlCode() {
		return mdlCode;
	}
	public void setMdlCode(String mdlCode) {
		this.mdlCode = mdlCode;
	}
	public String getTrim() {
		return trim;
	}
	public void setTrim(String trim) {
		this.trim = trim;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public List<HowToVehicalTO> getLookUplist() {
		return lookUplist;
	}
	public void setLookUplist(List<HowToVehicalTO> lookUplist) {
		this.lookUplist = lookUplist;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the squery
	 */
	public String getSquery() {
		return squery;
	}
	/**
	 * @param squery the squery to set
	 */
	public void setSquery(String squery) {
		this.squery = squery;
	}
	/**
	 * @return the streemURL
	 */
	public String getStreemURL() {
		return streemURL;
	}
	/**
	 * @param streemURL the streemURL to set
	 */
	public void setStreemURL(String streemURL) {
		this.streemURL = streemURL;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * @return the streemImg
	 */
	public String getStreemImg() {
		return streemImg;
	}
	/**
	 * @param streemImg the streemImg to set
	 */
	public void setStreemImg(String streemImg) {
		this.streemImg = streemImg;
	}
	/**
	 * @return the ssgText
	 */
	public List<String> getSsgText() {
		return ssgText;
	}
	/**
	 * @param ssgText the ssgText to set
	 */
	public void setSsgText(List<String> ssgText) {
		this.ssgText = ssgText;
	}
	/**
	 * @return the x1
	 */
	public float getX1() {
		return x1;
	}
	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(float x1) {
		this.x1 = x1;
	}
	/**
	 * @return the y1
	 */
	public float getY1() {
		return y1;
	}
	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(float y1) {
		this.y1 = y1;
	}
	/**
	 * @return the x2
	 */
	public float getX2() {
		return x2;
	}
	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(float x2) {
		this.x2 = x2;
	}
	/**
	 * @return the y2
	 */
	public float getY2() {
		return y2;
	}
	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(float y2) {
		this.y2 = y2;
	}
	/**
	 * @return the coordsList
	 */
	public List<HowToVehicalTO> getCoordsList() {
		return coordsList;
	}
	/**
	 * @param coordsList the coordsList to set
	 */
	public void setCoordsList(List<HowToVehicalTO> coordsList) {
		this.coordsList = coordsList;
	}
	/**
	 * @return the textContent
	 */
	public String getTextContent() {
		return textContent;
	}
	/**
	 * @param textContent the textContent to set
	 */
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public boolean isSame(int contentId2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
 