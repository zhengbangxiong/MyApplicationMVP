/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.01
 */
package cn.com.zcodec.myapplication.contract

import cn.com.zcodec.myapplication.base.BaseView

/**
 * 登录Contract
 */
interface LoginContract {

    interface View : BaseView.View {

        fun onResult(result: String)
        fun onError(result: String)
        fun getUserName(): String
        fun getPassword(): String
        fun getUserNameError()
        fun getPasswordError()
        fun showLoadingDialog()
        fun dmisssLoadingDialog()
    }

    interface Presenter : BaseView.Presenter {
        fun getUserNameError()
        fun getPasswordError()
        fun onResult(result: String)
        fun onError(result: String)
        fun showLoadingDialog()
        fun dmisssLoadingDialog()
        fun onApiFail(msg: String)
    }

    interface Model : BaseView.Model {
        fun login(userName: String, password: String, presenter: Presenter)
    }

}