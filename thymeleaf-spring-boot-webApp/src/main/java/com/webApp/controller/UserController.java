package com.webApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webApp.model.User;
import com.webApp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	
	@GetMapping("/users")
	public String homePageHandler(Model model) {
		model.addAttribute("listUsers", service.getAllUsers());
		return "index";
	}
	
	
	@GetMapping("/showNewUserForm")
	public String showUserFormHandler(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "new_user";
	}
	
	@PostMapping("/saveUser")
	public String saveUserHandler(@ModelAttribute("user") User user) {
		service.saveUser(user);
		return "redirect:/users";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteEmployee(@PathVariable (value = "id") Integer id) {
		service.deleteUserById(id);
		return "redirect:/users";
	}
}

