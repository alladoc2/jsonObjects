package com.gfs.projects.common.util;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import com.gfs.projects.common.bean.UserInfo;
import com.gfs.projects.common.db.ConnectionPool;
import com.gfs.projects.common.exception.MGSException;

public class TokenManagerUtil {
	public static final Logger logger =  Logger.getLogger(TokenManagerUtil.class);

	/**
	 * Generate Token
	 * @return
	 * @throws MGSException
	 */
	public static String generateToken() throws MGSException 
	{
		String methodName = ".generateToken";
		Connection conn = null;
		String token = null;
		
		try {
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("Select newid() as generatedtoken;");
			conn = ConnectionPool.getInstanceForMSSQLDBConnection();
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				token = rs.getString("generatedtoken");
			}
			
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
			
		} finally {
			ConnectionPool.free(conn);
		}		
		
		logger.debug(methodName + " token = [" + token + "]");
		return token;
	}
	
	/**
	 * Check if Token has expired
	 * @param userInfo
	 * @return
	 */
	public static boolean isMGSTokenHasExpired (UserInfo userInfo) {
		try {
			return (System.currentTimeMillis() >  userInfo.getTokenExpiry());
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}	

	
	/**
	 * Get Default Token Expiry value
	 * @return
	 */
	public static long getDefaultMGSTokenExpiry(){
		return 	Long.parseLong(MRDirectories.getProperties("mgs.token.expiry"));
	}
}
