package ehu.isad.controllers.db;

import ehu.isad.utils.Utils;

import java.sql.*;
import java.util.Properties;


public class DBKudeatzaileSQLite {

	Connection conn = null;
	// singleton patroia
	private static DBKudeatzaileSQLite instantzia = new DBKudeatzaileSQLite();
	public static DBKudeatzaileSQLite getInstantzia() {
		return instantzia;
	}
	private DBKudeatzaileSQLite()  {

		Properties ezarpenak = Utils.lortuEzarpenak();
		String datubasea = ezarpenak.getProperty("dbname");
		this.conOpen(datubasea);
	}



	private void conOpen(String dbpath) {
		try {
			String url = "jdbc:sqlite:"+ dbpath ;
			conn = DriverManager.getConnection(url);

			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server " + e);
		}
	}



	private void conClose() {

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		System.out.println("Database connection terminated");

	}

	private ResultSet query(Statement s, String query) {

		ResultSet rs = null;

		try {
			rs = s.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}



	public ResultSet execSQL(String query) {
		int count = 0;
		Statement s = null;
		ResultSet rs = null;

		try {
			s = (Statement) conn.createStatement();
			if (query.toLowerCase().indexOf("select") == 0) {
				// select agindu bat
				rs = this.query(s, query);

			} else {
				// update, delete, create agindu bat
				count = s.executeUpdate(query);
				System.out.println(count + " rows affected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}
}