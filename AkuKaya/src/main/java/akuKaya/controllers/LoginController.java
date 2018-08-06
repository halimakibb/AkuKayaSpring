package main.java.akuKaya.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;


import main.java.akuKaya.forms.LoginForm;

@Controller
public class LoginController {
	@GetMapping("/users/login")
	public String login(LoginForm loginForm, Model theModel, BindingResult bindingResult) {
		return "users/login";
	}
}
