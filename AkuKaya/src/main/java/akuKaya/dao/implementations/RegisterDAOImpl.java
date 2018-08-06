package main.java.akuKaya.dao.implementations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.akuKaya.dao.interfaces.RegisterDAO;
import main.java.akuKaya.models.Role;
import main.java.akuKaya.models.User;

@Repository
public class RegisterDAOImpl implements RegisterDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public void create(User user) {
		Session session = factory.getCurrentSession();

		// set role, can only register as user right now
		Role role = session.get(Role.class, 2);

		session.save(role);
		// add roles to user
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);

		user.setRoles(roles);

		// save user
		session.save(user);

		// commit the transaction
		System.out.println("Done!");

	}

}
