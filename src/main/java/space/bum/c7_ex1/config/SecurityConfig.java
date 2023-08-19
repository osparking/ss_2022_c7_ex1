package space.bum.c7_ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.httpBasic().and()
				.authorizeRequests().anyRequest().authenticated().and()
				.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails park = User.withUsername("park")
				.password(passwordEncoder().encode("1234"))
				.authorities("read")
				.build();
		
		var udm = new InMemoryUserDetailsManager();
		udm.createUser(park);
		
		return udm;
	}
	
	@Bean PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
