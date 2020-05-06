package com.CardTracker.SoftwareEng.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CardTracker.SoftwareEng.service.ScrapService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/scrap")
public class ScrapContoller {

	@Autowired
	ScrapService scrapService;

	@GetMapping
	public boolean scrapData() {
		boolean returnValue = false;

		returnValue = scrapService.scrapData();

		return returnValue;
	}
}
