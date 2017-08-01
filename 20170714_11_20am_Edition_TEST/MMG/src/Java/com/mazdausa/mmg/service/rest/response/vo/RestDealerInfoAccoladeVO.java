/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import javax.xml.bind.annotation.XmlElement;

/**
 * This Value Object will represent the Dealer Accolades Information.
 * @author PankajB
 */
public class RestDealerInfoAccoladeVO {

    // list of all the elements of Accolade.
    private String id,name,description;

    @XmlElement(name=ApplicationConstants.XML_DEALER_ACCOLADE_DESCRIPTION)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name=ApplicationConstants.XML_DEALER_ACCOLADE_ID)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name=ApplicationConstants.XML_DEALER_ACCOLADE_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
