package com.tdev.suggestiontrackapp.util;

import com.tdev.suggestiontrackapp.enums.TemperatureUnitEnum;

public class TemperatureConverterUtil {

	public static Double convertTemp(TemperatureUnitEnum unitFrom, Double temp, TemperatureUnitEnum unitTo) 
	{
		if (unitFrom == TemperatureUnitEnum.CELSIUS) 
		{
			return convFromCelsius(temp, unitTo);
		}
		else if (unitFrom == TemperatureUnitEnum.FAHRENHEIT) 
		{
			return convFromFahrenheit(temp, unitTo);
		}
		else if (unitFrom == TemperatureUnitEnum.KELVIN) 
		{
			return convFromKelvin(temp, unitTo);
		}
		return 0D;
	}

	public static Double convFromCelsius(double value, TemperatureUnitEnum unitTo)
	{
		if (unitTo == TemperatureUnitEnum.FAHRENHEIT) 
		{
			double newvalue = (value * 9 / 5) + 32;
			return newvalue;
		} 
		else if (unitTo == TemperatureUnitEnum.KELVIN) 
		{
			double newvalue = (5 / 9 * (value - 32) + 273.15);
			return newvalue;
		}
		return value;
	}

	public static Double convFromFahrenheit(double value, TemperatureUnitEnum unitTo) 
	{
		if (unitTo == TemperatureUnitEnum.CELSIUS) 
		{
			double newvalue = ((value - 32) * 5 / 9);
			return newvalue;
		}
		else if (unitTo == TemperatureUnitEnum.KELVIN) 
		{
			double newvalue = (value + 459.67) * 5 / 9;
			return newvalue;
		}
		return value;
	}

	public static Double convFromKelvin(double value, TemperatureUnitEnum unitTo) 
	{
		if (unitTo == TemperatureUnitEnum.CELSIUS) 
		{
			value = value - 273.15;
			return value;
		}
		else if (unitTo == TemperatureUnitEnum.FAHRENHEIT) 
		{
			value = value * 9 / 5 - 459.67;
			return value;
		}
		return value;
	}
}
