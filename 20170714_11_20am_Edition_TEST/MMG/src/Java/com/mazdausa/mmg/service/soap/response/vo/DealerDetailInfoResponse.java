/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Value Object will represent the Dealer Information Response as given by the SOAP Web Service.
 * @author PankajB
 * @version 1.0
 */

public class DealerDetailInfoResponse {

    //private String city="IRVINE", code, country="US", district="01", errorStatus, exclusive="1", factoryOrderOnly, macDealerCode, metroGroup="1401012", name="TUTTLE-CLICK MAZDA", phone="(949) 421-3200";
    //private String phoneAreaCode, phonePrefix, phoneSuffix, serviceOnlyflg, state="CA", status="A", address="41 AUTO CENTER DRIVE", volumeGroup="02", zip="92618-2803", regionCode="PA", regionName="WESTERN REGION";
    private String city=" ", code=" ", country=" ", district=" ", errorStatus, exclusive =" ", factoryOrderOnly =" ", macDealerCode= " ", metroGroup=" ", name=" ", phone=" ";
    private String phoneAreaCode=" ", phonePrefix= " ", phoneSuffix=" ", serviceOnlyflg=" ", state=" ", status, address=" ", volumeGroup=" ", zip=" ", regionCode=" ", regionName=" ";
    private String expServiceFlag=" "; 
    
    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_EXPSERFLAG)
    public String getExpServiceFlag() {
		return expServiceFlag;
	}

	public void setExpServiceFlag(String expServiceFlag) {
		this.expServiceFlag = expServiceFlag;
	}

	@XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_ADDRESS)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address !=null && address.length() !=0)
        this.address = address;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_CITY)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city !=null && city.length() !=0)
        this.city = city;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
         if(code !=null && code.length() !=0)
        this.code = code;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_COUNTRY)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
       if(country !=null && country.length() !=0)
        this.country = country;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_DISTRICT)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
         if(district !=null && district.length() !=0)
        this.district = district;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_ERRORSTATUS)
    public String getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(String errorStatus) {
        this.errorStatus = errorStatus;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_EXCLUSIVE)
    public String getExclusive() {
        return exclusive;
    }

    public void setExclusive(String exclusive) {
        if(exclusive !=null && exclusive.length() !=0)
        this.exclusive = exclusive;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_FACTORYORDERONLY)
    public String getFactoryOrderOnly() {
        return factoryOrderOnly;
    }

    public void setFactoryOrderOnly(String factoryOrderOnly) {
        if(factoryOrderOnly !=null && factoryOrderOnly.length() !=0)
        this.factoryOrderOnly = factoryOrderOnly;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_MACDEALERCODE)
    public String getMacDealerCode() {
        return macDealerCode;
    }

    public void setMacDealerCode(String macDealerCode) {
        if(macDealerCode !=null && macDealerCode.length() !=0)
        this.macDealerCode = macDealerCode;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_METROGROUP)
    public String getMetroGroup() {
        return metroGroup;
    }

    public void setMetroGroup(String metroGroup) {
        if(metroGroup !=null && metroGroup.length() !=0)
        this.metroGroup = metroGroup;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name !=null && name.length() !=0)
        this.name = name;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_PHONE)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone !=null && phone.length() !=0)
        this.phone = phone;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_PHONEAREACODE)
    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        if(phoneAreaCode !=null && phoneAreaCode.length() !=0)
        this.phoneAreaCode = phoneAreaCode;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_PHONEPREFIX)
    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        if(phonePrefix !=null && phonePrefix.length() !=0)
        this.phonePrefix = phonePrefix;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_PHONESUFFIX)
    public String getPhoneSuffix() {
        return phoneSuffix;
    }

    public void setPhoneSuffix(String phoneSuffix) {
        if(phoneSuffix !=null && phoneSuffix.length() !=0)
        this.phoneSuffix = phoneSuffix;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_REGIONCODE)
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        if(regionCode !=null && regionCode.length() !=0)
        this.regionCode = regionCode;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_REGIONNAME)
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
         if(regionName !=null && regionName.length() !=0)
        this.regionName = regionName;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_SERVICEONLYFIG)
    public String getServiceOnlyflg() {
        return serviceOnlyflg;
    }

    public void setServiceOnlyflg(String serviceOnlyflg) {
        if(serviceOnlyflg !=null && serviceOnlyflg.length() !=0)
        this.serviceOnlyflg = serviceOnlyflg;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_STATE)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(state !=null && state.length() !=0)
        this.state = state;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_VOLUMEGROUP)
    public String getVolumeGroup() {
        return volumeGroup;
    }

    public void setVolumeGroup(String volumeGroup) {
         if(volumeGroup !=null && volumeGroup.length() !=0)
        this.volumeGroup = volumeGroup;
    }

    @XmlElement(name = ApplicationConstants.SERVICE_SOAP_DEALERINFO_RESPONSE_DEALER_ZIP)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
         if(zip !=null && zip.length() !=0)
        this.zip = zip;
    }
}
