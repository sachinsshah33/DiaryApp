package com.appservicesunlimited.anydiary.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.appservicesunlimited.anydiary.data.entry.EntryWithDescription
import com.appservicesunlimited.anydiary.data.entry.EntryWithDescriptionUIModel
import com.appservicesunlimited.anydiary.data.entry.IEntriesRepository
import kotlinx.coroutines.flow.Flow

class FakeEntriesRepository: IEntriesRepository {

    private val items = mutableListOf<Entry>()
    private val observableItems = MutableLiveData<List<Entry>>(items)


    fun refreshLiveData(){
        observableItems.postValue(items)
    }

    override fun insertMultiple(models: List<Entry>) {
        items.addAll(models)
        refreshLiveData()
    }

    override suspend fun insertSingle(model: Entry): Long? {
        items.add(model)
        refreshLiveData()
        return model.entryId
    }

    override suspend fun updateSingle(model: Entry): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSingle(model: Entry) {
        items.remove(model)
        refreshLiveData()
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun getEntryById(entryId: Long): Entry? {
        TODO("Not yet implemented")
    }

    override fun getEntryWithDescriptionPaginated(): Flow<PagingData<EntryWithDescription>> {
        TODO("Not yet implemented")
    }

    override fun getEntryWithDescriptionPaginatedWithDateHeader(): Flow<PagingData<EntryWithDescriptionUIModel>> {
        TODO("Not yet implemented")
    }

}