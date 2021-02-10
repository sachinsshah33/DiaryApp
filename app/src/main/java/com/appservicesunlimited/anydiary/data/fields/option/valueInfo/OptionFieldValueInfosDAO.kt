package com.appservicesunlimited.anydiary.data.fields.option.valueInfo

import androidx.room.*


@Dao
interface OptionFieldValueInfosDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<OptionFieldValueInfo>)/*: LongArray?*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(model: OptionFieldValueInfo): Long?

    @Update
    fun updateSingle(model: OptionFieldValueInfo): Int

    @Update
    fun update(models: List<OptionFieldValueInfo>)

    @Delete
    fun delete(models: List<OptionFieldValueInfo>)

    @Query("DELETE FROM option_field_value_infos")
    fun deleteAll()


    @Query("SELECT * FROM option_field_value_infos WHERE optionFieldValueInfoParentOptionFieldId = :optionFieldId ORDER BY optionFieldValueInfoValuePosition ASC")
    suspend fun getOptionFieldValueInfosByOptionFieldId(optionFieldId: Long): List<OptionFieldValueInfo>?
}
