package com.appservicesunlimited.anydiary.ui.main


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.appservicesunlimited.anydiary.data.entry.EntryWithDescription
import com.appservicesunlimited.anydiary.databinding.EntryListItemBinding
import com.appservicesunlimited.anydiary.utils.extensions.toDateTimeStamp

@ExperimentalPagingApi
class EntriesWithDescriptionPaginatedAdapter(val onItemSelected: ((EntryWithDescription?) -> Unit)? = null) :
    PagingDataAdapter<EntryWithDescription, EntriesWithDescriptionPaginatedAdapter.ViewHolder>(
        COMPARATOR
    ) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<EntryWithDescription>() {
            override fun areItemsTheSame(
                oldItem: EntryWithDescription,
                newItem: EntryWithDescription
            ) =
                oldItem.entryWithDescriptionEntry?.entryId == newItem.entryWithDescriptionEntry?.entryId

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: EntryWithDescription,
                newItem: EntryWithDescription
            ) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EntryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(private val itemBinding: EntryListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: EntryWithDescription, position: Int) {
            itemBinding.title.text = data.entryWithDescriptionEntry?.entryTitle
            itemBinding.description.text =
                data.entryWithDescriptionEntry?.entryLastModifiedDate?.toDateTimeStamp()
            itemBinding.description.text =
                data.entryWithDescriptionDescription?.entryDescriptionHistoryDescription
            itemBinding.entryListItemBox.setOnClickListener {
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