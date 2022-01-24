package com.tdev.suggestiontrackapp.model.recommendation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TracksDTO 
{

	private String name;
	
	private AlbumDTO album;
	
	private List<ArtistDTO> artists;
	
	@JsonProperty("external_urls")
	private ExternalUrlDTO externalUrl;
}
