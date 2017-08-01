/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.soap.iface;

import com.mazdausa.mmg.service.soap.request.vo.SOAPDealerInfoRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPDealerInfoResponseVO;

/**
 * This Interface contain all the SOAP related functions to the Dealer
 * @author PankajB
 * @version 1.0
 */
public interface DealerWebServiceIFace {

    /**
     * THis Function is going to return the Dealer Information given the Dealer Code.
     * @param dealerCode
     * @return
     */
    public SOAPDealerInfoResponseVO getDealerInfo(SOAPDealerInfoRequestVO dealerInfoRequestVO);

}
