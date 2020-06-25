package com.danieljeon.todolist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.todolist.models.Category;
import com.danieljeon.todolist.repositories.CategoryRepo;

@Service
public class CategoryService {
	@Autowired
	private final CategoryRepo categoryRepo;
	
	public CategoryService(CategoryRepo cr) {
		this.categoryRepo = cr;
	}
	
	public List<Category> all() {
		return categoryRepo.findAll();
	}
}
