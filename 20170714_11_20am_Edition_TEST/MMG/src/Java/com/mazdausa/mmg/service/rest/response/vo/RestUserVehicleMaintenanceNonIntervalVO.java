/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object represents the Maintenance Non Interval 
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleMaintenanceNonIntervalVO implements Serializable {

    private List<RestUserVehicleMaintenanceNonIntervaItemlVO> listOfNonIntervalItem;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL_ELEMENT_NONINTERVALITEM)
    public List<RestUserVehicleMaintenanceNonIntervaItemlVO> getListOfNonIntervalItem() {
        return listOfNonIntervalItem;
    }

    public void setListOfNonIntervalItem(List<RestUserVehicleMaintenanceNonIntervaItemlVO> listOfNonIntervalItem) {
        this.listOfNonIntervalItem = listOfNonIntervalItem;
    }

    

    

}
