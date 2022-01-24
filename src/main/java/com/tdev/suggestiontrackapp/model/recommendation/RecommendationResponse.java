package com.tdev.suggestiontrackapp.model.recommendation;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendationResponse
{

	private List<TracksDTO> tracks;
}
