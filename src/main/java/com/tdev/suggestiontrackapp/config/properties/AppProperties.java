package com.tdev.suggestiontrackapp.config.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component	
public class AppProperties
{

	@Autowired
	private Environment env;

	public String getPropertyValue(String key) 
	{
		String returnValue = null;

		String keyValue = env.getProperty(key);

		if (keyValue != null && !keyValue.isEmpty()) 
		{
			returnValue = keyValue;
		}
		return returnValue;
	}
}
