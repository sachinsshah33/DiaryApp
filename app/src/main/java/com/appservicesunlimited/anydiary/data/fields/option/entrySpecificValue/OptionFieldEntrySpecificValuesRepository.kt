package com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue


import androidx.paging.ExperimentalPagingApi

@ExperimentalPagingApi
class OptionFieldEntrySpecificValuesRepository(private val dao: OptionFieldEntrySpecificValuesDAO) {

    fun insert(
        model: OptionFieldEntrySpecificValue? = null,
        models: List<OptionFieldEntrySpecificValue>? = null
    ) {
        ArrayList<OptionFieldEntrySpecificValue>().apply {
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


    suspend fun insertMultiple(models: List<OptionFieldEntrySpecificValue>): LongArray? {
        return dao.insert(models)
    }

    suspend fun insertSingle(model: OptionFieldEntrySpecificValue): Long? {
        return dao.insertSingle(model)
    }

    suspend fun updateSingle(model: OptionFieldEntrySpecificValue): Int {
        return dao.updateSingle(model)
    }

    suspend fun update(models: List<OptionFieldEntrySpecificValue>): Int {
        return dao.update(models)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

}