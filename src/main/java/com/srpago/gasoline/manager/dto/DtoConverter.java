package com.srpago.gasoline.manager.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.srpago.gasoline.manager.model.Purchase;

public class DtoConverter {
	

	public static Purchase fromDto(InfoRQDto infoRQDto) {
		Purchase purchase = new Purchase();
		BeanUtils.copyProperties(infoRQDto, purchase);
		return purchase;
	}

	public static InfoRQDto toInfoRQDto(Purchase purchase) {
		InfoRQDto InfoRQDto = new InfoRQDto();
		BeanUtils.copyProperties(purchase, InfoRQDto);
		return InfoRQDto;
	}

	public static List<PurchaseDto> toDto(List<Purchase> purchases) {
		List<PurchaseDto> purchasesDto =  new ArrayList<PurchaseDto>();
		purchases.forEach(purchase -> purchasesDto.add(toDto(purchase)));
		return purchasesDto;
	}

	public static PurchaseDto toDto(Purchase purchase) {
		PurchaseDto purchaseDto = new PurchaseDto();
		BeanUtils.copyProperties(purchase, purchaseDto);
		//FuelStationDto fuelStationDto = modelMapper.map(purchase.getFuelStation(), FuelStationDto.class);
		FuelStationDto fuelStationDto = new FuelStationDto();
		BeanUtils.copyProperties(purchase.getFuelStation(), fuelStationDto);
		purchaseDto.setFuelStationDto(fuelStationDto);
		return purchaseDto;
	}

	
	

}
