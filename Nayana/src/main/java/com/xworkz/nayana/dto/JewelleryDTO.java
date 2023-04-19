package com.xworkz.nayana.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class JewelleryDTO {
	private int id;
	@NotBlank(message = "Name cannot be null")
	@Size(min = 5, max = 30, message = "Name should be more than 5 or less than 30 characters")
	private String name;
	@NotNull(message = "Price cannot be null or blank")
	@Min(value = 1, message = "Price cannot be less than zero")
	private int price;
	@NotBlank(message = "Color cannot be null")
	@Size(min = 3, max = 20, message = "Color should be more than 3 or less than 20 characters")
	private String color;
	@NotBlank(message = "Type cannot be null")
	@Size(min = 3, max = 20, message = "Type should be more than 3 or less than 20 characters")
	private String type;
	@NotNull(message = "Weight cannot be null or blank")
	@Min(value = 1, message = "Weight cannot be less than zero")
	private int weight;
	
}
