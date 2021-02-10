package com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue

import androidx.room.*


@Dao
interface OptionFieldEntrySpecificValuesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<OptionFieldEntrySpecificValue>): LongArray?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(model: OptionFieldEntrySpecificValue): Long?

    @Update
    fun updateSingle(model: OptionFieldEntrySpecificValue): Int

    @Update
    fun update(models: List<OptionFieldEntrySpecificValue>): Int

    @Delete
    fun delete(models: List<OptionFieldEntrySpecificValue>)

    @Query("DELETE FROM option_field_entry_specific_value")
    fun deleteAll()
}
