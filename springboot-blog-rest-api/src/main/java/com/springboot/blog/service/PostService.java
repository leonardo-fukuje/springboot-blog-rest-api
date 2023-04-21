package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.PostDTO;

public interface PostService {
	PostDTO createPost(PostDTO postDTO);
	
	List<PostDTO> getAllPost();
	
	PostDTO getPostById(long id);
	
	PostDTO updatePost(PostDTO postDTO, long id);
	
	void deletePostById(long id);

}
