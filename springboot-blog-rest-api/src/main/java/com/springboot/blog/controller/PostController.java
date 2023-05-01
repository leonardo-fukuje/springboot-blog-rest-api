package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	@PostMapping
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public PostResponse getAllPosts(
		@RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
		@RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
		@RequestParam(value="sortByParameter", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
		@RequestParam(value="sortDirection", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDirection
		
		){
		return postService.getAllPost(pageNo, pageSize, sortBy, sortDirection);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostByID(@PathVariable(name= "id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name= "id") long id){
		PostDTO postResponse = postService.updatePost(postDTO, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletPost(@PathVariable(name= "id") long id){
		postService.deletePostById(id);
		return new ResponseEntity<>("Post deleted sucessfully", HttpStatus.OK);
	}

}
