/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This VO will contain the details of Package Options for a package of an Option Group of a vehicle
 * @author PankajB
 * @version 1.0
 */
public class RestUserVehicleOptionGroupPackageOptionVO {

    private String pkgOptCode, pkgOptDesc;

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_PACKAGE_PACKAGEOPT_PKGOPTCODE)
    public String getPkgOptCode() {
        return pkgOptCode;
    }

    public void setPkgOptCode(String pkgOptCode) {
        this.pkgOptCode = pkgOptCode;
    }

    @XmlElement(name = ApplicationConstants.XML_VEHICLE_DETAIL_INFORMATION_VEHICLERESULTSET_VEHICLE_OPTIONGROUP_PACKAGE_PACKAGEOPT_PKGOPTDESC)
    public String getPkgOptDesc() {
        return pkgOptDesc;
    }

    public void setPkgOptDesc(String pkgOptDesc) {
        this.pkgOptDesc = pkgOptDesc;
    }
}
