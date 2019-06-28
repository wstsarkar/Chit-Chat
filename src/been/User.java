package been;

/**
 * 
 * @author Williyam
 * 
 */
public class User {
	private int user_id;
	private String user_name;
	private String password;
	private String name;
	private String email;
	private String mobile_no;
	private int is_logged_in;
	
	public User() {
		
	}
	
	
	public User(int user_id, String user_name, String password, String name, String email, String mobile_no,
			int is_logged_in) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.name = name;
		this.email = email;
		this.mobile_no = mobile_no;
		this.is_logged_in = is_logged_in;
	}


	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	/**
	 * @param mobile_no the mobile_no to set
	 */
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public int getIs_logged_in() {
		return is_logged_in;
	}
	/**
	 * @param is_logged_in the is_logged_in to set
	 */
	public void setIs_logged_in(int is_logged_in) {
		this.is_logged_in = is_logged_in;
	}
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
