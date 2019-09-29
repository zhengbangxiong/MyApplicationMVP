/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-28
 * @version：1.02
 */
package cn.com.zcodec.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.com.zcodec.myapplication.beans.PictureList
import com.bumptech.glide.Glide
import cn.com.zcodec.myapplication.util.Utils
import android.graphics.Bitmap
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import android.os.Environment
import cn.com.zcodec.myapplication.R
import java.io.File

/**
 * pictureActivity的recyclerview的adapter
 */
class RecycleAdapter(val context:Context, val pictureList:List<PictureList>, val itemClick:(PictureList) ->Unit) : RecyclerView.Adapter<RecycleAdapter.Holder>() {

    //onCreateViewHolder():是用来配合写好的ViewHolder来返回一个ViewHolder对象。这里也涉及到了条目布局的加载。
    //viewType则表示需要给当前position生成的是哪一种ViewHolder，这个参数也说明了RecyclerView对多类型ItemView的支持。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view=LayoutInflater.from(context).inflate(R.layout.recyclerview_picture_listitem,parent,false)
        return Holder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return pictureList.count()
    }

    //专门用来绑定ViewHolder里的控件和数据源中position位置的数据。
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bindCategory(pictureList[position],context)
    }

     //定义内部类Holder
     inner class Holder(itemView:View,val itemClick:(PictureList) ->Unit): RecyclerView.ViewHolder(itemView){

         //控件初始化
        val pictureImage=itemView?.findViewById<ImageView>(R.id.pictureImage)
        val pictureName=itemView?.findViewById<TextView>(R.id.pictureName)

        fun bindCategory(pictureList: PictureList, context: Context){
            //点击监听
            itemView.setOnClickListener { itemClick(pictureList) }

            //赋值
            pictureName.text = pictureList.pictureName

            with(pictureList){
                //网络图片地址截取最后的名字，生成本地图片全路径
                val pathPicture = File(Environment.getExternalStorageDirectory().absolutePath,
                        "/pocket/picture/" + path.substring(path.lastIndexOf("/") + 1))


                if(!pathPicture.exists()){ //判断本地图片是否存在
                    //图片不存在加载网络
                    Glide.with(context)
                            .asBitmap()
                            .load(path)
                            .into(object : SimpleTarget<Bitmap>() {
                                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                    //To change body of created functions use File | Settings | File Templates.
                                    //保存网络图片到本地,并显示
                                    Glide.with(context).load(Utils.saveImage(resource, path)).into(pictureImage);
                                }
                            })
                }else{
                    //图片存在加载本地
                    Glide.with(context).load(pathPicture.absolutePath).into(pictureImage);
                }
            }



        }
    }
}