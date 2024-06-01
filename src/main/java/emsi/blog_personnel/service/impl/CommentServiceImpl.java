package emsi.blog_personnel.service.impl;

import emsi.blog_personnel.dto.CommentDto;
import emsi.blog_personnel.entity.Comment;
import emsi.blog_personnel.entity.Post;
import emsi.blog_personnel.entity.User;
import emsi.blog_personnel.mapper.CommentMapper;
import emsi.blog_personnel.repository.CommentRepository;
import emsi.blog_personnel.repository.PostRepository;
import emsi.blog_personnel.repository.UserRepository;
import emsi.blog_personnel.service.CommentService;
import emsi.blog_personnel.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	
	// constructor based dependency
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}


	@Override
	public void createComment(String postUrl, CommentDto commentdto) {
		// we are retrieving this post entity because whenever we save
		// the comment object we need to also this post object to the
		// comment jpa entity because this is one to many relationship
		Post post = postRepository.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentdto);
		comment.setPost(post);
		commentRepository.save(comment);
		
		
		
	}


	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList());
	}


	@Override
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}


	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment> comments = commentRepository.findCommentsByPost(userId);
		return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList()); 
	}

}
