/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.dao.iface;

import com.mazdausa.mmg.db.vo.AccessLogVO;
import java.util.List;

/**
 * This is an Interface which will represent the Class AccessLog Table in the Database.
 * @author pankajkh
 */
public interface AccessLogDaoIFace {

    public AccessLogVO getAccessLogDetails(int accessLogId);

    public AccessLogVO getAccessLogDetails(String installationGuid);

    public List<AccessLogVO> getAllAccessLogDetails();

    public void addAccessLogDetails(AccessLogVO accessLogVO);

    public void updateAccessLogDetails(AccessLogVO accessLogVO);

    public void deleteAccessLogDetails(AccessLogVO accessLogVO);

    public void log(AccessLogVO accessLogVO) ;

}
