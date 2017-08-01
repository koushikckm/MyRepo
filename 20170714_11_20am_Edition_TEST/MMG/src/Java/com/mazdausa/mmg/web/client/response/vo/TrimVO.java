/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;


public class TrimVO {

    private String trimName,trimCode;
    
	public TrimVO(String trimName, String trimCode) {
		super();
		this.trimName = trimName;
		this.trimCode = trimCode;
	}

	public String getTrimName() {
		return trimName;
	}

	public void setTrimName(String trimName) {
		this.trimName = trimName;
	}

	public String getTrimCode() {
		return trimCode;
	}

	public void setTrimCode(String trimCode) {
		this.trimCode = trimCode;
	}



}
