/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will contain the details of the Option Group of the Vehicle
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleOptionGroupVO {

    private String msc;
    private List<RestUserVehicleOptionGroupPackageVO> listOfPackages;
    private List<RestUserVehicleOptionGroupOptionVO> listOfOptions;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_MSC)
    public String getMsc() {
        return msc;
    }

    public void setMsc(String msc) {
        this.msc = msc;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_PACKAGE)
    public List<RestUserVehicleOptionGroupPackageVO> getListOfPackages() {
        return listOfPackages;
    }

    public void setListOfPackages(List<RestUserVehicleOptionGroupPackageVO> listOfPackages) {
        this.listOfPackages = listOfPackages;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_OPTION)
    public List<RestUserVehicleOptionGroupOptionVO> getListOfOptions() {
        return listOfOptions;
    }

    public void setListOfOptions(List<RestUserVehicleOptionGroupOptionVO> listOfOptions) {
        this.listOfOptions = listOfOptions;
    }


}
