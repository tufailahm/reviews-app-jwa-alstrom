package com.training.pms;

import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReviewsAppJwaAlstromApplicationTests {

	@LocalServerPort
	private String port;
	
	private String baseURL = "http://localhost";
	
	@Autowired
	RestTemplate restTemplate;
	
	URL url;
	@BeforeEach
	public void setUp() throws MalformedURLException {
		url = new URL(baseURL+":"+port+"/reviews/welcome");
	}
	
	@Test
	void testingHome() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
		assertEquals("----- Hello and welcome to Revature REVIEWS App -----",response.getBody());
		assertEquals(200,response.getStatusCodeValue());
	}

}
