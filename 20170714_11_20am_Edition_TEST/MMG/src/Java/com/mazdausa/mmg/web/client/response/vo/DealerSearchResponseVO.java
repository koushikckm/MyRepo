/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.response.vo;

import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerInfoVO;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the Value object, which will be returned by the service layer to the COntroller Layer. This class holds the results for performing
 * limited Dealer ZipCode Search.
 * @author PankajB
 * @version 1.0
 */
@XmlRootElement
public class DealerSearchResponseVO {

    private String status,description;
    private int searchcount;

    private List<RestDealerInfoVO> dealers;
    private ErrorResponseVO errorResponseVO;

    public List<RestDealerInfoVO> getDealers() {
        return dealers;
    }

    public void setDealers(List<RestDealerInfoVO> dealers) {
        this.dealers = dealers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSearchcount() {
        return searchcount;
    }

    public void setSearchcount(int searchcount) {
        this.searchcount = searchcount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ErrorResponseVO getErrorResponseVO() {
        return errorResponseVO;
    }

    public void setErrorResponseVO(ErrorResponseVO errorResponseVO) {
        this.errorResponseVO = errorResponseVO;
    }


}
