package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import been.User;

/**
 * 
 * @author Williyam
 * 
 */
public class UserRepository {

	
	private User Get(ResultSet rs) throws SQLException{
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setUser_id(rs.getInt("user_id"));
		user.setUser_name(rs.getString("user_name"));
		user.setPassword(rs.getString("password"));
		user.setMobile_no(rs.getString("mobile_no"));
		user.setIs_logged_in(rs.getInt("is_logged_in"));
		return user;
	}
	
	private List<User> list(String query){
		ResultSet rs = DataBaseHelper.executeSelectQuery(query);	

		List<User> list = new ArrayList<User>();
		try {
			User users;
			while (rs.next()) {
				users = Get(rs);
				list.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	private User user(String query){
		ResultSet rs = DataBaseHelper.executeSelectQuery(query);	
		User users = null;
		try {
			if (rs.next()) {
				users = Get(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public User getUser(String userName, String password){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM Users ");
		query.append(" WHERE user_name = '" + userName + "'");
		query.append(" AND password = '" + password + "';");
		
		return user(query.toString());
	}
	
	public List<User> getAllUserExcept(String userName){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM Users ");
		query.append(" WHERE user_name != '" + userName + "'");
		
		return list(query.toString());
	}
	
	public List<User> getAllOnlineUserExcept(String userName){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT * FROM Users ");
		query.append(" WHERE user_name != '" + userName + "'");
		query.append(" AND is_logged_in = 1");
		
		return list(query.toString());
	}

}
