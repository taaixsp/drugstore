package com.generation.drugstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_products")

public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Atribute name is mandatory.")
	@Size(min = 5, max = 300, message = "Atribute name must have minimum 5 and maximum 300 characters")
	private String name;
	
	@NotBlank(message = "Atribute description is mandatory.")
	@Size(min = 5, max = 300, message = "Atribute description must have minimum 5 and maximum 300 characters")
	private String description;
	
	@NotNull(message = "Atribute quantity is mandatory.")
	@Positive(message = "Atribute quantity must be positive.")
	private int quantity;
	
	@NotNull(message = "Atribute price is mandatory.")
	@Positive(message = "Atribute price must be positive.")
	private double price;
	
	private String image;
	
	@ManyToOne
	@JsonIgnoreProperties("products")
	private Category category;
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
