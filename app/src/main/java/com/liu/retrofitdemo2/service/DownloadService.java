package com.liu.retrofitdemo2.service;

import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/27 0027 15:37
 */
public interface DownloadService {


    @Streaming //大文件时要加不然会OOM
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
