package com.appservicesunlimited.anydiary.data.entry

import androidx.paging.PagingSource
import androidx.room.*


@Dao
interface EntriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<Entry>)/*: LongArray?*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(model: Entry): Long?

    @Update
    fun updateSingle(model: Entry): Int

    @Update
    fun update(models: List<Entry>)

    @Delete
    fun delete(models: List<Entry>)

    @Query("DELETE FROM entries")
    fun deleteAll()


    @Query("SELECT * FROM entries WHERE entryId = :entryId LIMIT 1")
    suspend fun getEntryById(entryId: Long): Entry?

    //https://stackoverflow.com/questions/2111384/sql-join-selecting-the-last-records-in-a-one-to-many-relationship
    @Query("SELECT entry.*, entry_description_history.*, max(entry_description_history.entryDescriptionHistoryAddedDate) FROM entries AS entry LEFT OUTER JOIN entry_description_histories AS entry_description_history ON entry.entryId = entry_description_history.entryDescriptionHistoryParentEntryId GROUP BY entry.entryId ORDER BY entry.entryShowAsDate DESC")
    fun getEntryWithDescriptionPaginated(): PagingSource<Int, EntryWithDescription>
}
