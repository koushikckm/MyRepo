package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;

public class FilterItemVO {

	private int id;
	private String name;
	private String iconUrl;
	private int trackingId;
	private List<FilterItemVO> subItems;
	
	//Constructor using fields
	public FilterItemVO(int id, String name, String iconUrl, int trackingId) {
		super();
		this.id = id;
		this.name = name;
		this.iconUrl = iconUrl;
		this.trackingId = trackingId;
	}
	
	//Default constructor
	public FilterItemVO() {
		super();
	}
	
	
	
	public FilterItemVO(int id, String name, String iconUrl) {
		super();
		this.id = id;
		this.name = name;
		this.iconUrl = iconUrl;
	}

	//setters-getters
	
	
	public int getId() {
		return id;
	}
	public List<FilterItemVO> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<FilterItemVO> subItems) {
		this.subItems = subItems;
	}

	public void setId(int id) {
		this.id = id;
	}
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
	public int getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}
	
	
}
