package com.liu.retrofitdemo2.service;

import com.liu.retrofitdemo2.bean.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/26 0026 10:53
 */
public interface GitHubService {


    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);


}
