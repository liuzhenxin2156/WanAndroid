package com.pzj.wanandroid_kotlin.network

import com.example.myapplication.BuildConfig
import com.pzj.wanandroid_kotlin.network.config.NetworkConfig
import com.pzj.wanandroid_kotlin.network.interceptor.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @description: RetrofitClient
 * @author: pengzhenjin
 * @date: 2020/10/29
 */
class RetrofitClient private constructor() {

    private var retrofit: Retrofit

    /**
     * 单例模式：双重校验锁式
     */
    companion object {
        val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { RetrofitClient() }
    }

    init {
        retrofit = Retrofit.Builder()
            .client(initOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConfig.BASE_URL)
            .build()
    }

    /**
     * 初始化 OkHttp
     */
    private fun initOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addNetworkInterceptor(initLoggingInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * 初始化日志拦截器
     */
    private fun initLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }
        return loggingInterceptor
    }

    /**
     * 创建 service
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

}