package emsi.blog_personnel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long id;
	@NotEmpty(message = "Post Title should not be empty")
	private String title;
	private String url;
	@NotEmpty(message = "Post content should not be empty")
	private String content;
	@NotEmpty(message = "Post short description should not be empty")
	private String shortDescription;

	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;

	// this will we used when we want to display commments for that post
	private Set<CommentDto> comments;
}
