package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.exception.PostRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;


	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public CommentDTO createComment(long postId, CommentDTO commentDTO) {
		Comment comment = mapToEntity(commentDTO);
		
		//retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("Post", "id", postId));
		
		//Set post to comment entity
		comment.setPost(post);
		
		//save comment entity
		Comment newComment = commentRepository.save(comment);
		return mapToDTO(newComment);
		
	}
	
	
	//convert entity into dto
		private CommentDTO mapToDTO(Comment comment) {
			CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
//			CommentDTO commentDTO = new CommentDTO();
//			commentDTO.setId(comment.getId());
//			commentDTO.setName(comment.getName());
//			commentDTO.setEmail(comment.getEmail());
//			commentDTO.setBody(comment.getBody());
			return commentDTO;
		}
		//convert dto into entity
		private Comment mapToEntity(CommentDTO commentDTO) {
			Comment comment = mapper.map(commentDTO, Comment.class);
//			Comment comment = new Comment();
//			comment.setId(commentDTO.getId());
//			comment.setName(commentDTO.getName());
//			comment.setEmail(commentDTO.getEmail());
//			comment.setBody(commentDTO.getBody());
			return comment;
		}

		@Override
		public List<CommentDTO> getCommentsByPostId(long postId) {
			//Retrieve comments by postId
			List<Comment> comments = commentRepository.findByPostId(postId);
			
			
			return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
		}

		@Override
		public CommentDTO getCommentById(long postId, long commentId) {
			//retrieve post entity by id
			Post post = postRepository.findById(postId).orElseThrow(()->
			new ResourceNotFoundException("Post", "id", postId));
			
			//retrieve comment by Id
			Comment comment = commentRepository.findById(commentId).orElseThrow(()->
			new ResourceNotFoundException("Comment", "id", commentId));
			
			if(!comment.getPost().getId().equals(post.getId())) {
				throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
			}
			
			return mapToDTO(comment);
		}

		@Override
		public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO) {
			//retrieve post entity by id
			Post post = postRepository.findById(postId).orElseThrow(()->
			new ResourceNotFoundException("Post", "id", postId));
			//retrieve comment by Id
			Comment comment = commentRepository.findById(commentId).orElseThrow(()->
			new ResourceNotFoundException("Comment", "id", commentId));
			
			if(!comment.getPost().getId().equals(post.getId())) {
				throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
			}
			comment.setName(commentDTO.getName());
			comment.setEmail(commentDTO.getEmail());
			comment.setBody(commentDTO.getBody());
			
			Comment updatedComment = commentRepository.save(comment);
			return mapToDTO(updatedComment);
		}

		@Override
		public void deleteComment(long postId, long commentId) {
			//retrieve post entity by id
			Post post = postRepository.findById(postId).orElseThrow(()->
			new ResourceNotFoundException("Post", "id", postId));
			//retrieve comment by Id
			Comment comment = commentRepository.findById(commentId).orElseThrow(()->
			new ResourceNotFoundException("Comment", "id", commentId));		
			
			if(!comment.getPost().getId().equals(post.getId())) {
				throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
				
		}

			commentRepository.delete(comment);
		}}
