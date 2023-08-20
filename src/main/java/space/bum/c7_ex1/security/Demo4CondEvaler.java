package space.bum.c7_ex1.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Demo4CondEvaler {
	public boolean condition(String username) {
		var a = SecurityContextHolder.getContext().getAuthentication();
		return a.getName().equals(username);
	}

}
