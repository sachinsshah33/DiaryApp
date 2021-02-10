package com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue

import androidx.room.Embedded
import com.appservicesunlimited.anydiary.data.fields.option.OptionField
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified {
    @SerialName("entry_option_field_with_value_infos_and_entry_specific_value_simplified_value_info")
    @Embedded
    var entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedValueInfos: List<OptionFieldValueInfo>? =
        null

    @SerialName("entry_option_field_with_value_infos_and_entry_specific_value_simplified_option_field")
    @Embedded
    var entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionField: OptionField? =
        null

    @SerialName("entry_option_field_with_value_infos_and_entry_specific_value_simplified_option_field_entry_specific_value")
    @Embedded
    var entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue: OptionFieldEntrySpecificValue? =
        null
}


fun List<EntryOptionFieldWithValueInfosAndEntrySpecificValueSimplified>.toDatabaseList(entryId: Long? = null): List<OptionFieldEntrySpecificValue> {
    entryId?.let {
        this.forEach {
            it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue?.specificValueParentEntryId =
                entryId
        }
    }
    return this.map { it.entryOptionFieldWithValueInfosAndEntrySpecificValueSimplifiedOptionFieldEntrySpecificValue }
        .filterNotNull()
}

