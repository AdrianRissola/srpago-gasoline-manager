package com.srpago.gasoline.manager.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private Logger logger = Logger.getLogger(HomeController.class);

	@GetMapping("/alive")
	public String greet() {
		logger.info("I'm alive without SpringBoot !!!");
		return "I'm alive without SpringBoot !!!";
	}
}