package com.gfs.projects.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.gfs.projects.common.bean.APIUser;
import com.gfs.projects.common.bean.UserInfo;
import com.gfs.projects.common.db.ConnectionPool;
import com.gfs.projects.common.exception.MGSException;
import com.gfs.projects.common.service.UserService;
import com.gfs.projects.common.util.MD5;
import com.gfs.projects.common.util.TokenManagerUtil;

public class UserServiceJdbcImpl implements UserService{
	
	public static final Logger logger =  Logger.getLogger(UserServiceJdbcImpl.class);
	

	public UserServiceJdbcImpl() {}
	
	public UserInfo getPlayerInfoByLoginNameAndPassword(String loginName, String password) throws MGSException 
	{
		String methodName = ".findUserInfoByLoginNameAndPassword";
		logger.info(methodName + " >>> Start");
		
		long startTime = System.currentTimeMillis();
		UserInfo userInfo = null;
		Connection conn = null;
		
		try {
			// TODO place in external xml file
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT * ");
			sqlSb.append("FROM T_MGS_PlayerInfo ");
			sqlSb.append("WHERE LoginName = ? ");
			sqlSb.append("AND password = ? ");

			
			conn = ConnectionPool.getInstanceForMSSQLDBConnection();
			
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, loginName);
			ps.setString(2, MD5.Encrypt(password));
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setUserid(rs.getString("Id"));
				userInfo.setBalance(rs.getInt("Balance"));
				userInfo.setLoginname(rs.getString("LoginName"));
				userInfo.setCurrency(rs.getString("Currency"));
				userInfo.setTokenA(rs.getString("tokena"));
				userInfo.setTokenB(rs.getString("tokenb"));
				userInfo.setTokenExpiry(rs.getLong("token_expiry"));
				
			} 
			
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		return userInfo;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.gfs.projects.common.service.UserService#getUserInfoByToken(java.lang.String)
	 */
	public UserInfo getPlayerInfoByToken(String tokenString) throws MGSException 
	{
		String methodName = ".getUserInfoByToken ";
		logger.info(methodName + " >>> Start");
		
		long startTime = System.currentTimeMillis();
		UserInfo userInfo = null;
		Connection conn = null;
		
		try {
			// TODO place in external xml file
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT * ");
			sqlSb.append("FROM T_MGS_PlayerInfo ");
			sqlSb.append("WHERE tokena = ? ");

			conn = ConnectionPool.getInstanceForMSSQLDBConnection();
			
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, tokenString);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setUserid(rs.getString("Id"));
				userInfo.setBalance(rs.getInt("Balance"));
				userInfo.setLoginname(rs.getString("LoginName"));
				userInfo.setCurrency(rs.getString("Currency"));
				userInfo.setTokenA(rs.getString("tokena"));
				userInfo.setTokenB(rs.getString("tokenb"));
				userInfo.setTokenExpiry(rs.getLong("token_expiry"));
			} 
			
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		return userInfo;
		
	}

	public void updateTokens(String userId, String token) throws Exception {
		String methodName = ".udpateTokens userId " + userId;
		logger.info(methodName + " >>> Start");
		long startTime = System.currentTimeMillis();
		Connection conn = null;
		
		try {
			// TODO place in external xml file
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("UPDATE T_MGS_PlayerInfo ");
			sqlSb.append("SET tokena = ?, tokenb = ?, token_expiry = ? ");
			sqlSb.append("WHERE id = ?");

			conn = ConnectionPool.getInstanceForMSSQLDBConnection();
			
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, token);
			ps.setString(2, token);
			ps.setLong(3, (System.currentTimeMillis() + TokenManagerUtil.getDefaultMGSTokenExpiry()));
			ps.setString(4, userId);

			ps.execute();
		} catch (SQLException e) {
			throw new MGSException (e.getMessage());
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");		
	}

	public UserInfo getPlayerInfoByLoginName(String loginName) throws SQLException, Exception 
	{
		String methodName = ".getPlayerInfoByLoginName ";
		logger.info(methodName + " >>> Start");
		
		long startTime = System.currentTimeMillis();
		UserInfo userInfo = null;
		Connection conn = null;
		
		try {
			// TODO place in external xml file
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("SELECT * ");
			sqlSb.append("FROM T_MGS_PlayerInfo ");
			sqlSb.append("WHERE loginname = ? ");

			conn = ConnectionPool.getInstanceForMSSQLDBConnection();
			
			PreparedStatement ps = conn.prepareStatement(sqlSb.toString());
			ps.setString(1, loginName);

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				userInfo = new UserInfo();
				userInfo.setUserid(rs.getString("Id"));
				userInfo.setBalance(rs.getInt("Balance"));
				userInfo.setLoginname(rs.getString("LoginName"));
				userInfo.setCurrency(rs.getString("Currency"));
				userInfo.setTokenA(rs.getString("tokena"));
				userInfo.setTokenB(rs.getString("tokenb"));
				userInfo.setTokenExpiry(rs.getLong("token_expiry"));
			} 
			
		} catch (Exception e) {
			throw new MGSException (e.getMessage());
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + ((System.currentTimeMillis()-startTime)/1000) + " seconds");
		return userInfo;
	}


}
