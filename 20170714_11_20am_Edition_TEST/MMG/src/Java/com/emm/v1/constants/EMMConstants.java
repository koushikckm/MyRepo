/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.emm.v1.constants;

import com.emm.v1.session.vo.ClientSessionVO;
import com.sun.jersey.spi.resource.Singleton;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author PankajB
 */
@Component
@Singleton
public class EMMConstants {


     /**
     * EMM Set of Specific Properties.
     */
    @Value("${EMM.WURFL.LOCATION}")
    public String EMM_WURFL_LOCATION;

    @Value("${EMM.MESSAGE.SUCCESSFUL.REGISTRATION}")
    public String EMM_REGISTRATION_SUCCESS;

    @Value("${EMM.MESSAGE.FAILURE.REGISTRATION}")
    public String EMM_REGISTRATION_FAILURE;

    @Value("${EMM.MESSAGE.REQUEST.SUCCESS}")
    public String EMM_REQUEST_SUCCESS;

    @Value("${EMM.MESSAGE.REQUEST.ERROR}")
    public String EMM_REQUEST_ERROR;

    @Value("${EMM.MESSAGE.GUID.MISMATCH}")
    public String EMM_REQUEST_GUID_MISMATCH;

    @Value("${EMM.AES.ENCRYTPION.KEY}")
    public String EMM_AES_ENCRYPTION_KEY;


    @Value("${EMM.COMMUNICATION.SERVICES.FAILURE}")
    public String EMM_SERVICES_COMMUNICATION_FAILURE;

    public static final String BRAND_NAME="brand_name";
    public static final String MODEL_NAME="model_name";

    // This Variable will be responsible for controlling whether each request from the logger should be logged in the DB.
    @Value("${EMM.REQUEST.DB.LOGGING}")
    public boolean EMM_REQUEST_DB_LOGGING;

    /**
     * iPhone specific EMM Related Constants.
     */
    @Value("${IPHONE_PUSH_APNS_CERTIFICATE_LOCATION}")
    public String IPHONE_PUSH_APNS_CERTIFICATE_LOCATION;

    @Value("${IPHONE_PUSH_APNS_CERTIFICATE_PASSWORD}")
    public String IPHONE_PUSH_APNS_CERTIFICATE_PASSWORD;

    @Value("${IPHONE_PUSH_APNS_MESSAGE_VINRECALL}")
    public String IPHONE_PUSH_APNS_MESSAGE_VINRECALL;

    @Value("${IPHONE_PUSH_APNS_MESSAGE_SERVICEREMINDER}")
    public String IPHONE_PUSH_APNS_MESSAGE_SERVICEREMINDER;

    public String EMM_DEFAULT_GUID="GUID11";

    /*
     * Added to identify user agent iphone/android
     */
    public static final String EMM_USER_AGENT_TYPE_IPHONE_NEW = "mymazda";
    public static final String EMM_USER_AGENT_TYPE_IPHONE = "iphone";
    public static final String EMM_USER_AGENT_TYPE_ANDROID = "android";
    public static final String EMM_USER_AGENT_TYPE_IPAD = "ipad";
    public static final String EMM_USER_AGENT_BROWSER = "mozilla";


    /**
     * Below are the all the functions defined that will be helpful to manage the Session and TOken of a Client.
     */
    private final static Map<String,ClientSessionVO>  TOKEN_TABLE=new HashMap<String,ClientSessionVO>();

    public static Map<String, ClientSessionVO> getTOKENTABLE() {
        return TOKEN_TABLE;
    }

    public static void updateTOKENTABLE(String sessionId,ClientSessionVO clientSessionVO) {
        synchronized(TOKEN_TABLE)
        {
            TOKEN_TABLE.put(sessionId, clientSessionVO);
        }
    }

     public static void deleteTOKENTABLEEntry(String sessionId,ClientSessionVO clientSessionVO) {
        synchronized(TOKEN_TABLE)
        {
            TOKEN_TABLE.remove(sessionId);
        }
    }
    

}
