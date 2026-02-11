package com.myprojects.CloudGateway.controller;

import java.security.Principal;

import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
	private final ReactiveOAuth2AuthorizedClientService clientService;
	
	
	
	public AuthenticationController(ReactiveOAuth2AuthorizedClientService clientService) {
		this.clientService = clientService;
	}



	@GetMapping("/print-token")
	public Mono<String> printToken(Principal principal){
		return clientService.loadAuthorizedClient("auth0", principal.getName())
				  .map(authorizedClient -> {
	                    OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
	                    System.out.println("Access Token Value: " + accessToken.getTokenValue());
	                    System.out.println("Token Type: " + accessToken.getTokenType().getValue());
	                    System.out.println("Expires At: " + accessToken.getExpiresAt());

	                    return accessToken.getTokenValue();
	                })
	                .defaultIfEmpty("No access token found");
	
	
	
	}
			

}
