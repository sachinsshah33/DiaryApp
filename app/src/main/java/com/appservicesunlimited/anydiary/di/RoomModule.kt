package com.appservicesunlimited.anydiary.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.data.AppDatabase
import com.appservicesunlimited.anydiary.data.entry.EntriesDAO
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistoriesDAO
import com.appservicesunlimited.anydiary.data.fields.option.OptionFieldsDAO
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValuesDAO
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfosDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideEntriesDAO(appDatabase: AppDatabase): EntriesDAO {
        return appDatabase.entriesDAO()
    }

    @Singleton
    @Provides
    fun provideEntryDescriptionHistoriesDAO(appDatabase: AppDatabase): EntryDescriptionHistoriesDAO {
        return appDatabase.entryDescriptionHistoriesDAO()
    }

    @Singleton
    @Provides
    fun provideOptionFieldsDAO(appDatabase: AppDatabase): OptionFieldsDAO {
        return appDatabase.optionFieldsDAO()
    }

    @Singleton
    @Provides
    fun provideOptionFieldValueInfosDAO(appDatabase: AppDatabase): OptionFieldValueInfosDAO {
        return appDatabase.optionFieldValueInfosDAO()
    }

    @Singleton
    @Provides
    fun provideOptionFieldEntrySpecificValuesDAO(appDatabase: AppDatabase): OptionFieldEntrySpecificValuesDAO {
        return appDatabase.optionFieldEntrySpecificValuesDAO()
    }
}