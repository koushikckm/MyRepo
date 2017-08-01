/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.iface;

import com.mazdausa.mmg.service.rest.request.vo.RestUserDetailsUpdateRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestUserDetailsUpdateResponseVO;
import com.mazdausa.mmg.service.soap.request.vo.SOAPUserGetEgiftsRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPUserGetEgiftsResponseVO;

/**
 * This Interface will contain all the functions related to SOAP Based web service
 * @author PankajB
 * @version 1.0
 */
public interface UserWebServiceIFace {

    /**
     * This Function will return the egift details for user from CustomerID and vin.
     * @param getEgiftsRequestVO
     * @return
     */
    public SOAPUserGetEgiftsResponseVO getUserEgifts(SOAPUserGetEgiftsRequestVO getEgiftsRequestVO);

}
