package com.tdev.suggestiontrackapp.model.recommendation;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumDTO 
{
	
	private String name;
	
	@JsonProperty("release_date")
	private String releaseDate;
	
	@JsonProperty("external_urls")
	private ExternalUrlDTO externalUrl;

}
