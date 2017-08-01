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
 * This Represent the Maintenance Schedule of the Maintenance ResponseVO.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleMaintenanceScheduleVO {

    private List<RestUserVehicleMaintenanceIntervalItemVO> listOfIntervalItem;
    private List<String> maintenanceNotes;

    @XmlElementWrapper(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS)
    @XmlElement(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST)
    public List<RestUserVehicleMaintenanceIntervalItemVO> getListOfIntervalItem() {
        return listOfIntervalItem;
    }

    public void setListOfIntervalItem(List<RestUserVehicleMaintenanceIntervalItemVO> listOfIntervalItem) {
        this.listOfIntervalItem = listOfIntervalItem;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_MAINTENANCENOTES)
    @XmlElement(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_MAINTENANCENOTES_MAINTENANCENOTE)
    public List<String> getMaintenanceNotes() {
        return maintenanceNotes;
    }

    public void setMaintenanceNotes(List<String> maintenanceNotes) {
        this.maintenanceNotes = maintenanceNotes;
    }




}
