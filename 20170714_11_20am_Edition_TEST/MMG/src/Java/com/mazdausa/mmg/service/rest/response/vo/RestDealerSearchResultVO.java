/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.rest.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.web.client.response.vo.ErrorResponseVO;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This  Value Object, will represent the search of the Dealer.
 * @author Pankaj Bhatt
 * @version 1.0
 */
@XmlRootElement(name = ApplicationConstants.RESULT)
public class RestDealerSearchResultVO {

    // List of all the Attributes
    private String status;
    // List of all the elements.
    private int dealerCount;
    private List<RestDealerInfoVO> dealers;
    private ErrorResponseVO errorResponseVO;
    

    @XmlElement(name = ApplicationConstants.XML_DEALERCOUNT)
    public int getDealerCount() {
        return dealerCount;
    }

    public void setDealerCount(int dealerCount) {
        this.dealerCount = dealerCount;
    }

    @XmlElementWrapper(name = ApplicationConstants.XML_DEALERS)
    @XmlElement(name = ApplicationConstants.XML_DEALER)
    public List<RestDealerInfoVO> getDealers() {
        return dealers;
    }

    public void setDealers(List<RestDealerInfoVO> dealers) {
        this.dealers = dealers;
    }

    @XmlAttribute(name = ApplicationConstants.RESULT_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = ApplicationConstants.XML_ERROR)
     public ErrorResponseVO getErrorResponseVO() {
        return errorResponseVO;
    }

    public void setErrorResponseVO(ErrorResponseVO errorResponseVO) {
        this.errorResponseVO = errorResponseVO;
    }
}
