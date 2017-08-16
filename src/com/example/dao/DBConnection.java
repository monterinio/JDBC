package com.example.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	private static DataSource dataSource;
	
	private DBConnection() { };
	
	public static DataSource getDataSource() {
		return getDSInstance();
	}
	
	private static DataSource getDSInstance() {
		if(dataSource == null) {
			try {
				Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/students");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return dataSource;
	}
}
