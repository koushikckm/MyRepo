/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import java.util.List;

/**
 * This is an Interface which will represent the Class AppDetails Table in the Database.
 * @author PankajB
 */
public interface ApplicationDetailsDaoIFace {


    public ApplicationDetailsVO getApplicationDetails(int appDetailsId);

    public ApplicationDetailsVO getApplicationDetailsByVersion(String appVersion);

    public List<ApplicationDetailsVO> getAllApplications();

    public List<ApplicationDetailsVO> getAllActiveApplications();
    
    public void addApplicationDetails(ApplicationDetailsVO applicationDetailsVO);

    public void  deleteApplicationDetails(ApplicationDetailsVO applicationDetailsVO);

    public void updateApplicationDetails(ApplicationDetailsVO applicationDetailsVO);

    public ApplicationDetailsVO getApplicationDetails(String appName,String appVersion,String osType);

    


}
