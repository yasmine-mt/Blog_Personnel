package emsi.blog_personnel.service.impl;


import emsi.blog_personnel.dto.RegistrationDto;
import emsi.blog_personnel.entity.Role;
import emsi.blog_personnel.entity.User;
import emsi.blog_personnel.repository.RoleRepository;
import emsi.blog_personnel.repository.UserRepository;
import emsi.blog_personnel.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	// dependency
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void saveUser(RegistrationDto registrationDto) {
		
		User user = new User();
		
		user.setName(registrationDto.getFirstName() + registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		// use spring security to encrypt the password
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		Role role = roleRepository.findByName("ROlE_ADMIN");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}



	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
}
