/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-29
 * @version：1.01
 */
package cn.com.zcodec.myapplication.base

import android.app.Fragment
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.com.zcodec.myapplication.R
import cn.com.zcodec.myapplication.dialog.CommonLoadingDialog
import cn.com.zcodec.myapplication.util.KeyboardktUtils
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), HasFragmentInjector, HasSupportFragmentInjector {

    /**
     * dagger2扩展库
     */
    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    @Inject
    lateinit var frameworkFragmentInjector: DispatchingAndroidInjector<Fragment>

    /**
     * 加载对话框
     */
    lateinit var loadingdialog: CommonLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

    }

    /**
     * 初始化对话框并且显示
     */
    fun showLoadDialog(){
        if(this::loadingdialog.isInitialized){//重要，this::前缀是必须的。
           //如果已经初始化了，返回true 　
            if(!loadingdialog.isShowing){
                loadingdialog.showLoading()
                showDialogAnimDrawable()

            }
        }else{
            loadingdialog = CommonLoadingDialog(this, R.style.commonDialogStyle).buildDialog(this)
            loadingdialog.showLoading()
            showDialogAnimDrawable()
        }
    }

    //获取对话框中的动画
    fun showDialogAnimDrawable(){
        val animView = loadingdialog.findViewById<ImageView>(R.id.loadingIv)
        val animDrawable = animView.background as AnimationDrawable
        animDrawable.start()
    }

    /**
     * 对话框隐藏
     */
    fun dmissLoadDialog(){
        loadingdialog?.let {
            loadingdialog?.hideLoading()
        }
    }


    /**
     * 点击空白处，键盘隐藏
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.getAction() == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (KeyboardktUtils.isShouldHideKeyboard(v, ev)) {
                KeyboardktUtils.hideKeyboard(currentFocus)
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    override fun fragmentInjector(): AndroidInjector<Fragment> = frameworkFragmentInjector

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = supportFragmentInjector

}