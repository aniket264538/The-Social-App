package com.socialapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.socialapp.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
}
