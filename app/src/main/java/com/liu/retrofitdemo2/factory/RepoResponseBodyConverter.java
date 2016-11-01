package com.liu.retrofitdemo2.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liu.retrofitdemo2.bean.Repo;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/11/1 0001 10:29
 */
public class RepoResponseBodyConverter <T> implements Converter<ResponseBody, T> {

    Type type;
    Gson mGson = new Gson();
    public RepoResponseBodyConverter(Type type){
        this.type=type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {


        String result = value.string();


        if(result.startsWith("[")){

            return (T)parseRepos(result);
        }else {
            return (T)parseRepo(result);
        }


    }



    private List<Repo> parseRepos(String result) {


        List<Repo> repos = new ArrayList<>();
        
        Type type = new TypeToken<ArrayList<Repo>>(){}.getType();

        repos = mGson.fromJson(result, type);


        return  repos;

    }


    private Repo parseRepo(String result) {

        Repo repo = mGson.fromJson(result, Repo.class);
        return repo;
    }
}
