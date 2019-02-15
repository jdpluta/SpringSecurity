package com.jdp.security.resource;

import java.util.Collection;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {
	@GetMapping
	public String hello() {
		return "Hello World!";
	}
/*	
	public String hello2(@AuthenticationPrincipal final UserDetails userDetails) { //inject UserDetails from Spring security core
		System.out.println("HelloResource. hello. userDetails: " + userDetails);
		System.out.println("HelloResource. hello. user name: " + userDetails.getUsername());
		System.out.println("HelloResource. hello. password: " + userDetails.getPassword());
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		authorities.forEach(auth -> System.out.println(auth));
		return "Hello World!";
	}
*/	
	@GetMapping("/all")
	public String helloUT() {
		return "Hello YouTube";
	}
	@PreAuthorize("hasAnyRole('ADMIN')") //Authorization where only user with ADMIN role can use this method
	@GetMapping("/secured/all")
	public String helloSecured() {
		return "Secured Hello";
	}
}
