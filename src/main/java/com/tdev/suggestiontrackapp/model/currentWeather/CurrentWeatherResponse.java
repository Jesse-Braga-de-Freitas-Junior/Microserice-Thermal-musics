package com.tdev.suggestiontrackapp.model.currentWeather;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeatherResponse 
{

	private MainDTO main;
	private List<WeatherDTO> weather;
}
