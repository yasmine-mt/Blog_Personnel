package emsi.blog_personnel.controller;

import  emsi.blog_personnel.dto.CommentDto;
import  emsi.blog_personnel.dto.PostDto;
import emsi.blog_personnel.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class BlogController {

	PostService postService;

	public BlogController(PostService postService) {
		this.postService = postService;
	}

	// handler method to handle localhost:8080
	@GetMapping("/")
	public String viewBlogPosts(Model model) {
		List<PostDto> postResponse = postService.findallPosts();
		model.addAttribute("postResponse", postResponse);
		return "blog/view_posts";
	}


	// handler method to handle view blog
	@GetMapping("/post/{postUrl}")
	public String show_post(@PathVariable String postUrl, Model model) {
		PostDto post = postService.findPostByUrl(postUrl);
		CommentDto commentDto = new CommentDto();
		model.addAttribute("post", post);
		model.addAttribute("comment", commentDto);
		return "blog/blog_post";
	}


	// handler method to handle search blog
	@GetMapping("/page/search")
	public String searchPost(@RequestParam String query, Model model) {
		List<PostDto> postResponse = postService.searchPosts(query);
		model.addAttribute("postResponse", postResponse);
		return "blog/view_posts";
	}


}