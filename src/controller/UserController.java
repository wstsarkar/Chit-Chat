package controller;

import java.util.List;

import been.User;
import repository.UserRepository;

/**
 * 
 * @author Williyam
 * 
 */
public class UserController {
	private UserRepository repository;
	
	public UserController() {
		this.repository = new UserRepository();
	}
	
	
	public User getUser(String userName, String password){
		
		return repository.getUser(userName, password);
	}

	public List<User> getAllUserExcept(String userName){
		
		return repository.getAllUserExcept(userName);
	}
	
	public List<User> getAllOnlineUserExcept(String userName){
		
		return repository.getAllOnlineUserExcept(userName);
	}

}
