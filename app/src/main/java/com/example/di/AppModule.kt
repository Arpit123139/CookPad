package com.example.di

import com.example.api.AuthInterceptor
import com.example.api.CustomApi
import com.example.api.MealApi
import com.example.api.MealSaveApi
import com.example.utils.constants.Companion.base_url
import com.example.utils.constants.Companion.base_url1
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())


    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit.Builder): MealApi =
        retrofit.baseUrl(base_url).build().create(MealApi::class.java)


    @Provides
    @Singleton
    fun providesCustomApi(retrofit: Retrofit.Builder): CustomApi =
        retrofit.baseUrl(base_url1).build().create(CustomApi::class.java)

    @Provides
    @Singleton
    fun ProvideOkHttpClient(authInterceptor: AuthInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesMealSaveApi(retrofit: Retrofit.Builder,okHttpClient: OkHttpClient): MealSaveApi =
        retrofit.baseUrl(base_url1)
            .client(okHttpClient)
            .build().create(MealSaveApi::class.java)

}