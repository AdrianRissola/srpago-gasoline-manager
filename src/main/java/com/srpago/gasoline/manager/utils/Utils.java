package com.srpago.gasoline.manager.utils;

import java.util.List;

import com.srpago.gasoline.manager.exception.FuelManagerException;

public class Utils {
	

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
