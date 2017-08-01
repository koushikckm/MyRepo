package com.mazdausa.mmg.web.client.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mazdausa.mmg.web.client.service.iface.AnalyticServiceIface;


/**
 * This is the Implementation class of Service layer Interface HowtoServiceIFace.
 * @author AnilK
 *
 */

@Service
public class AnalyticServiceImpl implements AnalyticServiceIface{

    private static final Logger logger = LoggerFactory.getLogger(AnalyticServiceImpl.class);

    @Autowired
    private DataSource dataSource;
    
    
    /**
     * This method is responsible for saving usage data of mmg client application usage.
     * @param resourceCode
     * @param deviceType
     * @param userId
     * @return success/failure
     */
	public String saveAnalytic(String resourceCode,String deviceType,String userId)
	{
		logger.debug(">>>entering saveAnalytic");
		String status="success";

		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
		String query="INSERT INTO mmgnavigations(resource_code, devicetype, last_update_user, last_update_date)VALUES(?,?,?,current_timestamp)";	
		conn=dataSource.getConnection();
		logger.debug("Query = "+query);
		
		stmt=conn.prepareStatement(query);
		stmt.setString(1,resourceCode);
		stmt.setString(2,deviceType);
		stmt.setString(3,userId);
		
		int updateRow=stmt.executeUpdate();
		logger.debug("No of affected rows = "+updateRow);
		
		}catch(Exception e){
			logger.error(" Problem -  saveAnalytic > "+e);
			status="failure";
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		logger.debug("<<< exiting saveAnalytic");
		
		
		return status;
	}
	
	
	public String saveAnalytic(String resourcecode,String devicetype,String userid,String contenttype,String videotype,String categoryid,String year,String model,String title)
	{
		logger.debug(">>>entering saveAnalytic for contents");
		String status="success";

		Connection conn=null;
		Statement stmt=null;
		
		try{
		
		conn=dataSource.getConnection();
		conn.setAutoCommit(false);

		stmt=conn.createStatement();

		String query="INSERT INTO mmgnavigations(resource_code, devicetype, last_update_user, last_update_date)VALUES("+resourcecode+",'"+devicetype+"','"+userid+"',current_timestamp)";	
		stmt.addBatch(query);
		query="INSERT INTO mmgnavigations_contents(resource_code, devicetype, content_type, video_type, categoryid, year, model, title, updated_by, updated_date) VALUES ("+resourcecode+",'"+devicetype+"', '"+contenttype+"', '"+videotype+"', "+categoryid+", "+year+", '"+model+"', '"+title+"','"+userid+"',current_timestamp)";	
		stmt.addBatch(query);

		
		int[] updateRow=stmt.executeBatch();
		logger.debug(query+" \n No of affected rows in mmgnavigations= "+updateRow);
			
		conn.setAutoCommit(true);
		}catch(Exception e){
			logger.error(" Problem -  saveAnalytic > "+e);
			status="failure";
			
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		logger.debug("<<< exiting saveAnalytic");
		
		
		return status;
	}
	
	
	
	public String saveAnalytic_x(String resourcecode,String devicetype,String userid,String contenttype,String videotype,String categoryid,String year,String model,String title)
	{
		logger.debug(">>>entering saveAnalytic for contents");
		String status="success";

		Connection conn=null;
		PreparedStatement stmt=null;
		
		try{
		
		conn=dataSource.getConnection();
		conn.setAutoCommit(false);

		String query="INSERT INTO mmgnavigations(resource_code, devicetype, last_update_user, last_update_date)VALUES(?,?,?,current_timestamp)";	
		stmt=conn.prepareStatement(query);
		stmt.setString(1,resourcecode);
		stmt.setString(2,devicetype);
		stmt.setString(3,userid);
		
		int updateRow=stmt.executeUpdate();
		logger.debug(query+" \n No of affected rows in mmgnavigations= "+updateRow);
		stmt=null;
		
		query="INSERT INTO mmgnavigations_contents(resource_code, devicetype, content_type, video_type, categoryid, year, model, title, updated_by, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp)";	
		stmt=conn.prepareStatement(query);
		stmt.setString(1,resourcecode);
		stmt.setString(2,devicetype);
		stmt.setString(3,contenttype);
		stmt.setString(4,videotype);
		stmt.setString(5,categoryid);
		stmt.setString(6,year);
		stmt.setString(7,model);
		stmt.setString(8,title);
		stmt.setString(9,userid);
		
		updateRow=stmt.executeUpdate();
		logger.debug(query+" \n No of affected rows in mmgnavigations_contents= "+updateRow);
		
		conn.setAutoCommit(true);
		}catch(Exception e){
			logger.error(" Problem -  saveAnalytic > "+e);
			status="failure";
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally{
		    try { stmt.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		    try { conn.close(); } catch (Exception e) { logger.error(e.getMessage()); }			
		}		
		logger.debug("<<< exiting saveAnalytic");
		
		
		return status;
	}

}
