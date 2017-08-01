/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.request.vo;

import java.io.Serializable;

/**
 * This Value Object is being responsible for containing all the search items that are being fed to the REST Based DEALER SEARCH Service.
 * @author PankajB
 * @version 1.0
 */
public class RestDealerSearchLocatorRequestVO implements Serializable {

    private String zipCode, state, city;
    private int count = -1, startFrom = -1, distance = -1;
    boolean mazdaSpeedOnly = false;
    private String name;
    private String latitude,longitude;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(int startFrom) {
        this.startFrom = startFrom;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean isMazdaSpeedOnly() {
        return mazdaSpeedOnly;
    }

    public void setMazdaSpeedOnly(boolean mazdaSpeedOnly) {
        this.mazdaSpeedOnly = mazdaSpeedOnly;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RestDealerSearchLocatorRequestVO other = (RestDealerSearchLocatorRequestVO) obj;
        if ((this.zipCode == null) ? (other.zipCode != null) : !this.zipCode.equals(other.zipCode)) {
            return false;
        }
        if ((this.state == null) ? (other.state != null) : !this.state.equals(other.state)) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if (this.startFrom != other.startFrom) {
            return false;
        }
        if (this.distance != other.distance) {
            return false;
        }
        if (this.mazdaSpeedOnly != other.mazdaSpeedOnly) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RestDealerSearchLocatorRequestVO{" + "zipCode=" + zipCode + "state=" + state + "city=" + city + "count=" + count + "startFrom=" + startFrom + "distance=" + distance + "mazdaSpeedOnly=" + mazdaSpeedOnly + "name=" + name + "latitude=" + latitude + "longitude=" + longitude + '}';
    }


   

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    


}
