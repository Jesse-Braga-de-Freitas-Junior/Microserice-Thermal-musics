package com.tdev.suggestiontrackapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tdev.suggestiontrackapp.config.properties.AppProperties;

@Component
public class ConfigApp
{
	
	@Autowired
	private AppProperties properties;
	
	public String getAppIdOpenWeatherMap() 
	{
		return properties.getPropertyValue("openweathermap-api.appid");
	}
	
	public String getUrlCurrentWeather() 
	{
		return properties.getPropertyValue("openweathermap-api.url-current-weather");
	}

	public String getUrlAccessTokenSpotifyApi() {

		return properties.getPropertyValue("spotify-api.url-access-token");
	}
	
	public String getUrlAvailableGenres() 
	{
		return properties.getPropertyValue("spotify-api.url-genres");
	}
	
	public String getUrlRecommendations() 
	{
		return properties.getPropertyValue("spotify-api.url-recommendations");
	}
	
	public String getClientIdSpotifyApi() 
	{
		return properties.getPropertyValue("spotify-api.client-id");
	}
	
	public String getClientSecretSpotifyApi() 
	{
		return properties.getPropertyValue("spotify-api.client-secret");
	}
}
