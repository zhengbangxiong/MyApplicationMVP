/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-09-03
 * @version：1.01
 */
package cn.com.zcodec.myapplication.di.module.activity

import cn.com.zcodec.myapplication.contract.LoginContract
import cn.com.zcodec.myapplication.contract.PictureContract
import cn.com.zcodec.myapplication.ui.LoginActivity
import cn.com.zcodec.myapplication.ui.PictureActivity
import dagger.Module
import dagger.Provides

@Module
class PictureActivityModule {

    @Provides
    fun provideView(activity: PictureActivity): PictureContract.View = activity

}