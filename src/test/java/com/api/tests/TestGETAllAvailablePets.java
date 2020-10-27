package com.api.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.helpers.PetServiceHelpers;
import com.api.models.Pet;
import com.fasterxml.jackson.core.type.TypeReference;

import io.restassured.response.Response;

public class TestGETAllAvailablePets extends TestBase{
	
	private PetServiceHelpers petServiceHelper = null;
	private String statusOfPets = "available";
	private Response response = null;
	private Type type = null;
	private List<Pet> petList = null;
	
	@BeforeClass
	public void init() {
		logger.info("************* Started Test case TestGETAllAvailablePet ******************* ");
		petServiceHelper = new PetServiceHelpers();
		response = petServiceHelper.getAllPetsByStatus(statusOfPets);
		type = new TypeReference<List<Pet>>() {}.getType();
		petList = response.as(type);
	}
	
	@Test(priority = 1)
	public void validateStatusCodeToBE200() {
		logger.info("************** validating status code to be 200 *******************");
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		logger.info("************** validated status code is 200 *******************");
	}
	
	@Test(priority = 2)
	public void validateResponseIsNotNull() {
		logger.info("************** validating response to be not null *******************");
		assertNotNull(petList, "Pet list is null");
		logger.info("************** validated response is not null *******************");
	}
	
	@Test(priority = 3)
	public void validateResponseIsNotEmpty() {
		logger.info("************** validating response to be not empty *******************");
		assertFalse(petList.isEmpty(), "Pet list is empty");
		logger.info("************** validated response is not empty *******************");
	}
	
	

}
