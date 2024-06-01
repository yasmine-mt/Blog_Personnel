package emsi.blog_personnel.service;



import emsi.blog_personnel.dto.RegistrationDto;
import emsi.blog_personnel.entity.User;

public interface UserService {
	
	void saveUser(RegistrationDto registrationDto);

	User findByEmail(String email);
	
}
