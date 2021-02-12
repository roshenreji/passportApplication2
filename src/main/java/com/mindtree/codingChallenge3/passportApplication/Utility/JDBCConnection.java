package com.mindtree.codingChallenge3.passportApplication.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.DatabaseConnectionFailedException;

public class JDBCConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/newdatabase";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "Helloworld1234";

	public static Connection getConnection() throws DatabaseConnectionFailedException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			throw new DatabaseConnectionFailedException("Database could not be connected");
		}

		return con;
	}

	public void closeResources(Connection con) {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public void closeResources(Statement st) {

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public void closeResources(PreparedStatement ps) {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public void closeResources(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}
}
