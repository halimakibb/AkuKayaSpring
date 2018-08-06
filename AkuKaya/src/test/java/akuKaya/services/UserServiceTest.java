package test.java.akuKaya.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import main.java.akuKaya.dao.interfaces.UserDAO;
import main.java.akuKaya.models.User;
import main.java.akuKaya.models.UserDetail;
import main.java.akuKaya.services.implementations.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	UserDAO userDAOMock;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	UserDetail sampleUserDetail = new UserDetail("habib", "malik", "test@gmail.com", new Date(), "jalan test");
	User sampleUser = new User("habibmalik", "test", true, "habib", new Date(), "habib", new Date(), sampleUserDetail);
	
	@Test
	public void testGetUser() {
	
		when(userDAOMock.getUser("habibmalik")).thenReturn(sampleUser);

		User testUser = userServiceImpl.getUser("habibmalik");
		assertEquals("test@gmail.com", testUser.getUserDetail().getEmail());

	}
	
	@Test
	public void testGetUserById() {
		when(userDAOMock.getUserById(1)).thenReturn(sampleUser);

		User testUser = userServiceImpl.getUserById(1);
		assertEquals("test@gmail.com", testUser.getUserDetail().getEmail());
	}

}
