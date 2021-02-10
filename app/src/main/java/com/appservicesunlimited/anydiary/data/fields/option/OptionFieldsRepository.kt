package com.appservicesunlimited.anydiary.data.fields.option


import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.EntryOptionFieldWithValueInfosAndEntrySpecificValue

@ExperimentalPagingApi
class OptionFieldsRepository(private val dao: OptionFieldsDAO) {

    fun insert(model: OptionField? = null, models: List<OptionField>? = null) {
        ArrayList<OptionField>().apply {
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

    suspend fun insertSingle(model: OptionField): Long? {
        return dao.insertSingle(model)
    }

    suspend fun updateSingle(model: OptionField): Int {
        return dao.updateSingle(model)
    }

    fun deleteAll() {
        dao.deleteAll()
    }


    suspend fun getAlOptionFields(): List<OptionField>? {
        return dao.getAllOptionFields()
    }

    suspend fun getAllOptionFieldsWithValueInfos(): List<OptionFieldWithValueInfo>? {
        return dao.getAllOptionFieldsWithValueInfos()
    }

    suspend fun getAllOptionFieldsWithValueInfosAndEntrySpecificValueByEntryId(entryId: Long): List<EntryOptionFieldWithValueInfosAndEntrySpecificValue>? {
        return dao.getAllOptionFieldsWithValueInfosAndEntrySpecificValueByEntryId(entryId)
    }
}