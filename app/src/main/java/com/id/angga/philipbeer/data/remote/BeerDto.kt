package com.id.angga.philipbeer.data.remote

data class BeerDto(
    val id: Int,
    val name: String,
    val description : String,
    val tagline: String,
    val first_brewed: String,
    val image_url: String?
)
