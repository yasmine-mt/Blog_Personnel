package emsi.blog_personnel.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUtils {
	
	public static User getCurrentUser() {
		// this principal object contians the username, password and roles 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof User) {
			return (User) principal;
		}
		return null;
	}
	
	public static String getRole() {
		User user = getCurrentUser();
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		for(GrantedAuthority authority: authorities) {
			return authority.getAuthority();
		}
		return null;
	}
}
