/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This is Value Object, that will contain all the Dealer Information as being returned by the Mazda Web Service.
 * @author PankajB
 */
public class RestDealerInfoVO {

	//New attributes adde @ 11-02-2013 start
	private String expServiceFlag="",timeDiff="";
	
	@JsonProperty
    public String getExpServiceFlag() {
		return expServiceFlag;
	}

	public void setExpServiceFlag(String expServiceFlag) {
		this.expServiceFlag = expServiceFlag;
	}

	@JsonProperty
	public String getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(String timeDiff) {
		this.timeDiff = timeDiff;
	}

	//New attributes adde @ 11-02-2013 End
	
	// Listing of all the Attributes
    private String id, name, url;
    // Listing of All the Elements.
    private RestDealerInfoAddressVO address;
    private String distance;
    private String location;
    private String sms;
    private String serviceUrl ;//= "http://www.google.com";
    private boolean mdol;
    private List<RestDealerInfoAccoladeVO> accolades;

    @XmlElementWrapper(name = ApplicationConstants.XML_DEALER_ACCOLADES)
    @XmlElement(name = ApplicationConstants.XML_DEALER_ACCOLADE)
    public List<RestDealerInfoAccoladeVO> getAccolades() {
        return accolades;
    }

    public void setAccolades(List<RestDealerInfoAccoladeVO> accolades) {
        this.accolades = accolades;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_ADDRESS)
    public RestDealerInfoAddressVO getAddress() {
        return address;
    }

    public void setAddress(RestDealerInfoAddressVO address) {
        this.address = address;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_DISTANCE)
    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @JsonProperty
    @XmlAttribute(name = ApplicationConstants.XML_DEALER_ATTRIBUTE_ID)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_LOCATION)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty
    @XmlAttribute(name = ApplicationConstants.XML_DEALER_ATTRIBUTE_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name.trim();
        } else {
            this.name = name;
        }
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_SMS)
    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    @XmlAttribute(name = ApplicationConstants.XML_DEALER_ATTRIBUTE_URL)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_MDOL)
    public boolean isMdol() {
        return mdol;
    }

    public void setMdol(boolean mdol) {
        this.mdol = mdol;
    }

    @XmlElement(name = ApplicationConstants.XML_DEALER_SERVICE_APPOINTMENT_URL)
    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
    }
}
