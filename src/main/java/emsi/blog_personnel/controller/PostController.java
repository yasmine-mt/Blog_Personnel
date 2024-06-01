package emsi.blog_personnel.controller;

import emsi.blog_personnel.dto.CommentDto;
import emsi.blog_personnel.dto.PostDto;
import emsi.blog_personnel.service.CommentService;
import emsi.blog_personnel.service.PostService;
import emsi.blog_personnel.util.ROLE;
import emsi.blog_personnel.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@Controller
public class PostController {

	private PostService postService;
	private CommentService commentService;

	//dependency
	public PostController(PostService postService, CommentService commentService) {
		super();
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping("/admin/posts")
	public String getPosts(Model model) {
		List<PostDto> posts = null;
		String role = SecurityUtils.getRole();
		// if user is admin user he should see all posts and comments
		if(ROLE.ROLE_ADMIN.name().equals(role)) {
			posts = postService.findallPosts();
		}else {
			posts = postService.findPostByUser();
		}
		model.addAttribute("posts", posts);
		return "/admin/posts";
	}

	// handler method to handle list comment reqeust
	@GetMapping("/admin/posts/comments")
	public String postComments(Model model) {
		String role = SecurityUtils.getRole();
		List<CommentDto> comments = null;
		// if user is admin he should see all the comments
		if(ROLE.ROLE_ADMIN.name().equals(role)) {
			comments = commentService.findAllComments();
		}
		else {
			comments = commentService.findCommentsByPost();
		}
		model.addAttribute("comments", comments);
		return "admin/comments";
	}


	//handler method to delete comment(only admin can delete)
	@GetMapping("/admin/posts/comments/{commentId}")
	public String deleteComment(@PathVariable Long commentId, Model model) {
		commentService.deleteComment(commentId);
		return "redirect:/admin/posts/comments";
	}


	// handler method to handle new post request (GET)
	@GetMapping("/admin/posts/newpost")
	public String newPostForm(Model model) {
		PostDto postDto = new PostDto();
		model.addAttribute("post", postDto);
		return "/admin/create_post";

	}

	// handler method to handle form submit request
	@PostMapping("/admin/posts")
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) { //@Valid for validation
		if(result.hasErrors()) {                                                                                  //BindingResult to check error and return to UI
			model.addAttribute("post", postDto);
			return "admin/create_post";
		}
		postDto.setUrl(getUrl(postDto.getTitle()));
		postService.createPost(postDto);
		return "redirect:/admin/posts";
	}

	// creating url using title of the post
	private static String getUrl(String postTitle) {
		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[^A-Za-z0-9]", "-");
		return url;
	}

	// Edit request (GET)
	@GetMapping("/admin/posts/{postId}/edit")
	public String editRequest(@PathVariable Long postId, Model model) {
		PostDto postDto = postService.findPostById(postId);
		model.addAttribute("post", postDto);
		return "admin/edit_post";
	}

	//Edit form submit request (POST)
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable Long postId, @Valid @ModelAttribute("post") PostDto post, Model model, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("post", post);
			return "admin/edit_post";
		}
		post.setId(postId);
		postService.updatePost(post);
		return "redirect:/admin/posts";
	}

	// handling Delete request
	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable Long postId) {
		postService.deletePost(postId);
		return  "redirect:/admin/posts";
	}

	// handling view post request
	@GetMapping("/admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable String postUrl, Model model) {
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post", postDto);
		return "admin/view_post";
	}

	// handling blog search posts request
	@GetMapping("/admin/posts/search")
	public String searchPosts(@RequestParam String query, Model model) {
		List<PostDto> posts = postService.searchPosts(query);
		model.addAttribute("posts", posts);
		return "admin/posts";
	}
}