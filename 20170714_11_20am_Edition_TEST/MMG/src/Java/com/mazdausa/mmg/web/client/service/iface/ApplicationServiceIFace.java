/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.service.iface;

import com.emm.v1.mobile.vo.MobileCharacteristicsVO;
import com.emm.v1.request.vo.ClientGuidRequestVO;

/**
 * This is an Service level Interface and being responsible for all Service Layer Functions.
 * @author PankajB
 * @version 1.0
 */
public interface ApplicationServiceIFace {

    /**
     * This is the servlce layer function which will be responsible for getting a new GUID based upon the Version Entered.
     * @param clientGuidRequestVO
     * @return
     */
    public String getGuid(ClientGuidRequestVO clientGuidRequestVO,MobileCharacteristicsVO mobileCharactersticsVO);

    /**
     * This Function will be responsible for checking the Authenticity of 
     * @param guid
     * @return
     */
    public boolean checkAuthentictyOfGUID(String guid);
            

}
