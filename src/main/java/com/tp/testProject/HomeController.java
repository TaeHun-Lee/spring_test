package com.tp.testProject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tp.testProject.service.UserService;
import com.tp.testProject.user.User;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	public boolean isUserSignedIn(HttpSession session) {
		boolean isSigned = false;
		User user = (User)session.getAttribute("signedUser");
		if(user != null) isSigned = true;
		return isSigned;
	}
	
	@RequestMapping(value="/userSignIn", method=RequestMethod.POST)
	public String userSignIn(User user, HttpSession session) {
		if(isUserSignedIn(session)) return "signInError";
		User signingUser = userService.userSignIn(user);
		if(signingUser != null) {
			session.setAttribute("signedUser", signingUser);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value="/userSignUp", method=RequestMethod.POST)
	public String userSignUp(User user, HttpSession session) {
		if(isUserSignedIn(session)) return "alreadySigned";
		boolean result = userService.userSignUp(user);
		if(result) {
			User signingUser = userService.userSignIn(user);
			if(signingUser != null) {
				session.setAttribute("signedUser", signingUser);
				return "redirect:/";
			}
			else {
				return "signInError";
			}
		}
		else
			return "signUpError";
	}
	
	@RequestMapping(value="/userModify", method=RequestMethod.POST)
	public String userModify(User user) {
		boolean result = userService.userModify(user);
		if(result == false) return "userModifyingError";
		return "redirect:/";
	}
	
	@RequestMapping(value="/userDelete", method=RequestMethod.POST)
	public String userDelete(User user) {
		boolean result = userService.userDelete(user);
		if(result == false) return "userDeleteError";
		return "redirect:/";
	}
	
	@RequestMapping(value="/userSignOut")
	public String userSignOut(HttpSession session) {
		boolean isUserSigned = isUserSignedIn(session);
		if(isUserSigned) session.invalidate();
		return "redirect:/";
	}
}
