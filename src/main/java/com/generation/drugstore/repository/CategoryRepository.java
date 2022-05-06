package com.generation.drugstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.drugstore.model.Category;

@Repository

public interface CategoryRepository extends JpaRepository <Category, Long> {
	
	public List <Category> findAllByNameContainingIgnoreCase (@Param ("name")String name);
	

}
