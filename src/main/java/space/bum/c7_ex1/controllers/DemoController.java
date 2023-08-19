package space.bum.c7_ex1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/demo")
	@PreAuthorize("hasAuthority('read')")
	String demo() {
		return "데모.";
	}

}
