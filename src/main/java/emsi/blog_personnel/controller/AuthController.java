package emsi.blog_personnel.controller;

import emsi.blog_personnel.dto.RegistrationDto;
import emsi.blog_personnel.entity.User;
import emsi.blog_personnel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

	private UserService userService;

	//dependency
	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}

	// handler method to handle login page request
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {

		// this object holds form data
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}


	// handler method to handle user registration form submit request
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
		// check for unique email
		User existingUser = userService.findByEmail(user.getEmail());
		if(existingUser != null) {
			result.rejectValue("email", null, "There is already a user with same email id");
		}
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		userService.saveUser(user);
		return "redirect:/register?success";
	}
}
