/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.02
 */
package cn.com.zcodec.myapplication.model

import cn.com.zcodec.myapplication.util.ResultConstant
import cn.com.zcodec.myapplication.contract.LoginContract
import cn.com.zcodec.myapplication.network.HttpAPI
import cn.com.zcodec.myapplication.util.Session
import cn.com.zcodec.myapplication.util.Utils
import cn.com.zcodec.myapplication.beans.Login
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * 登录model
 */
class LoginModel @Inject constructor(private val httpAPI: HttpAPI): LoginContract.Model {

    private val disposables by lazy { CompositeDisposable() }

    /**
     * username and password写死
     */
    val jsonStr = "{\"phone\":\"13048000897\",\"login_pin\":\"e3ceb5881a0a1fdaad01296d7554868d\"}"
    val signStr = "d718fa0bab4c0624dc157032c186109c070d1142f7f5477173d8a0df8ba4d7128f25dff8585064ef3e0f333e35d4379d87c9c0e68da1ec1c364562041dd6d6f97428bfc98931cc3ff5d55bc96db4442f0ead6b12fa983c720ce0bc9868475db3b2c02958973985cf71f207679b9f92a96e003b179757e08fadf4711c7767cf27"

    override fun login(userName: String, password: String, presenter: LoginContract.Presenter) {
        /**
         * 判断账号密码是否为空，如果不是,调用登录接口
         */
        if (Utils.isNullOrEmpty(userName)) {
            presenter.getUserNameError() //调用账号错误对话框
        } else if (Utils.isNullOrEmpty(password)) {
            presenter.getPasswordError() //调用密码错误对话框
        } else {
            presenter.showLoadingDialog() //加载对话框
            /**
             * 调用登录接口
             */
            httpAPI.PostLoginRetrofitRxjavaGson(jsonStr, signStr, Session.equipment)
                    .doOnSubscribe { disposables.add(it) }
                    .compose(Utils.ioMain())
                    .subscribe(object : Observer<Login> {
                        override fun onComplete() {
                            presenter.dmisssLoadingDialog() //取消对话框
                        }

                        override fun onError(e: Throwable) {
                            presenter.dmisssLoadingDialog() //取消对话框
                            presenter.onApiFail(Utils.checkException(e)) //显示api连接出错
                        }

                        override fun onNext(t: Login) {
                            if (ResultConstant.RESULT_0000.equals(t.result_no)) {
                                presenter.onResult("登录成功")
                            } else {
                                presenter.onError(t.result_description)
                            }

                        }

                        override fun onSubscribe(d: Disposable) {
                        }
                    })


        }
    }

    /**
     * 防止内存溢出
     */
    override fun onDestroy() {
        disposables.clear()
    }

}