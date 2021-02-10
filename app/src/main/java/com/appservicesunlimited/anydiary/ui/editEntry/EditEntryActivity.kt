package com.appservicesunlimited.anydiary.ui.editEntry

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.R
import com.appservicesunlimited.anydiary.data.entry.EntriesViewModel
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistoriesViewModel
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistory
import com.appservicesunlimited.anydiary.data.fields.option.OptionFieldsViewModel
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValuesViewModel
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.toDatabaseList
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.toUIList
import com.appservicesunlimited.anydiary.databinding.ActivityEditEntryBinding
import com.appservicesunlimited.anydiary.utils.extensions.toast
import com.appservicesunlimited.anydiary.utils.views.DividerItemDecorator
import com.xw.repo.BubbleSeekBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import kotlin.reflect.jvm.javaField

@ExperimentalPagingApi
@AndroidEntryPoint
class EditEntryActivity : AppCompatActivity(), ((EntryDescriptionHistory?) -> Unit) {
    private val entriesViewModel: EntriesViewModel by viewModels()
    private val entryDescriptionHistoriesViewModel: EntryDescriptionHistoriesViewModel by viewModels()
    private val optionFieldsViewModel: OptionFieldsViewModel by viewModels()
    private val optionFieldEntrySpecificValuesViewModel: OptionFieldEntrySpecificValuesViewModel by viewModels()

    private val binding by lazy {
        ActivityEditEntryBinding.inflate(layoutInflater)
    }


    var entryId: Long? = null
    var entry: Entry? = null
    var entryDescriptionHistory: EntryDescriptionHistory? = null
    var entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplified: List<EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified>? =
        null

    private val entryDescriptionHistoriesPaginatedAdapter by lazy {
        EntryDescriptionHistoriesPaginatedAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        entryId = intent.getLongExtra(Entry::entryId.javaField!!.name, -1L)

        lifecycleScope.launch {
            entryId?.let {
                if (it != -1L) {
                    entry = entriesViewModel.getEntryById(it)
                    entry?.let {
                        title = it.entryTitle
                        binding.titleEditText.setText(it.entryTitle)
                    }
                    getOptionFieldsForEntry()
                    entryDescriptionHistory =
                        entryDescriptionHistoriesViewModel.getEntryDescriptionHistoryLatestByEntryId(
                            it
                        )
                    entryDescriptionHistory?.let {
                        binding.descriptionEditText.setText(it.entryDescriptionHistoryDescription)
                    }
                    setupRecyclerView()
                    fetchEntryDescriptionHistoriesFromCache()
                }
            }
        }




        binding.saveFab.setOnClickListener {
            entry?.let {

                if (binding.titleEditText.text.toString().trim().isNotEmpty()) {
                    saveEntry()
                }


            }
        }
    }


    private fun getOptionFieldsForEntry() {
        lifecycleScope.launch {
            val value =
                optionFieldsViewModel.getAllOptionFieldsWithValueInfosAndEntrySpecificValueByEntryId(
                    entryId!!
                )
            val grouped =
                value?.groupBy { it.entryOptionFieldWithValueInfosAndEntrySpecificValueOptionField }
                    ?.toList()
            val groupedMap = grouped?.map { it.second.toUIList() }
            entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplified = groupedMap
            populate()
        }
    }

    private fun populate() {
        entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplified?.forEach {
            val seekBar = BubbleSeekBar(this).apply {
                this.getConfigBuilder()
                    .min(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMin)
                    .max(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMax)
                    .progress(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue!!.entryValue)

                    .trackColor(ContextCompat.getColor(context, R.color.seek_bar_text_light))
                    .secondTrackColor(ContextCompat.getColor(context, R.color.colorBlack))
                    .thumbColor(ContextCompat.getColor(context, R.color.colorBlack))
                    .sectionTextColor(ContextCompat.getColor(context, R.color.seek_bar_text_light))
                    .sectionTextSize(18)
                    .thumbTextColor(ContextCompat.getColor(context, R.color.colorBlack))
                    .thumbTextSize(18)
                    .bubbleColor(
                        ContextCompat.getColor(
                            context,
                            R.color.seek_bar_bubble_background
                        )
                    )
                    .bubbleTextSize(18)

                    .sectionCount(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMax.toInt() - it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMin.toInt())
                    //.sectionTextPosition(BubbleSeekBar.TextPosition.BOTTOM_SIDES)
                    .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                    //.showProgressInFloat()
                    .showSectionMark()
                    .showSectionText()
                    .showThumbText()
                    .touchToSeek()
                    .autoAdjustSectionMark()
                    .build()

                this.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                this.onProgressChangedListener = object : BubbleSeekBar.OnProgressChangedListener {
                    override fun onProgressChanged(
                        bubbleSeekBar: BubbleSeekBar?,
                        progress: Int,
                        progressFloat: Float,
                        fromUser: Boolean
                    ) {
                    }

                    override fun getProgressOnActionUp(
                        bubbleSeekBar: BubbleSeekBar?,
                        progress: Int,
                        progressFloat: Float
                    ) {
                    }

                    override fun getProgressOnFinally(
                        bubbleSeekBar: BubbleSeekBar?,
                        progress: Int,
                        progressFloat: Float,
                        fromUser: Boolean
                    ) {
                        updateSeekBarForOptionFieldId(
                            it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldId,
                            progressFloat
                        )
                    }

                }
            }
            binding.customFieldsLinearLayout.addView(seekBar)
        }
    }


    fun updateSeekBarForOptionFieldId(optionFieldId: Long, seekBarProgress: Float) {
        entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplified?.forEach {
            if (it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField?.optionFieldId == optionFieldId) {
                it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue?.entryValue =
                    seekBarProgress
                return@forEach
            }
        }
    }


    private fun saveEntry() {
        entry?.let {
            it.entryTitle = binding.titleEditText.text.toString()
            it.entryLastModifiedDate = Calendar.getInstance().time
            lifecycleScope.launch {
                val rowsUpdated = entriesViewModel.updateSingle(it)
                if (rowsUpdated > 0) {
                    saveHistory()
                }
            }
        }
    }

    private fun saveHistory() {
        entry?.let {
            if (binding.descriptionEditText.text.trim()
                    .toString() != entryDescriptionHistory?.entryDescriptionHistoryDescription ?: ""
            ) {
                entryDescriptionHistory = EntryDescriptionHistory(
                    entryDescriptionHistoryParentEntryId = it.entryId,
                    entryDescriptionHistoryDescription = binding.descriptionEditText.text.toString(),
                    entryDescriptionHistoryAddedDate = Calendar.getInstance().time
                )
                lifecycleScope.launch {
                    val id = entryDescriptionHistoriesViewModel.insertSingle(
                        entryDescriptionHistory!!
                    )
                    id?.let { _ ->
                        saveEntryOptionFieldValues()
                        scrollToTopOfDescriptionHistoriesRecyclerView()
                    }
                }
            } else {
                saveEntryOptionFieldValues()
                scrollToTopOfDescriptionHistoriesRecyclerView()
            }
        }
    }


    private fun saveEntryOptionFieldValues() {
        if (entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplified?.isNullOrEmpty() == false) {
            entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplified?.let {
                lifecycleScope.launch {
                    val rowsUpdated =
                        optionFieldEntrySpecificValuesViewModel.update(it.toDatabaseList())
                    if (rowsUpdated > 0) {
                        toast(getString(R.string.saved_toast_message))
                        //finish()
                    }
                }
            }
        } else {
            toast(getString(R.string.saved_toast_message))
        }
    }

    val scrollHandler = Handler(Looper.getMainLooper())
    private fun scrollToTopOfDescriptionHistoriesRecyclerView() {
        scrollHandler.removeCallbacksAndMessages(null)
        scrollHandler.postDelayed({
            binding.entryDescriptionHistoriesRecyclerView.smoothScrollToPosition(0)
        }, 200)
    }

    private fun setupRecyclerView() {
        binding.entryDescriptionHistoriesRecyclerView.apply {
            addItemDecoration(
                DividerItemDecorator(
                    ContextCompat.getDrawable(
                        this.context,
                        R.drawable.divider
                    )!!
                )
            )
            adapter = entryDescriptionHistoriesPaginatedAdapter
        }
    }


    var getEntryDescriptionHistoriesCoroutine: Job? = null

    @SuppressLint("CheckResult")
    private fun fetchEntryDescriptionHistoriesFromCache() {
        getEntryDescriptionHistoriesCoroutine?.cancel()
        getEntryDescriptionHistoriesCoroutine = CoroutineScope(Dispatchers.Main).launch {
            entryDescriptionHistoriesViewModel.getEntryDescriptionHistoriesByEntryIdPaginated(
                entryId!!
            ).collectLatest {
                entryDescriptionHistoriesPaginatedAdapter.submitData(it)
            }
        }
    }

    override fun invoke(item: EntryDescriptionHistory?) {
        item?.let {
            toast(it.entryDescriptionHistoryDescription)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        getEntryDescriptionHistoriesCoroutine?.cancel()
    }
}