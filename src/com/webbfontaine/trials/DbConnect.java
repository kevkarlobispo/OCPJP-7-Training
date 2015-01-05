package com.webbfontaine.trials;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Kev Obispo on 5/13/2014.
 */
class DbConnect {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3307/";
		String database = "addressBook";
		String userName = "root";
		String password = "root";
		try (Connection connection = DriverManager.getConnection
				(url + database, userName, password)){
			System.out.println("Database connection: Successful");
		} catch (Exception e) {
			System.out.println("Database connection: Failed");
			e.printStackTrace();
		}
	}
}