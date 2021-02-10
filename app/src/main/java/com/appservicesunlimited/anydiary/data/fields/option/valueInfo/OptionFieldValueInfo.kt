package com.appservicesunlimited.anydiary.data.fields.option.valueInfo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import com.appservicesunlimited.anydiary.data.fields.option.OptionField
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "option_field_value_infos",
    primaryKeys = ["optionFieldValueInfoParentOptionFieldId", "optionFieldValueInfoValuePosition"],
    foreignKeys = [
        ForeignKey(
            onDelete = ForeignKey.CASCADE, entity = OptionField::class,
            parentColumns = ["optionFieldId"],
            childColumns = ["optionFieldValueInfoParentOptionFieldId"]
        )
    ]
)
data class OptionFieldValueInfo(
    @Expose
    @SerializedName("option_field_value_info_parent_option_field_id")
    var optionFieldValueInfoParentOptionFieldId: Long = 0,

    @Expose
    @SerializedName("option_field_value_info_position")
    var optionFieldValueInfoValuePosition: Int = 0,

    @Expose
    @SerializedName("option_field_value_info_description")
    var optionFieldValueInfoDescription: String = ""
) : Parcelable