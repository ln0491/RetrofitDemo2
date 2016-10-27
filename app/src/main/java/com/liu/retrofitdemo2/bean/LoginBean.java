package com.liu.retrofitdemo2.bean;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/27 0027 11:06
 */
public class LoginBean {

    /**
     * {"msg":"OK","result":{"accessToken":"A0BdgCrMG22CwfSKrlsEPAs3ZAozND","securiyCode":"5jUduYno","time":null,"userId":"402882823a9d5e0d013a9dabe3680002","userName":"home","verId":"V1.0"},"status":1,"verId":"1.0"}
     */

    public String       msg;


    public ResultEntity result;
    public int    status;
    public String verId;

    public static class ResultEntity {
        public String accessToken;
        public String securiyCode;
        public String time;
        public String userId;
        public String userName;
        public String verId;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "accessToken='" + accessToken + '\'' +
                    ", securiyCode='" + securiyCode + '\'' +
                    ", time=" + time +
                    ", userId='" + userId + '\'' +
                    ", userName='" + userName + '\'' +
                    ", verId='" + verId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "msg='" + msg + '\'' +
                ", result=" + result +
                ", status=" + status +
                ", verId='" + verId + '\'' +
                '}';
    }
}
