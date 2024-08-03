package com.petstore.api

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import java.io.FileInputStream
import java.util.Properties

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseApiTest {
    @BeforeAll
    open fun setup() {
        val properties = Properties()
        properties.load(FileInputStream("src/test/resources/test.properties"))

        val baseUrl = properties.getProperty("base.url")

        RestAssured.baseURI = baseUrl
    }
}
