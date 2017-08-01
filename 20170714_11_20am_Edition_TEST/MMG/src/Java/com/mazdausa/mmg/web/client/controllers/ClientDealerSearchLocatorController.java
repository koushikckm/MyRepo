/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.service.rest.response.vo.RestDealerInfoVO;
import com.mazdausa.mmg.web.client.response.vo.DealerExpServiceVO;
import com.mazdausa.mmg.web.client.response.vo.DealerInfoResponseVO;
import com.mazdausa.mmg.web.client.response.vo.DealerSearchResponseVO;
import com.mazdausa.mmg.web.client.service.iface.DealerSearchServiceIFace;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This is the controller, which will be responsible for searching the Dealer given upon the various inputs and will return the response to the
 * client.
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/search/dealer")
public class ClientDealerSearchLocatorController {

    private static final String DEFAULT_COUNT_VALUE = "-1";
    private static final String DEFAULT_STARTFROM_VALUE = "-1";
    private static final String DEFAULT_DISTANCE_VALUE = "-1";
    private static final String DEFAULT_MAZDASPEEDONLY_VALUE = "-1";
    private static final String ERROR_MISSING_ZIPCODE_MESSAGE = "ZipCode is Mandatory. Please pass the ZipCode.";
    private static final String ERROR_MISSING_STATECITY_MESSAGE = "State & City is Mandatory. Either State & City is missing.";
    private static final String ERROR_MISSING_LONGLAT_MESSAGE = "Longitude & Latitude is Mandatory. Either Longitude & Latitude is missing.";
    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClientDealerSearchLocatorController.class);
    @Autowired
    EMMConstants EmmConstants;
    @Autowired
    ApplicationConstants AppConstants;
    @Autowired
    DealerSearchServiceIFace dealerSearchService;

    /**
     * This is the function, which will be responsible for handling the Dealer Search by Dealer Code.
     * @param request
     * @param dealerCode
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/code/{dealerCode}")
    public DealerInfoResponseVO getDealerInformation(@Context HttpServletRequest request, @PathParam("dealerCode") String dealerCode,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering {} getDealerInformation() with DealerCode={}", this.getClass(), dealerCode);
        DealerInfoResponseVO dealerInformationResponseVO = dealerSearchService.getDealerInfo(dealerCode);
        logger.debug("<< Exiting {} getDealerInformation() with result = {}", this.getClass(), dealerInformationResponseVO);

        // Do the Logging Part Here.

        return dealerInformationResponseVO;


    }

    /**
     * This Function will be responsible for performing the Limited ZIP Code Search.
     * @param request
     * @param zipCode
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zip/limited/{zipcode}")
    public DealerSearchResponseVO performLimitedDealerSearchByZip(@Context HttpServletRequest request,
            @PathParam("zipcode") String zipCode,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering {} performLimitedDealerSearch() with zipcoode={}", this.getClass(), zipCode);
        DealerSearchResponseVO dealerLimitedZipCodeSearchResultVO = dealerSearchService.dealerLimitedSearchByZipCode(zipCode);
        logger.debug("<< Exiting {} performLimitedDealerSearch() with result = {}", this.getClass(), dealerLimitedZipCodeSearchResultVO);

        
        //ExpServiceFlag update
        dealerLimitedZipCodeSearchResultVO=updateExpServiceFlag(dealerLimitedZipCodeSearchResultVO);

        return dealerLimitedZipCodeSearchResultVO;
    }

    /**
     * This Function Search the Dealer by ZIP CODE Only.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zip/{zipcode}")
    public DealerSearchResponseVO performDealerSearchByZip(@Context HttpServletRequest request,
            @PathParam("zipcode") String zipCode,
            @Context UriInfo uriInfo) {
    	
    	DealerSearchResponseVO tempRespVO=this.performDealerSearchByZip(request, zipCode, DEFAULT_DISTANCE_VALUE, uriInfo);

        //return updateExpServiceFlag(tempRespVO);
        return tempRespVO;
    }

    /**
     * This Function Search the Dealer by ZIP CODE & Distance .
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zip/{zipcode}/{distance}")
    public DealerSearchResponseVO performDealerSearchByZip(@Context HttpServletRequest request,
            @PathParam("zipcode") String zipCode,
            @PathParam("distance") String distance,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByZip(request, zipCode, distance, DEFAULT_MAZDASPEEDONLY_VALUE, uriInfo);


    }

    /**
     * This Function performs Dealer searcy by ZipCode, Distance and Mazdaspeed only.
     * @param request
     * @param zipCode
     * @param distance
     * @param mazdaspeedonly
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zip/{zipcode}/{distance}/{mazdaspeedonly}")
    public DealerSearchResponseVO performDealerSearchByZip(@Context HttpServletRequest request,
            @PathParam("zipcode") String zipCode,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByZip(request, zipCode, distance, mazdaspeedonly, DEFAULT_COUNT_VALUE, uriInfo);
    }

    /**
     * This Function Performs the Dealer Search with the pagination function.
     * @param request
     * @param zipCode
     * @param distance
     * @param mazdaspeedonly
     * @param countRequired
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zip/{zipcode}/{distance}/{mazdaspeedonly}/{count}")
    public DealerSearchResponseVO performDealerSearchByZip(@Context HttpServletRequest request,
            @PathParam("zipcode") String zipCode,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @PathParam("count") String countRequired,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByZip(request, zipCode, distance, mazdaspeedonly, countRequired, DEFAULT_DISTANCE_VALUE, uriInfo);


    }

    /**
     * This is the Function which will be responsible for performing the ZIP Code Search and will perform the pagination also.
     * @param request
     * @param zipCode
     * @param countRequired
     * @param startFrom
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zip/{zipcode}/{distance}/{mazdaspeedonly}/{count}/{startFrom}")
    public DealerSearchResponseVO performDealerSearchByZip(@Context HttpServletRequest request,
            @PathParam("zipcode") String zipCode,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @PathParam("count") String countRequired,
            @PathParam("startFrom") String startFrom,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering {} performLimitedDealerSearch() with zipcoode={}", this.getClass(), zipCode);
        DealerSearchResponseVO dealerZipCodeSearchResultVO;

        // Check whether the ZIPCODE IS MISSING OR NOT.
        if (zipCode == null || (zipCode != null && zipCode.trim().length() == 0)) {
            // Here it means the ZIPCode is not being passed in the Request, which is Mandatory.
            dealerZipCodeSearchResultVO = new DealerSearchResponseVO();
            dealerZipCodeSearchResultVO.setStatus(EmmConstants.EMM_REQUEST_SUCCESS);
            dealerZipCodeSearchResultVO.setDescription(ERROR_MISSING_ZIPCODE_MESSAGE);
            return dealerZipCodeSearchResultVO;
        }

        if (!(countRequired != null && countRequired.trim().length() != 0)) {
            countRequired = DEFAULT_COUNT_VALUE;
        }
        if (!(startFrom != null && startFrom.trim().length() != 0)) {
            startFrom = DEFAULT_STARTFROM_VALUE;
        }
        if (!(distance != null && distance.trim().length() != 0)) {
            distance = DEFAULT_DISTANCE_VALUE;
        }
        if (!(mazdaspeedonly != null && mazdaspeedonly.trim().length() != 0)) {
            mazdaspeedonly = DEFAULT_MAZDASPEEDONLY_VALUE;
        }

        dealerZipCodeSearchResultVO = dealerSearchService.dealerSearchByZipCode(zipCode, countRequired, startFrom, distance, mazdaspeedonly);
        logger.debug("<< Exiting {} performLimitedDealerSearch() with result = {}", this.getClass(), dealerZipCodeSearchResultVO);

        // Do the Loggin Part Here.

        return updateExpServiceFlag(dealerZipCodeSearchResultVO);
    }

    /*************************** Now We are Going to Search the Dealer by State,City.*******************************/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/statecity/{state}/{city}")
    public DealerSearchResponseVO performDealerSearchByStateCity(@Context HttpServletRequest request,
            @PathParam("state") String state,
            @PathParam("city") String city,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByStateCity(request, state, city, DEFAULT_DISTANCE_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/statecity/{state}/{city}/{distance}")
    public DealerSearchResponseVO performDealerSearchByStateCity(@Context HttpServletRequest request,
            @PathParam("state") String state,
            @PathParam("city") String city,
            @PathParam("distance") String distance,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByStateCity(request, state, city, distance, DEFAULT_MAZDASPEEDONLY_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/statecity/{state}/{city}/{distance}/{mazdaspeedonly}")
    public DealerSearchResponseVO performDealerSearchByStateCity(@Context HttpServletRequest request,
            @PathParam("state") String state,
            @PathParam("city") String city,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByStateCity(request, state, city, distance, mazdaspeedonly, DEFAULT_COUNT_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/statecity/{state}/{city}/{distance}/{mazdaspeedonly}/{count}")
    public DealerSearchResponseVO performDealerSearchByStateCity(@Context HttpServletRequest request,
            @PathParam("state") String state,
            @PathParam("city") String city,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @PathParam("count") String countRequired,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByStateCity(request, state, city, distance, mazdaspeedonly, countRequired, DEFAULT_STARTFROM_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/statecity/{state}/{city}/{distance}/{mazdaspeedonly}/{count}/{startFrom}")
    public DealerSearchResponseVO performDealerSearchByStateCity(@Context HttpServletRequest request,
            @PathParam("state") String state,
            @PathParam("city") String city,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @PathParam("count") String countRequired,
            @PathParam("startFrom") String startFrom,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering {} performDealerSearchByStateCity() with State={} and City = {}", new Object[]{this.getClass(), state, city});
        DealerSearchResponseVO dealerStateCitySearchResultVO;

        // Check whether the State City IS MISSING OR NOT.
        if ((state == null || (state != null && state.trim().length() == 0)) || (city == null || (city != null && city.trim().length() == 0))) {
            // This Means that the State and City is not present int the Request.
            dealerStateCitySearchResultVO = new DealerSearchResponseVO();
            dealerStateCitySearchResultVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            dealerStateCitySearchResultVO.setDescription(ERROR_MISSING_STATECITY_MESSAGE);
            return dealerStateCitySearchResultVO;
        }

        if (!(countRequired != null && countRequired.trim().length() != 0)) {
            countRequired = DEFAULT_COUNT_VALUE;
        }
        if (!(startFrom != null && startFrom.trim().length() != 0)) {
            startFrom = DEFAULT_STARTFROM_VALUE;
        }
        if (!(distance != null && distance.trim().length() != 0)) {
            distance = DEFAULT_DISTANCE_VALUE;
        }
        if (!(mazdaspeedonly != null && mazdaspeedonly.trim().length() != 0)) {
            mazdaspeedonly = DEFAULT_MAZDASPEEDONLY_VALUE;
        }

        dealerStateCitySearchResultVO = dealerSearchService.dealerSearchByStateCity(state, city, countRequired, startFrom, distance, mazdaspeedonly);
        logger.debug("<< Exiting {} performDealerSearchByStateCity() with result = {}", this.getClass(), dealerStateCitySearchResultVO);

        //ExpServiceFlag update
        dealerStateCitySearchResultVO=updateExpServiceFlag(dealerStateCitySearchResultVO);

        return dealerStateCitySearchResultVO;
    }

    /**
     *  **************** BELOW IS A LIST OF SERVICES that are being used to search for the DEALER.
     */
    /**
     * This Function is responsible for searching dealer by
     * @param request
     * @param dealerName
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public DealerSearchResponseVO performDealerSearchByName(@Context HttpServletRequest request,
            @PathParam("name") String dealerName,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByName(request, dealerName, DEFAULT_COUNT_VALUE, DEFAULT_STARTFROM_VALUE, uriInfo);
    }

    /**
     * This Function is responsible for searching a dealer by DealerName
     * @param request
     * @param dealerName Dealer Name
     * @param count Count
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}/{count}")
    public DealerSearchResponseVO performDealerSearchByName(@Context HttpServletRequest request,
            @PathParam("name") String dealerName,
            @PathParam("count") String count,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByName(request, dealerName, count, DEFAULT_STARTFROM_VALUE, uriInfo);
    }

    /**
     * This Function is responsible for searching the Dealer by Dealer Name.
     * @param request
     * @param dealerName Dealer Name
     * @param count Count - Number of record required
     * @param startFrom Starting Range.
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}/{count}/{startfrom}")
    public DealerSearchResponseVO performDealerSearchByName(@Context HttpServletRequest request,
            @PathParam("name") String dealerName,
            @PathParam("count") String count,
            @PathParam("startfrom") String startFrom,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering {} performDealerSearchByName() with name={}", this.getClass(), dealerName);
        DealerSearchResponseVO dealerLimitedZipCodeSearchResultVO = dealerSearchService.dealerSearchByName(dealerName, count, startFrom);
        logger.debug("<< Exiting {} performDealerSearchByName() with result = {}", this.getClass(), dealerLimitedZipCodeSearchResultVO);

        //ExpServiceFlag update
        dealerLimitedZipCodeSearchResultVO=updateExpServiceFlag(dealerLimitedZipCodeSearchResultVO);


        return dealerLimitedZipCodeSearchResultVO;
    }

    /*************************** Now Here We are Going to Search the Dealer by Longitude,Latitude.*******************************/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/longlat/{longitude}/{latitude}")
    public DealerSearchResponseVO performDealerSearchByLongLat(@Context HttpServletRequest request,
            @PathParam("longitude") String longitude,
            @PathParam("latitude") String latitude,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByLongLat(request, longitude, latitude, DEFAULT_DISTANCE_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/longlat/{longitude}/{latitude}/{distance}")
    public DealerSearchResponseVO performDealerSearchByLongLat(@Context HttpServletRequest request,
            @PathParam("longitude") String longitude,
            @PathParam("latitude") String latitude,
            @PathParam("distance") String distance,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByLongLat(request, longitude, latitude, distance, DEFAULT_MAZDASPEEDONLY_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/longlat/{longitude}/{latitude}/{distance}/{mazdaspeedonly}")
    public DealerSearchResponseVO performDealerSearchByLongLat(@Context HttpServletRequest request,
            @PathParam("longitude") String longitude,
            @PathParam("latitude") String latitude,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByLongLat(request, longitude, latitude, distance, mazdaspeedonly, DEFAULT_COUNT_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/longlat/{longitude}/{latitude}/{distance}/{mazdaspeedonly}/{count}")
    public DealerSearchResponseVO performDealerSearchByLongLat(@Context HttpServletRequest request,
            @PathParam("longitude") String longitude,
            @PathParam("latitude") String latitude,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @PathParam("count") String countRequired,
            @Context UriInfo uriInfo) {

        return this.performDealerSearchByLongLat(request, longitude, latitude, distance, mazdaspeedonly, countRequired, DEFAULT_STARTFROM_VALUE, uriInfo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/longlat/{longitude}/{latitude}/{distance}/{mazdaspeedonly}/{count}/{startFrom}")
    public DealerSearchResponseVO performDealerSearchByLongLat(@Context HttpServletRequest request,
            @PathParam("longitude") String longitude,
            @PathParam("latitude") String latitude,
            @PathParam("distance") String distance,
            @PathParam("mazdaspeedonly") String mazdaspeedonly,
            @PathParam("count") String countRequired,
            @PathParam("startFrom") String startFrom,
            @Context UriInfo uriInfo) {

        logger.debug(">> Entering {} performDealerSearchByLongLat() with Longitude={} and Latitude = {}", new Object[]{this.getClass(), longitude, latitude});
        DealerSearchResponseVO dealerStateCitySearchResultVO;

        // Check whether the Longitude,Latitude IS MISSING OR NOT.
        if ((longitude == null || (longitude != null && longitude.trim().length() == 0)) || (latitude == null || (latitude != null && latitude.trim().length() == 0))) {
            // This Means that the Longitude & Latitude is not present int the Request.
            dealerStateCitySearchResultVO = new DealerSearchResponseVO();
            dealerStateCitySearchResultVO.setStatus(EmmConstants.EMM_REQUEST_ERROR);
            dealerStateCitySearchResultVO.setDescription(ERROR_MISSING_LONGLAT_MESSAGE);
            return dealerStateCitySearchResultVO;
        }

        if (!(countRequired != null && countRequired.trim().length() != 0)) {
            countRequired = DEFAULT_COUNT_VALUE;
        }
        if (!(startFrom != null && startFrom.trim().length() != 0)) {
            startFrom = DEFAULT_STARTFROM_VALUE;
        }
        if (!(distance != null && distance.trim().length() != 0)) {
            distance = DEFAULT_DISTANCE_VALUE;
        }
        if (!(mazdaspeedonly != null && mazdaspeedonly.trim().length() != 0)) {
            mazdaspeedonly = DEFAULT_MAZDASPEEDONLY_VALUE;
        }

        dealerStateCitySearchResultVO = dealerSearchService.dealerSearchByLongLat(longitude, latitude, countRequired, startFrom, distance, mazdaspeedonly);
        logger.debug("<< Exiting {} performDealerSearchByLongLat() with result = {}", this.getClass(), dealerStateCitySearchResultVO);

        //ExpServiceFlag update
        dealerStateCitySearchResultVO=updateExpServiceFlag(dealerStateCitySearchResultVO);

        return dealerStateCitySearchResultVO;
    }

    
    /**
     * Fetch express service popup details
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/expressservice")
    public DealerExpServiceVO getExpressServiceDetails(@Context HttpServletRequest request) {
    	
    	StringBuffer tempUrl=new StringBuffer(request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getContextPath()))).append(request.getContextPath()).append("/");
    	String baseUrl=tempUrl.toString();
    	logger.debug("Base Address >  "+baseUrl);
    	
    	DealerExpServiceVO expSerVo=dealerSearchService.getExpressServiceDetails(baseUrl);
    	
    return expSerVo;	
    }
    
    
    /**
     * this function is used to update expServiceFlag commonly in all the dealer search services responses.
     * Search usage by '//ExpServiceFlag update' keyword in the functions where it has used.
     * @param DealerSearchResponseVO
     * @return DealerSearchResponseVO
     */
    private DealerSearchResponseVO updateExpServiceFlag(DealerSearchResponseVO tempvo){

    	DealerSearchResponseVO dealerResponseVO = tempvo;

        //test setExpServiceFlag start
        try{
        	
        List<RestDealerInfoVO> dealers=new ArrayList<RestDealerInfoVO>(dealerResponseVO.getDealers().size());        
        
        for(RestDealerInfoVO tempDealer:dealerResponseVO.getDealers())
        {
        	Date date1=new Date();
        	DealerInfoResponseVO dealerInformationResponseVO = dealerSearchService.getDealerInfo(tempDealer.getId());
        	Date date2=new Date();

        	long timeDiff=date2.getTime()-date1.getTime();
        	tempDealer.setExpServiceFlag(dealerInformationResponseVO.getDealer().getExpServiceFlag());
        	tempDealer.setTimeDiff(String.valueOf(timeDiff));
        	dealers.add(tempDealer);
        	
        }
        dealerResponseVO.setDealers(dealers);
        }catch(Exception e){logger.error("Error#updateExpServiceFlag "+e.getMessage());
        e.printStackTrace();
        }
        //test setExpServiceFlag end    	
    	
        return dealerResponseVO;
    }
    
    
}
