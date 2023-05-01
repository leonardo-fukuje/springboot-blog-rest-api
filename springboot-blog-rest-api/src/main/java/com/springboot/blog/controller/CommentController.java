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

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId,
			@Valid @RequestBody CommentDTO commentDTO) {

		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);

	}

	@GetMapping("/posts/{postId}/comments")
	public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {

		return commentService.getCommentsByPostId(postId);
	}

	@GetMapping("posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "id") Long commentId) {
		CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}
	
	@PutMapping("posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId")Long postId,
													@PathVariable(value = "id") Long commentId,
													@Valid @RequestBody CommentDTO commentDTO){
		
		CommentDTO updateComment = commentService.updateComment(postId, commentId,commentDTO);
		return new ResponseEntity<>(updateComment, HttpStatus.OK);
		
	}
	
	@DeleteMapping("posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId")Long postId,
													@PathVariable(value = "id") Long commentId){
		
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment deleted sucessfully", HttpStatus.OK);
		
	}

}
