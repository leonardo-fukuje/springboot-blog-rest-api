package com.springboot.blog.payload;

import java.util.Set;

import lombok.Data;

@Data
public class PostDTO {
	private Long id;
	private String title;
	private String description;
	private String content;
	private Set<CommentDTO> comments;
}
