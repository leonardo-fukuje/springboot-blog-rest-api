package com.springboot.blog.service;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;

public interface PostService {
	PostDTO createPost(PostDTO postDTO);
	
	PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDirection);
	
	PostDTO getPostById(long id);
	
	PostDTO updatePost(PostDTO postDTO, long id);
	
	void deletePostById(long id);

}
