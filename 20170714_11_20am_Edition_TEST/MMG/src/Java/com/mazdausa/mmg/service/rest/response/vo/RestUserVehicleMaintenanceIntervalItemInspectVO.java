/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO Represents the Inspect Element of Maintenance Interval Item.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleMaintenanceIntervalItemInspectVO {

    private String itemName,itemFootNote;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_INSPECTLIST_INSPECTELEMENT_ITEMNAME)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_INSPECTLIST_INSPECTELEMENT_ITEMFOOTNAME)
    public String getItemFootNote() {
        return itemFootNote;
    }

    public void setItemFootNote(String itemFootNote) {
        this.itemFootNote = itemFootNote;
    }


    
}
