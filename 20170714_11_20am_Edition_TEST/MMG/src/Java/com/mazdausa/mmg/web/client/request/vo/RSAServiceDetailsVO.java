package com.mazdausa.mmg.web.client.request.vo;

public class RSAServiceDetailsVO {

	private String disablementReason;
	private RSACommentsVO[] comments;
	
	public String getDisablementReason() {
		return disablementReason;
	}
	public void setDisablementReason(String disablementReason) {
		this.disablementReason = disablementReason;
	}
	public RSACommentsVO[] getComments() {
		return comments;
	}
	public void setComments(RSACommentsVO[] comments) {
		this.comments = comments;
	}

	
}
