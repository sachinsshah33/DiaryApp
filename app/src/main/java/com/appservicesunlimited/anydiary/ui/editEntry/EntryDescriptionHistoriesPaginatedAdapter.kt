package com.appservicesunlimited.anydiary.ui.editEntry


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistory
import com.appservicesunlimited.anydiary.databinding.DescriptionListItemBinding

@ExperimentalPagingApi
class EntryDescriptionHistoriesPaginatedAdapter(val onItemSelected: ((EntryDescriptionHistory?) -> Unit)? = null) :
    PagingDataAdapter<EntryDescriptionHistory, EntryDescriptionHistoriesPaginatedAdapter.ViewHolder>(
        COMPARATOR
    ) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<EntryDescriptionHistory>() {
            override fun areItemsTheSame(
                oldItem: EntryDescriptionHistory,
                newItem: EntryDescriptionHistory
            ) =
                oldItem.entryDescriptionHistoryId == newItem.entryDescriptionHistoryId

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: EntryDescriptionHistory,
                newItem: EntryDescriptionHistory
            ) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DescriptionListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val itemBinding: DescriptionListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: EntryDescriptionHistory, position: Int) {
            itemBinding.description.text = data.entryDescriptionHistoryDescription
            itemBinding.descriptionListItemBox.setOnClickListener {
                onItemSelected?.invoke(data)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }
}