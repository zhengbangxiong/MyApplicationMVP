/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.01
 */
package cn.com.zcodec.myapplication.contract

import cn.com.zcodec.myapplication.base.BaseView
import cn.com.zcodec.myapplication.presenter.PicturePresenter
import cn.com.zcodec.myapplication.beans.PictureList

/**
 * 登录Contract
 */
interface PictureContract {

    interface View : BaseView.View {

        fun onResult(list: List<PictureList>)
    }

    interface Presenter : BaseView.Presenter {
        fun success(list: List<PictureList>)
    }

    interface Model : BaseView.Model {
        fun pictureList(picturePresenter: PicturePresenter)
    }

}