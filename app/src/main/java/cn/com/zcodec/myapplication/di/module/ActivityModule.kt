/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-09-03
 * @version：1.01
 */
package cn.com.zcodec.myapplication.di.module

import cn.com.zcodec.myapplication.di.module.activity.LoginActivityModule
import cn.com.zcodec.myapplication.di.module.activity.PictureActivityModule
import cn.com.zcodec.myapplication.ui.LoginActivity
import cn.com.zcodec.myapplication.ui.PictureActivity
import com.wazing.mvp.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [LoginActivityModule::class])
    abstract fun bindLoginActivityInjector(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PictureActivityModule::class])
    abstract fun bindPictureActivitytInjector(): PictureActivity

}