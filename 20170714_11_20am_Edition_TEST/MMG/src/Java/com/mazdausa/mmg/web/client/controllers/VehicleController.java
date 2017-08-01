/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mazdausa.mmg.web.client.controllers;

import com.mazdausa.mmg.web.client.request.vo.UserConstantDetailsRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleSetServicingDealerRequestVO;
import com.mazdausa.mmg.web.client.request.vo.UserVehicleUpdateOwnershipRequestVO;
import com.mazdausa.mmg.web.client.response.vo.ConstantDetailsResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleSetServicingDealerResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleUpdateOwnershipResponseVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeResponseOldVO;
import com.mazdausa.mmg.web.client.response.vo.UserVehicleYearCodeResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehcileRecallAlertResponseVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleExtendedInformationVO;
import com.mazdausa.mmg.web.client.response.vo.VehicleInformationResponseVO;
import com.mazdausa.mmg.web.client.service.iface.UserVehicleServiceIFace;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This is the controller which is responsible for Retrieving the vehicle Details for a particular VIN
 * @author PankajB
 * @version 1.0
 */
@Controller
@Path("/vehicle")
public class VehicleController {

    /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
    @Autowired
    UserVehicleServiceIFace vehicleService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getVehicleTitle/{custid}/{vin}")
    public Object getVehicleTitle(@PathParam("custid") String custid, @PathParam("vin") String vin, @Context HttpServletRequest request) {
        logger.debug(" Entering  getVehicleTitle() with CUSTID = {} AND VIN = {}", custid,vin);

        class VehicleTitle{
        	private String title;

			public VehicleTitle(String title) {
				this.title = title;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}
        	
        }
        
        String tempTitle = vehicleService.getVehicleTitle(custid, vin);
        VehicleTitle temp=new VehicleTitle(tempTitle);

        logger.debug("<< Exiting getVehicleTitle()" );
        return temp;
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{vin}")
    public VehicleInformationResponseVO getVehicleInformation(@PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering  getVehicleInformation() withVIN = {}", vin);
        VehicleInformationResponseVO vehicleInformationResponseVO = null;

        // Do the Logging part here
        vehicleInformationResponseVO = (VehicleInformationResponseVO) vehicleService.getVehicleInformation(vin);

        logger.debug("<< Exiting getVehicleInformation() with response ={}" + vehicleInformationResponseVO);
        return vehicleInformationResponseVO;
    }

    /**
     * This function is responsible for Retrieving the Vehicle Detailed information.
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/detail/{vin}")
    public VehicleExtendedInformationVO getVehicleDetailInformation(@PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getVehicleDetailInformation() withVIN = {}", vin);

        // Do the Logging part here
        VehicleExtendedInformationVO vehicleExtendedInformationVO = (VehicleExtendedInformationVO) vehicleService.getVehicleDetailInformation(vin);
        logger.debug("<< Exiting getVehicleDetailInformation() with response ={}" + vehicleExtendedInformationVO);
        return vehicleExtendedInformationVO;
    }

    /**
     * This is the Controller function which will handle the Recall Alert function for a particular VIN.
     * @param vin
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recallalert/{vin}")
    public VehcileRecallAlertResponseVO getVehicleRecallAlertInformation(@PathParam("vin") String vin, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getVehicleRecallAlertInformation() withVIN = {}", vin);
        VehcileRecallAlertResponseVO vehicleRecallAlertResponseVO = null;
        // Do the Logging part here
        vehicleRecallAlertResponseVO = (VehcileRecallAlertResponseVO) vehicleService.getVehicleRecallAlert(vin);
        logger.debug("<< Exiting getVehicleRecallAlertInformation() with response ={}" + vehicleRecallAlertResponseVO);
        return vehicleRecallAlertResponseVO;
    }

    /**
     * This Function will handle the Set Servicing Dealer Request from the client.
     * @param userVehicleSetServicingDealerRequestVO
     * @param vin
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{vin}/updateservicingdealer")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserVehicleSetServicingDealerResponseVO updateServicingDealerForVehicle(UserVehicleSetServicingDealerRequestVO userVehicleSetServicingDealerRequestVO,
            @PathParam("vin") String vin, @Context HttpServletRequest request, @Context HttpHeaders headers) {

        logger.debug(">> Entering {} updateServicingDealerForVehicle() with VIN = {}", vin);
        UserVehicleSetServicingDealerResponseVO userVehicleSetServicingDealerResponseVO = null;
        userVehicleSetServicingDealerRequestVO.setVin(vin);

        // Do the Logging part here
        

        userVehicleSetServicingDealerResponseVO = (UserVehicleSetServicingDealerResponseVO) vehicleService.setServicingDealerForVin(userVehicleSetServicingDealerRequestVO, headers);
        logger.debug("<< Exiting updateServicingDealerForVehicle() with response ={}" + userVehicleSetServicingDealerResponseVO);
        return userVehicleSetServicingDealerResponseVO;
    }


     /**
     * This Function will perform the update Ownership of an VEHICLE from ONE Customer to Another.
     * @param vehicleUpdateOwnershipRequestVO
     * @param userId
     * @param vin
     * @param request
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{vin}/updateownership")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserVehicleUpdateOwnershipResponseVO updateOwnership(UserVehicleUpdateOwnershipRequestVO vehicleUpdateOwnershipRequestVO,
            @PathParam("vin") String vin,
            @Context HttpServletRequest request,
            @Context HttpHeaders headers) {

        logger.debug(" Entering {} updateOwnership() for VIN ={}  ", this.getClass(), new Object[]{this.getClass(),vehicleUpdateOwnershipRequestVO.getVin()});
        UserVehicleUpdateOwnershipResponseVO userVehicleUpdateOwnershipResponseVO = null;
        vehicleUpdateOwnershipRequestVO.setVin(vin);

        // Do the Logging part here

        userVehicleUpdateOwnershipResponseVO = vehicleService.updateOwnershipForVehicle(vehicleUpdateOwnershipRequestVO, headers);

        logger.debug("<< Exiting updateOwnership() with response ={}" + userVehicleUpdateOwnershipResponseVO);
        return userVehicleUpdateOwnershipResponseVO;
    }


    /**
     * This is the function, which will be responsible for returning all the Vehicle Codes for a given particular year.
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codes")
    public UserVehicleYearCodeResponseOldVO getVehicleCodesForAllYears(@Context HttpServletRequest request) {

        logger.debug(">> Entering {} getVehicleCodesForAllYears() ", this.getClass());
        UserVehicleYearCodeResponseOldVO userVehicleYearCodeResponse = null;
        
        userVehicleYearCodeResponse=vehicleService.getVehicleCodesOld();
        
        logger.debug("<< Exiting getVehicleCodesForAllYears() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codes/{year}")
    public UserVehicleYearCodeResponseOldVO getVehicleCodesForYear(@PathParam("year") String year,@Context HttpServletRequest request) {

        logger.debug(" Entering {} getVehicleCodesForYear() for YEAR ={}  ", this.getClass(), year);
        UserVehicleYearCodeResponseOldVO userVehicleYearCodeResponse = null;
        if(year!=null)
           year=year.trim();

        userVehicleYearCodeResponse=vehicleService.getVehicleCodesOld(year);

        logger.debug("<< Exiting updateOwnership() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }

    /**
     * This is the function, which will be responsible for returning all the Vehicle Codes for a given particular year.
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codeswithtrims")
    public UserVehicleYearCodeResponseVO getVehicleCodesForAllYearsWithTrims(@Context HttpServletRequest request) {

        logger.debug(">> Entering {} getVehicleCodesForAllYears() ", this.getClass());
        UserVehicleYearCodeResponseVO userVehicleYearCodeResponse = null;
        userVehicleYearCodeResponse=vehicleService.getVehicleCodes();
        logger.debug("<< Exiting getVehicleCodesForAllYears() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/codeswithtrims/{year}")
    public UserVehicleYearCodeResponseVO getVehicleCodesForYearWithTrims(@PathParam("year") String year,@Context HttpServletRequest request) {

        logger.debug(" Entering {} getVehicleCodesForYear() for YEAR ={}  ", this.getClass(), year);
        UserVehicleYearCodeResponseVO userVehicleYearCodeResponse = null;
        if(year!=null)
           year=year.trim();

        userVehicleYearCodeResponse=vehicleService.getVehicleCodes(year);

        logger.debug("<< Exiting updateOwnership() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }
    
    /**
     * This is the function, which will be responsible for returning all the Constant detail.
     * @param request
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/constant")
    public ConstantDetailsResponseVO getConstantDetails(@Context HttpServletRequest request) {

        logger.debug(">> Entering {} getConstantDetails() ", this.getClass());
        ConstantDetailsResponseVO userVehicleYearCodeResponse = null;
        userVehicleYearCodeResponse=vehicleService.getConstant();
        logger.debug("<< Exiting getConstantDetails() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }

    /**
     * This is the function, which will be responsible for returning all the Constant detail.
     * @param request
     * @param module
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/constant/{module}")
    public ConstantDetailsResponseVO getAllConstantDetails(@PathParam("module") String module,@Context HttpServletRequest request) {

        logger.debug(" Entering {} getAllConstantDetails() for module ={}  ", this.getClass(), module);
        ConstantDetailsResponseVO userVehicleYearCodeResponse = null;
        if(module != null){
            module = module.trim();
        }
        userVehicleYearCodeResponse=vehicleService.getConstant(module);
        logger.debug("<< Exiting getConstantDetails() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }

    /**
     * This is the function, which will be responsible for returning all the Constant detail.
     * @param request
     * @param userConstantDetailsRequestVO
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/constant/{module}/device")
    @Consumes(MediaType.APPLICATION_JSON)
    public ConstantDetailsResponseVO getDeviceConstantDetails(UserConstantDetailsRequestVO userConstantDetailsRequestVO,@Context HttpServletRequest request) {

        logger.debug(" Entering {} getDeviceConstantDetails() with the value ={}  ", this.getClass(), userConstantDetailsRequestVO);
        ConstantDetailsResponseVO userVehicleYearCodeResponse = null;
        
        userVehicleYearCodeResponse=vehicleService.getConstantAndDeviceDetails(userConstantDetailsRequestVO);
        logger.debug("<< Exiting getDeviceConstantDetails() with response ={}" + userVehicleYearCodeResponse);
        return userVehicleYearCodeResponse;
    }    
}
