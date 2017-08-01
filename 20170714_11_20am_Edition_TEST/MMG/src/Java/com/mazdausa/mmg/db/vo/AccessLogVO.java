/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.db.vo;

import com.mazdausa.mmg.db.constants.DatabaseConstants;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This Class will represent an access Log record that represent an request made by the client.
 * @author PankajB
 * @version 1.0
 */

@Entity
@Table(name=DatabaseConstants.TABLE_MMG_ACCESS_LOGS)
@NamedQueries({@NamedQuery(name="com.mazdausa.mmg.db.vo.AccessLogVO.findAccessLogDetailsByGUID",query="from com.mazdausa.mmg.db.vo.AccessLogVO  where trim(upper(appInstallationGuid))=?")})
public class AccessLogVO implements Serializable {

private int accessLog_ident,responseCode,executionTime;
private String installationGuid,relativeApplicationURI,requestData,sessionId;
private Date requestedTime;



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_ACCESSLOG_IDENT)
    public int getAccessLog_ident() {
        return accessLog_ident;
    }

    public void setAccessLog_ident(int accessLog_ident) {
        this.accessLog_ident = accessLog_ident;
    }
    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_REQUESTEXECUTIONTIME)
    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_APPINSTALLATIONGUID)
    public String getInstallationGuid() {
        return installationGuid;
    }

    public void setInstallationGuid(String installationGuid) {
        this.installationGuid = installationGuid;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_RELATIVEAPPLICATIONURI)
    public String getRelativeApplicationURI() {
        return relativeApplicationURI;
    }

    public void setRelativeApplicationURI(String relativeApplicationURI) {
        this.relativeApplicationURI = relativeApplicationURI;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_REQUESTDATA)
    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_REQUESTEDTIME)
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Date requestedTime) {
        this.requestedTime = requestedTime;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_RESPONSECODE)
    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Column(name=DatabaseConstants.TABLE_MMG_APP_ACCESS_LOG_COLUMN_CLIENTAPPSESSIONID)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }





    
}
