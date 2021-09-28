package com.srpago.gasoline.manager.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.srpago.gasoline.manager.dto.InfoRQDto;
import com.srpago.gasoline.manager.dto.PurchaseDto;
import com.srpago.gasoline.manager.dto.ResponseDto;
import com.srpago.gasoline.manager.exception.FuelManagerException;
import com.srpago.gasoline.manager.service.FuelService;
import com.srpago.gasoline.manager.utils.Utils;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FuelManagerController {

	private Logger logger = Logger.getLogger(FuelManagerController.class);
	
	@Autowired
	private FuelService fuelService;

	/**
     * @param infoRQDto: fuel purchase request
     * @return ResponseDto
     */
    @ApiOperation(value = "Handles a purchase request of fuel", notes = "This method handles a purchase request of fuel")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping("/fuel/purchases")
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseDto addNewPurchase(
			@ApiParam(
					name = "infoRQDto",
					type = "InfoRQDto",
    			    value = "purchase request",
    			    required = true)
			@Valid @RequestBody InfoRQDto infoRQDto) {
		validate(infoRQDto);
		this.logger.info("Adding new purchase: " + infoRQDto);
		return this.fuelService.addNewPurchase(infoRQDto);
	}

	/**
     * @return List<PurchaseDto>
     */
    @ApiOperation(value = "Get all purchases", notes = "This method get all purchases")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping("/fuel/purchases")
	@ResponseStatus(HttpStatus.OK)
	public List<PurchaseDto> getAllPurchases() {
		this.logger.info("Retrieveing all purchases...");
		return fuelService.getAllPurchases();
	}
	
	//TODO: replace with @Valid
	private void validate(InfoRQDto infoRQDto) {
		String error = "Bad Request";
		Utils.validateExistsNullOrBlank(new FuelManagerException(error, "Null or empty string field/s", HttpStatus.BAD_REQUEST), 
				infoRQDto.getCardNumber(), infoRQDto.getEmail(), infoRQDto.getGasStation(),
				infoRQDto.getLastName(), infoRQDto.getName(), infoRQDto.getSellerName());
		Utils.validateExistsNull(new FuelManagerException(error, "Null or empty number field/s", HttpStatus.BAD_REQUEST), 
				infoRQDto.getAmount(), infoRQDto.getExpirationDateMonth(), infoRQDto.getExpirationDateYear(), infoRQDto.getGasType());
	}
}