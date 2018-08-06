package main.java.akuKaya.controllers;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.akuKaya.forms.RegisterForm;
import main.java.akuKaya.models.ReturnMessage;
import main.java.akuKaya.models.ReturnMessage.MessageType;
import main.java.akuKaya.models.User;
import main.java.akuKaya.models.UserDetail;
import main.java.akuKaya.services.interfaces.RegisterService;

@Controller

public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/users/register")
	public String register(RegisterForm registerForm, Model theModel, BindingResult bindingResult, @RequestParam("page") Optional<String> errorMsg) {
		if (httpSession.getAttribute("username") != null) {
			return "redirect:/transaction/";
		}
		
		if (bindingResult.hasErrors()) {
			return "/users/register";
		}
		return "users/register";
	}
	
	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public String registerPage(@Valid RegisterForm registerForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/users/register";
		}
		
		// user detail
		String firstName = registerForm.getFirstName();
		String lastName = registerForm.getLastName();
		String email = registerForm.getEmail();
		Date birthdate = registerForm.getBirthdate();
		String address = registerForm.getAddress();
		
		UserDetail userDetail = new UserDetail(firstName, lastName, email, birthdate, address);
		
		// user account
		String username = registerForm.getUsername();
		String password = BCrypt.hashpw(registerForm.getPassword(), BCrypt.gensalt());
		User newUser = new User(username, password, true, username, new Date(), username, new Date(), userDetail);
		
		try {
			ReturnMessage returnMessage = registerService.authenticateUser(newUser);
			if (returnMessage.getMessageType().equals(MessageType.Success)) {
				try {
					registerService.create(newUser);
					return "redirect:/";
				}
				catch (Exception ex) {
					System.out.println(ex.getMessage());
					return "redirect:/users/register";
				}
			}
			else {
				return "redirect:/users/register?errorMsg=" + returnMessage.getMessageText();
			}
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "redirect:/users/register";
		}
		
	}
}
