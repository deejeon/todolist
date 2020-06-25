package com.danieljeon.todolist.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.danieljeon.todolist.models.Task;
import com.danieljeon.todolist.models.User;
import com.danieljeon.todolist.services.CommentService;
import com.danieljeon.todolist.services.TaskService;
import com.danieljeon.todolist.services.UserService;
import com.danieljeon.todolist.validators.UserValidator;

@Controller
public class MainController {
	@Autowired
	private final UserService userService;
	@Autowired
	private final TaskService taskService;
	@Autowired
	private final CommentService commentService;
	
	@Autowired
	private final UserValidator userValidator;
	
	public MainController(UserService us, TaskService ts, CommentService cs, UserValidator uv) {
		this.userService = us;
		this.taskService = ts;
		this.commentService = cs;
		this.userValidator = uv;
	}
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		if (session.getAttribute("userId") != null) {
			return "redirect:/tasks";
		}
		return "redirect:/home";
	}
	
	@RequestMapping("/home")
	public String home(HttpSession session) {
		if (session.getAttribute("userId") != null) {
			return "redirect:/tasks";
		}
		return "home.jsp";
	}
	
	@RequestMapping("/users/showregister")
	public String registerPage(HttpSession session, @ModelAttribute("user") User user) {
		if (session.getAttribute("userId") != null) {
			return "redirect:/tasks";
		}
		return "register.jsp";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) return "register.jsp";
		User u = userService.registerUser(user);
		session.setAttribute("userId", u.getId());
		return "redirect:/tasks";
	}
	
	@RequestMapping("/users/showlogin")
	public String loginPage(HttpSession session, @ModelAttribute("user") User user) {
		if (session.getAttribute("userId") != null) {
			return "redirect:/tasks";
		}
		return "login.jsp";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(HttpSession session, Model model, @ModelAttribute("user") User user, @RequestParam("email") String email, @RequestParam("password") String password) {
		// if email or password is empty
		if (email.equals("") || password.equals("")) {
			String error = "Please enter email and password";
			model.addAttribute("error", error);
			return "login.jsp";
		}
		
		// if the user is authenticated, save their user id in session
    	if (userService.authenticateUser(email, password)) {
    		session.setAttribute("userId", userService.findByEmail(email).getId());
    		return "redirect:/tasks";
    	}
        // else, add error messages and return the login page
    	else {
    		String error = "Incorrect email or password";
    		model.addAttribute("error", error);
    		return "login.jsp";
    	}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/tasks")
	public String tasksToday(HttpSession session, Model model) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		model.addAttribute("currentUser", currentUser);
		return "alltasks.jsp";
	}
}
