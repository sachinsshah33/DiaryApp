package com.appservicesunlimited.anydiary.data.entry


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class EntriesViewModel @Inject constructor(
    private val repository: EntriesRepository
) : ViewModel() {


    fun insert(model: Entry? = null, models: List<Entry>? = null) {
        repository.insert(model = model, models = models)
    }

    suspend fun insertSingle(model: Entry): Long? {
        return repository.insertSingle(model)
    }

    suspend fun updateSingle(model: Entry): Int {
        return repository.updateSingle(model)
    }

    fun deleteAll() {
        repository.deleteAll()
    }


    suspend fun getEntryById(entryId: Long): Entry? {
        return repository.getEntryById(entryId)
    }

    fun getEntryWithDescriptionPaginated(): Flow<PagingData<EntryWithDescription>> {
        return repository.getEntryWithDescriptionPaginated().cachedIn(viewModelScope)
            .distinctUntilChanged()
    }

    fun getEntryWithDescriptionPaginatedWithDateHeader(): Flow<PagingData<EntryWithDescriptionUIModel>> {
        return repository.getEntryWithDescriptionPaginatedWithDateHeader().cachedIn(viewModelScope)
            .distinctUntilChanged()
    }
}