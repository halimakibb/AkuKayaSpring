package main.java.akuKaya.dao.implementations;

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
		// save user
		session.save(user);

		// commit the transaction
		System.out.println("Done!");

	}

	@Override
	public Role getRole(Integer roleId) {
		Session session = factory.getCurrentSession();
		Role role = session.get(Role.class, roleId);
		return role;
	}

}
