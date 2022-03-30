package com.training.pms.model.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.model.Reviews;
import com.training.pms.repository.ReviewsRepository;

@RestController
@RequestMapping("reviews")
public class ReviewsController {

	@Autowired
	ReviewsRepository reviewsRepository;

	@GetMapping
	public ResponseEntity<List<Reviews>> getReviews() {
		List<Reviews> allReviews = (List<Reviews>) reviewsRepository.findAll();
		return new ResponseEntity<List<Reviews>>(allReviews, HttpStatus.OK);
	}
	// getting a single review by id
	@GetMapping("/{reviewId}")
	public ResponseEntity<Reviews> getReview(@PathVariable("reviewId") int reviewId) {
		Optional<Reviews> reviews = reviewsRepository.findById(reviewId);
		return new ResponseEntity<Reviews>(reviews.get(), HttpStatus.OK);
	}
	// saving review
	@PostMapping
	public ResponseEntity<String> saveReview(@RequestBody Reviews reviews) {
		reviewsRepository.save(reviews);
		return new ResponseEntity<String>("Reviews Saved successfully", HttpStatus.CREATED);
	}
	// update review
	@PutMapping
	public ResponseEntity<String> updateReview(@RequestBody Reviews reviews) {
		reviewsRepository.save(reviews);
		return new ResponseEntity<String>("Reviews Updated successfully", HttpStatus.OK);
	}
	// delete a single review by id
	@DeleteMapping("/{reviewId}")
	public ResponseEntity<String> deleteReview(@PathVariable("reviewId") int reviewId) {
		reviewsRepository.deleteById(reviewId);
		return new ResponseEntity<String>("Reviews Deleted successfully", HttpStatus.OK);
	}
}
