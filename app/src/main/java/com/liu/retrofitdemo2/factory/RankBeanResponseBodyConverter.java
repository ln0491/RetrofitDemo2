package com.liu.retrofitdemo2.factory;

import com.google.gson.Gson;
import com.liu.retrofitdemo2.bean.RankBean;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/11/1 0001 10:17
 */
public class RankBeanResponseBodyConverter <T> implements Converter<ResponseBody, T> {


    private Type type;
    Gson gson = new Gson();

    public RankBeanResponseBodyConverter(Type type){
        this.type=type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String val = value.string();

        RankBean rankBean = gson.fromJson(val, RankBean.class);

        return (T)rankBean;
    }
}
