/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the value object, which will represent the Vehicle Detail information.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY)
public class RestUserVehicleInformationVO {

    private RestUserVehicleVO vehicleVO;
    private String error;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_VEHICLE)
    public RestUserVehicleVO getVehicleVO() {
        return vehicleVO;
    }

    public void setVehicleVO(RestUserVehicleVO vehicleVO) {
        this.vehicleVO = vehicleVO;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_INFORMATION_VININQUIRY_ELEMENT_ERROR)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }





}
