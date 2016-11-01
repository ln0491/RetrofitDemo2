package com.liu.retrofitdemo2.factory;

import com.liu.retrofitdemo2.bean.Repo;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/11/1 0001 10:26
 */
public class RepoConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {


        if(type == Repo.class){

            return  new RepoResponseBodyConverter(type);


        }else if(type instanceof ParameterizedType){
            ////支持返回值是List<Repo>

            Type rawType = ((ParameterizedType) type).getRawType();

            Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];

            if(rawType==List.class&&actualType==Repo.class){
                return  new RepoResponseBodyConverter(type);

            }

        }

        return null;


    }


}
