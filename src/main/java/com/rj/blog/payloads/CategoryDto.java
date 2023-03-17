package com.rj.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int categoryId;
	
	@NotBlank
	@Size(min = 4,message="min size of Category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=5,message="min size of category desc is 5")
	private String categoryDescription;

}
