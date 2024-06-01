package emsi.blog_personnel.security;

import emsi.blog_personnel.entity.User;
import emsi.blog_personnel.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	
	//dependency
	public CustomUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			org.springframework.security.core.userdetails.User authenticatedUser = new org.springframework.security.core.userdetails.User(
						user.getEmail(),
						user.getPassword(),
						user.getRoles().stream()
							.map((role) -> new SimpleGrantedAuthority(role.getName()))
							.collect(Collectors.toList())
					); 
			
			return authenticatedUser;
		}else {
			throw new UsernameNotFoundException("Invalid Username and password");
		}
		
	}

}
