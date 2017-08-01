/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Represent the details of an Individual vehicle in very extended form.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleExtendedInformationVO {

    private String vin, error;
    private RestUserVehicleCarLineVO vehicleCarLineVO;
    private RestUserVehicleModelVO vehicleModelVO;
    private RestUserVehicleOptionGroupVO optionGroupVO;
    private RestUserVehicleExtColorVO extColorVO;
    private RestUserVehicleIntColorVO intColorVO;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_VIN)
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_CARLINE)
    public RestUserVehicleCarLineVO getVehicleCarLineVO() {
        return vehicleCarLineVO;
    }

    public void setVehicleCarLineVO(RestUserVehicleCarLineVO vehicleCarLineVO) {
        this.vehicleCarLineVO = vehicleCarLineVO;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_MODEL)
    public RestUserVehicleModelVO getVehicleModelVO() {
        return vehicleModelVO;
    }

    public void setVehicleModelVO(RestUserVehicleModelVO vehicleModelVO) {
        this.vehicleModelVO = vehicleModelVO;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP)
    public RestUserVehicleOptionGroupVO getOptionGroupVO() {
        return optionGroupVO;
    }

    public void setOptionGroupVO(RestUserVehicleOptionGroupVO optionGroupVO) {
        this.optionGroupVO = optionGroupVO;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_EXTCOLOR)
    public RestUserVehicleExtColorVO getExtColorVO() {
        return extColorVO;
    }

    public void setExtColorVO(RestUserVehicleExtColorVO extColorVO) {
        this.extColorVO = extColorVO;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_INTCOLOR)
    public RestUserVehicleIntColorVO getIntColorVO() {
        return intColorVO;
    }

    public void setIntColorVO(RestUserVehicleIntColorVO intColorVO) {
        this.intColorVO = intColorVO;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
