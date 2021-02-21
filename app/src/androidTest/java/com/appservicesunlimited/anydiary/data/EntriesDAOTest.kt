package com.appservicesunlimited.anydiary.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.appservicesunlimited.anydiary.data.entry.EntriesDAO
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.appservicesunlimited.anydiary.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class EntriesDAOTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: EntriesDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.entriesDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertItem() = runBlockingTest {
        val item = Entry(entryId = 1L, entryTitle = "entryTitle1")
        dao.insertSingle(item)
        val allItems = dao.observeAllItems().getOrAwaitValue()
        assertThat(allItems).contains(item)
    }

    @Test
    fun deleteItem() = runBlockingTest {
        val item = Entry(entryId = 1L, entryTitle = "entryTitle1")
        dao.insertSingle(item)
        dao.deleteSingle(item)
        val allItems = dao.observeAllItems().getOrAwaitValue()
        assertThat(allItems).doesNotContain(item)
    }
}













