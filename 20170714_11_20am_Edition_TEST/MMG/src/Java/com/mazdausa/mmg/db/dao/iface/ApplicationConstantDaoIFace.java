/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.ConstantVO;
import java.util.List;

/**
 *
 * @author pankajkh
 */
public interface ApplicationConstantDaoIFace {

    public ConstantVO getAppConstantDetails(int constantId);

    public ConstantVO getAppConstantDetails(String constantName);

    public boolean getAppConstantDetailsByModule(String moduleName);

    public ConstantVO getConstantDetailsByModule(String moduleName);

    public void addAppConstantDetails(ConstantVO constantVO);

    public void updateAppConstantDetails(ConstantVO constantVO);

    public void deleteAppConstantDetails(ConstantVO constantVO);

    /*
     * This function will retrieve all the constant details from the DB
     * This function added for Android device.
     */
    public List<ConstantVO> getAllConstantDetails();
}
