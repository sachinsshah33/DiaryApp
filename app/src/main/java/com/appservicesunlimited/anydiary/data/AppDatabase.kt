package com.appservicesunlimited.anydiary.data


import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.appservicesunlimited.anydiary.App
import com.appservicesunlimited.anydiary.data.entry.EntriesDAO
import com.appservicesunlimited.anydiary.data.entry.Entry
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistoriesDAO
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistory
import com.appservicesunlimited.anydiary.data.fields.option.OptionField
import com.appservicesunlimited.anydiary.data.fields.option.OptionFieldsDAO
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValue
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValuesDAO
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfo
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfosDAO
import com.appservicesunlimited.anydiary.utils.helpers.Constants.OptionFieldType
import java.util.*
import java.util.concurrent.Executors


@ExperimentalPagingApi
@Database(
    entities = [Entry::class, EntryDescriptionHistory::class, OptionField::class, OptionFieldValueInfo::class, OptionFieldEntrySpecificValue::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entriesDAO(): EntriesDAO
    abstract fun entryDescriptionHistoriesDAO(): EntryDescriptionHistoriesDAO

    abstract fun optionFieldsDAO(): OptionFieldsDAO
    abstract fun optionFieldValueInfosDAO(): OptionFieldValueInfosDAO
    abstract fun optionFieldEntrySpecificValuesDAO(): OptionFieldEntrySpecificValuesDAO


    companion object {
        val DATABASE_NAME: String = "anydiary_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        if (App.isDeveloper) {
                            getInstance(context).let {
                                //todo remove Provider<___>'s from provideAppDatabase above and code in here
                                /*ArrayList<Entry>().apply {
                                    val currentTime = Calendar.getInstance().time
                                    for (x in 0 until 1000) {
                                        val toAdd = Entry()
                                        toAdd.let {
                                            val time = currentTime.toCalander().add(hours = -(1000.random())).time
                                            it.entryTitle = UUID.randomUUID().toString()
                                            it.entryCreatedDate = time
                                            it.entryLastModifiedDate = time
                                        }
                                        add(toAdd)
                                    }
                                    Executors.newSingleThreadExecutor().execute {
                                        it.entriesDAO().insert(this)
                                    }
                                }*/




                                Executors.newSingleThreadExecutor().execute {
                                    it.runInTransaction {
                                        val optionFieldId = it.optionFieldsDAO().insertSingle(
                                            OptionField(
                                                optionFieldTitle = "OptionField1",
                                                optionFieldDescription = "OptionField1",
                                                optionFieldPosition = 0,
                                                optionFieldMin = 1f,
                                                optionFieldMax = 5f,
                                                optionFieldDefault = 3f,
                                                optionFieldType = OptionFieldType.seekBar
                                            )
                                        )
                                        it.optionFieldValueInfosDAO()
                                            .insert(ArrayList<OptionFieldValueInfo>().apply {
                                                for (x in 1..5) {
                                                    val toAdd = OptionFieldValueInfo()
                                                    toAdd.let {
                                                        it.optionFieldValueInfoParentOptionFieldId =
                                                            optionFieldId!!
                                                        it.optionFieldValueInfoValuePosition = x
                                                        it.optionFieldValueInfoDescription =
                                                            "A:${x}"
                                                    }
                                                    add(toAdd)
                                                }
                                            })


                                        val optionFieldId2 = it.optionFieldsDAO().insertSingle(
                                            OptionField(
                                                optionFieldTitle = "OptionField2",
                                                optionFieldDescription = "OptionField2",
                                                optionFieldPosition = 1,
                                                optionFieldMin = 1f,
                                                optionFieldMax = 2f,
                                                optionFieldDefault = 2f,
                                                optionFieldType = OptionFieldType.seekBar
                                            )
                                        )
                                        it.optionFieldValueInfosDAO()
                                            .insert(ArrayList<OptionFieldValueInfo>().apply {
                                                for (x in 1..2) {
                                                    val toAdd = OptionFieldValueInfo()
                                                    toAdd.let {
                                                        it.optionFieldValueInfoParentOptionFieldId =
                                                            optionFieldId2!!
                                                        it.optionFieldValueInfoValuePosition = x
                                                        it.optionFieldValueInfoDescription =
                                                            "B:${x}"
                                                    }
                                                    add(toAdd)
                                                }
                                            })

                                        val time = Calendar.getInstance().time
                                        val entryId = it.entriesDAO().insertSingle(
                                            Entry(
                                                entryTitle = "1st entry",
                                                entryCreatedDate = time,
                                                entryLastModifiedDate = time
                                            )
                                        )

                                        it.optionFieldEntrySpecificValuesDAO().insert(
                                            arrayListOf(
                                                OptionFieldEntrySpecificValue(
                                                    specificValueParentEntryId = entryId!!,
                                                    specificValueParentOptionFieldId = optionFieldId!!,
                                                    entryValue = 3f
                                                ),
                                                OptionFieldEntrySpecificValue(
                                                    specificValueParentEntryId = entryId,
                                                    specificValueParentOptionFieldId = optionFieldId2!!,
                                                    entryValue = 2f
                                                )
                                            )
                                        )
                                    }
                                }


                            }
                        }
                    }
                })
                .build()
    }
}