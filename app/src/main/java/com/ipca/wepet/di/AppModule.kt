package com.ipca.wepet.di

import android.app.Application
import androidx.room.Room
import com.ipca.wepet.data.local.animal.AnimalDatabase
import com.ipca.wepet.data.remote.WePetApi
import com.ipca.wepet.util.DateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWePetApi(): WePetApi {
        val moshi = Moshi.Builder()
            .add(DateAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl(WePetApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .build()
            )
            .build()
            .create(WePetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimalDatabase(app: Application): AnimalDatabase {
        return Room.databaseBuilder(
            app,
            AnimalDatabase::class.java,
            "animaldb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(DateAdapter())
            .build()
    }
}