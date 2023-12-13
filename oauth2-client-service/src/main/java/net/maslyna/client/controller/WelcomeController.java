package net.maslyna.client.controller;

import lombok.RequiredArgsConstructor;
import net.maslyna.client.config.WelcomeClient;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcomeController {
	
	private final WelcomeClient welcomeClient;
	
	@GetMapping("/")
	public String welcome(Authentication authentication) {

        return welcomeClient.getWelcome() + ' ' + authentication;
	}
	
}