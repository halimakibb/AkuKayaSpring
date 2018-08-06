package main.java.akuKaya.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.akuKaya.dao.interfaces.UserDAO;
import main.java.akuKaya.dao.interfaces.RegisterDAO;
import main.java.akuKaya.models.ReturnMessage;
import main.java.akuKaya.models.ReturnMessage.MessageType;
import main.java.akuKaya.models.User;
import main.java.akuKaya.services.interfaces.RegisterService;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterDAO registerDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void create(User user) {
		registerDAO.create(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public ReturnMessage authenticateUser(User user) {
		User testUser = userDAO.getUser(user.getUsername());
		
		if (testUser != null) {
			ReturnMessage returnMessage = new ReturnMessage(MessageType.Error, "Invalid Username");
			return returnMessage;
		}
		
		testUser = userDAO.getUserByEmail(user.getUserDetail().getEmail());
		
		if (testUser != null) {
			ReturnMessage returnMessage = new ReturnMessage(MessageType.Error, "Invalid Email");
			return returnMessage;
		}
		
		ReturnMessage returnMessage = new ReturnMessage(MessageType.Success, "User valid");
		return returnMessage;

	}

}
