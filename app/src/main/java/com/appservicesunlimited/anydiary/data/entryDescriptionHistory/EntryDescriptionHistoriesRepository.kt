package com.appservicesunlimited.anydiary.data.entryDescriptionHistory


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.appservicesunlimited.anydiary.utils.helpers.Constants
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class EntryDescriptionHistoriesRepository(private val dao: EntryDescriptionHistoriesDAO) {

    fun insert(
        model: EntryDescriptionHistory? = null,
        models: List<EntryDescriptionHistory>? = null
    ) {
        ArrayList<EntryDescriptionHistory>().apply {
            model?.let {
                add(it)
            }
            models?.let {
                addAll(it)
            }
            if (!isNullOrEmpty()) {
                dao.insert(this)
            }
        }
    }

    suspend fun insertSingle(model: EntryDescriptionHistory): Long? {
        return dao.insertSingle(model)
    }

    suspend fun getEntryDescriptionHistoryLatestByEntryId(entryId: Long): EntryDescriptionHistory? {
        return dao.getEntryDescriptionHistoryLatestByEntryId(entryId)
    }


    fun getEntryDescriptionHistoriesByEntryIdPaginated(entryId: Long): Flow<PagingData<EntryDescriptionHistory>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGINATED_SIZE,
                enablePlaceholders = true
            ), pagingSourceFactory = {
                dao.getEntryDescriptionHistoriesByEntryIdPaginated(entryId)
            }).flow
    }
}