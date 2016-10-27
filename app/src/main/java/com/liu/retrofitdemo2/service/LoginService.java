package com.liu.retrofitdemo2.service;

import com.liu.retrofitdemo2.bean.LoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/27 0027 10:19
 */
public interface LoginService {


    /** 表单提交要加 @FormUrlEncoded
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param deviceId 设置ID
     * @param platform 平台这里是Android
     * @param verId 版本号
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Call<String> login(@Field("loginName") String username,
                       @Field("password") String password,
                       @Field("deviceId") String deviceId,
                       @Field("platform") String platform,
                       @Field("verId") String verId);


    /**
     * 表单提交要加 @FormUrlEncoded
     * Post使用map多参数
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Call<LoginBean> login2(@FieldMap Map<String,String>params);




    @POST("p/userCenter")
    Call<String> getUserCenter(@HeaderMap Map<String,String> headers);

}
