/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will contains the details of Package of Option Group of a particular Vehicle.
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleOptionGroupPackageVO {

    private String packageCode,packageDescription;
    private List<RestUserVehicleOptionGroupPackageOptionVO> listOfPackageOptions;
    

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_PACKAGE_PACKAGECODE)
    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    @XmlElement(name=ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_PACKAGE_PACKAGEDESC)
    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_PACKAGE_PACKAGEOPT)
    public List<RestUserVehicleOptionGroupPackageOptionVO> getListOfPackageOptions() {
        return listOfPackageOptions;
    }

    public void setListOfPackageOptions(List<RestUserVehicleOptionGroupPackageOptionVO> listOfPackageOptions) {
        this.listOfPackageOptions = listOfPackageOptions;
    }

    

}
