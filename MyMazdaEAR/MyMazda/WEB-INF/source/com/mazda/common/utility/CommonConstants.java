package  com.mazda.common.utility;
/**
 * @author Riaz.Ansari
 * @since 20-09-2013
 * @version 1.0
 * Constant objects used to replace hard coded things in application 
 */
public interface CommonConstants
{

	/**
     * special characters 
     */
    public static final String HASH_CHARACTER 	= "-";
    public static final String COMMA_CHARACTER 	= ",";
    public static final String SPACE_CHARACTER 	= "";
    /**
	   * Date constants
	   */
    
    public static final String DATE_TIME_FORMAT ="h:mm a";
    public static final String DATE_FORMAT_ONE = "dd-MM-yyyy";
    public static final String DATE_FORMAT_SECOND = "dd/MM/yyyy";
    
    /**
     * Date time format for SQL queries.
     */
    public static final String DATE_TIME_FORMAT_SQL = "MM/DD/YYYY HH24:MI";

    /**
     * Date format for SQL queries.
     */
    public static final String DATE_FORMAT_SQL = "MM/dd/yyyy";
    /**
     * System date time format
     */
    public static final String SYS_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm";
    /**
     * Date time format for the Galileo.
     */
      public static final String GALILEO_DATE_TIME_FORMAT = "yyyyMMdd";
      public static final String GALILEO_REQUEST_DATE_TIME_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
      /** Date format for the GDS application. */
      public static final String GDS_FORMAT = "yyyy-MM-dd";

    /**
     * Travel Boutique Calendar Date format---2013-12-28T00:00:00
     */
  	public static final String USER 				= "U";
  	public static final String ADMIN 				= "A";  
	public static final String ADMIN_KEY 			= "Admin";
	public static final String USER_KEY 			= "User";
	public static final String AFTER_SAVE_MSG 		= "Permission Granted Successfully";
	public static final String MODERATOR_KEY 		= "Moderator";
	public static final String SUPER_ADMIN_KEY 		= "Super";
	public static final String SUBMITTER_KEY 		= "Submitter";
	public static final String ENABLE				= "ENABLE";
	public static final String DISABLE				= "DISABLE";
	public static final String SUPER_ADMIN 			= "Super Admin";
	
	public static final int LOGIN_HELPER 				= 231;
	public static final int VIEW_HELPER 				= 10;
	public static final int AdminId 					= 3;
	public static final int JAVA 						= 1;
	public static final int CPP 						= 2;
	public static final int PHP 						= 3;
	public static final int TRUE_FALSE_QUESTION_TYPE 	= 1;
	public static final int MULTI_ANSWER_QUESTION_TYPE 	= 2;
	public static final int Descriptive_QUESTION_TYPE 	= 3;
	public static final int EXAM 						= 1;
	public static final int SURVEY 						= 2;
	public static final int QUIZ 						= 3;
	public static final int INTERVIEW 					= 4;
	
	public static final int DRAFT 						= 1;
	public static final int PUBLISH 					= 2;
	public static final int CLOSE 						= 3;
	
	public static final int VIEW_ALL					= 0;
	
	public static final int ORAL						= 1;
	public static final int EXTRACT						= 2;
	
	public static final int projectType					= 1;
	public static final int ReportType					= 2;
	public static final int AuthoriseType				= 3;
	
	public static final int oralTemplate				= 1;
	public static final int extractTemplate				= 2;
	
	public static final String ADD_QUESTIONS			= "Add Questions";
	public static final String DEFINITION				= "Definition";
	
	public static final String CONFIG_REPORT			= "Config Report";
	public static final String PUBLISH_TAB				= "Publish";
	
	public static final String GRADE					= "Grade";
	public static final String GRANT_ACCESS				= "Grant Access";
	public static final String LINEAGE					= "Lineage";
	
	public static final String REPORT_ACCESSOR			= "ReportAccessor";
		
	
}