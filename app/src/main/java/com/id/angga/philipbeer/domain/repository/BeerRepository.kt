package com.id.angga.philipbeer.domain.repository

import com.id.angga.philipbeer.data.local.BeerEntity

interface BeerRepository {
    suspend fun deleteBeer(beerEntity: BeerEntity)
}