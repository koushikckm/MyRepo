/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.service.soap.impl;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.soap.iface.DealerWebServiceIFace;
import com.mazdausa.mmg.service.soap.request.vo.SOAPDealerInfoRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.SOAPDealerInfoResponseVO;
import com.mazdausa.mmg.utilities.MMGUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * This Implementation class will contain the implementation of the functions defined in the Dealer Web Service Interface.
 * @author PankajB
 * @version 1.0
 */
@Component
public class DealerWebServiceImpl implements DealerWebServiceIFace {

    private static final Logger logger = LoggerFactory.getLogger(DealerWebServiceImpl.class);
    @Autowired
    WebServiceTemplate webServiceTemplate;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    MMGUtilities mmgUtilities;
    @Autowired
    EMMConstants emmConstants;

    /**
     * This is the function, which will return the Dealer Details given the Dealer Input Code.
     * @param dealerCode
     * @return
     */
    public SOAPDealerInfoResponseVO getDealerInfo(SOAPDealerInfoRequestVO dealerInfoRequestVO) {
        logger.debug(">> Entering {} getDealerInfo() with DEALER CODE = {}", this.getClass(), dealerInfoRequestVO.getDealerCode());
        SOAPDealerInfoResponseVO dealerInfoResponseVO = null;

        Object object = mmgUtilities.getWebServiceHeaderElement(appConstants.SERVICE_SOAP_DEALERINFOACTION.trim(), appConstants);
        try {
            dealerInfoResponseVO = (SOAPDealerInfoResponseVO) webServiceTemplate.marshalSendAndReceive(appConstants.SERVICE_SOAP_DEALERINFO.trim(), dealerInfoRequestVO, (WebServiceMessageCallback) object);
        } catch (Exception ex) {
            logger.error("An Exception, has occured, while getting Dealer Information from the web service. ", ex);
        }
        logger.debug("<< Exiting getDealerInfo() with response = {} ", dealerInfoResponseVO);
        return dealerInfoResponseVO;
    }
}
