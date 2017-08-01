/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.iface;

import com.mazdausa.mmg.service.rest.request.vo.RestUserGetVehiclesRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleServiceHistoryRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.service.rest.request.vo.RestUserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserGetVehiclesResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleMaintenanceResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleServiceHistoryResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleUpdateOwnershipResponseVO;

/**
 * This is the interface, that will contain all the Tasks related to the Service Upload related actions.
 * @author pankajb
 * @version 1.0
 */
public interface RestUserVehicleTaskIFace {

    /**
     * This Function is responsible for fetching the Vehicle Schedule
     * @param vehicleNumber
     * @param drivingHabit
     * @return
     */
    public RestUserVehicleMaintenanceResponseVO getVehicleSchedule(String vehicleNumber,String drivingHabit);

    /**
     * This Function is responsible for fetching the Vehicle Maintenance schedule. 
     * @param model
     * @param year
     * @param drivingHabit
     * @return
     */
    public RestUserVehicleMaintenanceResponseVO getVehicleSchedule(String model,String year,String drivingHabit);

    /**
     * This Function will get service history details of the vehicle from Fusion service.
     * @param userDetailsUpdateRequestVO
     * @return
     */   
    public RestUserVehicleServiceHistoryResponseVO getUserVehicleHistoryDetails(RestUserVehicleServiceHistoryRequestVO userVehicleServiceHistoryRequest);
    
    /**
     * This Function will return the list of User Vehicles from CustomerID.
     * @param getVehiclesRequestVO
     * @return
     */
    public RestUserGetVehiclesResponseVO getUserVehicles(RestUserGetVehiclesRequestVO getVehiclesRequestVO);

    /**
     * This Function will be responsible for updating the Servicing Dealer for the Vehicle.
     * @param vehicleServicingDealerRequestVO
     * @return
     */
    public RestUserVehicleSetServicingDealerResponseVO setVehicleServicingDealer(RestUserVehicleSetServicingDealerRequestVO vehicleServicingDealerRequestVO);
    
    /**
     * This Function will update the ownership of the Vehicle.
     * @param vehicleUpdateOwnershipRequestVO
     * @return
     */
    public RestUserVehicleUpdateOwnershipResponseVO updateVehicleOwnership(RestUserVehicleUpdateOwnershipRequestVO vehicleUpdateOwnershipRequestVO);

}
