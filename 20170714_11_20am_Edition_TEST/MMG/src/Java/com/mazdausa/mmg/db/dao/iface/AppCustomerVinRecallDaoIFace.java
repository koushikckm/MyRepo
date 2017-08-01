/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.CustomerVINRecallVO;
import java.util.Date;

/**
 *
 * @author pankajkh
 */
public interface AppCustomerVinRecallDaoIFace {

    public CustomerVINRecallVO getCustomerVinRecallDetails(int vinRecallId);

    public CustomerVINRecallVO getCustomerVinRecallDetails(String vinRecallNo);

    public CustomerVINRecallVO getCustomerVinRecallDetails(String vin,String vinRecallNo,Date startDate);

    public void addCustomerVinRecallDetails(CustomerVINRecallVO customerVINRecallVO);

    public void updateCustomerVinRecallDetails(CustomerVINRecallVO customerVINRecallVO);

    public void deleteCustomerVinRecallDetails(CustomerVINRecallVO customerVINRecallVO);

}
