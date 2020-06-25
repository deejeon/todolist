package com.danieljeon.todolist.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.danieljeon.todolist.models.Comment;
import com.danieljeon.todolist.models.Task;
import com.danieljeon.todolist.models.User;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
	List<Comment> findAll();
	List<Comment> findByTask(Task task);
	List<Comment> findByWriter(User user);
}
