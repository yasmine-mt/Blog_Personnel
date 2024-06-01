package emsi.blog_personnel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
	
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty(message="Email should not be empty or null")
	@Email
	private String email;
	@NotEmpty(message="Message body should not be empty")
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
