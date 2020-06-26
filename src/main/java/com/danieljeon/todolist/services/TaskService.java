package com.danieljeon.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.todolist.models.Category;
import com.danieljeon.todolist.models.Task;
import com.danieljeon.todolist.models.User;
import com.danieljeon.todolist.repositories.TaskRepo;

@Service
public class TaskService {
	@Autowired
	private final TaskRepo taskRepo;
	
	public TaskService(TaskRepo tr) {
		this.taskRepo = tr;
	}
	
	public List<Task> all() {
		return taskRepo.findAllByOrderByDeadlineAsc();
	}
	
	public List<Task> allByAssigneeAndCreatorNot(User assignee, User creator) {
		return taskRepo.findByAssigneeAndCreatorNot(assignee, creator);
	}
	
	public List<Task> allByCreator(User user) {
		return taskRepo.findByCreatorOrderByDeadlineAsc(user);
	}
	
	public List<Task> allByCategory(Category category) {
		return taskRepo.findByCategory(category);
	}
	
	public List<Task> allByPriorityAndCreator(String priority, User user) {
		return taskRepo.findByPriorityAndCreator(priority, user);
	}
	
	public List<Task> allByPriorityAndAssigneeAndCreatorNot(String priority, User assignee, User creator) {
		return taskRepo.findByPriorityAndAssigneeAndCreatorNot(priority, assignee, creator);
	}
	
	public Task create(Task task) {
		return taskRepo.save(task);
	}
	
	public Task addCreator(Task task, User user) {
		task.setCreator(user);
		return taskRepo.save(task);
	}
	
	public Task findTaskById(Long id) {
		Optional<Task> optionalTask = taskRepo.findById(id);
		if (optionalTask.isPresent()) return optionalTask.get();
		else return null;
	}
	
	public Task update(Task currentTask, Task eTask) {
		Task updatedTask = this.findTaskById(currentTask.getId());
		updatedTask.setTitle(eTask.getTitle());
		updatedTask.setAssignee(eTask.getAssignee());
		updatedTask.setPriority(eTask.getPriority());
		updatedTask.setDeadline(eTask.getDeadline());
		updatedTask.setCompleted(eTask.isCompleted());
		return taskRepo.save(currentTask);
	}
	
	public void delete(Long id) {
		taskRepo.deleteById(id);
		return;
	}
	
	public void deleteAll() {
		taskRepo.deleteAll();
	}
}
