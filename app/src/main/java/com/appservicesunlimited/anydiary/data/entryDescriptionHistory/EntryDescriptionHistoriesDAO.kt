package com.appservicesunlimited.anydiary.data.entryDescriptionHistory

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface EntryDescriptionHistoriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<EntryDescriptionHistory>)/*: LongArray?*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(model: EntryDescriptionHistory): Long?

    @Update
    fun update(models: List<EntryDescriptionHistory>)

    @Delete
    fun delete(models: List<EntryDescriptionHistory>)


    @Query("SELECT * FROM entry_description_histories WHERE entryDescriptionHistoryParentEntryId = :entryId ORDER BY entryDescriptionHistoryAddedDate DESC LIMIT 1")
    suspend fun getEntryDescriptionHistoryLatestByEntryId(entryId: Long): EntryDescriptionHistory?


    @Query("SELECT * FROM entry_description_histories WHERE entryDescriptionHistoryParentEntryId = :entryId ORDER BY entryDescriptionHistoryAddedDate DESC")
    fun getEntryDescriptionHistoriesByEntryIdPaginated(entryId: Long): PagingSource<Int, EntryDescriptionHistory>
}
