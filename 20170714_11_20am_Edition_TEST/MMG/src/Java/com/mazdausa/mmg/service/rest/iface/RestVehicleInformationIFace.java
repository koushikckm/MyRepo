/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.iface;

import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleDetailInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserVehicleInformationVO;
import com.mazdausa.mmg.service.rest.response.vo.RestVehicleAlertResponseVO;

/**
 * This Rest based interface, will contain all the funtion required to get Vehicle Information from the available web services
 * @author PankajB
 * @version 1.0
 */
public interface RestVehicleInformationIFace {

    /**
     * This Function will return the Vehicle Information given the VIN
     * @param vin  Vehicle Identification Number.
     * @return
     */
    public RestUserVehicleInformationVO getVehicleInformation(String vin);

    /**
     * This Function will return the Vehicle Detailed Information about the VIN
     * @param vin Vehicle Identification Number.
     * @return
     */
    public RestUserVehicleDetailInformationVO getVehicleDetailInformation(String vin);

    /**
     * This Function will get an recall alerts that are being present for the VIn.
     * @param vin
     * @return
     */
    public RestVehicleAlertResponseVO getVehicleRecallAlertDetail(String vin);

   
}
