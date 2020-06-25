package com.danieljeon.todolist.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieljeon.todolist.models.Category;
import com.danieljeon.todolist.models.Task;
import com.danieljeon.todolist.models.User;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
	List<Task> findAll();
	List<Task> findByAssignee(User user);
	List<Task> findByCreator(User user);
	List<Task> findByCategory(Category category);
}
