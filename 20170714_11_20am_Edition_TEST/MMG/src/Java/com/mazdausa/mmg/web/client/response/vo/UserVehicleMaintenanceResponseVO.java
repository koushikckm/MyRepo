/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceNonIntervaItemlVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceScheduleVO;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This VO Represent the User Vehicle Maintenance Response and will pass the value from Service layer to Controller Layer
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class UserVehicleMaintenanceResponseVO {

    private String status,description,drivinghabit;

    private RestUserVehicleMaintenanceScheduleVO maintenanceschedule;
    private List<RestUserVehicleMaintenanceNonIntervaItemlVO> maintenancenoninterval;

    public List<RestUserVehicleMaintenanceNonIntervaItemlVO> getMaintenancenoninterval() {
        return maintenancenoninterval;
    }

    public void setMaintenancenoninterval(List<RestUserVehicleMaintenanceNonIntervaItemlVO> maintenancenoninterval) {
        this.maintenancenoninterval = maintenancenoninterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDrivinghabit() {
        return drivinghabit;
    }

    public void setDrivinghabit(String drivinghabit) {
        this.drivinghabit = drivinghabit;
    }

    

    public RestUserVehicleMaintenanceScheduleVO getMaintenanceschedule() {
        return maintenanceschedule;
    }

    public void setMaintenanceschedule(RestUserVehicleMaintenanceScheduleVO maintenanceschedule) {
        this.maintenanceschedule = maintenanceschedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    

}
