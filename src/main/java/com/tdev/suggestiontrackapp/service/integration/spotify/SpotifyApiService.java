package com.tdev.suggestiontrackapp.service.integration.spotify;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tdev.suggestiontrackapp.config.ConfigApp;
import com.tdev.suggestiontrackapp.enums.GenreEnum;
import com.tdev.suggestiontrackapp.config.AppConstants;
import com.tdev.suggestiontrackapp.model.recommendation.Generes;
import com.tdev.suggestiontrackapp.model.recommendation.RecommendationResponse;
import com.tdev.suggestiontrackapp.model.recommendation.TokenSpotifyApiResponse;
import com.tdev.suggestiontrackapp.util.AppUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpotifyApiService
{
	
	@Autowired
	private ConfigApp configApp;
	
	public TokenSpotifyApiResponse getAccessToken() 
	{
		log.info("GETTING THE ACCES TOKEN");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(configApp.getClientIdSpotifyApi(), configApp.getClientSecretSpotifyApi());
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add(AppConstants.FORM_PARAM_GRANT_TYPE, AppConstants.FORM_PARAM_GRANT_TYPE_CLIENT_CREDENTIALS_VALUE);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		TokenSpotifyApiResponse response = restTemplate.postForObject(configApp.getUrlAccessTokenSpotifyApi(), request, TokenSpotifyApiResponse.class);
		
		log.info("ACCES TOKEN SUCCESSFULLY OBTAINED");
		return response;
	}
	
	public Generes getAvailableGenreSeeds() 
	{
		log.info("GETTING THE GENDER AVAILABLE");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(getAccessToken().getAccesToken());
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
		
		ResponseEntity<Generes> result = restTemplate.exchange(configApp.getUrlAvailableGenres(), HttpMethod.GET, request, Generes.class);
		
		log.info("GENDERS GONE WITH SUCCESS");
		return result.getBody();
	}
	
	public RecommendationResponse getRecommendationsByGenere(GenreEnum genre) 
	{
		log.info("LOOKING FOR RECOMMENDATIONS OF TRACKS BY MUSICAL GENRE: {}", genre);
		RestTemplate restTemplate = new RestTemplate();
		
		UriComponentsBuilder builder = UriComponentsBuilder
			    .fromUriString(configApp.getUrlRecommendations())
			    .queryParam(AppConstants.QUERY_PARAM_MARKET, AppConstants.QUERY_PARAM_MARKET_VALUE)
			    .queryParam(AppConstants.QUERY_PARAM_SEED_GENRES, genre.getValue());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(getAccessToken().getAccesToken());
		
		HttpEntity<String> request = new HttpEntity<>(headers);
		
		ResponseEntity<RecommendationResponse> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, RecommendationResponse.class);
		
		RecommendationResponse responseBody = null;
		if (response.getStatusCode().equals(HttpStatus.OK)) 
		{
			responseBody = response.getBody();
		}
		else if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) 
		{
			throw new RuntimeException("FALED");
			// TODO GET REFRESH TOKEN
		}
		
		log.info("TRACKS RECOMMENDATIONS GETTING SUCCESSFULLY - [responseBody]: {}", AppUtil.convertObjectToStringJson(responseBody));
		return responseBody;
	}
}
