/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

/**
 * This Represent the Vehicle Information VO that will be sent to the client.
 * @author PankajB
 */
public class VehicleInformationVO {

    private String vin,modelYear,carline,trim,engine,transmission,extColor,intColor,description;

    public String getCarline() {
        return carline;
    }

    public void setCarline(String carline) {
        this.carline = carline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getExtColor() {
        return extColor;
    }

    public void setExtColor(String extColor) {
        this.extColor = extColor;
    }

    public String getIntColor() {
        return intColor;
    }

    public void setIntColor(String intColor) {
        this.intColor = intColor;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


}
