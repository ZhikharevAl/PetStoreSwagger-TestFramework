package com.petstore.model

data class Pet(
    val id: Long,
    val category: Category,
    val name: String,
    val photoUrls: List<String>,
    val tags: List<Tag>,
    val status: String,
)
