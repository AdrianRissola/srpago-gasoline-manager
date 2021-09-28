package com.srpago.gasoline.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.srpago.gasoline.manager.dto.DtoConverter;
import com.srpago.gasoline.manager.dto.InfoRQDto;
import com.srpago.gasoline.manager.dto.PurchaseDto;
import com.srpago.gasoline.manager.dto.ResponseDto;
import com.srpago.gasoline.manager.external.model.FuelStations;
import com.srpago.gasoline.manager.model.Purchase;
import com.srpago.gasoline.manager.repository.PurchaseRepository;
import com.srpago.gasoline.manager.service.BusinessRuleService;
import com.srpago.gasoline.manager.service.FuelService;
import com.srpago.gasoline.manager.service.FuelStationService;
import com.srpago.gasoline.manager.utils.Utils;

@Service("gasolineService")
public class FuelServiceImpl implements FuelService {
	
	private Logger logger = Logger.getLogger(FuelServiceImpl.class);
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private BusinessRuleService businessRuleService;
	
	@Autowired
	private FuelStationService fuelStationService;

	@Override
	public ResponseDto addNewPurchase(InfoRQDto infoRQDto) {
		businessRuleService.validatePurchaseRequest(infoRQDto);
		ResponseEntity<FuelStations> response = fuelStationService.getById(infoRQDto.getGasStation());
		businessRuleService.validateRequestedFuelStation(response, infoRQDto);
		Purchase newPurchase = DtoConverter.fromDto(infoRQDto);
		newPurchase.setFuelStation(response.getBody().getFuelStations().get(0));
		newPurchase.setDate(new Date());
		logger.info("Saving new purchase..." + newPurchase.toString());
		Purchase newPurchaseSaved = purchaseRepository.save(newPurchase);
		logger.info("New purchase saved " + newPurchase.toString() + ":" + Utils.toJsonString(newPurchase));
		return new ResponseDto(true, "Your purchase id: ".concat(String.valueOf(newPurchaseSaved.getId())));
	}

	@Override
	public List<PurchaseDto> getAllPurchases() {
		List<Purchase> purchases = purchaseRepository.findAll();
		logger.info("Retrieved purchases: " + Utils.toJsonString(purchases));
		return DtoConverter.toDto(purchases);
	}

}
