package space.bum.c7_ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
				.password("1234")
				.authorities("read")
				.build();
		return null;
	}

}