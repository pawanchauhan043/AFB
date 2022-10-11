package com.example.afb.di

import com.example.afb.AfbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AfbModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.imgflip.com/")
            .build()
    }

    @Provides
    fun providesAfbApi(retrofit: Retrofit): AfbApi {
        return retrofit.create(AfbApi::class.java)
    }
}
