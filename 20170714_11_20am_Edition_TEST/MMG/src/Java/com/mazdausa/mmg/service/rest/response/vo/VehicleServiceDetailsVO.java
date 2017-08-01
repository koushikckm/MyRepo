/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * This VO will contain all the details of vehicle services done in past.
 * @author PankajB
 * @version 1.0
 */
public class VehicleServiceDetailsVO {

    private int count;
    
    private List<VehicleServiceVO> vehicleServiceList;

    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_COUNT)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElementWrapper(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST)
    @XmlElement(name=ApplicationConstants.SERVICE_REST_USER_VEHICLEHISTORY_RESPONSE_PARAMETER_SERVICEHISTORYRESPONSE_SERVICEHISTORYRETURN_VEHSERVICEDETAILS_SERHISTORYLIST_SERVICE)
    public List<VehicleServiceVO> getVehicleServiceList() {
        return vehicleServiceList;
    }

    public void setVehicleServiceList(List<VehicleServiceVO> vehicleServiceList) {
        this.vehicleServiceList = vehicleServiceList;
    }

    




}
