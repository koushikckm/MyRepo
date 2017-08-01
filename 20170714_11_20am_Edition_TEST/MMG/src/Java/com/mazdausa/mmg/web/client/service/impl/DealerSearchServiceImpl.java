/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.service.impl;


import java.util.ArrayList;

import javax.sql.DataSource;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.iface.RestDealerSearchLocatorIFace;
import com.mazdausa.mmg.service.rest.request.vo.RestDealerSearchLocatorRequestVO;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerSearchResultVO;
import com.mazdausa.mmg.service.soap.iface.DealerWebServiceIFace;
import com.mazdausa.mmg.service.soap.request.vo.SOAPDealerInfoRequestVO;
import com.mazdausa.mmg.service.soap.response.vo.DealerDetailInfoResponse;
import com.mazdausa.mmg.service.soap.response.vo.SOAPDealerInfoResponseVO;
import com.mazdausa.mmg.web.client.response.vo.DealerExpServiceVO;
import com.mazdausa.mmg.web.client.response.vo.DealerInfoResponseVO;
import com.mazdausa.mmg.web.client.response.vo.DealerSearchResponseVO;
import com.mazdausa.mmg.web.client.response.vo.KeyValuePairVO;
import com.mazdausa.mmg.web.client.service.iface.DealerSearchServiceIFace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the service layer implementation of DealerSerachServiceIFace
 * @author PankajB
 * @version 1.0
 */
@Service
public class DealerSearchServiceImpl implements DealerSearchServiceIFace {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DealerSearchServiceImpl.class);

    @Autowired
    RestDealerSearchLocatorIFace dealerSearchLocator;
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    DealerWebServiceIFace dealerWebService;
    @Autowired
    ApplicationConstants appConstants;

    /**
     * This is the service layer function, which will be responsible for performing the search at the service Layer and bring the result.
     * @param zipCode ZipCode of the area
     * @return
     */
    public DealerSearchResponseVO dealerLimitedSearchByZipCode(String zipCode) {
        logger.debug(">> Entering dealerLimitedSearchByZipCode() with zipCode=" + zipCode);
        DealerSearchResponseVO dealerLimitedSearchResultVO = new DealerSearchResponseVO();
        RestDealerSearchLocatorRequestVO dealerSearchLocatorRequestVO = new RestDealerSearchLocatorRequestVO();
        dealerSearchLocatorRequestVO.setZipCode(zipCode);
        RestDealerSearchResultVO dealerSearchResultVO = dealerSearchLocator.performLimitedDealerSearch(dealerSearchLocatorRequestVO);
        if (dealerSearchResultVO != null) {
            dealerLimitedSearchResultVO.setDealers(dealerSearchResultVO.getDealers());
            dealerLimitedSearchResultVO.setSearchcount(dealerSearchResultVO.getDealerCount());
            dealerLimitedSearchResultVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {
            dealerLimitedSearchResultVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            dealerLimitedSearchResultVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            /*dealerLimitedSearchResultVO.setErrorResponseVO(dealerSearchResultVO.getErrorResponseVO());
            dealerLimitedSearchResultVO.setDescription(dealerSearchResultVO.getErrorResponseVO().getValue());*/
        }

        logger.debug("<< Exiting dealerLimitedSearchByZipCode() with result = {}", dealerLimitedSearchResultVO);

        return dealerLimitedSearchResultVO;
    }

    /**
     * This Function will make the pagination search of DealerCode by ZIPCODE.
     * @param zipCode
     * @param count
     * @param startFrom
     * @return
     */
    public DealerSearchResponseVO dealerSearchByZipCode(String zipCode, String count, String startFrom, String distance, String mazdaSpeedOnly) {
        logger.debug(">> Entering dealerSearchByZipCode() with zipCode=" + zipCode);
        DealerSearchResponseVO dealerSearchResponseVO = new DealerSearchResponseVO();
        RestDealerSearchLocatorRequestVO dealerSearchLocatorRequestVO = new RestDealerSearchLocatorRequestVO();
        dealerSearchLocatorRequestVO.setZipCode(zipCode);

        // Setting the Search Parameters.
        if (distance != null && distance.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setDistance(Integer.parseInt(distance.trim()));
        }
        if (mazdaSpeedOnly != null && mazdaSpeedOnly.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setMazdaSpeedOnly(Boolean.parseBoolean(mazdaSpeedOnly.trim()));
        }
        if (startFrom != null && startFrom.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setStartFrom(Integer.parseInt(startFrom.trim()));
        }
        if (count != null && count.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setCount(Integer.parseInt(count.trim()));
        }



        RestDealerSearchResultVO dealerSearchResultVO = dealerSearchLocator.performDealerSearchByZipCode(dealerSearchLocatorRequestVO);
        if (dealerSearchResultVO != null && dealerSearchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == true) {
            dealerSearchResponseVO.setDealers(dealerSearchResultVO.getDealers());
            dealerSearchResponseVO.setSearchcount(dealerSearchResultVO.getDealerCount());
            dealerSearchResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {
            dealerSearchResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            if (dealerSearchResultVO != null) {
                dealerSearchResponseVO.setErrorResponseVO(dealerSearchResultVO.getErrorResponseVO());
                dealerSearchResponseVO.setDescription(dealerSearchResultVO.getErrorResponseVO().getValue());
            } else {
                dealerSearchResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }

        }

        logger.debug("<< Exiting dealerSearchByZipCode() with result = {}", dealerSearchResponseVO);

        return dealerSearchResponseVO;
    }

    /**
     * This is the Function which is responsible for returning the Dealer Search by State and City
     * @param state State Code
     * @param city  City
     * @param count Number of Results Required
     * @param startFrom Starting Result Number
     * @param distance Distance within which the Dealer need to be searched
     * @param mazdaSpeedOnly True, if only MazdaSpeedOnly dealers are required else false.
     * @return
     */
    public DealerSearchResponseVO dealerSearchByStateCity(String state, String city, String count, String startFrom, String distance, String mazdaSpeedOnly) {
        logger.debug(">> Entering dealerSearchByStateCity() with state={} & City={}", state, city);
        DealerSearchResponseVO dealerSearchResponseVO = new DealerSearchResponseVO();
        RestDealerSearchLocatorRequestVO dealerSearchLocatorRequestVO = new RestDealerSearchLocatorRequestVO();
        // Setting the Search Main Parameters.
        dealerSearchLocatorRequestVO.setState(state);
        dealerSearchLocatorRequestVO.setCity(city);

        // Setting the Search Parameters.
        if (distance != null && distance.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setDistance(Integer.parseInt(distance.trim()));
        }
        if (mazdaSpeedOnly != null && mazdaSpeedOnly.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setMazdaSpeedOnly(Boolean.parseBoolean(mazdaSpeedOnly.trim()));
        }
        if (startFrom != null && startFrom.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setStartFrom(Integer.parseInt(startFrom.trim()));
        }
        if (count != null && count.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setCount(Integer.parseInt(count.trim()));
        }


        // Performing the Search.

        RestDealerSearchResultVO dealerSearchResultVO = dealerSearchLocator.performDealerSearchByStateCity(dealerSearchLocatorRequestVO);
        if (dealerSearchResultVO != null && dealerSearchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == true) {
            dealerSearchResponseVO.setDealers(dealerSearchResultVO.getDealers());
            dealerSearchResponseVO.setSearchcount(dealerSearchResultVO.getDealerCount());
            dealerSearchResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {
            dealerSearchResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            if (dealerSearchResultVO != null) {
                dealerSearchResponseVO.setErrorResponseVO(dealerSearchResultVO.getErrorResponseVO());
                dealerSearchResponseVO.setDescription(dealerSearchResultVO.getErrorResponseVO().getValue());
            } else {
                dealerSearchResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }


        }

        logger.debug("<< Exiting dealerSearchByStateCity() with result = {}", dealerSearchResponseVO);

        return dealerSearchResponseVO;
    }

    /**
     * This Function will responsible for searching the Dealer by Name
     * @param dealerName
     * @return
     */
    public DealerSearchResponseVO dealerSearchByName(String dealerName, String count, String startFrom) {
        logger.debug(">> Entering dealerSearchByName() with Dealer Name=" + dealerName);
        DealerSearchResponseVO dealerLimitedSearchResultVO = new DealerSearchResponseVO();
        RestDealerSearchLocatorRequestVO dealerSearchLocatorRequestVO = new RestDealerSearchLocatorRequestVO();
        dealerSearchLocatorRequestVO.setName(dealerName);

        if (startFrom != null && startFrom.trim().length() != 0 && startFrom.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setStartFrom(Integer.parseInt(startFrom.trim()));
        }
        if (count != null && count.trim().length() != 0 && count.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setCount(Integer.parseInt(count.trim()));
        }


        RestDealerSearchResultVO dealerSearchResultVO = dealerSearchLocator.performDealerSearchByName(dealerSearchLocatorRequestVO);
        if (dealerSearchResultVO != null) {
            dealerLimitedSearchResultVO.setDealers(dealerSearchResultVO.getDealers());
            dealerLimitedSearchResultVO.setSearchcount(dealerSearchResultVO.getDealerCount());
            dealerLimitedSearchResultVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {
            dealerLimitedSearchResultVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);

            if (dealerSearchResultVO != null) {
                dealerLimitedSearchResultVO.setErrorResponseVO(dealerSearchResultVO.getErrorResponseVO());
                dealerLimitedSearchResultVO.setDescription(dealerSearchResultVO.getErrorResponseVO().getValue());
            } else {
                dealerLimitedSearchResultVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);
            }

        }

        logger.debug("<< Exiting dealerSearchByName() with result = {}", dealerLimitedSearchResultVO);

        return dealerLimitedSearchResultVO;
    }

    /**
     * This is the implementation of the function, which will be responsible for searching the Dealer by Longitude & Latitude.
     * @param longtiude
     * @param latitude
     * @param count
     * @param startFrom
     * @param distance
     * @param mazdaSpeedOnly
     * @return
     */
    public DealerSearchResponseVO dealerSearchByLongLat(String longitude, String latitude, String count, String startFrom, String distance, String mazdaSpeedOnly) {
        logger.debug(">> Entering dealerSearchByLongLat() with Longitude={} and Latitude={}", longitude, latitude);
        DealerSearchResponseVO dealerSearchResponseVO = new DealerSearchResponseVO();
        RestDealerSearchLocatorRequestVO dealerSearchLocatorRequestVO = new RestDealerSearchLocatorRequestVO();
        // Setting the Search Main Parameters.
        dealerSearchLocatorRequestVO.setLongitude(longitude);
        dealerSearchLocatorRequestVO.setLatitude(latitude);

        // Setting the Search Parameters.
        if (distance != null && distance.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setDistance(Integer.parseInt(distance.trim()));
        }
        if (mazdaSpeedOnly != null && mazdaSpeedOnly.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setMazdaSpeedOnly(Boolean.parseBoolean(mazdaSpeedOnly.trim()));
        }
        if (startFrom != null && startFrom.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setStartFrom(Integer.parseInt(startFrom.trim()));
        }
        if (count != null && count.trim().equalsIgnoreCase(ApplicationConstants.PARAMETER_DEFAULT_VALUE) == false) {
            dealerSearchLocatorRequestVO.setCount(Integer.parseInt(count.trim()));
        }


        // Performing the Search.

        RestDealerSearchResultVO dealerSearchResultVO = dealerSearchLocator.performDealerSearchByLatLong(dealerSearchLocatorRequestVO);
        if (dealerSearchResultVO != null && dealerSearchResultVO.getStatus().trim().equalsIgnoreCase(ApplicationConstants.MAZDA_SERVICE_RESPONSE_OK) == true) {
            dealerSearchResponseVO.setDealers(dealerSearchResultVO.getDealers());
            dealerSearchResponseVO.setSearchcount(dealerSearchResultVO.getDealerCount());
            dealerSearchResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);

        } else {
            if (dealerSearchResultVO == null) {
                dealerSearchResultVO = new RestDealerSearchResultVO();
                dealerSearchResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);

            }
            dealerSearchResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            dealerSearchResponseVO.setErrorResponseVO(dealerSearchResultVO.getErrorResponseVO());
            if (dealerSearchResultVO.getErrorResponseVO() != null) {
                dealerSearchResponseVO.setDescription(dealerSearchResultVO.getErrorResponseVO().getValue());
            }
        }

        logger.debug("<< Exiting dealerSearchByLongLat() with result = {}", dealerSearchResponseVO);

        return dealerSearchResponseVO;
    }

    /**
     * This Function, will return the Dealer Information given the Dealer Code
     * @param zipCode
     * @return
     */
    public DealerInfoResponseVO getDealerInfo(String dealerCode) {
        logger.debug(">> Entering getDealerInfo() with Dealer Code=" + dealerCode);
        SOAPDealerInfoResponseVO dealerInfoResponseVO = new SOAPDealerInfoResponseVO();
        SOAPDealerInfoRequestVO dealerInfoRequestVO = new SOAPDealerInfoRequestVO();
        DealerInfoResponseVO dealerResponseVO = new DealerInfoResponseVO();

        dealerInfoRequestVO.setDealerCode(dealerCode);
        //dealerInfoResponseVO.setCode(dealerCode);
        dealerInfoResponseVO = dealerWebService.getDealerInfo(dealerInfoRequestVO);

        // Here a check needs to be done, that made sure, that teh error object is null, if there is a case or error occurs.
        // if (dealerInfoResponseVO != null && ( dealerInfoResponseVO.getDealer().getErrorStatus() == null || dealerInfoResponseVO.getDealer().getErrorStatus().trim().length() == 0)
        if (dealerInfoResponseVO != null) {
            // as we always need to return the success this is the case which wil be executed always.
            dealerResponseVO.setDealer(dealerInfoResponseVO.getDealer());
            dealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            
            //Added@17-01-2014 to add new alert parameter 
            dealerResponseVO.setAlert(getAlertData());

        } else if (dealerInfoResponseVO != null && dealerInfoResponseVO.getDealer().getErrorStatus() != null) {
            // This Code will never execute.
            // dealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);  This Code is being commented as asid by Pradeep on 28th July evening 6:10 pm
            dealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            dealerResponseVO.setDealer(new DealerDetailInfoResponse());
            //dealerResponseVO.setDescription(dealerInfoResponseVO.getDealer().getErrorStatus());  This is also commented because of above reason.
            
            //Added@17-01-2014 to add new alert parameter 
            dealerResponseVO.setAlert(getAlertData());
            
        } else {
            dealerResponseVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            dealerResponseVO.setDescription(EmmConstants.EMM_SERVICES_COMMUNICATION_FAILURE);

        }

        logger.debug("<< Exiting getDealerInfo() with result = {}", dealerResponseVO);
        return dealerResponseVO;
    }

	
    /**
     * This function is returning express service data
     */
	public DealerExpServiceVO getExpressServiceDetails(String baseUrl) {

		DealerExpServiceVO expSerVo=new DealerExpServiceVO();
		ArrayList<String> resourceList=new ArrayList<String>();
		
		try{
			
		//fETCH DATA CORRESPONDING TO KEY=ResourceListItem
		resourceList.add(baseUrl+appConstants.MMG_IMG_EXP_SERVICE);
		resourceList.add(baseUrl+appConstants.MMG_IMG_EXP_SERVICE_X);
		resourceList.add(baseUrl+appConstants.MMG_IMG_EXP_SERVICE_XX);
		expSerVo.setResourceList(resourceList);
		
		//fETCH DATA CORRESPONDING TO KEY=HtmlResource		
		expSerVo.setHtmlResource(appConstants.MMG_EXP_SERVICE_HTML);

		//fETCH DATA CORRESPONDING TO KEY=version		
		expSerVo.setVersion(appConstants.MMG_EXP_SERVICE_VER);
		
		}catch(Exception e){
			logger.error("getExpressServiceDetails > "+e);
			expSerVo.setStatus(e.getMessage());
		}
	
		return expSerVo;
	}
	
	
	private ArrayList<KeyValuePairVO> getAlertData(){
		ArrayList<KeyValuePairVO> list=new ArrayList<KeyValuePairVO>();
		
		try{
		String alertDataStr=appConstants.MMG_USER_MAINTENANCEREMINDER_ALERT;
		//-1#None,0#At  time of Event,5#5 min before,10#10 min before,15#15 min before,30#30 min before,60#1 hour before,120#2 hour before,1440#1 day before
		
		if(alertDataStr != null && !alertDataStr.isEmpty())
		{
			for(String temp:alertDataStr.split(","))
			{
				String[] tempArr=temp.split("#");
				KeyValuePairVO vo=new KeyValuePairVO();
				vo.setKey(tempArr[0]);
				vo.setValue(tempArr[1]);
				
				list.add(vo);
			}
		}
		}catch(Exception e){
			logger.error("Exception while getting reading alert data from config file >"+e.getMessage());
		}
		
		return list;
	}
	
	
// Express service data is coming now from properties file hence this method is not in use now.
	
//	public DealerExpServiceVO getExpressServiceDetails() {
//		// TODO Auto-generated method stub
//		DealerExpServiceVO expSerVo=new DealerExpServiceVO();
//		ArrayList<String> resourceList=new ArrayList<String>();
//		
//		Connection conn=null;
//		PreparedStatement pstmt=null;
//		ResultSet resultSet=null;
//		
//		try{
//		conn=dataSource.getConnection();
//		pstmt=conn.prepareStatement("SELECT PVALUE FROM expseritems WHERE PKEY=?");
//		
//		
//		//fETCH DATA CORRESPONDING TO KEY=ResourceListItem
//		pstmt.setString(1,"ResourceListItem");
//		resultSet=pstmt.executeQuery();
//		while(resultSet.next())
//			{
//			resourceList.add(resultSet.getString(1));
//			}
//		expSerVo.setResourceList(resourceList);
//		
//		//fETCH DATA CORRESPONDING TO KEY=HtmlResource		
//		pstmt.clearParameters();
//		pstmt.setString(1,"HtmlResource");
//		resultSet=pstmt.executeQuery();
//		while(resultSet.next())
//			{
//			expSerVo.setHtmlResource(resultSet.getString(1));
//			}
//
//		//fETCH DATA CORRESPONDING TO KEY=version		
//		pstmt.clearParameters();
//		pstmt.setString(1,"version");
//		resultSet=pstmt.executeQuery();
//		while(resultSet.next())
//			{
//			expSerVo.setVersion(resultSet.getString(1));
//			}
//		
//		
//		}catch(Exception e){
//			logger.error("getExpressServiceDetails > "+e);
//			expSerVo.setStatus(e.getMessage());
//		}
//		finally{
//		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
//		    try { pstmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
//		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
//		}		
//				
//		return expSerVo;
//	}
//	
	
	
	
}
