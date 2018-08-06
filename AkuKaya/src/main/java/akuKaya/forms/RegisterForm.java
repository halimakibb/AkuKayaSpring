package main.java.akuKaya.forms;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class RegisterForm {

	@NotNull
	@Size(min = 1, max = 20, message = "Username should have at least 1 character and at most 20 character")
	private String username;

	@NotNull
	@Size(min = 1, max = 68)
	private String password;

	@NotNull
	@Size(min = 1, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 1, max = 50)
	private String lastName;

	@NotNull
	@Size(min = 1, max = 50)
	@Email
	private String email;

	@DateTimeFormat (pattern="yyyy-MM-dd")
	private Date birthdate;

	@NotNull
	@Size(min = 1, max = 100)
	private String address;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdateString) throws Exception {
		final String inputFormat = "yyyy-MM-dd";
		try {
			this.birthdate =  new SimpleDateFormat(inputFormat).parse(birthdateString);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
			throw ex;
		}

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
