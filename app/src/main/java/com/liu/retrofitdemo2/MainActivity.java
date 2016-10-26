package com.liu.retrofitdemo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liu.retrofitdemo2.ui.FirstActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {


        mBtnFirst = (Button) findViewById(R.id.btnFirst);


    }

    private void initListener() {
        mBtnFirst.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnFirst:
                //第一天
                goFirstGet();
                break;
        }

    }

    /**
     * 第一天
     */
    private void goFirstGet() {


        startActivity(new Intent(this, FirstActivity.class));

    }
}
