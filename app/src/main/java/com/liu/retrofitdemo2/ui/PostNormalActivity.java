package com.liu.retrofitdemo2.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liu.retrofitdemo2.R;
import com.liu.retrofitdemo2.bean.LoginBean;
import com.liu.retrofitdemo2.service.LoginService;
import com.liu.retrofitdemo2.util.L;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PostNormalActivity extends AppCompatActivity implements View.OnClickListener {


    private  String baseUrl = "http://10.18.200.55:8080/shop_app/";
    private   String PLATFORM="Android";
    private  String VERSION_ID = "V1.0";

    //登录
    private Button mBtnLogin;
    private Button mBtnLoginMap;
    private LoginBean mLoginBean;
    private Button mBtnUseHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_normal);
        initView();
        initListener();
    }

    private void initView() {

        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLoginMap = (Button) findViewById(R.id.btnLoginMap);
        mBtnUseHeader = (Button) findViewById(R.id.btnUseHeader);
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnLoginMap.setOnClickListener(this);
        mBtnUseHeader.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                //登录
                login();
                break;
            case R.id.btnLoginMap:
                //登录
                login2();
                break; case R.id.btnUseHeader:
                //登录
                getUserInfo();
                break;
        }
    }

    /**
     * Post使用map
     */
    private void login2() {


        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("loginName", "home123");
        hashMap.put("password", "123456");
        hashMap.put("deviceId", getDeviceId());
        hashMap.put("platform", PLATFORM);
        hashMap.put("verId", VERSION_ID);


        //生成对象的Service
        LoginService loginService = retrofit.create(LoginService.class);

        //调用方法得到Call
        Call<LoginBean> loginCall = loginService.login2(hashMap);


        //异步执行
        loginCall.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {

               // L.d("vivi",response.message()+"   "+response.body().toString());

                if(response.isSuccessful()){


                    mLoginBean = response.body();
                    Toast.makeText(PostNormalActivity.this, "token "+mLoginBean.result.accessToken, Toast.LENGTH_SHORT).show();
                    L.d("vivi",response.message()+"   "+response.body().toString());

                }else {
                    Toast.makeText(PostNormalActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

                L.d("vivi",t.getMessage());
                L.d("vivi",t.toString());
            }
        });

    }

    /**
     * 登录
     */
    private void login(){

        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //生成对象的Service
        LoginService loginService = retrofit.create(LoginService.class);
        //调用方法得到Call
        Call<String> loginCall      = loginService.login("home123",
                "123456",
                getDeviceId(),
                PLATFORM,
                VERSION_ID);


        //异步执行
        loginCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                L.d("vivi",response.message()+"   "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

               L.d("vivi",t.getMessage());
               L.d("vivi",t.toString());
            }
        });


    }


    private void getUserInfo(){

        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //生成对象的Service
        LoginService loginService = retrofit.create(LoginService.class);
      /*  SimpleDateFormat     dateFormat   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String              date  = dateFormat.format(new Date());*/

        /**
         * 这里测试参数写死了，实际可以改变，
         */
        Map<String, String> headers = new HashMap<String, String>();
       /* headers.put("userId", "402882823a9d5e0d013a9dabe3680002");
        headers.put("userName", "home");
        headers.put("accessToken", "A0BdgCrMG22CwfSKrlsEPAs3ZAozND");
        //当前时间，因为后台加密，原因写死
        headers.put("time", "2016-10-27 13:28:07");
        headers.put("verId","V1.0");

    //这个是加载后的数据后台接收后，同样有一个比对
        String validateCode = "674BA63B66D0D4A1E6C9EED16CE193C7";
        headers.put("code", validateCode);
*/

        Call<String> userCall = loginService.getUserCenter(headers);
        userCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi",response.message()+"   "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.d("vivi",t.getMessage());
                L.d("vivi",t.toString());
            }
        });

    }

    /**
     * 获取设备号
     * @return
     */
    private String getDeviceId(){


        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


        String deviceId = tm.getDeviceId();
        L.d("vivi",deviceId);

        return deviceId;
    }




}
