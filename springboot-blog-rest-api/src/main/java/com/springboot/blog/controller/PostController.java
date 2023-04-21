package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	@PostMapping
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PostDTO> getAllPosts(){
		return postService.getAllPost();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostByID(@PathVariable(name= "id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable(name= "id") long id){
		PostDTO postResponse = postService.updatePost(postDTO, id);
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletPost(@PathVariable(name= "id") long id){
		postService.deletePostById(id);
		return new ResponseEntity<>("Post deleted sucessfully", HttpStatus.OK);
	}

}
