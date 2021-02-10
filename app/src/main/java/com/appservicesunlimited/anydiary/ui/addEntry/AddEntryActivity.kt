package com.appservicesunlimited.anydiary.ui.addEntry

import android.os.Bundle
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
import com.appservicesunlimited.anydiary.data.fields.option.toUIList
import com.appservicesunlimited.anydiary.databinding.ActivityAddEntryBinding
import com.xw.repo.BubbleSeekBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*


@ExperimentalPagingApi
@AndroidEntryPoint
class AddEntryActivity : AppCompatActivity() {
    private val entriesViewModel: EntriesViewModel by viewModels()
    private val entryDescriptionHistoriesViewModel: EntryDescriptionHistoriesViewModel by viewModels()
    private val optionFieldsViewModel: OptionFieldsViewModel by viewModels()
    private val optionFieldEntrySpecificValuesViewModel: OptionFieldEntrySpecificValuesViewModel by viewModels()


    var entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplifiedUI: List<EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified>? =
        null


    private val binding by lazy {
        ActivityAddEntryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getAllOptionFieldsUI()

        binding.addFab.setOnClickListener {
            if (binding.titleEditText.text.toString().trim().isNotEmpty()) {
                val entry = Entry(
                    entryTitle = binding.titleEditText.text.toString(),
                    entryCreatedDate = Calendar.getInstance().time,
                    entryLastModifiedDate = Calendar.getInstance().time
                )

                lifecycleScope.launch {
                    val entryId = entriesViewModel.insertSingle(entry)
                    entryId?.let {
                        if (binding.descriptionEditText.text.toString().trim().isNotEmpty()) {
                            val entryDescriptionHistory = EntryDescriptionHistory(
                                entryDescriptionHistoryParentEntryId = entryId,
                                entryDescriptionHistoryDescription = binding.descriptionEditText.text.toString()
                            )
                            val entryDescriptionHistoryId =
                                entryDescriptionHistoriesViewModel.insertSingle(
                                    entryDescriptionHistory
                                )
                            entryDescriptionHistoryId?.let {
                                saveEntryOptionFieldValues(entryId)
                                //finish()
                            }
                        } else {
                            saveEntryOptionFieldValues(entryId)
                            //finish()
                        }
                    }
                }
            }
        }
    }


    private fun getAllOptionFieldsUI() {
        lifecycleScope.launch {
            val value = optionFieldsViewModel.getAllOptionFieldsWithValueInfos()
            val grouped = value?.groupBy { it.optionFieldWithValueInfoOptionField }?.toList()
            val groupedMap = grouped?.map { it.second.toUIList() }
            entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplifiedUI = groupedMap
            populate()
        }
    }


    private fun populate() {
        entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplifiedUI?.forEach {


            val seekBar = BubbleSeekBar(this).apply {
                this.getConfigBuilder()
                    .min(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMin)
                    .max(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMax)
                    .progress(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldDefault)

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


            /*val seekBar = SeekBar(this).apply {
                this.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                this.max = it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMax
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    this.min = it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldMin//TODO FIXME THIS IS GOING TO BE AN ISSUE
                }
                this.progress = it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldDefault
                this.setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onStopTrackingTouch(seekBar: SeekBar) {
                        updateSeekBarForOptionFieldId(it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldId, seekBar.progress)
                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar) {}
                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {}
                })
            }*/
            binding.customFieldsLinearLayout.addView(seekBar)
        }
    }


    fun updateSeekBarForOptionFieldId(optionFieldId: Long, seekBarProgress: Float) {
        entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplifiedUI?.forEach {
            if (it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField?.optionFieldId == optionFieldId) {
                it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue?.entryValue =
                    seekBarProgress
                return@forEach
            }
        }
    }


    private fun saveEntryOptionFieldValues(entryId: Long) {
        lifecycleScope.launch {
            entryOptionFieldsWithValueInfosAndEntrySpecificValueSimplifiedUI?.let {
                val inserted = it.toDatabaseList(entryId).let {
                    optionFieldEntrySpecificValuesViewModel.insertMultiple(it)
                }
                inserted?.let {
                    val a = "sdads"
                    finish()
                }
            }
        }
    }
}