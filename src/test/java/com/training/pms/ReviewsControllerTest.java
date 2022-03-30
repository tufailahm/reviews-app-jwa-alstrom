package com.training.pms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.training.pms.model.Reviews;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ReviewsControllerTest extends AbstractTest {

	private String uri = "/reviews";
	private int reviewId = 7816215;

	@BeforeEach
	protected void setUp() {
		super.setUp();
	}

	// test save functionality
	@Test
	@Order(1)
	@DisplayName(value = "Testing review save functionality")
	void testUpdate() throws Exception {
		// creating an object for saving
		Reviews reviews = new Reviews(reviewId, "You are selling awesome product", 5);
		// transforming java object into json
		String reviewsJSON = super.mapToJson(reviews);

		// calling the rest API
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(reviewsJSON))
				.andReturn();

		int statusCode = mvcResult.getResponse().getStatus();
		String message = mvcResult.getResponse().getContentAsString();

		assertEquals(201, statusCode);
		assertEquals("Reviews Saved successfully", message);
	}

	// test get a single review functionality
	@Test
	@Order(2)
	@DisplayName(value = "Testing review get functionality")
	void testGet() throws Exception {

		Reviews expectedReviews = new Reviews(reviewId, "You are selling awesome product", 5);
		// calling the rest API
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get(uri + "/" + reviewId).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int statusCode = mvcResult.getResponse().getStatus();
		String message = mvcResult.getResponse().getContentAsString();
		Reviews actualReviews = super.mapFromJson(message, Reviews.class);
		assertEquals(200, statusCode);
		assertEquals(actualReviews, expectedReviews);
	}

	@Test
	@Order(3)
	@DisplayName(value = "Testing review update functionality")
	void testSave() throws Exception {
		// transforming java object into json
		Reviews updatedReviews = new Reviews(reviewId, "You are selling worst product of the decade", 0);

		String reviewsJSON = super.mapToJson(updatedReviews);

		// calling the rest API
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(reviewsJSON))
				.andReturn();

		int statusCode = mvcResult.getResponse().getStatus();
		String message = mvcResult.getResponse().getContentAsString();

		assertEquals(200, statusCode);
		assertEquals("Reviews Updated successfully", message);
	}

	// get all
	// hands on
	// test all reviews

	// test get all functionality
	@Test
	@Order(value = 4)
	@DisplayName("4. Testing get all functionality")
	void testGetAll() throws Exception {

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int statusCode = mvcResult.getResponse().getStatus();
		String message = mvcResult.getResponse().getContentAsString();
		Reviews[] actualreviews = super.mapFromJson(message, Reviews[].class);

		assertEquals(200, statusCode);
		assertTrue(actualreviews.length > 0);

	}

	// test delete functionality
	@Test
	@Order(5)
	@DisplayName(value = "Testing review delete functionality")
	void testDelete() throws Exception {
		// calling the rest API
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.delete(uri + "/" + reviewId).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int statusCode = mvcResult.getResponse().getStatus();
		String message = mvcResult.getResponse().getContentAsString();

		assertEquals(200, statusCode);
		assertEquals("Reviews Deleted successfully", message);
	}

}
