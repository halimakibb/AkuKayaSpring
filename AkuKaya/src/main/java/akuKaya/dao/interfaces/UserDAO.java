package main.java.akuKaya.dao.interfaces;

import main.java.akuKaya.models.User;

public interface UserDAO {

	User getUser(String username);
	User getUserById(int id);
	User getUserByEmail(String email);
}
