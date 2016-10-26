package com.liu.retrofitdemo2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.retrofitdemo2.R;
import com.liu.retrofitdemo2.bean.Repo;
import com.liu.retrofitdemo2.service.GitHubService;
import com.liu.retrofitdemo2.util.L;
import com.liu.retrofitdemo2.wrapper.RetrofitWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnFirstGet;
    private Button mBtnGetParams;
    private Button mBtnGetParams2;
    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        initListener();
    }

    private void initView() {


        mBtnFirstGet = (Button) findViewById(R.id.btnFirstGet);
        mBtnGetParams = (Button) findViewById(R.id.btnGetParams);
        mBtnGetParams2 = (Button) findViewById(R.id.btnGetParams2);
        mTvResult = (TextView) findViewById(R.id.tvResult);


    }

    private void initListener() {
        mBtnFirstGet.setOnClickListener(this);
        mBtnGetParams.setOnClickListener(this);
        mBtnGetParams2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnFirstGet:
                firstGet();
                break;
            case R.id.btnGetParams:
                break;
            case R.id.btnGetParams2:
                break;
        }
    }

    /**
     * 第一个get请求
     */
    private void firstGet() {


        //建立retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                //添加返回字符串的支持--不知道返回的是什么，添加字符串支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //添加GSON转换支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取接口
        GitHubService service = retrofit.create(GitHubService.class);

        //调用方法-返回 回调更换为对象
        Call<List<Repo>> call = service.listRepos("octocat");

        //异步调用
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                L.d("vivi",response.message()+"  "+response.body());
                mTvResult.setText(response.message()+" \n结果: "+response.body().toString());
                Toast.makeText(FirstActivity.this, "结果:\n "+response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

                t.printStackTrace();
                mTvResult.setText(t.getMessage());

            }
        });


        Call<List<Repo>> callLn0941 = RetrofitWrapper.getInstance().create(GitHubService.class).listRepos("octocat");

        callLn0941.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                L.d("vivi",response.message()+"  "+response.body());
                mTvResult.setText(response.message()+" \n结果: "+response.body().toString());
                Toast.makeText(FirstActivity.this, "结果:\n "+response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
                mTvResult.setText(t.getMessage());

            }
        });

    }
}
