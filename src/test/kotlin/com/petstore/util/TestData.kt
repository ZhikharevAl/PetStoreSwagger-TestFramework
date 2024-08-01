package com.petstore.util

import com.petstore.model.Category
import com.petstore.model.Pet
import com.petstore.model.Tag
import java.util.UUID
import kotlin.random.Random

object TestData {
    private val petNames = listOf("Fluffy", "Buddy", "Max", "Luna", "Charlie")
    private val categories = listOf("Cats", "Dogs", "Birds", "Fish", "Reptiles")
    private val statuses = listOf("available", "pending", "sold")
    private val tagNames = listOf("cute", "friendly", "playful", "smart", "active")

    fun createNewPet(): Pet {
        return Pet(
            id = Random.nextLong(1, 1000000),
            category =
                Category(
                    id = Random.nextLong(1, 100),
                    name = categories.random(),
                ),
            name = petNames.random(),
            photoUrls = listOf("resources/${UUID.randomUUID()}.png"),
            tags =
                listOf(
                    Tag(
                        id = Random.nextLong(1, 100),
                        name = tagNames.random(),
                    ),
                ),
            status = statuses.random(),
        )
    }
}
