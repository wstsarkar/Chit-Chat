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

	public int createUser(User user){		
		return repository.createUser(user);
	}

	public int updateUser(User user){		
		return repository.updateUser(user);
	}

	public User getUser(String userName, String password){
		
		return repository.getUser(userName, password);
	}

	public User getUser(String userName){
		
		return repository.getUser(userName);
	}

	public List<User> getAllUserExcept(String userName){
		
		return repository.getAllUserExcept(userName);
	}
	
	public List<User> getAllOnlineUserExcept(String userName){
		
		return repository.getAllOnlineUserExcept(userName);
	}

}
