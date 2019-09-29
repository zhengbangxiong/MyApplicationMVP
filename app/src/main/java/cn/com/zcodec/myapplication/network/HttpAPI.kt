/**
 * This file is part of the zngin software package
 * @copyright (c) zngin <https://www.zngin.com>
 * @author:zhengbangxiong
 * @date: 2019-08-02
 * @version：1.02
 */
package cn.com.zcodec.myapplication.network

import cn.com.zcodec.myapplication.beans.Login
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.POST
import retrofit2.http.Multipart
import retrofit2.http.PartMap





/**
 * @explain:定义API接口
 */
public interface HttpAPI {

    /**
     * 登录接口
     * @POST：代表post请求
     * @FormUrlEncoded：用表单数据提交
     * @Field：替换参数值，是结合post请求的
     * retrofit
     */
    @POST("cust/login")
    @FormUrlEncoded
    abstract fun PostLogin(@Field("jsonStr") jsonStr: String,
                           @Field("signStr") signStr: String,
                           @Field("equipment") equipment: String): Call<String>

    /**
     * 登录接口
     * @GET：代表get请求
     * @Query：替代参数值，通常是结合get请求的
     * retrofit
     */
    @GET("cust/login")
    abstract fun GetLogin(@Query("jsonStr") jsonStr: String,
                          @Query("signStr") signStr: String,
                          @Query("equipment") equipment: String): Call<String>


    /**
     * 登录接口
     * @POST：代表post请求
     * @FormUrlEncoded：用表单数据提交
     * @Field：替换参数值，是结合post请求的
     * retrofit+rxjava
     */
    @POST("cust/login")
    @FormUrlEncoded
    fun PostLoginRetrofitRxjava(@Field("jsonStr") jsonStr: String,
                                @Field("signStr") signStr: String,
                                @Field("equipment") equipment: String): Observable<String>

    /**
     * 登录接口
     * @POST：代表post请求
     * @FormUrlEncoded：用表单数据提交
     * @Field：替换参数值，是结合post请求的
     * retrofit+rxjava+Gson
     */
    @POST("cust/login")
    @FormUrlEncoded
    fun PostLoginRetrofitRxjavaGson(@Field("jsonStr") jsonStr: String,
                                    @Field("signStr") signStr: String,
                                    @Field("equipment") equipment: String): Observable<Login>

    /**
     * 单张图片上传
     * @Multipart：代表图片上传
     * @POST：代表post请求
     * @date: 2019-08-07
     */
    @Multipart
    @POST("images/appupload")
    fun uploadImage(@Part part: MultipartBody.Part): Observable<String>

    /**
     * 多张图片上传
     * @Multipart：代表图片上传
     * @POST：代表post请求
     * @date: 2019-08-07
     */
    @Multipart
    @POST("images/appupload")
    fun uploadImageMore(@PartMap files: MutableMap<String, RequestBody>): Observable<String>

}