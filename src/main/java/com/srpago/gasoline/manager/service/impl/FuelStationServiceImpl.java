package com.srpago.gasoline.manager.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.srpago.gasoline.manager.external.model.FuelStations;
import com.srpago.gasoline.manager.service.FuelStationService;
import com.srpago.gasoline.manager.utils.Utils;

@Service("fuelStationService")
public class FuelStationServiceImpl implements FuelStationService {
	
	@Value("${rest.api.fuel.url}")
	private String url;
	
	private Logger logger = Logger.getLogger(FuelStationServiceImpl.class);

	private RestTemplate restTemplate = new RestTemplate();

	
	@Override
	public ResponseEntity<FuelStations> getById(String id) {
		logger.info("Requestig GET ".concat(url).concat("?_id=").concat(id));
		ResponseEntity<FuelStations> response = restTemplate.getForEntity(url.concat("?_id=").concat(id), FuelStations.class);
		logger.info("Response for id:  ".concat(id) + Utils.toJsonString(response));
		return response;
	}



}
