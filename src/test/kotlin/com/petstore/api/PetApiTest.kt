package com.petstore.api

import com.petstore.model.Pet
import com.petstore.util.PetApiClient
import com.petstore.util.TestData
import io.qameta.allure.Allure
import io.qameta.allure.Description
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Story
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@Feature("PetApiTest")
class PetApiTest : BaseApiTest() {
    private val petApiClient = PetApiClient()

    @Test
    @Description("This test verifies that a new pet can be created")
    @Story("Create pet")
    @Severity(SeverityLevel.CRITICAL)
    fun testCreatePet() {
        val newPet = TestData.createNewPet()
        Allure.parameter("pet", newPet)

        val response = petApiClient.createPet(newPet)
        val createdPet = petApiClient.deserializePet(response)

        Allure.step("Verify response status code")
        assertEquals(200, response.statusCode)

        Allure.step("Verify created pet matches input")
        assertPetEquals(newPet, createdPet)
    }

    @Test
    @Description("This test verifies that a pet can be retrieved by its ID")
    @Story("Retrieve a pet by ID")
    @Severity(SeverityLevel.CRITICAL)
    fun testGetPetById() {
        val newPet = TestData.createNewPet()
        Allure.parameter("pet", newPet)
        val createdPetResponse = petApiClient.createPet(newPet)
        val createdPet = petApiClient.deserializePet(createdPetResponse)

        Allure.parameter("petId", createdPet.id)

        val response = petApiClient.getPetById(createdPet.id)
        val retrievedPet = petApiClient.deserializePet(response)

        Allure.step("Verify response status code")
        assertEquals(200, response.statusCode)

        Allure.step("Verify retrieved pet matches created pet")
        assertPetEquals(createdPet, retrievedPet)
    }

    private fun assertPetEquals(
        expected: Pet,
        actual: Pet,
    ) {
        assertEquals(expected.id, actual.id, "Pet ID should match")
        assertEquals(expected.name, actual.name, "Pet name should match")
        assertEquals(expected.category.id, actual.category.id, "Category ID should match")
        assertEquals(expected.category.name, actual.category.name, "Category name should match")
        assertEquals(expected.photoUrls, actual.photoUrls, "Photo URLs should match")
        assertEquals(expected.tags.size, actual.tags.size, "Number of tags should match")
        expected.tags.forEachIndexed { index, expectedTag ->
            assertEquals(expectedTag.id, actual.tags[index].id, "Tag ID at index $index should match")
            assertEquals(expectedTag.name, actual.tags[index].name, "Tag name at index $index should match")
        }
        assertEquals(expected.status, actual.status, "Pet status should match")
    }
}
