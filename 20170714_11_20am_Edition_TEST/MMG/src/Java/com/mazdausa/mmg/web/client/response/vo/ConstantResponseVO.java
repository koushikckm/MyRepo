/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

/**
 * This will be responsible for representing the Value Objects,  that will be sent in response to constant REQUEST.
 * @author PankajKh
 * @version 1.0
 */
public class ConstantResponseVO {

    private String constantvname, constantvalue, modulename;

    public String getConstantvalue() {
        return constantvalue;
    }

    public void setConstantvalue(String constantvalue) {
        this.constantvalue = constantvalue;
    }

    public String getConstantvname() {
        return constantvname;
    }

    public void setConstantvname(String constantvname) {
        this.constantvname = constantvname;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }

    
}
