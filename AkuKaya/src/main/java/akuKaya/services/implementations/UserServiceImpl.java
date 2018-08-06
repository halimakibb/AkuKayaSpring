package main.java.akuKaya.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.akuKaya.dao.interfaces.UserDAO;
import main.java.akuKaya.models.User;
import main.java.akuKaya.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public User getUser(String username) {
		return userDAO.getUser(username);
		
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}



}
