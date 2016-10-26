package com.liu.retrofitdemo2.service;

import com.liu.retrofitdemo2.bean.RankBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/26 0026 15:20
 */

public interface DayService {


    /**
     * 方法不要加返回值之类的void String。。CAll<String>代表返回值
     * @return
     */
    @GET("rank")
    Call<RankBean> getRank(@Query("key") String key , @Query("area") String area);


    /**
     * 使用@QueryMap
     * @param params
     * @return
     */
    @GET("rank")
    Call<RankBean> getRank2(@QueryMap Map<String,String> params);
}
