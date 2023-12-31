package space.bum.c7_ex1.controllers;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(produces = "application/hal+json;charset=UTF-8")
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
	
	@GetMapping("/demo4/{somebody}")
	@PreAuthorize("@demo4CondEvaler.condition(#somebody)")
	String demo4(@PathVariable("somebody") String somebody) {
		return "데모 4 : " + somebody;
	}
	
	@GetMapping("/demo5")
	@PostAuthorize("returnObject == '데모 5'")
	String demo5(HttpServletResponse resp) {
		System.out.println(":-)");
		return "데모 5";
	}
	
	@GetMapping("/demo6")
	@PreFilter("filterObject.contains('a')")
	String demo6(@RequestBody List<String> values) {
    System.setOut(new PrintStream(
    		new FileOutputStream(FileDescriptor.out), 
    		true, StandardCharsets.UTF_8));
		System.out.println("값:" + values);
		return "데모 6";
	}
	
	@GetMapping("/demo7")
	@PostFilter("filterObject.contains('a')")
	List<String> demo7() {
		var fruits = new ArrayList<String>();
		fruits.addAll(List.of("strawberry", "melon", "mango", "kiwi"));
		
		return fruits;
	}

}
