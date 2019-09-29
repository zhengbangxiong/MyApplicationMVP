/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-09-04
 * @version：1.01
 */
package cn.com.zcodec.myapplication.di.module

import cn.com.zcodec.myapplication.BuildConfig
import cn.com.zcodec.myapplication.network.HttpAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class RetrofitModule {

    companion object {
        private const val API_BASE_URL = "http://192.188.183.75/kj-gateway/"
    }

    /**
     * 返回拦截日志对象
     */
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return loggingInterceptor
    }

    /**
     * OkHttpClient
     * 添加拦截日志
     */
    @Provides
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    /**
     * 返回Retrofit对象
     * @return
     */
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                //网关
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Observable<T>的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    /**
     * 创建网络请求接口的实例
     * @return
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): HttpAPI {
        return retrofit.create(HttpAPI::class.java)
    }

}