package com.mazdausa.mmg.web.client.response.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO Represent the User Window Sticker Response and will pass the value from Service layer to Controller Layer
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserWindowStickerResponseVO {
	
	private String imageUrl;
	private String status;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
