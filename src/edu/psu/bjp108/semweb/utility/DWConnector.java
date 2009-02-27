package edu.psu.bjp108.semweb.utility;

import java.sql.*;

import edu.psu.bjp108.semweb.config.AppConfig;

public class DWConnector
{

	private static Connection dbConnection;

	private boolean connected = false;

	public DWConnector()
	{
	}

	private boolean connect()
	{

		try
		{
			Class.forName(AppConfig.DATABASE_DRIVER);
		}
		catch (Exception e)
		{
			System.out.println("Unable to find and load driver. " + e.toString());
			connected = false;
			return false;
		}

		try
		{
			dbConnection = DriverManager.getConnection(
					AppConfig.DATABASE_URL,
					AppConfig.DATABASE_LOGIN_NAME,
					AppConfig.DATABASE_LOGIN_PASSWORD);

			// executeQuery("SET NAMES 'utf8'", out);
			connected = true;
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Unable to connect to database. " + e.toString());
			connected = false;
			return false;
		}

	}

	private void disconnect()
	{
		try
		{
			dbConnection.close();
			connected = false;
		}
		catch (Exception e)
		{
			System.out.println("Unable to close database connection. "
					+ e.toString());
		}
	}

	public boolean executeUpdate(String sql)
	{

		if (!connected)
		{
			if (!connect())
			{
				return false;
			}
		}

		try
		{
			Statement sqlStatement = dbConnection.createStatement();
			sqlStatement.executeUpdate(sql);
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
			return false;
		}

	}

	public ResultSet executeQuery(String sql)
	{

		if (!connected)
		{
			if (!connect())
			{
				return null;
			}
		}

		try
		{

			Statement sqlStatement = dbConnection.createStatement();
			ResultSet resultSet = sqlStatement.executeQuery(sql);
			return resultSet;

		}
		catch (SQLException sqle)
		{
			System.out.println(sqle.toString());
			return null;
		}

	}

}