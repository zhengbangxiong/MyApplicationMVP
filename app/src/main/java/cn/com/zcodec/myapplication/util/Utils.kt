/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-12
 * @version：1.02
 */
package cn.com.zcodec.myapplication.util

import android.Manifest
import android.accounts.NetworkErrorException
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import java.io.FileOutputStream
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.*


/**
 * 辅助类
 */
object Utils{

    //判断字符串是否为空
    fun isNullOrEmpty(empty: String): Boolean {
        if ("".equals(empty) || null == empty || "null".equals(empty)) { //判断是否为空，为null
            return true
        }
        return false
    }

    /**
     * 获取读写权限
     * @date: 2019-08-07
     */
    fun getPerssion(activity: Activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            val REQUEST_CODE_CONTACT = 101
            val permissions = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //验证是否许可权限
            for (str in permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    activity.requestPermissions(permissions, REQUEST_CODE_CONTACT)
                }
            }
        }
    }

    fun checkException(ex: Throwable): String {
        Log.e("zz","${ex.message}",ex)
        return when (ex) {
            is SocketTimeoutException -> "连接超时，请检查网络"
            is NetworkErrorException -> "网络连接错误，请检查网络"
            is ConnectException -> "无网络连接，请检查网络"
            is MalformedJsonException, is JsonSyntaxException -> "解析Json异常"
//                is ApiCodeException -> ex.message ?: "empty message"
            else -> "服务繁忙，请联络管理员"
        }
    }

    /**
     * 图片保存,返回本地图片路径
     * @date: 2019-08-28
     */
    fun saveImage(image: Bitmap, path: String): String{
        lateinit var saveImagePath: String

        val imageFileName = path.substring(path.lastIndexOf("/") + 1)
        val storageDir = File(Environment.getExternalStorageDirectory().absolutePath, "/zcodec/picture/")

        var success = true
        if (!storageDir.exists()) {
            success = storageDir.mkdirs()
        }
        if (success) {
            val imageFile = File(storageDir, imageFileName)
            saveImagePath = imageFile.getAbsolutePath()
            try {
                val fout = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fout)
                fout.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return saveImagePath;
    }


    /**
     * rxjava切换线程封装
     */
    fun <T> ioMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }


}