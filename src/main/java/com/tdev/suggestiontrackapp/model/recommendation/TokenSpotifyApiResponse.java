package com.tdev.suggestiontrackapp.model.recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenSpotifyApiResponse 
{

	@JsonProperty("access_token")
	private String accesToken;

	@JsonProperty("token_type")
	private String tokenType;
	
	@JsonProperty("expires_in")
	private Integer expiresIn;
	
	private String scope;
}
