package com.tdev.suggestiontrackapp.model.recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDTO 
{

	private String name;
	
	@JsonProperty("external_urls")
	private ExternalUrlDTO externalUrl;
}
