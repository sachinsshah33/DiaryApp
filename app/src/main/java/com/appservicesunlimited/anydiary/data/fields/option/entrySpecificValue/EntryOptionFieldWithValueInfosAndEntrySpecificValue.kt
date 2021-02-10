package com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue

import androidx.room.Embedded
import com.appservicesunlimited.anydiary.data.fields.option.OptionField
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EntryOptionFieldWithValueInfosAndEntrySpecificValue {
    @SerialName("entry_option_field_with_value_infos_and_entry_specific_value_value_info")
    @Embedded
    var entryOptionFieldWithValueInfosAndEntrySpecificValueValueInfo: OptionFieldValueInfo? = null

    @SerialName("entry_option_field_with_value_infos_and_entry_specific_value_option_field")
    @Embedded
    var entryOptionFieldWithValueInfosAndEntrySpecificValueOptionField: OptionField? = null

    @SerialName("entry_option_field_with_value_infos_and_entry_specific_value_option_field_entry_specific_value")
    @Embedded
    var entryOptionFieldWithValueInfosAndEntrySpecificValueOptionFieldEntrySpecificValue: OptionFieldEntrySpecificValue? =
        null
}


fun List<EntryOptionFieldWithValueInfosAndEntrySpecificValue>.toUIList(): EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified {
    val entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField =
        this.first().entryOptionFieldWithValueInfosAndEntrySpecificValueOptionField
    val entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos =
        this.map { it.entryOptionFieldWithValueInfosAndEntrySpecificValueValueInfo!! }
    val entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue =
        this.first().entryOptionFieldWithValueInfosAndEntrySpecificValueOptionFieldEntrySpecificValue
    return EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified().apply {
        this.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos =
            entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos
        this.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField =
            entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField
        this.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue =
            entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue
    }
}