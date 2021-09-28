package com.srpago.gasoline.manager.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srpago.gasoline.manager.exception.FuelManagerException;

public class Utils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	

	public static void validateIsNullOrEmpty(List<?> list, FuelManagerException fuelManagerException) {
		validateCondition(!isNullOrEmpty(list), fuelManagerException);
	}
	
	public static void validateIsNullOrBlankOrZero(String string, FuelManagerException fuelManagerException) {
		validateCondition(!isNullOrBlankOrZero(string), fuelManagerException);
	}
	
	public static void validateExistsNullOrBlank(FuelManagerException fuelManagerException, String... strings) {
		for(String string : strings)
			validateCondition(!isNullOrBlank(string), fuelManagerException);
	}
	
	public static void validateExistsNull(FuelManagerException fuelManagerException, Number... numbers) {
		for(Number number : numbers)
			validateCondition(number!=null, fuelManagerException);
	}
	
	public static void validateCondition(boolean condition, FuelManagerException fuelManagerException) {
		if(!condition)
			throw fuelManagerException;
	}
	
	public static String toJsonString(Object object) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new FuelManagerException("Error converting object to json string", 
					"When converting:".concat(String.valueOf(object)), 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return json;
	}
	
	private static boolean isNullOrBlank(String string) {
		return string==null || string.isBlank();
	}
	
	private static boolean isNullOrEmpty(List<?> list) {
		return list==null || list.isEmpty();
	}
	
	public static void validateIsNumeric(String strNum, FuelManagerException fuelManagerException) {
	    if (strNum == null)
	        throw fuelManagerException;
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	    	throw fuelManagerException;
	    }
	}
	
	private static boolean isNullOrBlankOrZero(String string) {
		return isNullOrBlank(string) || string.equals("0");
	}



}
