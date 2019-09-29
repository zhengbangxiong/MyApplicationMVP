/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.01
 */
package cn.com.zcodec.myapplication.presenter

import cn.com.zcodec.myapplication.model.PictureModel
import cn.com.zcodec.myapplication.contract.PictureContract
import cn.com.zcodec.myapplication.beans.PictureList
import javax.inject.Inject

/**
 *  图片列表presenter
 */
class PicturePresenter @Inject constructor(
        private val pictureModel: PictureModel, private val pictureView: PictureContract.View
): PictureContract.Presenter{

    fun instanceRecyclerView(){
        pictureModel.pictureList(this)
    }

    override fun success(list: List<PictureList>) {
        pictureView.onResult(list)
    }

    override fun detachView() {
        pictureModel.onDestroy()
    }

}