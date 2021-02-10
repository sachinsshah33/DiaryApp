package com.appservicesunlimited.anydiary.data.entry

import androidx.room.Embedded
import androidx.room.Relation
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class EntryWithDescription {
    @SerialName("entry_with_description_entry")
    @Embedded
    var entryWithDescriptionEntry: Entry? = null

    @SerialName("entry_with_description_description")
    @Relation(parentColumn = "entryId", entityColumn = "entryDescriptionHistoryParentEntryId")
    var entryWithDescriptionDescription: EntryDescriptionHistory? = null
}

sealed class EntryWithDescriptionUIModel {
    class Item(val item: EntryWithDescription) : EntryWithDescriptionUIModel()
    class DateHeader(val date: Date?) : EntryWithDescriptionUIModel()
}