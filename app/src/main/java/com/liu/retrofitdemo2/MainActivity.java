package com.liu.retrofitdemo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liu.retrofitdemo2.ui.DownLoadActivity;
import com.liu.retrofitdemo2.ui.FirstActivity;
import com.liu.retrofitdemo2.ui.PostNormalActivity;
import com.liu.retrofitdemo2.ui.UploadActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirst;
    private Button mBtnNormalPost;
    private Button mBtnDownload;
    private Button mBtnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {


        mBtnFirst = (Button) findViewById(R.id.btnFirst);
        mBtnNormalPost= (Button) findViewById(R.id.btnNormalPost);
        mBtnDownload= (Button) findViewById(R.id.btnDownload);
        mBtnUpload= (Button) findViewById(R.id.btnUpload);


    }

    private void initListener() {
        mBtnFirst.setOnClickListener(this);
        mBtnNormalPost.setOnClickListener(this);
        mBtnDownload.setOnClickListener(this);
        mBtnUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnFirst:
                //第二天
                goFirstGet();
                break;
            case R.id.btnNormalPost:
                //第一天
               goNormalPost();
                break;case
                    R.id.btnDownload:
                //第三天
               download();
                break;
           case  R.id.btnUpload:
            //第四天
            upload();
            break;
        }

    }

    private void upload() {
        startActivity(new Intent(this, UploadActivity.class));
    }

    private void download() {

        startActivity(new Intent(this, DownLoadActivity.class));
    }

    /**
     * 正常的POST请求
     */
    private void goNormalPost() {

        startActivity(new Intent(this, PostNormalActivity.class));

    }

    /**
     * 第一天
     */
    private void goFirstGet() {


        startActivity(new Intent(this, FirstActivity.class));

    }
}
