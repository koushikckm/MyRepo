/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * This VO will contain the Vehicle Details of GetVehicles Request
 * @author PankajB
 * @version 1.0
 */
public class GetVehiclesDetailVO {

    private int count;
    private List<GetVehiclesDetailsVehicleVO> vehicleList;

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_COUNT)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST)
    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE)
    public List<GetVehiclesDetailsVehicleVO> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<GetVehiclesDetailsVehicleVO> vehicleList) {
        this.vehicleList = vehicleList;
    }

}
