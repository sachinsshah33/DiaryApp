package com.appservicesunlimited.anydiary.data.entry

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("entry_id")
    var entryId: Long = 0,

    @Expose
    @SerializedName("entry_title")
    var entryTitle: String = "",

    @Expose
    @SerializedName("entry_created_date")
    var entryCreatedDate: Date = Calendar.getInstance().time,

    @Expose
    @SerializedName("entry_last_modified_date")
    var entryLastModifiedDate: Date = Calendar.getInstance().time,

    @Expose
    @SerializedName("entry_show_as_date")
    var entryShowAsDate: Date = Calendar.getInstance().time
) : Parcelable