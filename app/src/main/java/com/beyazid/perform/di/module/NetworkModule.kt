package com.beyazid.perform.di.module

import com.beyazid.perform.ApiService
import com.beyazid.perform.PerformApp
import com.beyazid.perform.utils.BASE_URL
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    /*
     * The method returns the Gson object
     * */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        var gson = Gson()
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        gson = gsonBuilder.create()
        GsonConverterFactory.create(gson)
        return gson
    }

    /*
     * The method returns the Cache object
     */
    @Provides
    @Singleton
    fun provideCache(application: PerformApp): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        //build interceptor
        return Interceptor { chain ->
            // HttpUrl builder with api key
            val url = chain.request()
                .url()
                .newBuilder()
//                .addQueryParameter(CMC_PRO_API_KEY, API_KEY)
                .build()

            // request builder
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }
    }

    /*
     * This returns the OkHttpClient.
     * This will be using by Retrofit
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()


    }

    /*
     * The method returns the Retrofit object
     */
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create()) // To getting plain string
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    /*
     * We need provide ApiService for fetching data from API.
     * For this, We need the Retrofit object, Gson, Cache and OkHttpClient .
     * So we will define the providers for these objects here in this module.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideStethoInitializerBuilder(context: PerformApp): Stetho.InitializerBuilder {
        val initializerBuilder = Stetho.newInitializerBuilder(context)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(context))

        return initializerBuilder
    }

    @Provides
    @Singleton
    internal fun provideStethoInitializer(initializerBuilder: Stetho.InitializerBuilder): Stetho.Initializer {
        return initializerBuilder.build()
    }

}