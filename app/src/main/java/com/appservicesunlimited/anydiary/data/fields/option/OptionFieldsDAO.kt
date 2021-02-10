package com.appservicesunlimited.anydiary.data.fields.option

import androidx.room.*
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.EntryOptionFieldWithValueInfosAndEntrySpecificValue


@Dao
interface OptionFieldsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<OptionField>)/*: LongArray?*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingle(model: OptionField): Long?

    @Update
    fun updateSingle(model: OptionField): Int

    @Update
    fun update(models: List<OptionField>)

    @Delete
    fun delete(models: List<OptionField>)

    @Query("DELETE FROM option_fields")
    fun deleteAll()


    @Query("SELECT * FROM option_fields")
    suspend fun getAllOptionFields(): List<OptionField>?


    @Query("SELECT * FROM option_field_value_infos JOIN option_fields ON option_fields.optionFieldId = option_field_value_infos.optionFieldValueInfoParentOptionFieldId ORDER BY option_fields.optionFieldPosition ASC, option_fields.optionFieldId ASC, option_field_value_infos.optionFieldValueInfoValuePosition ASC")
    suspend fun getAllOptionFieldsWithValueInfos(): List<OptionFieldWithValueInfo>?

    @Query("SELECT * FROM option_field_value_infos JOIN option_fields ON option_fields.optionFieldId = option_field_value_infos.optionFieldValueInfoParentOptionFieldId JOIN option_field_entry_specific_value ON option_fields.optionFieldId = option_field_entry_specific_value.specificValueParentOptionFieldId WHERE option_field_entry_specific_value.specificValueParentEntryId = :entryId ORDER BY option_fields.optionFieldPosition ASC, option_fields.optionFieldId ASC, option_field_value_infos.optionFieldValueInfoValuePosition ASC")
    suspend fun getAllOptionFieldsWithValueInfosAndEntrySpecificValueByEntryId(entryId: Long): List<EntryOptionFieldWithValueInfosAndEntrySpecificValue>?


    //https://stackoverflow.com/questions/26827911/correctly-fetch-nested-list-in-sql


    /*@Query("SELECT * FROM option_field_value_infos LEFT JOIN option_fields ON option_fields.id = option_field_value_infos.optionFieldId")
    //UNION SELECT * FROM option_field_value_infos LEFT OUTER JOIN option_fields ON option_fields.id = option_field_value_infos.optionFieldId
    //@Query("SELECT option_fields.*, option_field_value_infos.* FROM option_fields LEFT JOIN option_field_value_infos ON option_fields.id = option_field_value_infos.optionFieldId UNION ALL SELECT option_fields.*, option_field_value_infos.* FROM   option_field_value_infos LEFT JOIN option_fields ON option_fields.id = option_field_value_infos.optionFieldId WHERE  option_fields.id IS NULL")
    //@Query("SELECT option_field_value_info.*, option_field.* FROM option_field_value_infos AS option_field_value_info LEFT JOIN option_fields AS option_field on option_field_value_info.optionFieldId = option_field.id ORDER BY option_field_value_info.valuePosition ASC, option_field.position ASC")
    suspend fun getAllOptionFieldsWithValueInfos(): List<OptionFieldWithValueInfos>?

    //https://stackoverflow.com/questions/26827911/correctly-fetch-nested-list-in-sql
    @Query("SELECT option_field_value_info.*, option_field.* FROM option_field_value_infos AS option_field_value_info LEFT JOIN option_fields AS option_field on option_field_value_info.optionFieldId = option_field.id ORDER BY option_field_value_info.valuePosition ASC, option_field.position ASC")
    suspend fun getAllOptionFieldsWithValueInfos3(): List<OptionFieldWithValueInfos>?

    //https://stackoverflow.com/questions/26827911/correctly-fetch-nested-list-in-sql
    @Query("SELECT * FROM option_field_value_infos ORDER BY valuePosition ASC")
    suspend fun getAllOptionFieldsWithValueInfos2(): List<OptionFieldValueInfo>?*/
}
