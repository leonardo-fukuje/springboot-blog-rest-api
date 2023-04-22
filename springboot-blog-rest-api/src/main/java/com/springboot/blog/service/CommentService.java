package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.CommentDTO;

public interface CommentService {
	CommentDTO createComment(long postId, CommentDTO commentDTO);
	
	List<CommentDTO> getCommentsByPostId(long postId);
	
	CommentDTO getCommentById(long postId, long commentId);
	
	CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO);
	
	void deleteComment(long postId, long commentId);
}