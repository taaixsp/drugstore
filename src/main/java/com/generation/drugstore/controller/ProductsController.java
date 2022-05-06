package com.generation.drugstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.drugstore.model.Products;
import com.generation.drugstore.repository.CategoryRepository;
import com.generation.drugstore.repository.ProductsRepository;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProductsController {

	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	@GetMapping
	public ResponseEntity<List<Products>> getAll(){
		return ResponseEntity.ok(productsRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Products> getById(@PathVariable Long id){
		return productsRepository.findById(id)
				.map(response-> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List <Products>> getByName(@PathVariable String name){
		return ResponseEntity.ok(productsRepository.findAllByNameContainingIgnoreCase(name));
	
	}
	
	@GetMapping("/name/{name}/anddescription/{description}")
	public ResponseEntity<List <Products>> getByNameAndDescription(@PathVariable String name, @PathVariable String description){
		return ResponseEntity.ok(productsRepository.findByNameAndDescription(name, description));
	}
	
	@GetMapping("/name/{name}/ordescription/{description}")
	public ResponseEntity<List <Products>> getByNameOrDescription(@PathVariable String name, @PathVariable String description){
		return ResponseEntity.ok(productsRepository.findByNameOrDescription(name, description));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		return productsRepository.findById(id)
				.map(response-> {
					productsRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
					})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Products> postProduct(@Valid @RequestBody Products products){
		if(categoryRepository.existsById(products.getCategory().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(products));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
    public ResponseEntity <Products> putProducts(@Valid @RequestBody Products products){

        if(productsRepository.existsById(products.getId())) {
            if(categoryRepository.existsById(products.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productsRepository.save(products));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
