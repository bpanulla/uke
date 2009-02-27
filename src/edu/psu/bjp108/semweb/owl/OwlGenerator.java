package edu.psu.bjp108.semweb.owl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class OwlGenerator
{

	private static Connection dbConnection = null;
	
	// Create an instance of the Log4j logger for debug logging
	private static org.apache.log4j.Logger logger = Logger.getLogger(OwlGenerator.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		// Establish the database connection
		try
		{
			Class.forName(edu.psu.bjp108.semweb.config.AppConfig.DATABASE_DRIVER);
			
			dbConnection = DriverManager.getConnection(
					edu.psu.bjp108.semweb.config.AppConfig.DATABASE_URL,
					edu.psu.bjp108.semweb.config.AppConfig.DATABASE_LOGIN_NAME,
					edu.psu.bjp108.semweb.config.AppConfig.DATABASE_LOGIN_PASSWORD);
		}
		catch (ClassNotFoundException cnfe)
		{
			logger.fatal("Cannot find database driver.", cnfe);
	
			return;
		}
		catch (SQLException sqle)
		{
			logger.fatal("Failed opening database connection.", sqle);
	
			return;
		}
		
		
		try
		{
			Statement queryStatement = dbConnection.createStatement();
			ResultSet courseResultSet = queryStatement
				.executeQuery(
					"SELECT DISTINCT TOP 20 course, course_title, course_short_title," +
					" academic_dept, controlling_college" +
					" FROM uf_ucm" +
					" ORDER BY course ASC");

			while (courseResultSet.next())
			{
				logger.debug(courseResultSet.getString("course"));
			}

		}
		catch (SQLException sqlException)
		{
			logger.fatal("Failed during RDF generation.", sqlException);

			return;
		}
		
		
		
	}

}
