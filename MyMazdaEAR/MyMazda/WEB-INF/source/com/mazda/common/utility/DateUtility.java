package  com.mazda.common.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

/**
 * @author Pradeep.Sharma
 * @since 05-12-2012
 * @version 1.0
 * Date Utility used for various date formatting and can be reused 
 */

public class DateUtility {
	
	public static DateFormat getSimpleDateFormat(String passedDatePattern, Locale locale) {
		DateFormat dateFormat = null;

		if (passedDatePattern == null || passedDatePattern.equals("")) {
			// Set the Default format
			dateFormat = new SimpleDateFormat(CommonConstants.SYS_DATE_TIME_FORMAT);
		} else if(locale != null){
			dateFormat = new SimpleDateFormat(passedDatePattern, locale);
		}else
			dateFormat = new SimpleDateFormat(passedDatePattern);
		return dateFormat;
	}

	/**
	 * To get parsed date from specified format to date 
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0 
	 * @param strAvDateVal
	 * @param dateFormat
	 * @return
	 */
	public static Date getParsedDate(String strAvDateVal, String passedDatePattern) {
		DateFormat dateFormat	= getSimpleDateFormat(passedDatePattern, null);		
		return dateFormat.parse(strAvDateVal, new ParsePosition(0));
	}
	/**
	 * This method changes the date format of a date from one String format to
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param date
	 * @param passedDatePattern
	 * @return
	 */
	public static String convertDateToString(Date date,String passedDatePattern) {
		DateFormat dateFormat	= getSimpleDateFormat(passedDatePattern, null);
		String returnDate = null;
		try {
			returnDate = dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return returnDate;
	}
	
	/**
	 * This method changes the date format of a date from one String format to
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param date
	 * @param passedDatePattern
	 * @param returnPattern
	 * @return
	 */
	public static String convertDateToString(String date,
						String passedDatePattern, String returnPattern) {
		DateFormat pDateFormat	= getSimpleDateFormat(passedDatePattern, null);
		DateFormat rDateFormat	= getSimpleDateFormat(returnPattern, null);
		Date convertedDate 		= null;
		String returnDate 		= null;
		try {
			convertedDate 	= pDateFormat.parse(date);
			returnDate 		= rDateFormat.format(convertedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
/**
 * This method changes the date to string with paramameter like date, date format and locale
 * @author Pradeep.Sharma
 * @since 05-12-2012
 * @version 1.0
 * @param dtAvDate
 * @param strDateFormat
 * @param locale
 * @return
 */
	public static String convertDateToString(Date dtAvDate, String strDateFormat, Locale locale) {
		DateFormat dateFormat = getSimpleDateFormat(strDateFormat, locale);
		return dateFormat.format(dtAvDate);
	}
	/**
	 * This method return a system date time
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0 
	 * @return
	 */
	public static String getSysDateTime() {
		return convertDateToString(new Date(),CommonConstants.SYS_DATE_TIME_FORMAT);
	}
	/**
	 * This method returns system date
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @return
	 */
	public static String getSysDate() {
		return convertDateToString(new Date(),CommonConstants.DATE_FORMAT_SECOND);
	}

	/**
	 * This method computes the difference in days.
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @return
	 */
	public static float diffInDays(Date dtAvFrmDtVal, Date dtAvToDtVal) {
		return (new Float(dtAvToDtVal.getTime() - dtAvFrmDtVal.getTime()))
				.floatValue()
				/ (1000 * 60 * 60 * 24);
	}
/**
 * This method calculated difference in days from given to dates
 * @author Pradeep.Sharma
 * @since 05-12-2012
 * @version 1.0
 * @param dtAvFrmDtVal
 * @param dtAvToDtVal
 * @param strAvFrmDtFormat
 * @return
 */
	public static int diffInDays(String dtAvFrmDtVal, String dtAvToDtVal,
			String strAvFrmDtFormat) {
		long time1 = getParsedDate(dtAvToDtVal, strAvFrmDtFormat)
				.getTime();
		long time2 = getParsedDate(dtAvFrmDtVal, strAvFrmDtFormat)
				.getTime();
		return (int) ((time1 - time2) / (1000 * 60 * 60 * 24));
	}

	/**
	 * This method returns the Date type data from a String type date value.
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param strAvDateVal
	 * @param dateFormat
	 * @return
	 */
	public static Date getDateFrmString(String strAvDateVal, String dateFormat) {
		Date date	= getParsedDate(strAvDateVal, dateFormat);
		return date;
	}
		
	/**
	 * This method checks whether a particular date is within a date range or
	 * not. All the date formats should be same.
	 * @author Pradeep.Sharma
	 * @since v1
	 * @param strAvFirstDtVal
	 * @param strAvSecondDtVal
	 * @param strAvDtToChk
	 * @param strAvDtFormat
	 * @return
	 */
	public static boolean chkDtExistInDtRange(String strAvFirstDtVal,
			String strAvSecondDtVal, String strAvDtToChk, String strAvDtFormat) {
		Date dtLvFirstDt 	= getParsedDate(strAvFirstDtVal, strAvDtFormat);
		Date dtLvSecondDt 	= getParsedDate(strAvSecondDtVal, strAvDtFormat);
		Date dtLvDtToChk 	= getParsedDate(strAvDtToChk, strAvDtFormat);

		if ((diffInDays(dtLvFirstDt, dtLvDtToChk) >= 0.0)
				&& (diffInDays(dtLvDtToChk, dtLvSecondDt) >= 0.0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *  This method checks whether a particular date is within a date range or
	 * not.
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param dtLvFirstDt
	 * @param dtLvSecondDt
	 * @param strAvDtToChk
	 * @param strAvDtFormat
	 * @return
	 */
	public static boolean chkDtExistInDtRange(Date dtLvFirstDt,
			Date dtLvSecondDt, String strAvDtToChk, String strAvDtFormat) {
		Date dtLvDtToChk = getParsedDate(strAvDtToChk, strAvDtFormat);

		if ((diffInDays(dtLvFirstDt, dtLvDtToChk) >= 0.0)
				&& (diffInDays(dtLvDtToChk, dtLvSecondDt) >= 0.0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks whether a particular date is within a date range or
	 * not.
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param dtLvFirstDt
	 * @param dtLvSecondDt
	 * @param strAvDtToChk
	 * @return
	 */
	public static boolean chkDtExistInDtRange(Date dtLvFirstDt,
			Date dtLvSecondDt, Date strAvDtToChk) {

		if ((diffInDays(dtLvFirstDt, strAvDtToChk) >= 0.0)
				&& (diffInDays(strAvDtToChk, dtLvSecondDt) >= 0.0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks whether a date is forward or backward with respect to
	 * reference date or not.
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param strAvFirstDtTm
	 * @param strAvSecDtTm
	 * @return
	 * @throws ParseException
	 */
	public static boolean isForwardCompareDates(String strAvFirstDtTm,
			String strAvSecDtTm) throws ParseException {

		long lngLvFirstTm = getParsedDate(strAvFirstDtTm, CommonConstants.SYS_DATE_TIME_FORMAT).getTime();
		long lngLvSecDtTm = getParsedDate(strAvSecDtTm, CommonConstants.SYS_DATE_TIME_FORMAT).getTime();

		if (lngLvFirstTm > lngLvSecDtTm) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * This method adds the no of days passed as parameter to a given Date and
	 * returns the new date obtained in the specified Date Format.
	 *
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param String
	 * @param String
	 * @param int
	 * @return String
	 */
	public static String addDays(String strAvDate, String strAvFormat, int iDays) {
		//getting date
		Date dt = getParsedDate(strAvDate, strAvFormat);
		Calendar clLvCal = Calendar.getInstance();

		clLvCal.setTime(dt);
		clLvCal.add(Calendar.DATE, iDays);
		//getting & returning date as string
		return convertDateToString(clLvCal.getTime(), strAvFormat);
	}

	public static boolean isForwardCompareDates(String strAvFirstDtTm,
			String strAvSecDtTm, String strDtFormat) throws ParseException {

		long lngLvFirstTm = getParsedDate(strAvFirstDtTm, strDtFormat).getTime();
		long lngLvSecDtTm = getParsedDate(strAvSecDtTm, strDtFormat).getTime();

		if (lngLvFirstTm > lngLvSecDtTm) {
			return false;
		} else {
			return true;
		}
	}

/**
 * This method format the time with date
 * @author Pradeep.Sharma
 * @since 05-12-2012
 * @version 1.0
 * @param date
 * @param time
 * @param datePattern
 * @return
 */
	public static String formattedTime(String date, String time,
			String datePattern) {

		String formattedDate 	= "";
		String dateStr 			= "";
			while (time.length() <= 3)
				time = "0" + time;

			String end 			= (time.substring(time.length() - 2));
			String Start 		= time.substring(0, 2);
			dateStr 			= date.concat(" " + Start + ":" + end);
			// parsing the date to specific format
			Date convertedDate 	= getParsedDate(dateStr, datePattern);
			// formating a date into specific format
			formattedDate		= convertDateToString(convertedDate, CommonConstants.DATE_TIME_FORMAT);

		return formattedDate;

	}
	/**
	 *  This method changes the date format of a date from one String format to
	 * another in calendar format.currently used in TBO request
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param strAvFrmDtFormat
	 * @param strAvFrmDtVal
	 * @return
	 */
	public static Calendar chngDtToCalendar(String strAvFrmDtVal, String strAvFrmDtFormat) {
		
		Calendar calendar 	= new GregorianCalendar(TimeZone.getDefault());
		Date date			= getDateFrmString(strAvFrmDtVal, strAvFrmDtFormat);
		calendar.clear(Calendar.ZONE_OFFSET);
		calendar.clear(Calendar.MILLISECOND);
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * This method helps to get age on the basis of month, day, year
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param Month  value
	 * @param day  value
	 * @param year  value 
	 * @return age value of String
	 */
	public static String getAge(String month,String day,String year)
	{
		Calendar dob = Calendar.getInstance(); 
		dob.set( Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day) );   
		dob.setTime(dob.getTime());   
		Calendar today = Calendar.getInstance();   
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);   
		if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) 
		{
		age--;  
		}
		return String.valueOf(age);  
	}
	/**
	 * This method helps to get age on the basis of dob and date format
	 * @author Pradeep.Sharma
	 * @since v1
	 * @param Date  value
	 * @param Date  pattern value of parameter
	 * @return age value of String
	 */
	public static String getAge( String dob,String pattern)
	{
		int age=0;
		try {
			Date date = new SimpleDateFormat(pattern).parse(dob);
		    Calendar birth = new GregorianCalendar();
		    birth.setTime(date);
		    Calendar today = Calendar.getInstance();   
			 age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);   
			if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) 
			{
			age--;  
			}
			
		    

		} catch (ParseException e) {

			e.printStackTrace();
		}
		return String.valueOf(age);  
		
	}
	
	/**
	 * This method check the date
	 * @author Pradeep.Sharma
	 * @since 05-12-2012
	 * @version 1.0
	 * @param strAvDateVal
	 * @param passedDatePattern
	 * @return
	 */
		public static boolean isDate(String strAvDateVal, String passedDatePattern) {
			try {
				Date date = getParsedDate(strAvDateVal, passedDatePattern);
				if (date != null)
					return true;
			} catch (Exception e) {
			}
			return false;
		}

	/***
	 *This method  add days to date passed with format and locale
	 * @author Pradeep.Sharma
	 * @since v1
	 * @param date
	 * @param format
	 * @param locale
	 * @param noOfDaysAdd
	 * @return
	 */
		public static String addDays(Date date, String format, Locale locale,
				String noOfDaysAdd) {
				DateFormat dateFormat 	= null;
				GregorianCalendar gc 	= new GregorianCalendar();
				gc.setTime(date);
				int noOfDays 			= 0;
				if (noOfDaysAdd != null && noOfDaysAdd.length() > 0) {
					noOfDays = Integer.parseInt(noOfDaysAdd);
				}
				gc.roll(Calendar.DAY_OF_YEAR, noOfDays);
				date 		= gc.getTime();
				dateFormat 	= getSimpleDateFormat(format, locale);
			return dateFormat.format(date);
		}
		/**
		 * This method helps to get any part of date
		 * @author Pradeep.Sharma
		 * @since 05-12-2012
		 * @version 1.0
		 * @param date
		 * @param datePart
		 * @return
		 */
		public static String getSpecificDatePart(Date date, int datePart){

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			switch(datePart){
			case 1:
				return Integer.toString(cal.get(Calendar.DATE));

			case 2:
				return Integer.toString(cal.get(Calendar.MONTH)+1);

			case 3:
				return Integer.toString(cal.get(Calendar.YEAR));
			}
			return "";

		}
		/**
		 * converts search date to galileo xml date
		 * @author Pradeep.Sharma
		 * @since 31-01-2013
		 * @version 1.0
		 * @param from
		 * @return
		 */
		public static String getGalilioDate(String from) {
			StringBuffer gdate 		= new StringBuffer();
			StringTokenizer dtToken = new StringTokenizer(from, CommonConstants.HASH_CHARACTER);
			
			String day 		= dtToken.nextToken();
			String month 	= dtToken.nextToken();
			String year		= dtToken.nextToken();
			
			gdate.append(year);
			gdate.append(month);
			gdate.append(day);
			return gdate.toString();
		}
		
		/**
		 * This method format a date to galileo format date
		 * @author Pradeep.Sharma
		 * @since 05-12-2012
		 * @version 1.0
		 * @param date
		 * @param time
		 * @param datePattern
		 * @return
		 */

		public static Date getgalileoDateFrmString(String strAvDateVal,	String dateFormat) {
			String returnDateStr 	= "";
			Date returnedDate 		= null;
			// convert the date into another format
			returnDateStr	= convertDateToString(strAvDateVal, CommonConstants.GALILEO_DATE_TIME_FORMAT, dateFormat);
			//parse date to galileo format
			returnedDate 	= getParsedDate(returnDateStr, dateFormat);
			return returnedDate;
		}

		
}
