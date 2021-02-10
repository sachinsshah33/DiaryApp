package com.appservicesunlimited.anydiary.data.fields.option

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.appservicesunlimited.anydiary.utils.helpers.Constants.OptionFieldType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "option_fields")
data class OptionField(
    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("option_field_id")
    var optionFieldId: Long = 0,

    @Expose
    @SerializedName("option_field_title")
    var optionFieldTitle: String = "",

    @Expose
    @SerializedName("option_field_description")
    var optionFieldDescription: String = "",

    @Expose
    @SerializedName("option_field_position")
    var optionFieldPosition: Int = 0,

    @Expose
    @SerializedName("option_field_min")
    var optionFieldMin: Float = 0f,

    @Expose
    @SerializedName("option_field_max")
    var optionFieldMax: Float = 1f,

    @Expose
    @SerializedName("option_field_default")
    var optionFieldDefault: Float = 0f,

    @Expose
    @SerializedName("option_field_type")
    var optionFieldType: OptionFieldType = OptionFieldType.unknown,

    @Expose
    @SerializedName("option_field_created_date")
    var optionFieldCreatedDate: Date = Calendar.getInstance().time,

    @Expose
    @SerializedName("option_field_last_modified_date")
    var optionFieldLastModifiedDate: Date = Calendar.getInstance().time
) : Parcelable