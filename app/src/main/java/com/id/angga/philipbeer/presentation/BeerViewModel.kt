package com.id.angga.philipbeer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.id.angga.philipbeer.data.local.BeerEntity
import com.id.angga.philipbeer.data.mappers.toBeer
import com.id.angga.philipbeer.data.mappers.toBeerEntity
import com.id.angga.philipbeer.domain.model.Beer
import com.id.angga.philipbeer.domain.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val repository: BeerRepository,
    pager: Pager<Int, BeerEntity>
) : ViewModel() {

    // Triggering flow for refresh
    private val refreshTrigger = MutableStateFlow(Unit)

    private val pagerFlow = pager.flow

    val beerPagingFlow = combine(
        pagerFlow,
        refreshTrigger
    ) { pager, refresh ->
        pager.map { it.toBeer() }
    }.cachedIn(viewModelScope)

    fun deleteItem(beer: Beer) {
       viewModelScope.launch(Dispatchers.IO) {
           repository.deleteBeer(
               beer.toBeerEntity()
           )
           refreshTrigger.value = Unit
       }
    }
}