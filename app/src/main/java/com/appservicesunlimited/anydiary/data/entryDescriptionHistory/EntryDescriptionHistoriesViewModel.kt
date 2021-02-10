package com.appservicesunlimited.anydiary.data.entryDescriptionHistory


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
class EntryDescriptionHistoriesViewModel @Inject constructor(
    private val repository: EntryDescriptionHistoriesRepository
) : ViewModel() {
    fun insert(
        model: EntryDescriptionHistory? = null,
        models: List<EntryDescriptionHistory>? = null
    ) {
        repository.insert(model = model, models = models)
    }

    suspend fun insertSingle(model: EntryDescriptionHistory): Long? {
        return repository.insertSingle(model)
    }


    suspend fun getEntryDescriptionHistoryLatestByEntryId(entryId: Long): EntryDescriptionHistory? {
        return repository.getEntryDescriptionHistoryLatestByEntryId(entryId)
    }


    fun getEntryDescriptionHistoriesByEntryIdPaginated(entryId: Long): Flow<PagingData<EntryDescriptionHistory>> {
        return repository.getEntryDescriptionHistoriesByEntryIdPaginated(entryId)
            .cachedIn(viewModelScope).distinctUntilChanged()
    }
}