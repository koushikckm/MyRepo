package com.mazdausa.mmg.service.rest.response.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mazdausa.mmg.constants.ApplicationConstants;

@XmlRootElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES, namespace = ApplicationConstants.SERVICE_REST_GETVEHICLES_RESPONSEELEMENT_NAMESPACE)
public class RestUserGetVehiclesResponseVO {

	private GetVehiclesVO vehicles;

    @XmlElement(name = ApplicationConstants.XML_REST_GETVEHICLES_RESPONSE_PARAMETER_GETVEHICLES_GETVEHICLESRETURN)
    public GetVehiclesVO getVehicles() {
        return vehicles;
    }

    public void setVehicles(GetVehiclesVO vehicles) {
        this.vehicles = vehicles;
    }
}
