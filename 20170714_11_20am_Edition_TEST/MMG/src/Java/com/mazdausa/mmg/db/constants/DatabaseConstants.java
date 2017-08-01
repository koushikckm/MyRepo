/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.db.constants;

/**
 * This Class will contain all the database related specific constants.
 * @author  Pankaj Bhatt
 * @version 1.0
 *
 */
public class DatabaseConstants {


    /**
     * Description of all the tables and their Attributes.
     */
    public static final String TABLE_MMG_APP_DETAILS="mmgappdetails";
    public static final String TABLE_MMG_ACCESS_LOGS="mmgaccesslogs";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS="mmgappinstalldetails";
    public static final String TABLE_MMG_APP_CONSTANTS="mmgconstants";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL="mmgcustvinrecall";
    public static final String TABLE_MMG_APP_CUSTOMER_SERVICE_REMINDERS="mmgservicereminders";
    public static final String TABLE_MMG_APP_USERS="mmgusers";
    public static final String TABLE_MMG_APP_USERS_VEHICLES="mmguservehicles";
    public static final String TABLE_MMG_APP_VEHICLE_CODE="mmgvehiclecode";
    
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE="mmgrecallcontroltable";
    public static final String TABLE_MMG_RECALL_PUSH_DATA="mmgrecallpushdata";
    public static final String TABLE_MMG_RECALL_HISTORY_TABLE="mmgrecallhistory";



    /**
     * LIST of TABLE MMGAPPDETAILS Table.
     */
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_APPDETAILSID="appdetails_ident";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_APPNAME="appname";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_APPVERSION="appversion";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_APPPATH="apppath";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_ADDEDDATE="addeddate";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_IP="ip";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_DELETEDDATE="deleteddate";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_ADDEDBY="addedby";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_DELETEDBY="deletedby";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_CLIENTOSTYPE="clientostype";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_CLIENTOSMINVERSION="clientosminversion";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_CLIENTOSMAXVERSION="clientosmaxversion";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_ISUPGRADEDVERSION="isupgradedversion";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_APPFEATURELIST="appfeaturelist";
    public static final String TABLE_MMG_APP_DETAILS_COLUMN_ISAPPDELETED="isappdeleted";

    // QUERIES OF MMGAPPDETAILS TABLE.




    /**
     * LIST of TABLE MMGAPPINSTALLDETALS
     */
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_APPINSTALLDETAILSID="appinstallationdetails_ident";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILEIMEI="mobile_imei";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_PHONETYPE="phonetype";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILEUSERAGENT="mobileuseragent";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_ADDEDDATE="addeddate";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_APPINSTALLATIONGUID="appinstallationguid";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_USERIP="userip";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_PHONEBRAND="phonebrand";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILENO="mobileno";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_MOBILEIMSI="mobile_imsi";
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_APPDETAILSID="appdetails_ident";   // Foreign KEY
    public static final String TABLE_MMG_APP_INSTALLATION_DETAILS_COLUMN_DEVICEAPNSTOKEN="deviceapnstoken";


    /**
     *  LIST OF TABLE MMGUSERS
     */
    public static final String TABLE_MMG_APP_USERS_COLUMN_USERID="user_ident";
    public static final String TABLE_MMG_APP_USERS_COLUMN_USERMAILID="usermailid";
    public static final String TABLE_MMG_APP_USERS_COLUMN_ISUSERDELETED="isuserdeleted";
    public static final String TABLE_MMG_APP_USERS_COLUMN_ADDEDBY="addedby";
    public static final String TABLE_MMG_APP_USERS_COLUMN_UPDATEDBY="updatedby";
    public static final String TABLE_MMG_APP_USERS_COLUMN_ADDEDDATE="addeddate";
    public static final String TABLE_MMG_APP_USERS_COLUMN_UPDATEDDATE="updateddate";
    public static final String TABLE_MMG_APP_USERS_COLUMN_ISURAUTHENTICATED="isuserauthenticated";
    public static final String TABLE_MMG_APP_USERS_COLUMN_CUSTOMERID="mmgservicecustid";
    public static final String TABLE_MMG_APP_USERS_COLUMN_LASTLOGINAPPINSTALLATIONID="lastloginAppInstallation_Ident";


    /**
     * LIST OF ALL USER VEHICLES
     */
    public static final String TABLE_MMG_APP_USERS_VEHICLES_COLUMN_USERVEHICLEID="mmguservehicle_ident";
    public static final String TABLE_MMG_APP_USERS_VEHICLES_COLUMN_CUSTVIN="custvin";
    public static final String TABLE_MMG_APP_USERS_VEHICLES_COLUMN_ADDEDDATE="addeddate";
    public static final String TABLE_MMG_APP_USERS_VEHICLES_COLUMN_USER="userid_ident";
    public static final String TABLE_MMG_APP_USERS_VEHICLES_COLUMN_VTITLE="vin_vehicle_name";

    /**
     * LIST OF ALL ACCESS LOG TABLE COLUMN
     */
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_ACCESSLOG_IDENT="accessLog_ident";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_APPINSTALLATIONGUID="appinstallationguid";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_RELATIVEAPPLICATIONURI="relativeapplicationuri";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_REQUESTDATA="requestdata";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_RESPONSECODE="repsonsecode";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_REQUESTEDTIME="requestedtime";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_REQUESTEXECUTIONTIME="requestexecutiontime";
    public static final String TABLE_MMG_APP_ACCESS_LOG_COLUMN_CLIENTAPPSESSIONID="clientappsessionid";

    /**
     * LIST OF ALL CONSTANTS TABLE COLUMN
     */
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_IDENT="constant_ident";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_NAME="appconstantname";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_VALUE="appconstantvalue";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_ADDED_BY="addedby";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_UPDATED_BY="updatedby";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_ADDED_DATE="addeddate";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_UPDATED_DATE="updatedate";
    public static final String TABLE_MMG_APP_MAZDA_CONSTANTS_COLUMN_CONSTANT_MODULE_NAME="appmodulename";

    /**
     * LIST OF ALL MMGSERVICEREMINDERS TABLE COLUMN
     */
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_SERVICE_REMINDER_IDENT="servicereminders_ident";
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_VIN="vin";
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_DEALER_CODE="dealercode";
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_COUPON_SERVICEREMINDER_NUMBER="couponserviceremindernumber";
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_ADDED_DATE="addeddate";
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_SENT_TIMES="senttimes";
    public static final String TABLE_MMG_APP_SERVICE_REMINDER_COLUMN_IS_ALERT_ACTIVATED="isalertActivated";    

    /**
     * LIST OF ALL MMGCUSTVINRECALL TABLE COLUMN
     */
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VINRECALL_ID="vinRecall_ident";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VINRECALL_SSPNO="vinrecallSSPNo";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VINRECALL_SSPDESC="vInrecallSSPDesc";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VIN_RECALLSTART_DATE="vinrecallStartDate";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_ADDED_DATE="addeddate";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_SENTTIMES="senttimes";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_IS_ALERT_ACTIVATED="isalertactivated";
    public static final String TABLE_MMG_APP_CUSTOMER_VIN_RECALL_COLUMN_VIN="VIN";
    

    /**
     * LIST OF ALL MMGVEHICLECODE TABLE COLUMN
     */
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_IDENT="vehiclecode_ident";
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_YEAR="vehicle_year";
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL="vehicle_model";
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODEL_SEPARACTOR=",";
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_ADDED_UPDATED="vehicleaddedupdateddate";
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_MODELNAME="modelname";
    public static final String TABLE_MMG_APP_VEHICLE_CODE_COLUMN_VEHICLE_CODE_VEHICLE_TRIMNAME="trimname";
    
    
    /**
     * LIST OF ALL MMGRECALLCONTROLTABLE TABLE COLUMN
     */
    //public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_PARAMETER_ID="parameter_id";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_PARAMETER="parameter";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_VALUE="value";
    //public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_LASTUPDATEDTIME="last_updated_time";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_ADDEDBY="addedby";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_UPDATEBY="updatedby";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_ADDEDDATE="addeddate";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_UPDATEDATE="updateddate";
    
    /**
     * LIST OF ALL mmgrecallhistory TABLE COLUMN
     */
    public static final String TABLE_MMG_RECALL_HISTORY_TABLE_COLUMN_RCLLJOBID="RecallJobId";
    public static final String TABLE_MMG_RECALL_HISTORY_TABLE_COLUMN_RCLLPUSHNOTDATE="recallPushNotificationDate";
    public static final String TABLE_MMG_RECALL_HISTORY_TABLE_COLUMN_RCLLPUSHNOTCOUNT_IOS="recallPushNotificationCount_IOS";
    public static final String TABLE_MMG_RECALL_HISTORY_TABLE_COLUMN_RCLLSUCCESSSTATUS="recallSuccessStatus";
    public static final String TABLE_MMG_RECALL_HISTORY_TABLE_COLUMN_RCLLPUSHNOTCOUNT_ANDROID="recallPushNotificationCount_Android";

    /**
     * LIST OF ALL MMGRECALLPUSHDATA TABLE COLUMN
     */
    
    /**
     * cust_id
vin_cd
rcll_no
rcll_strt_dt
launched_dt
recall_count
notification_flg
last_notification_date
notification_status
     */
    
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_CUST_ID="cust_id";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_VIN_ID="vin_cd";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_RCLL_NO="rcll_no";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_RCLL_STRT_DT="rcll_strt_dt";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_LAUNCHED_DT="launched_dt";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_RECALL_COUNT="recall_count";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_NOTIFICATION_FLG="notification_flg";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_LAST_NOTIFICATION_DATE="last_notification_date";
    public static final String TABLE_MMG_RECALL_CONTROL_TABLE_COLUMN_NOTIFICATION_STATUS="notification_status";
    
    
   
    // QUERIES of TABLE MMGAPPINSTALLDETAILS
}
