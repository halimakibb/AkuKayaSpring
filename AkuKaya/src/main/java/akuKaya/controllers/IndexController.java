package main.java.akuKaya.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String showForm(Model theModel) {

		return "index";
	}
	
	@RequestMapping("/forbidden")
	public String showForbidden(Model theModel) {

		return "forbidden";
	}
}
