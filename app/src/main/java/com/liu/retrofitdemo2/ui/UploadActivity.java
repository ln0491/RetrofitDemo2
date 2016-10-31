package com.liu.retrofitdemo2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liu.retrofitdemo2.R;
import com.liu.retrofitdemo2.service.UploadService;
import com.liu.retrofitdemo2.util.L;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnUploadOne;
    private Button mBtnUploadMany;

    String BASE_URL = "http://10.18.200.93:8080/trunk/";

    public final static String SIGN    = "sign";
    //唯一标识
    public final static String APP_KEY = "appKey";
    //接口版本号
    public final static String VERSION = "1.0";
    public final static String OS_NAME = "1002";
    private Button mBtnUploadOne2;
    private Button mBtnUploadOne3;
    private Button mBtnUploadMany2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initView();
        initListener();
    }

    private void initView() {
        mBtnUploadOne = (Button) findViewById(R.id.btnUploadOne);
        mBtnUploadMany = (Button) findViewById(R.id.btnUploadMany);
        mBtnUploadMany2 = (Button) findViewById(R.id.btnUploadMany2);
        mBtnUploadOne2 = (Button) findViewById(R.id.btnUploadOne2);
        mBtnUploadOne3 = (Button) findViewById(R.id.btnUploadOne3);
    }

    private void initListener() {
        mBtnUploadOne.setOnClickListener(this);
        mBtnUploadMany.setOnClickListener(this);
        mBtnUploadMany2.setOnClickListener(this);
        mBtnUploadOne2.setOnClickListener(this);
        mBtnUploadOne3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnUploadOne:
                uploadOne();
                break;
            case R.id.btnUploadOne2:
                uploadOne2();
                break;
            case R.id.btnUploadOne3:
                uploadOne3();
                break;
            case R.id.btnUploadMany:
                // uploadMany();
                uploadOne22();
                break;
            case R.id.btnUploadMany2:
                // uploadMany();
                uploadMany();
                break;
        }
    }

    /**
     * 单个 文件上传
     * <p>
     * /storage/emulated/0/Pictures/1477553156332.jpg
     * /storage/emulated/0/Pictures/1474366085035.jpg
     * /storage/emulated/0/Pictures/1474522550302.jpg
     * /storage/emulated/0/Pictures/1474423699408.jpg
     * /storage/emulated/0/Pictures/1477553128722.jpg
     * /storage/emulated/0/Pictures/1474365776853.jpg
     */
    private void uploadOne2() {


        String baseUrl = "http://10.18.200.140:8080/hellosp/";


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();


        UploadService uploadService = retrofit.create(UploadService.class);


        String path = "/storage/emulated/0/Pictures/1477553156332.jpg";


        File file = new File(path);


        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);


        /**
         * 创建多部分拿上面的请求体做参数
         * img 是上传是的参数key,根据需要更改为自己的
         */
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);


        Call<String> call = uploadService.upload2(body);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi", response.message() + "    " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                L.d("vivi", t.getMessage());
            }
        });


    }

    /**
     * 文件上传
     * <p>
     * /storage/emulated/0/Pictures/1477553156332.jpg
     * /storage/emulated/0/Pictures/1474366085035.jpg
     * /storage/emulated/0/Pictures/1474522550302.jpg
     * /storage/emulated/0/Pictures/1474423699408.jpg
     * /storage/emulated/0/Pictures/1477553128722.jpg
     * /storage/emulated/0/Pictures/1474365776853.jpg
     */
    private void uploadOne22() {


        String baseUrl = "http://10.18.200.140:8080/hellosp/";


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();


        UploadService uploadService = retrofit.create(UploadService.class);


        String path  = "/storage/emulated/0/Pictures/1477553156332.jpg";
        String path2 = "/storage/emulated/0/Pictures/1474366085035.jpg";
        // String path3 = "/storage/emulated/0/Pictures/1474522550302.jpg";
        String path4 = "/storage/emulated/0/Pictures/1474423699408.jpg";
        String path5 = "/storage/emulated/0/Pictures/1477553128722.jpg";
        String path6 = "/storage/emulated/0/Pictures/1474365776853.jpg";


        File file  = new File(path);
        File file2 = new File(path2);
        // File file3 = new File(path3);
        File file4 = new File(path4);
        File file5 = new File(path5);
        File file6 = new File(path6);


        File[] files = new File[]{file, file2, file4, file5, file6};


        Map<String, RequestBody> params = new HashMap<>();


        for(int i = 0; i < files.length; i++) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), files[i]);
            params.put("file\"; filename=\"" + files[i].getName() + "", requestBody);
        }






      /*  *//**
         * 创建多部分拿上面的请求体做参数
         * img 是上传是的参数key,根据需要更改为自己的
         *//*
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestBody);
*/

        Call<String> call = uploadService.upload22(params);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi", response.message() + "    " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                L.d("vivi", t.getMessage());
            }
        });


    }

    /**
     * 单个 文件上传
     * /storage/emulated/0/Pictures/1477553156332.jpg
     * /storage/emulated/0/Pictures/1474366085035.jpg
     * /storage/emulated/0/Pictures/1474522550302.jpg
     * /storage/emulated/0/Pictures/1474423699408.jpg
     * /storage/emulated/0/Pictures/1477553128722.jpg
     * /storage/emulated/0/Pictures/1474365776853.jpg
     */
    private void uploadOne3() {



        String baseUrl = "http://10.18.200.140:8080/hellosp/";


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();


        UploadService uploadService = retrofit.create(UploadService.class);


        String path = "/storage/emulated/0/Pictures/1477553156332.jpg";


        File file = new File(path);


        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Map<String, RequestBody> params = new HashMap<>();

        params.put("file\"; filename=\"" + file.getName() + "", requestBody);

        Call<String> call = uploadService.upload3(params);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi", response.message() + "    " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                L.d("vivi", t.getMessage());
            }
        });


    }


    /**
     * 上传单个
     * user/imgUpLoad
     * params.put("sign", ConfigureInfo.SIGN);
     * params.put("appKey", ConfigureInfo.APP_KEY);
     * params.put("osName", ConfigureInfo.OS_NAME);
     * params.put("memberNo", memberNo); 13410111258
     * params.put("img", iconImg);
     * /storage/emulated/0/Pictures/1477553156332.jpg
     * /storage/emulated/0/Pictures/1474366085035.jpg
     * /storage/emulated/0/Pictures/1474366052345.jpg
     */
    private void uploadOne() {


        String path = "/storage/emulated/0/Pictures/1477553156332.jpg";
        File   file = new File(path);

        /**
         * 创建请求体，内容是文件
         */
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //        final RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);


        /**
         * 创建多部分拿上面的请求体做参数
         * img 是上传是的参数key,根据需要更改为自己的
         */
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);


        Map<String, String> params = new HashMap<>();
        params.put("sign", SIGN);
        params.put("appKey", APP_KEY);
        params.put("osName", OS_NAME);
        params.put("memberNo", "13410111258");

        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();

        UploadService uploadService = retrofit.create(UploadService.class);
        Call<String>  call          = uploadService.uploadOne(params, body);
        // Call<String> call  = uploadService.uploadOne(SIGN,APP_KEY,OS_NAME,"13410111258",body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi", response.message() + "    " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                L.d("vivi", t.getMessage());
            }
        });

    }

    /**
     * 上传多个
     * user/uploadImages
     * params.put("sign", ConfigureInfo.SIGN);
     * params.put("appKey", ConfigureInfo.APP_KEY);
     * <p>
     * params.put("osName",ConfigureInfo.OS_NAME);
     * params.put("memberNo",memberNo); 13410111258z
     * params.put("consumType",consumType); 1001
     * /storage/emulated/0/Pictures/1477553156332.jpg
     * /storage/emulated/0/Pictures/1474366085035.jpg
     * /storage/emulated/0/Pictures/1474522550302.jpg
     * /storage/emulated/0/Pictures/1474423699408.jpg
     * /storage/emulated/0/Pictures/1477553128722.jpg
     * /storage/emulated/0/Pictures/1474365776853.jpg
     */
    private void uploadMany() {

        String baseUrl = "http://10.18.200.140:8080/hellosp/";
        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();


        String path  = "/storage/emulated/0/Pictures/1477553156332.jpg";
        String path2 = "/storage/emulated/0/Pictures/1474366085035.jpg";
        // String path3 = "/storage/emulated/0/Pictures/1474522550302.jpg";
        String path4 = "/storage/emulated/0/Pictures/1474423699408.jpg";
        String path5 = "/storage/emulated/0/Pictures/1477553128722.jpg";
        String path6 = "/storage/emulated/0/Pictures/1474365776853.jpg";


        File file  = new File(path);
        File file2 = new File(path2);
        // File file3 = new File(path3);
        File file4 = new File(path4);
        File file5 = new File(path5);
        File file6 = new File(path6);


        File[] files = new File[]{file, file2, file4, file5, file6};


        Map<String, RequestBody> params = new HashMap<>();


        for(int i = 0; i < files.length; i++) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), files[i]);
            params.put("file\"; filename=\"" + files[i].getName() + "", requestBody);
        }


        UploadService uploadService = retrofit.create(UploadService.class);
        Call<String>  call          = uploadService.uploadMany(params);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi", response.message() + "    " + response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                L.d("vivi", t.getMessage());
                L.d("vivi", t.toString());
            }
        });


    }
}
