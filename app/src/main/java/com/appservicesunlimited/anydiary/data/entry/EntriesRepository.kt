package com.appservicesunlimited.anydiary.data.entry


import androidx.paging.*
import com.appservicesunlimited.anydiary.utils.extensions.isToday
import com.appservicesunlimited.anydiary.utils.extensions.toDateStamp
import com.appservicesunlimited.anydiary.utils.helpers.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class EntriesRepository(private val dao: EntriesDAO) {

    fun insert(model: Entry? = null, models: List<Entry>? = null) {
        ArrayList<Entry>().apply {
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

    /*fun insertSingle(student: Entry): Single<Long>? {
    //for RxJava
        return Single.fromCallable { dao.insertSingle(student) }
    }*/

    suspend fun insertSingle(model: Entry): Long? {
        return dao.insertSingle(model)
    }

    suspend fun updateSingle(model: Entry): Int {
        return dao.updateSingle(model)
    }

    fun deleteAll() {
        dao.deleteAll()
    }


    suspend fun getEntryById(entryId: Long): Entry? {
        return dao.getEntryById(entryId)
    }

    fun getEntryWithDescriptionPaginated(): Flow<PagingData<EntryWithDescription>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGINATED_SIZE,
                enablePlaceholders = true
            ), pagingSourceFactory = {
                dao.getEntryWithDescriptionPaginated()
            }).flow
    }


    fun getEntryWithDescriptionPaginatedWithDateHeader(): Flow<PagingData<EntryWithDescriptionUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.DEFAULT_PAGINATED_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dao.getEntryWithDescriptionPaginated() }
        ).flow
            // Mapping Data into local Models
            .map { pagingData ->
                pagingData.map {
                    EntryWithDescriptionUIModel.Item(it)
                }.let {
                    it
                        .insertSeparators { before, after ->
                            when {
                                shouldAddDateHeader(
                                    before,
                                    after
                                ) -> EntryWithDescriptionUIModel.DateHeader(if (after is EntryWithDescriptionUIModel.Item) after.item.entryWithDescriptionEntry?.entryCreatedDate else null)
                                else -> null
                            }
                        }
                }

            }

    }


    fun shouldAddDateHeader(
        before: EntryWithDescriptionUIModel?,
        after: EntryWithDescriptionUIModel?
    ): Boolean {
        if (before == null && (after != null && after is EntryWithDescriptionUIModel.Item)) {
            return !(after.item.entryWithDescriptionEntry?.entryCreatedDate?.isToday() ?: false)
        }
        if (before == null || after == null) {
            return false
        }
        if (before is EntryWithDescriptionUIModel.Item && after is EntryWithDescriptionUIModel.Item) {
            if (before.item.entryWithDescriptionEntry?.entryCreatedDate?.toDateStamp() != after.item.entryWithDescriptionEntry?.entryCreatedDate?.toDateStamp()) {
                return true
            }
        }
        return false
    }


}