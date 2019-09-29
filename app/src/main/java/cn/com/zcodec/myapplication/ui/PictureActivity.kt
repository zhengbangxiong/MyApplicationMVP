/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.02
 */

package cn.com.zcodec.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import cn.com.zcodec.myapplication.R
import cn.com.zcodec.myapplication.adapter.RecycleAdapter
import cn.com.zcodec.myapplication.base.BaseActivity
import cn.com.zcodec.myapplication.presenter.PicturePresenter
import cn.com.zcodec.myapplication.contract.PictureContract
import cn.com.zcodec.myapplication.beans.PictureList
import kotlinx.android.synthetic.main.activity_picture.*
import javax.inject.Inject

/**
 * 图片列表显示
 */
class PictureActivity : BaseActivity(), PictureContract.View {

    lateinit var adapter: RecycleAdapter
    @Inject
    lateinit var picturePresenter: PicturePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)

        //控件初始化
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

//        picturePresenter = PicturePresenter(PictureModel(), this)
        lifecycle.addObserver(picturePresenter) //Activity和lifecycle生命周期绑定

        picturePresenter.instanceRecyclerView()

    }

    /**
     * 数据填充成功执行onResult
     */
    override fun onResult(list: List<PictureList>) {
        //列表每一条的点击事件
        adapter = RecycleAdapter(this, list) { pictureList ->
            Toast.makeText(this, "点击" + pictureList.pictureName, Toast.LENGTH_LONG).show() //提示控件
        }
        //控件赋值
        recyclerView.adapter = adapter
    }

    override fun onApiFail(msg: String) {

    }

}
