package com.tdev.suggestiontrackapp.model.currentWeather;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainDTO
{

	private Double temp;
	private Integer pressure;
	private Integer humidity;
	
	@JsonProperty("temp_min")
	private Double tempMin;
	
	@JsonProperty("temp_max")
	private Double tempMax;
	
}
