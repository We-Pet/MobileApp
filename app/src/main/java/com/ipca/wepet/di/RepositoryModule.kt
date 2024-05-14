package com.ipca.wepet.di

import com.ipca.wepet.data.repository.AnimalRepositoryImpl
import com.ipca.wepet.domain.repository.AnimalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAnimalRepository(
        animalRepositoryImpl: AnimalRepositoryImpl
    ): AnimalRepository
}