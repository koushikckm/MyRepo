/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO will contain detail information about Vehicle
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET)
public class RestUserVehicleDetailInformationVO {

    private RestUserVehicleExtendedInformationVO vehicle;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE)
    public RestUserVehicleExtendedInformationVO getVehicle() {
        return vehicle;
    }

    public void setVehicle(RestUserVehicleExtendedInformationVO vehicle) {
        this.vehicle = vehicle;
    }
}
