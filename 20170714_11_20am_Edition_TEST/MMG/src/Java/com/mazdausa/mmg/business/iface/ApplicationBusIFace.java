/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.business.iface;

import com.mazdausa.mmg.db.vo.AccessLogVO;
import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import com.mazdausa.mmg.db.vo.ConstantVO;
import java.util.List;

/**
 * This is the Business Interface, which will be responsible for performing all the BI LOGIC
 * @author PankajB
 * @version 1.0
 */
public interface ApplicationBusIFace {

    /**
     * Below is a list of functions that are being responsible for dealing with Applications present on the server.
     */

    public ApplicationDetailsVO getApplicationDetails(int appDetailsId);

    public ApplicationDetailsVO getApplicationDetailsByVersion(String appVersion);

    public List<ApplicationDetailsVO> getAllApplications();

    public List<ApplicationDetailsVO> getAllActiveApplications();

    public boolean addApplicationDetails(ApplicationDetailsVO applicationDetailsVO);

    public boolean  deleteApplicationDetails(ApplicationDetailsVO applicationDetailsVO);

    public boolean updateApplicationDetails(ApplicationDetailsVO applicationDetailsVO);

    public ApplicationDetailsVO getApplicationDetails(String appName,String appVersion,String osType);



    /**
     * This is the list of functions Related to Application Installation on the Client Device.
    */

    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(int appInstallationDetailsId);

    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appInstallationGuid);

    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appName, String appVersion, String osType);

    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations();

    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallationsByVersion(String version);

    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations(String appName, String version, String osType);

    public boolean addApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO);

    public boolean deleteApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO);

    public boolean updateApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO);


    /**
     * Declaration of Application Logging Related Thing.
     */
    public void logAuditRecord(AccessLogVO accessLogVO);


    /**
     * List of all the function that are related to the constants.
     */
    public ConstantVO getConstant(String constantName);

    /*
     * This is function, which will return all the constant from the DB
     */
    public List<ConstantVO> getAllConstantDetails();

    /**
     * List of all the function that are related to the constants.
     */
    public boolean getConstantByModuleName(String moduleName);

    public boolean addConstantDetails(ConstantVO constantVO);

    public ConstantVO getConstantByModule(String moduleName);

}
