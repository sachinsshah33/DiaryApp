package com.appservicesunlimited.anydiary.data.entryDescriptionHistory

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(
    tableName = "entry_description_histories",
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE, entity = Entry::class,
            parentColumns = ["entryId"],
            childColumns = ["entryDescriptionHistoryParentEntryId"]
        )
    ]
)
data class EntryDescriptionHistory(
    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("entry_description_history_id")
    var entryDescriptionHistoryId: Long = 0,

    @Expose
    @SerializedName("entry_description_history_parent_entry_id")
    var entryDescriptionHistoryParentEntryId: Long,

    @Expose
    @SerializedName("entry_description_history_description")
    var entryDescriptionHistoryDescription: String = "",

    @Expose
    @SerializedName("entry_description_history_added_date")
    var entryDescriptionHistoryAddedDate: Date = Calendar.getInstance().time
) : Parcelable