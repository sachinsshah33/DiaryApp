package com.appservicesunlimited.anydiary.data.fields.option.valueInfo


import androidx.paging.ExperimentalPagingApi

@ExperimentalPagingApi
class OptionFieldValueInfosRepository(private val dao: OptionFieldValueInfosDAO) {

    fun insert(model: OptionFieldValueInfo? = null, models: List<OptionFieldValueInfo>? = null) {
        ArrayList<OptionFieldValueInfo>().apply {
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

    suspend fun insertSingle(model: OptionFieldValueInfo): Long? {
        return dao.insertSingle(model)
    }

    suspend fun updateSingle(model: OptionFieldValueInfo): Int {
        return dao.updateSingle(model)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getOptionFieldValueInfosByOptionFieldId(optionFieldId: Long): List<OptionFieldValueInfo>? {
        return dao.getOptionFieldValueInfosByOptionFieldId(optionFieldId)
    }
}