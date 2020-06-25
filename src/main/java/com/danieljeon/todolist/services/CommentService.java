package com.danieljeon.todolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.todolist.repositories.CommentRepo;

@Service
public class CommentService {
	@Autowired
	private final CommentRepo commentRepo;
	
	public CommentService(CommentRepo cr) {
		this.commentRepo = cr;
	}
	
	
}
