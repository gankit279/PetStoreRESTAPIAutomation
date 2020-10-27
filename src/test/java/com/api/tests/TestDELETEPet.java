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

public class TestDELETEPet extends TestBase{
	
	private PetServiceHelpers petServiceHelper = null;
	private Response response = null;
	private Pet myPet = CommonUtilities.getNewPet();
	
	@BeforeClass
	public void init() {
		logger.info("************* Started Test case TestDELETEPet ******************* ");
		petServiceHelper = new PetServiceHelpers();
		response = petServiceHelper.deletePet(myPet.getId());
	}
	
	@Test(priority = 1)
	public void validateStatusCodeToBE200() {
		logger.info("************** validating status code to be 200 *******************");
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
		logger.info("************** validated status code is 200 *******************");
	}
	
	@Test(priority = 2)
	public void validateMessageInResponse() {
		logger.info("************** validating message in response *******************");
		int responseMessageValue = Integer.parseInt(response.jsonPath().getString("message"));
		int requestPetObjectID = CommonUtilities.getNewPet().getId().intValue();
		assertEquals(responseMessageValue, requestPetObjectID);
		logger.info("************** validated message in response *******************");
	}

}
