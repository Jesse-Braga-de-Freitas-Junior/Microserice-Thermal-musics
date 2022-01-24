package com.tdev.suggestiontrackapp.enums;

public enum GenreEnum 
{

	POP("pop"),
	CLASSICAL("classical"),
	PARTY("party"),
	ROCK("rock");

	private String value;

	GenreEnum(String valor)
	{
		value = valor;
	}
	
	public String getValue()
	{
		return this.value;
	}

}
