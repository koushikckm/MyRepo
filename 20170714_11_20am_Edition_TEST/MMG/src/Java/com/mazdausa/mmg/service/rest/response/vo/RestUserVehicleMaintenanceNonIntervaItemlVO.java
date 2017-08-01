/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object represent the Non Interval Item of Maintenance Non Interval.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleMaintenanceNonIntervaItemlVO implements Serializable {
    
    private String itemDescription,nonIntervalDescription,itemFootNote;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL_ELEMENT_NONINTERVALITEM_ITEMDESCRIPTION)
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL_ELEMENT_NONINTERVALITEM_ITEMFOOTNOTE)
    public String getItemFootNote() {
        return itemFootNote;
    }

    public void setItemFootNote(String itemFootNote) {
        this.itemFootNote = itemFootNote;
    }


    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL_ELEMENT_NONINTERVALITEM_NONINTERVALDESCRIPTION)
    public String getNonIntervalDescription() {
        return nonIntervalDescription;
    }

    public void setNonIntervalDescription(String nonIntervalDescription) {
        this.nonIntervalDescription = nonIntervalDescription;
    }

    
}
