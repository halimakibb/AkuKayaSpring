package main.java.akuKaya.dao.implementations;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.java.akuKaya.dao.interfaces.UserDAO;
import main.java.akuKaya.models.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory factory;

	@Override
	public User getUser(String username) {

		Session currentSession = factory.getCurrentSession();

		Query<User> query = currentSession
				.createQuery("from User " + "where username=:username " + "and isActive = true ", User.class);

		query.setParameter("username", username);
		List<User> result = query.getResultList();
		if (result.size() > 0) {
			User user = result.get(0);
			return user;
		}

		return null;
	}

	@Override
	public User getUserById(int id) {
		Session currentSession = factory.getCurrentSession();

		Query<User> query = currentSession
				.createQuery("from User " + "where userID=:id "
						+ "and isActive = true ", User.class);

		query.setParameter("id", id);
		List<User> result = query.getResultList();
		if (result.size() > 0) {
			User user = result.get(0);
			return user;
		}

		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		Session currentSession = factory.getCurrentSession();

		Query<User> query = currentSession
				.createQuery("from User as User "
						+ "where User.userDetail.email=:email "
						+ "and isActive = true ", User.class);

		query.setParameter("email", email);
		List<User> result = query.getResultList();
		if (result.size() > 0) {
			User user = result.get(0);
			return user;
		}

		return null;
	}


}
