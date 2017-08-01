/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.utilities;

import com.mazdausa.mmg.business.iface.VehicleBusIFace;
import com.mazdausa.mmg.constants.ApplicationConstants;
import java.io.IOException;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;

/**
 * This Utility class is being responsible for providing a number of utilities functions.
 * @author PankajB
 * @version 1.0
 */
@Component
@Scope(value = "singleton")
public class MMGUtilities {

    private static final Logger logger = LoggerFactory.getLogger(MMGUtilities.class);
    public static ObjectMapper objMapper = new ObjectMapper();
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    VehicleBusIFace vehicleBusService;

    /**
     * This Function is an Utility function which will return a substring of an list depending upon the parameters passed.
     * @inputList inputList The Input List
     * @startFrom startFrom The Point at which the SubString function should start
     * @count Count The Number of elements to Return.
     */
    public static <T> List<T> getSubStringList(List<T> inputList, int startFrom, int count) {
        List<T> newResultList = new ArrayList<T>();
        if (inputList != null) {
            int finalEnd = inputList.size();
            logger.debug("Size of Original List is = {}", finalEnd);
            // Just Making a check,that startFrom and Count should not be less then ZERO
            if (startFrom < 0) {
                startFrom = 0;
            }
            if (count < 0) {
                count = 0;
            }

            if (count != 0) {
                if ((startFrom + count) < finalEnd) {
                    finalEnd = (startFrom + count);
                }
            }

            logger.debug("For SubString START = {} and END={}  COUNT = {}", new Object[]{startFrom, finalEnd, count});
            newResultList = inputList.subList(startFrom, finalEnd);
            logger.debug("Size of new SubList is = " + newResultList.size());

        }

        return newResultList;
    }

    /**
     * Concatenates <code>uri</code> with a query string generated from
     * <code>params</code>.
     *
     * @param uri the base URI
     * @param params a <code>Map</code> of key/value pairs
     * @return a new <code>URI</code>
     */
    public String addParametersToQuery(String url, Map<String, String> params, String... args) {
        URI uri = null;

        StringBuilder query = new StringBuilder();
        char separator = '?';
        query.append(separator);
        separator = '&';
        if (args == null || args.length == 0) {
            query.append(appConstants.SERVICE_REST_SECURITY_PARAMETER_NAME).append('=').append(appConstants.SERVICE_REST_SECURITY_PARAMETER_VALUE).append(separator);
        }
        for (Entry<String, String> param : params.entrySet()) {


            try {
                uri = new URI(url);
                query.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                if (!StringUtils.isEmpty(param.getValue())) {
                    query.append('=');
                    //if(param.getValue().toString().indexOf("@") == -1)
                    query.append(URLEncoder.encode(param.getValue(), "UTF-8"));
                    //else
                    //  query.append(param.getValue());


                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            query.append(separator);
        }
        logger.debug("QUERY IS = " + query.toString());
        String finalURI = query.toString();
        if (finalURI != null && finalURI.endsWith(separator + "")) {
            finalURI = finalURI.substring(0, finalURI.length() - 1);
        }
        return uri.toString() + finalURI.toString();
        //return uri.toString() + query.toString();
        //return URI.create(uri.toString() + query.toString()).toString();
    }

    /**
     * Concatenates <code>uri</code> with a query string generated from
     * <code>params</code>.  The members of <code>params</code> will be
     * interpreted as {key1, val1, key2, val2}.  Empty values can be given
     * as <code>""</code> or <code>null</code>.
     *
     * @param uri the base URI
     * @param params the key/value pairs in sequence
     * @return a new <code>URI</code>
     */
    public String addParametersToQuery(String uri, String... params) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (int i = 0; i < params.length; i += 2) {
            String key = params[i];
            String val = i + 1 < params.length ? params[i + 1] : "";
            map.put(key, val);
        }
        return addParametersToQuery(uri, map);
    }

    /**
     * This is the function which will be invoked whenever we need to generate the SOAP Header.
     */
    public static SoapWebServiceCustomCallback getWebServiceHeaderElement(String soapAction, ApplicationConstants applicationConstants) {
        SoapWebServiceCustomCallback webServiceCallback = new SoapWebServiceCustomCallback(soapAction, applicationConstants);
        return webServiceCallback;

    }

    /**
     * This is the function, which will return Date from a format.
     * @param date
     * @param format
     * @return
     */
    public static Date getDateFromString(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (ParseException ex) {
            logger.error("An Exception has occured, while parsing the date from String. ", ex);
        }
        return null;

    }    

    @PostConstruct
    public void afterInitialize() {
//        Thread t = new TempThread(vehicleBusService);
//        t.start();
    }

    public String changeDate(String dateToBeChanged){

            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-mm-dd");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("mm/dd/yyyy");
            String changedDate = null;
            try{
                Date parseDate = inputDateFormat.parse(dateToBeChanged);
                changedDate = outputDateFormat.format(parseDate);
            }catch(ParseException pe){
                logger.error("An exception occured while parsing the date in changeDate() = ",pe);
            }

            return changedDate;
    }
	
	public static String changeDateToDDMMYYYY(String date) {

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-mm-dd");

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/mm/yyyy");

        String rcievedExpiratationDate = date;

        String finalDate = "";

        try {

            Date parsingDate = inputDateFormat.parse(rcievedExpiratationDate);

            finalDate = outputDateFormat.format(parsingDate);

            return finalDate;

        } catch (ParseException pe) {

            logger.error("An Exception has occured, while parsing the DATE input in changeDateToDDMMYYYY(). ", pe);

        }

        return finalDate;

    }

    /*
     * This is function to modify the description if there is any chance of occurring html tag "<br />"
     * @param searchStr delimeter
     * @param aList list of description
     * @return finalDataList
     */

    public List dataToBeModify(String searchStr, List<String> aList) {
        logger.debug(">> Enter to check <br /> {} ", aList);

            List<String> finalDataList = new ArrayList<String>();
        try{
            if(aList != null){
                String[] dataToBeBreak = aList.toArray(new String[aList.size()]);
                String[] temp;

                for(String stringToBreak : dataToBeBreak){
                 temp = stringToBreak.split(searchStr);
                 for(int increment = 0;increment<temp.length;increment++){
                     finalDataList.add(temp[increment].trim());
                 }
                }
            }else{
                finalDataList = aList;
            }
        }catch(Exception ex){
            logger.error("An Exception has occurred while removing the <br /> tag {} = ", ex);
        }
        return finalDataList;
    }

    /*
     * This is function to modify the description if there is any chance of occurring html tag "<br />"
     * @param searchStr delimeter
     * @param dataToCheckForBR desclaimer string
     * @return finalData
     */

    public String dataToBeModifyForDesclaimer(String searchStr, String dataToCheckForBR) {
        logger.debug(">> Enter to dataToBeModifyForDesclaimer() to check <br /> {} ", dataToCheckForBR);

            String finalData = new String();
            List<String> listData = new ArrayList<String>();
        try{
            
            if(dataToCheckForBR != null){
                //String[] dataToBeBreak = dataToCheckForBR.toArray(new String[dataToCheckForBR.size()]);
                String[] temp;                
                temp = dataToCheckForBR.split(searchStr);
                 for(int increment = 0;increment<temp.length;increment++){
                     listData.add(temp[increment]);
                 }
                for(String stringConvert : listData){
                    finalData +=stringConvert+" ";
                }
            }else{
                finalData = dataToCheckForBR;
            }
        }catch(Exception ex){
            logger.error("An Exception has occurred while removing the <br /> tag {} = ", ex);
        }
        return finalData;
    }
    
    /*
     * This is function to encode the user based authentication for Fusion services"
     * @param user 
     * @param password  
     * @return encodedAuthString
     */
    public String getBasicAuthEncodedStringForFusionService(String user, String password)
    {
   	 String encodedAuthString = "";
   	 try{
   		 String authString = user + ":" + password;
   		 byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
   		 String authStringEnc = new String(authEncBytes);
   		 logger.debug("In getBasicAuthEncodedStringForFusion - authStringEnc = {}",authStringEnc);

   		 encodedAuthString = "Basic " + authStringEnc;
   	 }
   	 catch(Exception e){
   		 logger.error("An Exception occured while encoding auth string for fusion ", e);
   	 }
   	 	logger.debug("In getBasicAuthEncodedStringForFusion - encodedAuthString = {}",encodedAuthString);
   	 	return encodedAuthString;
    }
    
    public String generaterandomReqIdForFusion()
    {
    	SecureRandom random = new SecureRandom();
    	int num = random.nextInt(100000);
    	String formatted = String.format("%05d", num);
    	
    	String timeStamp = new SimpleDateFormat("MMddyyyyHHmmss").format(new java.util.Date());
    	
    	return timeStamp+"-"+formatted;
    }

}


/**
 * This Class is being used while generating the Web Service Message SOAP XML.
 * @author PankajB
 */
class SoapWebServiceCustomCallback implements WebServiceMessageCallback {

    private String soapAction;
    private ApplicationConstants appConstants;

    public SoapWebServiceCustomCallback(String soapAction, ApplicationConstants appConstants) {
        this.soapAction = soapAction;
        this.appConstants = appConstants;

    }

    /**
     * THis is the function will be called, while sending the Web Service Message.
     * @param webServiceMessage
     * @throws IOException
     * @throws TransformerException
     */
    public void doWithMessage(WebServiceMessage webServiceMessage) throws IOException, TransformerException {

        SoapMessage soapMessage = (SoapMessage) webServiceMessage;

        soapMessage.setSoapAction(this.soapAction);

        soapMessage.getEnvelope().addNamespaceDeclaration(appConstants.SERVICE_SOAP_REQUEST_NAMESPACE_ID, this.appConstants.SERVICE_SOAP_REQUEST_NAMESPACE);
        SoapHeader soapHeader = soapMessage.getSoapHeader();

        // Setting the Security Token and Password Header ELEMENT.

        SoapHeaderElement headerElement = soapHeader.addHeaderElement(new QName(appConstants.SERVICE_SOAP_HEADER_NAMESPACE, appConstants.SERVICE_SOAP_HEADER_SECURITYTOKEN_NAME));
        headerElement.setText(appConstants.SERVICE_SOAP_HEADER_SECURITYTOKEN_VALUE);
        headerElement.setActorOrRole(appConstants.SERVICE_SOAP_HEADER_ACTOR_NAMESPACE);
        headerElement.setMustUnderstand(false);


        headerElement = soapHeader.addHeaderElement(new QName(appConstants.SERVICE_SOAP_HEADER_NAMESPACE, appConstants.SERVICE_SOAP_HEADER_GENERATOR_NAME));
        headerElement.setText(appConstants.SERVICE_SOAP_HEADER_GENERATOR_VALUE);
        headerElement.setActorOrRole(appConstants.SERVICE_SOAP_HEADER_ACTOR_NAMESPACE);
        headerElement.setMustUnderstand(false);

        /*
         *  The Below COde is just for printing the complete XML */
        Document dm = soapMessage.getDocument();
        try {
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(dm);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
        } catch (Exception rEx) {

            System.out.println("Exception OCCURED, " + rEx.getMessage());
            rEx.printStackTrace();
        }

    }
}

class TempThread extends Thread {

    VehicleBusIFace vehicleBusService;

    public TempThread(VehicleBusIFace vehicleBusService) {
        this.vehicleBusService = vehicleBusService;
    }

    public void run() {
        while (true) {
            vehicleBusService.getAllVehicleModels();
            System.out.println("What's your use?");
            try {
                Thread.currentThread().sleep(300000);
            } catch (Exception ex) {
                System.out.println("Exception occured. ");
                ex.printStackTrace();
            }
        }
        
    }
}
