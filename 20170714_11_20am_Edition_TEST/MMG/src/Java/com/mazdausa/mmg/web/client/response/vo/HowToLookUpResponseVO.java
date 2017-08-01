package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;


/**
 * This Value Object, will be responsible for representing the HowToLookupResponse Value Object.
 * @author anilk
 * @version 1.0
 */
public class HowToLookUpResponseVO {

	private String title;
	private String details;
	private String streamUrl;
	private String downloadUrl;
	private String thumbUrl;

	private List<String> filterItems;
	private List<HowToLookupDataVO> lookupItems;
	private String status="success",error;
	
//Added for 2014 Q2
	private List<FilterItemVO> filterObjects;
	private String imageUrl="default";


	public List<FilterItemVO> getFilterObjects() {
		return filterObjects;
	}
	public void setFilterObjects(List<FilterItemVO> filterObjects) {
		this.filterObjects = filterObjects;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	/*
	 * Defautl Constructor
	 */
	public HowToLookUpResponseVO() {
		super();
	}
	
	
	/**
	 * Parameterised constructor
	 * @param title
	 * @param details
	 * @param streamUrl
	 * @param downloadUrl
	 */
	public HowToLookUpResponseVO(String title, String details,
			String streamUrl, String downloadUrl) {
		super();
		this.title = title;
		this.details = details;
		this.streamUrl = streamUrl;
		this.downloadUrl = downloadUrl;
	}

	
	@Override
	public String toString() {
		return "HowToLookUpResponseVO [title=" + title + ", details=" + details
				+ ", streamUrl=" + streamUrl + ", downloadUrl=" + downloadUrl
				+ "]";
	}

	//Setter-getters


	public List<String> getFilterItems() {
		return filterItems;
	}
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public void setFilterItems(List<String> filterItems) {
		this.filterItems = filterItems;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStreamUrl() {
		return streamUrl;
	}

	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public List<HowToLookupDataVO> getLookupItems() {
		return lookupItems;
	}

	public void setLookupItems(List<HowToLookupDataVO> lookupItems) {
		this.lookupItems = lookupItems;
	}
	
	
}
