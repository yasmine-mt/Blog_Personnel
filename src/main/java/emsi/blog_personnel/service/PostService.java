package emsi.blog_personnel.service;

import emsi.blog_personnel.dto.*;

import java.util.List;

public interface PostService {
	List<PostDto> findallPosts();
	
	List<PostDto> findPostByUser();
	
	void createPost(PostDto postDto);
	
	PostDto findPostById(Long postId);
	
	void updatePost(PostDto postDto);
	
	void deletePost(Long postId);

	PostDto findPostByUrl(String postUrl);
	
	List<PostDto> searchPosts(String query);
}
