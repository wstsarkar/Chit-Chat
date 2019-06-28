package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Williyam
 * 
 */
public class DataBaseHelper {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Communication";
	private static final String USER = "sa";
	private static final String PASS = "sonic600";
	

	private static Connection conn = null;

	private DataBaseHelper() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER);


			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} 

	}
	
	public static Connection getConnection(){
		try {
			if(conn == null || conn.isClosed())
				new DataBaseHelper();
		} catch (SQLException e) {
			e.printStackTrace();
			new DataBaseHelper();
		}			
		return conn;
	}
	
	public static int executeQuery(String query) {
		Connection conn = getConnection();
		int rowEffected = -1;
		Statement st;
		try {
			st = conn.createStatement();
			rowEffected = st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowEffected;
	}
	
	public static ResultSet executeSelectQuery(String query) {
		Connection conn = getConnection();
		Statement st;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	

}
