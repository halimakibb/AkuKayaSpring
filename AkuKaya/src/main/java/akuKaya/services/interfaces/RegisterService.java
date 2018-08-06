package main.java.akuKaya.services.interfaces;

import org.springframework.stereotype.Service;

import main.java.akuKaya.models.ReturnMessage;
import main.java.akuKaya.models.User;
@Service()
public interface RegisterService {
	void create(User user);
	ReturnMessage authenticateUser(User user);
}
