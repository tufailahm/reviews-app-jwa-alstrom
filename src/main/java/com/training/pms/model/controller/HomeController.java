package com.training.pms.model.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reviews")
public class HomeController {

	//localhost:7070/reviews/welcome
	@GetMapping("/welcome")
	public ResponseEntity<String> getWelcome() {
		return new ResponseEntity<String>("----- Hello and welcome to Revature REVIEWS App -----",HttpStatus.OK);
	}
	
	@GetMapping("/home")
	public ResponseEntity<String> getHome() {
		return new ResponseEntity<String>("Home Page",HttpStatus.CREATED);
	}
}
