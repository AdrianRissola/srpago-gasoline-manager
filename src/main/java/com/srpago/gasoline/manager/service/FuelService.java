package com.srpago.gasoline.manager.service;

import java.util.List;

import com.srpago.gasoline.manager.dto.InfoRQDto;
import com.srpago.gasoline.manager.dto.PurchaseDto;
import com.srpago.gasoline.manager.dto.ResponseDto;

public interface FuelService {

	ResponseDto addNewPurchase(InfoRQDto infoRQDto);

	List<PurchaseDto> getAllPurchases();

}
