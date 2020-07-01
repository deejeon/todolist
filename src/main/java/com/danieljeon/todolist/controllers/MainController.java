package com.danieljeon.todolist.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.danieljeon.todolist.models.Category;
import com.danieljeon.todolist.models.Task;
import com.danieljeon.todolist.models.User;
import com.danieljeon.todolist.services.CategoryService;
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
	private final CategoryService categoryService;
	
	@Autowired
	private final UserValidator userValidator;
	
	public MainController(UserService us, TaskService ts, CommentService cs, CategoryService catS, UserValidator uv) {
		this.userService = us;
		this.taskService = ts;
		this.commentService = cs;
		this.categoryService = catS;
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
		List<Category> allCategories = categoryService.all();
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		List<Task> assignedTasks = taskService.allByAssigneeAndCreatorNot(currentUser, currentUser);
		
		List<Task> highTasks = taskService.allByPriorityAndCreator("high", currentUser);
		List<Task> mediumTasks = taskService.allByPriorityAndCreator("medium", currentUser);
		List<Task> lowTasks = taskService.allByPriorityAndCreator("low", currentUser);
		
		List<Task> highAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("high", currentUser, currentUser);
		List<Task> mediumAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("medium", currentUser, currentUser);
		List<Task> lowAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("low", currentUser, currentUser);
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("createdTasks", createdTasks);
		model.addAttribute("assignedTasks", assignedTasks);
		model.addAttribute("highTasks", highTasks);
		model.addAttribute("mediumTasks", mediumTasks);
		model.addAttribute("lowTasks", lowTasks);
		model.addAttribute("highAssignedTasks", highAssignedTasks);
		model.addAttribute("mediumAssignedTasks", mediumAssignedTasks);
		model.addAttribute("lowAssignedTasks", lowAssignedTasks);
		
		return "alltasks.jsp";
	}
	
	@RequestMapping("/tasks/new")
	public String newTaskPage(HttpSession session, Model model, @ModelAttribute("task") Task task) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		List<User> allUsers = userService.all();
		List<Category> allCategories = categoryService.all();
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		List<Task> assignedTasks = taskService.allByAssigneeAndCreatorNot(currentUser, currentUser);
		
		List<Task> highTasks = taskService.allByPriorityAndCreator("high", currentUser);
		List<Task> mediumTasks = taskService.allByPriorityAndCreator("medium", currentUser);
		List<Task> lowTasks = taskService.allByPriorityAndCreator("low", currentUser);
		
		List<Task> highAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("high", currentUser, currentUser);
		List<Task> mediumAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("medium", currentUser, currentUser);
		List<Task> lowAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("low", currentUser, currentUser);
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("createdTasks", createdTasks);
		model.addAttribute("assignedTasks", assignedTasks);
		model.addAttribute("highTasks", highTasks);
		model.addAttribute("mediumTasks", mediumTasks);
		model.addAttribute("lowTasks", lowTasks);
		model.addAttribute("highAssignedTasks", highAssignedTasks);
		model.addAttribute("mediumAssignedTasks", mediumAssignedTasks);
		model.addAttribute("lowAssignedTasks", lowAssignedTasks);
		return "newtask.jsp";
	}
	
	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public String createTask(HttpSession session, Model model, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		List<User> allUsers = userService.all();
		List<Category> allCategories = categoryService.all();
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("createdTasks", createdTasks);
		
		if (result.hasErrors()) {
			return "newtask.jsp";
		}
		
		Task createdTask = taskService.create(task);
		taskService.addCreator(createdTask, currentUser);
		return "redirect:/tasks";
	}
	
	@RequestMapping("/tasks/{priority}-priority")
	public String tasksByPriority(HttpSession session, Model model, @PathVariable("priority") String currentPriority) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		List<Category> allCategories = categoryService.all();
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		List<Task> assignedTasks = taskService.allByAssigneeAndCreatorNot(currentUser, currentUser);
		
		List<Task> highTasks = taskService.allByPriorityAndCreator("high", currentUser);
		List<Task> mediumTasks = taskService.allByPriorityAndCreator("medium", currentUser);
		List<Task> lowTasks = taskService.allByPriorityAndCreator("low", currentUser);
		
		List<Task> highAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("high", currentUser, currentUser);
		List<Task> mediumAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("medium", currentUser, currentUser);
		List<Task> lowAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("low", currentUser, currentUser);
		
		List<Task> currentTasks = new ArrayList<>();
		List<Task> currentAssignedTasks = new ArrayList<>();
		
		boolean isHigh = false;
		boolean isMedium = false;
		boolean isLow = false;
		
		String displayedPriority;
		if (currentPriority.equals("high")) {
			currentTasks = highTasks;
			currentAssignedTasks = highAssignedTasks;
			displayedPriority = "High";
			isHigh = true;
		}
		else if (currentPriority.equals("medium")) {
			currentTasks = mediumTasks;
			currentAssignedTasks = mediumAssignedTasks;
			displayedPriority = "Medium";
			isMedium = true;
		}
		else {
			currentTasks = lowTasks;
			currentAssignedTasks = lowAssignedTasks;
			displayedPriority = "Low";
			isLow = true;
		}
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("createdTasks", createdTasks);
		model.addAttribute("assignedTasks", assignedTasks);
		model.addAttribute("highTasks", highTasks);
		model.addAttribute("mediumTasks", mediumTasks);
		model.addAttribute("lowTasks", lowTasks);
		model.addAttribute("highAssignedTasks", highAssignedTasks);
		model.addAttribute("mediumAssignedTasks", mediumAssignedTasks);
		model.addAttribute("lowAssignedTasks", lowAssignedTasks);
		model.addAttribute("currentTasks", currentTasks);
		model.addAttribute("currentAssignedTasks", currentAssignedTasks);
		model.addAttribute("displayedPriority", displayedPriority);
		model.addAttribute("isHigh", isHigh);
		model.addAttribute("isMedium", isMedium);
		model.addAttribute("isLow", isLow);
		return "prioritytasks.jsp";
	}
	
	@RequestMapping("/tasks/{taskId}")
	public String showTaskPage(HttpSession session, Model model, @PathVariable("taskId") Long taskId) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		Task currentTask = taskService.findTaskById(taskId);
		boolean isCreator = (userId == currentTask.getCreator().getId());
		boolean isAssignee = (userId == currentTask.getAssignee().getId());
		boolean canComplete = (!currentTask.isCompleted() && isAssignee);
		Date currentTaskDeadline = currentTask.getDeadline();
		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
		String formattedDeadline = ft.format(currentTaskDeadline);
		
		List<Category> allCategories = categoryService.all();
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		List<Task> assignedTasks = taskService.allByAssigneeAndCreatorNot(currentUser, currentUser);

		List<Task> highTasks = taskService.allByPriorityAndCreator("high", currentUser);
		List<Task> mediumTasks = taskService.allByPriorityAndCreator("medium", currentUser);
		List<Task> lowTasks = taskService.allByPriorityAndCreator("low", currentUser);
		
		List<Task> highAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("high", currentUser, currentUser);
		List<Task> mediumAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("medium", currentUser, currentUser);
		List<Task> lowAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("low", currentUser, currentUser);
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("currentTask", currentTask);
		model.addAttribute("isCreator", isCreator);
		model.addAttribute("canComplete", canComplete);
		model.addAttribute("formattedDeadline", formattedDeadline);
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("createdTasks", createdTasks);
		model.addAttribute("assignedTasks", assignedTasks);
		model.addAttribute("highTasks", highTasks);
		model.addAttribute("mediumTasks", mediumTasks);
		model.addAttribute("lowTasks", lowTasks);
		model.addAttribute("highAssignedTasks", highAssignedTasks);
		model.addAttribute("mediumAssignedTasks", mediumAssignedTasks);
		model.addAttribute("lowAssignedTasks", lowAssignedTasks);
		
		return "showtask.jsp";
	}
	
	@RequestMapping(value="/tasks/{taskId}", method=RequestMethod.DELETE)
	public String deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.delete(taskId);
		return "redirect:/tasks";
	}
	
	@RequestMapping(value="/tasks/{taskId}/complete", method=RequestMethod.POST)
	public String completeTask(@PathVariable("taskId") Long taskId) {
		taskService.complete(taskId);
		return "redirect:/tasks";
	}
	
	@RequestMapping("/tasks/{taskId}/edit")
	public String editTaskPage(HttpSession session, Model model, @PathVariable("taskId") Long taskId, @ModelAttribute("eTask") Task eTask) {
		if (session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long) session.getAttribute("userId");
		User currentUser = userService.findUserById(userId);
		Task currentTask = taskService.findTaskById(taskId);
		boolean isCreator = (userId == currentTask.getCreator().getId());
		
		if (!isCreator) return "redirect:/tasks";
		
		Date currentTaskDeadline = currentTask.getDeadline();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDeadline = ft.format(currentTaskDeadline);
		
		List<User> allUsers = userService.all();
		
		List<Category> allCategories = categoryService.all();
		List<Task> createdTasks = taskService.allByCreator(currentUser);
		List<Task> assignedTasks = taskService.allByAssigneeAndCreatorNot(currentUser, currentUser);

		List<Task> highTasks = taskService.allByPriorityAndCreator("high", currentUser);
		List<Task> mediumTasks = taskService.allByPriorityAndCreator("medium", currentUser);
		List<Task> lowTasks = taskService.allByPriorityAndCreator("low", currentUser);
		
		List<Task> highAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("high", currentUser, currentUser);
		List<Task> mediumAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("medium", currentUser, currentUser);
		List<Task> lowAssignedTasks = taskService.allByPriorityAndAssigneeAndCreatorNot("low", currentUser, currentUser);
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("currentTask", currentTask);
		model.addAttribute("isCreator", isCreator);
		model.addAttribute("formattedDeadline", formattedDeadline);
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("allCategories", allCategories);
		model.addAttribute("createdTasks", createdTasks);
		model.addAttribute("assignedTasks", assignedTasks);
		model.addAttribute("highTasks", highTasks);
		model.addAttribute("mediumTasks", mediumTasks);
		model.addAttribute("lowTasks", lowTasks);
		model.addAttribute("highAssignedTasks", highAssignedTasks);
		model.addAttribute("mediumAssignedTasks", mediumAssignedTasks);
		model.addAttribute("lowAssignedTasks", lowAssignedTasks);
		
		return "edittask.jsp";
	}
	
//	@RequestMapping("/api/categories")
//	public String showCategories(Model model) {
//		List<Category> allCategories = categoryService.all();
//		model.addAttribute("allCategories", allCategories);
//		return "categories.jsp";
//	}
//	
//	@RequestMapping("/api/categories/new")
//	public String newCategoryPage(@ModelAttribute("category") Category category) {
//		return "newcategory.jsp";
//	}
//	
//	@RequestMapping(value = "/api/categories", method = RequestMethod.POST)
//	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
//		if (result.hasErrors()) return "newcategory.jsp";
//		categoryService.create(category);
//		return "redirect:/api/categories";
//	}
//	
//	@RequestMapping(value = "/api/categories", method = RequestMethod.DELETE)
//	public String deleteAllCategories() {
//		categoryService.deleteAll();
//		return "redirect:/api/categories";
//	}
//	
//	@RequestMapping(value = "/api/tasks", method = RequestMethod.DELETE)
//	public void deleteAllTasks() {
//		taskService.deleteAll();
//	}
}
