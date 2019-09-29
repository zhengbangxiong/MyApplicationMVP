/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-29
 * @version：1.02
 */
package cn.com.zcodec.myapplication.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import cn.com.zcodec.myapplication.R

/**
 * 加载对话框
 */
class CommonLoadingDialog constructor(context: Context, theme: Int) : Dialog(context, theme) {
    private lateinit var mDialog: CommonLoadingDialog
//    private lateinit var animDrawable: AnimationDrawable

    fun buildDialog(context: Context): CommonLoadingDialog {
        //根据指定主题样式创建Dialog
        mDialog = CommonLoadingDialog(context, R.style.commonDialogStyle)
        //设置Dialog的布局
        mDialog?.setContentView(R.layout.common_loading)
        //点击或按返回键时消失
        mDialog?.setCancelable(true)
        //点击对话框外的部分不消失.
        mDialog?.setCanceledOnTouchOutside(false)
        //设置对话框居中
        mDialog?.window.attributes.gravity = Gravity.CENTER
        val lp = mDialog?.window.attributes
        lp.dimAmount = 0.2f
        //设置属性
        mDialog?.window.attributes = lp
//        //获取对话框中的动画
//        val animView = mDialog.findViewById<ImageView>(R.id.loadingIv)
//        animDrawable = animView.background as AnimationDrawable
        return mDialog
    }

    //显示加载框
    fun showLoading() {
        super.show()
//        animDrawable?.start()
    }
    //关闭加载框
    fun hideLoading() {
        super.dismiss()
//        animDrawable?.stop()
    }
    //更改文本
    fun changeText(text: String){
        mDialog.findViewById<TextView>(R.id.tv_text).setText(text)
    }
}
