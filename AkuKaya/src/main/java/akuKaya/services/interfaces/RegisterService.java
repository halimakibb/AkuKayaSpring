package main.java.akuKaya.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import main.java.akuKaya.models.ReturnMessage;
import main.java.akuKaya.models.Role;
import main.java.akuKaya.models.User;
@Service()
public interface RegisterService {
	void create(User user);
	List<Role> getRoles(List<Integer> roleIds);
	ReturnMessage authenticateUser(User user);
}
