/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-06
 * @version：1.01
 */
package cn.com.zcodec.myapplication.beans

/**
 * 登录对象类，相当于java的get和set
 */
data class Login(val result_description: String, val phone: String, val result_no: String,
                 val login_token: String)