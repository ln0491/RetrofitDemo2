package com.liu.retrofitdemo2.service;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/27 0027 18:05
 */
public interface UploadService {


    @Multipart
    @POST("user/imgUpLoad")
//    Call<String> uploadOne(@Part("sign") String sign,@Part("appKey") String appKey,@Part("osName") String osName,@Part("memberNo") String memberNo, @Part  MultipartBody.Part file);
//    Call<String> uploadOne(@PartMap Map<String,String> params, @Part  MultipartBody.Part file);

//    Call<String> uploadOne(@Query("sign") String sign, @Query("appKey") String appKey, @Query("osName") String osName, @Query("memberNo") String memberNo, @Part  MultipartBody.Part file);
    Call<String> uploadOne(@QueryMap Map<String,String> params, @Part  MultipartBody.Part file);


    @Multipart
    @POST("user/uploadImage")
    Call<String> uploadMany(@PartMap Map<String, RequestBody> fileParams);

}
