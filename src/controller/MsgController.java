package controller;

import java.util.List;

import been.MSG;
import repository.MsgRepository;

/**
 * 
 * @author Williyam
 * 
 */
public class MsgController {
	private MsgRepository repository;
	
	public MsgController() {
		this.repository = new MsgRepository();
	}

	public int createMsg(MSG msg){		
		return repository.createMSG(msg);
	}

	public int updateMsg(MSG msg){		
		return repository.updateMSG(msg);
	}

	public List<MSG> getAllMyMsg(int userId){		
		return repository.getAllMyMsg(userId);
	}

	public List<MSG> getAllMySendMsg(int userId){		
		return repository.getAllMySendMsg(userId);
	}

	public List<MSG> getAllMyReceiveMsg(int userId){		
		return repository.getAllMyReceiveMsg(userId);
	}

	public List<MSG> getAllUnsendMSG(int userId) {
		return repository.getAllUnsendMSG(userId);
	}
	

}
