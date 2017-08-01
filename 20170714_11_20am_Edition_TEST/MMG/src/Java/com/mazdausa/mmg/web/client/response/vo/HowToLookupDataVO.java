package com.mazdausa.mmg.web.client.response.vo;

import java.util.List;


/**
 * This Value Object, will be responsible for representing the HowToLookup Value Object.
 * @author anilk
 * @version 1.0
 */
public class HowToLookupDataVO {

	private String contentId;
	private String description;
	private String thumbUrl;
	private String title;
	private String contentType;
	private String trim;
	private List<String> filters;
	private List<String> trimNames;
	
	//Added in 2014 Q2
	private List<Integer> filterIds;
	
	
	public List<Integer> getFilterIds() {
		return filterIds;
	}

	public void setFilterIds(List<Integer> filterIds) {
		this.filterIds = filterIds;
	}

	
	public String getTrim() {
		return trim;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}
	
	
	public List<String> getTrimNames() {
		return trimNames;
	}

	public void setTrimNames(List<String> trimNames) {
		this.trimNames = trimNames;
	}

	public List<String> getFilters() {
		return filters;
	}

	public void setFilters(List<String> filters) {
		this.filters = filters;
	}

	public HowToLookupDataVO() {
		super();
	}

	public HowToLookupDataVO(String contentId, String description,
			String thumbUrl, String title, String contentType) {
		super();
		this.contentId = contentId;
		this.description = description;
		this.thumbUrl = thumbUrl;
		this.title = title;
		this.contentType = contentType;
	}


	
	@Override
	public String toString() {
		return "HowToLookupDataVO [contentId=" + contentId + ", description="
				+ description + ", thumbUrl=" + thumbUrl + ", title=" + title
				+ ", contentType=" + contentType + ", trim=" + trim
				+ ", filters=" + filters + ", trimNames=" + trimNames
				+ ", filterIds=" + filterIds + "]";
	}

	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	
	
}
