package com.appservicesunlimited.anydiary.data.fields.option


import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.EntryOptionFieldWithValueInfosAndEntrySpecificValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class OptionFieldsViewModel @Inject constructor(
    private val repository: OptionFieldsRepository
) : ViewModel() {


    fun insert(model: OptionField? = null, models: List<OptionField>? = null) {
        repository.insert(model = model, models = models)
    }

    suspend fun insertSingle(model: OptionField): Long? {
        return repository.insertSingle(model)
    }

    suspend fun updateSingle(model: OptionField): Int {
        return repository.updateSingle(model)
    }

    fun deleteAll() {
        repository.deleteAll()
    }


    suspend fun getAlOptionFields(): List<OptionField>? {
        return repository.getAlOptionFields()
    }

    suspend fun getAllOptionFieldsWithValueInfos(): List<OptionFieldWithValueInfo>? {
        return repository.getAllOptionFieldsWithValueInfos()
    }

    suspend fun getAllOptionFieldsWithValueInfosAndEntrySpecificValueByEntryId(entryId: Long): List<EntryOptionFieldWithValueInfosAndEntrySpecificValue>? {
        return repository.getAllOptionFieldsWithValueInfosAndEntrySpecificValueByEntryId(entryId)
    }
}