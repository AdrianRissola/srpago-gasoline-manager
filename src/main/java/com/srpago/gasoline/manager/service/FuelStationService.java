package com.srpago.gasoline.manager.service;

import org.springframework.http.ResponseEntity;

import com.srpago.gasoline.manager.external.model.FuelStations;

public interface FuelStationService {
	
	ResponseEntity<FuelStations> getById(String id);

}
