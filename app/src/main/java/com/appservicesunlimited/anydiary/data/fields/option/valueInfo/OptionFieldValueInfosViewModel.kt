package com.appservicesunlimited.anydiary.data.fields.option.valueInfo


import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class OptionFieldValueInfosViewModel @Inject constructor(
    private val repository: OptionFieldValueInfosRepository
) : ViewModel() {


    fun insert(model: OptionFieldValueInfo? = null, models: List<OptionFieldValueInfo>? = null) {
        repository.insert(model = model, models = models)
    }

    suspend fun insertSingle(model: OptionFieldValueInfo): Long? {
        return repository.insertSingle(model)
    }

    suspend fun updateSingle(model: OptionFieldValueInfo): Int {
        return repository.updateSingle(model)
    }

    fun deleteAll() {
        repository.deleteAll()
    }


    suspend fun getOptionFieldValueInfosByOptionFieldId(optionFieldId: Long): List<OptionFieldValueInfo>? {
        return repository.getOptionFieldValueInfosByOptionFieldId(optionFieldId)
    }

}