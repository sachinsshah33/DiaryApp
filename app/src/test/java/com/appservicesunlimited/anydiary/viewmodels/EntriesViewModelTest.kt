package com.appservicesunlimited.anydiary.viewmodels

import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.data.entry.EntriesViewModel
import com.appservicesunlimited.anydiary.repositories.FakeEntriesRepository
import org.junit.Before
import org.junit.Test

@ExperimentalPagingApi
class EntriesViewModelTest {


    private lateinit var viewModel: EntriesViewModel



    @Before
    fun setup() {
        viewModel = EntriesViewModel(FakeEntriesRepository())
    }


    @Test
    fun `insert item with invalid field, returns error`(){
        //viewModel.insertSingle()
    }
}