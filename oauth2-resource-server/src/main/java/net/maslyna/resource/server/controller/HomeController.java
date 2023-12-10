package net.maslyna.resource.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String home(Authentication authentication) {
		return "Welcome Home! - %s \nyour Authorities = %s"
				.formatted(Instant.now(), authentication.getAuthorities());
	}

}