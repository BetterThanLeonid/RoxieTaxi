/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.di

import android.content.Context
import com.example.roxiemobiletestapp.BuildConfig
import com.example.roxiemobiletestapp.core.utils.Constants
import com.example.roxiemobiletestapp.core.utils.FileManager
import com.example.roxiemobiletestapp.feature_main.data.remote.api.TaxiApi
import com.example.roxiemobiletestapp.feature_main.data.remote.repository.TaxiRepositoryImpl
import com.example.roxiemobiletestapp.feature_main.domain.repository.TaxiRepository
import com.example.roxiemobiletestapp.feature_main.domain.use_case.GetImageUseCase
import com.example.roxiemobiletestapp.feature_main.domain.use_case.GetOrdersUseCase
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
object MainModule {

    @Provides
    @Singleton
    fun provideGetOrdersUseCase(repository: TaxiRepository): GetOrdersUseCase = GetOrdersUseCase(
        repository = repository
    )

    @Provides
    @Singleton
    fun provideGetImageUseCase(repository: TaxiRepository): GetImageUseCase = GetImageUseCase(
        repository = repository
    )

    @Provides
    @Singleton
    fun provideTaxiRepository(api: TaxiApi, fileManager: FileManager): TaxiRepository =
        TaxiRepositoryImpl(
            api = api,
            fileManager = fileManager
        )

    @Provides
    @Singleton
    fun provideTaxiApi(client: OkHttpClient): TaxiApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(TaxiApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .also { client ->
            if (BuildConfig.DEBUG) {
                client.addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
        }
        .build()

    @Provides
    @Singleton
    fun provideFileManager(@ApplicationContext context: Context): FileManager =
        FileManager(context)
}