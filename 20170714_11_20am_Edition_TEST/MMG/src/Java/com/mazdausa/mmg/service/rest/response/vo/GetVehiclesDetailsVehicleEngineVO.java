/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This VO will hold the details of the Vehicle Engine
 * @author PankajB
 * @version 1.0
 */
//@XmlType(namespace = "http://webservices.vehicles.marketing.mazdausa.com")
public class GetVehiclesDetailsVehicleEngineVO {

    private String camshaft,cylRot,engDesc,engType,cylQty,dispec,hpQty,hpRpm;

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_CAMSHAFT)
    public String getCamshaft() {
        return camshaft;
    }

    public void setCamshaft(String camshaft) {
        this.camshaft = camshaft;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_CYLQTY)
    public String getCylQty() {
        return cylQty;
    }

    public void setCylQty(String cylQty) {
        this.cylQty = cylQty;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_CYLROT)
    public String getCylRot() {
        return cylRot;
    }

    public void setCylRot(String cy1Rot) {
        this.cylRot = cy1Rot;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_DISPCC)
    public String getDispec() {
        return dispec;
    }

    public void setDispec(String dispec) {
        this.dispec = dispec;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_ENGDESC)
    public String getEngDesc() {
        return engDesc;
    }

    public void setEngDesc(String engDesc) {
        this.engDesc = engDesc.trim();
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_ENGTYPE)
    public String getEngType() {
        return engType;
    }

    public void setEngType(String engType) {
        this.engType = engType;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_HPQTY)
    public String getHpQty() {
        return hpQty;
    }

    public void setHpQty(String hpQty) {
        this.hpQty = hpQty;
    }

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_ENGINE_HPRPM)
    public String getHpRpm() {
        return hpRpm;
    }

    public void setHpRpm(String hpRpm) {
        this.hpRpm = hpRpm;
    }

    

}
