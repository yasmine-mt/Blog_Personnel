package emsi.blog_personnel.mapper;

import emsi.blog_personnel.dto.CommentDto;
import emsi.blog_personnel.entity.Comment;

public class CommentMapper {
	
	// convert comment entity to commentdto
	public static CommentDto mapToCommentDto(Comment comment) {
		return CommentDto.builder()
				.id(comment.getId())
				.name(comment.getName())
				.email(comment.getEmail())
				.content(comment.getContent())
				.createdOn(comment.getCreatedOn())
				.updatedOn(comment.getUpdatedOn())
				.build();
	}
	 
	//convert commentDto to comment
	public static Comment mapToComment(CommentDto commentDto) {
		return Comment.builder()
				.id(commentDto.getId())
				.name(commentDto.getName())
				.email(commentDto.getEmail())
				.content(commentDto.getContent())
				.createdOn(commentDto.getCreatedOn())
				.updatedOn(commentDto.getUpdatedOn())
				.build();
	}
} 
