/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.01
 */
package cn.com.zcodec.myapplication.presenter

import cn.com.zcodec.myapplication.contract.LoginContract
import cn.com.zcodec.myapplication.model.LoginModel
import javax.inject.Inject

/**
 * 登录presenter
 */
class LoginPresenter @Inject constructor(
        private val model: LoginModel, private val contract: LoginContract.View
) : LoginContract.Presenter {

    /**
     * 登录
     */
    fun login() {
        model.login(contract.getUserName(), contract.getPassword(), this)
    }

    /**
     * 账号错误
     */
    override fun getUserNameError() {
        contract.getUserNameError()
    }

    /**
     * 密码错误
     */
    override fun getPasswordError() {
        contract.getPasswordError()
    }

    /**
     * 接口成功
     */
    override fun onResult(result: String) {
        contract.onResult(result)
    }

    /**
     * 接口返回错误
     */
    override fun onError(result: String) {
        contract.onError(result)
    }

    /**
     * 对话框隐藏
     */
    override fun dmisssLoadingDialog() {
        contract.dmisssLoadingDialog()
    }

    /**
     * 对话框显示
     */
    override fun showLoadingDialog() {
        contract.showLoadingDialog()
    }

    /**
     * api连接出错
     */
    override fun onApiFail(msg: String) {
        contract.onApiFail(msg)
    }


    /**
     * 连接关闭
     */
    override fun detachView() {
        model.onDestroy()
    }

}