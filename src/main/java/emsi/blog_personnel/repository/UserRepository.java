package emsi.blog_personnel.repository;

import emsi.blog_personnel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}
