package com.id.angga.philipbeer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BeerDao {
    @Upsert
    suspend fun upsertAll(beers: List<BeerEntity>)

    @Query("SELECT * FROM beerentity")
    fun pagingSource() : PagingSource<Int, BeerEntity>

    @Delete
    fun deleteItem(beerEntity: BeerEntity)

    @Query("DELETE FROM beerentity")
    suspend fun clearAll()
}