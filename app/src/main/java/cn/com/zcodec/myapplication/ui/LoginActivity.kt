/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-07-31
 * @version：1.06
 */
package cn.com.zcodec.myapplication.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_login.*
import butterknife.ButterKnife
import butterknife.OnClick
import cn.com.zcodec.myapplication.R
import cn.com.zcodec.myapplication.base.BaseActivity
import cn.com.zcodec.myapplication.beans.Login
import cn.com.zcodec.myapplication.beans.LoginXML
import cn.com.zcodec.myapplication.contract.LoginContract
import cn.com.zcodec.myapplication.databinding.ActivityLoginBinding
import cn.com.zcodec.myapplication.model.LoginModel
import cn.com.zcodec.myapplication.presenter.LoginPresenter
import cn.com.zcodec.myapplication.util.Utils
import javax.inject.Inject


/**
 * @title:登录
 *
 */
class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener { //实现View.OnClickListener点击接口

    lateinit var loginBinding: ActivityLoginBinding

    @Inject
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) { //重写onCreate方法
        super.onCreate(savedInstanceState)
//        /**
//         * 读取login.xml文件,kotlin语言自动加载控件id
//         * 需要注意的是必须写import kotlinx.android.synthetic.main.activity_login.*，否则id不会自动加载
//         */
//        setContentView(R.layout.activity_login)

        /**
         * databinding
         */
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
//        loginBinding.loginXML = LoginXML("13048000897", "123456")


        /**
         * 控件绑定，butterknife依赖注入
         * 注意事项：使用butterknife10.1.0最新库，必须使用androidstudio3.4以上,grade插件更新到3.4以上
         * @date:2019-08-12
         * @author:zhengbangxiong
         */
        ButterKnife.bind(this)

        //获取读写权限
        Utils.getPerssion(this@LoginActivity)

        //presenter初始化
//        loginPresenter = LoginPresenter(LoginModel(), this)
        lifecycle.addObserver(loginPresenter) //Activity和lifecycle生命周期绑定

    }

    /**
     * 重写onclick点击方法
     */
    @OnClick(R.id.finance_next) //butterknife依赖注入
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.finance_next -> { //登录按钮
                loginPresenter.login()
            }
        }

    }


    /**
     * 获取用户名
     */
    override fun getUserName(): String {
        return et_phone.text.toString()
    }

    /**
     * 获取密码
     */
    override fun getPassword(): String {
        return et_pass.text.toString()
    }

    /**
     * 手机号不能为空
     */
    override fun getUserNameError() {
        Toast.makeText(this@LoginActivity, "手机号不能为空", Toast.LENGTH_LONG).show()
    }

    /**
     * 密码不能为空
     */
    override fun getPasswordError() {
        Toast.makeText(this@LoginActivity, "密码不能为空", Toast.LENGTH_LONG).show()
    }

    /**
     * 登录接口成功返回
     */
    override fun onResult(result: String) {
        Toast.makeText(this@LoginActivity, result, Toast.LENGTH_LONG).show()
        startActivity(Intent(this@LoginActivity, PictureActivity::class.java))
//        this.finish()
    }

    /**
     * 登录失败返回
     */
    override fun onError(result: String) {
        Toast.makeText(this@LoginActivity, result, Toast.LENGTH_LONG).show()
    }

    /**
     * 加载对话框取消
     */
    override fun dmisssLoadingDialog() {
        dmissLoadDialog()

    }

    /**
     * 加载对话框显示
     */
    override fun showLoadingDialog() {
        showLoadDialog()
    }

    /**
     * api连接出错
     */
    override fun onApiFail(msg: String) {
        Toast.makeText(this@LoginActivity, msg, Toast.LENGTH_LONG).show()
    }


}
