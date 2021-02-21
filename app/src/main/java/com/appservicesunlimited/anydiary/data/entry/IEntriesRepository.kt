package com.appservicesunlimited.anydiary.data.entry

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface IEntriesRepository {
    fun insertMultiple(models: List<Entry>)

    suspend fun insertSingle(model: Entry): Long?

    suspend fun updateSingle(model: Entry): Int

    suspend fun deleteSingle(model: Entry)

    fun deleteAll()

    suspend fun getEntryById(entryId: Long): Entry?

    fun getEntryWithDescriptionPaginated(): Flow<PagingData<EntryWithDescription>>

    fun getEntryWithDescriptionPaginatedWithDateHeader(): Flow<PagingData<EntryWithDescriptionUIModel>>
}