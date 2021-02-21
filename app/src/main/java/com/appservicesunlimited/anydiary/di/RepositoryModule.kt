package com.appservicesunlimited.anydiary.di

import androidx.paging.ExperimentalPagingApi
import com.appservicesunlimited.anydiary.data.entry.EntriesDAO
import com.appservicesunlimited.anydiary.data.entry.EntriesRepository
import com.appservicesunlimited.anydiary.data.entry.IEntriesRepository
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistoriesDAO
import com.appservicesunlimited.anydiary.data.entryDescriptionHistory.EntryDescriptionHistoriesRepository
import com.appservicesunlimited.anydiary.data.fields.option.OptionFieldsDAO
import com.appservicesunlimited.anydiary.data.fields.option.OptionFieldsRepository
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValuesDAO
import com.appservicesunlimited.anydiary.data.fields.option.entrySpecificValue.OptionFieldEntrySpecificValuesRepository
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfosDAO
import com.appservicesunlimited.anydiary.data.fields.option.valueInfo.OptionFieldValueInfosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@ExperimentalPagingApi
@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideEntriesRepository(dao: EntriesDAO): IEntriesRepository {
        return EntriesRepository(dao) as IEntriesRepository
    }


    @Singleton
    @Provides
    fun provideEntryDescriptionHistoriesRepository(dao: EntryDescriptionHistoriesDAO): EntryDescriptionHistoriesRepository {
        return EntryDescriptionHistoriesRepository(dao)
    }

    @Singleton
    @Provides
    fun provideOptionFieldsRepository(dao: OptionFieldsDAO): OptionFieldsRepository {
        return OptionFieldsRepository(dao)
    }

    @Singleton
    @Provides
    fun provideOptionFieldValueInfosRepository(dao: OptionFieldValueInfosDAO): OptionFieldValueInfosRepository {
        return OptionFieldValueInfosRepository(dao)
    }

    @Singleton
    @Provides
    fun provideOptionFieldEntrySpecificValuesRepository(dao: OptionFieldEntrySpecificValuesDAO): OptionFieldEntrySpecificValuesRepository {
        return OptionFieldEntrySpecificValuesRepository(dao)
    }
}