/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.ApplicationDetailsVO;
import com.mazdausa.mmg.db.vo.ApplicationInstallationDetailsVO;
import java.util.List;

/**
 * This interface will contain a list of functions, that are to be applied on the ApplicationInstallationDetails table in the Database.
 * @author PankajB
 * @version 1.0
 */
public interface ApplicationInstallationDetailsDaoIFace {

    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(int appInstallationDetailsId);

    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appInstallationGuid);

    public ApplicationInstallationDetailsVO getApplicationInstallationDetails(String appName, String appVersion, String osType);

    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations();

    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallationsByVersion(String version);

    public List<ApplicationInstallationDetailsVO> getAllApplicationInstallations(String appName, String version, String osType);

    public void addApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO);

    public void deleteApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO);

    public void updateApplicationInstallationDetails(ApplicationInstallationDetailsVO applicationInstallationDetailsVO);
}
