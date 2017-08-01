/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This Will contain all the attributes of an Vehicle.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleVO {

    private String vin,modelYear,carline,trim,engine,transmission,extColor,intColor,description;

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_CARLINE)
    public String getCarline() {
        return carline;
    }

    public void setCarline(String carline) {
        this.carline = carline;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_ENGINE)
    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_EXTCOLOR)
    public String getExtColor() {
        return extColor;
    }

    public void setExtColor(String extColor) {
        this.extColor = extColor;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_INTCOLOR)
    public String getIntColor() {
        return intColor;
    }

    public void setIntColor(String intColor) {
        this.intColor = intColor;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_MODELYEAR)
    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_TRANSMISSION)
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_TRIM)
    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    @XmlAttribute(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE_ATTRIBUTE_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }


}
