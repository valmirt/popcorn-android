package com.torres.valmir.kotlinMvpDagger2.di.module

import android.content.Context
import com.torres.valmir.kotlinMvpDagger2.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(context: Context): OkHttpClient {
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val httpCacheDirectory = File (context.cacheDir, "offlineCache")
        val cache = Cache(httpCacheDirectory, 20 * 1024 * 1024)

        val offlineInterceptor = Interceptor { chain ->
            try {
                return@Interceptor chain.proceed(chain.request())
            } catch (e: Exception) {
                val cacheControl = CacheControl.Builder()
                        .onlyIfCached()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                val offlineRequest = chain.request()
                        .newBuilder()
                        .removeHeader("Pragma")
                        .cacheControl(cacheControl)
                        .build()
                return@Interceptor chain.proceed(offlineRequest)
            }
        }

        val cacheInterceptor = Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            val cacheControl = originalResponse.header("Cache-Control")
            return@Interceptor if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
            ) {
                originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build()
            } else {
                originalResponse
            }
        }

        val client = OkHttpClient.Builder()
        client.cache(cache)
        client.addInterceptor(interceptor)
        client.addNetworkInterceptor(cacheInterceptor)
        client.addInterceptor(offlineInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build()
    }
}