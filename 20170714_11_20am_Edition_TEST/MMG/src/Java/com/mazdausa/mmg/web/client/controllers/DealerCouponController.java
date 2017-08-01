/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mazdausa.mmg.web.client.controllers;


import java.util.ArrayList;
import java.util.List;

import com.mazdausa.mmg.service.rest.response.vo.DealerCouponVO;
import com.mazdausa.mmg.web.client.response.vo.DealerCouponResponseVO;
import com.mazdausa.mmg.web.client.service.iface.DealerCouponServiceIFace;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * This Controller will be responsible for executing the functions related to Dealer Coupon.
 * @author PankajB
 * @version 1.0
 *
 */
@Controller
@Path("/dealercoupons")
public class DealerCouponController {


       /**
     * Initializing the Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DealerCouponController.class);
    @Autowired
    DealerCouponServiceIFace dealerCouponService;
   

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dealer/{dealerid}")
    public DealerCouponResponseVO getDealerCoupons(@PathParam("dealerid") String dealerId, @Context HttpServletRequest request) {

        logger.debug(" Entering {} getDealerCoupons() with DEALER ID = {}", dealerId);

        DealerCouponResponseVO dealerCouponResponseVO = null;

        // Do the Logging part here

        dealerCouponResponseVO = dealerCouponService.getDealerCouponByDealerId(dealerId, request);
        
        //13-09-2013:Update to append Coupons Exception tag data into service tag data -start 
        ArrayList<DealerCouponVO> coupons=new ArrayList<DealerCouponVO>(dealerCouponResponseVO.getCoupons().size());
        
        DealerCouponVO newVo=null;
        ArrayList<String> tempSerList=null;
        for(DealerCouponVO temp:dealerCouponResponseVO.getCoupons())
        {
        	newVo=temp;
        	
        	if(temp.getException()!=null)
        	{
        		if(temp.getServices()!=null)
        		{
                	newVo.getServices().add(temp.getServices().size(), "<br/><br/>"+temp.getException());
        		}else{
        			tempSerList=new ArrayList<String>(1);
        			tempSerList.add(temp.getException());
        			newVo.setServices(tempSerList);
        		}
        		
        	}
        	
        	coupons.add(newVo);
        }
        dealerCouponResponseVO.setCoupons(coupons);
        //13-09-2013:Update to append Coupons Exception tag data into service tag data -end 

        logger.debug("<< Exiting getDealerCoupons() with response ={}" + dealerCouponResponseVO);
        return dealerCouponResponseVO;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/dealer/{dealerid}/coupon/{couponid}")
    public DealerCouponResponseVO getDealerCouponsByCouponId(@PathParam("dealerid") String dealerId,
             @PathParam("couponid") String couponId,
             @Context HttpServletRequest request) {

        logger.debug(" Entering {} getDealerCouponsByCouponId() with DEALER ID = {}", dealerId);

        DealerCouponResponseVO dealerCouponResponseVO = null;

        // Do the Logging part here

        dealerCouponResponseVO = dealerCouponService.getDealerCouponByDealerIdAndCouponId(dealerId, couponId);

        logger.debug("<< Exiting getDealerCouponsByCouponId() with response ={}" + dealerCouponResponseVO);
        return dealerCouponResponseVO;
    }

}
