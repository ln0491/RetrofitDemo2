package com.liu.retrofitdemo2.mydemowrapper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/31 0031 11:39
 */
public class ServiceGenerator {

    //github
    public static final String BASE_URL="https://api.github.com/";

    private ServiceGenerator(){

    }

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
