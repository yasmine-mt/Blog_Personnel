package emsi.blog_personnel.repository;

import emsi.blog_personnel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByName(String name);
}
