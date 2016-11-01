package com.liu.retrofitdemo2.factory;

import com.liu.retrofitdemo2.bean.RankBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/11/1 0001 10:13
 */
public class RankBeanConverterFactory extends Converter.Factory  {






    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {


        if(type == RankBean.class){

            return  new RankBeanResponseBodyConverter<>(type);

        }
        return null;

    }



    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        if(type == RankBean.class){
            return  new RankBeanResponseBodyConverter<>(type);
        }
        return null;
    }
}
