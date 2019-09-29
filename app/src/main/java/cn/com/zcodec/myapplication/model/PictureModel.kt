/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.01
 */
package cn.com.zcodec.myapplication.model

import cn.com.zcodec.myapplication.presenter.PicturePresenter
import cn.com.zcodec.myapplication.contract.PictureContract
import cn.com.zcodec.myapplication.beans.PictureList
import javax.inject.Inject

/**
 * 图片列表model
 */
class PictureModel @Inject constructor(): PictureContract.Model{

    val list = listOf(PictureList("1", "/storage/emulated/0/DCIM/Camera/VID_20180128_115615.jpg"),
            PictureList("2", "/storage/emulated/0/DCIM/Camera/VID_20180128_115929.jpg"),
            PictureList("3", "/storage/emulated/0/DCIM/Camera/VID_20180128_130604.jpg"))

//    private val disposables by lazy { CompositeDisposable() }

    override fun pictureList(picturePresenter: PicturePresenter) {
        picturePresenter.success(list)
    }

    override fun onDestroy() {
//        disposables.clear()
    }

}