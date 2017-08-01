/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleCarLineVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleExtColorVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleIntColorVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleModelVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleOptionGroupVO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This value Object represent the extended information about the Vehicle and will be passed from Service Layer to Controller Layer
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class VehicleExtendedInformationVO {

    private String vin, status, description;
    private RestUserVehicleCarLineVO carline;
    private RestUserVehicleModelVO model;
    private RestUserVehicleOptionGroupVO optiongroup;
    private RestUserVehicleExtColorVO extcolor;
    private RestUserVehicleIntColorVO intcolorVO;

    @XmlElement
    public RestUserVehicleCarLineVO getCarline() {
        return carline;
    }

    public void setCarline(RestUserVehicleCarLineVO carline) {
        this.carline = carline;
    }

    @XmlElement
    public RestUserVehicleExtColorVO getExtcolor() {
        return extcolor;
    }

    public void setExtcolor(RestUserVehicleExtColorVO extcolor) {
        this.extcolor = extcolor;
    }

    @XmlElement
    public RestUserVehicleIntColorVO getIntcolorVO() {
        return intcolorVO;
    }

    public void setIntcolorVO(RestUserVehicleIntColorVO intcolorVO) {
        this.intcolorVO = intcolorVO;
    }

    @XmlElement
    public RestUserVehicleModelVO getModel() {
        return model;
    }

    public void setModel(RestUserVehicleModelVO model) {
        this.model = model;
    }

    @XmlElement
    public RestUserVehicleOptionGroupVO getOptiongroup() {
        return optiongroup;
    }

    public void setOptiongroup(RestUserVehicleOptionGroupVO optiongroup) {
        this.optiongroup = optiongroup;
    }

    @XmlElement
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
