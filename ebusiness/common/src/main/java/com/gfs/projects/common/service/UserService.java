package com.gfs.projects.common.service;

import java.sql.SQLException;

import com.gfs.projects.common.bean.UserInfo;

public interface UserService {
	
	public UserInfo getPlayerInfoByLoginNameAndPassword(String loginName, String password) throws Exception;
	public UserInfo getPlayerInfoByToken(String tokenString) throws Exception;
	public void updateTokens (String userId, String token) throws Exception;
	public UserInfo getPlayerInfoByLoginName(String loginName) throws SQLException, Exception;
	
}
