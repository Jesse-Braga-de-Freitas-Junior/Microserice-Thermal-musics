package com.tdev.suggestiontrackapp.model.currentWeather;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDTO 
{

	private Integer id;
	private String main;
	private String description;
	private String icon;
}
