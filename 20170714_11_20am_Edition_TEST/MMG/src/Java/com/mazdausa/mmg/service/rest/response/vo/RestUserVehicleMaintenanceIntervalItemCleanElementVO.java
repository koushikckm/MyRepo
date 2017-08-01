/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object will represent the Item Clean Element VO.
 * @author PankajB
 */
public class RestUserVehicleMaintenanceIntervalItemCleanElementVO {


    private String itemName;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_CLEANLISTS_CLEANELEMENT_ITEMANE)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
