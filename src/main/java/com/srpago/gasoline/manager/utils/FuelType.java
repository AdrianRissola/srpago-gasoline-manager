package com.srpago.gasoline.manager.utils;

import java.util.ArrayList;
import java.util.List;

public enum FuelType {
	
	MAGNA("Magna", 1),
	PREMIUM("Premium", 2);
	
	private String fuelType;
	private int id;
	
	private FuelType(String fuelType, int id){
		this.fuelType = fuelType;
		this.id = id;
	}

	public String getFuelType() {
		return fuelType;
	}

	public int getId() {
		return id;
	}

	public static FuelType getById(int id) {
		for(FuelType fuelType : FuelType.values()) {
			if(fuelType.getId()==id)
				return fuelType;
		}
		return null;
	}
	
	public static List<Integer> getIds() {
		List<Integer> ids = new ArrayList<Integer>();
		for(FuelType fuelType : FuelType.values()) {
			ids.add(fuelType.getId());
		}
		return ids;
	}

}
