package com.liu.retrofitdemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liu.retrofitdemo2.ui.FirstActivity;
import com.liu.retrofitdemo2.ui.PostNormalActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirst;
    private Button mBtnNormalPost;

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


    }

    private void initListener() {
        mBtnFirst.setOnClickListener(this);
        mBtnNormalPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnFirst:
                //第一天
                goFirstGet();
                break;
            case R.id.btnNormalPost:
                //第一天
               goNormalPost();
                break;
        }

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
