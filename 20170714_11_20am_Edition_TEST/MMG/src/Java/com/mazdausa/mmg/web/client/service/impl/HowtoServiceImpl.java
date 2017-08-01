package com.mazdausa.mmg.web.client.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emm.v1.constants.EMMConstants;
import com.mazdausa.mmg.constants.ApplicationConstants;
import com.mazdausa.mmg.web.client.response.vo.FilterItemVO;
import com.mazdausa.mmg.web.client.response.vo.HowToLookUpResponseVO;
import com.mazdausa.mmg.web.client.response.vo.HowToLookupDataVO;
import com.mazdausa.mmg.web.client.service.iface.HowtoServiceIFace;

import edu.emory.mathcs.backport.java.util.Arrays;


/**
 * This is the Implementation class of Service layer Interface HowtoServiceIFace.
 * @author AnilK
 *
 */

@Service
public class HowtoServiceImpl implements HowtoServiceIFace{

    private static final Logger logger = LoggerFactory.getLogger(HowtoServiceImpl.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    ApplicationConstants appConstants;
    @Autowired
    EMMConstants emmConstants;    
	    
	/**
	 * Fetch how to  listing based on Year model input
	 * @param model
	 * @param year
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItems(String model, String year) {

		logger.debug("<<<<<<<<Inside getHowtoItems Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();
		resultSet=stmt.executeQuery("SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID FROM howtolookup hlp where VEHICLECODE_INDENT in (SELECT vehiclecode_ident FROM mmgvehiclecode where vehicle_model='"+model+"' and vehicle_year='"+year+"')");

		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			ArrayList<String> filters=(ArrayList<String>) getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID"));
			datavo.setFilters(filters);
			
			lookupItems.add(datavo);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getHowtoItems > "+e);
			lookupItems=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterItems(getFilterList(year,model));
		
		return tempVo;
	}


	/**
	 * Fetch how to  listing based on Year model & trim input
	 * @param model
	 * @param year
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItemsWithFilterObjects(String model, String year, String trim) {

		logger.debug("<<<<<<<<Inside getHowtoItemsWithFilterObjects(y,m,t) Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		//String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID FROM howtolookup hlp where HOWTOLOOKUP_ID in (select t3.howtolookup_id from mmgvehiclecode vehicle inner join vehicle_trims t1 on vehicle.vehiclecode_ident=t1.vehicle_id inner join trims t2 on t1.trim_id=t2.trims_ident inner join lookup_vehicletrims t3 on t3.vehicletrims_id=t1.vehicletrim_ident where vehicle.vehicle_year='"+year+"' and vehicle.vehicle_model='"+model+"' and t2.trim_name='"+trim+"')";
		String query=null;
		if(trim.equalsIgnoreCase("all") || trim.equalsIgnoreCase("default") || trim.isEmpty())
		{
			//query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID, (select GROUP_CONCAT(tms.trim_name SEPARATOR ',') from vehicle_trims vtm inner join trims tms on tms.trims_ident=vtm.trim_id inner join lookup_vehicletrims lvts on lvts.vehicletrims_id=vtm.vehicletrim_ident where howtolookup_id=hlp.HOWTOLOOKUP_ID and tms.trim_name=tms.trim_name and tms.trim_name != 'default') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			//query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id and lower(tms.trim_name) != 'default' inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, year);
			stmt.setString(2, model);
		
		}
		else
		{	
			query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id and lower(tms.trim_name) != 'default' inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? and tms.trim_name=? group by HOWTOLOOKUP_ID";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, year);
			stmt.setString(2, model);
			stmt.setString(3, trim);
		}
		
		resultSet=stmt.executeQuery();
		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			
			String trims=resultSet.getString("TRIMNAME");
			//Setting trims list
			if(trims!=null)
			{	
			HashSet<String> list=new HashSet(Arrays.asList(trims.split(",")));
			list.remove("default");
			datavo.setTrimNames(new ArrayList(list));
			}
			else
			{
			datavo.setTrimNames(new ArrayList<String>());	
			}
			
			//Setting internal filters IDs
			//datavo.setFilterIds( getFilterIdsListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID")));
			
			//Setting internal filters names
			datavo.setFilters(getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID")));
			
			
			/*
			 * Note - One of above either filterIds list or filterNames list would be removed.
			 * 
			 */
			
			lookupItems.add(datavo);
			}
		
		//setting vehicle image url
		String imgName=this.getVehicleImgeUrl(year, model);
		if(imgName!=null && !imgName.isEmpty())
		tempVo.setImageUrl(appConstants.MMG_VEHICLE_BASEURL_IMG+"/"+imgName);
		else
		tempVo.setImageUrl("");

		}catch(Exception e){
			logger.error(" Problem -  getHowtoItemsWithFilterObjects(y,m,t) > "+e);
			lookupItems=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterObjects(getAllFilterObjectsList());
		
		return tempVo;
	}


	/*
	 * Fetch how to  listing based on Year model & trim input based on user agent of device.
	 * @param model
	 * @param year
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItemsWithFilterObjects(String model, String year, String trim,String userAgent) {

		logger.debug("<<<<<<<<Inside getHowtoItemsWithFilterObjects(y,m,t,ua) Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		//String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID FROM howtolookup hlp where HOWTOLOOKUP_ID in (select t3.howtolookup_id from mmgvehiclecode vehicle inner join vehicle_trims t1 on vehicle.vehiclecode_ident=t1.vehicle_id inner join trims t2 on t1.trim_id=t2.trims_ident inner join lookup_vehicletrims t3 on t3.vehicletrims_id=t1.vehicletrim_ident where vehicle.vehicle_year='"+year+"' and vehicle.vehicle_model='"+model+"' and t2.trim_name='"+trim+"')";
		String query=null;
		if(trim.equalsIgnoreCase("all") || trim.equalsIgnoreCase("default") || trim.isEmpty())
		{
			//query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID, (select GROUP_CONCAT(tms.trim_name SEPARATOR ',') from vehicle_trims vtm inner join trims tms on tms.trims_ident=vtm.trim_id inner join lookup_vehicletrims lvts on lvts.vehicletrims_id=vtm.vehicletrim_ident where howtolookup_id=hlp.HOWTOLOOKUP_ID and tms.trim_name=tms.trim_name and tms.trim_name != 'default') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			//query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id and lower(tms.trim_name) != 'default' inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, year);
			stmt.setString(2, model);
		
		}
		else
		{	
			query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id and lower(tms.trim_name) != 'default' inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? and tms.trim_name=? group by HOWTOLOOKUP_ID";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, year);
			stmt.setString(2, model);
			stmt.setString(3, trim);
		}
		
		resultSet=stmt.executeQuery();
		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			
			String trims=resultSet.getString("TRIMNAME");
			//Setting trims list
			if(trims!=null)
			{	
			HashSet<String> list=new HashSet(Arrays.asList(trims.split(",")));
			list.remove("default");
			datavo.setTrimNames(new ArrayList(list));
			}
			else
			{
			datavo.setTrimNames(new ArrayList<String>());	
			}
			
			//Setting internal filters IDs
			//datavo.setFilterIds( getFilterIdsListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID")));
			
			//Setting internal filters names
			datavo.setFilters(getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID")));
			
			
			/*
			 * Note - One of above either filterIds list or filterNames list would be removed.
			 * 
			 */
			
			lookupItems.add(datavo);
			}
		
		//setting vehicle image url
				String imgName=this.getVehicleImgeUrl(year, model);
				if(imgName!=null && !imgName.isEmpty())
				{
				tempVo.setImageUrl(getVehicleCoverImageBasePathByUserAgent(userAgent)+"/"+imgName);
				}
				else
				{
				tempVo.setImageUrl("");
				}

		}catch(Exception e){
			logger.error(" Problem -  getHowtoItemsWithFilterObjects(y,m,t) > "+e);
			lookupItems=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterObjects(getAllFilterObjectsList());
		
		return tempVo;
	}
	
	
	
	private String getVehicleCoverImageBasePathByUserAgent(String userAgentOfDevice)
	{
		System.out.println(userAgentOfDevice);
		
		String imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG;
		
	
		if(userAgentOfDevice != null)
		{
			if(userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_BROWSER))
			{	
			//For Browser
				if(userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_ANDROID))
				{
		    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB_ANDROID;
		    		
		        }else if(userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPHONE))
		        {
		    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB_IPHONE;
		    		
		        }else if(userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPAD))
		        {
		    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB_IPAD;
		    		
		    	}else{
		    		
		    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB;
		        }		
			}else{
			//For Native
				if(userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_ANDROID))
				{
		    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG;
		    		
		        }else if(userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPHONE_NEW))
		        {
		    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG;
		    		
		        }			
			}
		}
		
		
		return imgBaseUrl;
	}
	
	
	private String getVehicleCoverImageBasePathByUserAgent_x(String userAgentOfDevice)
	{
		String imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG;
		
		if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_ANDROID))
		{
    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB_ANDROID;
    		
        }else if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPHONE))
        {
    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB_IPHONE;
    		
        }else if(userAgentOfDevice != null && userAgentOfDevice.trim().toLowerCase().contains(emmConstants.EMM_USER_AGENT_TYPE_IPAD))
        {
    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB_IPAD;
    		
    	}else{
    		
    		imgBaseUrl=appConstants.MMG_VEHICLE_BASEURL_IMG_WEB;
        }		
		
		return imgBaseUrl;
	}
	
	
	/**
	 * Fetch how to  listing based on Year model & trim input
	 * @param model
	 * @param year
	 * @return
	 */
	public HowToLookUpResponseVO getHowtoItems(String model, String year, String trim) {

		logger.debug("<<<<<<<<Inside getHowtoItems(y,m,t) Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();

		//String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID FROM howtolookup hlp where HOWTOLOOKUP_ID in (select t3.howtolookup_id from mmgvehiclecode vehicle inner join vehicle_trims t1 on vehicle.vehiclecode_ident=t1.vehicle_id inner join trims t2 on t1.trim_id=t2.trims_ident inner join lookup_vehicletrims t3 on t3.vehicletrims_id=t1.vehicletrim_ident where vehicle.vehicle_year='"+year+"' and vehicle.vehicle_model='"+model+"' and t2.trim_name='"+trim+"')";
		String query=null;
		if(trim.equalsIgnoreCase("all") || trim.equalsIgnoreCase("default") || trim.isEmpty())
		{
			//query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID, (select GROUP_CONCAT(tms.trim_name SEPARATOR ',') from vehicle_trims vtm inner join trims tms on tms.trims_ident=vtm.trim_id inner join lookup_vehicletrims lvts on lvts.vehicletrims_id=vtm.vehicletrim_ident where howtolookup_id=hlp.HOWTOLOOKUP_ID and tms.trim_name=tms.trim_name and tms.trim_name != 'default') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			//query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id and lower(tms.trim_name) != 'default' inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? group by HOWTOLOOKUP_ID";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, year);
			stmt.setString(2, model);
		
		}
		else
		{	
			query="SELECT hlp.HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE,THUMBURL, CONTENTID, GROUP_CONCAT(distinct tms.trim_name SEPARATOR ',') as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id and lower(tms.trim_name) != 'default' inner join lookup_vehicletrims lvt on lvt.howtolookup_id=hlp.HOWTOLOOKUP_ID and lvt.vehicletrims_id=vt.vehicletrim_ident where vehicle.vehicle_year=? and vehicle.vehicle_model=? and tms.trim_name=? group by HOWTOLOOKUP_ID";
			stmt=conn.prepareStatement(query);
			stmt.setString(1, year);
			stmt.setString(2, model);
			stmt.setString(3, trim);
		}
		
		resultSet=stmt.executeQuery();
		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			
			String trims=resultSet.getString("TRIMNAME");
			//Setting trims list
			if(trims!=null)
			{	
			HashSet<String> list=new HashSet(Arrays.asList(trims.split(",")));
			list.remove("default");
			datavo.setTrimNames(new ArrayList(list));
			}
			else
			{
			datavo.setTrimNames(new ArrayList<String>());	
			}
			
			ArrayList<String> filters=(ArrayList<String>) getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID"));
			datavo.setFilters(filters);
			
			lookupItems.add(datavo);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getHowtoItems(y,m,t) > "+e);
			lookupItems=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterItems(getAllFilterList());
		
		return tempVo;
	}

	
	/**
	 * Fetch how to items listing based on search string
	 * 
	 * @param searchStr
	 * @return HowToLookUpResponseVO
	 */
	public HowToLookUpResponseVO getHowToSearchItems(String searchStr) {

		logger.debug("<<<<<<<<Inside getHowToSearchItems Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		//stmt=conn.createStatement();
		String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID FROM howtolookup hlp where HOWTOLOOKUP_ID in ( SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=2 and CONTENTID in (SELECT VIDEODETAILS_ID FROM videodetails where concat(DETAILS,TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=1 and CONTENTID in (SELECT PDFDETAILS_ID FROM pdfdetails where concat(TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=3 and CONTENTID in (SELECT TEXTDETAILS_ID FROM textdetails where concat(TITLE,DETAILS,RESOURCE_METADATA) like ?))";
		String temp="%"+searchStr+"%";
		PreparedStatement pstmt=conn.prepareStatement(query);
		pstmt.setString(1, temp);
		pstmt.setString(2, temp);
		pstmt.setString(3, temp);
		
		resultSet=pstmt.executeQuery();

		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			ArrayList<String> filters=(ArrayList<String>) getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID"));
			datavo.setFilters(filters);
			
			lookupItems.add(datavo);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getHowToSearchItems > "+e);
			lookupItems=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterItems(getAllFilterList());
		
		return tempVo;
	}
	
	
	/**
	 * Fetch how to items listing based on search string,Year & model
	 * 
	 * @param year
	 * @param model
	 * @param searchStr
	 * @return HowToLookUpResponseVO
	 */
	public HowToLookUpResponseVO getHowToSearchItemsWithFilterObjects(String year,String model,String searchStr) {

		logger.debug("<<<<<<<<Inside getHowToSearchItems(y/m/sq) Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		
		//String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID,tms.trim_name as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident and vehicle.vehicle_year=? and vehicle.vehicle_model=? inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where hlp.HOWTOLOOKUP_ID in ( SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=2 and CONTENTID in (SELECT VIDEODETAILS_ID FROM videodetails where concat(DETAILS,TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=1 and CONTENTID in (SELECT PDFDETAILS_ID FROM pdfdetails where concat(TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=3 and CONTENTID in (SELECT TEXTDETAILS_ID FROM textdetails where concat(TITLE,DETAILS,RESOURCE_METADATA) like ?))";
		//updated to get all mapped trims comma separated
		String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID,(select GROUP_CONCAT(tms.trim_name SEPARATOR ',') from vehicle_trims vtm inner join trims tms on tms.trims_ident=vtm.trim_id inner join lookup_vehicletrims lvts on lvts.vehicletrims_id=vtm.vehicletrim_ident where howtolookup_id=hlp.HOWTOLOOKUP_ID and tms.trim_name=tms.trim_name and tms.trim_name != 'default')as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident and vehicle.vehicle_year=? and vehicle.vehicle_model=? inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where hlp.HOWTOLOOKUP_ID in ( SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=2 and CONTENTID in (SELECT VIDEODETAILS_ID FROM videodetails where concat(DETAILS,TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=1 and CONTENTID in (SELECT PDFDETAILS_ID FROM pdfdetails where concat(TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=3 and CONTENTID in (SELECT TEXTDETAILS_ID FROM textdetails where concat(TITLE,DETAILS,RESOURCE_METADATA) like ?)) and tms.trim_name != 'default' group by HOWTOLOOKUP_ID";
		String temp="%"+searchStr+"%";
		PreparedStatement pstmt=conn.prepareStatement(query);
		pstmt.setString(1, year);
		pstmt.setString(2, model);
		pstmt.setString(3, temp);
		pstmt.setString(4, temp);
		pstmt.setString(5, temp);
		
		resultSet=pstmt.executeQuery();

		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			String trims=resultSet.getString("TRIMNAME");
			//Setting trims list
			if(trims!=null)
			datavo.setTrimNames(Arrays.asList(trims.split(",")));
			else
			datavo.setTrimNames(new ArrayList<String>());
			
			//Setting internal filters IDs
			//datavo.setFilterIds( getFilterIdsListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID")));
			
			//Setting internal filters names
			datavo.setFilters(getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID")));
			
			/*
			 * Note - One of above either filterIds list or filterNames list would be removed.
			 * 
			 */
			
			lookupItems.add(datavo);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getHowToSearchItemsWithFilterObjects(y/m/sq) > "+e);
			lookupItems=null;
			tempVo.setStatus("fail");
			tempVo.setError(e.getMessage());
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterObjects(getAllFilterObjectsList());
		
		return tempVo;
	}

	
	/**
	 * Fetch how to items listing based on search string,Year & model
	 * 
	 * @param year
	 * @param model
	 * @param searchStr
	 * @return HowToLookUpResponseVO
	 */
	public HowToLookUpResponseVO getHowToSearchItems(String year,String model,String searchStr) {

		logger.debug("<<<<<<<<Inside getHowToSearchItems(y/m/sq) Service");
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();
		
		ArrayList<HowToLookupDataVO> lookupItems=new ArrayList<HowToLookupDataVO>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		
		//String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID,tms.trim_name as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident and vehicle.vehicle_year=? and vehicle.vehicle_model=? inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where hlp.HOWTOLOOKUP_ID in ( SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=2 and CONTENTID in (SELECT VIDEODETAILS_ID FROM videodetails where concat(DETAILS,TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=1 and CONTENTID in (SELECT PDFDETAILS_ID FROM pdfdetails where concat(TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=3 and CONTENTID in (SELECT TEXTDETAILS_ID FROM textdetails where concat(TITLE,DETAILS,RESOURCE_METADATA) like ?))";
		//updated to get all mapped trims comma separated
		String query="SELECT HOWTOLOOKUP_ID,TITLE, DESCRIPTION,(SELECT CONTENTTYPE FROM contenttype where CONTENT_ID=hlp.IDCONTENTTYPE) as CONTENTTYPE, THUMBURL, CONTENTID,(select GROUP_CONCAT(tms.trim_name SEPARATOR ',') from vehicle_trims vtm inner join trims tms on tms.trims_ident=vtm.trim_id inner join lookup_vehicletrims lvts on lvts.vehicletrims_id=vtm.vehicletrim_ident where howtolookup_id=hlp.HOWTOLOOKUP_ID and tms.trim_name=tms.trim_name and tms.trim_name != 'default')as TRIMNAME FROM howtolookup hlp inner join mmgvehiclecode vehicle on hlp.VEHICLECODE_INDENT=vehicle.vehiclecode_ident and vehicle.vehicle_year=? and vehicle.vehicle_model=? inner join vehicle_trims vt on vt.vehicle_id=vehicle.vehiclecode_ident inner join trims tms on tms.trims_ident=vt.trim_id where hlp.HOWTOLOOKUP_ID in ( SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=2 and CONTENTID in (SELECT VIDEODETAILS_ID FROM videodetails where concat(DETAILS,TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=1 and CONTENTID in (SELECT PDFDETAILS_ID FROM pdfdetails where concat(TITLE,RESOURCE_METADATA) like ?) union SELECT HOWTOLOOKUP_ID FROM howtolookup where IDCONTENTTYPE=3 and CONTENTID in (SELECT TEXTDETAILS_ID FROM textdetails where concat(TITLE,DETAILS,RESOURCE_METADATA) like ?)) and tms.trim_name != 'default' group by HOWTOLOOKUP_ID";
		String temp="%"+searchStr+"%";
		PreparedStatement pstmt=conn.prepareStatement(query);
		pstmt.setString(1, year);
		pstmt.setString(2, model);
		pstmt.setString(3, temp);
		pstmt.setString(4, temp);
		pstmt.setString(5, temp);
		
		resultSet=pstmt.executeQuery();

		while(resultSet.next())
			{
			HowToLookupDataVO datavo=new HowToLookupDataVO(resultSet.getString("CONTENTID"), resultSet.getString("DESCRIPTION"), resultSet.getString("THUMBURL"), resultSet.getString("TITLE"), resultSet.getString("CONTENTTYPE"));
			String trims=resultSet.getString("TRIMNAME");
			//Setting trims list
			if(trims!=null)
			datavo.setTrimNames(Arrays.asList(trims.split(",")));
			else
			datavo.setTrimNames(new ArrayList<String>());
			
			ArrayList<String> filters=(ArrayList<String>) getFilterListByLookupId(resultSet.getInt("HOWTOLOOKUP_ID"));
			datavo.setFilters(filters);
			
			lookupItems.add(datavo);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getHowToSearchItems(y/m/sq) > "+e);
			lookupItems=null;
			tempVo.setStatus("fail");
			tempVo.setError(e.getMessage());
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		tempVo.setLookupItems(lookupItems);
		
		//set filters list
		tempVo.setFilterItems(getAllFilterList());
		
		return tempVo;
	}

	
	/**
	 * Fetch how to  item details based on Year model input
	 * @param type
	 * @param id
	 * @return
	 */	
	public HowToLookUpResponseVO getHowtoItemDetail(String type, String id) {

		logger.debug("<<<<<<<<Inside getHowtoItemDetail Service");
		
		HowToLookUpResponseVO tempVo=new HowToLookUpResponseVO();

		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();
		
		if(type.equalsIgnoreCase(ApplicationConstants.DOC_TYPE_VIDEO))
		{	
			resultSet=stmt.executeQuery("SELECT DETAILS, STREAMURL,TITLE,THUMBURL FROM videodetails where VIDEODETAILS_ID='"+id+"'");
			while(resultSet.next())
			{
				logger.debug("Video Details  > "+resultSet.getString(1)+" | "+resultSet.getString(2));
				tempVo.setDetails(resultSet.getString(1));
				tempVo.setStreamUrl(resultSet.getString(2));
				tempVo.setTitle(resultSet.getString(3));
				tempVo.setThumbUrl(resultSet.getString(4));
			}
		}
		else 
			if(type.equalsIgnoreCase(ApplicationConstants.DOC_TYPE_TEXT))
			{	
				resultSet=stmt.executeQuery("SELECT TITLE, DETAILS FROM textdetails where TEXTDETAILS_ID='"+id+"'");
				while(resultSet.next())
				{
					logger.debug("Text Details  > "+resultSet.getString(1)+" | "+resultSet.getString(2));
					tempVo.setTitle(resultSet.getString(1));
					tempVo.setDetails(resultSet.getString(2));
				}
			}else{
				resultSet=stmt.executeQuery("SELECT DOWNLOADURL, TITLE FROM pdfdetails where PDFDETAILS_ID='"+id+"'");
				while(resultSet.next())
				{
					logger.debug("Pdf Details  > "+resultSet.getString(1)+" | "+resultSet.getString(2));
					tempVo.setDownloadUrl(resultSet.getString(1));
					tempVo.setTitle(resultSet.getString(2));
				}
			}
		
		
		}catch(Exception e){
			logger.error(" Problem -  getHowtoItemDetail > "+e);
			tempVo=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) { logger.error(e.getMessage());}
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
			
		}		

		return tempVo;
	}
	
	
	/**
	 * This method is used to get filters list by lookup id.
	 * @param lookupId
	 * @return
	 */
	private List<Integer> getFilterIdsListByLookupId(int lookupId)
	{

		ArrayList<Integer> filters=new ArrayList<Integer>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();
		resultSet=stmt.executeQuery("SELECT ft.FILTER_ID FROM filtermapping fm left join filtertype ft on fm.IDFILTERTYPE=ft.FILTER_ID where IDHOWTOLOOKUP="+lookupId+"");

		while(resultSet.next())
			{
			filters.add(resultSet.getInt(1));
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getFilterIdsListByLookupId > "+e);
			filters=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) {logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		return filters;
	}
	
	
	/**
	 * This method is used to get filters list by lookup id.
	 * @param lookupId
	 * @return
	 */
	private List<String> getFilterListByLookupId(int lookupId)
	{

		ArrayList<String> filters=new ArrayList<String>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();
		resultSet=stmt.executeQuery("SELECT ft.FILTERTYPE FROM filtermapping fm left join filtertype ft on fm.IDFILTERTYPE=ft.FILTER_ID where IDHOWTOLOOKUP="+lookupId+"");

		while(resultSet.next())
			{
			filters.add(resultSet.getString(1));
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getFilterListByLookupId > "+e);
			filters=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) {logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		return filters;
	}
	
	
	/**
	 * This method is used to get all the filter type names.
	 * @return
	 */
	private List<String> getFilterList(String year,String model)
	{

		ArrayList<String> filters=new ArrayList<String>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();
		//resultSet=stmt.executeQuery("select FILTERTYPE from filtertype where FILTER_ID in (select distinct IDFILTERTYPE from filtermapping fmp where fmp.IDHOWTOLOOKUP in (SELECT HOWTOLOOKUP_ID FROM howtolookup hlp where VEHICLECODE_INDENT in (SELECT vehiclecode_ident FROM mmgvehiclecode where vehicle_model='"+model+"' and vehicle_year='"+year+"'))) order by FILTER_ORDER");
		//Updated on 07-11-2013 for listing all filter type
		resultSet=stmt.executeQuery("select FILTERTYPE from filtertype order by FILTER_ORDER");

		while(resultSet.next())
			{
			filters.add(resultSet.getString(1));
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getFilterList(yr,model) > "+e);
			filters=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) {logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		return filters;
	}
	
	/**
	 * This method is used to get all the filter type names.
	 * @return
	 */
	private List<String> getAllFilterList()
	{

		ArrayList<String> filters=new ArrayList<String>();
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();

		resultSet=stmt.executeQuery("select FILTERTYPE from filtertype order by FILTER_ORDER");

		while(resultSet.next())
			{
			filters.add(resultSet.getString(1));
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getAllFilterList > "+e);
			filters=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) {logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		return filters;
	}
	
	
	
	/**
	 * This method is used to get all the filter objects.
	 * @return
	 */
	private List<FilterItemVO> getAllFilterObjectsList()
	{

		ArrayList<FilterItemVO> filters=new ArrayList<FilterItemVO>();
		String query="select FILTER_ID,FILTERTYPE,ICON_URL,(SELECT resource_code FROM mmgresource_master where resource_parent_code=7 and upper(resource_name)=upper(ftp.FILTERTYPE)) as TRACK_ID from filtertype ftp where PARENT_ID is null order by FILTER_ORDER";
		String childQuery="select FILTER_ID,FILTERTYPE,ICON_URL,(SELECT resource_code FROM mmgresource_master where resource_parent_code=7 and upper(resource_name)=upper(ftp.FILTERTYPE)) as TRACK_ID from filtertype ftp where PARENT_ID =? order by FILTER_ORDER";
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		stmt=conn.createStatement();

		resultSet=stmt.executeQuery(query);

		FilterItemVO vo=null,childvo=null,subChildvo=null;
		while(resultSet.next())
			{
			vo=new FilterItemVO(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
			vo.setTrackingId(resultSet.getInt(4));
			ArrayList<FilterItemVO> childs=new ArrayList<FilterItemVO>();
			
			//Getting childs
			PreparedStatement childStmt=conn.prepareStatement(childQuery);
			childStmt.setInt(1,resultSet.getInt(1));
			ResultSet resultSetChild=childStmt.executeQuery();
			while(resultSetChild.next())
			{
				childvo=new FilterItemVO(resultSetChild.getInt(1),resultSetChild.getString(2),resultSetChild.getString(3));	
				childvo.setTrackingId(resultSetChild.getInt(4));

		//SubChilds start
				ArrayList<FilterItemVO> subChilds=new ArrayList<FilterItemVO>();
				
				PreparedStatement subChildStmt=conn.prepareStatement(childQuery);
				subChildStmt.setInt(1,resultSetChild.getInt(1));
				ResultSet resultSetsubChild=subChildStmt.executeQuery();
				while(resultSetsubChild.next())
				{
					subChildvo=new FilterItemVO(resultSetsubChild.getInt(1),resultSetsubChild.getString(2),resultSetsubChild.getString(3));	
					subChildvo.setTrackingId(resultSetsubChild.getInt(4));
					
					subChilds.add(subChildvo);
				}
				
				if(!subChilds.isEmpty())
					childvo.setSubItems(subChilds);
				
				resultSetsubChild.close();
				subChildStmt.close();
				subChildvo=null;
		//SubChilds end
				
				childs.add(childvo);
			}
			
			if(!childs.isEmpty())
			vo.setSubItems(childs);
			
			filters.add(vo);

			resultSetChild.close();
			childStmt.close();
			childvo=null;
			vo=null;
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getAllFilterObjectsList > "+e);
			filters=null;
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) {logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		return filters;
	}	
	
	
	/**
	 * This method is used to imageUrl of a vehicle based on year model
	 * @return
	 */
	private String getVehicleImgeUrl(String year,String model)
	{

		String imageUrl=null;
		
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		
		try{
		conn=dataSource.getConnection();
		
		stmt=conn.prepareStatement("SELECT vehicle_image FROM mmgvehiclecode where vehicle_year=? and vehicle_model=?");
		stmt.setString(1,year);
		stmt.setString(2,model);

		resultSet=stmt.executeQuery();

		if(resultSet.next())
			{
			imageUrl=resultSet.getString(1);
			}
		
		}catch(Exception e){
			logger.error(" Problem -  getVehicleImgeUrl > "+e);
		}
		finally{
		    try { resultSet.close(); } catch (Exception e) {logger.error(e.getMessage()); }
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		
		return imageUrl;
	}


}
