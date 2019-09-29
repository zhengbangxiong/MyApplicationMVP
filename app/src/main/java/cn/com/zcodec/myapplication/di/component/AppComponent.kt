/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-09-03
 * @version：1.02
 */
package cn.com.zcodec.myapplication.di.component

import cn.com.zcodec.myapplication.application.AppApplication
import cn.com.zcodec.myapplication.di.module.ActivityModule
import cn.com.zcodec.myapplication.di.module.RetrofitModule
import cn.com.zcodec.myapplication.network.HttpAPI
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import retrofit2.Retrofit

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    RetrofitModule::class
])
interface AppComponent {

    fun inject(application: AppApplication)

    fun getRetrofit(): Retrofit

    fun getApiService(): HttpAPI
}