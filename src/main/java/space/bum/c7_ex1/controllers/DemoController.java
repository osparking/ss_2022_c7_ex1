package space.bum.c7_ex1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@GetMapping("/demo")
	String demo() {
		return "데모.";
	}

}
