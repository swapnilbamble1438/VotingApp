package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.User;
import com.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("/createuser")
	public String createUser(@ModelAttribute User user, HttpSession session)
	{
		String email = user.getEmail();
		
		if(userServ.getUserByEmail(email) != null)
		{
			session.setAttribute("fail", "Registration Failed, Please try different Email Id");
			
			return "redirect:/register";
		}
		else{
			 
			userServ.addUser(user);
			session.setAttribute("msg", "Registration successful");
			return "redirect:/register";
		}
		
	}
	
	@GetMapping("/user")
	public String dashboard(Model m, Principal p)
	{
		String email = p.getName(); // 
		
		User user  = userServ.getUserByEmail(email);
		
		m.addAttribute("user",user);
		m.addAttribute("title","DASHBOARD");
		
		return "user/dashboard";
		
		
	}
	
		

}
