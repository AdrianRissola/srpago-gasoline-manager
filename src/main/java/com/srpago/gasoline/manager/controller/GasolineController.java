package com.srpago.gasoline.manager.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srpago.gasoline.manager.dto.InfoRQDto;

@RestController
public class GasolineController {

	private Logger logger = Logger.getLogger(GasolineController.class);

	@PostMapping("/gasoline/purchases")
	public InfoRQDto newPurchase(@Valid @RequestBody InfoRQDto infoRQDto) {
		logger.info(infoRQDto);
		return infoRQDto;
	}
}