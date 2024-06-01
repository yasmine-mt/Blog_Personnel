package emsi.blog_personnel.mapper;

import emsi.blog_personnel.dto.PostDto;
import emsi.blog_personnel.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {
	
	//Post entity to postDto
	public static PostDto mapToPostDto(Post post) {
		
		return  PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.url(post.getUrl())
				.content(post.getContent())
				.shortDescription(post.getShortDescription())
				.createdOn(post.getCreatedOn())
				.updatedOn(post.getUpdatedOn())
				.comments(post.getComments().stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toSet()))
				.build();	
	} 
	 
	//postDto to post entity
	
	public static Post mapToPost(PostDto postDto) {
		return  Post.builder()
				.id(postDto.getId())
				.title(postDto.getTitle())
				.url(postDto.getUrl())
				.content(postDto.getContent())
				.shortDescription(postDto.getShortDescription())
				.createdOn(postDto.getCreatedOn())
				.updatedOn(postDto.getUpdatedOn())
				.build();
	} 
}
