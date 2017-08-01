/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will contain the details of the User Vehicle Model
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleModelVO {

    private String modelYear,modelCode,trim,drivecode;
    private RestUserVehicleModelEngineVO  engineVO;
    private RestUserVehicleModelTransmissionVO vehicleTransmissionVO;
    private RestUserVehicleModelSeatingVO seatingVO;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_TRANSMISSION)
    public RestUserVehicleModelTransmissionVO getVehicleTransmissionVO() {
        return vehicleTransmissionVO;
    }

    public void setVehicleTransmissionVO(RestUserVehicleModelTransmissionVO vehicleTransmissionVO) {
        this.vehicleTransmissionVO = vehicleTransmissionVO;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_MODELCODE)
    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_MODELYEAR)
    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_TRIM)
    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_DRIVECODE)
    public String getDrivecode() {
        return drivecode;
    }

    public void setDrivecode(String drivecode) {
        this.drivecode = drivecode;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_ENGINE)
    public RestUserVehicleModelEngineVO getEngineVO() {
        return engineVO;
    }

    public void setEngineVO(RestUserVehicleModelEngineVO engineVO) {
        this.engineVO = engineVO;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL_SEATING)
    public RestUserVehicleModelSeatingVO getSeatingVO() {
        return seatingVO;
    }

    public void setSeatingVO(RestUserVehicleModelSeatingVO seatingVO) {
        this.seatingVO = seatingVO;
    }

    

}
