package com.srpago.gasoline.manager.service.impl;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.srpago.gasoline.manager.dto.InfoRQDto;
import com.srpago.gasoline.manager.exception.FuelManagerException;
import com.srpago.gasoline.manager.external.model.FuelStations;
import com.srpago.gasoline.manager.model.FuelStation;
import com.srpago.gasoline.manager.service.BusinessRuleService;
import com.srpago.gasoline.manager.service.FuelStationService;
import com.srpago.gasoline.manager.utils.FuelType;
import com.srpago.gasoline.manager.utils.Utils;

@Component("businessRuleService")
public class BusinessRuleServiceImpl implements BusinessRuleService {
	
	@Autowired
	private FuelStationService fuelStationService;

	@Override
	public void validatePurchaseRequest(InfoRQDto infoRQDto) {
		Utils.validateCondition(
				infoRQDto.getAmount()>1,
				new FuelManagerException("Bad request", "Amount: ".concat(String.valueOf(infoRQDto.getAmount())).concat(" must be greater than 1"), HttpStatus.BAD_REQUEST));
		this.validateEMail(infoRQDto.getEmail());
		this.validateCard(infoRQDto);
	}
	
	@Override
	public void validateRequestedFuelStation(ResponseEntity<FuelStations> response, InfoRQDto infoRQDto) {
		FuelStation fuelStation = this.validateRequestedFuelStation(infoRQDto.getGasStation());
		this.validateRequestedFuelType(infoRQDto, fuelStation);
	}

	private void validateEMail(String email) {
		Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPattern.matcher(email);
		Utils.validateCondition(matcher.find(), 
				new FuelManagerException("Bad request", "Invalid email: ".concat(email).concat(" must be example@domain.com"), HttpStatus.BAD_REQUEST));
	}

	private void validateCard(InfoRQDto infoRQDto) {
		this.validateExpirationDate(infoRQDto);
		this.validateExpiredCard(infoRQDto);
		this.validateCreditCardNumber(infoRQDto.getCardNumber());
	}

	private void validateExpirationDate(InfoRQDto infoRQDto) {
		Utils.validateCondition(infoRQDto.getExpirationDateMonth()>=1 && infoRQDto.getExpirationDateMonth()<=12,
				new FuelManagerException("Invalid Credit Card", "expirationDateMonth must be between 1 and 12", HttpStatus.BAD_REQUEST));
		Utils.validateCondition(String.valueOf(infoRQDto.getExpirationDateYear()).length()==4,
				new FuelManagerException("Invalid Credit Card", "expirationDateYear must be a number of 4 cifras", HttpStatus.BAD_REQUEST));
	}

	private void validateExpiredCard(InfoRQDto infoRQDto) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		boolean condition = infoRQDto.getExpirationDateYear()<currentYear || (infoRQDto.getExpirationDateYear()==currentYear && infoRQDto.getExpirationDateMonth()<=currentMonth);
		Utils.validateCondition(!condition,
				new FuelManagerException(
						"Invalid Credit Card", "Expired Card: ".concat(String.valueOf(infoRQDto.getExpirationDateMonth())).concat("-").concat(String.valueOf(infoRQDto.getExpirationDateYear())),
						HttpStatus.BAD_REQUEST));
	}
	
	private void validateCreditCardNumber(String creditCardNumber) {
		FuelManagerException fme = new FuelManagerException("Invalid Credit Card", "Invalid Credit Card Number: ".concat(creditCardNumber), HttpStatus.BAD_REQUEST);
		Utils.validateIsNumeric(creditCardNumber, fme);
		int[] numbers = new int[creditCardNumber.length()];
		for (int i = 0; i < creditCardNumber.length(); i++) {
			numbers[i] = Integer.parseInt(creditCardNumber.substring(i, i + 1));
		}
		for (int i = numbers.length - 2; i >= 0; i = i - 2) {
			int j = numbers[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			numbers[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		Utils.validateCondition(sum % 10 == 0, fme);
	}
	

	
	private FuelStation validateRequestedFuelStation(String id) {
		String notFoundError = "Not found";
		String notFoundMessage = "Requested fuel station ".concat(id).concat(" not found");
		ResponseEntity<FuelStations> response = fuelStationService.getById(id);
		if(response.getStatusCode().equals(HttpStatus.NOT_FOUND))
				new FuelManagerException(notFoundError, notFoundMessage, HttpStatus.NOT_FOUND);
		if(response.getStatusCode().is4xxClientError())
			new FuelManagerException("Bad request", "Bad request: ".concat(id), HttpStatus.BAD_REQUEST);
		if(response.getStatusCode().is5xxServerError())
			new FuelManagerException("External service error", "External service error for: ".concat(id), HttpStatus.INTERNAL_SERVER_ERROR);
		Utils.validateIsNullOrEmpty(
				response.getBody().getFuelStations(),
				new FuelManagerException(notFoundError, notFoundMessage, HttpStatus.NOT_FOUND));
		return response.getBody().getFuelStations().get(0);
	}
	
	private void validateRequestedFuelType(InfoRQDto infoRQDto, FuelStation fuelStation) {
		FuelType requestedFuelType = FuelType.getById(infoRQDto.getGasType());
		Utils.validateCondition(requestedFuelType!=null,
				new FuelManagerException("Bad request",
						"Invalid fuelType ".concat(String.valueOf(infoRQDto.getGasType())).concat(" must be one of the following: ").concat(String.valueOf(FuelType.getIds())),
						HttpStatus.BAD_REQUEST)
				);
		String requestedFuel = fuelStation.getFuel(requestedFuelType);
		Utils.validateIsNullOrBlankOrZero(requestedFuel, 
				new FuelManagerException("Bad request", "The requested fuel type '".concat(requestedFuelType.getFuelType()).concat("' is not found"), HttpStatus.BAD_REQUEST));		
	}

}
