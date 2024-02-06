package com.id.angga.philipbeer.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.id.angga.philipbeer.data.local.BeerDatabase
import com.id.angga.philipbeer.data.local.BeerEntity
import com.id.angga.philipbeer.data.remote.BeerApi
import com.id.angga.philipbeer.data.remote.BeerRemoteMediator
import com.id.angga.philipbeer.data.repository.BeerRepositoryImpl
import com.id.angga.philipbeer.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext context: Context) : BeerDatabase {
        return Room
            .databaseBuilder(
                context,
                BeerDatabase::class.java,
                "beers.db"
            ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit() : BeerApi {
        return Retrofit.Builder()
            .baseUrl(BeerApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(beerDb : BeerDatabase,  beerApi: BeerApi) : Pager<Int, BeerEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BeerRemoteMediator(
                beerDb = beerDb,
                beerApi = beerApi
            ),
            pagingSourceFactory = {
                beerDb.dao.pagingSource()
            }
        )
    }

    @Provides
    @Singleton
    fun provideBeerRepository(
        beerDatabase: BeerDatabase
    ) : BeerRepository {
        return BeerRepositoryImpl (beerDatabase)
    }
}