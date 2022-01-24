package com.tdev.suggestiontrackapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdev.suggestiontrackapp.enums.GenreEnum;
import com.tdev.suggestiontrackapp.enums.TemperatureUnitEnum;
import com.tdev.suggestiontrackapp.model.currentWeather.CurrentWeatherResponse;
import com.tdev.suggestiontrackapp.model.recommendation.RecommendationResponse;
import com.tdev.suggestiontrackapp.service.integration.openweathermap.CurrentWeatherService;
import com.tdev.suggestiontrackapp.service.integration.spotify.SpotifyApiService;
import com.tdev.suggestiontrackapp.util.TemperatureConverterUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SuggestionTrackService 
{
	
	@Autowired
	private CurrentWeatherService currentWeatherService;
	
	@Autowired
	private SpotifyApiService spotifyApiService;
	
	
	public Optional<RecommendationResponse> searchTrackRecommendationsByLocationWeather(String city) 
	{

		RecommendationResponse recommendationResponse = null;
		
		Optional<CurrentWeatherResponse> currentWeatherCity = currentWeatherService.getCurrentWeatherByCitysName(city);
		
		if (currentWeatherCity.isPresent()) {
			Double temp = currentWeatherCity.get().getMain().getTemp();
			Double convertTemp = TemperatureConverterUtil.convertTemp(TemperatureUnitEnum.KELVIN, 
																	  temp, 
																	  TemperatureUnitEnum.CELSIUS);
			log.info("CURRENT TEMPERATURE OF THE CITY OF {} IS {} DEGREES CELSIUS", city, convertTemp);
			
			if (convertTemp > 30) 
			{
				recommendationResponse = spotifyApiService.getRecommendationsByGenere(GenreEnum.PARTY);
			}
			else if(convertTemp >= 15 && convertTemp <= 30) 
			{
				recommendationResponse = spotifyApiService.getRecommendationsByGenere(GenreEnum.POP);
			}
			else if(convertTemp >= 10 && convertTemp <= 14) 
			{
				recommendationResponse = spotifyApiService.getRecommendationsByGenere(GenreEnum.ROCK);
			}
			else 
			{
				recommendationResponse = spotifyApiService.getRecommendationsByGenere(GenreEnum.CLASSICAL);
			}
		}
		
		return Optional.ofNullable(recommendationResponse);
	}

}
