package main.java.akuKaya.services.interfaces;

import org.springframework.stereotype.Service;

import main.java.akuKaya.models.User;

@Service()
public interface UserService {
	User getUser(String username);
	User getUserById(int id);
}
