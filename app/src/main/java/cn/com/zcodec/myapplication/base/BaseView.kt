/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.01
 */
package cn.com.zcodec.myapplication.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


/**
 * 封装通用View和Presenter和Model
 */
interface BaseView {

    /**
     * View通用方法
     */
    interface View {

        /**
         * api连接出错
         */
        fun onApiFail(msg: String)
    }

    /**
     * Presenter通用方法
     * LifecycleObserver和Activity生命周期绑定
     */
    interface Presenter: LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun detachView()
    }

    /**
     * Model通用方法
     */
    interface Model {

        fun onDestroy()
    }

}