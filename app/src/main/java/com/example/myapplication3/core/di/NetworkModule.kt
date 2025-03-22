package com.example.myapplication3.core.di

import android.content.Context
import com.example.myapplication3.R
import com.example.myapplication3.data.remote.ProjectApiServices

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesFourSquareServices(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient,
        gsonConverter : GsonConverterFactory) : ProjectApiServices{
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return  retrofit.create(ProjectApiServices::class.java)
    }
    @Provides
    @Singleton
    fun providesGsonFactory() : GsonConverterFactory {
        return  GsonConverterFactory.create();
    }
    @Provides
    @Singleton
    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        return builder.build();
    }
    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}