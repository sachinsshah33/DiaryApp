package com.appservicesunlimited.anydiary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appservicesunlimited.anydiary.R
import com.appservicesunlimited.anydiary.data.entry.EntryWithDescription
import com.appservicesunlimited.anydiary.data.entry.EntryWithDescriptionUIModel
import com.appservicesunlimited.anydiary.databinding.DateHeaderListItemBinding
import com.appservicesunlimited.anydiary.databinding.EntryListItemBinding
import com.appservicesunlimited.anydiary.utils.extensions.toDateStamp
import com.appservicesunlimited.anydiary.utils.extensions.toDateTimeStamp

class EntriesWithDescriptionAndHeaderPaginatedAdapter(val onItemSelected: ((EntryWithDescription?) -> Unit)? = null) :
    PagingDataAdapter<EntryWithDescriptionUIModel, RecyclerView.ViewHolder>(
        DIFF_CALLBACK
    ) {
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<EntryWithDescriptionUIModel> =
            object : DiffUtil.ItemCallback<EntryWithDescriptionUIModel>() {
                override fun areItemsTheSame(
                    oldItem: EntryWithDescriptionUIModel,
                    newItem: EntryWithDescriptionUIModel
                ): Boolean {
                    return if (oldItem is EntryWithDescriptionUIModel.Item && newItem is EntryWithDescriptionUIModel.Item) {
                        oldItem.item.entryWithDescriptionEntry?.entryId == newItem.item.entryWithDescriptionEntry?.entryId
                    } else if (oldItem is EntryWithDescriptionUIModel.DateHeader && newItem is EntryWithDescriptionUIModel.DateHeader) {
                        oldItem.date == newItem.date
                    } else {
                        false
                    }
                }

                override fun areContentsTheSame(
                    oldItem: EntryWithDescriptionUIModel,
                    newItem: EntryWithDescriptionUIModel
                ): Boolean {
                    return if (oldItem is EntryWithDescriptionUIModel.Item && newItem is EntryWithDescriptionUIModel.Item) {
                        oldItem.item.entryWithDescriptionEntry == newItem.item.entryWithDescriptionEntry && oldItem.item.entryWithDescriptionDescription == newItem.item.entryWithDescriptionDescription
                    } else if (oldItem is EntryWithDescriptionUIModel.DateHeader && newItem is EntryWithDescriptionUIModel.DateHeader) {
                        oldItem.date == newItem.date
                    } else {
                        false
                    }
                }
            }
    }


    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is EntryWithDescriptionUIModel.Item -> R.layout.entry_list_item
        is EntryWithDescriptionUIModel.DateHeader -> R.layout.date_header_list_item
        null -> throw IllegalStateException("Unknown view")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.date_header_list_item -> DateHeaderViewHolder(
                DateHeaderListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ItemViewHolder(
                EntryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            val item = getItem(position)
            when (holder) {
                is ItemViewHolder -> {
                    holder.bind(item as EntryWithDescriptionUIModel.Item, position)
                }
                is DateHeaderViewHolder -> {
                    holder.bind(item as EntryWithDescriptionUIModel.DateHeader, position)
                }
            }
        }
    }


    inner class ItemViewHolder(private val itemBinding: EntryListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: EntryWithDescriptionUIModel.Item, position: Int) {
            itemBinding.title.text = data.item.entryWithDescriptionEntry?.entryTitle
            itemBinding.description.text =
                data.item.entryWithDescriptionEntry?.entryLastModifiedDate?.toDateTimeStamp()
            itemBinding.description.text =
                data.item.entryWithDescriptionDescription?.entryDescriptionHistoryDescription
            itemBinding.entryListItemBox.setOnClickListener {
                onItemSelected?.invoke(data.item)
            }
        }
    }

    inner class DateHeaderViewHolder(private val itemBinding: DateHeaderListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: EntryWithDescriptionUIModel.DateHeader, position: Int) {
            itemBinding.dateHeader.text = data.date?.toDateStamp()
        }
    }
}