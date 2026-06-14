//package com.localservice.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.localservice.model.User;
//import com.localservice.service.UserService;
//
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class AuthController {
//
//	@Autowired
//	private UserService userService;
//
//	@GetMapping("/login")
//	public String loginPage() {
//		return "login";
//	}
//
//	@PostMapping("/login")
//	public String login(@RequestParam String email,
//	                    @RequestParam String password,
//	                    HttpSession session,
//	                    Model model){
//
//	User user = userService.login(email,password);
//
//	if(user == null){
//	model.addAttribute("error","Invalid credentials");
//	return "login";
//	}
//
//	session.setAttribute("loggedUser", user);
//
//	return "redirect:/services";
//	}
//
//	@GetMapping("/dashboard")
//	public String dashboard(HttpSession session) {
//
//		User user = (User) session.getAttribute("loggedUser");
//
//		if (user == null) {
//			return "redirect:/login";
//		}
//
//		return "user-dashboard";
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//
//		session.invalidate();
//
//		return "redirect:/login";
//	}
//}

package com.localservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.localservice.model.User;
import com.localservice.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ❌ REMOVE @GetMapping("/login") — React handles this now

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // keep for error fallback
        }
        session.setAttribute("loggedUser", user);
        return "redirect:/services";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return "redirect:/login";
        }
        return "redirect:/user/dashboard"; // React takes over
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}