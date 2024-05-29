package com.ipca.wepet.di

import com.ipca.wepet.data.repository.animal.AnimalRepositoryImpl
import com.ipca.wepet.data.repository.event.EventRepositoryImpl
import com.ipca.wepet.data.repository.shelter.ShelterRepositoryImpl
import com.ipca.wepet.data.repository.user.UserRepositoryImpl
import com.ipca.wepet.domain.repository.AnimalRepository
import com.ipca.wepet.domain.repository.EventRepository
import com.ipca.wepet.domain.repository.ShelterRepository
import com.ipca.wepet.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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

    @Binds
    @Singleton
    abstract fun bindShelterRepository(
        shelterRepositoryImpl: ShelterRepositoryImpl
    ): ShelterRepository

    @Binds
    @Singleton
    abstract fun bindEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}