package main.java.akuKaya.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

	@NotNull
	@Size(min = 1, max = 20, message = "Username should have at least 1 character and at most 20 character")
	private String username;

	@NotNull
	@Size(min = 1, max = 60)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
