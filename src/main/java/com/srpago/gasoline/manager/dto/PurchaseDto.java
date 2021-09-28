package com.srpago.gasoline.manager.dto;

import java.util.Date;

public class PurchaseDto {
	
	private Long id;
	private String email;
    private String name;
    private String lastName;
    private String cardNumber;
    private Integer expirationDateYear;
    private Integer expirationDateMonth;
    private Integer gasType;
    private Double amount;
    private String sellerName;
    private Date date;
    private FuelStationDto fuelStationDto;
    
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public Integer getExpirationDateYear() {
		return expirationDateYear;
	}
	
	public void setExpirationDateYear(Integer expirationDateYear) {
		this.expirationDateYear = expirationDateYear;
	}
	
	public Integer getExpirationDateMonth() {
		return expirationDateMonth;
	}
	
	public void setExpirationDateMonth(Integer expirationDateMonth) {
		this.expirationDateMonth = expirationDateMonth;
	}
	
	public Integer getGasType() {
		return gasType;
	}
	
	public void setGasType(Integer gasType) {
		this.gasType = gasType;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getSellerName() {
		return sellerName;
	}
	
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public FuelStationDto getFuelStationDto() {
		return fuelStationDto;
	}

	public void setFuelStationDto(FuelStationDto fuelStationDto) {
		this.fuelStationDto = fuelStationDto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
