package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Candidate;
import com.model.User;
import com.service.CandidateService;
import com.service.UserService;

@Controller
public class CandidateController {
	
	@Autowired
	private CandidateService canServ;
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("/addcandidate") // vote
	public String addCandidate(@RequestParam("candidate") String candidate,
			Principal p, Model model, HttpSession session)
	{
		String email = p.getName();
		User user = userServ.getUserByEmail(email);
	
		
		if(user.getStatus() == null)
		{
			try {
				// add a vote to the selectedCandidate
				Candidate selectedCan = canServ.getCandidateByCandidate(candidate);
				selectedCan.setVotes(selectedCan.getVotes() + 1);
				canServ.addCandidate(selectedCan); // update candidate
				
				user.setStatus("Voted");
				userServ.addUser(user); // update user
				
				session.setAttribute("vmsg", "Successfully Voted...");
			}
			catch(Exception e)
			{
				session.setAttribute("vmsg", "Something went wrong...");
				e.printStackTrace();
				return "redirect:user/";
			}
			
			
		}
		
		
		return "redirect:user/";
		
	}

}
