package com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue


import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class OptionFieldEntrySpecificValuesViewModel @Inject constructor(
    private val repository: OptionFieldEntrySpecificValuesRepository
) : ViewModel() {


    fun insert(
        model: OptionFieldEntrySpecificValue? = null,
        models: List<OptionFieldEntrySpecificValue>? = null
    ) {
        repository.insert(model = model, models = models)
    }

    suspend fun insertMultiple(models: List<OptionFieldEntrySpecificValue>): LongArray? {
        return repository.insertMultiple(models)
    }

    suspend fun insertSingle(model: OptionFieldEntrySpecificValue): Long? {
        return repository.insertSingle(model)
    }

    suspend fun updateSingle(model: OptionFieldEntrySpecificValue): Int {
        return repository.updateSingle(model)
    }

    suspend fun update(models: List<OptionFieldEntrySpecificValue>): Int {
        return repository.update(models)
    }

    fun deleteAll() {
        repository.deleteAll()
    }

}