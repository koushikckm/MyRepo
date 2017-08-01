package  com.mazda.common.utility;


/**
 * @author Pradeep.Sharma
 * @since 26-12-2012
 * @version 1.0
 * Constant variables used to replace hard coded things in content API 
 */
public interface ContentConstants
{
	
	public static final String GDS				= "GDS";
	public static final String LCC				= "LCC";
	/** Galileo Airlins*/
	public static final String GALILEO_AIR			= "1G";
	
	/** Travel Boutique LCCs*/
	public static final String TRAVEL_BOUTIQUE_LCC	= "TB";
	
	/** Desiya Hotels*/
	public static final String DESIYA_HOTELS		= "DS";
	
	/**
	 * Sector
	 */
	public static final String DOM_SECTOR		= "D";
	public static final String INT_SECTOR		= "I";
	/**
	 * Trip Type
	 */
	public static final int OUT_BOUND_TYPE			= 1;
	public static final int IN_BOUND_TYPE			= 2;
	
	/**Content Type */
	public static final String CONTENT_TYPE_AIR 	= "A";
	public static final String CONTENT_TYPE_HOTEL 	= "H";
	public static final String CONTENT_TYPE_TRANSFERS= "T";

	/**Search Type*/
	public static final String AIR_ONE_WAY_TRIP		= "OW";
	public static final String AIR_ROUND_TRIP 		= "RT";
	public static final String AIR_MULTICITY_TRIP 	= "MC";
	
	/**Search Type*/
	public static final String DIRECT_FLIGHT		= "DR";
	public static final String NON_STOP 			= "NS";
	public static final String ALL_FLIGHTS 			= "AL";
	/**
	 * Action Mappings
	 */
	public static final String NO_RESULT		= "no-result";
	public static final String NO_CREDENTIALS	= "no-credentials";
	/** search fare */
	public static final int  SEARCH_SERVICE 		= 1;
	/** Fast search fare */
	public static final int  FAST_SEARCH_SERVICE 	= 2;
	/**FQCS Service*/
	public static final int  FQCS_SERVICE 			= 3;
	/** Confirm fare */
	public static final int  CONFIRM_SERVICE 		= 4;
	/** search fare */
	public static final int  UPDATE_SERVICE 		= 5;
	/** Cancel Service. */
	public static final int  CANCEL_SERVICE 		= 6;
	/** Seat Map Service */
	public static final int  SEAT_MAP_SERVICE 		= 7;
	/**terminal service.*/
	public static final int  TERMINAL_SERVICE 		= 8;
	/** rule Service.*/
	public static final int  RULE_SERVICE 			= 9;
		
	public static final String BOOKING_OPERATION	= "B";
    public static final String MODIFY_OPERATION 	= "M";
    public static final String CANCEL_OPERATION		= "C";
    
    public static final String BOOKING_CONFIRMED 	= "C";
    public static final String BOOKING_CANCELLED 	= "L";
    public static final String BOOKING_PENDING 		= "P";
	
	/**
     * Passengers type & age constants
     */
    public static final String PAX_TYPE_SENIOR 		= "SRC";
    public static final String PAX_TYPE_SENIOR_AGE 	= "75";
    
    public static final String PAX_TYPE_ADULT 		= "ADT";
    public static final String PAX_TYPE_ADULT_AGE	= "20";
    
    public static final String PAX_TYPE_CHILD 		= "CNN";
    public static final String PAX_TYPE_CHILD_UN 	= "UNN";
    public static final String PAX_TYPE_CHILD_AGE 	= "10";
    
    public static final String PAX_TYPE_INFANT 		= "INF";
    public static final String PAX_TYPE_INFANT_SEAT	= "INS";
    public static final String PAX_TYPE_INFANT_AGE	= "1";
    
    /**
     *  Galileo XML Tags
 	 */
     public static final String PLATING_CARRIER_TEXT 	= "DEFAULT PLATING CARRIER";
     public static final String PAX_TYPE_ADT_UNIQUE_KEY = "0001";
     public static final String NO_AIRPLANE_CHANGE 		= "NAC";
     public static final String LAST_PURCHASING_TICKET_DATE_TEXT = "LAST DATE TO PURCHASE TICKET:";    
	
}