package com.liu.retrofitdemo2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.retrofitdemo2.R;
import com.liu.retrofitdemo2.bean.RankBean;
import com.liu.retrofitdemo2.bean.Repo;
import com.liu.retrofitdemo2.service.DayService;
import com.liu.retrofitdemo2.service.GitHubService;
import com.liu.retrofitdemo2.util.L;
import com.liu.retrofitdemo2.wrapper.RetrofitWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                getRank();
                break;
            case R.id.btnGetParams2:
                getQueryMap();
                break;
        }
    }

    /**
     * 使用QueryMap传参数
     */
    private void getQueryMap() {
        String baseUrl = "http://v.juhe.cn/boxoffice/";
        String appKey = "a915893513986a67fdac70235db2c0af";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DayService dayService = retrofit.create(DayService.class);


        /**
         * 传入多个参数，自己写key-value
         */
        Map<String,String> params = new HashMap<>();

        params.put("key",appKey);
        params.put("area","HK");
        Call<RankBean> rank2Call = dayService.getRank2(params);

        rank2Call.enqueue(new Callback<RankBean>() {
            @Override
            public void onResponse(Call<RankBean> call, Response<RankBean> response) {
                Toast.makeText(FirstActivity.this, "response  \n"+response.body().toString(), Toast.LENGTH_SHORT).show();
                L.d("vivi",response.message()+ "    "+ response.body().toString());
                mTvResult.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<RankBean> call, Throwable t) {
                t.printStackTrace();
            }
        });




    }

    /**
     *  http://v.juhe.cn/boxoffice/rank
     AppKey：a915893513986a67fdac70235db2c0af
     * 请求参数说明：
     area	string	是	票房榜的区域,CN-内地，US-北美，HK-香港
     key	string	是	应用APPKEY(应用详细页查询)
     dtype	string	否	返回数据的格式,xml/json，默认json
     */
    private void getRank() {

    String baseUrl = "http://v.juhe.cn/boxoffice/";

        String appKey = "a915893513986a67fdac70235db2c0af";


        Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DayService dayService = retrofit.create(DayService.class);


        Call<RankBean> call = dayService.getRank(appKey,"CN");



        call.enqueue(new Callback<RankBean>() {
            @Override
            public void onResponse(Call<RankBean> call, Response<RankBean> response) {
                mTvResult.setText(response.body().toString());
                L.d("vivi",response.message()+ "    "+ response.body().toString());
            }

            @Override
            public void onFailure(Call<RankBean> call, Throwable t) {

                t.printStackTrace();
            }
        });



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
