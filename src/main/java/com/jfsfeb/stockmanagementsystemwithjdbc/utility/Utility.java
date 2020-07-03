package com.jfsfeb.stockmanagementsystemwithjdbc.utility;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Utility {
	public Connection getConnection() {
		Connection connection = null;
		try {
			FileInputStream fis = new FileInputStream("stockmanagementsystemDatabase.properties");
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			connection = DriverManager.getConnection(properties.getProperty("dburl"));
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getQuery(String baseQuery) {
		String query = null;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("stockmanagementsystemDatabase.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			query = properties.getProperty(baseQuery);
			return query;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
