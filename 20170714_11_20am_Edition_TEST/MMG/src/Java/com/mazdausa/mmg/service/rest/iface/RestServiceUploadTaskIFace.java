/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.service.rest.iface;

import com.mazdausa.mmg.service.rest.response.vo.RestServiceHistoryInfoVO;
import com.mazdausa.mmg.web.client.request.vo.ServiceUploadRequestVO;


/**
 * This is the interface, that will contain all the Tasks related to the VEHICLE of an User.
 * @author Anilk
 * @version 1.0
 */
public interface RestServiceUploadTaskIFace {

	/**
	 * This method returns Service details corresponding to vin & customerId
	 * @param vin
	 * @param custId
	 * @return
	 */
    public RestServiceHistoryInfoVO getServiceInfo(String vin,String custId);
    
	/**
	 * TO Add new service record
	 * @param vo
	 * @return
	 */
    public RestServiceHistoryInfoVO addServiceRecord(ServiceUploadRequestVO serviceRecordReq);
    
    /**
     * To update a service record
     * @param vo
     * @return
     */
    public RestServiceHistoryInfoVO updateServiceRecord(ServiceUploadRequestVO serviceRecordReq);

    /**
     * Delete service record correspondind to musaRefId
     * @param vo
     * @return
     */
    public RestServiceHistoryInfoVO deleteServiceRecord(ServiceUploadRequestVO serviceRecordReq);

}
