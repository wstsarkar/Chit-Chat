package repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 
 * @author Williyam
 * 
 */
public class DataBaseHelper {

	private static String host;
	private static String user;
	private static String password;

	private static Connection conn = null;

	private DataBaseHelper() {

		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = DataBaseHelper.class.getClassLoader().getResourceAsStream("db.properties");
			prop.load(in);
			String driver = prop.getProperty("driver");
			host = prop.getProperty("host");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			Class.forName(driver);
			
			if (conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection(host, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed())
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
