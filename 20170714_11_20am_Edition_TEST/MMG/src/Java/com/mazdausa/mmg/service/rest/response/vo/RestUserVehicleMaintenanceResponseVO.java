/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO Represents the Vehicle Maintenance Response
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE)
public class RestUserVehicleMaintenanceResponseVO implements Serializable{

    private String status, drivingHabit;
    private RestUserVehicleMaintenanceScheduleVO maintenanceSchedule;
    private RestUserVehicleMaintenanceNonIntervalVO nonInterval;
    private List<RestUserVehicleMaintenanceNonIntervaItemlVO> maintenanceNonInterval;
    private RestUserVehicleMaintenanceErrorVO error;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_DRIVINGHABIT)
    public String getDrivingHabit() {
        return drivingHabit;
    }

    public void setDrivingHabit(String drivingHabit) {
        this.drivingHabit = drivingHabit;
    }

    @XmlAttribute(name = ApplicationConstants.XML_VEHICLE_MAINTENANCE_ATTRIBUTE_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCESCHEDULE)
    public RestUserVehicleMaintenanceScheduleVO getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public void setMaintenanceSchedule(RestUserVehicleMaintenanceScheduleVO maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    /*@XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL)
    public RestUserVehicleMaintenanceNonIntervalVO getNonInterval() {
        return nonInterval;
    }

    public void setNonIntervalItem(RestUserVehicleMaintenanceNonIntervalVO nonInterval) {
        this.nonInterval = nonInterval;
    }*/

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ERROR)
    public RestUserVehicleMaintenanceErrorVO getError() {
        return error;
    }

    public void setError(RestUserVehicleMaintenanceErrorVO error) {
        this.error = error;
    }

    @XmlElementWrapper(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL)
    @XmlElement(name=ApplicationConstants.XML_VEHICLE_MAINTENANCE_ELEMENT_MAINTENANCENONINTERVAL_ELEMENT_NONINTERVALITEM)
    public List<RestUserVehicleMaintenanceNonIntervaItemlVO> getMaintenanceNonInterval() {
        return maintenanceNonInterval;
    }

    public void setMaintenanceNonInterval(List<RestUserVehicleMaintenanceNonIntervaItemlVO> maintenanceNonInterval) {
        this.maintenanceNonInterval = maintenanceNonInterval;
    }




}
