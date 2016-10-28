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

    public final static String SIGN               = "sign";
    //唯一标识
    public final static String APP_KEY            = "appKey";
    //接口版本号
    public final static String VERSION            = "1.0";
    public final static String OS_NAME            = "1002";



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
    }

    private void initListener() {
        mBtnUploadOne.setOnClickListener(this);
        mBtnUploadMany.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnUploadOne:
                uploadOne();
                break;
            case R.id.btnUploadMany:
                uploadMany();
                break;
        }
    }


    /**
     * 上传单个
     * user/imgUpLoad
     * params.put("sign", ConfigureInfo.SIGN);
     params.put("appKey", ConfigureInfo.APP_KEY);
     params.put("osName", ConfigureInfo.OS_NAME);
     params.put("memberNo", memberNo); 13410111258
     params.put("img", iconImg);
     /storage/emulated/0/Pictures/1477553156332.jpg
     /storage/emulated/0/Pictures/1474366085035.jpg
     /storage/emulated/0/Pictures/1474366052345.jpg
     */
    private void uploadOne() {


        String path = "/storage/emulated/0/Pictures/1477553156332.jpg";
        File file = new File(path);

        /**
         * 创建请求体，内容是文件
         */
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        final RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);


        /**
         * 创建多部分拿上面的请求体做参数
         * img 是上传是的参数key,根据需要更改为自己的
         */
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);


        Map<String,String> params = new HashMap<>();
        params.put("sign", SIGN);
        params.put("appKey", APP_KEY);
        params.put("osName", OS_NAME);
        params.put("memberNo", "13410111258");

        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadService uploadService = retrofit.create(UploadService.class);
        Call<String> call  = uploadService.uploadOne(params,body);
       // Call<String> call  = uploadService.uploadOne(SIGN,APP_KEY,OS_NAME,"13410111258",body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi",response.message()+"    "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
L.d("vivi",t.getMessage());
            }
        });

    }

    /**
     * 上传多个
     * user/uploadImages
     * params.put("sign", ConfigureInfo.SIGN);
     params.put("appKey", ConfigureInfo.APP_KEY);

     params.put("osName",ConfigureInfo.OS_NAME);
     params.put("memberNo",memberNo); 13410111258z
     params.put("consumType",consumType); 1001
     /storage/emulated/0/Pictures/1477553156332.jpg
     /storage/emulated/0/Pictures/1474366085035.jpg
     /storage/emulated/0/Pictures/1474522550302.jpg
     /storage/emulated/0/Pictures/1474423699408.jpg
     /storage/emulated/0/Pictures/1477553128722.jpg
     /storage/emulated/0/Pictures/1474365776853.jpg

     */
    private void uploadMany() {


        /**
         * 初始化
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String,String> normalParams = new HashMap<>();
        normalParams.put("sign", SIGN);
        normalParams.put("appKey", APP_KEY);
        normalParams.put("osName", OS_NAME);
        normalParams.put("memberNo", "13410111258");
        normalParams.put("consumType", "1001");



        File [] files = {new File("/storage/emulated/0/Pictures/1477553156332.jpg"),
                new File("/storage/emulated/0/Pictures/1474366085035.jpg"),
                new File("/storage/emulated/0/Pictures/1474522550302.jpg")

        };



        Map<String,RequestBody> fileParams = new HashMap<>();

        for(int i = 0; i < files.length; i++) {


            RequestBody request =RequestBody.create(MediaType.parse("multipart/form-data"), files[i]);

           // MultipartBody.Part body =MultipartBody.Part.createFormData("imagesList", files[i].getName(), request);

            fileParams.put("imagesList\" ;filename=\""+files[i].getName()+"",request);

        }

        fileParams.put("consumType",RequestBody.create(MediaType.parse("multipart/form-data"),"1001"));
        fileParams.put("sign",RequestBody.create(MediaType.parse(null),SIGN));
        fileParams.put("appKey",RequestBody.create(MediaType.parse(null),APP_KEY));
        fileParams.put("osName",RequestBody.create(MediaType.parse(null),OS_NAME));
        fileParams.put("memberNo",RequestBody.create(MediaType.parse(null),"13410111258"));


        UploadService uploadService = retrofit.create(UploadService.class);
        Call<String> call  = uploadService.uploadMany(fileParams);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("vivi",response.message()+"    "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                L.d("vivi",t.getMessage());
                L.d("vivi",t.toString());
            }
        });


    }
}
