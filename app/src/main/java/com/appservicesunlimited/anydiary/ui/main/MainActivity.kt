package com.appservicesunlimited.anydiary.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.data.entry.EntriesViewModel
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.appservicesunlimited.anydiary.data.entry.EntryWithDescription
import com.appservicesunlimited.anydiary.databinding.ActivityMainBinding
import com.appservicesunlimited.anydiary.ui.addEntry.AddEntryActivity
import com.appservicesunlimited.anydiary.ui.editEntry.EditEntryActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.jvm.javaField

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val entriesViewModel: EntriesViewModel by viewModels()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val entriesWithDescriptionAndHeaderPaginatedAdapter by lazy {
        EntriesWithDescriptionAndHeaderPaginatedAdapter(onClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addFab.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddEntryActivity::class.java))
        }
        binding.addFab.setOnLongClickListener {
            entriesViewModel.deleteAll()
            true
        }

        setupRecyclerView()
        fetchFromCache()
    }


    private fun setupRecyclerView() {
        binding.entriesRecyclerView.apply {
            adapter = entriesWithDescriptionAndHeaderPaginatedAdapter
        }
    }


    var getEntriesCoroutine: Job? = null

    @SuppressLint("CheckResult")
    private fun fetchFromCache() {
        getEntriesCoroutine?.cancel()
        getEntriesCoroutine = CoroutineScope(Dispatchers.Main).launch {
            entriesViewModel.getEntryWithDescriptionPaginatedWithDateHeader().collectLatest {
                entriesWithDescriptionAndHeaderPaginatedAdapter.submitData(it)
            }
        }
    }


    val onClick: (item: EntryWithDescription?) -> Unit = {
        it?.entryWithDescriptionEntry?.let {
            startActivity(Intent(this@MainActivity, EditEntryActivity::class.java).apply {
                putExtra(Entry::entryId.javaField!!.name, it.entryId)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getEntriesCoroutine?.cancel()
    }
}