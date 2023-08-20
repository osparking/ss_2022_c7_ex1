package space.bum.c7_ex1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo1")
	@PreAuthorize("hasAuthority('read')")
	String demo1() {
		return "데모1.";
	}

	@GetMapping("/demo2")
	@PreAuthorize("hasAnyAuthority('read', 'write')")
	String demo2() {
		return "데모2.";
	}

	@GetMapping("/demo3/{smth}")
	@PreAuthorize("#something == authentication.name")
	String demo3(@PathVariable("smth") String something) {
		StringBuffer sb = new StringBuffer("'");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		sb.append(something);
		sb.append("'를 위한 데모");
		sb.append("auth.name : ");
		sb.append(auth.getName());
		return sb.toString();
	}

}
