package emsi.blog_personnel.service;

import emsi.blog_personnel.dto.CommentDto;

import java.util.List;

public interface CommentService {
	
	void createComment(String postUrl, CommentDto commentdto);
	
	List<CommentDto> findAllComments();
	
	void deleteComment(Long id);
	
	List<CommentDto> findCommentsByPost();
}
