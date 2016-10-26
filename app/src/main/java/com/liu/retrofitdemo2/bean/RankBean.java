package com.liu.retrofitdemo2.bean;

import java.util.List;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/26 0026 15:43
 */
public class RankBean {



    public String resultcode;
    public String reason;
    public int    error_code;


    public List<ResultEntity> result;

    public static class ResultEntity {
        public String rid;
        public String name;
        public String wk;
        public String wboxoffice;
        public String tboxoffice;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "rid='" + rid + '\'' +
                    ", name='" + name + '\'' +
                    ", wk='" + wk + '\'' +
                    ", wboxoffice='" + wboxoffice + '\'' +
                    ", tboxoffice='" + tboxoffice + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RankBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", error_code=" + error_code +
                ", result=" + result +
                '}';
    }
}
