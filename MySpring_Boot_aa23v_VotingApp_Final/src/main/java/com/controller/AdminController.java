package com.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.service.CandidateService;

@Controller
public class AdminController {
	
	@Autowired
	private CandidateService canServ;
	
	@GetMapping("/admin")
	public String dashboard(Model m, Principal p)
	{
		
		// total votes
		int c1 = canServ.getNumOfVotes("candidate1");
		int c2 = canServ.getNumOfVotes("candidate2");
		int c3 = canServ.getNumOfVotes("candidate3");
		int c4 = canServ.getNumOfVotes("candidate4");
		
		
		m.addAttribute("c1", c1);
		m.addAttribute("c2", c2);
		m.addAttribute("c3", c3);
		m.addAttribute("c4", c4);
		
		m.addAttribute("title","DASHBOARD");
		
		
		return "admin/dashboard";
		
		
	}

}
