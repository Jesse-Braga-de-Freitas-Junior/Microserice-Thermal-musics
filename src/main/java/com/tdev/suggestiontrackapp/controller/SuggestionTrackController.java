package com.tdev.suggestiontrackapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdev.suggestiontrackapp.model.recommendation.RecommendationResponse;
import com.tdev.suggestiontrackapp.service.SuggestionTrackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("api/suggestion")
@RestController
public class SuggestionTrackController 
{
	
	
	@Autowired
	private SuggestionTrackService suggestionTrackService;
	
	@GetMapping("/location/{city}")
	public ResponseEntity<RecommendationResponse> getCurrentWeatherTest(@PathVariable String city) 
	{
		log.info("GETTING RECOMMENDATIONS ACCORDING TO THE CLIMATE OF THE CITY OF: {}", city);
		
		Optional<RecommendationResponse> response = suggestionTrackService.searchTrackRecommendationsByLocationWeather(city);
		
		return response.isPresent() ? ResponseEntity.ok(response.get()) : ResponseEntity.notFound().build();
	}
}
