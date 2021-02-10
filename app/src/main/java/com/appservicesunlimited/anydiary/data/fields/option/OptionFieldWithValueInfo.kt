package com.appservicesunlimited.anydiary.data.fields.option

import androidx.room.Embedded
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValue
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OptionFieldWithValueInfo {
    @SerialName("option_field_with_value_info_value_info")
    @Embedded
    var optionFieldWithValueInfoValueInfo: OptionFieldValueInfo? = null

    @SerialName("option_field_with_value_info_option_field")
    @Embedded
    var optionFieldWithValueInfoOptionField: OptionField? = null
}

fun List<OptionFieldWithValueInfo>.toUIList(): EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified {
    val entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField =
        this.first().optionFieldWithValueInfoOptionField
    val entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos =
        this.map { it.optionFieldWithValueInfoValueInfo!! }
    return EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified().apply {
        this.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos =
            entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos
        this.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField =
            entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField
        this.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue =
            OptionFieldEntrySpecificValue(
                specificValueParentOptionFieldId = entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField!!.optionFieldId,
                entryValue = entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField.optionFieldDefault
            )
    }
}