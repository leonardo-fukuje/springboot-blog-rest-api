package com.springboot.blog.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {
	private Long id;
	
	@NotEmpty
	@Size(min = 2, message ="Post title should held at leats 2 characters")
	private String title;
	@NotEmpty
	@Size(min = 2, message ="Post description should held at leats 10 characters")
	private String description;
	@NotEmpty
	private String content;
	private Set<CommentDTO> comments;
}
