package space.bum.c7_ex1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/demo1")
	@PreAuthorize("hasAuthority('read')")
	String demo1() {
		return "데모1.";
	}
	
	@GetMapping("/demo2")
	@PreAuthorize("hasAuthority('read', 'write')")
	String demo2() {
		return "데모2.";
	}

}
