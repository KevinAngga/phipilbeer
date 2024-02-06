package com.id.angga.philipbeer.data.repository

import com.id.angga.philipbeer.data.local.BeerDatabase
import com.id.angga.philipbeer.data.local.BeerEntity
import com.id.angga.philipbeer.domain.repository.BeerRepository
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(private val database: BeerDatabase)
    : BeerRepository {
    override suspend fun deleteBeer(beerEntity: BeerEntity) {
        database.dao.deleteItem(beerEntity)
    }
}