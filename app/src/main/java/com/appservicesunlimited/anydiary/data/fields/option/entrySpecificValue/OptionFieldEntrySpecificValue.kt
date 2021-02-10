package com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.appservicesunlimited.anydiary.data.fields.option.OptionField
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "option_field_entry_specific_value",
    primaryKeys = ["specificValueParentEntryId", "specificValueParentOptionFieldId"],
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE, entity = Entry::class,
            parentColumns = ["entryId"],
            childColumns = ["specificValueParentEntryId"]
        ),
        ForeignKey(
            onDelete = ForeignKey.CASCADE, entity = OptionField::class,
            parentColumns = ["optionFieldId"],
            childColumns = ["specificValueParentOptionFieldId"]
        )
    ]
)
data class OptionFieldEntrySpecificValue(
    @Expose
    @SerializedName("specific_value_parent_entry_id")
    var specificValueParentEntryId: Long = 0,

    @Expose
    @SerializedName("specific_value_parent_option_field_id")
    var specificValueParentOptionFieldId: Long = 0,

    @Expose
    @SerializedName("entry_value")
    var entryValue: Float = 0f
) : Parcelable