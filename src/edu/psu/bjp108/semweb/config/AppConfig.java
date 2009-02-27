/**
 * 
 */
package edu.psu.bjp108.semweb.config;

/**
 * @author bpanulla
 *
 * This file contains static configuration properties that are read
 * by other classes in the system.
 * 
 * Main properties are the parameters to connect to the Data Warehouse.
 *
 */
public final class AppConfig {

    /**
     * 
     */
    
    public static final String
    	DATABASE_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver",
    	DATABASE_HOST = "warehouse.oas.psu.edu",
    	DATABASE_PORT = "1433",
    	DATABASE_NAME = "student",
    	DATABASE_LOGIN_NAME = "bjp108",
    	DATABASE_LOGIN_PASSWORD = "**********",
    	
    	DATABASE_URL = "jdbc:sqlserver://" + DATABASE_HOST + ":" + DATABASE_PORT +
    		";databaseName=" + DATABASE_NAME +
    		";user=" + DATABASE_LOGIN_NAME + 
    		";password=" + DATABASE_LOGIN_PASSWORD;
    
    public AppConfig()
    {
    }

}
