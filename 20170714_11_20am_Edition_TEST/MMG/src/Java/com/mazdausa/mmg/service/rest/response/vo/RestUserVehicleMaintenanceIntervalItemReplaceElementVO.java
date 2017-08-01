/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object represent the Vehicle Maintenance Interval Item Replace Element
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleMaintenanceIntervalItemReplaceElementVO {

    private String itemName;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_REPLACELIST_REPLACELEMENT_ITEMNAME)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    

}
