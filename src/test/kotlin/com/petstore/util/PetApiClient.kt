package com.petstore.util

import com.google.gson.Gson
import com.petstore.model.Pet
import io.qameta.allure.Step
import io.restassured.RestAssured
import io.restassured.response.Response

class PetApiClient {
    private val gson = Gson()

    @Step("Get pet by ID: {petId}")
    fun getPetById(petId: Long): Response {
        return RestAssured.given()
            .pathParam("petId", petId)
            .get("/pet/{petId}")
    }

    @Step("Deserialize pet response")
    fun deserializePet(response: Response): Pet {
        return gson.fromJson(response.asString(), Pet::class.java)
    }

    @Step("Create new pet")
    fun createPet(pet: Pet): Response {
        return RestAssured.given()
            .contentType("application/json")
            .body(pet)
            .post("/pet")
    }
}
