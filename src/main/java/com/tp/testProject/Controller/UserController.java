package com.tp.testProject.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.testProject.service.UserService;
import com.tp.testProject.userDTO.UserDTO;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public boolean isUserSignedIn(HttpSession session) {
		boolean isSigned = false;
		UserDTO user = (UserDTO)session.getAttribute("signedUser");
		if(user != null) isSigned = true;
		return isSigned;
	}
	
	@ResponseBody
	@RequestMapping(value="/userSignIn", method=RequestMethod.POST)
	public HashMap<String, String> userSignIn(@RequestBody UserDTO user, HttpSession session) {
		System.out.println(user);
		HashMap<String, String> map = new HashMap<String, String>();
		if(isUserSignedIn(session)) {
			map.put("Message", "Already Signed In");
			return map;
		}
		UserDTO signingUser = userService.userSignIn(user);
		if(signingUser != null) {
			map.put("Message", "SignIn Success");
			session.setAttribute("signedUser", signingUser);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userSignUp", method=RequestMethod.POST)
	public HashMap<String, String> userSignUp(@RequestBody UserDTO user, HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		if(isUserSignedIn(session)) {
			map.put("Message", "Already Signed In");
			return map;
		}
		System.out.println(user);
		boolean result = userService.userSignUp(user);
		if(result) {
			UserDTO signingUser = userService.userSignIn(user);
			if(signingUser != null) {
				map.put("Message", "SignUp Success");
				session.setAttribute("signedUser", signingUser);
			}
			else {
				map.put("Message", "SignIn After SignUp Error");
			}
		}
		else {
			map.put("Message", "SignUp Error");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userModify", method=RequestMethod.POST)
	public HashMap<String, String> userModify(@RequestBody UserDTO user) {
		HashMap<String, String> map = new HashMap<String, String>();
		boolean result = userService.userModify(user);
		if(result == false) {
			map.put("Message", "Modify Error");
		}
		else {
			map.put("Message", "Modify Success");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userDelete", method=RequestMethod.POST)
	public HashMap<String, String> userDelete(@RequestBody UserDTO user) {
		HashMap<String, String> map = new HashMap<String, String>();
		boolean result = userService.userDelete(user);
		if(result == false) {
			map.put("Message", "Delete Error");
		}
		else {
			map.put("Message", "Delete Success");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userSignOut")
	public HashMap<String, String> userSignOut(HttpSession session) {
		HashMap<String, String> map = new HashMap<String, String>();
		boolean isUserSigned = isUserSignedIn(session);
		if(isUserSigned) {
			map.put("Message", "SignOut Success");
			session.invalidate();
		}
		else {
			map.put("Message", "Not Sign In");
		}
		return map;
	}
}
