package com.srpago.gasoline.manager.service;

import org.springframework.http.ResponseEntity;

import com.srpago.gasoline.manager.dto.InfoRQDto;
import com.srpago.gasoline.manager.external.model.FuelStations;

public interface BusinessRuleService {

	void validatePurchaseRequest(InfoRQDto infoRQDto);

	void validateRequestedFuelStation(ResponseEntity<FuelStations> response, InfoRQDto infoRQDto);

}
