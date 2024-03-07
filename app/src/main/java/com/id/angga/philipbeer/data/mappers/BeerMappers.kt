package com.id.angga.philipbeer.data.mappers

import com.id.angga.philipbeer.data.local.BeerEntity
import com.id.angga.philipbeer.data.remote.BeerDto
import com.id.angga.philipbeer.domain.model.Beer

fun BeerDto.toBeerEntity() : BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun BeerEntity.toBeer() : Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}

fun Beer.toBeerEntity() : BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}