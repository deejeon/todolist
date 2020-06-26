package com.danieljeon.todolist.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieljeon.todolist.models.Category;
import com.danieljeon.todolist.models.Task;
import com.danieljeon.todolist.models.User;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
	List<Task> findAllByOrderByDeadlineAsc();
	List<Task> findByAssigneeAndCreatorNot(User assignee, User creator);
	List<Task> findByCreatorOrderByDeadlineAsc(User user);
	List<Task> findByCategory(Category category);
	List<Task> findByPriority(String priority);
	List<Task> findByPriorityAndCreator(String priority, User user);
	List<Task> findByPriorityAndAssigneeAndCreatorNot(String priority, User assignee, User creator);
}
