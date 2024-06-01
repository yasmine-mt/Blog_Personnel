package emsi.blog_personnel.controller;

import  emsi.blog_personnel.dto.CommentDto;
import  emsi.blog_personnel.dto.PostDto;
import   emsi.blog_personnel.service.CommentService;
import  emsi.blog_personnel.service.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
	
	private CommentService commentService;
	private PostService postservice;
	
	// dependency
	public CommentController(CommentService commentService, PostService postservice) {
		this.commentService = commentService;
		this.postservice = postservice;
	}

	//handler method to handle comment
		@PostMapping("/{postUrl}/comments")
		public String saveComment(@PathVariable String postUrl, @Valid @ModelAttribute("comment") CommentDto commentDto, BindingResult result, Model model) {
			PostDto postDto = postservice.findPostByUrl(postUrl);
			
			if(result.hasErrors()) {
				model.addAttribute("comment", commentDto);
				model.addAttribute("post", postDto);
				return "blog/blog_post";
			}
			commentService.createComment(postUrl, commentDto);
			//we have written a handler method to view post request
			// in that we have also added comment object
			// so we redirect to that handler method in blogcontroller
			return "redirect:/post/" + postUrl;
		}


}
