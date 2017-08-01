/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This VO will hold the transmission details of Vehicle
 * @author PankajB
 *
 */
@XmlType(namespace = "http://webservices.vehicles.marketing.mazdausa.com")
public class GetVehiclesDetailsVehicleTransmissionVO {

    private String speeds,transdesc,trntype;

    @XmlElement(name=ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_TRANSMISSION_SPEEDS)
    public String getSpeeds() {
        return speeds;
    }

    public void setSpeeds(String speeds) {
        this.speeds = speeds;
    }

    @XmlElement(name=ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_TRANSMISSION_TRANSDESC)
    public String getTransdesc() {
        return transdesc;
    }

    public void setTransdesc(String transdesc) {
        this.transdesc = transdesc;
    }

    @XmlElement(name=ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN_VEHICLEDETAILS_VEHICLELIST_VEHICLE_TRANSMISSION_TRNTYPE)
    public String getTrntype() {
        return trntype;
    }

    public void setTrntype(String trntype) {
        this.trntype = trntype;
    }



}
