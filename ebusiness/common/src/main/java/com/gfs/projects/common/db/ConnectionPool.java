package com.gfs.projects.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.gfs.projects.common.AC;

public class ConnectionPool {

	
	private static Logger logger = Logger.getLogger(ConnectionPool.class);
	
	// TODOs
	private DataSource ds;

	
	
	private ConnectionPool () {}
	
	public static Connection getInstanceForOracleDBConnection() {
		logger.info(".getInstanceOracleConnection >>> start ");
		try {
			Class.forName(AC.ORACLE_JDBC_DRIVER);
			logger.info(".getInstanceOracleConnection >>> end ");
			return DriverManager.getConnection(AC.ORACLE_DB_URL,AC.ORACLE_DB_USERNAME,AC.ORACLE_DB_PASSWORD);
		} catch (Exception e) {
			logger.error(e);
		}
		return null; 
	}

	public static Connection getInstanceForMSSQLDBConnection() {
		logger.info(".getInstanceOracleConnection >>> start ");
		try {
			Class.forName(AC.MSSQL_JDBC_DRIVER);
			return DriverManager.getConnection(AC.MSSQL_DB_URL, AC.MSSQL_DB_USERNAME, AC.MSSQL_DB_PASSWORD);
		} catch (Exception e) {
			logger.error(e);
		}
		return null; 
	}
	
	public void setDataSource (DataSource dataSource) {
		this.ds = dataSource;
	}
	
	
	
	public DataSource getDataSource() {
		return ds;
	}
	
	private DataSource lookupDataSource (String dataSourceName) {
        DataSource ds = null;

        if (logger.isDebugEnabled()) logger.debug(".lookupDataSource() >>>");

        try {
            InitialContext context = new InitialContext();
            ds =(javax.sql.DataSource)context.lookup(dataSourceName);
        } catch( NamingException e ) {
            logger.error("",e);
        }

        if (logger.isDebugEnabled()) logger.debug(".lookupDataSource() <<<");

        return ds;		
	}
	

    public Connection getConnection() throws SQLException {
        
    	if (logger.isDebugEnabled()) logger.debug(".getConnection() >>>");
        
        DataSource source = getDataSource();
        
        if (source == null){
            throw new SQLException("Unable to get connection because DataSource is null.");
        }

        if (logger.isDebugEnabled()) logger.debug(".getConnection() <<<");

        Connection connection = source.getConnection();

        return connection;
    }	
    
    
    public static void free(Connection connection) {


        if (logger.isDebugEnabled()) logger.debug(".free() >>>");
        try {
            if (connection != null){
                connection.close();
            }
        }
        catch( SQLException e ) {
            logger.error("",e);
        }
        if (logger.isDebugEnabled()) logger.debug(".free() <<<");
    }    
}
