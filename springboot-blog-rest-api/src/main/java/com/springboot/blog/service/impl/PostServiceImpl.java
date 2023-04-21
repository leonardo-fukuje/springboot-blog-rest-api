package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.repository.exception.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	//Serve par omitir Autowired annotation
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		// Convert DTO to Entity
		Post post = mapToEntity(postDTO);
		Post newPost = postRepository.save(post);
		//Convert entity into dto
		PostDTO postResponse = mapToDTO(newPost);

		return postResponse;
	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		
	}
	
//convert entity into dto
	private PostDTO mapToDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setTitle(post.getTitle());
		postDTO.setDescription(post.getDescription());
		postDTO.setContent(post.getContent());
		return postDTO;
	}
	
	private Post mapToEntity(PostDTO postDTO) {
		Post post = new Post();
		post.setId(postDTO.getId());
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		return post;
	}

	@Override
	public PostDTO getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		return mapToDTO(post);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setDescription(postDTO.getDescription());
		
		Post updatePost = postRepository.save(post);
		return mapToDTO(updatePost);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
	}

}
