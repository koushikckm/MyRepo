package  com.mazda.common.utility;

import java.util.Hashtable;
import java.util.ResourceBundle;

/**
 * @author Pradeep.Sharma
 * @since 30-11-2012
 * @version 1.0
 * Class to read application resource / property files
 */
public class PropertyFileReader{
	
	public static Hashtable<String, ResourceBundle> hashtable = null;
	static ResourceBundle bundle 		= null;
	public PropertyFileReader(){	
	}
	public PropertyFileReader(String propFileName){	
		
	}
	/**
	 * This method used to get property file using name and key values
	 * @author Pradeep.Sharma
	 * @since 30-11-2012
	 * @version 1.0
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getProperty(String fileName, String key){
		String value 			= null;
		ResourceBundle bundle 	= null;
		String fileNameOriginal = fileName;
		if(hashtable == null){
			hashtable = new Hashtable<String, ResourceBundle>();
		}
		
		bundle = hashtable.get(fileName);
		if(bundle == null){
			bundle = ResourceBundle.getBundle(fileName);
			hashtable.put(fileNameOriginal, bundle);
		}
		try{
			value = bundle.getString(key);
		} catch(Exception e){
			value="";			
		}
		return value;
	}
	 
	/**
	 * @author Pradeep.Sharma
	 * @since 30-11-2012
	 * @version 1.0
	 * This function is based upon singleton design pattern which will create 
	 * 			a single object of ResourceBundle declared at object level. 
	 * @param key contains the value for which value is required
	 * @return the value from property file named mailconfig.properties
	 */
	public static String getProperty(String key){
		String value = null;
		try{
			if(bundle == null ){
				bundle = ResourceBundle.getBundle("mailConfig");
			}
			value =  bundle.getString(key);
		}catch(Exception e){
			/*//##(""+e);*/
		}
		
		return value;		
	}

}
