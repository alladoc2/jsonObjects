package com.gfs.projects.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.gfs.projects.common.db.ConnectionPool;

public class MRDirectories {
	public static final Logger logger =  Logger.getLogger(MRDirectories.class);
	private static Map<String, String> cache = new ConcurrentHashMap<String, String>();
	private MRDirectories() {}
	
	public static String getProperties(String key) 
	{
		String methodName = ".getProperties";
		
		logger.info(methodName + " >>> start ");
		if (cache.isEmpty()) {
			updateCache();
		}
		logger.info(methodName + " <<< end "); 
		return (String) cache.get(key);
	}
	
	public static void updateCache() 
	{
		String methodName = ".updateCache";
		
		logger.info(methodName + " >>> start");
		long startTime = System.currentTimeMillis();
		Connection conn = null;
		try {
			String sql = "Select * from T_MR_Directories";
			conn = ConnectionPool.getInstanceForMSSQLDBConnection();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				cache.put(rs.getString("id"), rs.getString("value"));
			}
			logger.info(methodName + "Cache Size = " + cache.size());
		} catch (Exception e) {
			logger.error(e);
		} finally {
			ConnectionPool.free(conn);
		}
		
		logger.info(methodName + " <<< end " + (System.currentTimeMillis() - startTime) + " milliseconds"); 
	}
}
