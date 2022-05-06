package com.generation.drugstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.drugstore.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository <Products, Long>{
	
	public List <Products> findAllByNameContainingIgnoreCase(@Param("name") String name);
	
	public List <Products> findByNameAndDescription(String name, String description);

	public List <Products> findByNameOrDescription(String name, String description);
}
