package com.liu.retrofitdemo2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liu.retrofitdemo2.R;
import com.liu.retrofitdemo2.callback.HttpCallBack;
import com.liu.retrofitdemo2.service.DownloadService;
import com.liu.retrofitdemo2.util.FileUtils;
import com.liu.retrofitdemo2.util.L;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStatDownload;
    private ProgressBar mPb;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        initView();
        initListener();
    }

    private void initView() {

        mBtnStatDownload = (Button) findViewById(R.id.btnStatDownload);
        mPb = (ProgressBar) findViewById(R.id.pb);
        mTv = (TextView) findViewById(R.id.tv);

    }

    private void initListener() {

        mBtnStatDownload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStatDownload:
                startDownload();
                break;
        }
    }

    private void startDownload() {

            //http://surl.qq.com/195D0D?qbsrc=51&asr=4286

        String downloadUrl = "195D0D?qbsrc=51&asr=4286";

//
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://surl.qq.com/")
                 .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        DownloadService downloadService = retrofit.create(DownloadService.class);

        Call<ResponseBody> responseBodyCall = downloadService.downloadFile(downloadUrl);


        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                L.d("vivi",response.message()+"  length  "+response.body().contentLength()+"  type "+response.body().contentType());

                //建立一个文件
                final File        file          = FileUtils.createFile(DownLoadActivity.this);

                //下载文件放在子线程
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        //保存到本地
                        FileUtils.writeFile2Disk(response, file, new HttpCallBack() {
                            @Override
                            public void onLoading(final long current, final long total) {
                                /**
                                 * 更新进度条
                                 */
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        L.d("vivi",current+" to "+total);
                                        L.d("vivi"," runOnUiThread  "+ currentThread().getName());
                                        mTv.setText(current+"");
                                        mPb.setMax((int) total);
                                        mPb.setProgress((int) current);


                                    }
                                });
                            }
                        });


                    }
                }.start();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                t.printStackTrace();
                L.d("vivi",t.getMessage()+"  "+t.toString());
            }
        });


    }



}
