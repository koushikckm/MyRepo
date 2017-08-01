package com.mazdausa.mmg.web.client.service.iface;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.mazdausa.mmg.web.client.request.vo.RSACloseRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleRSARequestVO;
import com.mazdausa.mmg.web.client.response.vo.RSACloseResponseVO;
import com.mazdausa.mmg.web.client.response.vo.RSAResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleRSAResponseVO;

public interface UserVehicleRSAServiceIFace {
	
	 /**
     * This function will submit Road Side Assistance request
     * @param UserVehicleRSARequestVO
     * @param request
     * @return
     */
    public UserVehicleRSAResponseVO submitRSARequest(String custId, UserVehicleRSARequestVO userVehicleRSARequest, @Context HttpServletRequest request);
    
	 /**
     * This function will fetch Road Side Assistance status details for given RSA reference number
     * @param rsaRequestId
     * @param request
     * @return
     */
    public RSAResponseVO getRSARequestStatusForRefNo(String rsaRefNo, @Context HttpServletRequest request);
    
    /**
     * This function will fetch latest Road Side Assistance status details for selected
     * @param vin
     * @param request
     * @return
     */
    public RSAResponseVO getRSARequestStatusForVin(String vin, @Context HttpServletRequest request);

    /**
	   * This function will update Road Side Assistance request status to closed
	   * @param RSACloseRequestVO
	   * @param request
	   * @return
	  */
    public RSACloseResponseVO closeRSARequests(RSACloseRequestVO closeRequest, @Context HttpServletRequest request);
    
}
