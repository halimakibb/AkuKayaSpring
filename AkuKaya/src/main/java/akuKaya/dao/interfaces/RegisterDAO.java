package main.java.akuKaya.dao.interfaces;

import main.java.akuKaya.models.Role;
import main.java.akuKaya.models.User;

public interface RegisterDAO {
	void create(User user);

	Role getRole(Integer roleId);
}
