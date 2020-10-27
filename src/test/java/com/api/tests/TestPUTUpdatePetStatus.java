package com.api.tests;

import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.helpers.PetServiceHelpers;
import com.api.models.Pet;
import com.api.utils.CommonUtilities;

import io.restassured.response.Response;

public class TestPUTUpdatePetStatus extends TestBase{
	
	private PetServiceHelpers petServiceHelper = null;
	private Response response = null;
	private String status = "sold";
	private Pet modifiedPet = CommonUtilities.getNewPet();
	
	@BeforeClass
	public void init() {
		logger.info("************* Started Test case TestPUTUpdatePetStatus ******************* ");
		modifiedPet.setStatus(status);
		petServiceHelper = new PetServiceHelpers();
		response = petServiceHelper.updatePetStatus(modifiedPet);
	}
	
	@Test(priority = 1)
	public void validateStatusCodeToBE200() {
		logger.info("************** validating status code to be 200 *******************");
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		logger.info("************** validated status code is 200 *******************");
	}
	
	@Test(priority = 2)
	public void validateResponseIDToEqualToRequestID() {
		logger.info("************** validating response id to be equal to request id *******************");
		Pet responsePetObject = response.getBody().as(Pet.class);
		Pet requestPetObject = CommonUtilities.getNewPet();
		assertEquals(responsePetObject.getId(), requestPetObject.getId());
		logger.info("************** validated response id is equal to request id *******************");
	}
	
	@Test(priority=3)
	public void validateResponseStatusToBeSold() {
		logger.info("************** validating response status to be equal to sold *******************");
		Pet responsePetObject = response.getBody().as(Pet.class);
		assertEquals(responsePetObject.getStatus(), status);
		logger.info("************** validated response status is equal to sold *******************");
	}

}
