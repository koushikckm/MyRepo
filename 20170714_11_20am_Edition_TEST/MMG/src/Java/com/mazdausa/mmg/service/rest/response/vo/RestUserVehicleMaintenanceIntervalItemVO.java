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
 * This VO Represent the Vehicle Maintenance Interval List
 * @author PankajB
 * @version 1.0s
 */
public class RestUserVehicleMaintenanceIntervalItemVO {

    private String interval;
    private List<RestUserVehicleMaintenanceIntervalItemReplaceElementVO> listOfReplaceElement;
    private List<RestUserVehicleMaintenanceIntervalItemLubricateVO> listOfLubricateElement;
    private List<RestUserVehicleMaintenanceIntervalItemInspectVO> listOfInspectElement;
    private List<RestUserVehicleMaintenanceIntervalItemTightenVO> listOfTightenElement;
    private List<RestUserVehicleMaintenanceIntervalItemCleanElementVO> listOfCleanElement;

    private List<String> listOfMaintenanceNotes;

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL)
    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_REPLACELIST)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_REPLACELIST_REPLACELEMENT)
    public List<RestUserVehicleMaintenanceIntervalItemReplaceElementVO> getListOfReplaceElement() {
        return listOfReplaceElement;
    }

    public void setListOfReplaceElement(List<RestUserVehicleMaintenanceIntervalItemReplaceElementVO> listOfReplaceElement) {
        this.listOfReplaceElement = listOfReplaceElement;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_LUBRICATELISTS)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_LUBRICATELISTS_LUBRICATEELEMENT)
    public List<RestUserVehicleMaintenanceIntervalItemLubricateVO> getListOfLubricateElement() {
        return listOfLubricateElement;
    }

    public void setListOfLubricateElement(List<RestUserVehicleMaintenanceIntervalItemLubricateVO> listOfLubricateElement) {
        this.listOfLubricateElement = listOfLubricateElement;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_INSPECTLIST)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_INSPECTLIST_INSPECTELEMENT)
    public List<RestUserVehicleMaintenanceIntervalItemInspectVO> getListOfInspectElement() {
        return listOfInspectElement;
    }

    public void setListOfInspectElement(List<RestUserVehicleMaintenanceIntervalItemInspectVO> listOfInspectElement) {
        this.listOfInspectElement = listOfInspectElement;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_MAINTENANCENOTES)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_MAINTENANCENOTES_MAINTENANCENOTE)
    public List<String> getListOfMaintenanceNotes() {
        return listOfMaintenanceNotes;
    }

    public void setListOfMaintenanceNotes(List<String> listOfMaintenanceNotes) {
        this.listOfMaintenanceNotes = listOfMaintenanceNotes;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_TIGHTENLIST)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_TIGHTENLIST_TIGHTENELEMENT)
    public List<RestUserVehicleMaintenanceIntervalItemTightenVO> getListOfTightenElement() {
        return listOfTightenElement;
    }

    public void setListOfTightenElement(List<RestUserVehicleMaintenanceIntervalItemTightenVO> listOfTightenElement) {
        this.listOfTightenElement = listOfTightenElement;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_CLEANLISTS)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE_IILISTS_ITLIST_INTERVAL_CLEANLISTS_CLEANELEMENT)
    public List<RestUserVehicleMaintenanceIntervalItemCleanElementVO> getListOfCleanElement() {
        return listOfCleanElement;
    }

    public void setListOfCleanElement(List<RestUserVehicleMaintenanceIntervalItemCleanElementVO> listOfCleanElement) {
        this.listOfCleanElement = listOfCleanElement;
    }

      

}
